package com.fighting.domain;

public enum UserStatus {
    ONLINE("在线"),
    OFFLINE("离线"),
    BUSY("忙碌"),
    LOCKED("锁定");

    private String status;

    UserStatus(String status) {
        this.status = status;
    }
    
    public String getStatus() {
        return status;
    }
}
