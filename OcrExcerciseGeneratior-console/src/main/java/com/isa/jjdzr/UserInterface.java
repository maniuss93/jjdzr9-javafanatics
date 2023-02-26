package com.isa.jjdzr;

import com.isa.jjdzr.console.Menu;
import com.isa.jjdzr.console.Printable;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    Printable menu = new Menu();
    Scanner scanner = new Scanner(System.in);
    int userLevel;
    AdvancementLevelForm advancementLevelForm = new AdvancementLevelForm();

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

    public UserInterface(int userLevel) {
        this.userLevel = userLevel;
    }

    void showUserInterfaceMenu() {
        menu.printActualLine("");
        menu.printActualLine("     ****************************************");
        menu.printActualLine("     *        USER INFORMATION PANEL        *");
        menu.printActualLine("     ****************************************");
        menu.printActualLine("     *     TWÓJ POZIOM ZAAWANSOWANIA TO: ");
        printAdvancementLevel();
        menu.printActualLine("Wybierz opcje:\n1. Test poziomu zaawansowania\n2. Wygeneruj losowy zestaw ćwiczeń\n3. Zobacz historie treningów\n4. Dodaj ćwiczenie\n5. Powrót\n>>");
    }

    void userInterfaceMenu() {
        advancementLevelForm.setUserAdvancementLevel(userLevel);
        try {
            int optionNumber;
            do {
                showUserInterfaceMenu();
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
        AddExercises.createExercises();
    }

    private void showTrainingHistory() {
        menu.printActualLine("Dostępne wkrótce");
    }


    private void generateExerciseSet() {
//        RandomExerciseGenerator randomExerciseGenerator = new RandomExerciseGenerator();
        randomExerciseGenerator.generateExercise(advancementLevelForm.getUserAdvancementLevel());

        for (Exercises exercises : randomExerciseGenerator.exerciseList) {
            menu.printExercise(exercises.getCategory(), exercises.getExerciseName(), exercises.getDescription());
        }
    }

    private void takeAdvancementTest() {
        advancementLevelForm.advancementLevelMenu();
    }


}
