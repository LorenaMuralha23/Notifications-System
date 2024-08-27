package com.kingcode.demo.enums;

public enum DeliveryMethod {
    
    EMAIL(1),
    SMS(2),
    PUSH(3);

    private final int code;

    DeliveryMethod(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static DeliveryMethod fromCode(int code) {
        for (DeliveryMethod status : DeliveryMethod.values()) {
            if (status.getCode() == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("[SERVER SAYS] Invalid code: " + code + "[SERVER SAYS]");
    }
}
