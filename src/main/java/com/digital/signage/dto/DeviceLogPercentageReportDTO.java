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
//@Table(name = ReportConstants.DEVICE_LOG_PERCENTAGE_TABLE_NAME)
//@NamedStoredProcedureQueries({
//        @NamedStoredProcedureQuery(name = ReportConstants.DEVICE_LOG_PERCENTAGE_REPORT_PROCEDURE_NAME,
//                procedureName = ReportConstants.DEVICE_LOG_PERCENTAGE_REPORT_PROCEDURE_NAME,
//                parameters = {
//                        @StoredProcedureParameter(mode = ParameterMode.IN, name = ReportConstants.FROM_DATE_KEY, type = Date.class),
//                        @StoredProcedureParameter(mode = ParameterMode.IN, name = ReportConstants.TO_DATE_KEY, type = Date.class),
//                        @StoredProcedureParameter(mode = ParameterMode.IN, name = ReportConstants.DEVICE_KEY, type = String.class),
//                        @StoredProcedureParameter(mode = ParameterMode.IN, name = ReportConstants.REPORT_TOKEN__KEY, type = String.class)
//                })
//})
//public class DeviceLogPercentageReportDTO
//        implements EntityModel, Comparable<DeviceLogPercentageReportDTO> {
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
//    @Column(name = "locationName")
//    @ReportColumn(order = 2, columnName = "Location")
//    private String locationName;
//
//    @JsonProperty(value = "deviceGroupId")
//    @Column(name = "deviceGroupId")
//    private Long deviceGroupId;
//
//    @JsonProperty(value = "deviceGroupName")
//    @Column(name = "deviceGroupName")
//    @ReportColumn(order = 3, columnName = "Device Group")
//    private String deviceGroupName;
//
//    @ReportColumn(order = 4, columnName = "Date")
//    @Column(name = "date")
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "IST")
//    @ReportSimpleDateFormat
//    private Date date;
//
//    @ReportColumn(order = 5, columnName = "Scheduled Player Uptime")
//    @Column(name = "scheduledUpTime")
//    @JsonIgnore
//    @ReportTimeFormat(beginIndex = 0, lastIndex = 8)
//    private String scheduledPlayerUpTime;
//
//
//    @JsonProperty(value = "scheduledPlayerUpTime")
//    public String getScheduledPlayerUpTimeForJson() {
//        return formatTimeForJson(scheduledPlayerUpTime);
//    }
//
//    private String formatTimeForJson(String time) {
//        if (time == null) {
//            return null;
//        } else if (time.length() == 15) {
//            return time.substring(0, 8);
//        } else {
//            return time;
//        }
//    }
//
//    @JsonProperty(value = "onHours")
//    public String getOnHoursForJson() {
//        return formatTimeForJson(onHours);
//    }
//
//    @JsonProperty(value = "offHours")
//    public String getOffHoursForJson() {
//        return formatTimeForJson(offHours);
//    }
//
//    @ReportColumn(order = 6, columnName = "On Hrs")
//    @Column(name = "onHours")
//    @JsonIgnore
//    @ReportTimeFormat(beginIndex = 0, lastIndex = 8)
//    private String onHours;
//
//    @ReportColumn(order = 7, columnName = "Off Hrs")
//    @Column(name = "offHours")
//    @JsonIgnore
//    @ReportTimeFormat(beginIndex = 0, lastIndex = 8)
//    private String offHours;
//
//    @ReportColumn(order = 8, columnName = "On %")
//    @JsonProperty(value = "onPercentage")
//    @Column(name = "onPercentage")
//    private Double onPercentage;
//
//    @Column(name = "offPercentage")
//    @JsonProperty(value = "offPercentage")
//    @ReportColumn(order = 9, columnName = "Off %")
//    private Double offPercentage;
//
//    @JsonIgnore
//    @JsonIgnoreProperties("reportToken")
//    @Column(name = "reportToken")
//    private String reportToken;
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
//    public String getOffHours() {
//        return offHours;
//    }
//
//    public String getOnHours() {
//        return onHours;
//    }
//
//    public void setOffHours(String offHours) {
//        this.offHours = offHours;
//    }
//
//    public void setOnHours(String onHours) {
//        this.onHours = onHours;
//    }
//
//    public Double getOffPercentage() {
//        return offPercentage;
//    }
//
//    public Double getOnPercentage() {
//        return onPercentage;
//    }
//
//    public void setOffPercentage(Double offPercentage) {
//        this.offPercentage = offPercentage;
//    }
//
//    public void setOnPercentage(Double onPercentage) {
//        this.onPercentage = onPercentage;
//    }
//
//    @Override
//    public int compareTo(DeviceLogPercentageReportDTO o) {
//        return 0;
//    }
//
//    public Long getDeviceGroupId() {
//        return deviceGroupId;
//    }
//
//    public void setDeviceGroupId(Long deviceGroupId) {
//        this.deviceGroupId = deviceGroupId;
//    }
//
//    public String getDeviceGroupName() {
//        return deviceGroupName;
//    }
//
//    public void setDeviceGroupName(String deviceGroupName) {
//        this.deviceGroupName = deviceGroupName;
//    }
//}
