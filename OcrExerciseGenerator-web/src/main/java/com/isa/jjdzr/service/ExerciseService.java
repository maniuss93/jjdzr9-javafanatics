package com.isa.jjdzr.service;

import com.isa.jjdzr.exercise.model.Exercise;
import com.isa.jjdzr.exercise.service.ExerciseCategory;
import com.isa.jjdzr.repository.ExerciseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;

    public Exercise addExercise(Exercise exercise){
       return exerciseRepository.save(exercise);
    }
    public List<Exercise> addExercises(List<Exercise> exercises){
        return exerciseRepository.saveAll(exercises);
    }
    public List<Exercise> findAllExercises(){
        return exerciseRepository.findAll();
    }
    public Optional<Exercise> findExerciseByName(String exerciseName){
        return exerciseRepository.findByExerciseName(exerciseName);
    }
    public Optional<Exercise> findExerciseByCategory(ExerciseCategory exerciseCategory){
        return exerciseRepository.findByExerciseCategory(exerciseCategory);
    }
    public void deleteExercise(Long id){
        Exercise exercise = exerciseRepository.findByExerciseId(id);
        exerciseRepository.delete(exercise);
    }
}
