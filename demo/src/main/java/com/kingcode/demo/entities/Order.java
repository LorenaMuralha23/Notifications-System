package com.kingcode.demo.entities;

import com.kingcode.demo.enums.OrderStatus;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Order {
    
    private Integer id;
    private List<String> listOfProducts = new ArrayList<>();
    private Double value;
    private Payment payment;
    private User client;
    private OrderStatus status;

    public Order() {
    }

    public Order(Double value, Payment payment, User client) {
        this.value = value;
        this.payment = payment;
        this.client = client;
    }

    public Integer getId() {
        return id;
    }

    public User getClient() {
        return client;
    }

    public List<String> getListOfProducts() {
        return listOfProducts;
    }

    public void setListOfProducts(List<String> listOfProducts) {
        this.listOfProducts = listOfProducts;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.id);
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
        final Order other = (Order) obj;
        return Objects.equals(this.id, other.id);
    }
    
    
    
}
