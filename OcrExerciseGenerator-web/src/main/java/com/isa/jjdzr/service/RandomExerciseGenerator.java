package com.isa.jjdzr.service;

import com.isa.jjdzr.dictionary.AdvancementLevelCategory;
import com.isa.jjdzr.dictionary.ExerciseCategory;
import com.isa.jjdzr.model.Exercise;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Component
public class RandomExerciseGenerator {
    private static final double indicatorOfWarmUp = 0.2;
    private static final double indicatorOfCoreExercises = 0.6;
    private static final double indicatorOfStretching = 0.2;


    public List<Exercise> generateRandomExercises(int userLevel, List<Exercise> exercises) {
        List<Exercise> exerciseList = new ArrayList<>();
        double warmUpPoints = userLevel * indicatorOfWarmUp;
        double coreExercisesPoints = userLevel * indicatorOfCoreExercises;
        double stretchingPoints = userLevel * indicatorOfStretching;
        if (userLevel != 0) {
            exerciseList.addAll(fillExerciseList(
                    exercises.stream()
                            .filter(c -> c.getExerciseCategory().equals(ExerciseCategory.WARM_UP))
                            .collect(Collectors.toList()), warmUpPoints));
            exerciseList.addAll(fillExerciseList(
                    exercises.stream().filter(c -> c.getExerciseCategory().equals(ExerciseCategory.CORE_EXERCISES))
                            .collect(Collectors.toList()), coreExercisesPoints));
            exerciseList.addAll(fillExerciseList(
                    exercises.stream().filter(c -> c.getExerciseCategory().equals(ExerciseCategory.STRETCHING))
                            .collect(Collectors.toList()), stretchingPoints));
        } else {
            System.out.println("Proszę wykonać: Test poziomu zaawansowania");
        }
        return exerciseList;
    }

    private List<Exercise> fillExerciseList(List<Exercise> exerciseByCategory, double exercisePoints) {
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

    private int drawIndex(List<Exercise> warmUpList) {
        Random generator = new Random();
        int indexDrawn = 0;
        for (int i = 0; i < warmUpList.size(); i++) {
            indexDrawn = generator.nextInt(warmUpList.size());
        }
        return indexDrawn;
    }

    public int convertAdvancementLevel(AdvancementLevelCategory category) {
        if (category == null) {
            return 0;
        } else if (category.equals(AdvancementLevelCategory.BEGINNER)) {
            return 50;
        } else if (category.equals(AdvancementLevelCategory.ADVANCE)) {
            return 100;
        } else {
            return 150;
        }
    }
}