package com.isa.jjdzr.controller;

import com.isa.jjdzr.service.UserService;
import com.isa.jjdzr.user.model.User;
import com.isa.jjdzr.user.service.AdvancementLevelCategory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/new")
    public String getNewUserForm(Model model) {
        model.addAttribute("user", new User());
        return "user-form";
    }

    @PostMapping(value = "/new")
    public String createUser(@ModelAttribute("user") User user, Model model) {
        Optional<User> userByName = userService.findByUserName(user.getUserName());
        Optional<User> userByEmail = userService.findByUserEmail(user.getUserEmail());

        if (userByName.isPresent()) {
            model.addAttribute("usernameAlreadyTaken", "Ta nazwa użytkownika jest zajęta");
            return "user-form";
        } else if (userByEmail.isPresent()) {
            model.addAttribute("userEmailAlreadyTaken", "Ten address email jest zajęty");
            return "user-form";
        } else {
            model.addAttribute("user", userService.createUser(user));
            return "redirect:/";
        }
    }

    @ModelAttribute("availableUserAdvancementLevel")
    List<AdvancementLevelCategory> getAdvancementLevel() {
        return Arrays.asList(AdvancementLevelCategory.values());
    }
}
