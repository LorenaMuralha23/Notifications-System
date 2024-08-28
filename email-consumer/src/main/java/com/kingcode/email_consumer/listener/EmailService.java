package com.kingcode.email_consumer.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String from, String toEmail, String subject, String body) {
        try {
            SimpleMailMessage messageToSend = new SimpleMailMessage();

            messageToSend.setFrom(from);
            messageToSend.setTo(toEmail);
            messageToSend.setSubject(subject);
            messageToSend.setText(body);

            mailSender.send(messageToSend);

            System.out.println("Email sended successfully!");
        } catch (Exception e) {
            System.out.println("Error sending the message!");
        }
    }

}
