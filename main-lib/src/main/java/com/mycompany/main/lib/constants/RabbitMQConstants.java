package com.mycompany.main.lib.constants;

public class RabbitMQConstants {
    
    //Queue's names
    public static final String EMAIL_QUEUE_NAME = "email-notification-queue";
    public static final String EMAIL_RESPONSE_QUEUE_NAME = "email-response-queue";
    public static final String SMS_QUEUE_NAME = "sms-notification-queue";
    public static final String SMS_RESPONSE_QUEUE_NAME = "sms-response-queue";
    
    //Exchange's names
    public static final String EMAIL_EXCHANGE_NAME = "email-notification-exchange";
    public static final String EMAIL_RESPONSE_EXCHANGE_NAME = "email-reponse-exchange";
    public static final String SMS_EXCHANGE_NAME = "sms-notification-exchange";
    public static final String SMS_RESPONSE_EXCHANGE_NAME = "sms-response-exchange";
    
    //Routing keys
    public static final String EMAIL_ROUTING_KEY = "email.notification.*";
    public static final String EMAIL_RESPONSE_ROUTING_KEY = "email.response.";
    public static final String SMS_ROUTING_KEY = "sms.notification.*";
    public static final String SMS_RESPONSE_ROUTING_KEY = "sms.response.*";
    
    
}
