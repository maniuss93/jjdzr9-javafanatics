package com.isa.jjdzr.service;

import com.isa.jjdzr.repository.UserRepository;
import com.isa.jjdzr.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    // klasa zawierająca metody które coś robią z userem: GET, POST, DELETE - fasada

    private final UserRepository userRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User editUser(User user) {
        Optional<User> userById = userRepository.findById(user.getUserID());
        if (userById.isPresent()) {
            User userDB = userById.get();
            userDB.setUserName(user.getUserName());
            userDB.setUserEmail(user.getUserEmail());
            userDB.setUserPassword(user.getUserPassword());
            userDB.setUserID(user.getUserID());
            userDB.setUserAdvancementLevel(userDB.getUserAdvancementLevel());
            return userDB;
        }
        return null;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    public User findByUserId (Long id) {
        return userRepository.findByUserID(id);
    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id).
                orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));
        userRepository.delete(user);
    }
}
