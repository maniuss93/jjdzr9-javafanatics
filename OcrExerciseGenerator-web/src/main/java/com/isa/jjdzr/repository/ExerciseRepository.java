package com.isa.jjdzr.repository;

import com.isa.jjdzr.exercise.model.Exercise;
import com.isa.jjdzr.exercise.service.ExerciseCategory;
import com.isa.jjdzr.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
    Optional<Exercise>  findByExerciseName(String exerciseName);
    Optional<Exercise>  findByExerciseCategory(ExerciseCategory exerciseCategory);
    Exercise findByExerciseId(Long id);
}
