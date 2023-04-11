
package com.isa.jjdzr.exercise.service;

import com.isa.jjdzr.exercise.model.Exercise;
import com.isa.jjdzr.utils.Validation;

import java.util.List;

public class CreateExercise {

    public static Exercise createExercise(Exercise exercise, List<Exercise> exerciseList) {
        exercise.setExerciseName(Validation.validateNewExerciseName(exerciseList));
        exercise.setDescription(Validation.validateCorrectExerciseDescription());
        exercise.setUrl(Validation.validateAddressUrl());
        exercise.setExercisePoints(Validation.validateCorrectEffortPointsFormat());
        exercise.setExerciseId(GenerateExerciseId.generateUserID(exerciseList));
        return exercise;
    }
}