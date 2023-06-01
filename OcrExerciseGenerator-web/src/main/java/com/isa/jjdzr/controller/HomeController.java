package com.isa.jjdzr.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping()
public class HomeController {

    // http://localhost:8080

    @GetMapping
    public String getHome(@RequestParam(value = "logout", required = false) String logout, Model model) {
        if ("true".equals(logout)) {
            model.addAttribute("message", "Wylogowano pomyślnie");
        }
        return "index"; // zwracamy nazwę widoku
    }

    // http://localhost:8080/info
    @GetMapping("/info")
    public String getInfo() {
        return "info";
    }

    // http://localhost:8080/info
    @GetMapping("/calendar")
    public String getCalendar() {
        return "calendar";
    }

    // http://localhost:8080/demo
    @GetMapping("/demo")
    public String getDemo() {
        return "demo-exercises";
    }

    // http://localhost:8080/userpanel/{id}
    @GetMapping("/userpanel/{id}")
    public String getUserPanel(@PathVariable Long id) {
        return "user-panel";
    }

    @GetMapping("/user/{id}/confirmdelete")
    public String confirmDeleteUser(@PathVariable Long id) {
        return "user-confirm-delete";
    }

}
