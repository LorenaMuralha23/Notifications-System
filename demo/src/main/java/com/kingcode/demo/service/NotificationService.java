package com.kingcode.demo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mycompany.main.lib.constants.RabbitMQConstants;
import com.mycompany.main.lib.dtos.NotificationDTO;
import java.util.concurrent.TimeoutException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Autowired
    private RabbitMQService messageService;

    public ResponseEntity sendMessage(NotificationDTO notificationDTO) throws TimeoutException, JsonProcessingException {
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
        
        ResponseEntity sendSuccess = messageService.sendMessageOnce(exchange, routingKey, messageToSend);
        if (!sendSuccess.getStatusCode().is2xxSuccessful()){
            return sendSuccess;
        }else{
            return messageService.waitForResponse(exchange);
        }
        
    }
}
