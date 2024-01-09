package com.digital.signage.report;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;


public class PdfInProgress {
    private ColumnsOrdersAndFields columnsOrdersAndFields;
    private Document document;
    private PdfWriter pdfWriter;

    public PdfInProgress(ColumnsOrdersAndFields columnsOrdersAndFields, Document document, PdfWriter pdfWriter) {
        this.columnsOrdersAndFields = columnsOrdersAndFields;
        this.document = document;
        this.pdfWriter = pdfWriter;
    }

    public ColumnsOrdersAndFields getColumnsOrdersAndFields() {
        return columnsOrdersAndFields;
    }

    public Document getDocument() {
        return document;
    }

    public PdfWriter getPdfWriter() {
        return pdfWriter;
    }
}

