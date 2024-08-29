package com.kingcode.demo.configurations;

import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ReturnsCallback;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("localhost");
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("123456");

        connectionFactory.setPublisherConfirmType(CachingConnectionFactory.ConfirmType.CORRELATED);
        return connectionFactory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);

        rabbitTemplate.setConfirmCallback((CorrelationData correlationData, boolean ack, String cause) -> {
            if (ack) {
                System.out.println("[SERVER SAYS] MESSAGE RECEIVED BY BROKER SUCCESSFULLY [SERVER SAYS]");
            } else {
                System.out.println("[SERVER SAYS] AN ERROR OCCURRED WHILE SENDING THE MESSAGE [SERVER SAYS]");
                System.out.println("Reason: " + cause);
            }
        });

        rabbitTemplate.setReturnsCallback(new ReturnsCallback() {
            @Override
            public void returnedMessage(ReturnedMessage returned) {
                System.out.println("[SERVER SAYS] ======= MESSAGE RETURNED ======== [SERVER SAYS]");
                System.out.println("Reply Code: " + returned.getReplyCode());
                System.out.println("Reply Text: " + returned.getReplyText());
                System.out.println("Exchange: " + returned.getExchange());
                System.out.println("Routing Key: " + returned.getRoutingKey());

                System.out.println("[SERVER SAYS] MESSAGE SENT SUCCESSFULLY BUT NO ROUTE WAS FOUND [SERVER SAYS]");
            }
        });

        rabbitTemplate.setMandatory(true);

        return rabbitTemplate;
    }
}
