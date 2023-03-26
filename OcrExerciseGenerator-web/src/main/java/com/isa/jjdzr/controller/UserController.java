package com.isa.jjdzr.controller;

import com.isa.jjdzr.service.UserService;
import com.isa.jjdzr.user.model.User;
import com.isa.jjdzr.user.service.AdvancementLevelCategory;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/new")
    public String getNewUserForm(Model model){
        model.addAttribute("user", new User());
        return "user-form";
    }

    @PostMapping(value = "/new")
    public String createUser(@Valid @ModelAttribute ("user") User user, Model model){
        model.addAttribute("user", userService.createUser(user));
        return "home";
    }

    @ModelAttribute("availableUserAdvancementLevel")
    List<AdvancementLevelCategory> getAdvancementLevel(){
        return Arrays.asList(AdvancementLevelCategory.values());
    }
}
