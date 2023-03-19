package com.isa.jjdzr.utils;

import com.isa.jjdzr.console.Menu;
import com.isa.jjdzr.console.UserPanel;
import com.isa.jjdzr.exercise.model.Exercise;
import com.isa.jjdzr.interfaces.Printable;
import com.isa.jjdzr.user.model.User;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Validation {
    static Printable menu = new Menu();

    public static String validateNewUserName(List<User> allUsers) {
        Scanner scanner = new Scanner(System.in);
        menu.printActualLine("Podaj nazwe użytkownika");
        String userName = scanner.nextLine();
        List<String> listOfUsersName = allUsers.stream().map(User::getUserName).toList();
        while (listOfUsersName.contains(userName) || userName.length() > 10 || userName.length() < 2) {
            if (listOfUsersName.contains(userName)) {
                menu.printActualLine("Ta nazwa użytkownika już istnieje");
                userName = scanner.nextLine();
            } else if (userName.length() > 10) {
                menu.printActualLine("Nazwa użytkownika jest zbyt długa. Maksymalnie 10 znaków");
                userName = scanner.nextLine();
            } else {
                menu.printActualLine("Nazwa użytkownika jest zbyt krótka. Conajmniej 2 znaki");
                userName = scanner.nextLine();
            }
        }
        return userName;
    }

    public static String validateUserPassword() {
        Scanner scanner = new Scanner(System.in);
        menu.printActualLine("Stwórz hasło");
        String password = scanner.nextLine();
        while (password.length() > 10 || password.length() < 3) {
            if (password.length() > 10) {
                menu.printActualLine("Hasło jest zbyt długie. Maksymalnie 10 znaków");
                password = scanner.nextLine();
            } else {
                menu.printActualLine("Hasło jest zbyt krótkie. Conajmniej 3 znaki");
                password = scanner.nextLine();
            }
        }
        menu.printActualLine("Powtórz hasło");
        while (!Objects.equals(password, scanner.nextLine())) {
            menu.printActualLine("Hasła nie są identyczne\nPowtórz hasło");
        }
        return password;
    }

    public static String validateEmailAddress(List<User> allUsers) {
        Scanner scanner = new Scanner(System.in);
        String emailPattern ="^[a-zA-Z0-9._%+-]{2,}@[a-zA-Z0-9.-]{2,}\\.[a-zA-Z]{2,}$";
        menu.printActualLine("Podaj adres email");
        String email = scanner.nextLine();
        List<String> listOfEmails = allUsers.stream().map(User::getUserEmail).toList();
        while (listOfEmails.contains(email) || email.length() < 6 || !email.matches(emailPattern)) {
            if (listOfEmails.contains(email)) {
                menu.printActualLine("Ten adres email posiada już konto. Proszę użyc inny adres email.");
                email = scanner.nextLine();
            } else {
                menu.printActualLine("To nie jest adres email");
                email = scanner.nextLine();
            }
        }
        return email;
    }

    public static User validateCorrectUserLogin(List<User> allUsers) {
        Scanner scanner = new Scanner(System.in);
        menu.printActualLine("Podaj nazwe użytkownika");
        String userName = scanner.nextLine();
        while (!allUsers.stream().map(User::getUserName).toList().contains(userName)) {
            menu.printActualLine("Użytkownik nie istnieje \nPodaj nazwe użytkownika jeszcze raz");
            userName = scanner.nextLine();
        }
        String finalUser = userName;
        menu.printActualLine("Podaj hasło");
        String userPassword = scanner.nextLine();
        User user = allUsers.stream().filter(u -> u.getUserName().equals(finalUser)).findFirst().orElseThrow();
        while (!Objects.equals(user.getUserPassword(), userPassword)) {
            menu.printActualLine("Hasło nie jest poprawne");
            userPassword = scanner.nextLine();
        }
        return user;
    }

    public static String validateNewExerciseName(List<Exercise> exerciseList) {
        Scanner scanner = new Scanner(System.in);
        menu.printActualLine("Podaj nazwe cwiczenia");
        String exerciseName = scanner.nextLine();
        List<String> listOfExercisesName = exerciseList.stream().map(Exercise::getExerciseName).toList();
        while (listOfExercisesName.contains(exerciseName) || exerciseName.length() > 20 || exerciseName.length() < 3) {
            if (listOfExercisesName.contains(exerciseName)) {
                menu.printActualLine("Ta nazwa cwiczenia już istnieje");
                exerciseName = scanner.nextLine();
            } else if (exerciseName.length() > 20) {
                menu.printActualLine("Nazwa cwiczenia jest zbyt długa. Maksymalnie 20 znaków");
                exerciseName = scanner.nextLine();
            } else {
                menu.printActualLine("Nazwa cwiczenia jest zbyt krótka. Conajmniej 3 znaki");
                exerciseName = scanner.nextLine();
            }
        }
        return exerciseName;
    }

    public static String validateAddressUrl() {
        Scanner scanner = new Scanner(System.in);
        String addressUrlPattern = "^www\\.[a-zA-Z0-9-]{2,}\\.[a-zA-Z]{2,}$";
        menu.printActualLine("Podaj adres url (Zaczynając od www..)");
        String url = scanner.nextLine();
        while (!url.matches(addressUrlPattern)) {
            menu.printActualLine("Nieprawidłowy format");
            url = scanner.nextLine();
        }
        return url;
    }

    public static int validateCorrectEffortPointsFormat() {
        Scanner scanner = new Scanner(System.in);
        int effortPoints = 0;
        while (!(effortPoints == 5 || effortPoints == 10 || effortPoints == 15)) {
            try {
                menu.printActualLine("Dodaj wartość punktową ćwiczenia (5, 10 lub 20)");
                effortPoints = scanner.nextInt();
                switch (effortPoints) {
                    case 5 -> {}
                    case 10 -> {}
                    case 15 -> {}
                    default -> menu.printActualLine(UserPanel.wrongInput);
                }
            } catch (InputMismatchException e) {
                menu.printActualLine(UserPanel.wrongInput);
                scanner.nextLine();
            }
        }
        return effortPoints;
    }


    public static String validateCorrectExerciseDescription() {
        Scanner scanner = new Scanner(System.in);
        menu.printActualLine("Podaj opis ćwiczenia");
        String description = scanner.nextLine();
        while ((description.length() < 5 || description.length() > 2000)) {
            if (description.length() < 5) {
                menu.printActualLine("Opis jest zbyt krótki");
                description = scanner.nextLine();
            } else {
                menu.printActualLine("Opis jest zbyt długi");
                description = scanner.nextLine();
            }
        }
        return description;
    }

    public static boolean isUserSignedUp(User user) {
        return !Objects.equals(user.getUserName(), "");
    }
}
