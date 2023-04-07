package com.isa.jjdzr.user.service;

import com.isa.jjdzr.user.model.User;
import com.isa.jjdzr.utils.Validation;

import java.util.List;

public class UserSignUp {

    public static User createUser(List<User> allUsers) {
        User user = new User();
        user.setUserName(Validation.validateNewUserName(allUsers));
        user.setUserPassword(Validation.validateUserPassword());
        user.setUserEmail(Validation.validateEmailAddress(allUsers));
        user.setUserId(GenerateUserId.generateUserID(allUsers));
        return user;
    }

    private UserSignUp() {
    }
}