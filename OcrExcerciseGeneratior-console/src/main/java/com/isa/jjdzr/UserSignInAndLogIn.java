package com.isa.jjdzr;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class UserSignInAndLogIn {

    static void login() {
        Scanner scanner = new Scanner(System.in);
        List<User> usersList = WriteAndReadFromFiles.readUserList();
        System.out.println("Podaj nazwe użytkownika");
        String userName = scanner.nextLine();
        while (!usersList.stream().map(User::getUserName).toList().contains(userName)) {
            System.out.println("Użytkownik nie istnieje \nPodaj nazwe użytkownika jeszcze raz");
            userName = scanner.nextLine();
        }
        String finalUser = userName;
        User user = usersList.stream().filter(u -> u.getUserName().equals(finalUser)).findFirst().orElseThrow();
        System.out.println("Podaj hasło");
        String userPassword = scanner.nextLine();
        while (!Objects.equals(user.getUserPassword(), userPassword)) {
            System.out.println("Hasło nie jest poprawne");
            userPassword = scanner.nextLine();
        }
        System.out.println("Zalogowano pomyślnie");

        UserInterface userInterface = new UserInterface(user);
        userInterface.userInterfaceMenu();
        WriteAndReadFromFiles.writeUserList(usersList);
    }
}
