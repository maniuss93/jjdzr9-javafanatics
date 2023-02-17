package com.isa.jjdzr;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomExerciseGenerator {
    List<Exercises> warmUpList = new ArrayList<>();
    Double indicatorOfWarmUp = 0.2;
    Double indicatorOfCoreExercises = 0.6;
    Double indicatorOfStretching = 0.2;
    public void generateWarmUpFromFile() {
        Exercises exercise = new Exercises();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<Exercises> exercisesList = objectMapper.readValue
                    (new File("src/main/resources/warmUp.json"), new TypeReference<>(){});
            warmUpList.addAll(exercisesList);
            System.out.println(warmUpList);
        } catch (IOException ignored) {}
    }

    public List<Exercises> generateExercise(List<Exercises> exercisesList1, Integer userAdvancementLevel, Double indicator) {
        List<Exercises> generatedList = new ArrayList<>();
        Double warmUpPoints = userAdvancementLevel * indicator;
        Double counter = 0.0;
        List<Integer> drawnIndexes = new ArrayList<>();
        while (counter < warmUpPoints) {
            Random generator = new Random();
            int indexDrawn=0;
            indexDrawn = getIndexDrawn(exercisesList1, generator, indexDrawn);
            if(!drawnIndexes.contains(indexDrawn)) {
                drawnIndexes.add(indexDrawn);
                Exercises exercisesDrawn = exercisesList1.get(indexDrawn);
                generatedList.add(exercisesDrawn);
                counter = counter + exercisesDrawn.getExercisePoints();
            }
        }
        return generatedList;
    }
    private int getIndexDrawn(List<Exercises> warmUpList, Random generator, int indexDrawn) {
        for (int i = 0; i < warmUpList.size(); i++) {
            indexDrawn = generator.nextInt(warmUpList.size());
        }
        return indexDrawn;
    }
    public Double getIndicatorOfWarmUp() {
        return indicatorOfWarmUp;
    }
    public void setIndicatorOfWarmUp(Double indicatorOfWarmUp) {
        this.indicatorOfWarmUp = indicatorOfWarmUp;
    }
    public Double getIndicatorOfCoreExercises() {
        return indicatorOfCoreExercises;
    }
    public void setIndicatorOfCoreExercises(Double indicatorOfCoreExercises) {
        this.indicatorOfCoreExercises = indicatorOfCoreExercises;
    }
    public Double getIndicatorOfStretching() {
        return indicatorOfStretching;
    }
    public void setIndicatorOfStretching(Double indicatorOfStretching) {
        this.indicatorOfStretching = indicatorOfStretching;
    }
}
