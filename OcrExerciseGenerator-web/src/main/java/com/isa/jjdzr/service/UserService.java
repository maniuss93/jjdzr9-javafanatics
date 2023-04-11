package com.isa.jjdzr.service;

import com.isa.jjdzr.repository.UserRepository;
import com.isa.jjdzr.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User editUser(User user) {
        Optional<User> userById = userRepository.findById(user.getUserId());
        if (userById.isPresent()) {
            User userFromDb = userById.get();
            userFromDb.setUserName(user.getUserName());
            userFromDb.setUserEmail(user.getUserEmail());
            userFromDb.setUserPassword(user.getUserPassword());
            userFromDb.setUserId(user.getUserId());
            userFromDb.setUserAdvancementLevel(user.getUserAdvancementLevel());
            return userFromDb;
        }
        return null;
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public boolean existsByEmail(String userEmail) {
        return userRepository.existsByUserEmail(userEmail);
    }

    public boolean existsByName(String userName) {
        return userRepository.existsByUserName(userName);
    }

    public Optional<User> findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    public User findByUserId(Long id) {
        return userRepository.findByUserId(id);
    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id).
                orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));
        userRepository.delete(user);
    }
}
