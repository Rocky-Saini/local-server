package com.digital.signage.report;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class LicenseReportResponseDto<T> {
    private String reportToken;

    @JsonProperty("downloadAsXls")
    private String reportDownloadUrl;

    @JsonProperty("downloadAsPdf")
    private String pdfDownloadUrl;

    private List<T> data;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Boolean isReportCompleted;

    private Pagination pagination;

    // Constructors, getters, and setters for the fields

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }
}
