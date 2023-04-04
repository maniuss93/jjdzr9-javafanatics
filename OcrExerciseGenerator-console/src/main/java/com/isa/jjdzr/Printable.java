package com.isa.jjdzr;

import com.isa.jjdzr.exercise.model.Exercise;
import com.isa.jjdzr.exercise.service.ExerciseCategory;

import java.util.List;

public interface Printable {
    void printActualLine(String line);

    void printExerciseList(List<Exercise> exercises);

    void printExercise(ExerciseCategory exerciseCategory, String line1, String line2);

}
