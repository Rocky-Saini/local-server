package com.digital.signage.models;


import com.digital.signage.annotations.PdfColumn;
import com.digital.signage.annotations.PdfTitle;
import com.digital.signage.annotations.ReportColumn;
import com.digital.signage.annotations.ReportSimpleDateFormat;
import com.digital.signage.util.PlayBackStatus;
import com.digital.signage.util.ReportConstants;
import com.digital.signage.util.WidgetType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = ContentPlaybackActuals.TABLE_NAME)
@PdfTitle(title = ReportConstants.CONTENT_PLAYBACK_REPORT_TITLE)
public class ContentPlaybackActuals {
    public static final String TABLE_NAME = "content_playback_actuals_report";

    public static final String COL_ID = "content_playback_actuals_id";
    public static final String COL_PLANO_ID = "planogram_id";
    public static final String COL_PLANO_NAME = "planogram_name";
    public static final String COL_DEVICE_ID = "device_id";
    public static final String COL_DEVICE_NAME = "device_name";
    public static final String COL_DEVICE_GROUP_ID = "device_group_id";
    public static final String COL_DEVICE_GROUP_NAME = "device_group_name";
    public static final String COL_LAYOUT_ID = "campaign_id";
    public static final String COL_LAYOUT_NAME = "campaign_title";
    public static final String COL_REGION_ID = "region_id";
    public static final String COL_REGION_NAME = "layout_region_name";
    public static final String COL_CONTENT_ID = "content_id";
    public static final String COL_CONTENT_TYPE = "content_type";
    public static final String COL_CONTENT_NAME = "content_name";
    public static final String COL_START_TIME = "start_time";
    public static final String COL_END_TIME = "end_time";
    public static final String COL_DATE = "date";
    public static final String COL_START_DATE_TIME = "start_date_time";
    public static final String COL_END_DATE_TIME = "end_date_time";
    public static final String COL_LOCATION_ID = "location_id";
    public static final String COL_LOCATION_NAME = "location_name";
    public static final String COL_IS_PLAYED = "is_played";
    public static final String COL_CONTENT_LAST_PLAYED = "content_last_played";
    public static final String COL_REASON_FOR_FAILURE = "reason_for_failure";
    public static final String COL_SNAPSHOT_START_TIME = "snapshot_start_time";
    public static final String COL_SNAPSHOT_END_TIME = "snapshot_end_time";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = COL_ID)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY, value = "contentPlaybackActualsId")
    private Long contentPlaybackActualsId;

    //@ReportColumn(columnName = "Planogram Id", order = 9)
    //@PdfColumn(columnName = "Planogram Id", order = 9)
    @Column(name = COL_PLANO_ID)
    private Long planogramId;

    @ReportColumn(columnName = "Planogram Name", order = 8)
    @PdfColumn(columnName = "Planogram Name", order = 8)
    @Column(name = COL_PLANO_NAME)
    private String planogramName;

    @Column(name = COL_DEVICE_ID)
    private Long deviceId;

    @Column(name = COL_DEVICE_NAME)
    @ReportColumn(columnName = "Media Player", order = 1)
    @PdfColumn(columnName = "Media Player", order = 1)
    private String deviceName;

    @Column(name = COL_DEVICE_GROUP_ID)
    private Long deviceGroupId;

    @Column(name = COL_DEVICE_GROUP_NAME)
    @ReportColumn(columnName = "Group", order = 3)
    @PdfColumn(columnName = "Group", order = 3)
    private String deviceGroupName;

    @JsonProperty("campaignId")
    @Column(name = COL_LAYOUT_ID)
    private Long layoutId;

    @JsonProperty("campaignName")
    @Column(name = "campaign_name")
    @ReportColumn(columnName = "Campaign Name", order = 10)
    @PdfColumn(columnName = "Campaign Name", order = 10)
    private String layoutName;

    @JsonProperty("regionId")
    @Column(name = COL_REGION_ID)
    private Long regionId;

    @ReportColumn(columnName = "Region Name", order = 11)
    @PdfColumn(columnName = "Region Name", order = 11)
    @Column(name = COL_REGION_NAME)
    @JsonProperty("regionName")
    private String layoutRegionName;

    @Column(name = COL_CONTENT_ID)
    private Long contentId;

    @Column(name = COL_CONTENT_TYPE)
    @ReportColumn(columnName = "Content Type", order = 13)
    @PdfColumn(columnName = "Content Type", order = 13)
    private String contentType;

    @Column(name = COL_CONTENT_NAME)
    @ReportColumn(columnName = "Content Name", order = 12)
    @PdfColumn(columnName = "Content Name", order = 12)
    private String contentName;

    @Column(name = COL_START_TIME)
    @ReportColumn(columnName = "Start Time", order = 5)
    @PdfColumn(columnName = "Start Time", order = 5)
    @JsonProperty("startTimeOfCampaign")
    // this is just time. Ex: 19:00
    private String startTime;

    @Column(name = COL_END_TIME)
    @ReportColumn(columnName = "End Time", order = 6)
    @PdfColumn(columnName = "End Time", order = 6)
    @JsonProperty("endTimeOfCampaign")
    // this is just time. Ex: 19:00
    private String endTime;

    @Transient
    @ReportColumn(columnName = "Duration", order = 7)
    @PdfColumn(columnName = "Duration", order = 7)
    private String duration;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "IST")
    @Column(name = COL_DATE)
    @ReportColumn(columnName = "Date", order = 4)
    @PdfColumn(columnName = "Date", order = 4)
    @ReportSimpleDateFormat
    // this is a date without time. Ex: 12-12-2019
    private Date date;

    //Only for DB
    @Column(name = COL_START_DATE_TIME)
    //@JsonIgnore
    // this is combination of startTime and date
    private Date dateTime;

    //Only for DB
    @Column(name = COL_END_DATE_TIME)
    //@JsonIgnore
    // this is combination of endTime and date
    private Date endDateTime;

    @Column(name = COL_LOCATION_ID)
    private Long locationId;

    @ReportColumn(columnName = "Location", order = 2)
    @PdfColumn(columnName = "Location", order = 2)
    @Column(name = COL_LOCATION_NAME)
    private String locationName;

    @ReportColumn(columnName = "Playback Status", order = 14)
    @PdfColumn(columnName = "Playback Status", order = 14)
    @Column(name = COL_IS_PLAYED)
    @JsonProperty("playbackStatus")
    private PlayBackStatus isPlayed;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "IST")
    @ReportColumn(columnName = "content Last Played", order = 15)
    @PdfColumn(columnName = "content Last Played", order = 15)
    @ReportSimpleDateFormat
    @Column(name = COL_CONTENT_LAST_PLAYED)
    private Date contentLastPlayed;

    @ReportColumn(columnName = "Reason For Failure Or Proof Of PlayLink", order = 16)
    @PdfColumn(columnName = "Reason For Failure Or Proof Of PlayLink", order = 16)
    @Column(name = COL_REASON_FOR_FAILURE)
    private String reasonForFailure;

    @Column(name = COL_SNAPSHOT_START_TIME)
    private Date snapshotStartTime;

    @Column(name = COL_SNAPSHOT_END_TIME)
    private Date snapshotEndTime;

    @Transient
    private WidgetType widgetType;

    public Date getSnapshotStartTime() {
        return snapshotStartTime;
    }

    public void setSnapshotStartTime(Date snapshotStartTime) {
        this.snapshotStartTime = snapshotStartTime;
    }

    public Date getSnapshotEndTime() {
        return snapshotEndTime;
    }

    public void setSnapshotEndTime(Date snapshotEndTime) {
        this.snapshotEndTime = snapshotEndTime;
    }

    public Long getContentPlaybackActualsId() {
        return contentPlaybackActualsId;
    }

    public void setContentPlaybackActualsId(Long contentPlaybackActualsId) {
        this.contentPlaybackActualsId = contentPlaybackActualsId;
    }

    public Long getPlanogramId() {
        return planogramId;
    }

    public void setPlanogramId(Long planogramId) {
        this.planogramId = planogramId;
    }

    public String getPlanogramName() {
        return planogramName;
    }

    public void setPlanogramName(String planogramName) {
        this.planogramName = planogramName;
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

    public String getLayoutName() {
        return layoutName;
    }

    public void setLayoutName(String layoutName) {
        this.layoutName = layoutName;
    }

    public Long getLayoutId() {
        return layoutId;
    }

    public void setLayoutId(Long layoutId) {
        this.layoutId = layoutId;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getContentName() {
        return contentName;
    }

    public void setContentName(String contentName) {
        this.contentName = contentName;
    }

    public Long getContentId() {
        return contentId;
    }

    public void setContentId(Long contentId) {
        this.contentId = contentId;
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

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public PlayBackStatus getIsPlayed() {
        return isPlayed;
    }

    public void setIsPlayed(PlayBackStatus played) {
        isPlayed = played;
    }

    public Date getContentLastPlayed() {
        return contentLastPlayed;
    }

    public void setContentLastPlayed(Date contentLastPlayed) {
        this.contentLastPlayed = contentLastPlayed;
    }

    public String getReasonForFailure() {
        return reasonForFailure;
    }

    public void setReasonForFailure(String reasonForFailureOrProofOfPlayLink) {
        this.reasonForFailure = reasonForFailureOrProofOfPlayLink;
    }

    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }

    public String getLayoutRegionName() {
        return layoutRegionName;
    }

    public void setLayoutRegionName(String layoutRegionName) {
        this.layoutRegionName = layoutRegionName;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public Date getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(Date endDateTime) {
        this.endDateTime = endDateTime;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public WidgetType getWidgetType() {
        return widgetType;
    }

    public void setWidgetType(WidgetType widgetType) {
        this.widgetType = widgetType;
    }

    @Override
    public String toString() {
        return "ContentPlaybackActuals{" +
                "contentPlaybackActualsId=" + contentPlaybackActualsId +
                ", planogramId=" + planogramId +
                ", planogramName='" + planogramName + '\'' +
                ", deviceId=" + deviceId +
                ", deviceName='" + deviceName + '\'' +
                ", deviceGroupName='" + deviceGroupName + '\'' +
                ", deviceGroupId=" + deviceGroupId +
                ", layoutName='" + layoutName + '\'' +
                ", layoutId=" + layoutId +
                ", regionId=" + regionId +
                ", layoutRegionName='" + layoutRegionName + '\'' +
                ", contentType='" + contentType + '\'' +
                ", contentName='" + contentName + '\'' +
                ", contentId=" + contentId +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", date=" + date +
                ", dateTime=" + dateTime +
                ", locationName='" + locationName + '\'' +
                ", locationId=" + locationId +
                ", isPlayed=" + isPlayed +
                ", contentLastPlayed=" + contentLastPlayed +
                ", reasonForFailure='" + reasonForFailure + '\'' +
                ", snapshotStartTime=" + snapshotStartTime +
                ", snapshotEndTime=" + snapshotEndTime +
                '}';
    }
}
