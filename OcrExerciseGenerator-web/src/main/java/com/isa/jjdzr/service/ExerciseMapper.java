package com.isa.jjdzr.service;

import com.isa.jjdzr.dto.ExerciseDto;
import com.isa.jjdzr.model.Exercise;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component

public class ExerciseMapper {
    public ExerciseDto exerciseEntityToDto(Exercise exercise) {
        ExerciseDto exerciseDto = new ExerciseDto();
        exerciseDto.setExerciseId(exercise.getExerciseId());
        exerciseDto.setExerciseName(exercise.getExerciseName());
        exerciseDto.setDescription(exercise.getDescription());
        exerciseDto.setExerciseCategory(exercise.getExerciseCategory());
        exerciseDto.setExercisePoints(exercise.getExercisePoints());
        exerciseDto.setUrl(exercise.getUrl());
        return exerciseDto;
    }

    public Exercise exerciseDtoToEntity(ExerciseDto exerciseDto) {
        Exercise exercise = new Exercise();
        exercise.setExerciseId(exerciseDto.getExerciseId());
        exercise.setExerciseName(exerciseDto.getExerciseName());
        exercise.setDescription(exerciseDto.getDescription());
        exercise.setExerciseCategory(exerciseDto.getExerciseCategory());
        exercise.setExercisePoints(exerciseDto.getExercisePoints());
        exercise.setUrl(exerciseDto.getUrl());
        return exercise;
    }

    public Exercise updateEntity(Exercise exercise, ExerciseDto exerciseDto) {
        exercise.setExerciseName(exerciseDto.getExerciseName());
        exercise.setExerciseCategory(exerciseDto.getExerciseCategory());
        exercise.setExercisePoints(exerciseDto.getExercisePoints());
        exercise.setDescription(exerciseDto.getDescription());
        exercise.setUrl(exerciseDto.getUrl());
        return exercise;
    }

    public List<ExerciseDto> allExercisesToDto(List<Exercise> allExercises) {
        return allExercises.stream().map(this::exerciseEntityToDto)
                .collect(Collectors.toList());
    }
}
