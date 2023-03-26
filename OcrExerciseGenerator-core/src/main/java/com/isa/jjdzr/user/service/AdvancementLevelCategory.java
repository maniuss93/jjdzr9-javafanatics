package com.isa.jjdzr.user.service;

public enum AdvancementLevelCategory {
    BEGINNER("Beginner"),
    ADVANCE("Advance"),
    PROFESSIONAL("Professional");

    private String description;

    AdvancementLevelCategory(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }
}
