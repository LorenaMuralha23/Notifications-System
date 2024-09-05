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

    public void configureEmailSender() {
        if (this.username != null && this.password != null) {
            this.mailSenderImpl.setUsername(username);
            this.mailSenderImpl.setPassword(password);
            System.out.println("[EMAIL SERVER] CREDENTIALS CONFIGURED SUCCESSFULLY [EMAIL SERVER]");
        }
    }

    public void updateEmailConfig(String username, String password) {
        this.username = username;
        this.password = password;
        configureEmailSender();
    }

    public void sendEmail(NotificationDTO notificationDTO) {
        boolean emailSent = false;
        try {
            System.out.println("[EMAIL SERVICE] Sending e-mail...[EMAIL SERVICE]");
            SimpleMailMessage messageToSend = new SimpleMailMessage();

            if (username == null || password == null) {
                throw new IllegalStateException("[EMAIL SERVICE ERROR] Illegal Credentials [EMAIL SERVICE ERROR]");
            }

            if (this.username.equals("your-email@gmail.com") || this.password.equals("your-password")) {
                throw new IllegalStateException("[EMAIL SERVICE ERROR] Creaditals are needed [EMAIL SERVICE ERROR]");
            }

            messageToSend.setFrom(this.username);
            messageToSend.setTo(notificationDTO.getToSend());
            messageToSend.setSubject(notificationDTO.getTitle());
            messageToSend.setText(notificationDTO.getBody());

            mailSender.send(messageToSend);
            emailSent = true;
        } catch (IllegalStateException e) {
            System.out.println("[EMAIL SERVICE ERROR] An state error ocurred while sending the email. Trying again... [EMAIL SERVICE ERROR]");
            throw e;
        } catch (MailException e) {
            System.out.println("[EMAIL SERVICE ERROR] An email error ocurred while sending the email. Trying again... [EMAIL SERVICE ERROR]\n");
            throw e;
        } catch (Exception e) {
            System.out.println("[EMAIL SERVICE ERROR] An error ocurred while sending the email.  Trying again... [EMAIL SERVICE ERROR]\n");
            throw e;
        } finally {
            try {
                String responseJSON = Boolean.toString(emailSent);
                System.out.println("Response json: " + responseJSON);
                rabbitTemplate.convertAndSend(RabbitMQConstants.EMAIL_RESPONSE_QUEUE_NAME, responseJSON);
                System.out.println("[EMAIL SERVICE] Response sent to the reply queue [EMAIL SERVICE]");

            } catch (AmqpException e) {
                System.out.println("[EMAIL SERVICE ERROR] Failed to process response message.  Trying again... [EMAIL SERVICE ERROR]");
                throw e;
            }
        }
    }


}
