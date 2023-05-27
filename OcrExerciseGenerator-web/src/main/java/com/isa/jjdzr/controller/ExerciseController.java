package com.isa.jjdzr.controller;

import com.isa.jjdzr.dto.ExerciseDto;
import com.isa.jjdzr.exercise.model.Exercise;
import com.isa.jjdzr.exercise.service.ExerciseCategory;
import com.isa.jjdzr.repository.UserRepository;
import com.isa.jjdzr.service.ExerciseService;
import com.isa.jjdzr.user.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping()
public class ExerciseController {

    private final ExerciseService exerciseService;
    private final UserRepository userRepository;

    //localhost:8080/exercises/not-approved

    @GetMapping("/{id}/exercises/not-approved")
    @PreAuthorize("hasRole('ADMIN')")

    public String getApprovedExercises(@PathVariable Long id, Model model) {
        List<Exercise> exercisesListNotApproved = exerciseService.getNotApprovedExercises();
        model.addAttribute("exercises", exercisesListNotApproved);
        return "not-approved-exercises";
    }
    //localhost:8080/exercises/approved

    @GetMapping("/{id}/exercises/approved")
    public String getNoApprovedExercises(@PathVariable Long id, Model model) {
        List<Exercise> exercisesListApproved = exerciseService.getApprovedExercises();
        model.addAttribute("exercises", exercisesListApproved);
        return "all-exercises";
    }

    //localhost:8080/{id}/exercises/random
    @GetMapping("/{id}/exercises/random")
    public String generateRandomExercises(@PathVariable Long id, Model model) {
        User userFromDb = userRepository.findByUserId(id);
        List<Exercise> randomExercises = exerciseService.generateRandomExercises(userFromDb.getUserAdvancementLevel());
        model.addAttribute("randomExercises", randomExercises);
        model.addAttribute("userId", id);
        return "random-exercises";
    }
    @GetMapping("/{id}/exercises/all")
    public String getAllExercises(@PathVariable Long id, Model model) {
        List<ExerciseDto> exercisesList = exerciseService.findAllExercises();
        model.addAttribute("exercises", exercisesList);
        return "all-exercises";
    }

    @GetMapping("/generate-pdf")
    public ResponseEntity<byte[]> generatePdf(@RequestParam List<Long> exerciseIds) throws Exception {
        List<Exercise> exercises = exerciseService.getExercisesByIds(exerciseIds);
        return exerciseService.generatePdf(exercises);
    }

    @GetMapping("/{id}/exercise/add")
    public String getAddExerciseForm(@PathVariable Long id, Model model) {
        model.addAttribute("exercise", new Exercise());
        return "add-exercise-form";
    }


    @PostMapping(value = "/{id}/exercise/add")
    public String createExercise(@PathVariable Long id, @ModelAttribute("exercise") ExerciseDto exercise, Model model) {
        if (exerciseService.existsByExerciseName(exercise.getExerciseName())) {
            model.addAttribute("exerciseNameAlreadyTaken", "Ćwiczenie z taką nazwą już istnieje");
            return "add-exercise-form";
        } else if (exerciseService.existsByUrl(exercise.getUrl())) {
            model.addAttribute("addressUrlAlreadyTaken", "Ćwiczenie z takim adresem url już istnieje");
            return "add-exercise-form";
        } else {
            model.addAttribute("exercise", exerciseService.addExercise(exercise));
        }
        return "redirect:/user/" + id + "/userpanel";
    }


    @PostMapping("/exercises/accept/{exerciseId}")
    @PreAuthorize("hasRole('ADMIN')")
    public String acceptExercise(@PathVariable Long exerciseId, @RequestParam Long id) {
        exerciseService.acceptExercise(exerciseId);
        return "redirect:/" + id + "/exercises/not-approved";
    }



    @PostMapping("/exercises/delete/{exerciseId}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteExercise(@PathVariable Long exerciseId, @RequestParam Long id) {
        exerciseService.deleteExercise(exerciseId);
        return "redirect:/" + id + "/exercises/not-approved";
    }
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/";
    }


    @ModelAttribute("availableExerciseCategory")
    List<ExerciseCategory> getDescription() {
        return Arrays.asList(ExerciseCategory.values());
    }
}