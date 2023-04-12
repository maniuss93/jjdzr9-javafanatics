package com.isa.jjdzr.exercise.service;

import com.isa.jjdzr.exercise.model.Exercise;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
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

        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100);

        PdfPCell header1 = new PdfPCell(new Paragraph("Exercise Name"));
        PdfPCell header2 = new PdfPCell(new Paragraph("Category"));
        PdfPCell header3 = new PdfPCell(new Paragraph("Description"));
        table.addCell(header1);
        table.addCell(header2);
        table.addCell(header3);

        for (Exercise exercise : exercises) {
            PdfPCell cell1 = new PdfPCell(new Paragraph(exercise.getExerciseName()));
            PdfPCell cell2 = new PdfPCell(new Paragraph(exercise.getExerciseCategory().getDescription()));
            PdfPCell cell3 = new PdfPCell(new Paragraph(exercise.getDescription()));
            table.addCell(cell1);
            table.addCell(cell2);
            table.addCell(cell3);
        }

        document.add(table);
        document.close();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "exercise(" + id + ").pdf");

        return new ResponseEntity<>(outputStream.toByteArray(), headers, HttpStatus.OK);
    }

}