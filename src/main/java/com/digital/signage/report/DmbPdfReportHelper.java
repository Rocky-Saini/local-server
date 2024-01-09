package com.digital.signage.report;

import com.digital.signage.dto.DeviceIdAndName;
import com.digital.signage.dto.DmbReportResponseData;
import com.digital.signage.util.TypeEnum;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DmbPdfReportHelper {

    private DmbPdfReportHelper() {
    }

    private static final Font HEADER_FONT =
            new Font(Font.FontFamily.TIMES_ROMAN, Font.DEFAULTSIZE, Font.BOLD);
    private static final Font CELL_FONT =
            new Font(Font.FontFamily.TIMES_ROMAN, Font.DEFAULTSIZE, Font.NORMAL);

    static void writeReportDataInDocument(
            PdfInProgress pdfInProgress,
            List<DmbReportResponseData> data
    ) throws JsonArrayToPdf.ServerPDFException {
        if (data == null || data.isEmpty()) {
            // no data. No report. sorry
            return;
        }
        assert pdfInProgress.getDocument() != null;

        PdfPTable table = new PdfPTable(4);

        int hourCount = 24;
        PdfPTable hourTable = new PdfPTable(hourCount);
        PdfPTable countTable = new PdfPTable(hourCount);
        PdfPTable percentageTable = new PdfPTable(hourCount);
        PdfPTable deviceTable = new PdfPTable(hourCount);
        table.setWidthPercentage(100);
        float[] columnWidths = {4, 4, 88, 4};

        try {
            deviceTable.setSplitLate(false);
            table.setSplitLate(false);
            table.setTotalWidth(columnWidths);
        } catch (DocumentException e) {
            throw new JsonArrayToPdf.ServerPDFException(e);
        }

        table.addCell(getHeaderCell(""));
        table.addCell(getHeaderCell("OFF"));
        table.addCell(getHeaderCell("ON"));
        table.addCell(getHeaderCell("Grand Total"));

        Integer offCount = null;
        Integer totalCount = null;
        Double offPercentage = null;
        List<DeviceIdAndName> offDevices = new ArrayList<>();

        for (DmbReportResponseData dto : data) {
            if (TypeEnum.ON.equals(dto.getType())) {
                hourTable.addCell(getReportCell(dto.getNumberOfHrs()));
                countTable.addCell(getReportCell(dto.getCount()));
                percentageTable.addCell(getValueCell(
                        Objects.requireNonNull(dto.getPercentage())+ "%"));
                deviceTable.addCell(getDeviceCell(dto.getDevices()));
            } else if (TypeEnum.OFF.equals(dto.getType())) {
                offCount = dto.getCount();
                offPercentage = (Objects.requireNonNull(dto.getPercentage()));
                offDevices = dto.getDevices();
            } else {
                totalCount = dto.getCount();
            }
        }
        table.addCell(getHeaderCell("Hours"));
        table.addCell(getHeaderCell(""));
        table.addCell(getInnerTableCell(hourTable));
        table.addCell(getHeaderCell(""));

        table.addCell(getHeaderCell("Count"));
        table.addCell(getReportCell(offCount));
        table.addCell(getInnerTableCell(countTable));
        table.addCell(getReportCell(totalCount));

        table.addCell(getHeaderCell("%age"));
        table.addCell(getReportCellDataTypeDouble(offPercentage));
        table.addCell(getInnerTableCell(percentageTable));
        table.addCell(getValueCell("100%"));

        table.addCell(getHeaderCell("Devices"));
        table.addCell(getDeviceCell(offDevices));
        table.addCell(getInnerTableCell(deviceTable));
        table.addCell(getValueCell(""));

        try {
            pdfInProgress.getDocument().add(table);
        } catch (DocumentException e) {
            throw new JsonArrayToPdf.ServerPDFException(e);
        }
    }

    private static PdfPCell getReportCell(Integer value) {
        String string = (value == null) ? "" : String.valueOf(value);
        PdfPCell cell = new PdfPCell(new Phrase(string, CELL_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        return cell;
    }

    private static PdfPCell getReportCellDataTypeDouble(Double value) {
        String string = (value == null) ? "" : String.valueOf(value);
        PdfPCell cell = new PdfPCell(new Phrase(string+"%", CELL_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        return cell;
    }

    private static PdfPCell getDeviceCell(List<DeviceIdAndName> deviceList) {
        String deviceText = "";
        if (deviceList != null && !deviceList.isEmpty()) {
            deviceText = getDeviceText(deviceList);
        }
        return getValueCell(deviceText);
    }

    private static String getDeviceText(List<DeviceIdAndName> devices) {
        StringBuilder deviceText = new StringBuilder();
        boolean isFirst = true;
        for (DeviceIdAndName device : devices) {
            if (isFirst) {
                isFirst = false;
            } else {
                deviceText.append(",\n");
            }
            deviceText.append(device.getDeviceName());
        }
        deviceText.append(".");
        return deviceText.toString();
    }

    private static PdfPCell getInnerTableCell(PdfPTable table) {
        PdfPCell cell = new PdfPCell(table);
        cell.setPadding(0);
        return cell;
    }

    private static PdfPCell getHeaderCell(String string) {
        if (string == null || "".equals(string)) {
            return new PdfPCell();
        }
        PdfPCell cell = new PdfPCell(new Phrase(string, HEADER_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        return cell;
    }

    private static PdfPCell getValueCell(String string) {
        if (string == null || "".equals(string)) {
            return new PdfPCell();
        }
        PdfPCell cell = new PdfPCell(new Phrase(string, CELL_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        return cell;
    }
}

