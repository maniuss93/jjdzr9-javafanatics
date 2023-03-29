package com.isa.jjdzr.service;

import com.isa.jjdzr.exercise.service.PdfExerciseGenerator;
import com.isa.jjdzr.repository.ExerciseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;
    private final PdfExerciseGenerator pdfExerciseGenerator;
    public ResponseEntity<byte[]> generatePdf() throws Exception {
        return pdfExerciseGenerator.generatePdf();
    }

}
