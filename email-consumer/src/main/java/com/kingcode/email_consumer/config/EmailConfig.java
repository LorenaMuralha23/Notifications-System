package com.kingcode.email_consumer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class EmailConfig {
     
    @Bean
    public JavaMailSenderImpl javaMailSender(){
        return new JavaMailSenderImpl();
    }
    
//    public JavaMailSender configurenMailSender(String username, String password){
//        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//        
//        mailSender.setUsername(username);
//        mailSender.setPassword(password);
//        
//        return mailSender;
//    }
    
}
