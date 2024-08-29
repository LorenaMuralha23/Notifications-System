package com.kingcode.email_consumer.listener;

import com.mycompany.main.lib.dtos.NotificationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(NotificationDTO notificationDTO) {
        try {
            SimpleMailMessage messageToSend = new SimpleMailMessage();

            messageToSend.setFrom(notificationDTO.getFrom());
            messageToSend.setTo(notificationDTO.getToSend());
            messageToSend.setSubject(notificationDTO.getTitle());
            messageToSend.setText(notificationDTO.getBody());

            mailSender.send(messageToSend);

            System.out.println("Email sended successfully!");
        } catch (Exception e) {
            System.out.println("Error sending the message!");
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
        }
    }

}
