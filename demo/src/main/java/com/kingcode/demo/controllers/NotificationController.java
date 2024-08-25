package com.kingcode.demo.controllers;

import com.kingcode.demo.dtos.NotificationDTO;
import com.kingcode.demo.entities.Notification;
import com.kingcode.demo.enums.EventType;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/notifications")
public class NotificationController {
    
    @PostMapping("/create")
    public ResponseEntity<String> createNotification(@RequestBody NotificationDTO notificationDTO) throws Exception{
        //recebi os dados da notificação -> criar a notificação -> armazena-la no db (depois)
        Notification notificationCreated = new Notification();
        
        if (notificationDTO.getTitle().equals("")) {
            throw new Exception();
        }
        
        if (notificationDTO.getBody().equals("")) {
            throw new Exception();
        }
        
        if (notificationDTO.getEventTypeCode() >= 0 || notificationDTO.getEventTypeCode() > 3){
            throw new Exception();
        }
        
        if (notificationDTO.getStatusCode() <= 0 || notificationDTO.getStatusCode() > 3){
            throw new Exception();
        }
        
        notificationCreated.setTitle(notificationDTO.getTitle());
        notificationCreated.setBody(notificationDTO.getBody());
        notificationCreated.setEventTypeByCode(notificationDTO.getEventTypeCode());
        notificationCreated.setIdUserToSend(notificationDTO.getIdUserToSend());
        notificationCreated.setStatusCode(notificationDTO.getStatusCode());
        
        System.out.println("==============================");
        System.out.println("    NOTIFICATION CREATED    ");
        System.out.println("==============================");
        System.out.println("==============================");
        System.out.println("    NOTIFICATION INFO    ");
        System.out.println("Title: " + notificationCreated.getTitle());
        System.out.println("Body: " + notificationCreated.getBody());
        System.out.println("Event Type: " + notificationCreated.getEventType());
        System.out.println("User id: " + notificationCreated.getIdUserToSend());
        System.out.println("Status Code: " + notificationCreated.getStatus());
        
        returnNotification(notificationCreated);
        
        return new ResponseEntity(HttpStatus.OK);
    }
    
    public Notification returnNotification(Notification notification){
        return notification;
    }
    
}
