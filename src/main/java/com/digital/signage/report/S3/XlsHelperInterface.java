package com.digital.signage.report.S3;


import java.io.IOException;
import java.nio.file.Path;

public interface XlsHelperInterface {
    static enum ForReportOrForAdvanceSearch {
        FOR_REPORT,
        FOR_ADVANCE_SEARCH
    }
    void uploadXlsFile(
            Path xlsFilePath,
            String objectTxt,
            String reportToken,
            ForReportOrForAdvanceSearch forReportOrForAdvanceSearch,
            String ext
    ) throws IOException;
    void uploadPdfFile(
            Path pdfFilePath,
            String objectText,
            String reportToken,
            ForReportOrForAdvanceSearch forReportOrForAdvanceSearch,
            String ext
    ) throws IOException;

    String generateReportDownloadableLink(String reportToken) throws IOException;
    String generatePdfReportDownloadableLink(String reportToken) throws IOException;

    void uploadJsonFile(Path jsonFilePath, String reportToken) throws IOException;
    String getJsonAsAString(String reportToken) throws IOException;


}
