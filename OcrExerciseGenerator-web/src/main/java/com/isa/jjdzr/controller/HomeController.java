package com.isa.jjdzr.controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping()
public class HomeController {


    @GetMapping
    public String getHome(@RequestParam(value = "logout", required = false) String logout, Model model) {
        if ("true".equals(logout)) {
            model.addAttribute("message", "Wylogowano pomyślnie");
        }
        return "index";
    }

    @GetMapping("/access-denied")
    public String getAccessDenied() {
        return "access-denied";
    }

    @GetMapping("/calendar")
    public String getCalendar() {
        return "calendar";
    }

    @GetMapping("/admin")
    public String getAdmin() {
        return "admin-panel";
    }



}
