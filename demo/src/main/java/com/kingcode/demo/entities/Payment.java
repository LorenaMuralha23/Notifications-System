package com.kingcode.demo.entities;

import com.kingcode.demo.enums.PaymentStatus;
import java.util.Objects;

public class Payment {
    
    private Integer id;
    private Order order;
    private PaymentStatus status;

    public Payment() {
    }

    public Payment(int codeStatus) {
        this.status = PaymentStatus.fromCode(codeStatus);
    }

    public Integer getId() {
        return id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(int codeStatus) {
        this.status = PaymentStatus.fromCode(codeStatus);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id);
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
        final Payment other = (Payment) obj;
        return Objects.equals(this.id, other.id);
    }
    
        
    
}
