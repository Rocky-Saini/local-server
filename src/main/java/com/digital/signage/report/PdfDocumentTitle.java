package com.digital.signage.report;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;

public class PdfDocumentTitle {
    private Document document;
    private PdfWriter pdfWriter;

    public PdfDocumentTitle(Document document, PdfWriter pdfWriter) {
        this.document = document;
        this.pdfWriter = pdfWriter;
    }

    public Document getDocument() {
        return document;
    }

    public PdfWriter getPdfWriter() {
        return pdfWriter;
    }
}