package com.digital.signage.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BaseReportResponseDto<T> {
    public static final BaseReportResponseDto<Void> EMPTY = new BaseReportResponseDto<>(true);

    private String reportToken;

    @JsonProperty("downloadAsXls")
    private String reportDownloadUrl;

    @JsonProperty("downloadAsPdf")
    private String pdfDownloadUrl;

    private List<T> data;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Boolean isReportCompleted;

    protected BaseReportResponseDto(boolean immutableData) {
        if (immutableData) {
            data = Collections.unmodifiableList(new ArrayList<>());
        }
    }

    public BaseReportResponseDto() {
        this(false);
    }

    public String getReportToken() {
        return reportToken;
    }

    public void setReportToken(String reportToken) {
        this.reportToken = reportToken;
    }

    public String getReportDownloadUrl() {
        return reportDownloadUrl;
    }

    public void setReportDownloadUrl(String reportDownloadUrl) {
        this.reportDownloadUrl = reportDownloadUrl;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public Boolean getIsReportCompleted() {
        return isReportCompleted;
    }

    public void setIsReportCompleted(Boolean reportCompleted) {
        isReportCompleted = reportCompleted;
    }

    public String getPdfDownloadUrl() {
        return pdfDownloadUrl;
    }

    public void setPdfDownloadUrl(String pdfDownloadUrl) {
        this.pdfDownloadUrl = pdfDownloadUrl;
    }
}

