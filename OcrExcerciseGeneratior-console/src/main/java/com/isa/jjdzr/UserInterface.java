package com.isa.jjdzr;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInterface {
    Scanner scanner = new Scanner(System.in);
    int userLevel;
    AdvancementLevelForm advancementLevelForm = new AdvancementLevelForm();
    RandomExerciseGenerator randomExerciseGenerator = new RandomExerciseGenerator();

    void printAdvancementLevel() {
        if (advancementLevelForm.getUserAdvancementLevel() == 50) {
            System.out.println("POCZĄTKUJĄCY");
        } else if (advancementLevelForm.getUserAdvancementLevel() == 100) {
            System.out.println("ZAAWANSOWANY");
        } else if (advancementLevelForm.getUserAdvancementLevel() == 150) {
            System.out.println("PROFESJONALNY");
        } else {
            System.out.println("BRAK");
        }
    }

    public UserInterface(int userLevel) {
        this.userLevel = userLevel;
    }

    void showUserInterfaceMenu() {
        System.out.println("");
        System.out.println("     ****************************************");
        System.out.println("     *        USER INFORMATION PANEL        *");
        System.out.println("     ****************************************");
        System.out.print("     *     TWÓJ POZIOM ZAAWANSOWANIA TO: ");
        printAdvancementLevel();
        System.out.print("Wybierz opcje:\n1. Test poziomu zaawansowania\n2. Wygeneruj losowy zestaw ćwiczeń\n3. Zobacz historie treningów\n4. Dodaj ćwiczenie\n5. Powrót\n>>");
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
            System.out.println("Niepoprawna opcja");
        }
    }

    private void addUserExercise() {
        AddExercises.createExercises();
    }

    private void showTrainingHistory() {
        System.out.println("Wybrano opcję POKAŻ HISTORIĘ ĆWICZEŃ");
    }

    private void generateExerciseSet() {
    }

    private void takeAdvancementTest() {
        advancementLevelForm.advancementLevelMenu();
    }


}
