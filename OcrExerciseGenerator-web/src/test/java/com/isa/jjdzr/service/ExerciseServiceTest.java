package com.isa.jjdzr.service;

import com.isa.jjdzr.controller.ExerciseController;
import com.isa.jjdzr.exercise.model.Exercise;
import com.isa.jjdzr.exercise.service.PdfExerciseGenerator;
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
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ExerciseServiceTest {
    @Mock
    private PdfExerciseGenerator pdfExerciseGenerator;
    @Mock
    private ExerciseRepository exerciseRepository;
    @Mock
    private UserRepository userRepository;
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
        List<Exercise> mockExercises = Arrays.asList(new Exercise("Exercise 1", 50), new Exercise("Exercise 2", 100), new Exercise("Exercise 3", 150));

        mockExercises.forEach(exercise -> exercise.setExerciseId((long) mockExercises.indexOf(exercise) + 1));

        List<Long> mockExercisesIds = Arrays.asList(1L, 2L, 3L);

        byte[] mockPdf = new byte[]{0x25, 0x50, 0x44, 0x46};
        when(pdfExerciseGenerator.generatePdf(mockExercises)).thenReturn(ResponseEntity.ok().body(mockPdf));
        Mockito.when(exerciseRepository.findAllById(Arrays.asList(1L, 2L, 3L))).thenReturn(mockExercises);

        ResponseEntity<byte[]> response = exerciseController.generatePdf(mockExercisesIds);

        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertArrayEquals(mockPdf, response.getBody());

    }


    @Test
    void addExercise() {
    }

    @Test
    void findAllExercises() {
    }

    @Test
    void findExerciseByName() {
    }

    @Test
    void existsByExerciseName() {
    }

    @Test
    void existsByUrl() {
    }

    @Test
    void editExercise() {
    }

    @Test
    void deleteExercise() {
    }

    @Test
    void generateRandomExercises() {
    }

    @Test
    void getExercisesByIds() {

        List<Exercise> mockExercises = Arrays.asList(new Exercise("Exercise 1", 50), new Exercise("Exercise 2", 100), new Exercise("Exercise 3", 150));
        mockExercises.forEach(exercise -> exercise.setExerciseId((long) mockExercises.indexOf(exercise) + 1));
        List<Long> mockExerciseIds = Arrays.asList(1L, 2L, 3L);

        Mockito.when(exerciseRepository.findAllById(mockExerciseIds)).thenReturn(mockExercises);

        List<Exercise> exercises = exerciseService.getExercisesByIds(mockExerciseIds);

        assertEquals(3, exercises.size());
        assertEquals("Exercise 1", exercises.get(0).getExerciseName());
        assertEquals("Exercise 2", exercises.get(1).getExerciseName());
        assertEquals("Exercise 3", exercises.get(2).getExerciseName());

    }
}