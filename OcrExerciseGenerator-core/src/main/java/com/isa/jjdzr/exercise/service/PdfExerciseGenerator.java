package com.isa.jjdzr.exercise.service;

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

@Service
public class PdfExerciseGenerator {

    public ResponseEntity<byte[]> generatePdf() throws Exception {

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

        PdfPCell header1 = new PdfPCell(new Paragraph("Column 1"));
        PdfPCell header2 = new PdfPCell(new Paragraph("Column 2"));
        PdfPCell header3 = new PdfPCell(new Paragraph("Column 3"));
        table.addCell(header1);
        table.addCell(header2);
        table.addCell(header3);

        for (int i = 1; i <= 6; i++) {
            PdfPCell cell1 = new PdfPCell(new Paragraph("Row " + i + ", Column 1"));
            PdfPCell cell2 = new PdfPCell(new Paragraph("Row " + i + ", Column 2"));
            PdfPCell cell3 = new PdfPCell(new Paragraph("Row " + i + ", Column 3"));
            table.addCell(cell1);
            table.addCell(cell2);
            table.addCell(cell3);
        }

        document.add(table);
        document.close();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "exercise(" + id + ").pdf");

        ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(outputStream.toByteArray(), headers, HttpStatus.OK);
        return response;
    }
}
