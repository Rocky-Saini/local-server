package com.digital.signage.report;

import com.digital.signage.dto.BaseReportRequestDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class CachedReport<T, R> {
    @JsonIgnore
    private List<T> data;
    @JsonIgnore
    private String reportToken;
    @JsonIgnore
    private String downloadableLinkToXlsReport;
    @JsonIgnore
    private Boolean isReportCompleted;
    @JsonIgnore
    private String downloadableLinkToPdfReport;

    public CachedReport() {
    }

    public CachedReport(
            List<T> data,
            String reportToken,
            Boolean isReportCompleted
    ) {
        this.data = data;
        this.reportToken = reportToken;
        this.isReportCompleted=isReportCompleted;
    }

    public <T> CachedReport(List<T> data, String json, String reportToken, boolean b) {
    }

    public String getDownloadbleLinkToXlsReport() {
        return downloadableLinkToXlsReport;
    }

    public void setDownloadbleLinkToXlsReport(String downloadableLinkToXlsReport) {
        this.downloadableLinkToXlsReport = downloadableLinkToXlsReport;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
    public String getReportToken() {
        return reportToken;
    }

    public void setReportToken(String reportToken) {
        this.reportToken = reportToken;
    }

    public Boolean getIsReportCompleted() {
        return isReportCompleted;
    }

    public void setIsReportCompleted(Boolean reportCompleted) {
        isReportCompleted = reportCompleted;
    }

    public String getDownloadableLinkToPdfReport() {
        return downloadableLinkToPdfReport;
    }

    public void setDownloadableLinkToPdfReport(String downloadableLinkToPdfReport) {
        this.downloadableLinkToPdfReport = downloadableLinkToPdfReport;
    }
}
