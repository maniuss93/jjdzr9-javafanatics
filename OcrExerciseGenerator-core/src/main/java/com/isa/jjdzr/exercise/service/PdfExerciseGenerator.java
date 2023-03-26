package com.isa.jjdzr.exercise.service;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class PdfExerciseGenerator {


    public void generatePdf() throws Exception {
        // Utwórz dokument PDF
        Document document = new Document();

        // Utwórz strumień wyjściowy dla pliku PDF
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        PdfWriter.getInstance(document, outputStream);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String id = formatter.format(new Date());


        // Otwórz dokument
        document.open();

        // Dodaj treść do dokumentu
        //document.add((Element) RandomExerciseGenerator.generateExercise(50));
            document.add(new Paragraph("Kupa i siku"));
        // Zakończ dokument
        document.close();
        String filePath = "/home/jacek/Desktop/jjdzr9-javafanatics/OcrExerciseGenerator-core/src/main/resources/newpdf.pdf";







    }
}
