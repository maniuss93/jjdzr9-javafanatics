package com.isa.jjdzr;

import com.isa.jjdzr.exercise.model.Exercise;
import com.isa.jjdzr.exercise.service.CreateExercise;
import com.isa.jjdzr.exercise.service.ExerciseDataBase;
import com.isa.jjdzr.exercise.service.RandomExerciseGenerator;
import com.isa.jjdzr.user.model.User;
import com.isa.jjdzr.user.service.AdvancementLevelCategory;
import com.isa.jjdzr.user.service.UserDataBase;
import com.isa.jjdzr.utils.Validation;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class UserPanel {
    public static final Printable menu = new Menu();
    static User user = new User();
    public static final String wrongInput = "Niepoprawna opcja";
    private static List<Exercise> randomExerciseList;


    static void printAdvancementLevel() {
        if (user.getUserAdvancementLevel() == AdvancementLevelCategory.BEGINNER) {
            menu.printActualLine("POCZĄTKUJĄCY");
        } else if (user.getUserAdvancementLevel() == AdvancementLevelCategory.ADVANCE) {
            menu.printActualLine("ZAAWANSOWANY");
        } else if (user.getUserAdvancementLevel() == AdvancementLevelCategory.PROFESSIONAL) {
            menu.printActualLine("PROFESJONALNY");
        } else {
            menu.printActualLine("BRAK");
        }
    }

    static void showUserPanelMenu() {
        menu.printActualLine("""
                ****************************************
                *            PANEL UŻYTKOWNIKA         *
                ****************************************
                *     TWÓJ POZIOM ZAAWANSOWANIA TO:    *""");
        printAdvancementLevel();
        menu.printActualLine("""
                Wybierz opcje:
                1. Test poziomu zaawansowania
                2. Wygeneruj losowy zestaw ćwiczeń
                3. Zobacz historie treningów
                4. Dodaj ćwiczenie
                5. Powrót
                >>""");
    }

    public static void userPanelMenu() {
        Scanner scanner = new Scanner(System.in);
        try {
            int optionNumber;
            do {
                showUserPanelMenu();
                optionNumber = scanner.nextInt();
                switch (optionNumber) {
                    case 1 -> takeAdvancementTest();
                    case 2 -> {
                        randomExerciseList = generateExerciseSet();
                        menu.printExerciseList(randomExerciseList);
                        if ((Validation.isUserSignedUp(user)) && user.getUserAdvancementLevel() != null) {
                            TrainingHistory.saveNewTrainingHistory(user, randomExerciseList);
                        }
                    }
                    case 3 -> showTrainingHistory();
                    case 4 -> addNewExercise();
                    case 5 -> {
                    }
                    default -> menu.printActualLine(wrongInput);
                }
            }
            while (optionNumber != 5);
        } catch (InputMismatchException e) {
            menu.printActualLine(wrongInput);
        }

    }

    public static void addNewExercise() {
        if (!(Validation.isUserSignedUp(user))) {
            menu.printActualLine("Musisz być zalogowany aby dodać ćwiczenie");
            return;
        }
        ExerciseDataBase.saveNewExerciseToDataBase(CreateExercise.createExercise(ExercisePanel.setNewExerciseCategory(), ExerciseDataBase.getExerciseList()));
    }


    public static void showTrainingHistory() {
        TrainingHistory.showTrainingHistory(user, randomExerciseList);
    }

    public static List<Exercise> generateExerciseSet() {
        RandomExerciseGenerator randomExerciseGenerator = new RandomExerciseGenerator();
        return randomExerciseGenerator.generateRandomExercises(randomExerciseGenerator.convertAdvancementLevel(user.getUserAdvancementLevel()));
    }

    public static void takeAdvancementTest() {
        user.setUserAdvancementLevel(AdvancementLevelForm.advancementLevelMenu(user));
        UserDataBase.saveUserToDataBase(user);
    }

    public UserPanel(User user) {
        UserPanel.user = user;
    }
}

