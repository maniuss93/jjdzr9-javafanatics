package com.isa.jjdzr.service;

import com.isa.jjdzr.exercise.service.PdfExerciseGenerator;
import com.isa.jjdzr.repository.ExerciseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;
    private final PdfExerciseGenerator pdfExerciseGenerator;
    public void generatePdf() throws Exception {
        pdfExerciseGenerator.generatePdf();
    }

}
