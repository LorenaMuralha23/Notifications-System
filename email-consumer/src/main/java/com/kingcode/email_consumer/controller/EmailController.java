package com.kingcode.email_consumer.controller;

import com.kingcode.email_consumer.dtos.EmailConfigDTO;
import com.kingcode.email_consumer.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/email")
public class EmailController {
    
    @Autowired
    private EmailService emailService;
    
    @PostMapping("/config")
    public void configureEmailApi(@RequestBody EmailConfigDTO emailConfigDTO){
        System.out.println("[EMAIL SERVER] CONFIGURING CREDENTIALS... [EMAIL SERVER]");
        if (emailConfigDTO.getUsername() != null && emailConfigDTO.getPassword() != null) {
            emailService.updateEmailConfig(emailConfigDTO.getUsername(), emailConfigDTO.getPassword());
        }else{
            System.out.println("[ERROR] Invalid credentials [ERROR]");
        }
    }
   
    
}
