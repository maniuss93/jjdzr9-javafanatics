package com.isa.jjdzr.exercise.service;

import com.isa.jjdzr.exercise.model.Exercise;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Service
public class PdfExerciseGenerator {

    public ResponseEntity<byte[]> generatePdf(List<Exercise> exercises) throws Exception {
        Document document = new Document();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, outputStream);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String id = formatter.format(new Date());

        document.open();
        Font titleFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
        Paragraph title = new Paragraph("This document PDF was created thanks to Jack's hard work :)", titleFont);
        title.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(title);
        Paragraph emptyLine = new Paragraph(" ");
        document.add(emptyLine);
        Collections.sort(exercises, new Comparator<Exercise>() {
            @Override
            public int compare(Exercise o1, Exercise o2) {
                return o1.getExerciseCategory().compareTo(o2.getExerciseCategory());
            }
        });

        Font subtitleFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
        for (Exercise exercise : exercises) {
            Paragraph exerciseName = new Paragraph(exercise.getExerciseName(), subtitleFont);
            exerciseName.setAlignment(Paragraph.ALIGN_LEFT);
            document.add(exerciseName);

            Paragraph category = new Paragraph("Kategoria: " + exercise.getExerciseCategory().getDescription());
            category.setAlignment(Paragraph.ALIGN_LEFT);
            document.add(category);

            Paragraph description = new Paragraph("Opis: " + exercise.getDescription());
            description.setAlignment(Paragraph.ALIGN_LEFT);
            document.add(description);

            Paragraph url = new Paragraph("URL: " + exercise.getUrl());
            url.setAlignment(Paragraph.ALIGN_LEFT);
            document.add(url);

            document.add(emptyLine);
        }

        document.close();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "exercise(" + id + ").pdf");

        return new ResponseEntity<>(outputStream.toByteArray(), headers, HttpStatus.OK);
    }

}

