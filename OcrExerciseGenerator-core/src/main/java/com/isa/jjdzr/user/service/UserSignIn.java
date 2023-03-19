package com.isa.jjdzr.user.service;

import com.isa.jjdzr.console.Menu;
import com.isa.jjdzr.interfaces.Printable;
import com.isa.jjdzr.user.model.User;
import com.isa.jjdzr.utils.Validation;

import java.util.List;

public class UserSignIn {
    static Printable menu = new Menu();

    public static User login(List<User> allUsers) {
        User user = Validation.validateCorrectUserLogin(allUsers);
        menu.printActualLine("Zalogowano pomyślnie");
        return user;
    }

    private UserSignIn() {
    }
}