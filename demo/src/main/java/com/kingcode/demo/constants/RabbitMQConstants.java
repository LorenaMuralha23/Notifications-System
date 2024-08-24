package com.kingcode.demo.constants;

public class RabbitMQConstants {
    
    //Queue's names
    public static final String ORDER_CONFIRMED_QUEUE_NAME = "order-confirmed-queue";
    public static final String ORDER_CANCELLED_QUEUE_NAME = "order-cancelled-queue";
    public static final String ORDER_CREATED_QUEUE_NAME = "order-created-queue";
    public static final String PAYMENT_STATUS_QUEUE_NAME = "payment-status-queue";
    
    
    //Exchange's names
    public static final String ORDER_EXCHANGE_NAME = "order-notifications-exchange";
    public static final String PAYMENT_EXCHANGE_NAME = "payment-notifications-exchange";
    
    //Binding's names
    public static final String ORDER_CONFIRMED_BINDING = "order.confirmed";
    public static final String ORDER_CANCELLED_BINDING = "order.cancelled";
    public static final String ORDER_CREATED_BINDING = "order.created";
    
}
