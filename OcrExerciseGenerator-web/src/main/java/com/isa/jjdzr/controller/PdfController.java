package com.isa.jjdzr.controller;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

@Controller
public class PdfController {

    @PostMapping("/generate-pdf")
    public ResponseEntity<byte[]> generatePdf() throws Exception {
        // Utwórz dokument PDF
        Document document = new Document();

        // Utwórz strumień wyjściowy dla pliku PDF
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, outputStream);

        // Otwórz dokument
        document.open();

        // Dodaj treść do dokumentu
        document.add(new Paragraph("To jest przykładowy tekst w dokumencie PDF."));

        // Zakończ dokument
        document.close();

        // Utwórz nagłówek odpowiedzi
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "plik.pdf");

        // Utwórz odpowiedź z byte[] zawierającym plik PDF
        ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(outputStream.toByteArray(), headers, HttpStatus.OK);
        return response;
    }
}
