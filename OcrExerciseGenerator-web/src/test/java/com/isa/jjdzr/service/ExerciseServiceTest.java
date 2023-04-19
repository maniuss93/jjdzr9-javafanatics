package com.isa.jjdzr.service;

import com.isa.jjdzr.controller.ExerciseController;
import com.isa.jjdzr.exercise.model.Exercise;
import com.isa.jjdzr.exercise.service.PdfExerciseGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ExerciseServiceTest {
    @Mock
    private PdfExerciseGenerator pdfExerciseGenerator;

    @InjectMocks
    private ExerciseController exerciseController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGeneratePdf() throws Exception{

        List<Exercise> mockExercises = new ArrayList<>();
        List<Long> mockExercisesIds = new ArrayList<>();
        mockExercises.add(new Exercise("Exercise 1", 50));
        mockExercises.add(new Exercise("Exercise 2",100));
        mockExercises.add(new Exercise("Exercise 2",150));
        mockExercisesIds.add(new Exercise("Exercise 1", 50).getExerciseId());
        mockExercisesIds.add(new Exercise("Exercise 1", 100).getExerciseId());
        mockExercisesIds.add(new Exercise("Exercise 1", 150).getExerciseId());


        byte[] mockPdf = new byte[] { 0x25, 0x50, 0x44, 0x46 };
        when(pdfExerciseGenerator.generatePdf(mockExercises)).thenReturn(ResponseEntity.ok().body(mockPdf));

        ResponseEntity<byte[]> response = exerciseController.generatePdf(mockExercisesIds);
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
    }
}