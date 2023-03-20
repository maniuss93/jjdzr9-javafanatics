package com.isa.jjdzr.user.service;

import com.isa.jjdzr.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    // klasa zawierająca metody które coś robią z userem: GET, POST, DELETE - fasada
    private final UserDataBase userDataBase;

    public void createUser(User user) {
        userDataBase.saveUserToDataBase(user);
    }
    public List<User> getAllUsers(){
        return userDataBase.getAllUsers();
    }
}
