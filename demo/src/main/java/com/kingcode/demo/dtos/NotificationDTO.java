package com.kingcode.demo.dtos;

import com.kingcode.demo.enums.NotificationStatus;
import java.io.Serializable;

public class NotificationDTO implements Serializable{
    private String title;
    private String body;
    private Integer idUserToSend;
    private Integer statusCode;
    private Integer deliveryMethodCode;

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

    public Integer getDeliveryMethodCode() {
        return deliveryMethodCode;
    }

    public void setDeliveryMethodCode(Integer deliveryMethodCode) {
        this.deliveryMethodCode = deliveryMethodCode;
    }
    
}
