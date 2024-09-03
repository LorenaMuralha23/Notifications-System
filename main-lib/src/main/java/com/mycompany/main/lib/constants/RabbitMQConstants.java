package com.mycompany.main.lib.constants;

public class RabbitMQConstants {
    
    //Queue's names
    public static final String EMAIL_QUEUE_NAME = "email-notification-queue";
    public static final String SMS_QUEUE_NAME = "sms-notification-queue";
    
    //Exchange's names
    public static final String EMAIL_EXCHANGE_NAME = "email-notification-exchange";
    public static final String SMS_EXCHANGE_NAME = "sms-notification-exchange";
    
    //Routing keys
    public static final String EMAIL_ROUTING_KEY = "email.notification.*";
    public static final String SMS_ROUTING_KEY = "sms.notification.*";
    
    
}
