package com.isa.jjdzr.user.service;

import com.isa.jjdzr.console.Menu;
import com.isa.jjdzr.interfaces.Printable;
import com.isa.jjdzr.user.model.User;
import com.isa.jjdzr.utils.Validation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public
class UserSignUp {
    static Printable menu = new Menu();

    public static User createUser(List<User> allUsers) {
        User user = new User();
        user.setUserName(Validation.validateNewUserName(allUsers));
        user.setUserPassword(Validation.validateUserPassword());
        user.setUserEmail(Validation.validateEmailAddress(allUsers));
        user.setUserID(GenerateID.generateUserID(allUsers));
        menu.printUserNameInBrackes(user.getUserName());
        return user;
    }

    private UserSignUp() {
    }
}