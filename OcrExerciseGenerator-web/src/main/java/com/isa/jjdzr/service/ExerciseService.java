package com.isa.jjdzr.service;

import com.isa.jjdzr.exercise.model.Exercise;
import com.isa.jjdzr.exercise.service.PdfExerciseGenerator;
import com.isa.jjdzr.exercise.service.RandomExerciseGenerator;
import com.isa.jjdzr.repository.ExerciseRepository;
import com.isa.jjdzr.user.service.AdvancementLevelCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;

    private final PdfExerciseGenerator pdfExerciseGenerator;
    public ResponseEntity<byte[]> generatePdf(List<Optional<Exercise>> exercises) throws Exception {
        return pdfExerciseGenerator.generatePdf(exercises);
    }
    private final RandomExerciseGenerator randomExerciseGenerator;

    public Exercise addExercise(Exercise exercise) {
        return exerciseRepository.save(exercise);
    }

    public List<Exercise> findAllExercises() {
        return exerciseRepository.findAll();
    }

    public Optional<Exercise> findExerciseByName(String exerciseName) {
        return exerciseRepository.findByExerciseName(exerciseName);
    }

    public boolean existsByExerciseName(String exerciseName){
        return exerciseRepository.existsByExerciseName(exerciseName);
    }

    public boolean existsByUrl(String url){
        return exerciseRepository.existsByUrl(url);
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
    public List<Exercise> generateRandomExercises(AdvancementLevelCategory userAdvancementLevel){
        return randomExerciseGenerator.generateRandomExercises(randomExerciseGenerator
                .convertAdvancementLevel(userAdvancementLevel));
    }
    public List<Optional<Exercise>> getExercisesByIds(List<Long> exerciseIds) {
        List<Optional<Exercise>> exercises = new ArrayList<>();
        for (Long id : exerciseIds) {
            Optional<Exercise> exercise = exerciseRepository.findById(id);
            exercises.add(exercise);
        }
        return exercises;
    }
}
