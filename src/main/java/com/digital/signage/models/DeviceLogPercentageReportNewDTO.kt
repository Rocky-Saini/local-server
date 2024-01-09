package com.digital.signage.models

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.digital.signage.annotations.*
import com.digital.signage.util.ReportConstants
import java.util.Date
import javax.persistence.*

@Entity(name = "device_per_report")
@Table(name = "device_per_report")
@PdfTitle(title = ReportConstants.MP_PER_REPORT_TITLE)
data class DeviceLogPercentageReportNewDTO(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = COL_DEVICE_LOG_ID)
    @JsonIgnore
    @JsonIgnoreProperties(COL_DEVICE_LOG_ID)
    var deviceLogId: Long? = null,

    @Column(name = COL_DEVICE_ID)
    var deviceId: Long? = null,

    @ReportColumn(order = 1, columnName = "MP Name")
    @PdfColumn(order = 1, columnName = "MP Name")
    @Column(name = COL_DEVICE_NAME)
    var deviceName: String? = null,

    @Column(name = COL_LOCATION_ID)
    var locationId: Long? = null,

    @Column(name = COL_LOCATION_NAME)
    @ReportColumn(order = 2, columnName = "Location")
    @PdfColumn(order = 2, columnName = "Location")
    var locationName: String? = null,

    @JsonProperty(value = "deviceGroupId")
    @Column(name = COL_DEVICE_GROUP_ID)
    var deviceGroupId: Long? = null,

    @JsonProperty(value = "deviceGroupName")
    @Column(name = COL_DEVICE_GROUP_NAME)
    @ReportColumn(order = 3, columnName = "MP Group")
    @PdfColumn(order = 3, columnName = "MP Group")
    var deviceGroupName: String? = null,

    @ReportColumn(order = 4, columnName = "Date")
    @PdfColumn(order = 4, columnName = "Date")
    @Column(name = COL_DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "IST")
    @ReportSimpleDateFormat(ReportConstants.DATE_FORMAT_FOR_PDF_REPORT)
    var date: Date? = null,

    @Column(name = COL_SCHEDULED_PLAYER_UP_TIME)
    @JsonProperty(value = "scheduledPlayerUpTime")
    var scheduledPlayerUpTime: String? = null,

    @ReportColumn(order = 5, columnName = "On Hrs")
    @PdfColumn(order = 5, columnName = "On Hrs")
    @Column(name = COL_ON_HRS)
    @JsonProperty(value = "onHours")
    @ReportTimeFormat(beginIndex = 0, lastIndex = 8)
    var onHours: String? = null,

    @ReportColumn(order = 6, columnName = "On %")
    @PdfColumn(order = 6, columnName = "On %")
    @JsonProperty(value = "onPercentage")
    @Column(name = COL_ON_PER)
    var onPercentage: String? = null,

    @ReportColumn(order = 7, columnName = "Off Hrs")
    @PdfColumn(order = 7, columnName = "Off Hrs")
    @Column(name = COL_OFF_HRS)
    @JsonProperty(value = "offHours")
    @ReportTimeFormat(beginIndex = 0, lastIndex = 8)
    var offHours: String? = null,

    @Column(name = COL_OFF_PER)
    @JsonProperty(value = "offPercentage")
    @ReportColumn(order = 8, columnName = "Off %")
    @PdfColumn(order = 8, columnName = "Off %")
    var offPercentage: String? = null,

    @ReportColumn(order = 9, columnName = "InActive Hrs")
    @PdfColumn(order = 9, columnName = "InActive Hrs")
    @Column(name = COL_INACTIVE_HRS)
    @JsonProperty(value = "inActiveHours")
    @ReportTimeFormat(beginIndex = 0, lastIndex = 8)
    var inActiveHours: String? = null,

    @Column(name = COL_INACTIVE_PER)
    @JsonProperty(value = "inActivePercentage")
    @ReportColumn(order = 10, columnName = "InActive  %")
    @PdfColumn(order = 10, columnName = "InActive %")
    var inActivePercentage: String? = null,

    @ReportColumn(order = 11, columnName = "Data Deleted Hrs")
    @PdfColumn(order = 11, columnName = "Data Deleted Hrs")
    @Column(name = COL_DATA_DELETED_HRS)
    @JsonProperty(value = "dataDeletedHours")
    @ReportTimeFormat(beginIndex = 0, lastIndex = 8)
    var dataDeletedHours: String? = null,

    @Column(name = COL_DATA_DELETED_PER)
    @JsonProperty(value = "dataDeletedPercentage")
    @ReportColumn(order = 12, columnName = "Data Deleted  %")
    @PdfColumn(order = 12, columnName = "Data Deleted  %")
    var dataDeletedPercentage: String? = null,

    @ReportColumn(order = 13, columnName = "Not Applicable Hrs")
    @PdfColumn(order = 13, columnName = "Not Applicable Hrs")
    @Column(name = COL_NOT_APPLICABLE_HRS)
    @JsonProperty(value = "notApplicableHours")
    @ReportTimeFormat(beginIndex = 0, lastIndex = 8)
    var notApplicableHours: String? = null,

    @Column(name = COL_NOT_APPLICABLE_PER)
    @JsonProperty(value = "notApplicablePercentage")
    @ReportColumn(order = 14, columnName = "Not Applicable  %")
    @PdfColumn(order = 14, columnName = "Not Applicable %")
    var notApplicablePercentage: String? = null,

    @ReportColumn(order = 15, columnName = "Data Not Available Hrs")
    @PdfColumn(order = 15, columnName = "Data Not Available Hrs")
    @Column(name = COL_DATA_NOT_AVAILABLE_HRS)
    @JsonProperty(value = "dataNotAvailableHours")
    @ReportTimeFormat(beginIndex = 0, lastIndex = 8)
    var dataNotAvailableHours: String? = null,

    @Column(name = COL_DATA_NOT_AVAILABLE_PER)
    @JsonProperty(value = "dataNotAvailablePercentage")
    @ReportColumn(order = 16, columnName = "Data Not Available  %")
    @PdfColumn(order = 16, columnName = "Data Not Available %")
    var dataNotAvailablePercentage: String? = null,

    @ReportColumn(order = 17, columnName = "Week Off Hrs")
    @PdfColumn(order = 17, columnName = "Week Off Hrs")
    @Column(name = COL_WEEK_OFF_HRS)
    @JsonProperty(value = "weekOffHours")
    @ReportTimeFormat(beginIndex = 0, lastIndex = 8)
    var weekOffHours: String? = null,

    @Column(name = COL_WEEK_OFF_PER)
    @JsonProperty(value = "weekOffPercentage")
    @ReportColumn(order = 18, columnName = "Week Off %")
    @PdfColumn(order = 18, columnName = "Week Off %")
    var weekOffPercentage: String? = null,

    @JsonIgnore
    @JsonIgnoreProperties("reportToken")
    @Column(name = COL_REPORT_TOKEN)
    var reportToken: String? = null
) : EntityModel {

    constructor(
        deviceId: Long?,
        deviceName: String?,
        locationId: Long?,
        locationName: String?,
        deviceGroupId: Long?,
        deviceGroupName: String?,
        date: Date?,
        scheduledPlayerUpTime: String?,
        onHours: String?,
        offHours: String?,
        weekOffHours: String?,
        notApplicableHours: String?,
        dataNotAvailableHours: String?,
        inActiveHours: String?,
        dataDeletedHours: String?,
        onPercentage: String?,
        offPercentage: String?,
        weekOffPercentage: String?,
        notApplicablePercentage: String?,
        dataNotAvailablePercentage: String?,
        inActivePercentage: String?,
        dataDeletedPercentage: String?,
        reportToken: String?
    ) : this(
        deviceLogId = null,
        deviceId = deviceId,
        deviceName = deviceName,
        locationId = locationId,
        locationName = locationName,
        deviceGroupId = deviceGroupId,
        deviceGroupName = deviceGroupName,
        date = date,
        scheduledPlayerUpTime = scheduledPlayerUpTime,
        onHours = onHours,
        offHours = offHours,
        weekOffHours = weekOffHours,
        notApplicableHours = notApplicableHours,
        dataNotAvailableHours = dataNotAvailableHours,
        dataDeletedHours = dataDeletedHours,
        inActiveHours = inActiveHours,
        onPercentage = onPercentage,
        offPercentage = offPercentage,
        weekOffPercentage = weekOffPercentage,
        notApplicablePercentage = notApplicablePercentage,
        dataNotAvailablePercentage = dataNotAvailablePercentage,
        inActivePercentage = inActivePercentage,
        dataDeletedPercentage = dataDeletedPercentage,
        reportToken = reportToken
    )

    companion object {
        const val COL_DEVICE_LOG_ID = "device_log_id"
        const val COL_DEVICE_ID = "device_id"
        const val COL_DEVICE_NAME = "device_name"
        const val COL_LOCATION_ID = "location_id"
        const val COL_LOCATION_NAME = "location_name"
        const val COL_DEVICE_GROUP_ID = "device_group_id"
        const val COL_DEVICE_GROUP_NAME = "device_group_name"
        const val COL_DATE = "date"
        const val COL_SCHEDULED_PLAYER_UP_TIME = "scheduled_player_up_time"
        const val COL_ON_HRS = "on_hours"
        const val COL_OFF_HRS = "off_hours"
        const val COL_WEEK_OFF_HRS = "week_off_hours"
        const val COL_NOT_APPLICABLE_HRS = "not_applicable_hours"
        const val COL_DATA_NOT_AVAILABLE_HRS = "data_not_available_hours"
        const val COL_DATA_DELETED_HRS = "data_deleted_hours"
        const val COL_INACTIVE_HRS = "in_active_hours"
        const val COL_ON_PER = "on_percentage"
        const val COL_OFF_PER = "off_percentage"
        const val COL_WEEK_OFF_PER = "week_off_percentage"
        const val COL_NOT_APPLICABLE_PER = "not_applicable_percentage"
        const val COL_DATA_NOT_AVAILABLE_PER = "data_not_available_percentage"
        const val COL_INACTIVE_PER = "in_active_percentage"
        const val COL_DATA_DELETED_PER = "data_deleted_percentage"
        const val COL_REPORT_TOKEN = "report_token"
    }
}