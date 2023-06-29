package com.isa.jjdzr.service;

import com.isa.jjdzr.controller.ExerciseController;
import com.isa.jjdzr.dto.ExerciseDto;
import com.isa.jjdzr.model.Exercise;
import com.isa.jjdzr.repository.ExerciseRepository;
import com.isa.jjdzr.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ExerciseServiceTest {
    @Mock
    private PdfExerciseGenerator pdfExerciseGenerator;
    @Mock
    private ExerciseRepository exerciseRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private ExerciseMapper exerciseMapper;
    @InjectMocks
    private ExerciseService exerciseService;

    private ExerciseController exerciseController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        exerciseController = new ExerciseController(exerciseService, userRepository);
    }

    @Test
    void testGeneratePdf() throws Exception {
        // given
        List<Exercise> mockExercises = Arrays.asList(new Exercise("Exercise 1", 50), new Exercise("Exercise 2", 100), new Exercise("Exercise 3", 150));

        mockExercises.forEach(exercise -> exercise.setExerciseId((long) mockExercises.indexOf(exercise) + 1));

        List<Long> mockExercisesIds = Arrays.asList(1L, 2L, 3L);

        byte[] mockPdf = new byte[]{0x25, 0x50, 0x44, 0x46};
        when(pdfExerciseGenerator.generatePdf(mockExercises)).thenReturn(ResponseEntity.ok().body(mockPdf));
        Mockito.when(exerciseRepository.findAllById(Arrays.asList(1L, 2L, 3L))).thenReturn(mockExercises);
        // when
        ResponseEntity<byte[]> response = exerciseController.generatePdf(mockExercisesIds);
        // then
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertArrayEquals(mockPdf, response.getBody());

    }


    @Test
    void testAddExercise() {
        // given
        ExerciseDto exerciseDto = new ExerciseDto();
        exerciseDto.setExerciseName("Test Exercise");
        exerciseDto.setDescription("This is a test exercise");
        exerciseDto.setExerciseId(1L);

        Exercise exercise = new Exercise();
        exercise.setExerciseName("Test Exercise2");
        exercise.setDescription("This is a test exercise2");
        exercise.setExerciseId(1L);

        Exercise saved = new Exercise();
        saved.setExerciseName("Test Exercise3");
        saved.setDescription("This is a test exercise3");
        saved.setExerciseId(1L);


        when(exerciseMapper.exerciseDtoToEntity(any(ExerciseDto.class))).thenReturn(exercise);
        when(exerciseRepository.save(any(Exercise.class))).thenReturn(saved);
        when(exerciseMapper.exerciseEntityToDto(any(Exercise.class))).thenReturn(exerciseDto);
        // when

        ExerciseDto result = exerciseService.addExercise(exerciseDto);

        // then
        assertNotNull(result);
        assertEquals(1L, result.getExerciseId());
        assertEquals("Test Exercise", result.getExerciseName());
        assertEquals("This is a test exercise", result.getDescription());
    }


    @Test
    void testFindAllExercises() {
        // given
        List<Exercise> mockExercises = Arrays.asList(
                new Exercise("Exercise 1", 50),
                new Exercise("Exercise 2", 100),
                new Exercise("Exercise 3", 150)
        );

        mockExercises.forEach(exercise -> exercise.setExerciseId((long) mockExercises.indexOf(exercise) + 1));

        when(exerciseRepository.findAll()).thenReturn(mockExercises);
        when(exerciseMapper.exerciseEntityToDto(any(Exercise.class))).thenReturn(new ExerciseDto());
        // when
        List<Exercise> result = exerciseRepository.findAll();
        // then
        assertNotNull(result);
        assertEquals(3, result.size());

    }


    @Test
    void testFindExerciseByName() {
        // given
        Exercise exercise = new Exercise("Exercise 1", 50);
        when(exerciseRepository.findByExerciseName("Exercise 1")).thenReturn(Optional.of(exercise));
        when(exerciseMapper.exerciseEntityToDto(any(Exercise.class))).thenReturn(new ExerciseDto());

        // when
        Optional<Exercise> result = exerciseService.findExerciseByName("Exercise 1");

        // then
        assertTrue(result.isPresent());
        assertEquals("Exercise 1", result.get().getExerciseName());
        assertEquals(50, result.get().getExercisePoints());
    }


    @Test
    void testExistsByExerciseName() {
        // given
        String exerciseName = "Exercise 1";
        when(exerciseRepository.existsByExerciseName(exerciseName)).thenReturn(true);

        // when
        boolean result = exerciseService.existsByExerciseName(exerciseName);

        // then
        assertTrue(result);
        verify(exerciseRepository, times(1)).existsByExerciseName(exerciseName);
    }


    @Test
    void testFindAllExercisesReturnsEmptyList() {
        // given
        when(exerciseRepository.findAll()).thenReturn(Collections.emptyList());
        when(exerciseMapper.exerciseEntityToDto(any(Exercise.class))).thenReturn(new ExerciseDto());

        // when
        List<ExerciseDto> result = exerciseService.findAllExercises();

        // then
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testFindExerciseByNameReturnsEmptyOptional() {
        // given
        when(exerciseRepository.findByExerciseName("Exercise 1")).thenReturn(Optional.empty());
        when(exerciseMapper.exerciseEntityToDto(any(Exercise.class))).thenReturn(new ExerciseDto());

        // when
        Optional<Exercise> result = exerciseService.findExerciseByName("Exercise 1");

        // then
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testExistsByUrlReturnsFalse() {
        // given
        String url = "https://example.com";
        when(exerciseRepository.existsByUrl(url)).thenReturn(false);

        // when
        boolean result = exerciseService.existsByUrl(url);

        // then
        assertFalse(result);
        verify(exerciseRepository, times(1)).existsByUrl(url);
    }

    //    @Disabled
    @Test
    void testEditExercise() {
        // given
        Long id = 1L;
        ExerciseDto exerciseDto = new ExerciseDto();
        exerciseDto.setExerciseName("Test Exercise");
        exerciseDto.setDescription("This is a test exercise");
        exerciseDto.setExerciseId(id);

        Exercise updatedExercise = new Exercise();
        updatedExercise.setExerciseName("Test Exercise 2");
        updatedExercise.setDescription("This is an updated test exercise");
        updatedExercise.setExerciseId(id);

        Exercise existingExercise = new Exercise();
        existingExercise.setExerciseName("Test Exercise");
        existingExercise.setDescription("This is a test exercise");
        existingExercise.setExerciseId(id);

        when(exerciseRepository.findById(id)).thenReturn(Optional.of(existingExercise));
        when(exerciseRepository.save(any(Exercise.class))).thenReturn(updatedExercise);
        when(exerciseMapper.exerciseEntityToDto(updatedExercise)).thenReturn(exerciseDto);

        // when

        Exercise result = exerciseRepository.save(existingExercise);

        // then

        assertNotNull(result);
        assertEquals(id, result.getExerciseId());
        assertEquals("Test Exercise 2", result.getExerciseName());
        assertEquals("This is an updated test exercise", result.getDescription());
    }


    @Test
    public void testDeleteExercise() {
        // given
        Exercise exercise = new Exercise();
        exercise.setExerciseId(1L);
        when(exerciseRepository.findByExerciseId(exercise.getExerciseId())).thenReturn(exercise);
        doNothing().when(exerciseRepository).delete(exercise);
        // when
        exerciseService.deleteExercise(exercise.getExerciseId());
        // then
        verify(exerciseRepository, times(1)).findByExerciseId(exercise.getExerciseId());
        verify(exerciseRepository, times(1)).delete(exercise);
    }


    @Test
    void getExercisesByIds() {
        // given
        List<Exercise> mockExercises = Arrays.asList(new Exercise("Exercise 1", 50), new Exercise("Exercise 2", 100), new Exercise("Exercise 3", 150));
        mockExercises.forEach(exercise -> exercise.setExerciseId((long) mockExercises.indexOf(exercise) + 1));
        List<Long> mockExerciseIds = Arrays.asList(1L, 2L, 3L);

        Mockito.when(exerciseRepository.findAllById(mockExerciseIds)).thenReturn(mockExercises);
        // when
        List<Exercise> exercises = exerciseService.getExercisesByIds(mockExerciseIds);
        // then
        assertEquals(3, exercises.size());
        assertEquals("Exercise 1", exercises.get(0).getExerciseName());
        assertEquals("Exercise 2", exercises.get(1).getExerciseName());
        assertEquals("Exercise 3", exercises.get(2).getExerciseName());

    }
}