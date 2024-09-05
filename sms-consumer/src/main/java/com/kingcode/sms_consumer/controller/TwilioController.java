package com.kingcode.sms_consumer.controller;

import com.kingcode.sms_consumer.service.SMSService;
import com.mycompany.main.lib.dtos.TwilioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/service/sms")
public class TwilioController {

    @Autowired
    private SMSService smsService;

    @PostMapping("/config")
    public String configTwilio(@RequestBody TwilioDTO twilioDTO) {
        boolean success = smsService.configureTwilio(twilioDTO.getACCOUNT_SID(), twilioDTO.getAUTH_TOKEN(), twilioDTO.getPHONE_NUMBER());
        if (success){
            return "true";
        }
        return "false";
    }

}
