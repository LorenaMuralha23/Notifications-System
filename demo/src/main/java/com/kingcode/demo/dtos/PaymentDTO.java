package com.kingcode.demo.dtos;

import java.io.Serializable;

public class PaymentDTO implements Serializable{
    
    private Integer id;
    private Integer idClient;
    private Integer idOrder;
    private String status;

    public PaymentDTO() {
    }
    
}
