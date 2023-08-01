package com.isa.jjdzr.mapper;

import com.isa.jjdzr.dto.UserDto;
import com.isa.jjdzr.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public UserDto userEntityToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setUserId(user.getUserId());
        userDto.setUserName(user.getUsername());
        userDto.setUserEmail(user.getUserEmail());
        userDto.setUserPassword(user.getPassword());
        userDto.setUserAdvancementLevel(user.getUserAdvancementLevel());
        return userDto;
    }

    public User userDtoToEntity(UserDto userDto) {
        User user = new User();
        user.setUserId(userDto.getUserId());
        user.setUserName(userDto.getUserName());
        user.setUserEmail(userDto.getUserEmail());
        user.setUserPassword(userDto.getUserPassword());
        user.setUserAdvancementLevel(userDto.getUserAdvancementLevel());
        user.setRoles(userDto.getRoles());
        return user;
    }

    public User updateEntity(User user, UserDto userDto) {
        user.setUserName(userDto.getUserName());
        user.setUserPassword(userDto.getUserPassword());
        user.setUserEmail(userDto.getUserEmail());
        user.setUserAdvancementLevel(userDto.getUserAdvancementLevel());
        user.setRoles(userDto.getRoles());
        return user;
    }

    public List<UserDto> allUsersToDto(List<User> users) {
        return users.stream().map(this::userEntityToDto)
                .collect(Collectors.toList());
    }
}
