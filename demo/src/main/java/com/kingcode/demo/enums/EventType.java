package com.kingcode.demo.enums;

public enum EventType {
    
    CREATED(1),
    CONFIRMED(2),
    CANCELLED(3);

    private final int code;

    EventType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static EventType fromCode(int code) {
        for (EventType status : EventType.values()) {
            if (status.getCode() == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("[SERVER SAYS] Invalid code: " + code + "[SERVER SAYS]");
    }
}
