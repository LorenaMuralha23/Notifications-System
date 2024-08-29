package com.kingcode.email_consumer.listener;

import com.mycompany.main.lib.constants.RabbitMQConstants;
import com.mycompany.main.lib.dtos.NotificationDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class Listener {

    @Autowired
    private EmailService service;

    @RabbitListener(queues = RabbitMQConstants.EMAIL_QUEUE_NAME)
    public void receiveMessage(NotificationDTO notificationDTO) {
        
        System.out.println("[SERVICE SAYS] NOTIFICATION RECEIVED [SERVICE SAYS]");
        
        service.sendEmail(notificationDTO);
        
    }

}
