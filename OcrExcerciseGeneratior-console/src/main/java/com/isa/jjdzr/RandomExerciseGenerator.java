package com.isa.jjdzr;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class RandomExerciseGenerator {
    List<Exercises> exerciseList = new ArrayList<>();
    Double indicatorOfWarmUp = 0.2;
    Double indicatorOfCoreExercises = 0.6;
    Double indicatorOfStretching = 0.2;

    public void generateExercise(int userlevel) {
        List<Exercises> listFromFile = WriteAndReadFromFiles.readExercisesList();
        if (userlevel != 0) {
//            String finalWarmUp = "warmUp";
            List<Exercises> warmUp = listFromFile.stream().filter(u -> u.getCategory().equals("warmUp")).toList();
            Double warmUpPoints = userlevel * indicatorOfWarmUp;
            Double coreExercisesPoints = userlevel * indicatorOfCoreExercises;
            Double stretchingPoints = userlevel * indicatorOfStretching;
            Double counter1 = 0.0;
            List<Integer> drawnIndexes1 = new ArrayList<>();
            while (counter1 < warmUpPoints) {
                Random generator = new Random();
                int indexDrawn = 0;
                indexDrawn = getIndexDrawn(warmUp, generator, indexDrawn);
                if (!drawnIndexes1.contains(indexDrawn)) {
                    drawnIndexes1.add(indexDrawn);
                    Exercises exercisesDrawn = warmUp.get(indexDrawn);
                    exerciseList.add(exercisesDrawn);
                    counter1 = counter1 + exercisesDrawn.getExercisePoints();
                }
            }
            List<Exercises> coreExercises = listFromFile.stream().filter(u -> u.getCategory().equals("coreExercises")).toList();
            Double counter2 = 0.0;
            List<Integer> drawnIndexes2 = new ArrayList<>();
            while (counter2 < coreExercisesPoints) {
                Random generator = new Random();
                int indexDrawn = 0;
                indexDrawn = getIndexDrawn(coreExercises, generator, indexDrawn);
                if (!drawnIndexes2.contains(indexDrawn)) {
                    drawnIndexes2.add(indexDrawn);
                    Exercises exercisesDrawn = coreExercises.get(indexDrawn);
                    exerciseList.add(exercisesDrawn);
                    counter2 = counter2 + exercisesDrawn.getExercisePoints();
                }
            }
            List<Exercises> stretching= listFromFile.stream().filter(u -> u.getCategory().equals("stretching")).toList();
            Double counter3 = 0.0;
            List<Integer> drawnIndexes3 = new ArrayList<>();
            while (counter3 < stretchingPoints) {
                Random generator = new Random();
                int indexDrawn = 0;
                indexDrawn = getIndexDrawn(stretching, generator, indexDrawn);
                if (!drawnIndexes3.contains(indexDrawn)) {
                    drawnIndexes3.add(indexDrawn);
                    Exercises exercisesDrawn = stretching.get(indexDrawn);
                    exerciseList.add(exercisesDrawn);
                    counter3 = counter3 + exercisesDrawn.getExercisePoints();
                }
            }
        } else {
            System.out.println("Proszę wykonać: Test poziomu zaawansowania");
        }
    }
        private int getIndexDrawn (List < Exercises > warmUpList, Random generator,int indexDrawn){
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
