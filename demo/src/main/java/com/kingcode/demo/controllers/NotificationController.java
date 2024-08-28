package com.kingcode.demo.controllers;

import com.kingcode.demo.service.NotificationService;
import com.mycompany.main.lib.dtos.NotificationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = ("api/notification"))
public class NotificationController {
    
    @Autowired
    private NotificationService service;
    
    @PostMapping("/send")
    public void sendMessage(@RequestBody NotificationDTO notificationDTO) throws Exception{
        
        if (notificationDTO.getTitle().equals("")) {
            throw new Exception();
        }
        
        if (notificationDTO.getBody().equals("")) {
            throw new Exception();
        }
        
        if (notificationDTO.getFrom().equals("")){
            throw new Exception();
        }
        
        if (notificationDTO.getToSend().equals("")){
            throw new Exception();
        }
        
        service.sendMessage(notificationDTO);
    }
    
}
