package com.digital.signage.dto;//package com.digital.signage.dto;
//
//import com.fasterxml.jackson.annotation.JsonFormat;
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import com.fasterxml.jackson.annotation.JsonProperty;
//import com.digital.signage.annotations.ReportColumn;
//import com.digital.signage.annotations.ReportSimpleDateFormat;
//import com.digital.signage.annotations.ReportTimeFormat;
//import com.digital.signage.models.EntityModel;
//import com.digital.signage.util.DataCollectionEnum;
//import com.digital.signage.util.ReportConstants;
//
//import java.util.Date;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.NamedStoredProcedureQueries;
//import javax.persistence.NamedStoredProcedureQuery;
//import javax.persistence.ParameterMode;
//import javax.persistence.StoredProcedureParameter;
//import javax.persistence.Table;
//
//@Entity
//@Table(name = ReportConstants.PANEL_LOG_TEMP_TABLE_NAME)
//@NamedStoredProcedureQueries({
//        @NamedStoredProcedureQuery(name = ReportConstants.PANEL_LOG_REPORT_PROCEDURE_NAME,
//                procedureName = ReportConstants.PANEL_LOG_REPORT_PROCEDURE_NAME,
//                parameters = {
//                        @StoredProcedureParameter(mode = ParameterMode.IN, name = ReportConstants.FROM_DATE_KEY, type = Date.class),
//                        @StoredProcedureParameter(mode = ParameterMode.IN, name = ReportConstants.TO_DATE_KEY, type = Date.class),
//                        @StoredProcedureParameter(mode = ParameterMode.IN, name = ReportConstants.PANEL_ID_AS_STRING_IN_PARAMETER, type = String.class),
//                        @StoredProcedureParameter(mode = ParameterMode.IN, name = ReportConstants.PANEL_STATUS_AS_STRING_IN_PARAMETER, type = String.class),
//                        @StoredProcedureParameter(mode = ParameterMode.IN, name = ReportConstants.REPORT_TOKEN__KEY, type = String.class)
//                })
//})
//public class PanelLogReportResponseDTO implements EntityModel, Comparable<PanelLogReportResponseDTO> {
//
//    private static final String ID_KEY = "panelLogId";
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = ID_KEY)
//    @JsonIgnore
//    @JsonIgnoreProperties(ID_KEY)
//    private Long panelLogId;
//
//    @Column(name = "deviceId")
//    private Long deviceId;
//
//    @ReportColumn(order = 1, columnName = "Media Player Name")
//    @Column(name = "deviceName")
//    private String deviceName;
//
//    @Column(name = "locationId")
//    private Long locationId;
//
//    @ReportColumn(order = 2, columnName = "Location")
//    @Column(name = "locationName")
//    private String locationName;
//
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "IST")
//    @ReportColumn(order = 3, columnName = "Date")
//    @Column(name = "date")
//    @ReportSimpleDateFormat
//    private Date date;
//
//    private String formatTimeForJson(String time) {
//        if (time == null) {
//            return null;
//        } else if (time.length() == 19) {
//            return time.substring(11);
//        } else {
//            return time;
//        }
//    }
//
//    @JsonProperty("scheduledPanelUpTime")
//    public String getScheduledPlayerUpTimeForJson() {
//        if (scheduledPlayerUpTime == null) {
//            return null;
//        } else if (scheduledPlayerUpTime.length() == 15) {
//            return scheduledPlayerUpTime.substring(0, 8);
//        } else {
//            return scheduledPlayerUpTime;
//        }
//    }
//
//    @ReportColumn(order = 4, columnName = "Scheduled panel Uptime")
//    @Column(name = "scheduledUpTime")
//    @JsonIgnore
//    @ReportTimeFormat(beginIndex = 0, lastIndex = 8)
//    private String scheduledPlayerUpTime;
//
//    @ReportColumn(order = 5, columnName = "Start Time")
//    @Column(name = "startTime")
//    @JsonIgnore
//    @ReportTimeFormat(beginIndex = 11, lastIndex = 19)
//    private String startTime;
//
//    @JsonProperty(value = "startTime")
//    public String getStartTimeForJson() {
//        return formatTimeForJson(startTime);
//    }
//
//    @JsonProperty(value = "endTime")
//    public String getEndTimeForJson() {
//        return formatTimeForJson(endTime);
//    }
//
//    @ReportColumn(order = 6, columnName = "End Time")
//    @Column(name = "endTime")
//    @JsonIgnore
//    @ReportTimeFormat(beginIndex = 11, lastIndex = 19)
//    private String endTime;
//
//    @ReportColumn(order = 7, columnName = "Duration")
//    @Column(name = "duration")
//    private String duration;
//
//    @Column(name = "panelId")
//    private Long panelId;
//
//    @ReportColumn(order = 8, columnName = "Panel name")
//    @Column(name = "panelName")
//    private String panelName;
//
//    @ReportColumn(order = 9, columnName = "Panel IP")
//    @Column(name = "panelIp")
//    @JsonProperty(value = "panelIp")
//    private String panelIp;
//
//    @ReportColumn(order = 10, columnName = "Panel Status")
//    @Column(name = "panelStatus")
//    private DataCollectionEnum.OutputStatus panelStatus;
//
//    @ReportColumn(order = 11, columnName = "Reason for failure")
//    @Column(name = "reasonForFailure")
//    private String reasonForFailure;
//
//    @JsonIgnore
//    @JsonIgnoreProperties("durationInSeconds")
//    @Column(name = "durationInSeconds")
//    private Float durationInSeconds;
//
//    @JsonIgnore
//    @JsonIgnoreProperties("scheduledTimeInSeconds")
//    @Column(name = "scheduledTimeInSeconds")
//    private Float scheduledTimeInSeconds;
//
//    @JsonIgnore
//    @JsonIgnoreProperties("reportToken")
//    @Column(name = "reportToken")
//    private String reportToken;
//
//    public Long getPanelLogId() {
//        return panelLogId;
//    }
//
//    public void setPanelLogId(Long panelLogId) {
//        this.panelLogId = panelLogId;
//    }
//
//    public Long getDeviceId() {
//        return deviceId;
//    }
//
//    public void setDeviceId(Long deviceId) {
//        this.deviceId = deviceId;
//    }
//
//    public String getDeviceName() {
//        return deviceName;
//    }
//
//    public void setDeviceName(String deviceName) {
//        this.deviceName = deviceName;
//    }
//
//    public Long getLocationId() {
//        return locationId;
//    }
//
//    public void setLocationId(Long locationId) {
//        this.locationId = locationId;
//    }
//
//    public String getLocationName() {
//        return locationName;
//    }
//
//    public void setLocationName(String locationName) {
//        this.locationName = locationName;
//    }
//
//    public Date getDate() {
//        return date;
//    }
//
//    public void setDate(Date date) {
//        this.date = date;
//    }
//
//    public String getScheduledPlayerUpTime() {
//        return scheduledPlayerUpTime;
//    }
//
//    public void setScheduledPlayerUpTime(String scheduledPlayerUpTime) {
//        this.scheduledPlayerUpTime = scheduledPlayerUpTime;
//    }
//
//    public String getStartTime() {
//        return startTime;
//    }
//
//    public void setStartTime(String startTime) {
//        this.startTime = startTime;
//    }
//
//    public String getEndTime() {
//        return endTime;
//    }
//
//    public void setEndTime(String endTime) {
//        this.endTime = endTime;
//    }
//
//    public String getDuration() {
//        return duration;
//    }
//
//    public void setDuration(String duration) {
//        this.duration = duration;
//    }
//
//    public Long getPanelId() {
//        return panelId;
//    }
//
//    public void setPanelId(Long panelId) {
//        this.panelId = panelId;
//    }
//
//    public String getPanelName() {
//        return panelName;
//    }
//
//    public void setPanelName(String panelName) {
//        this.panelName = panelName;
//    }
//
//    public String getPanelIp() {
//        return panelIp;
//    }
//
//    public void setPanelIp(String panelIp) {
//        this.panelIp = panelIp;
//    }
//
//    public DataCollectionEnum.OutputStatus getPanelStatus() {
//        return panelStatus;
//    }
//
//    public void setPanelStatus(DataCollectionEnum.OutputStatus panelStatus) {
//        this.panelStatus = panelStatus;
//    }
//
//    public String getReasonForFailure() {
//        return reasonForFailure;
//    }
//
//    public void setReasonForFailure(String reasonForFailure) {
//        this.reasonForFailure = reasonForFailure;
//    }
//
//    public Float getDurationInSeconds() {
//        return durationInSeconds;
//    }
//
//    public void setDurationInSeconds(Float durationInSeconds) {
//        this.durationInSeconds = durationInSeconds;
//    }
//
//    public Float getScheduledTimeInSeconds() {
//        return scheduledTimeInSeconds;
//    }
//
//    public void setScheduledTimeInSeconds(Float scheduledTimeInSeconds) {
//        this.scheduledTimeInSeconds = scheduledTimeInSeconds;
//    }
//
//    public String getReportToken() {
//        return reportToken;
//    }
//
//    public void setReportToken(String reportToken) {
//        this.reportToken = reportToken;
//    }
//
//    @Override
//    public int compareTo(PanelLogReportResponseDTO o) {
//        return 0;
//    }
//
//}
