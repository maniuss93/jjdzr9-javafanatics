package com.isa.jjdzr.exercise.service;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GeneratePDF {
    public static void generatePdf() {
        try {
            Document document = new Document();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd_HHmmss");
            String id = formatter.format(new Date());
            String fileName = "exercise" + id + ".pdf";
            String filePath = Paths.get("OcrExerciseGenerator-core","src","main","java","com","isa","jjdzr","resourcesPDF", fileName).toString();
            OutputStream outputStream = new FileOutputStream(new File(filePath));
            PdfWriter.getInstance(document, outputStream);
            document.open();
            document.add(new Paragraph("Hello World!"));
            document.close();
            System.out.println("The PDF file has been created.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
