package com.digital.signage.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author -Ravi Kumar created on 1/29/2023 8:40 PM
 * @project - Digital Sign-edge
 */
public class AdvanceSearchResponse<T> extends Response<T> {

    @JsonProperty("downloadAsXls")
    private String xlsDownloadUrl;
    @JsonProperty("downloadAsPdf")
    private String pdfDownloadUrl;

    public AdvanceSearchResponse(T result, Pagination pagination, String name, Integer code,
                                 String message, String xlsDownloadUrl, String pdfDownloadUrl) {
        this(result, pagination, name, code, message, null, xlsDownloadUrl, pdfDownloadUrl);
    }

    public AdvanceSearchResponse(T result, Pagination pagination, String name, Integer code,
                                 String message, Integer httpStatusCode, String xlsDownloadUrl, String pdfDownloadUrl) {
        super(result, pagination, name, code, message, httpStatusCode);
        this.xlsDownloadUrl = xlsDownloadUrl;
        this.pdfDownloadUrl = pdfDownloadUrl;
    }

    public String getPdfDownloadUrl() {
        return pdfDownloadUrl;
    }

    public String getXlsDownloadUrl() {
        return xlsDownloadUrl;
    }

    public void setXlsDownloadUrl(String xlsDownloadUrl) {
        this.xlsDownloadUrl = xlsDownloadUrl;
    }

    public void setPdfDownloadUrl(String pdfDownloadUrl) {
        this.pdfDownloadUrl = pdfDownloadUrl;
    }
}

