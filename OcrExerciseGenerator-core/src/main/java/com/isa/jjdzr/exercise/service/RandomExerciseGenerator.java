package com.isa.jjdzr.exercise.service;

import com.isa.jjdzr.exercise.model.Exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomExerciseGenerator {
    private static final double indicatorOfWarmUp = 0.2;
    private static final double indicatorOfCoreExercises = 0.6;
    private static final double indicatorOfStretching = 0.2;



    public static List<Exercise> generateExercise(int userLevel) {
        List<Exercise> exerciseList = new ArrayList<>();
        double warmUpPoints = userLevel * indicatorOfWarmUp;
        double coreExercisesPoints = userLevel * indicatorOfCoreExercises;
        double stretchingPoints = userLevel * indicatorOfStretching;
        if (userLevel != 0) {
            exerciseList.addAll(fillExerciseList(ExerciseDataBase.
                    findExerciseByCategory(ExerciseCategory.WARM_UP), warmUpPoints));
            exerciseList.addAll(fillExerciseList(ExerciseDataBase.findExerciseByCategory(ExerciseCategory.CORE_EXERCISES), coreExercisesPoints));
            exerciseList.addAll(fillExerciseList(ExerciseDataBase.findExerciseByCategory(ExerciseCategory.STRETCHING), stretchingPoints));
        } else {
            System.out.println("Proszę wykonać: Test poziomu zaawansowania");
        }
        return exerciseList;
    }
    public static List<Exercise> fillExerciseList(List<Exercise> exerciseByCategory, double exercisePoints) {
        List<Integer> drawnIndexes = new ArrayList<>();
        List<Exercise> exerciseList = new ArrayList<>();
        double counter = 0.0;
        while (counter < exercisePoints) {
            int indexDrawn = drawIndex(exerciseByCategory);
            if (!drawnIndexes.contains(indexDrawn)) {
                drawnIndexes.add(indexDrawn);
                Exercise exerciseDrawn = exerciseByCategory.get(indexDrawn);
                counter = counter + exerciseDrawn.getExercisePoints();
                exerciseList.add(exerciseDrawn);
            }
        }
        return exerciseList;
    }

    private static int drawIndex(List<Exercise> warmUpList) {
        Random generator = new Random();
        int indexDrawn = 0;
        for (int i = 0; i < warmUpList.size(); i++) {
            indexDrawn = generator.nextInt(warmUpList.size());
        }
        return indexDrawn;
    }


    private RandomExerciseGenerator() {
    }
}
