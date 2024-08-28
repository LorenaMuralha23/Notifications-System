package com.kingcode.email_consumer.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.main.lib.constants.RabbitMQConstants;
import com.mycompany.main.lib.dtos.NotificationDTO;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class Listener {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private EmailService service;

    
    //TA DANDO PROBLEMAS UHU
    @RabbitListener(queues = RabbitMQConstants.EMAIL_QUEUE_NAME)
    public void receiveMessage(Message message) throws Exception {
        System.out.println("Received message: " + new String(message.getBody()));

        System.out.println("Content type: " + message.getMessageProperties().getContentType());
        NotificationDTO notificationDTO = objectMapper.readValue(message.getBody(), NotificationDTO.class);

        try {
            service.sendEmail(notificationDTO.getFrom(), notificationDTO.getToSend(), notificationDTO.getTitle(), notificationDTO.getBody());
        } catch (Exception e) {
            throw new Exception();
        }
    }

}
