package com.kingcode.demo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public CompletableFuture<Boolean> sendMessage(String exchange, String routingKey, Object message) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                String messageJSON = this.objectMapper.writeValueAsString(message);
                
                CompletableFuture<Boolean> confirmationFuture = new CompletableFuture<>();
                
                System.out.println("[SERVER SAYS] SENDING MESSAGE... [SERVER SAYS]");
                
                this.rabbitTemplate.convertAndSend(exchange, routingKey, messageJSON, m -> {
                    m.getMessageProperties().setContentType("application/json");
                    return m;
                });

                return confirmationFuture.join();
            } catch (JsonProcessingException ex) {
                Logger.getLogger(RabbitMQService.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        });
    }

}
