package com.kingcode.demo.connections;

import com.mycompany.main.lib.constants.RabbitMQConstants;
import jakarta.annotation.PostConstruct;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConnection {

    private AmqpAdmin admin;

    public RabbitMQConnection(AmqpAdmin admin) {
        this.admin = admin;
    }

    private Queue createQueue(String queueName) {
        return new Queue(queueName, true, false, false);
    }

    private DirectExchange createExchange(String exchangeName) {
        return new DirectExchange(exchangeName, true, false, null);
    }

    private Binding createBinding(String queueName, String exchangeName, String routingKey) {
        return new Binding(queueName, Binding.DestinationType.QUEUE, exchangeName, routingKey, null);
    }

    @PostConstruct
    private void connectionSettings() throws Exception {
        System.out.println("Tentando configurar RabbitMQ...");
        //creating queues
        Queue emailQueue = createQueue(RabbitMQConstants.EMAIL_QUEUE_NAME);
        Queue smsQueue = createQueue(RabbitMQConstants.SMS_QUEUE_NAME);

        //creating Exchange
        DirectExchange emailExchange = createExchange(RabbitMQConstants.EMAIL_EXCHANGE_NAME);
        DirectExchange smsExchange = createExchange(RabbitMQConstants.SMS_EXCHANGE_NAME);

        //creating Bindings
        Binding bindingEmail = createBinding(emailQueue.getName(), emailExchange.getName(), RabbitMQConstants.EMAIL_ROUTING_KEY);
        Binding bindingSms = createBinding(smsQueue.getName(), smsExchange.getName(), RabbitMQConstants.SMS_ROUTING_KEY);

        this.admin.declareQueue(emailQueue);
        this.admin.declareQueue(smsQueue);

        this.admin.declareExchange(emailExchange);
        this.admin.declareExchange(smsExchange);

        this.admin.declareBinding(bindingEmail);
        this.admin.declareBinding(bindingSms);

    }
    
}
