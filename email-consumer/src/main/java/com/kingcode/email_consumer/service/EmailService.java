package com.kingcode.email_consumer.service;

import com.mycompany.main.lib.dtos.NotificationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private JavaMailSenderImpl mailSenderImpl;

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
        try {
            SimpleMailMessage messageToSend = new SimpleMailMessage();

            if (username == null || password == null) {
                throw new IllegalStateException("[EMAIL SERVICE ERROR] Illegal Credentials [username] [password] [EMAIL SERVICE ERROR]");
            }
            
            if (this.username.equals("your-email@gmail.com") || this.password.equals("your-password")) {
                throw new IllegalStateException("[ERROR] Creaditals are needed [ERROR]");
            }

            messageToSend.setFrom(this.username);
            messageToSend.setTo(notificationDTO.getToSend());
            messageToSend.setSubject(notificationDTO.getTitle());
            messageToSend.setText(notificationDTO.getBody());

            mailSender.send(messageToSend);

            System.out.println("[EMAIL SERVICE] Email sent successfully [EMAIL SERVICE]");
        } catch (IllegalStateException | MailException e) {
            System.out.println("[EMAIL SERVICE ERROR] An error ocurred while sending the email [EMAIL SERVICE ERROR]");
            System.out.println(e.getMessage());
        }
    }

}
