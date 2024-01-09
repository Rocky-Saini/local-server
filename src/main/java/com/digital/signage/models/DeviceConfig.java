package com.digital.signage.models;

import com.digital.signage.converter.WeekArrayDbConverter;
import com.digital.signage.util.Week;
import com.digital.signage.validators.LongRange;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.digital.signage.annotations.NotEmptyIfNotNull;
import com.digital.signage.deserializers.TimeObjectDeserializer;
import com.digital.signage.exceptions.CopyingPropertiesException;
import com.digital.signage.serializers.TimeObjectSerializer;
import com.digital.signage.util.ApplicationConstants;
import com.digital.signage.validators.Url;

import java.lang.reflect.InvocationTargetException;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.persistence.*;

import org.apache.commons.beanutils.BeanUtilsBean2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.digital.signage.util.ApplicationConstants.DATA_COLLECTION_MIN_TIME_IN_SECOND;
import static com.digital.signage.util.ApplicationConstants.ERROR_UPDATE_MIN_TIME_IN_MINUTE;
import static com.digital.signage.util.ApplicationConstants.LOG_UPLOAD_MIN_TIME_IN_MINUTE;
import static com.digital.signage.util.ApplicationConstants.MAX_DURATION_APP_UPGRADE_MSG_TIME_IN_SEC;
import static com.digital.signage.util.ApplicationConstants.MIN_DURATION_APP_UPGRADE_MSG_TIME_IN_SEC;
import static com.digital.signage.util.ApplicationConstants.PANEL_STATUS_MIN_TIME_IN_SECOND;
import static com.digital.signage.util.ApplicationConstants.SCREEN_CAPTURE_MIN_TIME_IN_MINUTE;
import static com.digital.signage.util.ApplicationConstants.SNAPSHOT_UPDATE_MIN_TIME_IN_SECOND;
import static com.digital.signage.util.ApplicationConstants.TOUCH_TIME_MIN_TIME_IN_SECOND;
import static com.digital.signage.util.ApplicationConstants.TWENTY_FOUR_HRS_IN_MINS;
import static com.digital.signage.util.ApplicationConstants.TWENTY_FOUR_HRS_IN_SECONDS;

@Entity
@Table(name = "device_config")
public class DeviceConfig implements EntityModel, Cloneable {
    private static final Logger logger = LoggerFactory.getLogger(DeviceConfig.class);
    private static final long THREE_HOURS_IN_MINUTES = 180L;
    public static final long DEFAULT_DURATION_APP_UPGRADE_MSG_IN_SECOND = 60L;
    public static final String JSON_B_ON = "businessOnTime";
    public static final String JSON_B_OFF = "businessOffTime";
    public static final String JSON_P_ON = "panelOnTime";
    public static final String JSON_P_OFF = "panelOffTime";
    public static final String JSON_TOUCH_WEB_URL = "touchScreenWebViewUrl";
    public static final String JSON_TOUCH_WEB_TIMEOUT = "touchScreenWebViewNoActionTimeoutInSeconds";
    public static final String JSON_PLANO_SYNC_START_TIME = "planogramSyncStartTime";
    public static final String JSON_WEEK_OFF = "weekOffs";
    public static final String JSON_STOP_AUTO_LAUNCH_APP = "stopAutoLaunchingApp";
    public static final String JSON_DURATION_APP_UPGRADE_TIME = "durationOfAppUpgradeMessageInSec";

    public static final String SCREEN_CAPTURE_INTERVAL_TIME = "screenCaptureIntervalTime";
    public static final String ERROR_UPDATE_INTERVAL_TIME = "errorUpdateIntervalTime";
    public static final String DISPLAY_LOG_INTERVAL_TIME = "displayLogIntervalTime";
    public static final String LOG_UPLOAD_INTERVAL_TIME = "logUploadIntervalInMinutes";
    public static final String PLANOGRAM_SYNC_INTERVAL_INMINUTES = "planogramSyncIntervalInMinutes";
    public static final String PANEL_STATUS_UPDATE_INTERVAL_INSEC = "PanelStatusIntervalInSeconds";
    public static final String SNAPSHOT_UPDATE_INTERVAL_INSEC = "SnapShotUpdateIntervalInSeconds";
    public static final String TOUCHSCREEN_UPDATE_INTERVAL_INSEC =
            "TouchScreenUpdateIntervalInSeconds";
    public static final String DATACOLLECTION_UPDATE_INTERVAL_INSEC =
            "DataCollectionUpdateIntervalInSeconds";
    public static final String CONTENTPLAYBACK_UPDATE_INTERVAL_INSEC = "contentPlaybackSyncInterval";
    public static final String UPLOAD_DEMOGRAPHIC_DATA_INTERVAL_IN_MIN =
            "upload_demographic_data_interval_in_mins";
    public static final String ZOOM_PERCENT_FOR_WEBVIEW = "zoom_percent_for_web_view";


    @JsonProperty("logUpdateIntervalTime")
    @Column(name = "log_upload_interval_in_minutes")
    @LongRange(
            min = LOG_UPLOAD_MIN_TIME_IN_MINUTE,
            max = TWENTY_FOUR_HRS_IN_MINS,
            jsonKey = "logUpdateIntervalTime"
    )
    @NotEmptyIfNotNull
    private Long logUploadIntervalInMinutes;

    @Column(name = "planogram_sync_interval_in_minutes")
    @LongRange(
            min = 30,
            max = TWENTY_FOUR_HRS_IN_MINS,
            jsonKey = "planogramSyncIntervalInMinutes"
    )
    private Long planogramSyncIntervalInMinutes;

    @Column(name = "screen_capture_interval_time")
    @LongRange(
            min = SCREEN_CAPTURE_MIN_TIME_IN_MINUTE,
            max = TWENTY_FOUR_HRS_IN_MINS
    )
    @NotEmptyIfNotNull
    private Long screenCaptureIntervalTime;

    @Column(name = "error_update_interval_time")
    @LongRange(
            min = ERROR_UPDATE_MIN_TIME_IN_MINUTE,
            max = TWENTY_FOUR_HRS_IN_MINS
    )
    @NotEmptyIfNotNull
    private Long errorUpdateIntervalTime;

    @Column(name = "display_log_interval_time")
    @LongRange(
            min = ERROR_UPDATE_MIN_TIME_IN_MINUTE,
            max = THREE_HOURS_IN_MINUTES
    )
    @NotEmptyIfNotNull
    private Long displayLogIntervalTime;

    @Column(name = "touch_screen_web_view_url")
    @JsonProperty(JSON_TOUCH_WEB_URL)
    @Url(jsonKey = JSON_TOUCH_WEB_URL)
    @NotEmptyIfNotNull
    private String touchScreenWebViewUrl;

    @Column(name = "touch_screen_web_view_no_action_timeout_in_seconds")
    @JsonProperty(JSON_TOUCH_WEB_TIMEOUT)
    @LongRange(
            min = TOUCH_TIME_MIN_TIME_IN_SECOND,
            max = TWENTY_FOUR_HRS_IN_SECONDS,
            jsonKey = JSON_TOUCH_WEB_TIMEOUT,
            isNegativeValueAllowed = true
    )
    @NotEmptyIfNotNull
    private Long touchScreenWebViewNoActionTimeoutInSeconds;

    @Column(name = "frequency_of_panel_status_update")
    @LongRange(
            min = PANEL_STATUS_MIN_TIME_IN_SECOND,
            max = TWENTY_FOUR_HRS_IN_SECONDS
    )
    @NotEmptyIfNotNull
    private Long frequencyOfPanelStatusUpdate; // in seconds

    @Column(name = "frequency_of_snapshots_update")
    @LongRange(
            min = SNAPSHOT_UPDATE_MIN_TIME_IN_SECOND,
            max = TWENTY_FOUR_HRS_IN_SECONDS
    )
    @NotEmptyIfNotNull
    private Long frequencyOfSnapshotsUpdate;

    @Column(name = "frequency_of_data_collection_update")
    @LongRange(
            min = DATA_COLLECTION_MIN_TIME_IN_SECOND,
            max = TWENTY_FOUR_HRS_IN_SECONDS
    )
    @NotEmptyIfNotNull
    private Long frequencyOfDataCollectionUpdate;

    @JsonProperty(CONTENTPLAYBACK_UPDATE_INTERVAL_INSEC)
    @Column(name = "content_playback_sync_interval")
    @LongRange(
            min = DATA_COLLECTION_MIN_TIME_IN_SECOND,
            max = TWENTY_FOUR_HRS_IN_SECONDS,
            jsonKey = CONTENTPLAYBACK_UPDATE_INTERVAL_INSEC
    )
    @NotEmptyIfNotNull
    private Long contentPlaybackSyncInterval;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "device_config_id")
    private Long deviceConfigId;

    @Column(name = "device_id")
    private Long deviceId;

    @Column(name = "log_upload_start_time")
    @JsonSerialize(using = TimeObjectSerializer.class)
    @JsonDeserialize(using = TimeObjectDeserializer.class)
    @NotEmptyIfNotNull
    private Time logUploadStartTime;

    @JsonProperty(JSON_PLANO_SYNC_START_TIME)
    @Transient
    @JsonSerialize(using = TimeObjectSerializer.class)
    @JsonDeserialize(using = TimeObjectDeserializer.class)
    private Time planogramSyncStartTime;

    @Column(name = "created_by")
    private Long createdBy;

    @Column(name = "created_on")
    private Timestamp createdOn;

    @Column(name = "panel_on_time")
    @JsonProperty(JSON_P_ON)
    @JsonSerialize(using = TimeObjectSerializer.class)
    @JsonDeserialize(using = TimeObjectDeserializer.class)
    @NotEmptyIfNotNull
    private Time panelOnTime;

    @Column(name = "panel_off_time")
    @JsonProperty(JSON_P_OFF)
    @JsonSerialize(using = TimeObjectSerializer.class)
    @JsonDeserialize(using = TimeObjectDeserializer.class)
    @NotEmptyIfNotNull
    private Time panelOffTime;

    @JsonProperty(JSON_B_ON)
    @Column(name = "business_on_time")
    @JsonSerialize(using = TimeObjectSerializer.class)
    @JsonDeserialize(using = TimeObjectDeserializer.class)
    @NotEmptyIfNotNull
    private Time businessOnTime;

    @JsonProperty(JSON_B_OFF)
    @JsonSerialize(using = TimeObjectSerializer.class)
    @JsonDeserialize(using = TimeObjectDeserializer.class)
    @Column(name = "business_off_time")
    @NotEmptyIfNotNull
    private Time businessOffTime;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "screenshot_upload_time")
    @JsonSerialize(using = TimeObjectSerializer.class)
    @JsonDeserialize(using = TimeObjectDeserializer.class)
    @NotEmptyIfNotNull
    private Time screenshotUploadTime;

    @Column(name = "enable_proof_of_play_snapshots")
    @NotEmptyIfNotNull
    private Boolean enableProofOfPlaySnapshots;

    @JsonProperty(JSON_WEEK_OFF)
    @Column(name = "week_offs")
    @Convert(converter = WeekArrayDbConverter.class)
    @NotEmptyIfNotNull
    private Week[] weekOffs;

    public Week[] getWeekOffs() {
        return weekOffs;
    }

    public void setWeekOffs(Week[] weekOffs) {
        this.weekOffs = weekOffs;
    }

    @JsonIgnore
    @Transient
    private Date facebookKeyUpdateTimestamp;

    @JsonProperty(JSON_STOP_AUTO_LAUNCH_APP)
    @Column(name = "stop_auto_launching_app")
    private Boolean stopAutoLaunchingApp;


    @Column(name = ZOOM_PERCENT_FOR_WEBVIEW)
    private Integer zoomPercentForWebview;


    @JsonProperty(JSON_DURATION_APP_UPGRADE_TIME)
    @Column(name = "duration_of_app_upgrade_message_in_sec")
    @LongRange(
            min = MIN_DURATION_APP_UPGRADE_MSG_TIME_IN_SEC,
            max = MAX_DURATION_APP_UPGRADE_MSG_TIME_IN_SEC
    )
    @NotEmptyIfNotNull
    private Long durationOfAppUpgradeMessageInSec;

    @Column(name = UPLOAD_DEMOGRAPHIC_DATA_INTERVAL_IN_MIN)
    @JsonProperty(UPLOAD_DEMOGRAPHIC_DATA_INTERVAL_IN_MIN)
    @LongRange(
            min = 15,  // 15 mins
            max = TWENTY_FOUR_HRS_IN_MINS,
            jsonKey = UPLOAD_DEMOGRAPHIC_DATA_INTERVAL_IN_MIN
    )
    @NotEmptyIfNotNull
    private Long uploadDemographicDataIntervalInMins;

    @Column(name = "default_layout_id")
    @JsonProperty("defaultLayoutId")
    @NotEmptyIfNotNull
    private Long defaultLayoutId;
    /**
     * Create a config for default customer config when a customer
     * is created
     *
     * @param customerId      {@link Long}
     * @param createdByUserId {@link Long}
     * @return {@link DeviceConfig}
     */
    public static DeviceConfig globalDefault(Long customerId, Long createdByUserId) {
        DeviceConfig g = new DeviceConfig();
        g.setCreatedBy(createdByUserId);  // 1
        g.setCreatedOn(Timestamp.from(Instant.now())); // 2
        g.setDeviceId(null); // global config means deviceId = null 3
        g.setLogUploadIntervalInMinutes(60L); // 4
        g.setLogUploadStartTime(Time.valueOf("02:00:00")); // 5
        g.setPlanogramSyncIntervalInMinutes(30L); // 6
        g.setPlanogramSyncStartTime(Time.valueOf("02:30:00")); // 7
        g.setPanelOnTime(Time.valueOf("09:00:00")); // 8
        g.setPanelOffTime(Time.valueOf("18:00:00")); // 9
        g.setBusinessOnTime(Time.valueOf("09:00:00")); // 10
        g.setBusinessOffTime(Time.valueOf("18:00:00")); // 11
        g.setScreenCaptureIntervalTime(60L); // 12
        g.setErrorUpdateIntervalTime(TimeUnit.HOURS.toMinutes(1)); // 13
        g.setDisplayLogIntervalTime(TimeUnit.HOURS.toMinutes(1)); // 14
        g.setCustomerId(customerId);  // 15
        g.setTouchScreenWebViewUrl(null); // should not be set by default 16
        g.setTouchScreenWebViewNoActionTimeoutInSeconds(null); // should not be set by default 17
        g.setFrequencyOfPanelStatusUpdate(TimeUnit.HOURS.toSeconds(2)); // 18
        g.setFrequencyOfSnapshotsUpdate(TimeUnit.HOURS.toSeconds(2));  // 19
        g.setFrequencyOfDataCollectionUpdate(TimeUnit.HOURS.toSeconds(2)); // 20
        g.setContentPlaybackSyncInterval(TimeUnit.HOURS.toSeconds(2)); // 21
        g.setEnableProofOfPlaySnapshots(Boolean.FALSE); // 22
        g.setDurationOfAppUpgradeMessageInSec(60L); // 23
        g.setUploadDemographicDataIntervalInMins(TimeUnit.HOURS.toMinutes(1L)); // 23
        //g.setConfigUpdated(Boolean.FALSE); // 25
        g.setZoomPercentForWebview(ApplicationConstants.DEFAULT_ZOOM_WEBVIEW);
        g.setDefaultLayoutId(null);
        return g;
    }

    public static DeviceConfig copy(DeviceConfig c) {
        return copy(c, null, null);
    }

    public static DeviceConfig copy(DeviceConfig c, Long deviceId,
                                    Long createdByUserId) throws CopyingPropertiesException {
        DeviceConfig copy = new DeviceConfig();
        try {
            BeanUtilsBean2.getInstance().copyProperties(copy, c);
        } catch (IllegalAccessException | InvocationTargetException e) {
            logger.error("", e);
            throw new CopyingPropertiesException(e);
        }
        copy.setDeviceId(deviceId);
        copy.setCreatedBy(createdByUserId);
        //copy.setCreatedOn(Timestamp.from(Instant.now()));
        //copy.setLogUploadIntervalInMinutes(c.getLogUploadIntervalInMinutes());
        //copy.setLogUploadStartTime(c.getLogUploadStartTime());
        //copy.setPlanogramSyncIntervalInMinutes(c.getPlanogramSyncIntervalInMinutes());
        //copy.setPlanogramSyncStartTime(c.getPlanogramSyncStartTime());
        //copy.setPanelOnTime(c.getPanelOnTime());
        //copy.setPanelOffTime(c.getPanelOffTime());
        //copy.setBusinessOnTime(c.getBusinessOnTime());
        //copy.setBusinessOffTime(c.getBusinessOffTime());
        //copy.setScreenCaptureIntervalTime(c.getScreenCaptureIntervalTime());
        //copy.setErrorUpdateIntervalTime(c.getErrorUpdateIntervalTime());
        //copy.setDisplayLogIntervalTime(c.getDisplayLogIntervalTime());
        //copy.setCustomerId(c.getCustomerId());
        //copy.setTouchScreenWebViewUrl(c.getTouchScreenWebViewUrl());
        //copy.setTouchScreenWebViewNoActionTimeoutInSeconds(
        //    c.getTouchScreenWebViewNoActionTimeoutInSeconds());
        //copy.setFrequencyOfPanelStatusUpdate(c.getFrequencyOfPanelStatusUpdate());
        //copy.setFrequencyOfSnapshotsUpdate(c.getFrequencyOfSnapshotsUpdate());
        //copy.setFrequencyOfDataCollectionUpdate(c.getFrequencyOfDataCollectionUpdate());
        //copy.setContentPlaybackSyncInterval(c.getContentPlaybackSyncInterval());
        //copy.setEnableProofOfPlaySnapshots(c.getEnableProofOfPlaySnapshots());
        //copy.setConfigUpdated(Boolean.FALSE);
        return copy;
    }

    @JsonProperty("facebookKeyUpdateTimestamp")
    public Date getFacebookKeyUpdateTimestamp() {
        return facebookKeyUpdateTimestamp;
    }

    @JsonIgnore
    public void setFacebookKeyUpdateTimestamp(Date facebookKeyUpdateTimestamp) {
        this.facebookKeyUpdateTimestamp = facebookKeyUpdateTimestamp;
    }

    /*public Week[] getWeekOffs() {
        return weekOffs;
    }

    public void setWeekOffs(Week[] weekOffs) {
        this.weekOffs = weekOffs;
    }*/

    public Boolean getEnableProofOfPlaySnapshots() {
        return enableProofOfPlaySnapshots;
    }

    public void setEnableProofOfPlaySnapshots(Boolean enableProofOfPlaySnapshots) {
        this.enableProofOfPlaySnapshots = enableProofOfPlaySnapshots;
    }

    public Long getFrequencyOfPanelStatusUpdate() {
        return frequencyOfPanelStatusUpdate;
    }

    public void setFrequencyOfPanelStatusUpdate(Long frequencyOfPanelStatusUpdate) {
        this.frequencyOfPanelStatusUpdate = frequencyOfPanelStatusUpdate;
    }

    public Long getFrequencyOfSnapshotsUpdate() {
        return frequencyOfSnapshotsUpdate;
    }

    public void setFrequencyOfSnapshotsUpdate(Long frequencyOfSnapshotsUpdate) {
        this.frequencyOfSnapshotsUpdate = frequencyOfSnapshotsUpdate;
    }

    public Long getFrequencyOfDataCollectionUpdate() {
        return frequencyOfDataCollectionUpdate;
    }

    public void setFrequencyOfDataCollectionUpdate(Long frequencyOfDataCollectionUpdate) {
        this.frequencyOfDataCollectionUpdate = frequencyOfDataCollectionUpdate;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Time getPanelOnTime() {
        return panelOnTime;
    }

    public void setPanelOnTime(Time panelOnTime) {
        this.panelOnTime = panelOnTime;
    }

    public Time getPanelOffTime() {
        return panelOffTime;
    }

    public void setPanelOffTime(Time panelOffTime) {
        this.panelOffTime = panelOffTime;
    }

    public Time getBusinessOnTime() {
        return businessOnTime;
    }

    public void setBusinessOnTime(Time businessOnTime) {
        this.businessOnTime = businessOnTime;
    }

    public Time getBusinessOffTime() {
        return businessOffTime;
    }

    public void setBusinessOffTime(Time businessOffTime) {
        this.businessOffTime = businessOffTime;
    }

    public Long getScreenCaptureIntervalTime() {
        return screenCaptureIntervalTime;
    }

    public void setScreenCaptureIntervalTime(Long screenCaptureIntervalTime) {
        this.screenCaptureIntervalTime = screenCaptureIntervalTime;
    }

    public Long getErrorUpdateIntervalTime() {
        return errorUpdateIntervalTime;
    }

    public void setErrorUpdateIntervalTime(Long errorUpdateIntervalTime) {
        this.errorUpdateIntervalTime = errorUpdateIntervalTime;
    }

    public Long getDisplayLogIntervalTime() {
        return displayLogIntervalTime;
    }

    public void setDisplayLogIntervalTime(Long displayLogIntervalTime) {
        this.displayLogIntervalTime = displayLogIntervalTime;
    }

    public Long getDeviceConfigId() {
        return deviceConfigId;
    }

    public void setDeviceConfigId(Long deviceConfigId) {
        this.deviceConfigId = deviceConfigId;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public Time getLogUploadStartTime() {
        return logUploadStartTime;
    }

    public void setLogUploadStartTime(Time logUploadStartTime) {
        this.logUploadStartTime = logUploadStartTime;
    }

    public Long getLogUploadIntervalInMinutes() {
        return logUploadIntervalInMinutes;
    }

    public void setLogUploadIntervalInMinutes(Long logUploadIntervalInMinutes) {
        this.logUploadIntervalInMinutes = logUploadIntervalInMinutes;
    }

    public Time getPlanogramSyncStartTime() {
        return planogramSyncStartTime;
    }

    public void setPlanogramSyncStartTime(Time planogramSyncStartTime) {
        this.planogramSyncStartTime = planogramSyncStartTime;
    }

    public Long getPlanogramSyncIntervalInMinutes() {
        return planogramSyncIntervalInMinutes;
    }

    public void setPlanogramSyncIntervalInMinutes(Long planogramSyncIntervalInMinutes) {
        this.planogramSyncIntervalInMinutes = planogramSyncIntervalInMinutes;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Timestamp getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }

    public String getTouchScreenWebViewUrl() {
        return touchScreenWebViewUrl;
    }

    public void setTouchScreenWebViewUrl(String touchScreenWebViewUrl) {
        this.touchScreenWebViewUrl = touchScreenWebViewUrl;
    }

    public Long getTouchScreenWebViewNoActionTimeoutInSeconds() {
        return touchScreenWebViewNoActionTimeoutInSeconds;
    }

    public void setTouchScreenWebViewNoActionTimeoutInSeconds(
            Long touchScreenWebViewNoActionTimeoutInSeconds) {
        this.touchScreenWebViewNoActionTimeoutInSeconds = touchScreenWebViewNoActionTimeoutInSeconds;
    }

    public DeviceConfig clone() {
        try {
            return (DeviceConfig) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    public Time getScreenshotUploadTime() {
        return screenshotUploadTime;
    }

    public void setScreenshotUploadTime(Time screenshotUploadTime) {
        this.screenshotUploadTime = screenshotUploadTime;
    }

    public Long getContentPlaybackSyncInterval() {
        return contentPlaybackSyncInterval;
    }

    public void setContentPlaybackSyncInterval(Long contentPlaybackSyncInterval) {
        this.contentPlaybackSyncInterval = contentPlaybackSyncInterval;
    }

    public Boolean getStopAutoLaunchingApp() {
        return stopAutoLaunchingApp;
    }

    public Long getDurationOfAppUpgradeMessageInSec() {
        return durationOfAppUpgradeMessageInSec;
    }

    public void setDurationOfAppUpgradeMessageInSec(Long durationOfAppUpgradeMessageInSec) {
        this.durationOfAppUpgradeMessageInSec = durationOfAppUpgradeMessageInSec;
    }

    public void setStopAutoLaunchingApp(Boolean stopAutoLaunchingApp) {
        this.stopAutoLaunchingApp = stopAutoLaunchingApp;
    }

    public Integer getZoomPercentForWebview() {
        return zoomPercentForWebview;
    }

    public void setZoomPercentForWebview(Integer zoomPercentForWebview) {
        this.zoomPercentForWebview = zoomPercentForWebview;
    }

    @Override
    public String toString() {
        return "DeviceConfig{"
                +
                "logUploadIntervalInMinutes="
                + logUploadIntervalInMinutes
                +
                ", planogramSyncIntervalInMinutes="
                + planogramSyncIntervalInMinutes
                +
                ", screenCaptureIntervalTime="
                + screenCaptureIntervalTime
                +
                ", errorUpdateIntervalTime="
                + errorUpdateIntervalTime
                +
                ", displayLogIntervalTime="
                + displayLogIntervalTime
                +
                ", touchScreenWebViewUrl='"
                + touchScreenWebViewUrl
                + '\''
                +
                ", touchScreenWebViewNoActionTimeoutInSeconds="
                + touchScreenWebViewNoActionTimeoutInSeconds
                +
                ", frequencyOfPanelStatusUpdate="
                + frequencyOfPanelStatusUpdate
                +
                ", frequencyOfSnapshotsUpdate="
                + frequencyOfSnapshotsUpdate
                +
                ", frequencyOfDataCollectionUpdate="
                + frequencyOfDataCollectionUpdate
                +
                ", contentPlaybackSyncInterval="
                + contentPlaybackSyncInterval
                +
                ", deviceConfigId="
                + deviceConfigId
                +
                ", deviceId="
                + deviceId
                +
                ", logUploadStartTime="
                + logUploadStartTime
                +
                ", planogramSyncStartTime="
                + planogramSyncStartTime
                +
                ", createdBy="
                + createdBy
                +
                ", createdOn="
                + createdOn
                +
                ", panelOnTime="
                + panelOnTime
                +
                ", panelOffTime="
                + panelOffTime
                +
                ", businessOnTime="
                + businessOnTime
                +
                ", businessOffTime="
                + businessOffTime
                +
                ", customerId="
                + customerId
                +
                ", screenshotUploadTime="
                + screenshotUploadTime
                +
                ", enableProofOfPlaySnapshots="
                + enableProofOfPlaySnapshots
                +
                ", weekOffs="
                + Arrays.toString(weekOffs)
                +
                ", facebookKeyUpdateTimestamp="
                + facebookKeyUpdateTimestamp
                +
                ", stopAutoLaunchingApp="
                + stopAutoLaunchingApp
                +
                ", uploadDemographicDataIntervalInMins="
                + uploadDemographicDataIntervalInMins
                +
                ", zoomPercentForWebview="
                + zoomPercentForWebview
                +
                ", defaultLayoutId="
                + defaultLayoutId
                +
                '}';
    }

    @JsonProperty("touchScreenWebViewUrlSchema")
    public String getTouchScreenWebViewUrlSchema() {
        if (touchScreenWebViewUrl == null) return null;
        return touchScreenWebViewUrl.split("://")[0];
    }

    @JsonProperty("touchScreenWebViewUrlPath")
    public String getTouchScreenWebViewUrlPath() {
        if (touchScreenWebViewUrl == null) return null;
        String[] arr = touchScreenWebViewUrl.split("://");
        if (arr.length > 1) {
            return arr[1];
        } else {
            return "";
        }
    }

    public Long getUploadDemographicDataIntervalInMins() {
        return uploadDemographicDataIntervalInMins;
    }

    public void setUploadDemographicDataIntervalInMins(Long uploadDemographicDataIntervalInMins) {
        this.uploadDemographicDataIntervalInMins = uploadDemographicDataIntervalInMins;
    }

    public Long getDefaultLayoutId() {
        return defaultLayoutId;
    }

    public void setDefaultLayoutId(Long defaultLayoutId) {
        this.defaultLayoutId = defaultLayoutId;
    }
}

