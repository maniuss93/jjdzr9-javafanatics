package com.isa.jjdzr.dto;

import com.isa.jjdzr.dictionary.ExerciseCategory;
import org.springframework.stereotype.Service;

@Service
public class ExerciseDto {

    private Long exerciseId;

    private int exercisePoints;

    private String exerciseName;

    private String description;

    private String url;

    private ExerciseCategory exerciseCategory;

    public ExerciseCategory getExerciseCategory() {
        return exerciseCategory;
    }

    public void setExerciseCategory(ExerciseCategory exerciseCategory) {
        this.exerciseCategory = exerciseCategory;
    }

    public ExerciseDto() {
    }

    public ExerciseDto(String exerciseName, Integer exercisePoints) {
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

    public void setExerciseId(Long exerciseId) {
        this.exerciseId = exerciseId;
    }

    public Long getExerciseId() {
        return exerciseId;
    }


}
