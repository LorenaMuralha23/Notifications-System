package com.kingcode.demo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.amqp.AmqpException;
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
        try {
            // Simulando uma exceção para testar o retry
            if ("simulateError".equals(routingKey)) {
                throw new AmqpException("Simulated AMQP Exception for testing");
            }

            String messageJSON = this.objectMapper.writeValueAsString(message);

            System.out.println("[SERVER SAYS] SENDING MESSAGE... [SERVER SAYS]");

            this.rabbitTemplate.convertAndSend(exchange, routingKey, messageJSON, m -> {
                m.getMessageProperties().setContentType("application/json");
                return m;
            });
            
            System.out.println("[SERVER SAYS] MESSAGE SENDED [SERVER SAYS]");
        return new ResponseEntity("[SERVER SAYS] Your message has been sent successfully [SERVER SAYS]", HttpStatus.OK); // Retorna OK se a mensagem foi enviada com sucesso

        } catch (JsonProcessingException ex) {
            System.out.println("Error processing JSON. Trying again...");
            throw new RuntimeException("Error processing JSON", ex);
        } catch (AmqpException ex) {
            System.out.println("Error sending message to RabbitMQ. Trying again...");
            throw ex; // Re-lançar para acionar o retry
        } catch (Exception ex) {
            System.out.println("Unexpected error. Trying again...");
            throw new RuntimeException("Unexpected error", ex);
        }

    }

    @Recover
    public ResponseEntity recover(AmqpException ex, String exchange, String routingKey, Object message) {
        System.out.println("[SERVER SAYS] ALL RETRIES FAILED. PLEASE, TRY AGAIN LATER. [SERVER SAYS]");
        return new ResponseEntity("[SERVER SAYS] An internal error occurred while sending your message. Please try again later [SERVER SAYS]", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
