package com.isa.jjdzr.exercise.model;

public class Exercise {
    private int exercisePoints;
    private String exerciseName;
    private String description;
    private String url;
    private String category;

    public Exercise() {
    }

    public Exercise(String exerciseName, Integer exercisePoints) {
        this.exerciseName = exerciseName;
        this.exercisePoints = exercisePoints;
    }

    public int getExercisePoints() {
        return exercisePoints;
    }

    public void setExercisePoints(int exercisePoints) {
        this.exercisePoints = exercisePoints;
    }


    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}
