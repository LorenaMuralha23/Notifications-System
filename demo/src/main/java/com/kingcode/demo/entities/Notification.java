package com.kingcode.demo.entities;

import com.kingcode.demo.enums.DeliveryMethod;
import com.kingcode.demo.enums.NotificationStatus;
import java.util.Objects;

public class Notification {
    
    private Integer id;
    private String title;
    private String body;
    private Integer idUserToSend;
    private NotificationStatus status;
    private DeliveryMethod deliveryMethod;

    public Notification(String title, String body, Integer idUserToSend, NotificationStatus status, DeliveryMethod method) {
        this.title = title;
        this.body = body;
        this.idUserToSend = idUserToSend;
        this.status = status;
        this.deliveryMethod = method;
    }

    public Notification() {
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

    public NotificationStatus getStatus() {
        return status;
    }

    public void setStatus(NotificationStatus status) {
        this.status = status;
    }
    
    public void setStatusCode(int code) {
        this.status = NotificationStatus.fromCode(code);
    }

    public Integer getId() {
        return id;
    }

    public DeliveryMethod getDeliveryMethodByCode(int code) {
        return DeliveryMethod.fromCode(code);
    }
    
    public DeliveryMethod getDeliveryMethod(){
        return this.deliveryMethod;
    }
    
    public void setDeliveryMethod(int code) {
        this.deliveryMethod = DeliveryMethod.fromCode(code);
    }
    

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 31 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Notification other = (Notification) obj;
        return Objects.equals(this.id, other.id);
    }
    
    
    
    
    
}
