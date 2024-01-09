package com.digital.signage.report;

import com.digital.signage.dto.DeviceIdAndName;
import com.digital.signage.dto.DeviceLogReportRequestDTO;
import com.digital.signage.dto.DmbReportResponseData;
import com.digital.signage.util.DateTimeUtilsKt;
import com.digital.signage.util.Message;
import com.digital.signage.util.TypeEnum;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

public class DmbXlsReportHelper {
    private static final Logger logger = LoggerFactory.getLogger(DmbXlsReportHelper.class);
    private static final int START_ROW_INDEX = 0;
    private static final int START_COLUMN_INDEX = 0;

    private static final int DEVICES_START_ROW_INDEX = START_ROW_INDEX + 6;

    private static final int OFF_COLUMN_NUMBER = START_COLUMN_INDEX + 1;

    private static final int TOTAL_COLUMN_NUMBER = START_COLUMN_INDEX + 26;

    @FunctionalInterface public interface LocationNameById {
        String getLocationNameById(long locationId);
    }

    @FunctionalInterface public interface LocalizedMessage {
        String getLocalizedMessage(String key, Object... params);
    }

    private DmbXlsReportHelper() {
    }

    private static CellRangeAddress cellRangeString(int c1, int r1, int c2, int r2) {
        String cellRange = CellReference.convertNumToColString(START_COLUMN_INDEX + c1)
                + (START_ROW_INDEX + r1)
                + ":"
                + CellReference.convertNumToColString(START_COLUMN_INDEX + c2)
                + (START_ROW_INDEX + r2);
        return CellRangeAddress.valueOf(cellRange);
    }

    private static CellStyle generateCenterCellStyle(XSSFWorkbook xssfWorkbook) {
        CellStyle centerCellStyle = xssfWorkbook.createCellStyle();
        centerCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        centerCellStyle.setAlignment(HorizontalAlignment.CENTER);
        centerCellStyle.setWrapText(true);
        return centerCellStyle;
    }

    private static void setData(
            int columnIndex,
            DmbReportResponseData dmbReportResponseData,
            CellStyle centerCellStyle,
            Row columnHeaderTitleRow,
            Row hoursRow,
            Row countRow,
            Row percentageRow
    ) {
        setColumnHeaderTitleCell(columnHeaderTitleRow, columnIndex,
                Objects.requireNonNull(dmbReportResponseData.getType()), centerCellStyle);
        hoursRow.createCell(columnIndex)
                .setCellValue(String.valueOf((dmbReportResponseData.getNumberOfHrs() == null) ? ""
                        : dmbReportResponseData.getNumberOfHrs()));
        countRow.createCell(columnIndex)
                .setCellValue(String.valueOf(
                        (dmbReportResponseData.getCount() == null) ? "" : dmbReportResponseData.getCount()));
        percentageRow.createCell(columnIndex)
                .setCellValue(String.valueOf((dmbReportResponseData.getPercentage() == null) ? ""
                        : dmbReportResponseData.getPercentage()));
    }

    private static Row getOrCreateRow(XSSFSheet xssfSheet, int rowIndex) {
        Row row = xssfSheet.getRow(rowIndex);
        if (row == null) {
            row = xssfSheet.createRow(rowIndex);
        }
        return row;
    }

    private static void setDevicesName(
            List<DeviceIdAndName> devices,
            XSSFSheet xssfSheet,
            int columnIndex
    ) {
        int devicesSize = devices.size();
        for (int i = 0; i < devicesSize; i++) {
            DeviceIdAndName deviceIdAndName = devices.get(i);
            Row row4 = getOrCreateRow(xssfSheet, DEVICES_START_ROW_INDEX + i);
            row4.createCell(columnIndex).setCellValue(deviceIdAndName.getDeviceName());
        }
    }

    private static int getColumnIndexByDmbData(DmbReportResponseData dmbReportResponseData) {
        int columnIndex;
        if (TypeEnum.OFF.equals(dmbReportResponseData.getType())) {
            columnIndex = OFF_COLUMN_NUMBER;
        } else if (TypeEnum.TOTAL.equals(dmbReportResponseData.getType())) {
            columnIndex = TOTAL_COLUMN_NUMBER;
        } else if (TypeEnum.ON.equals(dmbReportResponseData.getType())) {
            columnIndex =
                    START_COLUMN_INDEX + Objects.requireNonNull(dmbReportResponseData.getNumberOfHrs()) + 2;
        } else {
            throw new IllegalStateException("this should not happen");
        }
        return columnIndex;
    }

    private static void setColumnHeaderTitleCell(
            Row columnHeaderTitleRow,
            int columnIndex,
            TypeEnum typeEnum,
            CellStyle centerCellStyle
    ) {
        final Cell columnHeaderTitleCell = columnHeaderTitleRow.createCell(columnIndex);
        columnHeaderTitleCell.setCellValue(typeEnum.name());
        columnHeaderTitleCell.setCellStyle(centerCellStyle);
    }

    private static void mergeRelevantCells(XSSFSheet xssfSheet) {
        xssfSheet.addMergedRegion(cellRangeString(2, 2, 25, 2)); // C2:Z2
        xssfSheet.addMergedRegion(cellRangeString(26, 2, 26, 3)); // AA2:AA3
        xssfSheet.addMergedRegion(cellRangeString(26, 4, 26, 5)); // AA4:AA5
        xssfSheet.addMergedRegion(cellRangeString(1, 2, 1, 3)); // B2:B3
        xssfSheet.addMergedRegion(cellRangeString(0, 1, 26, 1)); // A1:AA1
    }

    private static String getTitle(
            LocalizedMessage localizedMessage,
            LocationNameById locationNameById,
            DeviceLogReportRequestDTO deviceLogReportRequestDTO
    ) {
        String header;
        if (Objects.nonNull(deviceLogReportRequestDTO.getLocationId())) {
            String locationName =
                    locationNameById.getLocationNameById(deviceLogReportRequestDTO.getLocationId());

            header = localizedMessage.getLocalizedMessage(
                    Message.MediaPlayerSummaryReport.MEDIA_PLAYER_SUMMARY_REPORT_TITLE_WITH_LOCATION,
                    DateTimeUtilsKt.dateFromDateTime(deviceLogReportRequestDTO.getFromDate()), locationName);
        } else {
            header = localizedMessage.getLocalizedMessage(
                    Message.MediaPlayerSummaryReport.MEDIA_PLAYER_SUMMARY_REPORT_TITLE,
                    DateTimeUtilsKt.dateFromDateTime(deviceLogReportRequestDTO.getFromDate()));
        }
        return header;
    }

    public static void generateXLSSheet(
            List<DmbReportResponseData> data,
            String sheetName,
            Path filePath,
            DeviceLogReportRequestDTO deviceLogReportRequestDTO,
            LocationNameById locationNameById,
            LocalizedMessage localizedMessage
    ) throws IOException {
        Objects.requireNonNull(deviceLogReportRequestDTO);
        if (data == null || data.isEmpty()) {
            return;
        }
        try (XSSFWorkbook xssfWorkbook = new XSSFWorkbook()) {
            XSSFSheet xssfSheet = xssfWorkbook.createSheet(sheetName);

            mergeRelevantCells(xssfSheet);

            CellStyle centerCellStyle = generateCenterCellStyle(xssfWorkbook);

            CellRangeAddress region = new CellRangeAddress(1, 4, 12, 12);
            RegionUtil.setBottomBorderColor(IndexedColors.GREEN.index, region, xssfSheet);

            Row titleRow = xssfSheet.createRow(START_ROW_INDEX);
            Row columnHeaderTitleRow = xssfSheet.createRow(START_ROW_INDEX + 1);
            Row hoursRow = xssfSheet.createRow(START_ROW_INDEX + 2);
            Row countRow = xssfSheet.createRow(START_ROW_INDEX + 3);
            Row percentageRow = xssfSheet.createRow(START_ROW_INDEX + 4);

            hoursRow.createCell(START_COLUMN_INDEX).setCellValue("Hours");
            countRow.createCell(START_COLUMN_INDEX).setCellValue("Count");
            percentageRow.createCell(START_COLUMN_INDEX).setCellValue("%age");
            xssfSheet.createRow(START_ROW_INDEX + 6)
                    .createCell(START_COLUMN_INDEX)
                    .setCellValue("Devices");

            Cell headerCell = titleRow.createCell(START_COLUMN_INDEX);
            headerCell.setCellValue(
                    getTitle(localizedMessage, locationNameById, deviceLogReportRequestDTO));
            headerCell.setCellStyle(centerCellStyle);
            data.forEach(dmbReportResponseData -> {
                int columnIndex = getColumnIndexByDmbData(dmbReportResponseData);
                setData(columnIndex, dmbReportResponseData, centerCellStyle, columnHeaderTitleRow, hoursRow,
                        countRow, percentageRow);
                if (!TypeEnum.TOTAL.equals(dmbReportResponseData.getType())) {
                    setDevicesName(dmbReportResponseData.getDevices(), xssfSheet, columnIndex);
                }
            });
            try (OutputStream outputStream = Files.newOutputStream(filePath)) {
                xssfWorkbook.write(outputStream);
            }
        }
    }
}

