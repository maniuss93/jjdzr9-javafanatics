package com.isa.jjdzr.user.service;

import com.isa.jjdzr.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    // klasa zawierająca metody które coś robią z userem: GET, POST, DELETE - fasada
    private final CreateUser createUser;

    public User createUser(User user) {
        return createUser.createUser(user);
    }
}
