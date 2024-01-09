package com.digital.signage.report;


import com.digital.signage.annotations.ReportColumn;
import com.digital.signage.annotations.ReportSimpleDateFormat;
import com.digital.signage.annotations.ReportTimeFormat;
import com.digital.signage.util.LocalizationUtils;
import com.digital.signage.util.ReportsUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static com.digital.signage.annotations.ReportConstants.SHEET_NAME;
import static com.digital.signage.util.ReportConstants.FETCH_MORE_RECORD_PER_ITERATION;

public class JsonArrayToXls {
    private static final Logger logger = LoggerFactory.getLogger(JsonArrayToXls.class);
    private static final Map<String, SimpleDateFormat> DATE_TIME_FORMATS_MAP =
            new ConcurrentHashMap<>();

    static <T> int convertToXls(
            List<T> data,
            Class<T> clazz,
            Path filePath,
            int rowNumberToStartWith
    ) throws IOException {
        if (data == null || data.isEmpty()) {
            // no data. No report. sorry
            return 0;
        }
        WorkbookAndSheets workbookAndSheets = createNewXls();
        int rowCount;
        try (Workbook workbook = workbookAndSheets.getWorkbook()) {
            CellStyle cellStyle = workbook.createCellStyle();
            Sheet sheet = workbookAndSheets.getSheet();

            ColumnsOrdersAndFields columnsOrdersAndFields = generateMetaDataForSheet(clazz);

            Map<Integer, String> columnNameMap = columnsOrdersAndFields.getOrderAndColumnNameMap();
            List<Integer> orderList = columnsOrdersAndFields.getOrderList();
            List<Field> orderedFields = columnsOrdersAndFields.getOrderedFields();

            final Font font = workbook.createFont();
            font.setBold(true);
            cellStyle.setFont(font);
            // create title row
            Row titleRow = sheet.createRow(0);
            for (int i = 0; i < orderList.size(); i++) {
                int order = orderList.get(i);
                Cell titleCell = titleRow.createCell(i);
                titleCell.setCellValue(new XSSFRichTextString(columnNameMap.get(order)));
                titleCell.setCellStyle(cellStyle);
//                titleCell.getRichStringCellValue().applyFont(font);
            }
            rowCount = rowNumberToStartWith;

            // add rows data
            for (T aData : data) {
                Row row = sheet.createRow(++rowCount);
                for (int j = 0; j < orderedFields.size(); j++) {
                    try {
                        Field field = orderedFields.get(j);
                        boolean isAccessible = field.isAccessible();
                        field.setAccessible(true);
                        Object value = orderedFields.get(j).get(aData);
                        field.setAccessible(isAccessible);
                        row.createCell(j, CellType.STRING)
                                .setCellValue(value(field, value));
                    } catch (IllegalAccessException e) {
                        logger.error("", e);
                    }
                }
            }

            // write to file
            try (OutputStream outputStream = Files.newOutputStream(filePath)) {
                workbook.write(outputStream);
            } catch (IOException ioe) {
                // either file could not be created or could not be written to
                // delete the file and throw exception
                Files.deleteIfExists(filePath);
                throw ioe;
            }
        }
        return rowCount;
    }

    private static <T> ColumnsOrdersAndFields generateMetaDataForSheet(Class<T> clazz,
                                                                       LocalizationUtils localizationUtils, String language)
            throws RuntimeException {
        /* map of order and column name
         *  example: in a following sheet
         *
         *  | Sl No  |  Person Name  |
         *  --------------------------
         *  |   1    |   Ashok       |
         *  |   2    |   Kumar       |
         *  --------------------------
         *
         *  order = 1, columnName = Person Name
         */
        Map<Integer, String> orderAndColumnNameMap = new HashMap<>();
        /*
         * suppose class is Person
         *
         *    class Person {
         *       @ReportColumn(order = 1, columnName = "Sl No")
         *       private Integer slNo;
         *
         *       @ReportColumn(order = 2, columnName = "Person Name")
         *       private String personName;
         *    }
         *
         *    order = 1, field = java.lang.reflect.Field of Integer slNo
         *    order = 2, field = java.lang.reflect.Field of String personName
         *
         */
        Map<Integer, Field> orderAndFieldMap = new HashMap<>();
        List<Integer> orderList = new ArrayList<>();
        boolean isColumnChangeLocalizationWise = clazz.isAnnotationPresent(
                ConvertToLocalizedColumnString.class);
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(ReportColumn.class)) {
                ReportColumn reportColumn = field.getAnnotation(ReportColumn.class);
                int order = reportColumn.order();
                String columnNameAfterLocalization = isColumnChangeLocalizationWise ?
                        localizationUtils.getLocalizedValueAgainstKey(reportColumn.columnName(),
                                (language == null ? localizationUtils.getDefaultLanguage() : language)) : null;
                String columnName = isColumnChangeLocalizationWise &&
                        !StringUtils.isBlank(columnNameAfterLocalization) ? columnNameAfterLocalization
                        : reportColumn.columnName();
                if (orderAndColumnNameMap.containsKey(order)) {
                    throw new RuntimeException("No two columns can have same order");
                }
                if (order < 1) {
                    throw new RuntimeException("Order should be greater than 1");
                }
                if (ObjectUtils.isEmpty(columnName)) {
                    columnName = field.getName();
                }
                orderList.add(order);
                orderAndColumnNameMap.put(order, columnName);
                orderAndFieldMap.put(order, field);
            }
        }
        Collections.sort(orderList);
        List<Field> orderedFields = new ArrayList<>(orderAndFieldMap.size());

        for (int order : orderList) {
            Field f = orderAndFieldMap.get(order);
            orderedFields.add(f);
        }

        return new ColumnsOrdersAndFields(orderAndFieldMap,
                orderedFields,
                orderAndColumnNameMap,
                orderList
        );
    }

    private static <T> ColumnsOrdersAndFields generateMetaDataForSheet(Class<T> clazz)
            throws RuntimeException {

        Map<Integer, String> orderAndColumnNameMap = new HashMap<>();
        Map<Integer, Field> orderAndFieldMap = new HashMap<>();
        List<Integer> orderList = new ArrayList<>();
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(ReportColumn.class)) {
                ReportColumn reportColumn = field.getAnnotation(ReportColumn.class);
                int order = reportColumn.order();
                String columnName = reportColumn.columnName();
                if (orderAndColumnNameMap.containsKey(order)) {
                    throw new RuntimeException("No two columns can have same order");
                }
                if (order < 1) {
                    throw new RuntimeException("Order should be greater than 1");
                }
                if (ObjectUtils.isEmpty(columnName)) {
                    columnName = field.getName();
                }
                orderList.add(order);
                orderAndColumnNameMap.put(order, columnName);
                orderAndFieldMap.put(order, field);
            }
        }
        Collections.sort(orderList);
        List<Field> orderedFields = new ArrayList<>(orderAndFieldMap.size());

        for (int order : orderList) {
            Field f = orderAndFieldMap.get(order);
            orderedFields.add(f);
        }

        return new ColumnsOrdersAndFields(orderAndFieldMap,
                orderedFields,
                orderAndColumnNameMap,
                orderList
        );
    }
    private static WorkbookAndSheets createNewXls() throws IOException {
        SXSSFWorkbook workbook = new SXSSFWorkbook();
        SXSSFSheet sheet = workbook.createSheet(SHEET_NAME);
        return new WorkbookAndSheets(workbook, sheet);
    }

    @SuppressWarnings("Duplicates")
    private static String value(
            Field field,
            Object object
    ) {
        try {
            boolean isDateField = field.isAnnotationPresent(ReportSimpleDateFormat.class);
            if (isDateField && object instanceof Date) {
                String key = field.getAnnotation(ReportSimpleDateFormat.class).value();
                SimpleDateFormat simpleDateFormat;
                if (DATE_TIME_FORMATS_MAP.containsKey(key)) {
                    simpleDateFormat = DATE_TIME_FORMATS_MAP.get(key);
                } else {
                    simpleDateFormat = new SimpleDateFormat(key);
                    DATE_TIME_FORMATS_MAP.put(key, simpleDateFormat);
                }
                return simpleDateFormat.format((Date) object);
            }
            else {
                return object == null ? "" : object.toString();
            }
        } catch (Exception e) {
            logger.error("", e);
            return "";
        }
    }

    static <T> void convertToXls(
            FetchMoreDataInterface<T> fetchMoreDataInterface,
            Class<T> clazz,
            Path outputFilePathForXls,
            @Nullable String language,
            LocalizationUtils localizationUtils,
            boolean isScheduleEnabled
    ) throws RuntimeException, IOException {
        long start = System.currentTimeMillis();
        logger.debug("creating xls for {}", clazz.getSimpleName());
        WorkbookAndSheets workbookAndSheets = createNewXls(false, null);

        try (Workbook workbook = workbookAndSheets.getWorkbook()) {
            Sheet sheet = workbookAndSheets.getSheet();

            final Font font = new XSSFFont(CTFont.Factory.newInstance());
            font.setBold(true);

            ColumnsOrdersAndFields columnsOrdersAndFields = generateMetaDataForSheet(clazz,
                    localizationUtils, language);

            Map<Integer, String> columnNameMap = columnsOrdersAndFields.getOrderAndColumnNameMap();
            if(isScheduleEnabled && clazz.getSimpleName().equals("ContentPlaybackActuals")) {
                if(columnNameMap.containsKey(8) && columnNameMap.get(8).equals("Planogram Name")) {
                    columnNameMap.put(8, "Scheduler Name");
                }
            }
            List<Integer> orderList = columnsOrdersAndFields.getOrderList();
            List<Field> orderedFields = columnsOrdersAndFields.getOrderedFields();

            // create title row
            Row titleRow = sheet.createRow(0);
            for (int i = 0; i < orderList.size(); i++) {
                int order = orderList.get(i);
                Cell titleCell = titleRow.createCell(i);
                titleCell.setCellValue(new XSSFRichTextString(columnNameMap.get(order)));
                titleCell.getRichStringCellValue().applyFont(font);
            }

            int pageNumber = 1;
            String fileName = FilenameUtils.getName(outputFilePathForXls.toString());
            fetchMoreDataInterface.resetToStart();
            List<T> data = fetchMoreDataInterface.fetchMoreData(
                    pageNumber,
                    FETCH_MORE_RECORD_PER_ITERATION
            );
            long totalDataFetched = 0L;
            int rowCount = 0;
            while (data != null && !data.isEmpty()) {
                for (T aData : data) {
                    totalDataFetched = totalDataFetched + 1;
                    if (totalDataFetched % TEN_LAKH == 0) {
                        sheet = createNewSheetTab(workbookAndSheets, orderList, font, columnNameMap,
                                totalDataFetched);
                        rowCount = 0;
                    }
                    Row row = sheet.createRow(++rowCount);
                    for (int j = 0; j < orderedFields.size(); j++) {
                        try {
                            Field field = orderedFields.get(j);
                            boolean isAccessible = field.isAccessible();
                            field.setAccessible(true);
                            Object value = orderedFields.get(j).get(aData);
                            field.setAccessible(isAccessible);
                            row.createCell(j, CellType.STRING)
                                    .setCellValue(value(field, value, language, localizationUtils));
                        } catch (IllegalAccessException e) {
                            logger.error("error converting to xls", e);
                        }
                    }
                }
                pageNumber++;
                logger.debug("XLSX row count intermediate = {}", rowCount);
                data = fetchMoreDataInterface.fetchMoreData(
                        pageNumber,
                        FETCH_MORE_RECORD_PER_ITERATION
                );
            }

            logger.debug("XLSX row count finish = {}", rowCount);
            logger.debug("FILE GENERATION - looping complete writing to xls file, {}", fileName);

            // write to file
            try (OutputStream outputStream = Files.newOutputStream(outputFilePathForXls)) {
                workbook.write(outputStream);
            } catch (IOException ioe) {
                // either file could not be created or could not be written to
                // delete the file and throw exception
                Files.deleteIfExists(outputFilePathForXls);
                throw ioe;
            }
        }
        logger.debug("completed creating xls in {}ms", (System.currentTimeMillis() - start));
    }

    @SuppressWarnings("Duplicates")
    private static String value(
            Field field,
            Object object,
            @Nullable String language,
            LocalizationUtils localizationUtils
    ) {
        try {
            boolean isDateField = field.isAnnotationPresent(ReportSimpleDateFormat.class);
            boolean isTimeField = field.isAnnotationPresent(ReportTimeFormat.class);
            boolean shouldLocalizeString = field.isAnnotationPresent(ConvertToLocalizedString.class);
            boolean isChangeNewLine = field.isAnnotationPresent(NewLineConverter.class);
            boolean isReplaceZero = field.isAnnotationPresent(ReplaceZero.class);
            if (isChangeNewLine && object instanceof String && object != null) {
                return StringUtils.replace((String) object, ReportsUtils.DELIMITER, "\n\n");
            } else if (isTimeField && object instanceof String) {
                String timeObj = (String) object;
                ReportTimeFormat reportFormatTime = field.getAnnotation(ReportTimeFormat.class);
                int beginIndex = reportFormatTime.beginIndex();
                int lastIndex = reportFormatTime.lastIndex();
                if (beginIndex < lastIndex && lastIndex <= timeObj.length()) {
                    return timeObj.substring(beginIndex, lastIndex);
                } else if (beginIndex <= timeObj.length()) {
                    return timeObj.substring(beginIndex);
                } else {
                    return timeObj;
                }
            } else if (isDateField && object instanceof Date) {
                String key = field.getAnnotation(ReportSimpleDateFormat.class).value();
                SimpleDateFormat simpleDateFormat;
                if (DATE_TIME_FORMATS_MAP.containsKey(key)) {
                    simpleDateFormat = DATE_TIME_FORMATS_MAP.get(key);
                } else {
                    simpleDateFormat = new SimpleDateFormat(key);
                    DATE_TIME_FORMATS_MAP.put(key, simpleDateFormat);
                }
                return simpleDateFormat.format((Date) object);
            } else if (shouldLocalizeString && object instanceof String) {
                String key = (String) object;
                return localizationUtils.getLocalizedValueAgainstKey(key,
                        (language == null ? localizationUtils.getDefaultLanguage() : language));
            } else if (isReplaceZero && object != null && object instanceof Number) {
                if (object.equals(0)) {
                    return "-";
                } else {
                    return object.toString();
                }
            } else {
                return object == null ? "" : object.toString();
            }
        } catch (Exception e) {
            logger.error("", e);
            return "";
        }
    }

    private static final long TEN_LAKH = 1000000L;
    private static Sheet createNewSheetTab(
            WorkbookAndSheets workbookAndSheets,
            List<Integer> orderList,
            Font font,
            Map<Integer, String> columnNameMap,
            long totalDataFetched
    ) {
        long dataUpTo = totalDataFetched + TEN_LAKH;
        Sheet sheet = workbookAndSheets.getWorkbook()
                .createSheet("Report " + totalDataFetched + " - " + dataUpTo);
        Row titleRow = sheet.createRow(0);
        for (int i = 0; i < orderList.size(); i++) {
            int order = orderList.get(i);
            Cell titleCell = titleRow.createCell(i);
            titleCell.setCellValue(new XSSFRichTextString(columnNameMap.get(order)));
            titleCell.getRichStringCellValue().applyFont(font);
        }
        return sheet;
    }

    private static WorkbookAndSheets createNewXls(
            boolean isChildSheetRequired,
            String childSheetName
    ) throws IOException {
        SXSSFWorkbook workbook = new SXSSFWorkbook();
        SXSSFSheet sheet = workbook.createSheet(SHEET_NAME);
        SXSSFSheet childSheet = null;
        if (isChildSheetRequired) {
            childSheet = workbook.createSheet(childSheetName);
        }
        return new WorkbookAndSheets(workbook, sheet);
    }

}
