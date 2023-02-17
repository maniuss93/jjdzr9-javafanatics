
package com.isa.jjdzr;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class AddExercises {

    private static final List<Exercises> exerciseList = new ArrayList<>();

    static void createExercises() {
        Exercises exercise = new Exercises();

        exerciseList.addAll(WriteAndReadFromFiles.readExercisesList());
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj nazwe cwiczenia");
        String exerciseName = scanner.nextLine();
        List<String> listOfExercisesName = exerciseList.stream().map(Exercises::getExerciseName).toList();
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
        exercise.setExerciseName(exerciseName);
        System.out.println("Stwórz opis");
        String description = scanner.nextLine();
        exercise.setDescription(description);
        exerciseList.add(exercise);
        WriteAndReadFromFiles.writeExercisesList(exerciseList);

    }
}

