package com.isa.jjdzr.controller;

import com.isa.jjdzr.service.UserService;
import com.isa.jjdzr.user.model.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    @GetMapping("/new")
    public String getNewUserForm(Model model){
        model.addAttribute("user", new User());
        return "user-form";
    }

    @PostMapping(value = "/new")
    public String createUser(@Valid @ModelAttribute ("user") User user, Model model){
        model.addAttribute(userService.createUser(user));
        return "user";
    }

}
