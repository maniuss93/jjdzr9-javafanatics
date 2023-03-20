package com.isa.jjdzr.utils;

import com.isa.jjdzr.exercise.model.Exercise;
import com.isa.jjdzr.user.model.User;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Validation {

    public static String validateNewUserName(List<User> allUsers) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj nazwe użytkownika");
        String userName = scanner.nextLine();
        List<String> listOfUsersName = allUsers.stream().map(User::getUserName).toList();
        while (listOfUsersName.contains(userName) || userName.length() > 10 || userName.length() < 2) {
            if (listOfUsersName.contains(userName)) {
                System.out.println("Ta nazwa użytkownika już istnieje");
                userName = scanner.nextLine();
            } else if (userName.length() > 10) {
                System.out.println("Nazwa użytkownika jest zbyt długa. Maksymalnie 10 znaków");
                userName = scanner.nextLine();
            } else {
                System.out.println("Nazwa użytkownika jest zbyt krótka. Conajmniej 2 znaki");
                userName = scanner.nextLine();
            }
        }
        return userName;
    }

    public static String validateUserPassword() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Stwórz hasło");
        String password = scanner.nextLine();
        while (password.length() > 10 || password.length() < 3) {
            if (password.length() > 10) {
                System.out.println("Hasło jest zbyt długie. Maksymalnie 10 znaków");
                password = scanner.nextLine();
            } else {
                System.out.println("Hasło jest zbyt krótkie. Conajmniej 3 znaki");
                password = scanner.nextLine();
            }
        }
        System.out.println("Powtórz hasło");
        while (!Objects.equals(password, scanner.nextLine())) {
            System.out.println("Hasła nie są identyczne\nPowtórz hasło");
        }
        return password;
    }

    public static String validateEmailAddress(List<User> allUsers) {
        Scanner scanner = new Scanner(System.in);
        String emailPattern ="^[a-zA-Z0-9._%+-]{2,}@[a-zA-Z0-9.-]{2,}\\.[a-zA-Z]{2,}$";
        System.out.println("Podaj adres email");
        String email = scanner.nextLine();
        List<String> listOfEmails = allUsers.stream().map(User::getUserEmail).toList();
        while (listOfEmails.contains(email) || email.length() < 6 || !email.matches(emailPattern)) {
            if (listOfEmails.contains(email)) {
                System.out.println("Ten adres email posiada już konto. Proszę użyc inny adres email.");
                email = scanner.nextLine();
            } else {
                System.out.println("To nie jest adres email");
                email = scanner.nextLine();
            }
        }
        return email;
    }

    public static User validateCorrectUserLogin(List<User> allUsers) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj nazwe użytkownika");
        String userName = scanner.nextLine();
        while (!allUsers.stream().map(User::getUserName).toList().contains(userName)) {
            System.out.println("Użytkownik nie istnieje \nPodaj nazwe użytkownika jeszcze raz");
            userName = scanner.nextLine();
        }
        String finalUser = userName;
        System.out.println("Podaj hasło");
        String userPassword = scanner.nextLine();
        User user = allUsers.stream().filter(u -> u.getUserName().equals(finalUser)).findFirst().orElseThrow();
        while (!Objects.equals(user.getUserPassword(), userPassword)) {
            System.out.println("Hasło nie jest poprawne");
            userPassword = scanner.nextLine();
        }
        return user;
    }

    public static String validateNewExerciseName(List<Exercise> exerciseList) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj nazwe cwiczenia");
        String exerciseName = scanner.nextLine();
        List<String> listOfExercisesName = exerciseList.stream().map(Exercise::getExerciseName).toList();
        while (listOfExercisesName.contains(exerciseName) || exerciseName.length() > 20 || exerciseName.length() < 3) {
            if (listOfExercisesName.contains(exerciseName)) {
                System.out.println("Ta nazwa cwiczenia już istnieje");
                exerciseName = scanner.nextLine();
            } else if (exerciseName.length() > 20) {
                System.out.println("Nazwa cwiczenia jest zbyt długa. Maksymalnie 20 znaków");
                exerciseName = scanner.nextLine();
            } else {
                System.out.println("Nazwa cwiczenia jest zbyt krótka. Conajmniej 3 znaki");
                exerciseName = scanner.nextLine();
            }
        }
        return exerciseName;
    }

    public static String validateAddressUrl() {
        Scanner scanner = new Scanner(System.in);
        String addressUrlPattern = "^www\\.[a-zA-Z0-9-]{2,}\\.[a-zA-Z]{2,}$";
        System.out.println("Podaj adres url (Zaczynając od www..)");
        String url = scanner.nextLine();
        while (!url.matches(addressUrlPattern)) {
            System.out.println("Nieprawidłowy format");
            url = scanner.nextLine();
        }
        return url;
    }

    public static int validateCorrectEffortPointsFormat() {
        Scanner scanner = new Scanner(System.in);
        int effortPoints = 0;
        while (!(effortPoints == 5 || effortPoints == 10 || effortPoints == 15)) {
            try {
                System.out.println("Dodaj wartość punktową ćwiczenia (5, 10 lub 20)");
                effortPoints = scanner.nextInt();
                switch (effortPoints) {
                    case 5, 10, 15 -> {}
                    default -> System.out.println("Nieprawidłowa wartość");
                }
            } catch (InputMismatchException e) {
                System.out.println("Nieprawidłowa wartość");
                scanner.nextLine();
            }
        }
        return effortPoints;
    }


    public static String validateCorrectExerciseDescription() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj opis ćwiczenia");
        String description = scanner.nextLine();
        while ((description.length() < 5 || description.length() > 2000)) {
            if (description.length() < 5) {
                System.out.println("Opis jest zbyt krótki");
                description = scanner.nextLine();
            } else {
                System.out.println("Opis jest zbyt długi");
                description = scanner.nextLine();
            }
        }
        return description;
    }

    public static boolean isUserSignedUp(User user) {
        return !Objects.equals(user.getUserName(), "");
    }
}
