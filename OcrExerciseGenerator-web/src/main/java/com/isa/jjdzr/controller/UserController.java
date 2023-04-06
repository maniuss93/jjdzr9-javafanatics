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

    //localhost:8080/user/new
    @GetMapping("/new")
    public String getCreateUserForm(Model model) {
        model.addAttribute("user", new User());
        return "registration-form";
    }

    //localhost:8080/user/new
    @PostMapping(value = "/new")
    public String createUser(@ModelAttribute("user") User user, Model model) {
        if (userService.existyByName(user.getUserName())) {
            model.addAttribute("usernameAlreadyTaken", "Ta nazwa użytkownika jest zajęta");
            return "registration-form";
        } else if (userService.existsByEmail(user.getUserEmail())) {
            model.addAttribute("userEmailAlreadyTaken", "Ten address email jest zajęty");
            return "registration-form";
        } else {
            model.addAttribute("user", userService.createUser(user));
            return "redirect:/";
        }
    }

    //localhost:8080/user/login
    @GetMapping("/login")
    public String getLoginForm(Model model) {
        model.addAttribute("user", new User());
        return "user-login";
    }

    //localhost:8080/user/login
    @PostMapping(value = "/login")
    public String userLogin(@ModelAttribute("user") User user, Model model) {
        Optional<User> userByName = userService.findByUserName(user.getUserName());
        if (userByName.isEmpty() || !(userByName.get().getUserPassword()).equals(user.getUserPassword())) {
            model.addAttribute("incorrectDetails", "Dane nie są poprawne");
            return "user-login";
        }
        Long id = userByName.get().getUserId();
        return "redirect:/userpanel/" + id;
    }

    @ModelAttribute("availableUserAdvancementLevel")
    List<AdvancementLevelCategory> getAdvancementLevel() {
        return Arrays.asList(AdvancementLevelCategory.values());
    }
}
