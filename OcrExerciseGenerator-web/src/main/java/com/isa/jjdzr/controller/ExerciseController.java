package com.isa.jjdzr.controller;

import com.isa.jjdzr.dictionary.ExerciseCategory;
import com.isa.jjdzr.dto.ExerciseDto;
import com.isa.jjdzr.model.Exercise;
import com.isa.jjdzr.model.User;
import com.isa.jjdzr.repository.UserRepository;
import com.isa.jjdzr.service.ExerciseService;
import com.isa.jjdzr.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping()
public class ExerciseController {

    private static final String redirectUser = "redirect:/user/";
    private static final String userPanel = "/userpanel";

    private final ExerciseService exerciseService;
    private final UserRepository userRepository;
    private final UserService userService;

    private static final String exercise = "exercise";
    private static final String exerciseForm = "add-exercise-form";


    @GetMapping("/exercises/not-approved")
    public String getNotApprovedExercises(Model model) {
        List<Exercise> exercisesListNotApproved = exerciseService.getNotApprovedExercises();
        model.addAttribute(exercise, exercisesListNotApproved);
        return "not-approved-exercises";
    }


    @GetMapping("/{id}/exercises/approved")
    public String getApprovedExercises(@PathVariable Long id, Authentication authentication, Model model) {
        userService.isUserAuthorized(id, authentication);
        List<Exercise> exercisesListApproved = exerciseService.getApprovedExercises();
        model.addAttribute(exercise, exercisesListApproved);
        return "all-exercises";
    }


    @GetMapping("/{id}/exercises/random")
    public String generateRandomExercises(@PathVariable Long id, Authentication authentication, Model model) {
        userService.isUserAuthorized(id, authentication);
        Optional<User> userFromDb = userRepository.findByUserId(id);
        List<Exercise> randomExercises = exerciseService.generateRandomExercises(userFromDb.orElseThrow().getUserAdvancementLevel());
        model.addAttribute("randomExercises", randomExercises);
        model.addAttribute("userId", id);
        return "random-exercises";
    }

    @GetMapping("/{id}/exercises/all")
    public String getAllExercises(@PathVariable Long id, Authentication authentication, Model model) {
        userService.isUserAuthorized(id, authentication);
        List<ExerciseDto> exercisesList = exerciseService.findAllExercises();
        model.addAttribute(exercise, exercisesList);
        return "all-exercises";
    }

    @GetMapping("/exercises/all")
    public String getAllExercises(Model model) {
        List<ExerciseDto> exercisesList = exerciseService.findAllExercises();
        model.addAttribute(exercise, exercisesList);
        return "admin-all-exercises";
    }


    @GetMapping("/generate-pdf")
    public ResponseEntity<byte[]> generatePdf(@RequestParam List<Long> exerciseIds) throws Exception {
        List<Exercise> exercises = exerciseService.getExercisesByIds(exerciseIds);
        return exerciseService.generatePdf(exercises);
    }


    @GetMapping("/{id}/exercise/add")
    public String getAddExerciseForm(@PathVariable Long id, Authentication authentication, Model model) {
        userService.isUserAuthorized(id, authentication);
        model.addAttribute(exercise, new Exercise());
        return exerciseForm;
    }


    @PostMapping(value = "/{id}/exercise/add")
    public String createExercise(@PathVariable Long id, @ModelAttribute("exercise") ExerciseDto exercise, Model model, RedirectAttributes redirectAttributes) {
        if (exerciseService.existsByExerciseName(exercise.getExerciseName())) {
            model.addAttribute("exerciseNameAlreadyTaken", "Ćwiczenie z taką nazwą już istnieje");
            return exerciseForm;
        } else if (exerciseService.existsByUrl(exercise.getUrl())) {
            model.addAttribute("addressUrlAlreadyTaken", "Ćwiczenie z takim adresem url już istnieje");
            return exerciseForm;
        } else {
            model.addAttribute("exercise", exerciseService.addExercise(exercise));
            redirectAttributes.addAttribute("successMessage", "Ćwiczenie zostało dodane pomyślnie!");

        }
        return redirectUser + id + userPanel;
    }


    @PostMapping("/exercises/accept/{exerciseId}")
    public String acceptExercise(@PathVariable Long exerciseId) {
        exerciseService.acceptExercise(exerciseId);
        return "redirect:/exercises/not-approved";
    }


    @PostMapping("/exercises/delete/{exerciseId}")
    public String deleteExercise(@PathVariable Long exerciseId) {
        exerciseService.deleteExercise(exerciseId);
        return "redirect:/exercises/not-approved";
    }

    @ModelAttribute("availableExerciseCategory")
    List<ExerciseCategory> getDescription() {
        return Arrays.asList(ExerciseCategory.values());
    }
}