package com.isa.jjdzr.user.service;

import com.isa.jjdzr.console.UserPanel;
import com.isa.jjdzr.console.Menu;
import com.isa.jjdzr.interfaces.Printable;
import com.isa.jjdzr.user.model.User;
import com.isa.jjdzr.utils.WriteAndReadFromFile;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class UserSignInAndLogIn {
    static Printable menu = new Menu();


    public static void login() {
        Scanner scanner = new Scanner(System.in);
        List<User> usersList = WriteAndReadFromFile.readUserList();
        menu.printActualLine("Podaj nazwe użytkownika");
        String userName = scanner.nextLine();
        while (!usersList.stream().map(User::getUserName).toList().contains(userName)) {
            menu.printActualLine("Użytkownik nie istnieje \nPodaj nazwe użytkownika jeszcze raz");
            userName = scanner.nextLine();
        }
        String finalUser = userName;
        User user = usersList.stream().filter(u -> u.getUserName().equals(finalUser)).findFirst().orElseThrow();
        menu.printActualLine("Podaj hasło");
        String userPassword = scanner.nextLine();
        while (!Objects.equals(user.getUserPassword(), userPassword)) {
            menu.printActualLine("Hasło nie jest poprawne");
            userPassword = scanner.nextLine();
        }
        menu.printActualLine("Zalogowano pomyślnie");
        UserPanel userPanel = new UserPanel(user.getUserAdvancementLevel(), user.getUserExercises());
        userPanel.userPanelMenu();
        user.setUserAdvancementLevel(userPanel.advancementLevelForm.getUserAdvancementLevel());
        user.setUserExercises(userPanel.getUserExercises());
        WriteAndReadFromFile.writeUserList(usersList);
    }
}
