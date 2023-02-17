
package com.isa.jjdzr;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.*;

class AddExercises {

    private static final List<Exercises> exerciseList = new ArrayList<>();

    static void createExercises() {
        Exercises exercise = new Exercises();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<Exercises> exercisesList1 = objectMapper.readValue
                    (new File("src/main/resources/exercises.json"), new TypeReference<>() {
                    });
            exerciseList.addAll(exercisesList1);
        } catch (IOException ignored) {
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj nazwe cwiczenia");
        String exerciseName = scanner.nextLine();
        List<String> listOfExercisesName = exerciseList.stream().map(Exercises::getName).toList();
        while (listOfExercisesName.contains(exerciseName) || exerciseName.length() > 10 || exerciseName.length() < 3) {
            if (listOfExercisesName.contains(exerciseName)) {
                System.out.println("Ta nazwa cwiczenia już istnieje");
                exerciseName = scanner.nextLine();
            } else if (exerciseName.length() > 10) {
                System.out.println("Nazwa cwiczenia jest zbyt długa. Maksymalnie 10 znaków");
                exerciseName = scanner.nextLine();
            } else {
                System.out.println("Nazwa cwiczenia jest zbyt krótka. Conajmniej 3 znaki");
                exerciseName = scanner.nextLine();
            }
        }
        exercise.setName(exerciseName);
        System.out.println("Stwórz opis");
        String description = scanner.nextLine();
        exercise.setDescription(description);
        exerciseList.add(exercise);

        try {
            objectMapper.writeValue(new FileWriter
                    ("src/main/resources/exercises.json"), exerciseList);
        } catch (IOException e) {
            System.out.println("Nie można utworzyć cwiczenia"+ e);
        }
    }
}

