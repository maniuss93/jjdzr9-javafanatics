package com.isa.jjdzr.rest;

import com.isa.jjdzr.exercise.model.Exercise;
import com.isa.jjdzr.service.ExerciseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/exercise")
public class ExerciseRestController {

    private final ExerciseService exerciseService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity addExercise(@RequestBody Exercise exercise) {
        Optional<Exercise> exerciseFromDb = exerciseService.
                findExerciseByName(exercise.getExerciseName());
        if (exerciseFromDb.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.ok(exerciseService.addExercise(exercise));
    }

}
