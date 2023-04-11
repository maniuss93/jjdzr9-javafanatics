package com.isa.jjdzr.repository;

import com.isa.jjdzr.exercise.model.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
    boolean existsByExerciseName(String exerciseName);

    boolean existsByUrl(String url);

    Optional<Exercise> findByExerciseName(String exerciseName);

    Exercise findByExerciseId(Long id);
}
