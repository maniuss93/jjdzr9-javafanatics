package com.isa.jjdzr;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        //wygeneruj poziom zaawansowania
//        UserInterface userInterface = new UserInterface();
//        userInterface.userInterfaceMenu();
        //wygeneruj listę ćwiczeń
//        AdvancementLevelForm advancementLevelForm = new AdvancementLevelForm();
//        Integer userAdvancementLevel = advancementLevelForm.getUserAdvancementLevel();
//        System.out.println(userAdvancementLevel);
//        Integer userAdvancementLevel = advancementLevelForm.getUserAdvancementLevel();
//        Integer userAdvancementLevel = 50;
        RandomExerciseGenerator randomExerciseGenerator = new RandomExerciseGenerator();
        randomExerciseGenerator.generateWarmUpFromFile();
        System.out.println(randomExerciseGenerator.warmUpList);

//        //tworzę listę z ćwiczeniami (zamiast json)
//        List<Exercises> warmUpList = new ArrayList<>();
//        Exercises warmUp1 = new Exercises("warmUp1", 5);
//        Exercises warmUp2 = new Exercises("warmUp2", 5);
//        Exercises warmUp3 = new Exercises("warmUp3", 5);
//        Exercises warmUp4 = new Exercises("warmUp4", 5);
//        Exercises warmUp5 = new Exercises("warmUp5", 5);
//        Exercises warmUp6 = new Exercises("warmUp6", 5);
//        Exercises warmUp7 = new Exercises("warmUp7", 5);
//        warmUpList.add(warmUp1);
//        warmUpList.add(warmUp2);
//        warmUpList.add(warmUp3);
//        warmUpList.add(warmUp4);
//        warmUpList.add(warmUp5);
//        warmUpList.add(warmUp6);
//        warmUpList.add(warmUp7);
//
////        List<Exercises> generatedWarmUpList1 = randomExerciseGenerator.generateExercise(warmUpList, userAdvancementLevel, randomExerciseGenerator.getIndicatorOfWarmUp());
////        for (int i = 0; i < generatedWarmUpList1.size(); i++) {
////            System.out.println(generatedWarmUpList1.get(i).getExerciseName());
//        }
//        List<Exercises> coreExercisesList = new ArrayList<>();
//        Exercises coreExercise1 = new Exercises("coreExercise1", 5);
//        Exercises coreExercise2 = new Exercises("coreExercise2", 5);
//        Exercises coreExercise3 = new Exercises("coreExercise3", 5);
//        Exercises coreExercise4 = new Exercises("coreExercise4", 5);
//        Exercises coreExercise5 = new Exercises("coreExercise5", 5);
//        Exercises coreExercise6 = new Exercises("coreExercise6", 5);
//        Exercises coreExercise7 = new Exercises("coreExercise7", 5);
//        Exercises coreExercise8 = new Exercises("coreExercise8", 5);
//        Exercises coreExercise9 = new Exercises("coreExercise9", 5);
//        Exercises coreExercise10 = new Exercises("coreExercise10", 5);
//        coreExercisesList.add(coreExercise1);
//        coreExercisesList.add(coreExercise2);
//        coreExercisesList.add(coreExercise3);
//        coreExercisesList.add(coreExercise4);
//        coreExercisesList.add(coreExercise5);
//        coreExercisesList.add(coreExercise6);
//        coreExercisesList.add(coreExercise7);
//        coreExercisesList.add(coreExercise8);
//        coreExercisesList.add(coreExercise9);
//        coreExercisesList.add(coreExercise10);
//
////        List<Exercises> generatedCoreExerciseList = randomExerciseGenerator.generateExercise(coreExercisesList, userAdvancementLevel, randomExerciseGenerator.getIndicatorOfCoreExercises());
////        for (int i = 0; i < generatedCoreExerciseList.size(); i++) {
////            System.out.println(generatedCoreExerciseList.get(i).getExerciseName());
////        }
//        List<Exercises> stretchingList = new ArrayList<>();
//        Exercises stretching1 = new Exercises("Stretching1", 5);
//        Exercises stretching2 = new Exercises("Stretching2", 5);
//        Exercises stretching3 = new Exercises("Stretching3", 5);
//        Exercises stretching4 = new Exercises("Stretching4", 5);
//        Exercises stretching5 = new Exercises("Stretching5", 5);
//        Exercises stretching6 = new Exercises("Stretching6", 5);
//        stretchingList.add(stretching1);
//        stretchingList.add(stretching2);
//        stretchingList.add(stretching3);
//        stretchingList.add(stretching4);
//        stretchingList.add(stretching5);
//        stretchingList.add(stretching6);
//
////        List<Exercises> generatedStretchingList = randomExerciseGenerator.generateExercise(stretchingList, userAdvancementLevel, randomExerciseGenerator.getIndicatorOfStretching());
////        for (int i = 0; i < generatedStretchingList.size(); i++) {
////            System.out.println(generatedStretchingList.get(i).getExerciseName());
    }

//        System.out.println("-------------------------------------------------------------------------------------------");
//
//        JavaFanaticsTerminalLogo javaFanaticsTerminalLogo = new JavaFanaticsTerminalLogo();
//        JavaFanaticsTerminalLogo.javaFanaticsLogo();
//
//        System.out.println("-------------------------------------------------------------------------------------------");
//
//        WelcomePage welcomePage = new WelcomePage();
//        System.out.println(welcomePage.welcomePageInfo);
//
//        System.out.println("-------------------------------------------------------------------------------------------");
//
//        UserSignInAndLogIn userSignInAndLogIn = new UserSignInAndLogIn();
//        System.out.println(userSignInAndLogIn.userSignInAndLogInInfo);
//
//        System.out.println("-------------------------------------------------------------------------------------------");
//
//        UserInterface userInterface = new UserInterface();
//        System.out.println(userInterface.userInterfaceInfo);
//
//        System.out.println("-------------------------------------------------------------------------------------------");
//
//        AdvancementLevelForm advancementLevelForm = new AdvancementLevelForm();
//        System.out.println(advancementLevelForm.advancementLevelFormInfo);
//
//        System.out.println("-------------------------------------------------------------------------------------------");
//
//        LevelOfAdvancement levelOfAdvancement = new LevelOfAdvancement();
//        System.out.println(levelOfAdvancement.assignLevelOfAdvancementInfo);
//
//        System.out.println("-------------------------------------------------------------------------------------------");
//
//        RandomExerciseGenerator randomExerciseGenerator = new RandomExerciseGenerator();
//        System.out.println(randomExerciseGenerator.randomExerciseGeneratorInfo);
//
//        System.out.println("-------------------------------------------------------------------------------------------");
//
//        Exercises exercises = new Exercises();
//        System.out.println(exercises.addingExercisesInfo);
//
//        System.out.println("-------------------------------------------------------------------------------------------");
}
//}
