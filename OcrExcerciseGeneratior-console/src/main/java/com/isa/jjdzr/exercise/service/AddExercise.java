
package com.isa.jjdzr.exercise.service;

import com.isa.jjdzr.console.Menu;
import com.isa.jjdzr.exercise.model.Exercise;
import com.isa.jjdzr.interfaces.Printable;
import com.isa.jjdzr.utils.WriteAndReadFromFile;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class AddExercise {

    private static final List<Exercise> exerciseList = new ArrayList<>();
    static Printable menu = new Menu();

    public static void createExercises() {
        Exercise exercise = new Exercise();
        Scanner scanner = new Scanner(System.in);
        exerciseList.addAll(WriteAndReadFromFile.readExercisesList());
        menu.printActualLine("Wybierz kategorię ćwiczenia: \n1. Rozgrzewka \n2. Główne \n3. Rozciąganie");
        try {
            int optionNumber;
            do {
                optionNumber = scanner.nextInt();
                switch (optionNumber) {
                    case 1 -> {
                        exercise.setCategory("warmUp");
                    }
                    case 2 -> {
                        exercise.setCategory("coreExercises");
                    }
                    case 3 -> {
                        exercise.setCategory("stretching");
                    }
                }
            }
            while (optionNumber != 3 && optionNumber != 2 && optionNumber != 1);
        } catch (InputMismatchException e) {
            menu.printActualLine("Niepoprawna opcja");
        }


        Scanner scanner2 = new Scanner(System.in);
        menu.printActualLine("Podaj nazwe cwiczenia");
        String exerciseName = scanner2.nextLine();
        List<String> listOfExercisesName = exerciseList.stream().map(Exercise::getExerciseName).toList();
        while (listOfExercisesName.contains(exerciseName) || exerciseName.length() > 20 || exerciseName.length() < 3) {
            if (listOfExercisesName.contains(exerciseName)) {
                menu.printActualLine("Ta nazwa cwiczenia już istnieje");
                exerciseName = scanner2.nextLine();
            } else if (exerciseName.length() > 20) {
                menu.printActualLine("Nazwa cwiczenia jest zbyt długa. Maksymalnie 20 znaków");
                exerciseName = scanner2.nextLine();
            } else {
                menu.printActualLine("Nazwa cwiczenia jest zbyt krótka. Conajmniej 3 znaki");
                exerciseName = scanner2.nextLine();
            }
        }
        exercise.setExerciseName(exerciseName);
        menu.printActualLine("Stwórz opis");
        String description = scanner2.nextLine();
        exercise.setDescription(description);
        menu.printActualLine("Podaj adres url (np youtube)");
        String url = scanner2.nextLine();
        exercise.setUrl(url);
        menu.printActualLine("Dodaj wartość punktową ćwiczenia (5, 10 lub 20)");
        Integer effortPoints = scanner2.nextInt();
        exercise.setExercisePoints(effortPoints);
        exerciseList.add(exercise);
        WriteAndReadFromFile.writeExercisesList(exerciseList);
        menu.printExerciseName(exerciseName);
    }
}


