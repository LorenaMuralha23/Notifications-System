package com.kingcode.demo.connections;

import com.kingcode.demo.constants.RabbitMQConstants;
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
    
    private Queue createQueue(String queueName){
        return new Queue(queueName, true, false, false);
    }
    
    private DirectExchange createExchange(String exchangeName){
        return new DirectExchange(exchangeName, true, false, null);
    }
    
    private Binding createBinding(String queueName, String exchangeName, String routingKey){
        return new Binding(queueName, Binding.DestinationType.QUEUE, exchangeName, routingKey, null);
    }
    
    @PostConstruct
    private void connectionSettings(){
        //creating queues
        Queue orderCreatedQueue = createQueue(RabbitMQConstants.ORDER_CREATED_QUEUE_NAME);
        Queue orderConfirmedQueue = createQueue(RabbitMQConstants.ORDER_CONFIRMED_QUEUE_NAME);
        Queue orderCancelledQueue = createQueue(RabbitMQConstants.ORDER_CANCELLED_QUEUE_NAME);
        
        Queue paymentStatusQueue = createQueue(RabbitMQConstants.PAYMENT_STATUS_QUEUE_NAME);
        
        
        //creating Exchange
        DirectExchange orderExchange = createExchange(RabbitMQConstants.ORDER_EXCHANGE_NAME);
        DirectExchange paymentExchange = createExchange(RabbitMQConstants.PAYMENT_EXCHANGE_NAME);
        
        //creating Bindings
        Binding bindigOrderCreated = createBinding(orderCreatedQueue.getName(), orderExchange.getName(), "order.created");
        Binding bindigOrderConfirmed = createBinding(orderConfirmedQueue.getName(), orderExchange.getName(), "order.confirmed");
        Binding bindigOrderCancelled = createBinding(orderCancelledQueue.getName(), orderExchange.getName(), "order.cancelled");
        Binding bindigPaymentStatus = createBinding(paymentStatusQueue.getName(), paymentExchange.getName(), "payment.status");
        
        this.admin.declareQueue(orderCreatedQueue);
        this.admin.declareQueue(orderConfirmedQueue);
        this.admin.declareQueue(orderCancelledQueue);
        this.admin.declareQueue(paymentStatusQueue);
        
        this.admin.declareExchange(orderExchange);
        this.admin.declareExchange(paymentExchange);
        
        this.admin.declareBinding(bindigOrderCreated);
        this.admin.declareBinding(bindigOrderConfirmed);
        this.admin.declareBinding(bindigOrderCancelled);
        this.admin.declareBinding(bindigPaymentStatus);
    }
}
