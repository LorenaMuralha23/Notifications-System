package com.kingcode.sms_consumer.service;

import com.kingcode.sms_consumer.config.TwilioConfig;
import com.mycompany.main.lib.constants.RabbitMQConstants;
import com.twilio.Twilio;
import com.twilio.exception.ApiException;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SMSService {

    @Autowired
    private TwilioConfig twilioConfig;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public boolean configureTwilio(String accountSid, String authToken, String phoneNumber) {
        twilioConfig.setACCOUNT_SID(accountSid);
        twilioConfig.setAUTH_TOKEN(authToken);
        twilioConfig.setPHONE_NUMBER(phoneNumber);
        try {
            Twilio.init(accountSid, authToken);
            System.out.println("[SMS SERVICE SAYS] CREDENTIALS CONFIGURED SUCCESSFULLY [SMS SERVICE SAYS]");
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void sendSMS(String numberToSend, String smsMessage) throws Exception {
        boolean smsSent = false;

        try {
            if (twilioConfig.getTwillioPhoneNumber() == null || twilioConfig.getTwillioPhoneNumber().isEmpty()) {
                throw new IllegalArgumentException("[SERVICE SAYS] Invalid twillion number [SERVICE SAYS]");
            }

            if (numberToSend == null || numberToSend.isEmpty()) {
                throw new IllegalArgumentException("[SERVICE SAYS] The phone number you want to send a message can not be null [SERVICE SAYS]");
            }

            Message message = Message.creator(
                    new PhoneNumber(numberToSend),
                    new PhoneNumber(this.twilioConfig.getTwillioPhoneNumber()),
                    smsMessage
            ).create();

            smsSent = true;
        } catch (IllegalArgumentException e) {
            System.out.println("[ERROR] Invalid parameter: " + e.getMessage());
        } catch (ApiException e) {
            System.out.println("[ERROR] Twilio API Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("[ERROR] Unexpected error occurred while sending the message: " + e.getMessage());
        } finally {
            try {
                String responseJSON = Boolean.toString(smsSent);
                rabbitTemplate.convertAndSend(RabbitMQConstants.SMS_RESPONSE_QUEUE_NAME, responseJSON);
                System.out.println("[SMS SERVICE] Response sent to the reply queue [SMS SERVICE]");
            } catch (AmqpException e) {
                System.out.println("[SMS SERVICE ERROR] Failed to process response message [SMS SERVICE ERROR]");
            }

        }
    }

}
