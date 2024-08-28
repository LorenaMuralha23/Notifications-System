package com.mycompany.main.lib.enums;

public enum NotificationStatus {
    
    DELIVERED(1),
    READ(2),
    UNREAD(3);

    private final int code;

    NotificationStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static NotificationStatus fromCode(int code) {
        for (NotificationStatus status : NotificationStatus.values()) {
            if (status.getCode() == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("[SERVER SAYS] Invalid code: " + code + "[SERVER SAYS]");
    }
}
