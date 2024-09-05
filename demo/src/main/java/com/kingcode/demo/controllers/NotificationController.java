package com.kingcode.demo.controllers;

import com.kingcode.demo.service.NotificationService;
import com.mycompany.main.lib.constants.ServicesConstants;
import com.mycompany.main.lib.dtos.EmailConfigDTO;
import com.mycompany.main.lib.dtos.NotificationDTO;
import com.mycompany.main.lib.dtos.TwilioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value = ("api/notification"))
public class NotificationController {

    @Autowired
    private NotificationService service;

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/send")
    public ResponseEntity sendMessage(@RequestBody NotificationDTO notificationDTO) throws Exception {

        if (notificationDTO.getTitle().equals("")) {
            throw new Exception();
        }

        if (notificationDTO.getBody().equals("")) {
            throw new Exception();
        }

        if (notificationDTO.getToSend().equals("")) {
            throw new Exception();
        }

        return service.sendMessage(notificationDTO);

    }

    @PostMapping("/email/config")
    public ResponseEntity configureEmailService(@RequestBody EmailConfigDTO emailConfigDTO) {
        System.out.println("[SERVER SAYS] CONFIGURING E-MAIL SERVICE... [SERVER SAYS]");
        if (emailConfigDTO.getUsername() != null && emailConfigDTO.getPassword() != null) {
            String response = restTemplate.postForObject(ServicesConstants.EMAIL_SERVICE_URL, emailConfigDTO, String.class);
            boolean success = Boolean.parseBoolean(response);

            if (success) {
                System.out.println("[SERVER SAYS] E-MAIL SERVICE CONFIGURED SUCCESSFULLY [SERVER SAYS]\n");
                return new ResponseEntity("[SERVER SAYS] E-MAIL CREDENTIALS CONFIGURED SUCCESSFULLY [SERVER SAYS]", HttpStatus.OK);
            }

        }
        
        System.out.println("[SERVER SAYS] INVALID CREDENTIALS [SERVER SAYS]\n");
        return new ResponseEntity("[SERVER SAYS] INVALID CREDENTIALS [SERVER SAYS]", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/sms/config")
    public ResponseEntity configureSmsService(@RequestBody TwilioDTO twilioDTO) {
        System.out.println("[SERVER SAYS] CONFIGURING SMS SERVICE... [SERVER SAYS]");
        try {
            if (twilioDTO.getACCOUNT_SID() == null || twilioDTO.getACCOUNT_SID().equals("")) {
                throw new IllegalArgumentException();
            }

            if (twilioDTO.getAUTH_TOKEN() == null || twilioDTO.getAUTH_TOKEN().equals("")) {
                throw new IllegalArgumentException();
            }

            if (twilioDTO.getPHONE_NUMBER() == null || twilioDTO.getPHONE_NUMBER().equals("")) {
                throw new IllegalArgumentException();
            }

            String response = restTemplate.postForObject(ServicesConstants.SMS_SERVICE_URL, twilioDTO, String.class);
            boolean success = Boolean.parseBoolean(response);

            if (!success) {
                System.out.println("[SERVER SAYS] SMS CONFIGURATION FAILED [SERVER SAYS]");
                throw new Exception();
            }
            System.out.println("[SERVER SAYS] SMS SERVICE CONFIGURED SUCCESSFULLY [SERVER SAYS]");
            return new ResponseEntity("[SERVER SAYS] SMS SERVICE CONFIGURED SUCCESSFULLY [SERVER SAYS]", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity("[SERVER SAYS] ILLEGAL CREDENTIALS [SERVER SAYS]", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception ex) {
            return new ResponseEntity("[SERVER SAYS] AN UNEXCPEDCTED ERROR OCURRED [SERVER SAYS]", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
