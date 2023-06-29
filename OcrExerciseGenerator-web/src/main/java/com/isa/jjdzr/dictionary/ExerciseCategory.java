package com.isa.jjdzr.dictionary;

public enum ExerciseCategory {
    WARM_UP ("Rozgrzewka"),
    CORE_EXERCISES ("Trening właściwy"),
    STRETCHING ("Rozciąganie");



    private final String description;

    ExerciseCategory(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }

}
