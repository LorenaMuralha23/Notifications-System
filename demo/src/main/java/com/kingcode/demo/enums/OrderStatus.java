package com.kingcode.demo.enums;

public enum OrderStatus {
    PENDING(1),
    CONFIRMED(2),
    PROCESSING(3),
    SHIPPED(4),
    DELIVERED(5),
    CANCELLED(6);
    
    private final int code;

    OrderStatus(int code) {
        this.code = code;
    }
    
    public int getCode() {
        return code;
    }
    
    public static OrderStatus fromCode(int code) {
        for (OrderStatus status : OrderStatus.values()) {
            if (status.getCode() == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("[SERVER SAYS] Invalid code: " + code + "[SERVER SAYS]");
    }
}
