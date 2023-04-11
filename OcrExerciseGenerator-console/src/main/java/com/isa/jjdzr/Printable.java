package com.isa.jjdzr;

import com.isa.jjdzr.exercise.model.Exercise;

import java.util.List;

public interface Printable {
    void printActualLine(String line);

    void printExerciseList(List<Exercise> exercises);

    void printExercise(String category, String exerciseName, String exerciseDescription);

}
