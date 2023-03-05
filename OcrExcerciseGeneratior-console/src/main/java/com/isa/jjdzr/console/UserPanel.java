package com.isa.jjdzr.console;

import com.isa.jjdzr.interfaces.Printable;
import com.isa.jjdzr.exercise.service.AddExercise;
import com.isa.jjdzr.exercise.model.Exercise;
import com.isa.jjdzr.exercise.service.RandomExerciseGenerator;
import com.isa.jjdzr.user.service.AdvancementLevelForm;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserPanel {
    Printable menu = new Menu();
    Scanner scanner = new Scanner(System.in);
    int userLevel;
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

    public UserPanel(int userLevel) {
        this.userLevel = userLevel;
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
        menu.printActualLine("Dostępne wkrótce");
    }


    private void generateExerciseSet() {
//        RandomExerciseGenerator randomExerciseGenerator = new RandomExerciseGenerator();
        randomExerciseGenerator.generateExercise(advancementLevelForm.getUserAdvancementLevel());

        for (Exercise exercise : randomExerciseGenerator.exerciseList) {
            menu.printExercise(exercise.getCategory(), exercise.getExerciseName(), exercise.getDescription());
        }
    }

    private void takeAdvancementTest() {
        advancementLevelForm.advancementLevelMenu();
    }


}
