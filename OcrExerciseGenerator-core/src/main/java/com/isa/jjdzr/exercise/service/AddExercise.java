
package com.isa.jjdzr.exercise.service;

import com.isa.jjdzr.console.ExercisePanel;
import com.isa.jjdzr.console.Menu;
import com.isa.jjdzr.exercise.model.Exercise;
import com.isa.jjdzr.interfaces.Printable;
import com.isa.jjdzr.utils.Validation;

import java.util.List;

public class AddExercise {
    static Printable menu = new Menu();

    public static Exercise createExercise(List<Exercise> exerciseList) {
        Exercise exercise = new Exercise();
        ExercisePanel.printExerciseMenu(exercise);
        exercise.setExerciseName(Validation.validateNewExerciseName(exerciseList));
        exercise.setDescription(Validation.validateCorrectExerciseDescription());
        exercise.setUrl(Validation.validateAddressUrl());
        exercise.setExercisePoints(Validation.validateCorrectEffortPointsFormat());
        menu.printExerciseName(exercise.getExerciseName());
        return exercise;
    }
}