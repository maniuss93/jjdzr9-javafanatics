package com.isa.jjdzr.controller;

import com.isa.jjdzr.dictionary.AdvancementLevelCategory;
import com.isa.jjdzr.dto.UserDto;
import com.isa.jjdzr.model.User;
import com.isa.jjdzr.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {
    private static final String userEditDetails = "user-edit-details";
    private static final String userCreateForm = "user-create-form";
    private static final String userEditPassword = "user-edit-password";
    private static final String successMessage = "successMessage";
    private static final String redirectHome = "redirect:/";
    private static final String redirectUser = "redirect:/user/";
    private static final String userPanel = "/userpanel/";

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/new")
    public String getCreateUserForm(Model model) {
        model.addAttribute("user", new User());
        return userCreateForm;
    }

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
            user.setUserPassword(passwordEncoder.encode(userPassword));
            model.addAttribute("user", userService.createUser(user));
            redirectAttributes.addAttribute(successMessage, "Użytkownik został dodany pomyślnie!");
            return redirectHome;
        }
    }

    @GetMapping("/login")
    public String getLoginForm(Model model) {
        model.addAttribute("user", new User());
        return "user-login-form";
    }

    @PostMapping(value = "/login")
    public String userLogin(@ModelAttribute("user") UserDto user, Model model) {
        Optional<User> userByName = userService.findByUserName(user.getUserName());
        if (userByName.isEmpty() || !(userByName.get().getPassword()).equals(user.getUserPassword())) {
            model.addAttribute("incorrectDetails", "Dane nie są poprawne");
            return "user-login-form";
        }
        Long id = userByName.get().getUserId();
        return redirectUser + id + userPanel;
    }
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return redirectHome;
    }

    @GetMapping("/{id}/userpanel")
    public String showUserProfile(@PathVariable Long id, Authentication authentication, Model model) {
        userService.isUserAuthorized(id, authentication);
        Optional<User> user = userService.findByUserId(id);
        model.addAttribute("user", user.orElseThrow());
        return "user-panel";
    }

    @GetMapping("/{id}/editdetails")
    public String getUserProfileEditForm(@PathVariable Long id, Authentication authentication, Model model) {
        userService.isUserAuthorized(id, authentication);
        Optional<User> user = userService.findByUserId(id);
        model.addAttribute("user", user.orElseThrow());
        return userEditDetails;
    }

    @PostMapping(value = "/{id}/editdetails")
    public String userProfileEdit(@Valid @ModelAttribute("user") UserDto user,
                                  @PathVariable Long id,
                                  Model model,
                                  RedirectAttributes redirectAttributes) {
        List<UserDto> allUsers = userService.findAllUsers();
        if (allUsers.stream().anyMatch(u -> !u.getUserId().equals(id) && u.getUserName().equals(user.getUserName()))) {
            model.addAttribute("usernameAlreadyTaken", "Ta nazwa użytkownika jest zajęta");
            return userEditDetails;
        }
        if (allUsers.stream().anyMatch(u -> !u.getUserId().equals(id) && u.getUserEmail().equals(user.getUserEmail()))) {
            model.addAttribute("userEmailAlreadyTaken", "Ten adres email jest zajęty");
            return userEditDetails;
        }
        userService.editUser(user);
        redirectAttributes.addAttribute(successMessage, "Aktualizacja użytkownika przebiegła pomyślnie!");
        return redirectUser + id + userPanel;
    }


    @GetMapping("/{id}/editpassword")
    public String getUserPasswordEditForm(@PathVariable Long id, Authentication authentication, Model model) {
        userService.isUserAuthorized(id, authentication);
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
        if (userFromDb.isPresent() && !BCrypt.checkpw(userCurrentPassword, userFromDb.get().getPassword())) {
            model.addAttribute("wrongPassword", "Twoje aktualne hasło jest nie poprawne");
            return userEditPassword;
        } else if (!userPassword.equals(confirmPassword)) {
            model.addAttribute("userPasswordsDoNotMatch", "Hasła muszą być identyczne");
            return userEditPassword;
        } else {
            user.setUserPassword(passwordEncoder.encode(userPassword));
            userService.editUser(user);
        }
        redirectAttributes.addAttribute(successMessage, "Aktualizacja hasła użytkownika przebiegła pomyślnie!");
        return redirectUser + id + userPanel;
    }

    @GetMapping("/{id}/delete")
    public String deleteUser(@PathVariable Long id, Authentication authentication) {
        userService.isUserAuthorized(id, authentication);
        userService.deleteUser(id);
        return "redirect:/?successMessage=Uzytkownik+zostal+usuniety+pomyslnie";
    }

    @GetMapping("/{id}/confirmdelete")
    public String confirmDeleteUser(@PathVariable Long id, Authentication authentication) {
        userService.isUserAuthorized(id, authentication);
        return "user-confirm-delete";
    }

    @ModelAttribute("availableUserAdvancementLevel")
    List<AdvancementLevelCategory> getAdvancementLevel() {
        return Arrays.asList(AdvancementLevelCategory.values());
    }
}
