package com.isa.jjdzr.user.service;

import com.isa.jjdzr.user.model.User;
import com.isa.jjdzr.utils.Validation;

import java.util.List;

public class UserSignIn {

    public static User login(List<User> allUsers) {
        return Validation.validateCorrectUserLogin(allUsers);
    }

    private UserSignIn() {
    }
}