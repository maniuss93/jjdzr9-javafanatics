package com.isa.jjdzr.user.service;

import com.isa.jjdzr.user.model.User;
import com.isa.jjdzr.utils.WriteAndReadFromFile;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
public class UserDataBase {
    private static final List<User> allUsers = WriteAndReadFromFile.readUserList();

    void saveUserToDataBase(User user) {
        if (allUsers.contains(user)) {
            allUsers.set(allUsers.indexOf(user), user);
        } else {
            allUsers.add(user);
        }
        WriteAndReadFromFile.writeUserList(allUsers);
    }
    List<User> getAllUsers() {
        return allUsers;
    }

    private UserDataBase() {
    }

}
