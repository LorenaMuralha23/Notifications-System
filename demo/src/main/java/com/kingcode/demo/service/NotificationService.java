package com.kingcode.demo.service;

import com.mycompany.main.lib.constants.RabbitMQConstants;
import com.mycompany.main.lib.dtos.NotificationDTO;
import java.util.concurrent.CompletableFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

@Service
public class NotificationService {
    
    @Autowired
    private RabbitMQService messageService;
    
    public ResponseEntity sendMessage(NotificationDTO notificationDTO){
        String exchange = "";
        String routingKey = "";
        
        switch (notificationDTO.getDeliveryMethodCode()) {
            case 1:
                exchange = RabbitMQConstants.EMAIL_EXCHANGE_NAME;
                routingKey = RabbitMQConstants.EMAIL_ROUTING_KEY;
                break;
            case 2:
                exchange = RabbitMQConstants.SMS_EXCHANGE_NAME;
                routingKey = RabbitMQConstants.SMS_ROUTING_KEY;
                break;
                
            default:
                throw new AssertionError();
        }
        
        Object messageToSend = notificationDTO;
        
        return messageService.sendMessage(exchange, routingKey, messageToSend);
    }
}
