//package com.kingcode.demo.configurations;
//
//import org.springframework.amqp.rabbit.connection.ConnectionFactory;
//import org.springframework.amqp.rabbit.connection.CorrelationData;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.amqp.rabbit.core.RabbitTemplate.ConfirmCallback;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class RabbitMQConfig {
//
//    @Bean
//    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
//        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
//
//        rabbitTemplate.setConfirmCallback(new ConfirmCallback() {
//            @Override
//            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
//                if (ack) {
//                    // Mensagem confirmada com sucesso
//                    System.out.println("Mensagem confirmada com sucesso.");
//                } else {
//                    // Mensagem não confirmada; 'cause' fornece detalhes do erro
//                    System.out.println("Falha ao confirmar a mensagem: " + cause);
//                }
//            }
//
//        });
//        // Adicione logs para verificar a configuração
//        System.out.println("RabbitTemplate configurado com sucesso.");
//        return rabbitTemplate;
//    }
//}
