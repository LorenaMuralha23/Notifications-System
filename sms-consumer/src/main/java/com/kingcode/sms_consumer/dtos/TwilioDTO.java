package com.kingcode.sms_consumer.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

public class TwilioDTO implements Serializable{
    
    @JsonProperty("ACCOUNT_SID")
    private String ACCOUNT_SID;
    
    @JsonProperty("AUTH_TOKEN")
    private String AUTH_TOKEN;
    
    @JsonProperty("PHONE_NUMBER")
    private String PHONE_NUMBER;

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
