package com.kingcode.demo.enums;

public enum PaymentStatus {
    PENDING(1),
    CONFIRMED(2),
    CANCELLED(3),
    FAILED(4);
    
    private final int code;

    PaymentStatus(int code) {
        this.code = code;
    }
    
    public int getCode() {
        return code;
    }
    
    public static PaymentStatus fromCode(int code) {
        for (PaymentStatus status : PaymentStatus.values()) {
            if (status.getCode() == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("[SERVER SAYS] Invalid code: " + code + "[SERVER SAYS]");
    }
}
