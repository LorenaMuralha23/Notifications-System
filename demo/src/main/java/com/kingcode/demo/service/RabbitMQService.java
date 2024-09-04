package com.kingcode.demo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.main.lib.constants.RabbitMQConstants;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
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
            value = {AmqpException.class, JsonProcessingException.class, Exception.class}, // Especifica a(s) exceção(ões) que devem acionar o retry
            maxAttempts = 5,
            backoff = @Backoff(2000) // Configuração do backoff
    )
    public ResponseEntity sendMessage(String exchange, String routingKey, Object message) {
        final String responseQueueName;
        try {
            String correlationId = UUID.randomUUID().toString();
            String messageJSON = this.objectMapper.writeValueAsString(message);
            

            System.out.println("[SERVER SAYS] SENDING MESSAGE... [SERVER SAYS]\n");

            if (exchange.equals(RabbitMQConstants.EMAIL_EXCHANGE_NAME)) {
                responseQueueName = RabbitMQConstants.EMAIL_RESPONSE_QUEUE_NAME;
            }else{
                 responseQueueName = RabbitMQConstants.SMS_RESPONSE_QUEUE_NAME;
            }
            
            this.rabbitTemplate.convertAndSend(exchange, routingKey, messageJSON, m -> {
                m.getMessageProperties().setContentType("application/json");
                m.getMessageProperties().setCorrelationId(correlationId);
                m.getMessageProperties().setReplyTo(responseQueueName);
                return m;
            });

            Message responseMessage = rabbitTemplate.receive(responseQueueName, TimeUnit.SECONDS.toMillis(60));

            if (responseMessage == null) {
                System.out.println("[SERVER SAYS] NO RESPONSE RECEIVED, TIMEOUT [SERVER SAYS]\n");
                return new ResponseEntity("[SERVER SAYS] No response received, please try again later [SERVER SAYS]", HttpStatus.REQUEST_TIMEOUT);
            }

            String responseBody = new String(responseMessage.getBody());

            System.out.println("Resposta que chega: " + responseBody);
            boolean success = false;

            if (responseBody.equals("\"true\"")) {
                success = true;
            }

            System.out.println("Success: " + success);

            if (success) {
                System.out.println("[SERVER SAYS] MESSAGE HAS BEEN SENT SUCCESSFULLY! [SERVER SAYS]\n");
                return new ResponseEntity("[SERVER SAYS] Your message has been sent successfully [SERVER SAYS]", HttpStatus.OK);
            } else {
                System.out.println("[SERVER SAYS] FAILED TO SEND MESSAGE! [SERVER SAYS]\n");
                return new ResponseEntity("[SERVER SAYS] Failed to send your message, please try again later [SERVER SAYS]", HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } catch (JsonProcessingException ex) {
            System.out.println("[SERVER ERROR] Error processing JSON. Trying again... [SERVER ERROR]\n");
            throw new RuntimeException("Error processing JSON", ex);
        } catch (AmqpException ex) {
            System.out.println("[SERVER ERROR] Error sending message to RabbitMQ. Trying again... [SERVER ERROR]\n");
            System.out.println(ex.getMessage());
            throw ex;
        } catch (Exception ex) {
            System.out.println("[SERVER ERROR] Unexpected error. Trying again... [SERVER ERROR]\n");
            throw new RuntimeException("Unexpected error", ex);
        }

    }

    @Recover
    public ResponseEntity recover(AmqpException ex, String exchange, String routingKey, Object message) {
        System.out.println("[SERVER SAYS] ALL RETRIES FAILED. PLEASE, TRY AGAIN LATER. [SERVER SAYS]");
        return new ResponseEntity("[SERVER SAYS] An internal error occurred while sending your message. Please try again later [SERVER SAYS]", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
