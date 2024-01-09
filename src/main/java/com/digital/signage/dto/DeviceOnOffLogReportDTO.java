package com.digital.signage.dto;//package com.digital.signage.dto;
//
//import com.fasterxml.jackson.annotation.JsonFormat;
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import com.fasterxml.jackson.annotation.JsonProperty;
//import com.digital.signage.annotations.ReportColumn;
//import com.digital.signage.annotations.ReportTimeFormat;
//import com.digital.signage.annotations.ReportSimpleDateFormat;
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
//@Table(name = ReportConstants.DEVICE_LOG_TEMP_TABLE_NAME)
//@NamedStoredProcedureQueries({
//        @NamedStoredProcedureQuery(name = ReportConstants.DEVICE_LOG_REPORT_PROCEDURE_NAME,
//                procedureName = ReportConstants.DEVICE_LOG_REPORT_PROCEDURE_NAME,
//                parameters = {
//                        @StoredProcedureParameter(mode = ParameterMode.IN, name = ReportConstants.FROM_DATE_KEY, type = Date.class),
//                        @StoredProcedureParameter(mode = ParameterMode.IN, name = ReportConstants.TO_DATE_KEY, type = Date.class),
//                        @StoredProcedureParameter(mode = ParameterMode.IN, name = ReportConstants.DEVICE_KEY, type = String.class),
//                        @StoredProcedureParameter(mode = ParameterMode.IN, name = ReportConstants.DEVICE_STATUS_KEY, type = String.class),
//                        @StoredProcedureParameter(mode = ParameterMode.IN, name = ReportConstants.REPORT_TOKEN__KEY, type = String.class)
//                })
//})
//public class DeviceOnOffLogReportDTO implements EntityModel, Comparable<DeviceOnOffLogReportDTO> {
//
//    private static final String ID_KEY = "deviceLogId";
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = ID_KEY)
//    @JsonIgnore
//    @JsonIgnoreProperties(ID_KEY)
//    private Long deviceLogId;
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
//    @ReportSimpleDateFormat(value = ReportConstants.DATE_FORMAT_FOR_PDF_REPORT)
//    private Date date;
//
//    @ReportColumn(order = 4, columnName = "Scheduled Player Uptime")
//    @Column(name = "scheduledUpTime")
//    @JsonIgnore
//    @ReportTimeFormat(beginIndex = 0, lastIndex = 8)
//    private String scheduledPlayerUpTime;
//
//    @JsonProperty(value = "scheduledPlayerUpTime")
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
//    @ReportColumn(order = 6, columnName = "End Time")
//    @Column(name = "endTime")
//    @JsonIgnore
//    @ReportTimeFormat(beginIndex = 11, lastIndex = 19)
//    private String endTime;
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
//    @JsonProperty(value = "endTime")
//    public String getEndTimeForJson() {
//        return formatTimeForJson(endTime);
//    }
//
//    @ReportColumn(order = 7, columnName = "Duration")
//    @Column(name = "duration")
//    private String duration;
//
//    @ReportColumn(order = 8, columnName = "MP Status")
//    @Column(name = "deviceStatus")
//    private DataCollectionEnum.OutputStatus deviceStatus;
//
//    @ReportColumn(order = 9, columnName = "Reason")
//    @Column(name = "reasonForOffOrDisconnection")
//    private String reasonForOffOrDisconnection;
//
//    @JsonIgnore
//    @JsonIgnoreProperties("isDeviceDown")
//    @Column(name = "isDeviceDown")
//    private Boolean isDeviceDown;
//
//    @JsonIgnore
//    @JsonIgnoreProperties("reportToken")
//    @Column(name = "reportToken")
//    private String reportToken;
//
//    @JsonIgnore
//    @JsonIgnoreProperties("durationInSeconds")
//    @Column(name = "durationInSeconds")
//    private Float durationInSeconds;
//
//    @JsonIgnore
//    @JsonIgnoreProperties("scheduledUpTimeInSecond")
//    @Column(name = "scheduledUpTimeInSecond")
//    private Float scheduledUpTimeInSecond;
//
//    public Long getDeviceLogId() {
//        return deviceLogId;
//    }
//
//    public void setDeviceLogId(Long deviceLogId) {
//        this.deviceLogId = deviceLogId;
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
//    public DataCollectionEnum.OutputStatus getDeviceStatus() {
//        return deviceStatus;
//    }
//
//    public void setDeviceStatus(DataCollectionEnum.OutputStatus deviceStatus) {
//        this.deviceStatus = deviceStatus;
//    }
//
//    public String getReasonForOffOrDisconnection() {
//        return reasonForOffOrDisconnection;
//    }
//
//    public void setReasonForOffOrDisconnection(String reasonForOffOrDisconnection) {
//        this.reasonForOffOrDisconnection = reasonForOffOrDisconnection;
//    }
//
//    public Boolean getIsDeviceDown() {
//        return isDeviceDown;
//    }
//
//    public void setIsDeviceDown(Boolean isDeviceDown) {
//        this.isDeviceDown = isDeviceDown;
//    }
//
//    @Override
//    public int compareTo(DeviceOnOffLogReportDTO o) {
//        return 0;
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
//    public Float getScheduledUpTimeInSecond() {
//        return scheduledUpTimeInSecond;
//    }
//
//    public void setScheduledUpTimeInSecond(Float scheduledUpTimeInSecond) {
//        this.scheduledUpTimeInSecond = scheduledUpTimeInSecond;
//    }
//}
