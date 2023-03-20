
package com.isa.jjdzr.exercise.service;

import com.isa.jjdzr.exercise.model.Exercise;
import com.isa.jjdzr.utils.Validation;

import java.util.List;

public class AddExercise {

    public static Exercise createExercise(List<Exercise> exerciseList) {
        Exercise exercise = new Exercise();
        exercise.setExerciseName(Validation.validateNewExerciseName(exerciseList));
        exercise.setDescription(Validation.validateCorrectExerciseDescription());
        exercise.setUrl(Validation.validateAddressUrl());
        exercise.setExercisePoints(Validation.validateCorrectEffortPointsFormat());
        return exercise;
    }
}