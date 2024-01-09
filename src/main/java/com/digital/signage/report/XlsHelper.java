package com.digital.signage.report;


import com.digital.signage.configuration.ApplicationProperties;
import com.digital.signage.dto.BaseReportRequestDTO;
import com.digital.signage.dto.DeviceLogReportRequestDTO;
import com.digital.signage.dto.DmbReportPaths;
import com.digital.signage.dto.DmbReportResponseData;
import com.digital.signage.models.ReportCacheEntity;
import com.digital.signage.report.S3.XlsHelperInterface;
import com.digital.signage.repository.LocationRepository;
import com.digital.signage.repository.ReportCacheRepository;
import com.digital.signage.util.LocalizationUtils;
import com.digital.signage.util.Message;
import com.digital.signage.util.ReportType;
import com.google.common.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static com.digital.signage.annotations.ReportConstants.REPORTS_DIR;
import static com.digital.signage.util.ApplicationConstants.PDF_FILE_EXTENSION;
import static com.digital.signage.util.ApplicationConstants.SPREADSHEET_FILE_EXTENSION;

@Component
@Slf4j
public class XlsHelper {

    private final ApplicationProperties properties;
    private final Gson gson;
    private final XlsHelperInterface xlsHelperInterface;
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private Message message;
    @Autowired
    private LocalizationUtils localizationUtils;
    @Autowired
    ApplicationProperties applicationProperties;
    @Autowired
    private ReportCacheRepository reportCacheRepository;
    public enum GenerateReportType {PDF, XLS, BOTH}



    public XlsHelper(ApplicationProperties properties, Gson gson, XlsHelperInterface xlsHelperInterface) {
        this.properties = properties;
        this.gson = gson;
        this.xlsHelperInterface = xlsHelperInterface;
    }

    public String generateReportTokenOnly() {
        return UUID.randomUUID().toString();
    }
    private Path getReportJsonFilePath(String reportToken) {
        return Paths.get(applicationProperties.getStorageDirectory(), REPORTS_DIR, reportToken + ".json");
    }

    public <T> CachedReport<T, BaseReportRequestDTO> cacheReportAndGenerateXls(
            List<T> data,
            Class<T> classOfT
    ) throws IOException {
        if (data == null || data.isEmpty()) {
            return null;
        }
        String reportToken = UUID.randomUUID().toString();
        generateXlsReport(data, reportToken, classOfT);
        generatePdfReport(data, reportToken, classOfT);
        return generateXlsAndPdfDownloadableLink(data, reportToken);
    }

    public <T, R> CachedReport<T, R> cacheReportAndGenerateXls(
            String reportToken,
            FetchMoreDataInterface<T> fetchMoreDataInterface,
            Class<T> classOfT,
            R requestDTO
    ) throws IOException {
        return cacheReportAndGenerateXls(
                reportToken,
                fetchMoreDataInterface,
                classOfT,
                requestDTO,
                null,
                GenerateReportType.BOTH,
                false
        );
    }

    public <T, R> CachedReport<T, R> cacheReportAndGenerateXls(
            String reportToken,
            FetchMoreDataInterface<T> fetchMoreDataInterface,
            Class<T> classOfT,
            R requestDTO,
            @Nullable String language,
            GenerateReportType generateReportType,
            boolean isScheduleReport
    ) throws IOException {
        final boolean isBothReportRequired = GenerateReportType.BOTH.equals(generateReportType);
        final boolean isExcelFileRequired =
                isBothReportRequired || GenerateReportType.XLS.equals(generateReportType);
        final boolean isPdfFileRequired =
                isBothReportRequired || GenerateReportType.PDF.equals(generateReportType);
        //Going to generate Xls report
        if (isExcelFileRequired) {
            generateXls(
                    reportToken,
                    fetchMoreDataInterface,
                    classOfT,
                    XlsHelperInterface.ForReportOrForAdvanceSearch.FOR_REPORT,
                    language,
                    isScheduleReport
            );
        }
        //Going to generate Pdf report
        if (isPdfFileRequired) {
            generatePdfReport(
                    reportToken,
                    fetchMoreDataInterface,
                    classOfT,
                    language,
                    isScheduleReport
            );
        }
        return generateJsonAndCacheOnly(null, reportToken, classOfT, requestDTO);
    }

    private <T> void generatePdfReport(
            String reportToken,
            FetchMoreDataInterface<T> fetchMoreDataInterface,
            Class<T> classOfT,
            @Nullable String language,
            boolean isScheduleReport
    ) throws IOException {
        generatePdfReport(
                reportToken,
                fetchMoreDataInterface,
                classOfT,
                XlsHelperInterface.ForReportOrForAdvanceSearch.FOR_REPORT,
                language,
                isScheduleReport
        );
    }
    private <T> void generatePdfReport(
            String reportToken,
            FetchMoreDataInterface<T> fetchMoreDataInterface,
            Class<T> classOfT,
            XlsHelperInterface.ForReportOrForAdvanceSearch forReportOrForAdvanceSearch,
            @Nullable String language,
            boolean isScheduleReport
    ) throws IOException {
        Path pdfReportPath = getPdfReportPath(reportToken);
        JsonArrayToPdf.convertToPdf(
                classOfT,
                pdfReportPath,
                fetchMoreDataInterface,
                language,
                localizationUtils,
                isScheduleReport
        );
        //S3 Integration For PDF Report
        xlsHelperInterface.uploadPdfFile(
                pdfReportPath,
                null,
                reportToken,
                forReportOrForAdvanceSearch,
                "pdf"
        );
    }

    private <T> void generatePdfReport(List<T> data, String reportToken, Class<T> classOfT) throws IOException {
        Path pdfReportPath = getPdfReportPath(reportToken);
        JsonArrayToPdf.convertToPdf(
                data,
                classOfT,
                pdfReportPath
        );
        //S3 Integration For PDF Report
        xlsHelperInterface.uploadPdfFile(
                pdfReportPath,
                null,
                reportToken,
                XlsHelperInterface.ForReportOrForAdvanceSearch.FOR_REPORT,
                "pdf");
    }

    private <T> void generateXlsReport(List<T> data, String reportToken, Class<T> classOfT) throws IOException {
        Path xlsReportPath = getXlsReportPath(reportToken);
        JsonArrayToXls.convertToXls(
                data,
                classOfT,
                xlsReportPath,
                0
        );
        //S3 Integration For Xls Report
        xlsHelperInterface.uploadXlsFile(
                xlsReportPath,
                null,
                reportToken,
                XlsHelperInterface.ForReportOrForAdvanceSearch.FOR_REPORT,
                "xls"
        );
    }

    private <T> CachedReport<T, BaseReportRequestDTO> generateXlsAndPdfDownloadableLink(
            List<T> data,
            String reportToken
    ) throws IOException {
        CachedReport<T, BaseReportRequestDTO> cr = new CachedReport<>(data, reportToken, true);
        cr.setDownloadbleLinkToXlsReport(getDownloadableLinkToXlsReport(reportToken));
        cr.setDownloadableLinkToPdfReport(getDownloadableLinkToPdfReport(reportToken));
        return cr;
    }

    public String getDownloadableLinkToXlsReport(String reportToken) throws IOException {
        return xlsHelperInterface.generateReportDownloadableLink(reportToken);
    }

    public String getDownloadableLinkToPdfReport(String reportToken) throws IOException {
        return xlsHelperInterface.generatePdfReportDownloadableLink(reportToken);
    }

    private Path getXlsReportPath(String reportToken) {
        String rootStorageDir = properties.getStorageDirectory();
        return Paths.get(rootStorageDir, REPORTS_DIR, reportToken + SPREADSHEET_FILE_EXTENSION);
    }

    private Path getPdfReportPath(String reportToken) {
        String rootStorageDir = properties.getStorageDirectory();
        return Paths.get(rootStorageDir, REPORTS_DIR, reportToken + PDF_FILE_EXTENSION);
    }
    public DmbReportPaths dmb(
            String reportToken,
            List<DmbReportResponseData> data,
            @Nullable String language,
            DeviceLogReportRequestDTO deviceLogReportRequestDTO
    ) throws IOException {
        Path xlsReportPath = getXlsReportPath(reportToken);
        Path pdfReportPath = getPdfReportPath(reportToken);
        DmbXlsReportHelper.generateXLSSheet(
                data,
                "MP Summary Report",
                xlsReportPath,
                deviceLogReportRequestDTO,
                locationId -> locationRepository.getLocationNameByLocationId(locationId),
                (key, params) -> message.get(key, params)
        );
        JsonArrayToPdf.convertDmbReportToPdf(
                data,
                pdfReportPath,
                language,
                deviceLogReportRequestDTO,
                localizationUtils,
                locationId -> locationRepository.getLocationNameByLocationId(locationId),
                (key, params) -> message.get(key, params)
        );
        return new DmbReportPaths(xlsReportPath, pdfReportPath);
    }

    public <R> CachedReport<DmbReportResponseData, R> cacheDmbReportAndGenerateXls(
            String reportToken,
            List<DmbReportResponseData> data,
            DmbReportPaths dmbReportPaths,
            R requestDTO
    ) throws IOException {
        generateDmbReportXls(reportToken, dmbReportPaths.getXlsReportPath());
        //Going to generate Pdf report
        generateDmbReportPdf(dmbReportPaths.getPdfReportPath(), reportToken);
        return generateJsonAndCacheOnly(data, reportToken, DmbReportResponseData.class, requestDTO);
    }

    public <T> void generateDmbReportPdf(
            Path pdfReportPath,
            String reportToken
    ) throws IOException {
        xlsHelperInterface.uploadPdfFile(
                pdfReportPath,
                null,
                reportToken,
                XlsHelperInterface.ForReportOrForAdvanceSearch.FOR_REPORT,
                "pdf"
        );
    }
    public String generateDmbReportXls(
            String reportToken,
            Path xlsReportPath
    ) throws IOException {
        xlsHelperInterface.uploadXlsFile(xlsReportPath, null, reportToken,
                XlsHelperInterface.ForReportOrForAdvanceSearch.FOR_REPORT, SPREADSHEET_FILE_EXTENSION);
        return getDownloadableLinkToXlsReport(reportToken);
    }

    private <T, R> CachedReport<T, R> generateJsonAndCacheOnly(
            List<T> data,
            String reportToken,
            Class<T> classOfT,
            R requestDTO
    ) throws IOException {
        /* store report cache */
        Path jsonFilePath = getReportJsonFilePath(reportToken);
        data = data == null ? new ArrayList<>(0) : data;
        //Going to generate json file.
        String json = gson.toJson(data);
        FileUtils.writeStringToFile(new File(jsonFilePath.toString()), json, StandardCharsets.UTF_8);
        //going to save in reportCache
        createReportCacheEntity(classOfT, requestDTO, reportToken);
        if (jsonFilePath.toFile().exists()) {
            xlsHelperInterface.uploadJsonFile(jsonFilePath, reportToken);
        }
        CachedReport<T, R> cr = new CachedReport<>(data, json, reportToken, true);
        cr.setDownloadbleLinkToXlsReport(getDownloadableLinkToXlsReport(reportToken));
        cr.setDownloadableLinkToPdfReport(getDownloadableLinkToPdfReport(reportToken));
        return cr;
    }

    private <R, T> void createReportCacheEntity(Class<T> classOfT, R requestDTO, String reportToken) {
        ReportCacheEntity reportCacheEntity = new ReportCacheEntity();
        reportCacheEntity.setReportToken(reportToken);
        reportCacheEntity.setTimeOfReportGeneration(new Date());
        reportCacheEntity.setRequestObjectJson(gson.toJson(requestDTO));
        // Get repository class name and save in database in repositoryClassName;
        reportCacheEntity.setReportType(ReportType.getReportTypeFromClass(classOfT));
        reportCacheEntity.setIsReportCompleted(true);
        reportCacheRepository.save(reportCacheEntity);
    }

    public <T, R> CachedReport<T, R> fetchReportFromCache(
            String reportToken,
            Class<T> classOfResponseDTO,
            R requestDTO,
            Class<R> classOfRequestDTO
    ) throws IOException {
        if (reportToken == null || reportToken.isEmpty()) return null;
        // check if report is there in cache
        ReportCacheEntity reportCache = reportCacheRepository.findByReportToken(reportToken);
        if (reportCache == null) {
            // report not in cache so return null
            return null;
        }
        // check if request DTO matches cached one
        if (reportCache.getRequestObjectJson() != null) {
            R cachedRequest = gson.fromJson(reportCache.getRequestObjectJson(), classOfRequestDTO);
            if (cachedRequest != null && cachedRequest.equals(requestDTO)) {
                // cached request matches

                String json = null;
                try {
                    json = xlsHelperInterface.getJsonAsAString(reportToken);
                } catch (Exception e) {
//                    logger.error("", e);
                }
                List<T> data = gson.fromJson(json, new TypeToken<List<T>>() {
                }.getType());
                data = data == null ? new ArrayList<>(0) : data;
                CachedReport<T, R> cr = new CachedReport<>(data, json, reportToken,// reportPath,
                        reportCache.getIsReportCompleted());
                cr.setDownloadbleLinkToXlsReport(getDownloadableLinkToXlsReport(reportToken));
                cr.setDownloadableLinkToPdfReport(getDownloadableLinkToPdfReport(reportToken));
                return cr;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public <T> void generateXls(
            String reportToken,
            FetchMoreDataInterface<T> fetchMoreDataInterface,
            Class<T> classOfT,
            XlsHelperInterface.ForReportOrForAdvanceSearch forReportOrForAdvanceSearch,
            @Nullable String language,
            boolean isScheduleReport
    ) throws IOException {
        Path xlsReportPath = getXlsReportPath(reportToken);
        JsonArrayToXls.convertToXls(
                fetchMoreDataInterface,
                classOfT,
                xlsReportPath,
                language,
                localizationUtils,
                isScheduleReport
        );
        xlsHelperInterface.uploadXlsFile(
                xlsReportPath,
                null,
                reportToken,
                forReportOrForAdvanceSearch,
                SPREADSHEET_FILE_EXTENSION
        );
    }

}
