package com.isa.jjdzr.controller;

import com.isa.jjdzr.service.UserService;
import com.isa.jjdzr.user.model.User;
import com.isa.jjdzr.user.service.AdvancementLevelCategory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        return "user-create-form";
    }

    //localhost:8080/user/new
    @PostMapping(value = "/new")
    public String createUser(@ModelAttribute("user") User user, Model model) {
        if (userService.existsByName(user.getUserName())) {
            model.addAttribute("usernameAlreadyTaken", "Ta nazwa użytkownika jest zajęta");
            return "user-create-form";
        } else if (userService.existsByEmail(user.getUserEmail())) {
            model.addAttribute("userEmailAlreadyTaken", "Ten address email jest zajęty");
            return "user-create-form";
        } else {
            model.addAttribute("user", userService.createUser(user));
            return "redirect:/";
        }
    }

    //localhost:8080/user/login
    @GetMapping("/login")
    public String getLoginForm(Model model) {
        model.addAttribute("user", new User());
        return "user-login-form";
    }

    //localhost:8080/user/login
    @PostMapping(value = "/login")
    public String userLogin(@ModelAttribute("user") User user, Model model) {
        Optional<User> userByName = userService.findByUserName(user.getUserName());
        if (userByName.isEmpty() || !(userByName.get().getUserPassword()).equals(user.getUserPassword())) {
            model.addAttribute("incorrectDetails", "Dane nie są poprawne");
            return "user-login-form";
        }
        Long id = userByName.get().getUserId();
        return "redirect:/user/" + id + "/userpanel";
    }
    @GetMapping("/{id}/edit")
    public String getUserProfileEditForm(@PathVariable Long id, Model model){
        User user = userService.findByUserId(id);
        model.addAttribute("user", user);
        return "user-profile";
    }

    @PutMapping(value = "/{id}/edit")
    public String userProfileEdit(@ModelAttribute ("user") User user, @PathVariable Long id) {
            userService.editUser(user);
            return "redirect:/user/" + id + "/userpanel";
    }

    @GetMapping("/{id}/userpanel")
    public String showUserProfile(@PathVariable Long id, Model model){
       User user = userService.findByUserId(id);
       model.addAttribute("user", user);
       return "user-panel";
    }

    @ModelAttribute("availableUserAdvancementLevel")
    List<AdvancementLevelCategory> getAdvancementLevel() {
        return Arrays.asList(AdvancementLevelCategory.values());
    }
}
