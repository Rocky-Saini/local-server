package com.digital.signage.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity(name = "logs")
public class Logs implements EntityModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "log_id")
    private Long logId;
    @Column(name = "log_file_name")
    private String logFileName;
    @Column(name = "log_file_start_time")
    private Date logFileStartTime;
    @Column(name = "log_file_end_time")
    private Date logFileEndTime;
    @Column(name = "device_id")
    private Long deviceId;
    @Column(name = "logs_update_status")
    private Boolean logsUpdateStatus;

    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }

    public String getLogFileName() {
        return logFileName;
    }

    public void setLogFileName(String logFileName) {
        this.logFileName = logFileName;
    }

    public Date getLogFileStartTime() {
        return logFileStartTime;
    }

    public void setLogFileStartTime(Date logFileStartTime) {
        this.logFileStartTime = logFileStartTime;
    }

    public Date getLogFileEndTime() {
        return logFileEndTime;
    }

    public void setLogFileEndTime(Date logFileEndTime) {
        this.logFileEndTime = logFileEndTime;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public Boolean getLogsUpdateStatus() {
        return logsUpdateStatus;
    }

    public void setLogsUpdateStatus(Boolean logsUpdateStatus) {
        this.logsUpdateStatus = logsUpdateStatus;
    }
}

