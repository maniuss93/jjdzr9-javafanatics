package com.isa.jjdzr.controller;

import com.isa.jjdzr.service.ExerciseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("exercises")
public class ExerciseController {
    private final ExerciseService exerciseService;

    @PostMapping("/generate-pdf")
    public ResponseEntity<byte[]> generatePdf() throws Exception {
        return exerciseService.generatePdf();
    }

}
