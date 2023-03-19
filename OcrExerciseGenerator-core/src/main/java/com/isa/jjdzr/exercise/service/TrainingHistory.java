package com.isa.jjdzr.exercise.service;

import com.isa.jjdzr.console.Menu;
import com.isa.jjdzr.console.UserPanel;
import com.isa.jjdzr.exercise.model.Exercise;
import com.isa.jjdzr.interfaces.Printable;
import com.isa.jjdzr.user.model.User;
import com.isa.jjdzr.utils.Validation;
import com.isa.jjdzr.utils.WriteAndReadFromFile;

import java.util.List;
import java.util.Scanner;

public class TrainingHistory {
    static Printable menu = new Menu();
    static List<Exercise> userExerciseList;

    public static void showTrainingHistory(User user, List<Exercise> randomExerciseList) {
        if (!(Validation.isUserSignedUp(user))) {
            menu.printActualLine("Musisz być zalogowany aby zobaczyć historie ćwiczeń");
            return;
        }
        if (user.getUserAdvancementLevel() == 0) {
            menu.printActualLine("Nie posiadasz histori treningu");
            return;
        }
        userExerciseList = ExerciseDataBase.getUserExerciseList(user);
        if (userExerciseList != null) {
            menu.printExerciseList(userExerciseList);
        } else {
            if (randomExerciseList != null) {
                menu.printExerciseList(randomExerciseList);
            } else {
                menu.printActualLine("Nie posiadasz histori treningu");
            }
        }
    }

    public static void saveNewTrainingHistory(User user, List<Exercise> randomExerciseList) {
        menu.printActualLine("Chcesz zapisać listę ćwiczeń do Twojej histori? T/N");
        Scanner scanner = new Scanner(System.in);
        String answear = scanner.nextLine();
        while (!(answear.equalsIgnoreCase("T") || answear.equalsIgnoreCase("N"))) {
            menu.printActualLine(UserPanel.wrongInput);
            answear = scanner.nextLine();
        }
        if (answear.equalsIgnoreCase("T")) {
            userExerciseList = ExerciseDataBase.getUserExerciseList(user);
            if (userExerciseList == null) {
                userExerciseList = randomExerciseList;
            } else {
                userExerciseList.addAll(randomExerciseList);
            }
            WriteAndReadFromFile.writeUserExerciseList(user.getUserName(), userExerciseList);
        }
    }
}
