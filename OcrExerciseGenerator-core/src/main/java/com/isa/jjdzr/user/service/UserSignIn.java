package com.isa.jjdzr.user.service;

import com.isa.jjdzr.user.model.User;
import com.isa.jjdzr.utils.Validation;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserSignIn {

    public static User login(List<User> allUsers) {
        return Validation.validateCorrectUserLogin(allUsers);
    }

    private UserSignIn() {
    }
}