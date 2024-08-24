package com.kingcode.demo.dtos;

import com.kingcode.demo.entities.Payment;
import com.kingcode.demo.entities.User;
import com.kingcode.demo.enums.OrderStatus;
import java.io.Serializable;
import java.util.List;

public class OrderDTO implements Serializable{
    
    private Integer id;
    private List<String> listOfProducts;
    private String paymentStatus;
    private String clientName;
    private String status;

    public OrderDTO() {
    }
    
}
