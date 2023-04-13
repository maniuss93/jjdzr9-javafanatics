package com.isa.jjdzr.controller;

import com.isa.jjdzr.service.ExerciseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping()
@RequiredArgsConstructor
public class HomeController {

    private final ExerciseService exerciseService;


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
    @GetMapping("/demo")
    public String getDemo() {
        return "demo-exercises";
    }

    // http://localhost:8080/userpanel/{id}
    @GetMapping("/userpanel/{id}")
    public String getUserPanel(@PathVariable Long id) {
        return "user-panel";
    }


}
