package com.digital.signage.report.S3;


import com.digital.signage.configuration.ApplicationProperties;
import com.digital.signage.report.S3Service;
import com.digital.signage.report.FileUploadFailedException;
import com.digital.signage.report.SignedUrlGenerationFailedException;
import org.apache.commons.io.FileUtils;
import org.checkerframework.checker.units.qual.A;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.digital.signage.annotations.ReportConstants.REPORTS_DIR;
import static com.digital.signage.util.ApplicationConstants.SPREADSHEET_FILE_EXTENSION;

@Service
public class XlsHelperInterfaceForS3 implements XlsHelperInterface {
    private static final String REPLACE_REPORT_TOKEN = "{reportToken}";
    private static final String S3_REPORT_KEY = "reports/" + REPLACE_REPORT_TOKEN;
    private static final String S3_REPORT_JSON_KEY = "reports/json/" + REPLACE_REPORT_TOKEN;
    private static final String S3_REPORT_PDF_KEY = "reports/pdf/" + REPLACE_REPORT_TOKEN;
    private static final String S3_SEARCH_PDF_KEY = "/advance/search/pdf/" + REPLACE_REPORT_TOKEN;
    private static final String S3_SEARCH_XLS_KEY = "/advance/search/xls/" + REPLACE_REPORT_TOKEN;
    private static final Logger logger = LoggerFactory.getLogger(XlsHelperInterfaceForS3.class);

    @Autowired
    private ApplicationProperties applicationProperties;
    @Autowired
    private S3Service s3Services;

    private static String getPdfFileKey(String reportToken) {
        return S3_REPORT_PDF_KEY.replace(REPLACE_REPORT_TOKEN, reportToken);
    }
    private static String getSearchXlsFileKey(String reportToken) {
        return S3_SEARCH_XLS_KEY.replace(REPLACE_REPORT_TOKEN, reportToken);
    }
    private static String getXlsFileKey(String reportToken) {
        return S3_REPORT_KEY.replace(REPLACE_REPORT_TOKEN, reportToken);
    }
    private static String getSearchPdfFileKey(String reportToken) {
        return S3_SEARCH_PDF_KEY.replace(REPLACE_REPORT_TOKEN, reportToken);
    }
    private static String getJsonFileKey(String reportToken) {
        return S3_REPORT_JSON_KEY.replace(REPLACE_REPORT_TOKEN, reportToken);
    }
    private void deleteFile(Path filePath) {
        FileUtils.deleteQuietly(filePath.toFile());
    }
    @Override
    public void uploadXlsFile(
            Path filePath,
            String objectTxt,
            String reportToken,
            ForReportOrForAdvanceSearch forReportOrForAdvanceSearch,
            String ext
    ) throws IOException {
        if (ext.equalsIgnoreCase("xls")) {
            String fileUploadKey =
                    ForReportOrForAdvanceSearch.FOR_REPORT.equals(forReportOrForAdvanceSearch)
                            ? getXlsFileKey(reportToken)
                            : getSearchXlsFileKey(reportToken);
            try {
                s3Services.uploadFile(fileUploadKey, filePath.toFile());
            } catch (FileUploadFailedException e) {
                throw new IOException(e);
            }
        }
        deleteFile(filePath);
    }
    @Override
    public void uploadPdfFile(
            Path pdfFilePath,
            String objectText,
            String reportToken,
            ForReportOrForAdvanceSearch forReportOrForAdvanceSearch,
            String ext
    ) throws IOException {
        if (ext.equalsIgnoreCase("pdf")) {
            String fileUploadKey =
                    ForReportOrForAdvanceSearch.FOR_REPORT.equals(forReportOrForAdvanceSearch)
                            ? getPdfFileKey(reportToken)
                            : getSearchPdfFileKey(reportToken);
            try {
                s3Services.uploadFile(fileUploadKey, pdfFilePath.toFile());
            } catch (FileUploadFailedException e) {
                throw new IOException(e);
            }
        }
        deleteFile(pdfFilePath);
    }
    @Override
    public String generateReportDownloadableLink(String reportToken) throws IOException {
        String reportDownloadS3Key = getXlsFileKey(reportToken);
        try {
            return s3Services.signedUrl(reportDownloadS3Key,
                    "attachment; filename=report" + SPREADSHEET_FILE_EXTENSION, null);
        } catch (SignedUrlGenerationFailedException e) {
            throw new IOException(e);
        }
    }
    @Override
    public String generatePdfReportDownloadableLink(String reportToken) throws IOException {
        String reportDownloadS3Key = getPdfFileKey(reportToken);
        try {
            return s3Services.signedUrl(reportDownloadS3Key, "attachment; filename=report.pdf", null);
        } catch (SignedUrlGenerationFailedException e) {
            throw new IOException(e);
        }
    }

    @Override
    public void uploadJsonFile(Path jsonFilePath, String reportToken) throws IOException {
        String objectKey = getJsonFileKey(reportToken);
        try {
            s3Services.uploadFile(objectKey, jsonFilePath);
        } catch (FileUploadFailedException e) {
            throw new IOException(e);
        }
        deleteFile(jsonFilePath);
    }

    @Override
    public String getJsonAsAString(String reportToken) throws IOException {

        Path jsonFilePath = getReportJsonFilePath(reportToken);
        String json = null;
        try {
            json = FileUtils.readFileToString(new File(jsonFilePath.toString()), "UTF-8");
        } catch (IOException e) {
            logger.error("", e);
        }

        return json;
    }

    private Path getReportJsonFilePath(String reportToken) {
        return Paths.get(applicationProperties.getStorageDirectory(), REPORTS_DIR, reportToken + ".json");
    }

}

