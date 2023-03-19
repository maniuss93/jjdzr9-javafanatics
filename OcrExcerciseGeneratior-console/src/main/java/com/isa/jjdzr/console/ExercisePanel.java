package com.isa.jjdzr.console;

import com.isa.jjdzr.exercise.model.Exercise;
import com.isa.jjdzr.interfaces.Printable;

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
                >> """);
    }

    public static Exercise printExerciseMenu(Exercise exercise) {
        Scanner scanner = new Scanner(System.in);
        showAddExerciseMenu();
        int optionNumber = 0;
        while (!(optionNumber == 3 || optionNumber == 2 || optionNumber == 1)) {
            try {
                optionNumber = scanner.nextInt();
                switch (optionNumber) {
                    case 1 -> exercise.setCategory("warmUp");
                    case 2 -> exercise.setCategory("coreExercises");
                    case 3 -> exercise.setCategory("stretching");
                    default -> menu.printActualLine(UserPanel.wrongInput);
                }
            } catch (InputMismatchException e) {
                menu.printActualLine("Nieprawidłowa wartość");
                scanner.nextLine();
            }
        }
        return exercise;
    }
}
