
package com.isa.jjdzr;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

class AddExercises {

    private static final List<Exercises> exerciseList = new ArrayList<>();

    static void createExercises() {
        Exercises exercise = new Exercises();
        Scanner scanner = new Scanner(System.in);
        exerciseList.addAll(WriteAndReadFromFiles.readExercisesList());
        System.out.println("Wybierz kategorię ćwiczenia: \n1. Rozgrzewka \n2. Główne \n3. Rozciąganie");
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
            System.out.println("Niepoprawna opcja");
        }


        Scanner scanner2 = new Scanner(System.in);
        System.out.println("Podaj nazwe cwiczenia");
        String exerciseName = scanner2.nextLine();
        List<String> listOfExercisesName = exerciseList.stream().map(Exercises::getExerciseName).toList();
        while (listOfExercisesName.contains(exerciseName) || exerciseName.length() > 20 || exerciseName.length() < 3) {
            if (listOfExercisesName.contains(exerciseName)) {
                System.out.println("Ta nazwa cwiczenia już istnieje");
                exerciseName = scanner2.nextLine();
            } else if (exerciseName.length() > 20) {
                System.out.println("Nazwa cwiczenia jest zbyt długa. Maksymalnie 20 znaków");
                exerciseName = scanner2.nextLine();
            } else {
                System.out.println("Nazwa cwiczenia jest zbyt krótka. Conajmniej 3 znaki");
                exerciseName = scanner2.nextLine();
            }
        }
        exercise.setExerciseName(exerciseName);
        System.out.println("Stwórz opis");
        String description = scanner2.nextLine();
        exercise.setDescription(description);
        System.out.println("Podaj adres url (np youtube)");
        String url = scanner2.nextLine();
        exercise.setUrl(url);
        System.out.println("Dodaj wartość punktową ćwiczenia (5, 10 lub 20)");
        Integer effortPoints = scanner2.nextInt();
        exercise.setExercisePoints(effortPoints);
        exerciseList.add(exercise);
        WriteAndReadFromFiles.writeExercisesList(exerciseList);
        System.out.println("\nNowe ćwiczenie: " + exerciseName + ", zostało dodane :)");
    }
}


