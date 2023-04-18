package com.isa.jjdzr.rest;

import com.isa.jjdzr.dto.ExerciseDto;
import com.isa.jjdzr.exercise.model.Exercise;
import com.isa.jjdzr.service.ExerciseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/exercise")
public class ExerciseRestController {

    private final ExerciseService exerciseService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity addExercise(@RequestBody ExerciseDto exercise) {
        Optional<Exercise> exerciseFromDb = exerciseService.
                findExerciseByName(exercise.getExerciseName());
        if (exerciseFromDb.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.ok(exerciseService.addExercise(exercise));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ExerciseDto> getAllExercises() {
        return exerciseService.findAllExercises();
    }

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity editExercise(@RequestBody ExerciseDto exercise) {
        ExerciseDto updated = exerciseService.editExercise(exercise);
        return updated != null ?
                ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body(updated) :
                ResponseEntity
                        .badRequest()
                        .build();
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        exerciseService.deleteExercise(id);
        return ResponseEntity.accepted().build();
    }

}
