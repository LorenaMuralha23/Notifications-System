package com.kingcode.demo.dtos;

import com.kingcode.demo.enums.EventType;
import com.kingcode.demo.enums.NotificationStatus;
import java.io.Serializable;

public class NotificationDTO implements Serializable{
    private String title;
    private String body;
    private Integer eventType;
    private Integer idUserToSend;
    private Integer statusCode;

    public NotificationDTO() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public EventType getEventTypeByCode(int code) {
        return EventType.fromCode(code);
    }

    public Integer getEventTypeCode(){
        return this.eventType;
    }
    
    public void setEventType(Integer eventCode) {
        this.eventType = eventCode;
    }

    public Integer getIdUserToSend() {
        return idUserToSend;
    }

    public void setIdUserToSend(Integer idUserToSend) {
        this.idUserToSend = idUserToSend;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }
    
    
}
