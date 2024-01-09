package com.digital.signage.report;
import com.digital.signage.annotations.PdfTitle;
import com.digital.signage.annotations.ReportTimeFormat;
import com.digital.signage.dto.DeviceLogReportRequestDTO;
import com.digital.signage.dto.DmbReportResponseData;
import com.digital.signage.util.*;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.digital.signage.annotations.PdfColumn;
import com.digital.signage.annotations.ReportSimpleDateFormat;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.util.ObjectUtils;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

import static com.digital.signage.util.ReportConstants.FETCH_MORE_RECORD_PER_ITERATION;

public class JsonArrayToPdf {
    private static final Logger logger = LoggerFactory.getLogger(JsonArrayToPdf.class);
    private static final Font TITLE_FONT = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD);
    private static final Font HEADER_FONT =
            new Font(Font.FontFamily.TIMES_ROMAN, Font.DEFAULTSIZE, Font.BOLD);
    private static final Font CELL_FONT =
            new Font(Font.FontFamily.TIMES_ROMAN, Font.DEFAULTSIZE, Font.NORMAL);
    private static final float LEFT = 20;
    private static final float RIGHT = 20;
    private static final float TOP = 15;
    private static final float BOTTOM = 45;

    public static <T> void convertToPdf(
            List<T> data,
            Class<T> clazz,
            Path outputFilePathForPdf
    ) throws ServerPDFException, IOException {
        PdfInProgress pdfInProgress = null;
        try (FileOutputStream fileOutputStream = new FileOutputStream(outputFilePathForPdf.toFile())) {
            try {
                pdfInProgress = open(clazz, fileOutputStream);
                writeDataInDocument(
                        pdfInProgress,
                        data
                );
            } catch (DocumentException e) {
                throw new ServerPDFException(e);
            } finally {
                close(pdfInProgress);
            }
        }
    }
    static <T> void convertToPdf(
            Class<T> clazz,
            Path outputFilePathForPdf,
            FetchMoreDataInterface<T> fetchMoreDataInterface,
            @Nullable String language,
            LocalizationUtils localizationUtils,
            boolean isScheduleEnabled
    ) throws ServerPDFException, IOException {
        PdfInProgress pdfInProgress = null;
        String fileName = FilenameUtils.getName(outputFilePathForPdf.toString());
        try (FileOutputStream fileOutputStream = new FileOutputStream(outputFilePathForPdf.toFile())) {
            try {
                pdfInProgress = open(clazz, fileOutputStream, localizationUtils, language);
                int pageNumber = 1;
                fetchMoreDataInterface.resetToStart();
                List<T> data =
                        fetchMoreDataInterface.fetchMoreData(pageNumber, FETCH_MORE_RECORD_PER_ITERATION);
                int rowCount = 0;
                while (data != null && !data.isEmpty()) {
                    logger.debug(
                            "FILE GENERATION - iterating over data. data.size() = {}, reportFileName = {}",
                            data.size(),
                            fileName
                    );
                    rowCount = rowCount + data.size();
                    logger.debug("PDF row count intermediate = {}", rowCount);
                    writeDataInDocument(
                            pdfInProgress,
                            data,
                            language,
                            localizationUtils,
                            isScheduleEnabled,
                            clazz
                    );
                    pageNumber++;
                    data =
                            fetchMoreDataInterface.fetchMoreData(pageNumber, FETCH_MORE_RECORD_PER_ITERATION);
                }
                logger.debug("PDF row count finish = {}", rowCount);
                logger.debug("FILE GENERATION - looping complete writing to pdf file, {}", fileName);
            } catch (DocumentException e) {
                throw new ServerPDFException(e);
            } finally {
                close(pdfInProgress);
                logger.debug("FILE GENERATION - closing pdf stream, {}", fileName);
            }
        }
    }

    private static <T> void writeDataInDocument(
            PdfInProgress pdfInProgress,
            List<T> data,
            @Nullable String language,
            LocalizationUtils localizationUtils,
            boolean isScheduleEnabled,
            Class<T> clazz
    ) throws ServerPDFException {
        if (data == null || data.isEmpty()) {
            // no data. No report. sorry
            return;
        }
        assert pdfInProgress.getDocument() != null;
        Map<Integer, String> columnNameMap =
                pdfInProgress.getColumnsOrdersAndFields().getOrderAndColumnNameMap();
        if(isScheduleEnabled && clazz.getSimpleName().equals("ContentPlaybackActuals")) {
            if(columnNameMap.containsKey(8) && columnNameMap.get(8).equals("Planogram Name")) {
                columnNameMap.put(8, "Scheduler Name");
            }
        }
        Map<Integer, Field> fieldMap = pdfInProgress.getColumnsOrdersAndFields().getOrderAndFieldMap();
        List<Integer> orderList = pdfInProgress.getColumnsOrdersAndFields().getOrderList();
        Collections.sort(orderList);
        List<Field> orderedFields = new ArrayList<>(fieldMap.size());
        PdfPTable table = new PdfPTable(orderList.size());
        table.setWidthPercentage(100);
        // create title row
        for (int order : orderList) {
            Field f = fieldMap.get(order);
            orderedFields.add(f);
            table.addCell(getHeaderCell(columnNameMap.get(order)));
        }
        //create data row
        for (T aData : data) {
            for (Field field : orderedFields) {
                try {
                    boolean isAccessible = field.isAccessible();
                    field.setAccessible(true);
                    Object value = field.get(aData);
                    field.setAccessible(isAccessible);
                    table.addCell(getValueCell(value(
                            field,
                            value,
                            language,
                            localizationUtils
                    )));
                } catch (IllegalAccessException e) {
                    logger.error("", e);
                }
            }
        }
        try {
            pdfInProgress.getDocument().add(table);
        } catch (DocumentException e) {
            throw new ServerPDFException(e);
        }
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
            boolean isTimeFiled = field.isAnnotationPresent(ReportTimeFormat.class);
            boolean shouldLocalizeString = field.isAnnotationPresent(ConvertToLocalizedString.class);
            boolean isChangeNewLine = field.isAnnotationPresent(NewLineConverter.class);
            boolean isReplaceZero = field.isAnnotationPresent(ReplaceZero.class);
            if (isChangeNewLine && object instanceof String && object != null) {
                return StringUtils.replace((String) object, ReportsUtils.DELIMITER, "\n\n");
            } else if (isTimeFiled && object instanceof String) {
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
                SimpleDateFormat simpleDateFormat =
                        new SimpleDateFormat(field.getAnnotation(ReportSimpleDateFormat.class).value());
                return simpleDateFormat.format((Date) object);
            } else if (shouldLocalizeString && object instanceof String) {
                String key = (String) object;
                return localizationUtils.getLocalizedValueAgainstKey(key,
                        (language == null ? localizationUtils.getDefaultLanguage() : language));
            } else if (isReplaceZero && object instanceof Number) {
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

    private static <T> void writeDataInDocument(
            PdfInProgress pdfInProgress,
            List<T> data
    ) throws ServerPDFException {
        if (data == null || data.isEmpty()) {
            // no data. No report. sorry
            return;
        }
        assert pdfInProgress.getDocument() != null;
        Map<Integer, String> columnNameMap =
                pdfInProgress.getColumnsOrdersAndFields().getOrderAndColumnNameMap();
        Map<Integer, Field> fieldMap = pdfInProgress.getColumnsOrdersAndFields().getOrderAndFieldMap();
        List<Integer> orderList = pdfInProgress.getColumnsOrdersAndFields().getOrderList();
        Collections.sort(orderList);
        List<Field> orderedFields = new ArrayList<>(fieldMap.size());
        PdfPTable table = new PdfPTable(orderList.size());
        table.setWidthPercentage(100);
        // create title row
        for (int order : orderList) {
            Field f = fieldMap.get(order);
            orderedFields.add(f);
            table.addCell(getHeaderCell(columnNameMap.get(order)));
        }
        //create data row
        for (T aData : data) {
            for (Field field : orderedFields) {
                try {
                    boolean isAccessible = field.isAccessible();
                    field.setAccessible(true);
                    Object value = field.get(aData);
                    field.setAccessible(isAccessible);
                    table.addCell(getValueCell(value(
                            field,
                            value
                    )));
                } catch (IllegalAccessException e) {
                    logger.error("", e);
                }
            }
        }
        try {
            pdfInProgress.getDocument().add(table);
        } catch (DocumentException e) {
            throw new ServerPDFException(e);
        }
    }

    @SuppressWarnings("Duplicates")
    private static String value(
            Field field,
            Object object
    ) {
        try {
            boolean isDateField = field.isAnnotationPresent(ReportSimpleDateFormat.class);
            if (isDateField && object instanceof Date) {
                SimpleDateFormat simpleDateFormat =
                        new SimpleDateFormat(field.getAnnotation(ReportSimpleDateFormat.class).value());
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

    static PdfPCell getValueCell(String string) {
        if (string == null || "".equals(string)) {
            return new PdfPCell();
        }
        PdfPCell cell = new PdfPCell(new Phrase(string, CELL_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        return cell;
    }

    static PdfPCell getHeaderCell(String string) {
        if (string == null || "".equals(string)) {
            return new PdfPCell();
        }
        PdfPCell cell = new PdfPCell(new Phrase(string, HEADER_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        return cell;
    }

    private static <T> PdfInProgress open(
            Class<T> clazz,
            FileOutputStream fileOutputStream
    ) throws DocumentException {
        int noOfColumn = countNoOfColumns(clazz);
        if (noOfColumn == 0) {
            throw new ServerPDFException("Number of columns cannot be zero");
        }
        String title = findReportTitle(clazz);
        logger.debug("No of column in pdf :: {}, title :: {}", noOfColumn, title);
        PdfDocumentTitle pdfDocumentTitle =
                createDocumentAndMapColumn(noOfColumn, title, fileOutputStream);
        ColumnsOrdersAndFields columnsOrdersAndFields =
                getAllPdfColumn(clazz);
        return new PdfInProgress(
                columnsOrdersAndFields,
                pdfDocumentTitle.getDocument(),
                pdfDocumentTitle.getPdfWriter()
        );
    }

    private static <T> ColumnsOrdersAndFields getAllPdfColumn(
            Class<T> clazz
    ) {
        Map<Integer, String> columnNameMap = new HashMap<>();
        Map<Integer, Field> fieldMap = new HashMap<>();
        List<Integer> orderList = new ArrayList<>();
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(PdfColumn.class)) {
                PdfColumn reportColumn = field.getAnnotation(PdfColumn.class);
                int order = reportColumn.order();
                String columnName = reportColumn.columnName();
                if (columnNameMap.containsKey(order)) {
                    throw new RuntimeException("No two columns can have same order");
                }
                if (order < 1) {
                    throw new RuntimeException("Order should be greater than 1");
                }
                if (ObjectUtils.isEmpty(columnName)) {
                    columnName = field.getName();
                }
                orderList.add(order);
                columnNameMap.put(order, columnName);
                fieldMap.put(order, field);
            }
        }
        return new ColumnsOrdersAndFields(
                fieldMap,
                new ArrayList<>(0),
                columnNameMap,
                orderList
        );
    }
    private static void close(@Nullable PdfInProgress pdfInProgress) {
        if (pdfInProgress != null
                && pdfInProgress.getDocument() != null
                && pdfInProgress.getDocument().isOpen()) {
            pdfInProgress.getDocument().close();
        }
    }

    private static PdfDocumentTitle createDocumentAndMapColumn(
            int noOfColumn,
            String title,
            FileOutputStream fileOutputStream
    ) throws DocumentException {
        Rectangle pageSize;
        if (noOfColumn <= 9) {
            pageSize = PageSize.A4;
        } else if (noOfColumn > 18) {
            pageSize = PageSize.A1;
            pageSize = pageSize.rotate();
        } else {
            pageSize = PageSize.A2;
        }
        Document document =
                new Document(pageSize, LEFT, RIGHT, TOP, BOTTOM);
        PdfWriter pdfWriter = PdfWriter.getInstance(document, fileOutputStream);
        pdfWriter.setCloseStream(false);
        pdfWriter.createXmpMetadata();
        document.setMargins(LEFT, RIGHT, 10, BOTTOM);
        pdfWriter.setBoxSize("art", PageSize.A4);
        document.open();
        document.addCreator("Panasonic User");
        document.addCreationDate();
        document.addTitle(title);
        document.newPage();
        pdfWriter.setPageEvent(new HeaderFooterPageEvent());
        addDocumentTitle(document, title);
        return new PdfDocumentTitle(document, pdfWriter);
    }

    private static void addDocumentTitle(Document document, String title)
            throws DocumentException {
        addEmptyLine(document, 2);
        PdfPTable titleTable = new PdfPTable(1);
        titleTable.setWidthPercentage(100);
        PdfPCell titleCell = getTitleHeaderCell(title);
        titleCell.setBorder(Rectangle.NO_BORDER);
        titleTable.addCell(titleCell);
        document.add(titleTable);
        addEmptyLine(document, 2);
    }
    private static void addEmptyLine(Document document, int noOfEmptyLine) throws DocumentException {
        for (int i = 1; i < noOfEmptyLine; i++) {
            document.add(new Phrase(Chunk.NEWLINE));
        }
    }
    private static PdfPCell getTitleHeaderCell(String string) {
        if ("".equals(string)) {
            return new PdfPCell();
        }
        PdfPCell cell = new PdfPCell(new Phrase(string, TITLE_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        return cell;
    }

    private static <T> String findReportTitle(Class<T> tClass) {
        PdfTitle pdfTitle = tClass.getAnnotation(PdfTitle.class);
        if (pdfTitle != null && pdfTitle.title() != null) {
            return pdfTitle.title();
        }
        return "";
    }

    private static <T> int countNoOfColumns(Class<T> tClass) {
        int counter = 0;
        for (Field field : tClass.getDeclaredFields()) {
            if (field.isAnnotationPresent(PdfColumn.class)) {
                counter++;
            }
        }
        return counter;
    }
    public static class ServerPDFException extends RuntimeException {
        public ServerPDFException(Throwable t) {
            super(t);
        }

        public ServerPDFException(String error) {
            super(error);
        }
    }

    private static class HeaderFooterPageEvent extends PdfPageEventHelper {

        @Override
        public void onStartPage(PdfWriter writer, Document document) {
            ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER,
                    new Phrase(""), 30, 800, 0);
            ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER,
                    new Phrase(""), 550, 800, 0);
        }

        @Override
        public void onEndPage(PdfWriter writer, Document document) {
            ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER,
                    new Phrase(DateUtils.formattedDateForPdfReport()), 110, 30, 0);
            ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER,
                    new Phrase("page " + document.getPageNumber()), 550, 30, 0);
        }
    }

    public static void convertDmbReportToPdf(
            List<DmbReportResponseData> data,
            Path outputFilePathForPdf,
            @Nullable String language,
            DeviceLogReportRequestDTO deviceLogReportRequestDTO,
            LocalizationUtils localizationUtils,
            DmbXlsReportHelper.LocationNameById locationNameById,
            DmbXlsReportHelper.LocalizedMessage localizedMessage
    ) throws ServerPDFException, IOException {
        PdfInProgress pdfInProgress = null;
        try (FileOutputStream fileOutputStream = new FileOutputStream(outputFilePathForPdf.toFile())) {
            try {
                String title =
                        getDmbReportTitle(deviceLogReportRequestDTO, locationNameById, localizedMessage);
                PdfDocumentTitle pdfDocumentTitle =
                        createDocumentAndMapColumn(28, title, fileOutputStream);
                pdfInProgress = new PdfInProgress(
                        getAllPdfColumn(DmbReportResponseData.class, localizationUtils, language),
                        pdfDocumentTitle.getDocument(),
                        pdfDocumentTitle.getPdfWriter()
                );
                DmbPdfReportHelper.writeReportDataInDocument(
                        pdfInProgress,
                        data
                );
            } catch (DocumentException e) {
                throw new ServerPDFException(e);
            } finally {
                close(pdfInProgress);
            }
        }
    }

    private static String getDmbReportTitle(
            DeviceLogReportRequestDTO deviceLogReportRequestDTO,
            DmbXlsReportHelper.LocationNameById locationNameById,
            DmbXlsReportHelper.LocalizedMessage localizedMessage
    ) {
        String header;
        if (Objects.nonNull(deviceLogReportRequestDTO.getLocationId())) {
            String locationName =
                    locationNameById.getLocationNameById(deviceLogReportRequestDTO.getLocationId());

            header = localizedMessage.getLocalizedMessage(
                    Message.MediaPlayerSummaryReport.MEDIA_PLAYER_SUMMARY_REPORT_TITLE_WITH_LOCATION,
                    DateTimeUtilsKt.dateFromDateTime(deviceLogReportRequestDTO.getFromDate()),
                    locationName
            );
        } else {
            header = localizedMessage.getLocalizedMessage(
                    Message.MediaPlayerSummaryReport.MEDIA_PLAYER_SUMMARY_REPORT_TITLE,
                    DateTimeUtilsKt.dateFromDateTime(deviceLogReportRequestDTO.getFromDate()));
        }
        return header;
    }

    private static <T> ColumnsOrdersAndFields getAllPdfColumn(
            Class<T> clazz,
            LocalizationUtils localizationUtils,
            String language
    ) {
        Map<Integer, String> columnNameMap = new HashMap<>();
        Map<Integer, Field> fieldMap = new HashMap<>();
        List<Integer> orderList = new ArrayList<>();
        boolean shouldColumnChangeAsLocalization = clazz.isAnnotationPresent(
                ConvertToLocalizedColumnString.class);
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(PdfColumn.class)) {
                PdfColumn reportColumn = field.getAnnotation(PdfColumn.class);
                int order = reportColumn.order();
                String colAfterLocalization = shouldColumnChangeAsLocalization ?
                        localizationUtils.getLocalizedValueAgainstKey(reportColumn.columnName(),
                                (language == null ? localizationUtils.getDefaultLanguage() : language)) : null;

                String columnName =
                        shouldColumnChangeAsLocalization && !StringUtils.isBlank(colAfterLocalization)
                                ? colAfterLocalization : reportColumn.columnName();
                if (columnNameMap.containsKey(order)) {
                    throw new RuntimeException("No two columns can have same order");
                }
                if (order < 1) {
                    throw new RuntimeException("Order should be greater than 1");
                }
                if (ObjectUtils.isEmpty(columnName)) {
                    columnName = field.getName();
                }
                orderList.add(order);
                columnNameMap.put(order, columnName);
                fieldMap.put(order, field);
            }
        }
        return new ColumnsOrdersAndFields(
                fieldMap,
                new ArrayList<>(0),
                columnNameMap,
                orderList
        );
    }

    private static <T> PdfInProgress open(
            Class<T> clazz,
            FileOutputStream fileOutputStream,
            LocalizationUtils localizationUtils,
            String language
    ) throws DocumentException {
        int noOfColumn = countNoOfColumns(clazz);
        if (noOfColumn == 0) {
            throw new ServerPDFException("Number of columns cannot be zero");
        }
        String title = findReportTitle(clazz, localizationUtils, language);
        logger.debug("No of column in pdf :: {}, title :: {}", noOfColumn, title);
        PdfDocumentTitle pdfDocumentTitle =
                createDocumentAndMapColumn(noOfColumn, title, fileOutputStream);
        ColumnsOrdersAndFields columnsOrdersAndFields =
                getAllPdfColumn(clazz, localizationUtils, language);
        return new PdfInProgress(
                columnsOrdersAndFields,
                pdfDocumentTitle.getDocument(),
                pdfDocumentTitle.getPdfWriter()
        );
    }

    private static <T> String findReportTitle(Class<T> tClass,
                                              LocalizationUtils localizationUtils, String language) {
        PdfTitle pdfTitle = tClass.getAnnotation(PdfTitle.class);
        if (pdfTitle != null && pdfTitle.title() != null) {
            String titleAfterLocalization =
                    localizationUtils.getLocalizedValueAgainstKey(pdfTitle.title(),
                            (language == null ? localizationUtils.getDefaultLanguage() : language));
            return StringUtils.isBlank(titleAfterLocalization) ? pdfTitle.title()
                    : titleAfterLocalization;
        }
        return "";
    }

}
