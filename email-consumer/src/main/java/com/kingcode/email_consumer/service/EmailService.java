package com.kingcode.email_consumer.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.main.lib.constants.RabbitMQConstants;
import com.mycompany.main.lib.dtos.NotificationDTO;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private JavaMailSenderImpl mailSenderImpl;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private String username;
    private String password;

    public boolean configureEmailSender() {
        boolean success = false;
        if (this.username != null && this.password != null) {
            this.mailSenderImpl.setUsername(username);
            this.mailSenderImpl.setPassword(password);
            System.out.println("[E-MAIL SERVICE] CREDENTIALS CONFIGURED SUCCESSFULLY [E-MAIL SERVICE]\n");
            success = true;
        }
        return success;
    }

    public boolean updateEmailConfig(String username, String password) {
        this.username = username;
        this.password = password;
        return configureEmailSender();
    }

    public void sendEmail(NotificationDTO notificationDTO) {
        boolean emailSent = false;
        try {
            System.out.println("[EMAIL SERVICE] SENDING E-MAIL...[EMAIL SERVICE]");
            SimpleMailMessage messageToSend = new SimpleMailMessage();

            if (username == null || password == null) {
                throw new IllegalStateException("[EMAIL SERVICE ERROR] ILLEGAL CREDENTIALS [EMAIL SERVICE ERROR]");
            }

            if (this.username.equals("your-email@gmail.com") || this.password.equals("your-password")) {
                throw new IllegalStateException("[EMAIL SERVICE ERROR] CREDENTIALS ARE NEEDED [EMAIL SERVICE ERROR]");
            }

            messageToSend.setFrom(this.username);
            messageToSend.setTo(notificationDTO.getToSend());
            messageToSend.setSubject(notificationDTO.getTitle());
            messageToSend.setText(notificationDTO.getBody());

            mailSender.send(messageToSend);
            emailSent = true;
        } catch (IllegalStateException e) {
            e.getMessage();
        } catch (MailException e) {
            System.out.println("[EMAIL SERVICE ERROR] AN E-MAIL EXCEPTION OCURRED WHILE SENDING THE E-MAIL [EMAIL SERVICE ERROR]\n");
        } catch (Exception e) {
            System.out.println("[EMAIL SERVICE ERROR] AN ERROR OCURRED WHILE SENDING THE E-MAIL [EMAIL SERVICE ERROR]\n");
        } finally {
            try {
                String responseJSON = Boolean.toString(emailSent);
                rabbitTemplate.convertAndSend(RabbitMQConstants.EMAIL_RESPONSE_QUEUE_NAME, responseJSON);
                System.out.println("[EMAIL SERVICE] RESPONSE SENT TO THE REPLY QUEUE [EMAIL SERVICE]\n");
            } catch (AmqpException e) {
                System.out.println("[EMAIL SERVICE ERROR] FAILED TO PROCESS RESPONSE MESSAGE [EMAIL SERVICE ERROR]\n");
            }
        }
    }


}
