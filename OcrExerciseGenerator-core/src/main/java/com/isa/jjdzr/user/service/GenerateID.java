package com.isa.jjdzr.user.service;

import com.isa.jjdzr.user.model.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
class GenerateID {

    public static Long generateUserID(List<User> usersList) {
        return usersList.stream().map(User::getUserId)
                .reduce((first, second) -> second).orElse(0L) + 1;
    }
    private GenerateID() {
    }
}
