package com.isa.jjdzr.user.service;

public enum UserRole {
    ADMIN("administartor"),
    USER("user");
    private final String description;

    UserRole(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
