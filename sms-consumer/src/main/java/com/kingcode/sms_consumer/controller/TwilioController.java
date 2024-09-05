package com.kingcode.sms_consumer.controller;

import com.kingcode.sms_consumer.dtos.TwilioDTO;
import com.kingcode.sms_consumer.service.SMSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/sms")
public class TwilioController {

    @Autowired
    private SMSService smsService;

    @PostMapping("/config")
    public ResponseEntity configTwilio(@RequestBody TwilioDTO twilioDTO) {
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

            smsService.configureTwilio(twilioDTO.getACCOUNT_SID(), twilioDTO.getAUTH_TOKEN(), twilioDTO.getPHONE_NUMBER());

            return new ResponseEntity(HttpStatus.OK);

        } catch (IllegalArgumentException e) {
            return new ResponseEntity("Illeagal Credentials", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
