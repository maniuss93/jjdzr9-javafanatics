package com.isa.jjdzr.service;

import com.isa.jjdzr.dto.UserDto;
import com.isa.jjdzr.mapper.UserMapper;
import com.isa.jjdzr.repository.UserRepository;
import com.isa.jjdzr.model.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    
    public UserDto createUser(UserDto userDto) {
        User user = userMapper.userDtoToEntity(userDto);
        User saved = userRepository.save(user);
        return userMapper.userEntityToDto(saved);
    }

    @Transactional
    public UserDto editUser(UserDto user) {
        Optional<User> userById = userRepository.findById(user.getUserId());
        if (userById.isPresent()) {
            User userFromDb = userById.get();
            userFromDb = userMapper.updateEntity(userFromDb, user);
            userRepository.save(userFromDb);
            return userMapper.userEntityToDto(userFromDb);
        }
        return null;
    }

    public List<UserDto> findAllUsers() {
        List<User> findAll = userRepository.findAll();
        return userMapper.allUsersToDto(findAll);
    }

    public void isUserAuthorized (Long id, Authentication authentication){
        if (authentication == null || !(authentication.getPrincipal() instanceof User userDetails)) {
            throw new AccessDeniedException("Access Denied");
        }
        if (!userDetails.getUserId().equals(id)) {
            throw new AccessDeniedException("Access Denied");
        }
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

    public Optional<User> findByUserId(Long id) {
        return userRepository.findByUserId(id);
    }

    @Transactional
    public void deleteUser(Long id) {
        User user = userRepository.findById(id).
                orElseThrow(() -> new IllegalArgumentException("Invalid User Id:" + id));
        userRepository.delete(user);
    }
}
