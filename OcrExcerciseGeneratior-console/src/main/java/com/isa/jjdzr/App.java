package com.isa.jjdzr;

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
        System.out.println(userSignInAndLogIn.userSignInAndLogInInfo);

        System.out.println("-------------------------------------------------------------------------------------------");

        UserInterface userInterface = new UserInterface();
        System.out.println(userInterface.userInterfaceInfo);

        System.out.println("-------------------------------------------------------------------------------------------");

        AdvancementLevelForm advancementLevelForm = new AdvancementLevelForm();
        System.out.println(advancementLevelForm.advancementLevelFormInfo);

        System.out.println("-------------------------------------------------------------------------------------------");

        AssignLevelOfAdvancement assignLevelOfAdvancement = new AssignLevelOfAdvancement();
        System.out.println(assignLevelOfAdvancement.assignLevelOfAdvancementInfo);

        System.out.println("-------------------------------------------------------------------------------------------");

        RandomExerciseGenerator randomExerciseGenerator = new RandomExerciseGenerator();
        System.out.println(randomExerciseGenerator.randomExerciseGeneratorInfo);

        System.out.println("-------------------------------------------------------------------------------------------");

        AddingExercises addingExercises = new AddingExercises();
        System.out.println(addingExercises.addingExercisesInfo);

        System.out.println("-------------------------------------------------------------------------------------------");

    }


}
