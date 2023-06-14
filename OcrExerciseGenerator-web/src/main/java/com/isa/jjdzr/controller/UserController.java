package com.isa.jjdzr.controller;

import com.isa.jjdzr.dto.UserDto;
import com.isa.jjdzr.service.UserService;
import com.isa.jjdzr.user.model.User;
import com.isa.jjdzr.user.service.AdvancementLevelCategory;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("user")
public class UserController {
    private static final String userEditDetails = "user-edit-details";
    private static final String userCreateForm = "user-create-form";
    private static final String userEditPassword = "user-edit-password";

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //localhost:8080/user/new
    @GetMapping("/new")
    public String getCreateUserForm(Model model) {
        model.addAttribute("user", new User());
        return userCreateForm;
    }

    //localhost:8080/user/new
    @PostMapping(value = "/new")
    public String createUser(@ModelAttribute("user") UserDto user,
                             @RequestParam("userPassword") String userPassword,
                             @RequestParam("confirmPassword") String confirmPassword,
                             Model model, RedirectAttributes redirectAttributes) {
        if (userService.existsByName(user.getUserName())) {
            model.addAttribute("usernameAlreadyTaken", "Ta nazwa użytkownika jest zajęta");
            return userCreateForm;
        } else if (userService.existsByEmail(user.getUserEmail())) {
            model.addAttribute("userEmailAlreadyTaken", "Ten address email jest zajęty");
            return userCreateForm;
        } else if (!userPassword.equals(confirmPassword)) {
            model.addAttribute("userPasswordsDoNotMatch", "Hasła muszą być identyczne");
            return userCreateForm;
        } else {
            model.addAttribute("user", userService.createUser(user));
            redirectAttributes.addAttribute("successMessage", "Użytkownik został dodany pomyślnie!");
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
    public String userLogin(@ModelAttribute("user") UserDto user, Model model) {
        Optional<User> userByName = userService.findByUserName(user.getUserName());
        if (userByName.isEmpty() || !(userByName.get().getUserPassword()).equals(user.getUserPassword())) {
            model.addAttribute("incorrectDetails", "Dane nie są poprawne");
            return "user-login-form";
        }
        Long id = userByName.get().getUserId();
        return "redirect:/user/" + id + "/userpanel";
    }

    @GetMapping("/{id}/userpanel")
    public String showUserProfile(@PathVariable Long id, Model model) {
        Optional<User> user = userService.findByUserId(id);
        model.addAttribute("user", user.orElseThrow());
        return "user-panel";
    }

    @GetMapping("/{id}/editdetails")
    public String getUserProfileEditForm(@PathVariable Long id, Model model) {
        Optional<User> user = userService.findByUserId(id);
        model.addAttribute("user", user.orElseThrow());
        return userEditDetails;
    }

    @PostMapping(value = "/{id}/edit")
    public String userProfileEdit(@Valid @ModelAttribute("user") UserDto user,
                                  @PathVariable Long id,
                                  Model model, RedirectAttributes redirectAttributes) {
        UserDto userFromDb = userService.getUserDtoById(id);
        List<UserDto> allUsers = userService.findAllUsers();
        allUsers.remove(userFromDb);
        if (allUsers.stream().map(UserDto::getUserName).toList().contains(user.getUserName())) {
            model.addAttribute("usernameAlreadyTaken", "Ta nazwa użytkownika jest zajęta");
            return userEditDetails;
        } else if (allUsers.stream().map(UserDto::getUserEmail).toList().contains(user.getUserEmail())) {
            model.addAttribute("userEmailAlreadyTaken", "Ten address email jest zajęty");
            return userEditDetails;
        } else {
            userService.editUser(user);
        }
        redirectAttributes.addAttribute("successMessage", "Aktualizacja użytkownika przebiegła pomyślnie!");
        return "redirect:/user/" + id + "/userpanel";
    }

    @GetMapping("/{id}/editpassword")
    public String getUserPasswordEditForm(@PathVariable Long id, Model model) {
        Optional<User> user = userService.findByUserId(id);
        model.addAttribute("user", user.orElseThrow());
        return userEditPassword;
    }

    @PostMapping(value = "/{id}/editpassword")
    public String userPasswordEdit(@Valid @ModelAttribute("user") UserDto user,
                                   @PathVariable Long id,
                                   @RequestParam("userCurrentPassword") String userCurrentPassword,
                                   @RequestParam("userPassword") String userPassword,
                                   @RequestParam("confirmPassword") String confirmPassword,
                                   Model model, RedirectAttributes redirectAttributes) {
        Optional<User> userFromDb = userService.findByUserId(id);
        if (userFromDb.isPresent() && !userCurrentPassword.equals(userFromDb.get().getUserPassword())) {
            model.addAttribute("wrongPassword", "Twoje aktualne hasło jest nie poprawne");
            return userEditPassword;
        } else if (!userPassword.equals(confirmPassword)) {
            model.addAttribute("userPasswordsDoNotMatch", "Hasła muszą być identyczne");
            return userEditPassword;
        } else {
            userService.editUser(user);
        }
        redirectAttributes.addAttribute("successMessage", "Aktualizacja hasła użytkownika przebiegła pomyślnie!");
        return "redirect:/user/" + id + "/userpanel";
    }

    @GetMapping("/{id}/delete")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/?successMessage=Uzytkownik+zostal+usuniety+pomyslnie";
    }

    @ModelAttribute("availableUserAdvancementLevel")
    List<AdvancementLevelCategory> getAdvancementLevel() {
        return Arrays.asList(AdvancementLevelCategory.values());
    }
}
