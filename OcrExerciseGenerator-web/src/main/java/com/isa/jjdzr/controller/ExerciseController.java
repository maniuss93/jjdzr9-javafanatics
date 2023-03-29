package com.isa.jjdzr.controller;

import com.isa.jjdzr.exercise.service.PdfExerciseGenerator;
import com.isa.jjdzr.service.ExerciseService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("exercises")
public class ExerciseController {

    private final ExerciseService exerciseService;

    @Autowired
    private PdfExerciseGenerator pdfExerciseGenerator;

    @GetMapping("/generate-pdf")
    public ResponseEntity<byte[]> generatePdf() throws Exception {
        return pdfExerciseGenerator.generatePdf();
    }



}
