package com.isa.jjdzr.service;

import com.isa.jjdzr.exercise.model.Exercise;
import com.isa.jjdzr.repository.ExerciseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;

    public Exercise addExercise(Exercise exercise) {
        return exerciseRepository.save(exercise);
    }

    public List<Exercise> findAllExercises() {
        return exerciseRepository.findAll();
    }

    public Optional<Exercise> findExerciseByName(String exerciseName) {
        return exerciseRepository.findByExerciseName(exerciseName);
    }

    public Exercise editExercise(Exercise exercise) {
        Optional<Exercise> exerciseById = exerciseRepository.findById(exercise.getExerciseId());
        if (exerciseById.isPresent()) {
            Exercise exerciseFromDb = exerciseById.get();
            exerciseFromDb.setExerciseName(exercise.getExerciseName());
            exerciseFromDb.setExerciseCategory(exercise.getExerciseCategory());
            exerciseFromDb.setExercisePoints(exercise.getExercisePoints());
            exerciseFromDb.setDescription(exercise.getDescription());
            exerciseFromDb.setUrl(exercise.getUrl());
            exerciseFromDb.setExerciseId(exercise.getExerciseId());
            return exerciseFromDb;
        }
        return null;
    }

    public void deleteExercise(Long id) {
        Exercise exercise = exerciseRepository.findByExerciseId(id);
        exerciseRepository.delete(exercise);
    }
}
