package com.isa.jjdzr.user.service;

import com.isa.jjdzr.user.model.User;

import java.util.List;

class GenerateUserId {

    public static Long generateUserID(List<User> usersList) {
        return usersList.stream().map(User::getUserId)
                       .reduce((first, second) -> second).orElse(0L) + 1;
    }

    private GenerateUserId() {
    }
}
