package com.digital.signage.models;

import com.digital.signage.util.ReportType;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "reports_cache")
public class ReportCacheEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "report_cache_id")
    private Long reportCacheId;
    @Column(name = "report_token")
    private String reportToken;
    @Column(name = "time_of_report_generation")
    private Date timeOfReportGeneration;
    @Column(name = "request_object_json")
    private String requestObjectJson;
    //to save repositoryClassName;
    @Column(name = "report_type")
    private ReportType reportType;
    @Column(name="report_completed")
    private Boolean isReportCompleted;

    public String getRequestObjectJson() {
        return requestObjectJson;
    }

    public void setRequestObjectJson(String requestObjectJson) {
        this.requestObjectJson = requestObjectJson;
    }

    public Date getTimeOfReportGeneration() {
        return timeOfReportGeneration;
    }

    public void setTimeOfReportGeneration(Date timeOfReportGeneration) {
        this.timeOfReportGeneration = timeOfReportGeneration;
    }

    public Long getReportCacheId() {
        return reportCacheId;
    }

    public void setReportCacheId(Long reportCacheId) {
        this.reportCacheId = reportCacheId;
    }

    public String getReportToken() {
        return reportToken;
    }

    public void setReportToken(String reportToken) {
        this.reportToken = reportToken;
    }

    public ReportType getReportType() {    return reportType;  }

    public void setReportType(ReportType reportType) {    this.reportType = reportType;  }

    public Boolean getIsReportCompleted() {
        return isReportCompleted;
    }

    public void setIsReportCompleted(Boolean reportCompleted) {
        isReportCompleted = reportCompleted;
    }
}


