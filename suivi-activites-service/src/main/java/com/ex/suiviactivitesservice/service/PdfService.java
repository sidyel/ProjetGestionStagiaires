package com.ex.suiviactivitesservice.service;

import com.ex.suiviactivitesservice.objet.Personne;
import com.ex.suiviactivitesservice.objet.Stagiaire;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.*;
import java.time.LocalDate;

import java.awt.*;
import java.io.FileOutputStream;


import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;


import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class PdfService {

    /**
     * Génère l'attestation de stage en mémoire et renvoie le PDF sous forme de tableau de bytes.
     */
    public byte[] createAttestationPdf(String nom,
                                       String prenom,
                                       Long idpersonne,
                                       String dateDebut,
                                       String dateFin) throws Exception
    {
        Document document = new Document(PageSize.A4, 50, 50, 80, 50);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter writer = PdfWriter.getInstance(document, baos);

        document.open();

        // 1. Contour léger
        PdfContentByte canvas = writer.getDirectContent();
        canvas.setLineWidth(2f);
        canvas.setColorStroke(new Color(200, 200, 200));
        float margin = 30;
        canvas.rectangle(
                margin,
                margin,
                PageSize.A4.getWidth() - 2 * margin,
                PageSize.A4.getHeight() - 2 * margin
        );
        canvas.stroke();

        // 2. Logo + date
        Image logo = Image.getInstance("C:\\Users\\DELL\\Documents\\logo.jpeg");
        logo.scaleToFit(120, 60);
        logo.setAbsolutePosition(50, PageSize.A4.getHeight() - 110);
        document.add(logo);

        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("dd MMMM yyyy"));
        Font dateFont = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.ITALIC, new Color(100, 100, 100));
        Paragraph date = new Paragraph("DAKAR, " + today, dateFont);
        date.setAlignment(Element.ALIGN_RIGHT);
        document.add(date);

        // 3. Titre
        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20, new Color(0, 102, 204));
        Chunk titleChunk = new Chunk("ATTESTATION DE STAGE", titleFont);
        titleChunk.setUnderline(2f, -3f);
        Paragraph title = new Paragraph(titleChunk);
        title.setAlignment(Element.ALIGN_CENTER);
        title.setSpacingBefore(40);
        title.setSpacingAfter(30);
        document.add(title);

        // 4. Corps principal
        Font bodyFont = FontFactory.getFont(FontFactory.TIMES, 13, new Color(50, 50, 50));
        Paragraph body = new Paragraph();
        body.setFont(bodyFont);
        body.setAlignment(Element.ALIGN_JUSTIFIED);
        body.setFirstLineIndent(20);
        body.add(String.format(
                "Nous soussignés, société SENTECH, dont le siège social est situé à Ouest Foire Cité Air France Dakar, " +
                        "attestons que M./Mme %s %s, inscrit(e) sous l’ID %d, a effectué un stage au sein de notre société du %s au %s.",
                prenom, nom, idpersonne, dateDebut, dateFin
        ));
        body.setSpacingAfter(20);
        document.add(body);

        // 5. Félicitations
        Paragraph praise1 = new Paragraph(
                "Au cours de ce stage, M./Mme " + prenom + " " + nom +
                        " a fait preuve d'un professionnalisme exemplaire, d'une grande rigueur et d'une capacité d'adaptation remarquable.",
                bodyFont
        );
        praise1.setAlignment(Element.ALIGN_JUSTIFIED);
        praise1.setFirstLineIndent(20);
        praise1.setSpacingAfter(15);
        document.add(praise1);

        Paragraph praise2 = new Paragraph(
                "Sa curiosité intellectuelle, sa motivation sans faille et sa créativité ont grandement contribué à la réussite des missions qui lui ont été confiées.",
                bodyFont
        );
        praise2.setAlignment(Element.ALIGN_JUSTIFIED);
        praise2.setFirstLineIndent(20);
        praise2.setSpacingAfter(20);
        document.add(praise2);

        // 6. Footer explicatif
        Paragraph footer = new Paragraph(
                "En foi de quoi, nous lui délivrons la présente attestation sur demande pour servir et valoir ce que de droit.",
                bodyFont
        );
        footer.setAlignment(Element.ALIGN_JUSTIFIED);
        footer.setFirstLineIndent(20);
        footer.setSpacingAfter(40);
        document.add(footer);

        // 7. Signature
        Font signFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, new Color(0, 102, 204));
        Paragraph sign = new Paragraph("Le Directeur", signFont);
        sign.setAlignment(Element.ALIGN_LEFT);
        document.add(sign);

        document.close();
        return baos.toByteArray();
    }
}
