package com.digital.signage.models;

import com.digital.signage.util.ObjectUtils;
import com.digital.signage.util.Week;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author -Ravi Kumar created on 1/19/2023 9:48 AM
 * @project - Digital Sign-edge
 */
@Entity
@Table(name = "data_collection_config")
public class DataCollectionConfig {

    public static final String PANEL_ON_TIME_KEY = "panelOnTime";
    public static final String PANEL_OFF_TIME_KEY = "panelOffTime";
    public static final String CONFIG_DATE_KEY = "configDate";
    public static final String TIMEZONE = "timeZone";


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "data_collection_config_id")
    private Long dataCollectionConfigId;
    @JsonProperty(value = "deviceId")
    @Column(name = "device_id")
    private Long deviceId;
    @JsonProperty(value = CONFIG_DATE_KEY)
    @Column(name = "config_date")
    private Date configDate;
    @JsonProperty(value = PANEL_ON_TIME_KEY)
    @Column(name = "panel_on_time")
    private String panelOnTime;
    @JsonProperty(value = PANEL_OFF_TIME_KEY)
    @Column(name = "panel_off_time")
    private String panelOffTime;
    @JsonIgnore
    //@Column(name = "timeZone")
    @Transient
    private String timeZone;

    @JsonProperty("weekOffs")
    @Column(name = "week_offs")
    @Convert(converter = Week.WeekDbConverter.class)
    private Week[] weekOffs;

    public Week[] getWeekOffs() {
        return weekOffs;
    }

    public void setWeekOffs(Week[] weekOffs) {
        this.weekOffs = weekOffs;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public Long getDataCollectionConfigId() {
        return dataCollectionConfigId;
    }

    public void setDataCollectionConfigId(Long dataCollectionConfigId) {
        this.dataCollectionConfigId =
                dataCollectionConfigId;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public Date getConfigDate() {
        return configDate;
    }

    public void setConfigDate(Date configDate) {
        this.configDate = configDate;
    }

    public String getPanelOnTime() {
        return panelOnTime;
    }

    public void setPanelOnTime(String panelOnTime) {
        this.panelOnTime = panelOnTime;
    }

    public String getPanelOffTime() {
        return panelOffTime;
    }

    public void setPanelOffTime(String panelOffTime) {
        this.panelOffTime = panelOffTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataCollectionConfig that = (DataCollectionConfig) o;
        return
                ObjectUtils.areTwoObjectsEqual(deviceId, that.deviceId) &&
                        ObjectUtils.areTwoObjectsEqual(configDate, that.configDate) &&
                        ObjectUtils.areTwoObjectsEqual(panelOnTime, that.panelOnTime) &&
                        ObjectUtils.areTwoObjectsEqual(panelOffTime, that.panelOffTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dataCollectionConfigId, deviceId, configDate, panelOnTime, panelOffTime);
    }
}
