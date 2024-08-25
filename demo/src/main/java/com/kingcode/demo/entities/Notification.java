package com.kingcode.demo.entities;

import com.kingcode.demo.enums.EventType;
import com.kingcode.demo.enums.NotificationStatus;
import java.util.Objects;

public class Notification {
    
    private Integer id;
    private String title;
    private String body;
    private EventType eventType;
    private Integer idUserToSend;
    private NotificationStatus status;

    public Notification(String title, String body, EventType eventType, Integer idUserToSend, NotificationStatus status) {
        this.title = title;
        this.body = body;
        this.eventType = eventType;
        this.idUserToSend = idUserToSend;
        this.status = status;
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

    public EventType getEventType() {
        return eventType;
    }
    
    public EventType getEventTypeByCode(Integer code){
        return EventType.fromCode(code);
    }

    public void setEventTypeByCode(Integer eventType) {
        this.eventType = EventType.fromCode(eventType);
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
