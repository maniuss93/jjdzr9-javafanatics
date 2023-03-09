package com.isa.jjdzr.console;

import com.isa.jjdzr.interfaces.Printable;
import com.isa.jjdzr.exercise.service.AddExercise;
import com.isa.jjdzr.exercise.model.Exercise;
import com.isa.jjdzr.exercise.service.RandomExerciseGenerator;
import com.isa.jjdzr.user.service.AdvancementLevelForm;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class UserPanel {
    Printable menu = new Menu();
    private final int userLevel;
    private List<Exercise> userExercises;
    private List<Exercise> randomExerciseList;
    public AdvancementLevelForm advancementLevelForm = new AdvancementLevelForm();

    RandomExerciseGenerator randomExerciseGenerator = new RandomExerciseGenerator();

    void printAdvancementLevel() {
        if (advancementLevelForm.getUserAdvancementLevel() == 50) {
            menu.printActualLine("POCZĄTKUJĄCY");
        } else if (advancementLevelForm.getUserAdvancementLevel() == 100) {
            menu.printActualLine("ZAAWANSOWANY");
        } else if (advancementLevelForm.getUserAdvancementLevel() == 150) {
            menu.printActualLine("PROFESJONALNY");
        } else {
            menu.printActualLine("BRAK");
        }
    }

    public UserPanel(int userLevel, List<Exercise> userExercises) {
        this.userLevel = userLevel;
        this.userExercises = userExercises;
    }

    void showUserPanelMenu() {
        menu.printActualLine("");
        menu.printActualLine("     ****************************************");
        menu.printActualLine("     *            PANEL UŻYTKOWNIKA         *");
        menu.printActualLine("     ****************************************");
        menu.printActualLine("     *     TWÓJ POZIOM ZAAWANSOWANIA TO: ");
        printAdvancementLevel();
        menu.printActualLine("Wybierz opcje:\n1. Test poziomu zaawansowania\n2. Wygeneruj losowy zestaw ćwiczeń\n3. Zobacz historie treningów\n4. Dodaj ćwiczenie\n5. Powrót\n>>");
    }

    public void userPanelMenu() {
        Scanner scanner = new Scanner(System.in);
        advancementLevelForm.setUserAdvancementLevel(userLevel);
        try {
            int optionNumber;
            do {
                showUserPanelMenu();
                optionNumber = scanner.nextInt();
                switch (optionNumber) {
                    case 1 -> takeAdvancementTest();
                    case 2 -> generateExerciseSet();
                    case 3 -> showTrainingHistory();
                    case 4 -> addUserExercise();
                }
            }
            while (optionNumber != 5);
        } catch (InputMismatchException e) {
            menu.printActualLine("Niepoprawna opcja");
        }
    }

    private void addUserExercise() {
        AddExercise.createExercises();
    }

    private void showTrainingHistory() {
        if (userExercises != null) {
            menu.printExerciseList(userExercises);
        } else {
            menu.printActualLine("Nie posiadasz histori treningu");
        }
    }

    private void generateExerciseSet() {
        this.randomExerciseList = randomExerciseGenerator
                .generateExercise(advancementLevelForm.getUserAdvancementLevel());;
        menu.printExerciseList(randomExerciseList);
        saveExerciseSet();
    }

    private void saveExerciseSet() {
        Scanner scanner = new Scanner(System.in);
        menu.printActualLine("Chcesz zapisać listę ćwiczeń do Twojej histori? T/N");
        String answear = scanner.nextLine();
        while (!((answear.equals("T") || answear.equals("N")))) {
            menu.printActualLine("Niepoprawna opcja");
            answear = scanner.nextLine();
        }
        if (answear.equals("T")) {
            if(userExercises == null) {
                userExercises = new ArrayList<>();
            }
            userExercises.addAll(randomExerciseList);
        }
    }

    private void takeAdvancementTest() {
        advancementLevelForm.advancementLevelMenu();
    }

    public List<Exercise> getUserExercises() {
        return userExercises;
    }
}
