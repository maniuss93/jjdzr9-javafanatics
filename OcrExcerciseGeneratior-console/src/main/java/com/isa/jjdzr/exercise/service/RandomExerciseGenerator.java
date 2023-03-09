package com.isa.jjdzr.exercise.service;

import com.isa.jjdzr.exercise.model.Exercise;
import com.isa.jjdzr.utils.WriteAndReadFromFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomExerciseGenerator {
    double indicatorOfWarmUp = 0.2;
    double indicatorOfCoreExercises = 0.6;
    double indicatorOfStretching = 0.2;

    public List<Exercise> generateExercise(int userlevel) {
        List<Exercise> exerciseList = new ArrayList<>();
        List<Exercise> listFromFile = WriteAndReadFromFile.readExercisesList();
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
            System.out.println("Proszę wykonać: Test poziomu zaawansowania");
        }
        return exerciseList;
    }
        private int getIndexDrawn (List <Exercise> warmUpList, Random generator, int indexDrawn){
            for (int i = 0; i < warmUpList.size(); i++) {
                indexDrawn = generator.nextInt(warmUpList.size());
            }
            return indexDrawn;
        }
        public Double getIndicatorOfWarmUp () {
            return indicatorOfWarmUp;
        }
        public void setIndicatorOfWarmUp (Double indicatorOfWarmUp){
            this.indicatorOfWarmUp = indicatorOfWarmUp;
        }
        public Double getIndicatorOfCoreExercises () {
            return indicatorOfCoreExercises;
        }
        public void setIndicatorOfCoreExercises (Double indicatorOfCoreExercises){
            this.indicatorOfCoreExercises = indicatorOfCoreExercises;
        }
        public Double getIndicatorOfStretching () {
            return indicatorOfStretching;
        }
        public void setIndicatorOfStretching (Double indicatorOfStretching){
            this.indicatorOfStretching = indicatorOfStretching;
        }

    }
