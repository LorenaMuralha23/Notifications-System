package com.kingcode.sms_consumer.listener;

import com.kingcode.sms_consumer.service.SMSService;
import com.mycompany.main.lib.constants.RabbitMQConstants;
import com.mycompany.main.lib.dtos.NotificationDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Listener {

    @Autowired
    private SMSService service;

    @RabbitListener(queues = RabbitMQConstants.SMS_QUEUE_NAME)
    public void receiveMessage(NotificationDTO notificationDTO) throws Exception {
        service.sendSMS(notificationDTO.getToSend(), notificationDTO.getBody());
    }

}
