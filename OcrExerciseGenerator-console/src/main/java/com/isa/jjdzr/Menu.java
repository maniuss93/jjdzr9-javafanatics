package com.isa.jjdzr;

import com.isa.jjdzr.exercise.model.Exercise;
import com.isa.jjdzr.user.service.AdvancementLevelCategory;
import com.isa.jjdzr.user.service.UserDataBase;
import com.isa.jjdzr.user.service.UserSignIn;
import com.isa.jjdzr.user.service.UserSignUp;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Menu implements Printable {
    static Scanner scanner = new Scanner(System.in);

    static void userSighUp() {
        UserDataBase.saveUserToDataBase(UserSignUp.createUser(UserDataBase.getAllUsers()));
    }

    static void userSignIn() {
        new UserPanel(UserSignIn.login(UserDataBase.getAllUsers()));
        UserPanel.userPanelMenu();
    }

    static void withNoLogin() {
        UserPanel.user.setUserName("");
        UserPanel.user.setUserAdvancementLevel(AdvancementLevelCategory.ADVANCE);
        UserPanel.userPanelMenu();
    }

    static void printMenu() {
        System.out.println("""
                ****************************************
                *                 MENU                 *
                ****************************************""");
        System.out.print("""
                Wybierz opcje:
                1. Tworzenie użytkownika
                2. Logowanie
                3. Bez zalogowania
                4. Koniec
                >>""");
    }

    public static void menu() {
        int nrOpcji = 0;
        while (nrOpcji != 4) {
            try {
                printMenu();
                nrOpcji = scanner.nextInt();
                switch (nrOpcji) {
                    case 1 -> userSighUp();
                    case 2 -> userSignIn();
                    case 3 -> withNoLogin();
                    case 4 -> System.out.println("Koniec programu");
                    default -> System.out.println(UserPanel.wrongInput);
                }
            } catch (InputMismatchException e) {
                System.out.println(UserPanel.wrongInput);
                scanner.nextLine();
            }
        }
    }


    @Override
    public void printActualLine(String line) {
        System.out.println(line);
    }

    @Override
    public void printExerciseName(String line) {
        System.out.println("Nowe ćwiczenie: " + line + ", zostało dodane :)");
    }

    @Override
    public void printExerciseList(List<Exercise> exercises) {
        for (Exercise exercise : exercises) {
            printExercise(exercise.getCategory(), exercise.getExerciseName(), exercise.getDescription());
        }
    }

    @Override
    public void printExercise(String line1, String line2, String line3) {
        System.out.println("Kategoria: " + line1 + " \nNazwa ćwiczenia: " + line2 + "\nOpis:  " + line3 + "\n");
    }
}