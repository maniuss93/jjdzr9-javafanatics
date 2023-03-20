package com.isa.jjdzr.controller;

import com.isa.jjdzr.user.model.User;
import com.isa.jjdzr.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // http://localhost:8080/user/new
    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody User user) {
        userService.createUser(user);
    }

    // http://localhost:8080/user/all
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<User> list() {
       return userService.getAllUsers();
    }
}
