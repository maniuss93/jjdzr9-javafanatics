package com.isa.jjdzr;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class UserSignInAndLogIn {

    static void login() {
        ObjectMapper objectMapper = new ObjectMapper();
        Scanner scanner = new Scanner(System.in);
        List<User> usersList = null;
        try {
            usersList = objectMapper.readValue
                    (new File("src/main/resources/user.json"), new TypeReference<>() {
                    });
        } catch (IOException e) {
            System.out.println("Nie można wczytać listy użytkowników");
        }
        List<String> usersNames = usersList.stream().map(User::getUserName).toList();
        System.out.println("Podaj nazwe użytkownika");
        String name = scanner.nextLine();
        while (!usersNames.contains(name)) {
            System.out.println("Użytkownik nie istnieje \nPodaj nazwe użytkownika jeszcze raz");
            name = scanner.nextLine();
        }
        String finalUser = name;
        User user2 = usersList.stream().filter(user -> user.getUserName().equals(finalUser)).findFirst().orElseThrow();
        System.out.println(user2.getUserPassword() + " " + user2.getUserEmail());
        System.out.println("Podaj hasło");
    }
}
