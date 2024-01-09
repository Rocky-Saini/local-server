package com.digital.signage.report;


import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class WorkbookAndSheets {
    private Workbook workbook;
    private Sheet sheet;
    /*    private Sheet childSheet;*/

    public WorkbookAndSheets(Workbook workbook, Sheet sheet/*, Sheet childSheet*/) {
        this.workbook = workbook;
        this.sheet = sheet;
        /*        this.childSheet = childSheet;*/
    }

    public Workbook getWorkbook() {
        return workbook;
    }

    public Sheet getSheet() {
        return sheet;
    }

/*
    public Sheet getChildSheet() {
        return childSheet;
    }
*/
}
