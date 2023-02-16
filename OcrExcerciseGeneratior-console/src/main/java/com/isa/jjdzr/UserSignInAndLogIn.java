package com.isa.jjdzr;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class UserSignInAndLogIn {

    static void login() {
        ObjectMapper objectMapper = new ObjectMapper();
        Scanner scanner = new Scanner(System.in);
        List<User> usersList = null;
        try {
            usersList = objectMapper.readValue(new File("src/main/resources/user.json"), new TypeReference<>() {
            });
        } catch (IOException e) {
            System.out.println("Nie można wczytać listy użytkowników");
        }
        List<String> usersNames = usersList.stream().map(User::getUserName).toList();
        System.out.println("Podaj nazwe użytkownika");
        String userName = scanner.nextLine();
        while (!usersNames.contains(userName)) {
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
        UserInterface userInterface = new UserInterface();
        userInterface.showUserInterfaceMenu();
    }
}
