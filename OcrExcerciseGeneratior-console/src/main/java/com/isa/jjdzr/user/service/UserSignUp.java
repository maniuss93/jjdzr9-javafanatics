package com.isa.jjdzr.user.service;

import com.isa.jjdzr.console.Menu;
import com.isa.jjdzr.interfaces.Printable;
import com.isa.jjdzr.user.model.User;
import com.isa.jjdzr.utils.WriteAndReadFromFile;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
public class UserSignUp {
    private static final List<User> allUsers = new ArrayList<>();
    static Printable menu = new Menu();

    public static void createUser() {
        User user = new User();
        allUsers.addAll(WriteAndReadFromFile.readUserList());
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
        user.setUserName(userName);
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
        user.setUserPassword(password);
        menu.printActualLine("Podaj adres email");
        String email = scanner.nextLine();
        List<String> listOfEmails = allUsers.stream().map(User::getUserEmail).toList();
        while (listOfEmails.contains(email) || email.length() < 6 || !email.contains("@") || !email.contains(".")) {
            if (listOfEmails.contains(email)) {
                menu.printActualLine("Ten adres email posiada już konto. Proszę użyc inny adres email.");
                email = scanner.nextLine();
            } else {
                menu.printActualLine("To nie jest adres email");
                email = scanner.nextLine();
            }
        }
        user.setUserEmail(email);
        user.setUserID(GenerateID.generateUserID(allUsers));
        menu.printUserNameInBrackes(user.getUserName());
        allUsers.add(user);
        WriteAndReadFromFile.writeUserList(allUsers);
    }
}



