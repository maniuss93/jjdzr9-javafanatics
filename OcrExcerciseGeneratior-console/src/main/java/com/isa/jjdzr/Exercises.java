package com.isa.jjdzr;

public class Exercises {
    private String exerciseName;
    private Integer exercisePoints;
    public Exercises() {
    }
    public Exercises(String exerciseName, Integer exercisePoints) {
        this.exerciseName = exerciseName;
        this.exercisePoints = exercisePoints;
    }
    public String getExerciseName() {
        return exerciseName;
    }
    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }
    public Integer getExercisePoints() {
        return exercisePoints;
    }
    public void setExercisePoints(Integer exercisePoints) {
        this.exercisePoints = exercisePoints;
    }
}
