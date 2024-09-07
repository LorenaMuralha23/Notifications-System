package com.mycompany.main.lib.dtos;

import java.time.LocalDateTime;


public class NotificationErrorDTO {
    private String title;
    private String body;
    private String toSend;
    private Integer statusCode;
    private Integer deliveryMethodCode;
    
    private String errorMessage;          
    private String exceptionType;         
    private LocalDateTime errorDate;  

    public NotificationErrorDTO() {
    }

    public NotificationErrorDTO(String title, String body, String toSend, Integer statusCode, Integer deliveryMethodCode, String errorMessage, String exceptionType, String stackTrace, LocalDateTime errorDate) {
        this.title = title;
        this.body = body;
        this.toSend = toSend;
        this.statusCode = statusCode;
        this.deliveryMethodCode = deliveryMethodCode;
        this.errorMessage = errorMessage;
        this.exceptionType = exceptionType;
        this.errorDate = errorDate;
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

    public String getToSend() {
        return toSend;
    }

    public void setToSend(String toSend) {
        this.toSend = toSend;
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

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getExceptionType() {
        return exceptionType;
    }

    public void setExceptionType(String exceptionType) {
        this.exceptionType = exceptionType;
    }

    

    public LocalDateTime getErrorDate() {
        return errorDate;
    }

    public void setErrorDate(LocalDateTime errorDate) {
        this.errorDate = errorDate;
    }
    
    
}
