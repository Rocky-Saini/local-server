package com.digital.signage.dto;

import com.digital.signage.annotations.ReportColumn;
import com.digital.signage.annotations.ReportSimpleDateFormat;
import com.digital.signage.annotations.ReportTimeFormat;
import com.digital.signage.models.EntityModel;
import com.digital.signage.util.ReportConstants;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = ReportConstants.PANEL_LOG_PER_TABLE_NAME)
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = ReportConstants.PANEL_LOG_PER_REPORT_PROCEDURE_NAME,
                procedureName = ReportConstants.PANEL_LOG_PER_REPORT_PROCEDURE_NAME,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = ReportConstants.FROM_DATE_KEY, type = Date.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = ReportConstants.TO_DATE_KEY, type = Date.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = ReportConstants.PANEL_ID_AS_STRING_IN_PARAMETER, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = ReportConstants.REPORT_TOKEN__KEY, type = String.class)
                })
})
public class PanelLogPercentageResponseDTO
        implements EntityModel, Comparable<PanelLogPercentageResponseDTO> {
    public static final String COLUMN_SCHEDULE_UP_TIME = "scheduledUpTime";
    private static final String ID_KEY = "panelLogId";
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = ID_KEY)
    @JsonIgnore
    @JsonIgnoreProperties(ID_KEY)
    private Long panelLogId;

    @Column(name = "deviceId")
    private Long deviceId;

    @ReportColumn(order = 1, columnName = "Media Player Name")
    @Column(name = "deviceName")
    private String deviceName;

    @Column(name = "locationId")
    private Long locationId;

    @ReportColumn(order = 2, columnName = "Location")
    @Column(name = "locationName")
    private String locationName;

    @ReportColumn(order = 3, columnName = "Date")
    @Column(name = "date")
    @ReportSimpleDateFormat
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "IST")
    private Date date;

    @ReportColumn(order = 4, columnName = "Scheduled panel Uptime")
    @Column(name = COLUMN_SCHEDULE_UP_TIME)
    @ReportTimeFormat(beginIndex = 0, lastIndex = 8)
    @JsonIgnore
    private String scheduledPlayerUpTime;

    @JsonProperty(value = "scheduledPanelUpTime")
    public String getScheduledPlayerUpTimeForJson() {
        return formatTimeForJson(scheduledPlayerUpTime);
    }

    @JsonProperty(value = "onHrs")
    public String getOnHoursForJson() {
        return formatTimeForJson(onHours);
    }

    @JsonProperty(value = "offHrs")
    public String getOffHoursForJson() {
        return formatTimeForJson(offHours);
    }

    private String formatTimeForJson(String time) {
        if (time == null) {
            return null;
        } else if (time.length() == 15) {
            return time.substring(0, 8);
        } else {
            return time;
        }
    }

    @Column(name = "panelId")
    private Long panelId;

    @ReportColumn(order = 5, columnName = "Panel name")
    @Column(name = "panelName")
    private String panelName;

    @ReportColumn(order = 6, columnName = "Panel IP")
    @Column(name = "panelIp")
    @JsonProperty(value = "panelIp")
    private String panelIp;

    @ReportColumn(order = 7, columnName = "On Hrs")
    @Column(name = "onHours")
    @JsonIgnore
    @ReportTimeFormat(beginIndex = 0, lastIndex = 8)
    private String onHours;

    @ReportColumn(order = 8, columnName = "Off Hrs")
    @Column(name = "offHours")
    @JsonIgnore
    @ReportTimeFormat(beginIndex = 0, lastIndex = 8)
    private String offHours;

    @ReportColumn(order = 9, columnName = "on %")
    @Column(name = "onPercentage")
    private String onPercentage;

    @ReportColumn(order = 10, columnName = "off %")
    @Column(name = "offPercentage")
    private String offPercentage;

    @JsonIgnore
    @JsonIgnoreProperties("reportToken")
    @Column(name = "reportToken")
    private String reportToken;

    public Long getPanelLogId() {
        return panelLogId;
    }

    public void setPanelLogId(Long panelLogId) {
        this.panelLogId = panelLogId;
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

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getScheduledPlayerUpTime() {
        return scheduledPlayerUpTime;
    }

    public void setScheduledPlayerUpTime(String scheduledPlayerUpTime) {
        this.scheduledPlayerUpTime = scheduledPlayerUpTime;
    }

    public Long getPanelId() {
        return panelId;
    }

    public void setPanelId(Long panelId) {
        this.panelId = panelId;
    }

    public String getPanelName() {
        return panelName;
    }

    public void setPanelName(String panelName) {
        this.panelName = panelName;
    }

    public String getPanelIp() {
        return panelIp;
    }

    public void setPanelIp(String panelIp) {
        this.panelIp = panelIp;
    }

    public void setOnHours(String onHours) {
        this.onHours = onHours;
    }

    public void setOffHours(String offHours) {
        this.offHours = offHours;
    }

    public String getOffHours() {
        return offHours;
    }

    public String getOnHours() {
        return onHours;
    }

    public String getOnPercentage() {
        return onPercentage;
    }

    public void setOnPercentage(String onPercentage) {
        this.onPercentage = onPercentage;
    }

    public String getOffPercentage() {
        return offPercentage;
    }

    public void setOffPercentage(String offPercentage) {
        this.offPercentage = offPercentage;
    }

    public String getReportToken() {
        return reportToken;
    }

    public void setReportToken(String reportToken) {
        this.reportToken = reportToken;
    }

    @Override
    public int compareTo(PanelLogPercentageResponseDTO o) {
        return 0;
    }
}
