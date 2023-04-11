package com.isa.jjdzr.exercise.service;

import com.isa.jjdzr.exercise.model.Exercise;

import java.util.List;

public class GenerateExerciseId {

    public static Long generateUserID(List<Exercise> exercisesList) {
        return exercisesList.stream().map(Exercise::getExerciseId)
                       .reduce((first, second) -> second).orElse(0L) + 1;
    }

    private GenerateExerciseId() {
    }
}
