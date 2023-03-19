package com.isa.jjdzr.exercise.service;

import com.isa.jjdzr.console.Menu;
import com.isa.jjdzr.exercise.model.Exercise;
import com.isa.jjdzr.interfaces.Printable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomExerciseGenerator {
    static Printable menu = new Menu();
    static double indicatorOfWarmUp = 0.2;
    static double indicatorOfCoreExercises = 0.6;
    static double indicatorOfStretching = 0.2;

    public static List<Exercise> generateExercise(int userlevel) {
        List<Exercise> exerciseList = new ArrayList<>();
        List<Exercise> listFromFile = ExerciseDataBase.getExerciseList();
        if (userlevel != 0) {
            List<Exercise> warmUp = listFromFile.stream().filter(u -> u.getCategory().equals("warmUp")).toList();
            double warmUpPoints = userlevel * indicatorOfWarmUp;
            double coreExercisesPoints = userlevel * indicatorOfCoreExercises;
            double stretchingPoints = userlevel * indicatorOfStretching;
            double counter1 = 0.0;
            List<Integer> drawnIndexes1 = new ArrayList<>();

            while (counter1 < warmUpPoints) {
                Random generator = new Random();
                int indexDrawn = 0;
                indexDrawn = getIndexDrawn(warmUp, generator, indexDrawn);
                if (!drawnIndexes1.contains(indexDrawn)) {
                    drawnIndexes1.add(indexDrawn);
                    Exercise exerciseDrawn = warmUp.get(indexDrawn);
                    exerciseList.add(exerciseDrawn);
                    counter1 = counter1 + exerciseDrawn.getExercisePoints();
                }
            }
            List<Exercise> coreExercises = listFromFile.stream().filter(u -> u.getCategory().equals("coreExercises")).toList();
            double counter2 = 0.0;
            List<Integer> drawnIndexes2 = new ArrayList<>();
            while (counter2 < coreExercisesPoints) {
                Random generator = new Random();
                int indexDrawn = 0;
                indexDrawn = getIndexDrawn(coreExercises, generator, indexDrawn);
                if (!drawnIndexes2.contains(indexDrawn)) {
                    drawnIndexes2.add(indexDrawn);
                    Exercise exerciseDrawn = coreExercises.get(indexDrawn);
                    exerciseList.add(exerciseDrawn);
                    counter2 = counter2 + exerciseDrawn.getExercisePoints();
                }
            }
            List<Exercise> stretching= listFromFile.stream().filter(u -> u.getCategory().equals("stretching")).toList();
            double counter3 = 0.0;
            List<Integer> drawnIndexes3 = new ArrayList<>();
            while (counter3 < stretchingPoints) {
                Random generator = new Random();
                int indexDrawn = 0;
                indexDrawn = getIndexDrawn(stretching, generator, indexDrawn);
                if (!drawnIndexes3.contains(indexDrawn)) {
                    drawnIndexes3.add(indexDrawn);
                    Exercise exerciseDrawn = stretching.get(indexDrawn);
                    exerciseList.add(exerciseDrawn);
                    counter3 = counter3 + exerciseDrawn.getExercisePoints();
                }
            }
        } else {
            menu.printActualLine("Proszę wykonać: Test poziomu zaawansowania");
        }
        return exerciseList;
    }
        private static int getIndexDrawn(List<Exercise> warmUpList, Random generator, int indexDrawn){
            for (int i = 0; i < warmUpList.size(); i++) {
                indexDrawn = generator.nextInt(warmUpList.size());
            }
            return indexDrawn;
        }
        public double getIndicatorOfWarmUp () {
            return indicatorOfWarmUp;
        }
        public void setIndicatorOfWarmUp (double indicatorOfWarmUp){
            this.indicatorOfWarmUp = indicatorOfWarmUp;
        }
        public double getIndicatorOfCoreExercises () {
            return indicatorOfCoreExercises;
        }
        public void setIndicatorOfCoreExercises (double indicatorOfCoreExercises){
            this.indicatorOfCoreExercises = indicatorOfCoreExercises;
        }
        public double getIndicatorOfStretching () {
            return indicatorOfStretching;
        }
        public void setIndicatorOfStretching (double indicatorOfStretching){
            this.indicatorOfStretching = indicatorOfStretching;
        }
    }
