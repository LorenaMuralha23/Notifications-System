package com.kingcode.sms_consumer.config;

import com.twilio.Twilio;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TwilioConfig {
    
    @Value("${twilio.account.sid}")
    private String ACCOUNT_SID;
    
    @Value("${twilio.auth.token}")
    private String AUTH_TOKEN;
    
    @Value("${twilio.phone.number}")
    private String PHONE_NUMBER;
    
    
    @PostConstruct
    public void setUp(){
        Twilio.init(this.ACCOUNT_SID, this.AUTH_TOKEN);
    }
    
    public String getTwillioPhoneNumber(){
        return this.PHONE_NUMBER;
    }

    public String getACCOUNT_SID() {
        return ACCOUNT_SID;
    }

    public void setACCOUNT_SID(String ACCOUNT_SID) {
        this.ACCOUNT_SID = ACCOUNT_SID;
    }

    public String getAUTH_TOKEN() {
        return AUTH_TOKEN;
    }

    public void setAUTH_TOKEN(String AUTH_TOKEN) {
        this.AUTH_TOKEN = AUTH_TOKEN;
    }

    public String getPHONE_NUMBER() {
        return PHONE_NUMBER;
    }

    public void setPHONE_NUMBER(String PHONE_NUMBER) {
        this.PHONE_NUMBER = PHONE_NUMBER;
    }
    
    
    
}
