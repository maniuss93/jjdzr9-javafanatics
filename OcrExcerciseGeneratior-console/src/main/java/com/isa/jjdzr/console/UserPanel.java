package com.isa.jjdzr.console;

import com.isa.jjdzr.exercise.model.Exercise;
import com.isa.jjdzr.exercise.service.AddExercise;
import com.isa.jjdzr.exercise.service.RandomExerciseGenerator;
import com.isa.jjdzr.interfaces.Printable;
import com.isa.jjdzr.user.service.AdvancementLevelForm;
import com.isa.jjdzr.utils.WriteAndReadFromFile;

import java.util.*;

public class UserPanel {
    Printable menu = new Menu();
    private final int userLevel;
    private final String userName;
    private List<Exercise> userExercises;
    private List<Exercise> randomExerciseList;
    private List<Exercise> temporaryList;
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

    public UserPanel(int userLevel, String userName) {
        this.userLevel = userLevel;
        this.userName = userName;
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
        System.out.println(userName);
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
        if (Objects.equals(userName, "")) {
             menu.printActualLine("Musisz sie zalogować, aby wyswietlić historie");
             return;
        }
        userExercises = WriteAndReadFromFile.readUserExerciseList(userName);
        if (userExercises != null) {
            menu.printExerciseList(userExercises);
        } else {
            if (randomExerciseList != null) {
                menu.printExerciseList(randomExerciseList);
            } else {
                menu.printActualLine("Nie posiadasz histori treningu");
            }
        }
    }

    private void generateExerciseSet() {
        this.temporaryList = randomExerciseGenerator
                .generateExercise(advancementLevelForm.getUserAdvancementLevel());
        menu.printExerciseList(temporaryList);
        if (Objects.equals(userName, "")) {
            return;
        }
        saveExerciseSet();
    }

    private void saveExerciseSet() {
        menu.printActualLine("Chcesz zapisać listę ćwiczeń do Twojej histori? T/N");
        Scanner scanner = new Scanner(System.in);
        String answear = scanner.nextLine();
        while (!(answear.equalsIgnoreCase("T") || answear.equalsIgnoreCase("N"))) {
            menu.printActualLine("Niepoprawna opcja");
            answear = scanner.nextLine();
        }
        if (answear.equalsIgnoreCase("T")) {
            if (userExercises == null) {
                randomExerciseList = temporaryList;
                WriteAndReadFromFile.writeUserExerciseList(userName, randomExerciseList);
            } else {
                userExercises.addAll(temporaryList);
                WriteAndReadFromFile.writeUserExerciseList(userName, userExercises);
            }
        }
    }
    private void takeAdvancementTest() {
        advancementLevelForm.advancementLevelMenu();
    }
}

