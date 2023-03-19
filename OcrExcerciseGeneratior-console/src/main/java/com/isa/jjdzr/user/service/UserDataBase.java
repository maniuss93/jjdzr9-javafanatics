package com.isa.jjdzr.user.service;

import com.isa.jjdzr.user.model.User;
import com.isa.jjdzr.utils.WriteAndReadFromFile;

import java.util.List;

public class UserDataBase {
    private static final List<User> allUsers = WriteAndReadFromFile.readUserList();

    public static void saveUserToDataBase(User user) {
        allUsers.add(user);
        WriteAndReadFromFile.writeUserList(allUsers);
    }

    public static List<User> getAllUsers() {
        return allUsers;
    }

    private UserDataBase() {
    }

}
