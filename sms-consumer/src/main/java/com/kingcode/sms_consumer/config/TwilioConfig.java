package com.kingcode.sms_consumer.config;

import org.springframework.stereotype.Component;

@Component
public class TwilioConfig {
    
    private String ACCOUNT_SID;
    
    private String AUTH_TOKEN;
    
    private String PHONE_NUMBER;

    public TwilioConfig(String ACCOUNT_SID, String AUTH_TOKEN, String PHONE_NUMBER) {
        this.ACCOUNT_SID = ACCOUNT_SID;
        this.AUTH_TOKEN = AUTH_TOKEN;
        this.PHONE_NUMBER = PHONE_NUMBER;
    }

    public TwilioConfig() {
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
