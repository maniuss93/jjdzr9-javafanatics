package com.isa.jjdzr;

import com.isa.jjdzr.exercise.model.Exercise;
import com.isa.jjdzr.exercise.service.ExerciseCategory;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ExercisePanel {
    static Printable menu = new Menu();

    public static void showAddExerciseMenu() {
        menu.printActualLine("""
                Wybierz kategorię ćwiczenia:
                1. Rozgrzewka
                2. Główne
                3. Rozciąganie
                >>""");
    }

    public static void printExerciseMenu(Exercise exercise) {
        Scanner scanner = new Scanner(System.in);
        showAddExerciseMenu();
        int optionNumber = 0;
        while (!(optionNumber == 3 || optionNumber == 2 || optionNumber == 1)) {
            try {
                optionNumber = scanner.nextInt();
                switch (optionNumber) {
                    case 1 -> exercise.setExerciseCategory(ExerciseCategory.WARM_UP);
                    case 2 -> exercise.setExerciseCategory(ExerciseCategory.CORE_EXERCISES);
                    case 3 -> exercise.setExerciseCategory(ExerciseCategory.STRETCHING);
                    default -> menu.printActualLine(UserPanel.wrongInput);
                }
            } catch (InputMismatchException e) {
                menu.printActualLine("Nieprawidłowa wartość");
                scanner.nextLine();
            }
        }
    }
}
