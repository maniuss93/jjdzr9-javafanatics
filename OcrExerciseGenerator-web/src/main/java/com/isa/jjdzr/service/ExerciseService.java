package com.isa.jjdzr.service;

import com.isa.jjdzr.dto.ExerciseDto;
import com.isa.jjdzr.exercise.model.Exercise;
import com.isa.jjdzr.exercise.service.PdfExerciseGenerator;
import com.isa.jjdzr.exercise.service.RandomExerciseGenerator;
import com.isa.jjdzr.repository.ExerciseRepository;
import com.isa.jjdzr.user.service.AdvancementLevelCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;
    private final ExerciseMapper exerciseMapper;
    private final PdfExerciseGenerator pdfExerciseGenerator;
    private final RandomExerciseGenerator randomExerciseGenerator;


    public ResponseEntity<byte[]> generatePdf(List<Exercise> exercises) throws Exception {
        return pdfExerciseGenerator.generatePdf(exercises);
    }

    public ExerciseDto addExercise(ExerciseDto exerciseDto) {
        Exercise exercise = exerciseMapper.exerciseDtoToEntity(exerciseDto);
        Exercise saved = exerciseRepository.save(exercise);
        return exerciseMapper.exerciseEntityToDto(saved);
    }

    public List<ExerciseDto> findAllExercises() {
        List<Exercise> findAll = exerciseRepository.findAll();
        return exerciseMapper.allExercisesToDto(findAll);
    }

    public Optional<Exercise> findExerciseByName(String exerciseName) {
        return exerciseRepository.findByExerciseName(exerciseName);
    }

    public boolean existsByExerciseName(String exerciseName) {
        return exerciseRepository.existsByExerciseName(exerciseName);
    }

    public boolean existsByUrl(String url) {
        return exerciseRepository.existsByUrl(url);
    }

    public ExerciseDto editExercise(ExerciseDto exercise) {
        Optional<Exercise> exerciseById = exerciseRepository.findById(exercise.getExerciseId());
        if (exerciseById.isPresent()) {
            Exercise exerciseFromDb = exerciseById.get();
            Exercise updated = exerciseMapper.updateEntity(exerciseFromDb, exercise);
            exerciseRepository.save(updated);
            return exerciseMapper.exerciseEntityToDto(updated);
        }
        return null;
    }

    public void deleteExercise(Long id) {
        Exercise exercise = exerciseRepository.findByExerciseId(id);
        exerciseRepository.delete(exercise);
    }

    public List<Exercise> generateRandomExercises(AdvancementLevelCategory userAdvancementLevel) {
        return randomExerciseGenerator.generateRandomExercises(randomExerciseGenerator
                .convertAdvancementLevel(userAdvancementLevel), exerciseRepository.findAll());
    }

    public List<Exercise> getExercisesByIds(List<Long> ids) {
        return exerciseRepository.findAllById(ids);
    }


}
