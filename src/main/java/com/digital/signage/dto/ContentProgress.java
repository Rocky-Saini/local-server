package com.digital.signage.dto;

import com.digital.signage.annotations.ReportColumn;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class ContentProgress {

    @JsonIgnore
    private Long contentProgressId;

    private Long contentId;

    private String reasonForFailure;

    @ReportColumn(columnName = "Download Status(%)", order = 2)
    private Double contentProgressInPercentage;
    //For Previous Download report
    @ReportColumn(columnName = "File Name", order = 1)
    private String contentName;

    public Long getContentId() {
        return contentId;
    }

    public String getContentName() {
        return contentName;
    }

    public void setContentName(String contentName) {
        this.contentName = contentName;
    }

    public void setContentId(Long contentId) {
        this.contentId = contentId;
    }

    public Double getProgressPercent() {
        return contentProgressInPercentage;
    }

    public Long getContentProgressId() {
        return contentProgressId;
    }

    public void setContentProgressId(Long contentProgressId) {
        this.contentProgressId = contentProgressId;
    }

    public void setProgressPercent(Double progressPercent) {
        this.contentProgressInPercentage = progressPercent;
    }

    public String getReasonForFailure() {
        return reasonForFailure;
    }

    public void setReasonForFailure(String reasonForFailure) {
        this.reasonForFailure = reasonForFailure;
    }

    @Override public String toString() {
        return "ContentProgress{" +
                "contentProgressId=" + contentProgressId +
                ", contentId=" + contentId +
                ", reasonForFailure='" + reasonForFailure + '\'' +
                ", contentProgressInPercentage=" + contentProgressInPercentage +
                ", contentName='" + contentName + '\'' +
                '}';
    }
}
