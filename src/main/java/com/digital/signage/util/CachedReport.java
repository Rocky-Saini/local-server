package com.digital.signage.util;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class CachedReport<T, R> {
  @JsonIgnore
  private List<T> data;
  @JsonIgnore
  private String jsonArray;
  @JsonIgnore
  private String reportToken;
  @JsonIgnore
  private String downloadableLinkToXlsReport;
  @JsonIgnore
  private R requestObject;
  @JsonIgnore
  private Boolean isReportCompleted;
  @JsonIgnore
  private String downloadableLinkToPdfReport;

  public CachedReport() {
  }

  public CachedReport(
      List<T> data,
      String jsonArray,
      String reportToken,
      Boolean isReportCompleted
  ) {
    this.data = data;
    this.jsonArray = jsonArray;
    this.reportToken = reportToken;
    this.isReportCompleted=isReportCompleted;
  }

  public R getRequestObject() {
    return requestObject;
  }

  public void setRequestObject(R requestObject) {
    this.requestObject = requestObject;
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

  public String getJsonArray() {
    return jsonArray;
  }

  public void setJsonArray(String jsonArray) {
    this.jsonArray = jsonArray;
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
