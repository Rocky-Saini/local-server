package com.digital.signage.models;

import com.digital.signage.annotations.PdfColumn;
import com.digital.signage.annotations.ReportColumn;
import com.digital.signage.annotations.ReportSimpleDateFormat;
import com.digital.signage.util.datacollection.ContentPlaybackDataProcessing;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name = "contentPlaybackTpappData")
public class ContentPlaybackTpAppData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long contentPlaybackTpAppId;

    private Long tpAppId;

    @ReportColumn(columnName = "Tp App Name", order = 6)
    @PdfColumn(columnName = "Tp App Name", order = 6)
    private String tpAppName;

    @Column(name = "deviceId")
    private Long deviceId;

    @Column(name = "deviceName")
    @ReportColumn(columnName = "Device Name", order = 1)
    @PdfColumn(columnName = "Device Name", order = 1)
    private String deviceName;

    @Column(name = "deviceGroupName")
    @ReportColumn(columnName = "Device Group", order = 2)
    @PdfColumn(columnName = "Device Group", order = 2)
    private String deviceGroupName;

    @Column(name = "deviceGroupId")
    private Long deviceGroupId;

    @Column(name = "startTime")
    @ReportColumn(columnName = "Start Time", order = 4)
    @PdfColumn(columnName = "Start Time", order = 4)
    private String startTime;

    @Column(name = "endTime")
    @ReportColumn(columnName = "End Time", order = 5)
    @PdfColumn(columnName = "End Time", order = 5)
    private String endTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "IST")
    @Column(name = "date")
    @ReportColumn(columnName = "Date", order = 3)
    @PdfColumn(columnName = "Date", order = 3)
    @ReportSimpleDateFormat
    private Date date;

    @Column(name = "tpaDataProcessing")
    private ContentPlaybackDataProcessing contentPlaybackDataProcessing;

    //Only for DB
    @Column(name = "startDateTime")
    @JsonIgnore
    private Date dateTime;

    public Long getContentPlaybackTpAppId() {
        return contentPlaybackTpAppId;
    }

    public void setContentPlaybackTpAppId(Long contentPlaybackTpappId) {
        this.contentPlaybackTpAppId = contentPlaybackTpappId;
    }

    public Long getTpAppId() {
        return tpAppId;
    }

    public void setTpAppId(Long tpAppId) {
        this.tpAppId = tpAppId;
    }

    public String getTpAppName() {
        return tpAppName;
    }

    public void setTpAppName(String tpAppName) {
        this.tpAppName = tpAppName;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceGroupName() {
        return deviceGroupName;
    }

    public void setDeviceGroupName(String deviceGroupName) {
        this.deviceGroupName = deviceGroupName;
    }

    public Long getDeviceGroupId() {
        return deviceGroupId;
    }

    public void setDeviceGroupId(Long deviceGroupId) {
        this.deviceGroupId = deviceGroupId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public ContentPlaybackDataProcessing getContentPlaybackDataProcessing() {
        return contentPlaybackDataProcessing;
    }

    public void setContentPlaybackDataProcessing(
            ContentPlaybackDataProcessing contentPlaybackDataProcessing) {
        this.contentPlaybackDataProcessing = contentPlaybackDataProcessing;
    }
}

