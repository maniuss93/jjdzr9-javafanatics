package com.isa.jjdzr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping()
public class HomeController {

    // http://localhost:8080
    @GetMapping
    public String getHome() {
        return "index";
    }

    // http://localhost:8080/info
    @GetMapping("/info")
    public String getInfo() {
        return "info";
    }

    // http://localhost:8080/userpanel/{id}
    @GetMapping("/userpanel/{id}")
    public String getUserPanel(@PathVariable Long id) {
        return "user-panel";
    }
}
