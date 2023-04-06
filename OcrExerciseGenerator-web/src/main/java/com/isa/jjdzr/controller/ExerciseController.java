package com.isa.jjdzr.controller;

import com.isa.jjdzr.exercise.model.Exercise;
import com.isa.jjdzr.repository.UserRepository;
import com.isa.jjdzr.service.ExerciseService;
import com.isa.jjdzr.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping
public class ExerciseController {

    private final ExerciseService exerciseService;
    private final UserRepository userRepository;

    //localhost:8080/exercises/all
    @GetMapping("/exercises/all")
    public String getAllExercises(Model model) {
        List<Exercise> exercisesList = exerciseService.findAllExercises();
        model.addAttribute("exercises", exercisesList);
        return "all-exercises";
    }

    //localhost:8080/{id}/exercises/random
    @GetMapping("/{id}/exercises/random")
    public String generateRandomExercises(@PathVariable Long id, Model model) {
        User userFromDb = userRepository.findByUserId(id);
        List<Exercise> randomExercises = exerciseService
                .generateRandomExercises(userFromDb
                        .getUserAdvancementLevel());
        model.addAttribute("randomExercises", randomExercises);
        return "random-exercises";
    }
}

