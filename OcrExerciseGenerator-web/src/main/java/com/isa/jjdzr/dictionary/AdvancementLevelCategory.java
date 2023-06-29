package com.isa.jjdzr.dictionary;

public enum AdvancementLevelCategory {
    BEGINNER("Początkujący"),
    ADVANCE("Zaawansowany"),
    PROFESSIONAL("Profesionalny");

    private final String description;

    AdvancementLevelCategory(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
