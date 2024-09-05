package com.kingcode.email_consumer.controller;

import com.kingcode.email_consumer.service.EmailService;
import com.mycompany.main.lib.dtos.EmailConfigDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/service/email")
public class EmailController {
    
    @Autowired
    private EmailService emailService;
    
    @PostMapping("/config")
    public String configureEmailApi(@RequestBody EmailConfigDTO emailConfigDTO){
        System.out.println("[E-MAIL SERVICE] CONFIGURING CREDENTIALS... [E-MAIL SERVICE]");
        boolean success = emailService.updateEmailConfig(emailConfigDTO.getUsername(), emailConfigDTO.getPassword());
        
        if (success) {
            return "true";
        }
        
        return "false";
    }
   
    
}
