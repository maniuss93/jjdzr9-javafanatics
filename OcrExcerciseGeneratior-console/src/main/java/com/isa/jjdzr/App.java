package com.isa.jjdzr;
import java.util.Scanner;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )    {
        System.out.println( "JavaFanatic" );

        System.out.println("-------------------------------------------------------------------------------------------");

        JavaFanaticsTerminalLogo javaFanaticsTerminalLogo = new JavaFanaticsTerminalLogo();
        JavaFanaticsTerminalLogo.javaFanaticsLogo();

        System.out.println("-------------------------------------------------------------------------------------------");

        WelcomePage welcomePage = new WelcomePage();
        System.out.println(welcomePage.welcomePageInfo);

        System.out.println("-------------------------------------------------------------------------------------------");

        UserSignInAndLogIn userSignInAndLogIn = new UserSignInAndLogIn();
        System.out.println("-------------------------------------------------------------------------------------------");

        UserInterface userInterface = new UserInterface();
        System.out.println(userInterface.userInterfaceInfo);

        System.out.println("-------------------------------------------------------------------------------------------");

        AdvancementLevelForm advancementLevelForm = new AdvancementLevelForm();
        System.out.println(advancementLevelForm.advancementLevelFormInfo);

        System.out.println("-------------------------------------------------------------------------------------------");

        LevelOfAdvancement levelOfAdvancement = new LevelOfAdvancement();
        System.out.println(levelOfAdvancement.assignLevelOfAdvancementInfo);

        System.out.println("-------------------------------------------------------------------------------------------");

        RandomExerciseGenerator randomExerciseGenerator = new RandomExerciseGenerator();
        System.out.println(randomExerciseGenerator.randomExerciseGeneratorInfo);

        System.out.println("-------------------------------------------------------------------------------------------");

        Exercises exercises = new Exercises();
        System.out.println(exercises.addingExercisesInfo);

        System.out.println("-------------------------------------------------------------------------------------------");

    }


}
