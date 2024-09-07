package com.kingcode.demo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.main.lib.constants.RabbitMQConstants;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
@EnableRetry
public class RabbitMQService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Retryable(
            value = {AmqpException.class},
            maxAttempts = 5,
            backoff = @Backoff(30000)
    )
    public ResponseEntity sendMessageOnce(String exchange, String routingKey, Object message) throws JsonProcessingException, AmqpException {
        try {
            String correlationId = UUID.randomUUID().toString();
            String messageJSON = this.objectMapper.writeValueAsString(message);

            System.out.println("\n[SERVER SAYS] SENDING MESSAGE... [SERVER SAYS]");

            String responseQueueName = getResponseQueueName(exchange);

            this.rabbitTemplate.convertAndSend(exchange, routingKey, messageJSON, m -> {
                m.getMessageProperties().setContentType("application/json");
                m.getMessageProperties().setCorrelationId(correlationId);
                m.getMessageProperties().setReplyTo(responseQueueName);
                return m;
            });

            return new ResponseEntity("Success", HttpStatus.OK);
        } catch (AmqpException e) {
            System.out.println("[SERVER SAYS] RABBITMQ CONNECTION ERROR OCCURRED. TRYING AGAIN... [SERVER SAYS]\n");
            throw e;
        }
    }

    @Retryable(
            value = {TimeoutException.class},
            maxAttempts = 5,
            backoff = @Backoff(30000)
    )
    public ResponseEntity waitForResponse(String exchange) throws TimeoutException, JsonProcessingException {
        System.out.println("[SERVER SAYS] WAITING FOR RESPONSE... [SERVER SAYS]");

        String responseQueueName = getResponseQueueName(exchange);

        Message responseMessage = rabbitTemplate.receive(responseQueueName, TimeUnit.SECONDS.toMillis(10));

        if (responseMessage == null) {
            throw new TimeoutException("[SERVER SAYS] NO RESPONSE RECEIVED, TIMEOUT. [SERVER SAYS]");
        }

        String responseBody = new String(responseMessage.getBody());
        boolean success = responseBody.equals("\"true\"");

        if (success) {
            System.out.println("[SERVER SAYS] MESSAGE HAS BEEN SENT SUCCESSFULLY! [SERVER SAYS]\n");
            return new ResponseEntity("[SERVER SAYS] Your message has been sent successfully [SERVER SAYS]", HttpStatus.OK);
        } else {
            System.out.println("[SERVER SAYS] FAILED TO SEND MESSAGE! [SERVER SAYS]\n");
            return new ResponseEntity("[SERVER SAYS] Failed to send your message, please try again later [SERVER SAYS]", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Recover
    public ResponseEntity recoverTimeoutException(TimeoutException ex, String exchange, String routingKey, Object message) throws JsonProcessingException {
        System.out.println("[SERVER SAYS] ALL RETRIES FAILED. PLEASE, TRY AGAIN LATER. [SERVER SAYS]");
        return new ResponseEntity("[SERVER SAYS] AN INTERNAL ERROR OCCURRED WHILE SENDING YOUR MESSAGE. WE WILL INVESTIGATE THE CAUSE. PLEASE TRY AGAIN LATER [SERVER SAYS]", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private String getResponseQueueName(String exchange) {
        if (exchange.equals(RabbitMQConstants.EMAIL_EXCHANGE_NAME)) {
            return RabbitMQConstants.EMAIL_RESPONSE_QUEUE_NAME;
        } else {
            return RabbitMQConstants.SMS_RESPONSE_QUEUE_NAME;
        }
    }

}
