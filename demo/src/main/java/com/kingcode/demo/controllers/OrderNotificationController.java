package com.kingcode.demo.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kingcode.demo.constants.RabbitMQConstants;
import com.kingcode.demo.dtos.NotificationDTO;
import com.kingcode.demo.enums.EventType;
import com.kingcode.demo.service.RabbitMQService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/order")
public class OrderNotificationController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private RabbitMQService messageService;
    
    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping
    public ResponseEntity<String> sendNotification(@RequestBody NotificationDTO notificationDTO) {
        String exchangeName = RabbitMQConstants.ORDER_EXCHANGE_NAME;
        String routingKey = "";

        if (notificationDTO.getEventTypeCode() == 1) {
            //created
            routingKey = RabbitMQConstants.ORDER_CREATED_ROUTING_KEY;
        }

        if (notificationDTO.getEventTypeCode() == 2) {
            //confirmed
            routingKey = RabbitMQConstants.ORDER_CONFIRMED_ROUTING_KEY;
        }

        if (notificationDTO.getEventTypeCode() == 3) {
            //cancelled
            routingKey = RabbitMQConstants.ORDER_CANCELLED_ROUTING_KEY;
        }
        
        Object messageToSend = notificationDTO;
        
        messageService.sendMessage(exchangeName, routingKey, messageToSend);
        
        return new ResponseEntity(HttpStatus.OK);
    }

}
