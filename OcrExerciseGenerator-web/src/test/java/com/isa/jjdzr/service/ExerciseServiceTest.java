package com.isa.jjdzr.service;

import com.isa.jjdzr.controller.ExerciseController;
import com.isa.jjdzr.exercise.model.Exercise;
import com.isa.jjdzr.exercise.service.PdfExerciseGenerator;
import com.isa.jjdzr.repository.ExerciseRepository;
import com.isa.jjdzr.repository.UserRepository;
import org.junit.Assert;
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
        Exercise exercise1 = new Exercise("Exercise 1", 50);
        Exercise exercise2 = new Exercise("Exercise 2", 100);
        Exercise exercise3 = new Exercise("Exercise 3", 150);
        exercise1.setExerciseId(1L);
        exercise2.setExerciseId(2L);
        exercise3.setExerciseId(3L);

        List<Exercise> mockExercises = Arrays.asList(exercise1, exercise2, exercise3);
        List<Long> mockExercisesIds = Arrays.asList(1L, 2L, 3L);


        byte[] mockPdf = new byte[]{0x25, 0x50, 0x44, 0x46};
        when(pdfExerciseGenerator.generatePdf(mockExercises)).thenReturn(ResponseEntity.ok().body(mockPdf));
        Mockito.when(exerciseRepository.findAllById(Arrays.asList(1L, 2L, 3L))).thenReturn(Arrays.asList(exercise1, exercise2, exercise3));

        ResponseEntity<byte[]> response = exerciseController.generatePdf(mockExercisesIds);

        System.out.println(mockExercises);
        System.out.println(mockExercisesIds);
        System.out.println(exerciseController.generatePdf(mockExercisesIds));
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
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

        Exercise exercise1 = new Exercise();
        exercise1.setExerciseId(1L);
        exercise1.setExerciseName("Exercise 1");

        Exercise exercise2 = new Exercise();
        exercise2.setExerciseId(2L);
        exercise2.setExerciseName("Exercise 2");

        Mockito.when(exerciseRepository.findAllById(Arrays.asList(1L, 2L))).thenReturn(Arrays.asList(exercise1, exercise2));

        List<Exercise> exercises = exerciseService.getExercisesByIds(Arrays.asList(1L, 2L));
        List<Long> mockExercisesIds = Arrays.asList(1L, 2L);
        System.out.println(exerciseService.getExercisesByIds(mockExercisesIds));

        Assert.assertEquals(2, exercises.size());
        Assert.assertEquals(exercise1, exercises.get(0));
        Assert.assertEquals(exercise2, exercises.get(1));
    }
}