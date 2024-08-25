package com.kingcode.demo.dtos;
import java.io.Serializable;
import java.util.List;

public class OrderDTO implements Serializable{
    
    private Integer id;
    private List<String> listOfProducts;
    private String paymentStatus;
    private String clientName;
    private String status;
    private double value;

    public OrderDTO() {
    }
    
}
