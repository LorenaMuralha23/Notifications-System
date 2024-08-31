package com.kingcode.sms_consumer.service;

import com.kingcode.sms_consumer.config.TwilioConfig;
import com.twilio.exception.ApiException;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SMSService {

    private TwilioConfig twilioConfig;

    @Autowired
    public SMSService(TwilioConfig twilioConfig) {
        this.twilioConfig = twilioConfig;
    }

    public String sendSMS(String numberToSend, String smsMessage) throws Exception {
        try {
            if (twilioConfig.getTwillioPhoneNumber() == null || twilioConfig.getTwillioPhoneNumber().isEmpty()) {
                throw new IllegalArgumentException("[SERVICE SAYS] Invalid twillion number [SERVICE SAYS]");
            }

            if (numberToSend == null || numberToSend.isEmpty()) {
                throw new IllegalArgumentException("[SERVICE SAYS] The phone number you want to send a message can not be null [SERVICE SAYS]");
            }

            Message message = Message.creator(
                    new PhoneNumber(numberToSend),
                    new PhoneNumber(twilioConfig.getTwillioPhoneNumber()),
                    smsMessage
            ).create();

            return message.getSid();

        } catch (IllegalArgumentException e) {
            System.out.println("[ERROR] Invalid parameter: " + e.getMessage());
            throw e;  
        } catch (ApiException e) {
            System.out.println("[ERROR] Twilio API Error: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            System.out.println("[ERROR] Unexpected error occurred while sending the message: " + e.getMessage());
            throw e;
        }
    }

}
