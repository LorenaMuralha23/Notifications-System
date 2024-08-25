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
    
    public static final String ORDER_CONFIRMED_ROUTING_KEY = "order.confirmed";
    public static final String ORDER_CANCELLED_ROUTING_KEY = "order.cancelled";
    public static final String ORDER_CREATED_ROUTING_KEY = "order.created";
    
}
