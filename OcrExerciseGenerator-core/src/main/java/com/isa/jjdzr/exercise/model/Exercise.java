package com.isa.jjdzr.exercise.model;

import com.isa.jjdzr.exercise.service.ExerciseCategory;
import jakarta.persistence.*;

@Entity
@Table(name = "exercises")
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exerciseId", nullable = false)
    private Long exerciseId;

    @Column(name = "exercise_points", nullable = false)
    private int exercisePoints;

    @Column(name = "exercise_name", nullable = false)
    private String exerciseName;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "url")
    private String url;

    @Enumerated(EnumType.STRING)
    @Column(name = "exercise_category", nullable = false)
    private ExerciseCategory exerciseCategory;

    public ExerciseCategory getExerciseCategory() {
        return exerciseCategory;
    }

    public void setExerciseCategory(ExerciseCategory exerciseCategory) {
        this.exerciseCategory = exerciseCategory;
    }

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

    public void setExerciseId(Long exerciseId) {
        this.exerciseId = exerciseId;
    }

    public Long getExerciseId() {
        return exerciseId;
    }
}
