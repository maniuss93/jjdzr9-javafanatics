package com.isa.jjdzr.exercise.service;

import com.isa.jjdzr.exercise.model.Exercise;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
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
        BaseFont baseFont = BaseFont.createFont("fonts/times.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font titleFont = new Font(baseFont, 18, Font.ITALIC);
        Paragraph title = new Paragraph("Twój zestaw ćwiczeń", titleFont);
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


        Font subtitleFont = new Font(baseFont, 12, Font.BOLD);
        Font headerFont = new Font(baseFont, 12, Font.BOLD);
        Font contentFont = new Font(baseFont, 12);

        for (Exercise exercise : exercises) {
            Paragraph exerciseName = new Paragraph(exercise.getExerciseName(), subtitleFont);
            exerciseName.setAlignment(Paragraph.ALIGN_LEFT);
            document.add(exerciseName);

            Chunk categoryHeader = new Chunk("Kategoria: ", headerFont);
            Paragraph category = new Paragraph();
            category.add(categoryHeader);
            category.add(new Chunk(exercise.getExerciseCategory().getDescription(), contentFont));
            category.setAlignment(Paragraph.ALIGN_LEFT);
            document.add(category);

            Chunk descriptionHeader = new Chunk("Opis: ", headerFont);
            Paragraph description = new Paragraph();
            description.add(descriptionHeader);
            description.add(new Chunk(exercise.getDescription(), contentFont));
            description.setAlignment(Paragraph.ALIGN_LEFT);
            document.add(description);

            Chunk urlHeader = new Chunk("URL: ", headerFont);
            Paragraph url = new Paragraph();
            url.add(urlHeader);
            url.add(new Chunk(exercise.getUrl(), contentFont));
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

