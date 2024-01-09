package com.digital.signage.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * @author -Ravi Kumar created on 1/22/2023 5:43 PM
 * @project - Digital Sign-edge
 */
@Entity
@Table(name = "device_errors")
public class DeviceErrors {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "device_error_id")
    private Long deviceErrorId;

    @Column(name = "device_id")
    private Long deviceId;

    @NotNull(message = "errorCode can not be null")
    //@Size(min = 1,max = 8,message = "please entered value  between 1 to 8")
    @Column(name = "error_code")
    private Integer errorCode;

    @NotNull(message = "ErrorDescription can not be null")
    @Size(max = 500, message = "size for errorDescription must not be greater than 500 characters")
    @Column(name = "error_description")
    private String errorDescription;

    @NotNull(message = "Timesamp can not be null")
    @JsonProperty("timestamp")
    @Column(name = "time_of_error")

    private Date timeOfError;

    @JsonProperty("url")
    @Column(name = "failed_url")
    private String apiFailedUrl;

    @Column(name = "content_id")
    private Long contentId;

    @Column(name = "panel_id")
    private Long panelId;

    public Long getDeviceErrorId() {
        return deviceErrorId;
    }

    public void setDeviceErrorId(Long deviceErrorId) {
        this.deviceErrorId = deviceErrorId;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    public Date getTimeOfError() {
        return timeOfError;
    }

    public void setTimeOfError(Date timeOfError) {
        this.timeOfError = timeOfError;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public String getApiFailedUrl() {
        return apiFailedUrl;
    }

    public void setApiFailedUrl(String apiFailedUrl) {
        this.apiFailedUrl = apiFailedUrl;
    }

    public Long getContentId() {
        return contentId;
    }

    public void setContentId(Long contentId) {
        this.contentId = contentId;
    }

    public Long getPanelId() {
        return panelId;
    }

    public void setPanelId(Long panelId) {
        this.panelId = panelId;
    }

    @Override
    public String toString() {
        return "DeviceErrors {" +
                "  deviceErrorId=" + deviceErrorId +
                ", deviceId=" + deviceId +
                ", errorCode=" + errorCode +
                ", errorDescription='" + errorDescription + '\'' +
                ", timeOfError=" + timeOfError +
                ", apiFailedUrl='" + apiFailedUrl + '\'' +
                ", contentId=" + contentId +
                ", panelId=" + panelId +
                " }";
    }
}
