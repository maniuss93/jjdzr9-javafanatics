package com.isa.jjdzr;

import java.util.*;

class UserSignUp {
    private static final List<User> allUsers = new ArrayList<>();

    static void createUser() {
        User user = new User();
        allUsers.addAll(WriteAndReadFromFiles.readUserList());
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
        user.setUserName(userName);
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
        user.setUserPassword(password);
        System.out.println("Podaj adres email");
        String email = scanner.nextLine();
        List<String> listOfEmails = allUsers.stream().map(User::getUserEmail).toList();
        while (listOfEmails.contains(email) || email.length() < 6 || !email.contains("@") || !email.contains(".")) {
            if (listOfEmails.contains(email)) {
                System.out.println("Ten adres email posiada już konto. Proszę użyc inny adres email.");
                email = scanner.nextLine();
            } else {
                System.out.println("To nie jest adres email");
                email = scanner.nextLine();
            }
        }
        user.setUserEmail(email);
        System.out.println("Utworzono użytkownika '" + user.getUserName() + "'");
        allUsers.add(user);

        WriteAndReadFromFiles.writeUserList(allUsers);
    }
}



