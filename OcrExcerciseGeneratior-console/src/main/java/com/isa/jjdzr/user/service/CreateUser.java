package com.isa.jjdzr.user.service;

import com.isa.jjdzr.user.model.User;
import com.isa.jjdzr.utils.WriteAndReadFromFile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class CreateUser {

    User createUser(User user) {
        List<User> allUsers = WriteAndReadFromFile.readUserList();
        List<String> listOfUsersName = allUsers.stream().map(User::getUserName).toList();
        if (listOfUsersName.contains(user.getUserName()) || user.getUserName().length() > 10 || user.getUserName().length() < 2) {
            if (listOfUsersName.contains(user.getUserName())) {
                throw new RuntimeException("Ta nazwa użytkownika już istnieje");
            } else if (user.getUserName().length() > 10) {
                throw new RuntimeException("Nazwa użytkownika jest zbyt długa. Maksymalnie 10 znaków");
            } else {
                throw new RuntimeException("Nazwa użytkownika jest zbyt krótka. Conajmniej 2 znaki");
            }
        }

        if (user.getUserPassword().length() > 10 || user.getUserPassword().length() < 3) {
            if (user.getUserPassword().length() > 10) {
                throw new RuntimeException("Hasło jest zbyt długie. Maksymalnie 10 znaków");
            } else {
                throw new RuntimeException("Hasło jest zbyt krótkie. Conajmniej 3 znaki");
            }
        }

        List<String> listOfEmails = allUsers.stream().map(User::getUserEmail).toList();
        if (listOfEmails.contains(user.getUserEmail()) || user.getUserEmail().length() < 6 || !user.getUserEmail().contains("@") || !user.getUserEmail().contains(".")) {
            if (listOfEmails.contains(user.getUserEmail())) {
                throw new RuntimeException("Ten adres email posiada już konto. Proszę użyc inny adres email.");

            } else {
                throw new RuntimeException("To nie jest adres email");

            }
        }

        allUsers.add(user);
        WriteAndReadFromFile.writeUserList(allUsers);
        return allUsers.stream().filter(u -> u.getUserEmail().equals(user.getUserEmail())).findAny().orElseThrow();
    }
}


