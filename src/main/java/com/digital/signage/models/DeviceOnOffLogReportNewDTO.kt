package com.digital.signage.models

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.digital.signage.annotations.*
import com.digital.signage.exceptions.CopyingPropertiesException
import com.digital.signage.util.DataCollectionEnum
import com.digital.signage.util.ReportConstants
import org.apache.commons.beanutils.BeanUtilsBean2
import java.lang.reflect.InvocationTargetException
import java.util.*
import javax.persistence.*

@Entity(name = "device_report")
@Table(name = "device_report")
@PdfTitle(title = ReportConstants.MP_LOG_REPORT_TITLE)
data class DeviceOnOffLogReportNewDTO(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = COL_ID)
    @JsonIgnore
    var deviceLogId: Long? = null,

    @Column(name = COL_DEVICE_ID)
    var deviceId: Long? = null,

    @ReportColumn(order = 1, columnName = "MP Name")
    @PdfColumn(order = 1, columnName = "MP Name")
    @Column(name = COL_DEVICE_NAME)
    var deviceName: String? = null,

    @Column(name = COL_LOCATION_ID)
    var locationId: Long? = null,

    @ReportColumn(order = 2, columnName = "Location")
    @PdfColumn(order = 2, columnName = "Location")
    @Column(name = COL_LOCATION_NAME)
    var locationName: String? = null,

    @JsonIgnore
    @JsonIgnoreProperties("deviceGroupId")
    @Column(name = COL_DEVICE_GROUP_ID)
    var deviceGroupId: Long? = null,

    @JsonIgnore
    @JsonIgnoreProperties("deviceGroupName")
    @Column(name = COL_DEVICE_GROUP_NAME)
    var deviceGroupName: String? = null,

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "IST")
    @ReportColumn(order = 3, columnName = "Date")
    @PdfColumn(order = 3, columnName = "Date")
    @Column(name = COL_DATE)
    @ReportSimpleDateFormat(value = ReportConstants.DATE_FORMAT_FOR_PDF_REPORT)
    var date: Date? = null,

    @ReportColumn(order = 4, columnName = "Scheduled MP Uptime")
    @PdfColumn(order = 4, columnName = "Scheduled MP Uptime")
    @Column(name = COL_SCHEDULE_PLAYER_UP_TIME)
    @JsonProperty(value = "scheduledPlayerUpTime")
    var scheduledPlayerUpTime: String? = null,

    @ReportColumn(order = 5, columnName = "MP Status Summary")
    @PdfColumn(order = 5, columnName = "MP Status Summary")
    //@NewLineConverter
    @Column(name = COL_TOTAL_SUMMARY)
    @JsonProperty(value = "totalSummary")
    var totalSummary: String? = null,

    @ReportColumn(order = 6, columnName = "MP Status")
    @PdfColumn(order = 6, columnName = "MP Status")
    @Column(name = COL_DEVICE_STATUS)
    @Enumerated(EnumType.STRING)
    var deviceStatus: DataCollectionEnum.OutputStatus? = null,

    @ReportColumn(order = 7, columnName = "From Time")
    @PdfColumn(order = 7, columnName = "From Time")
    @Column(name = COL_START_TIME)
    @JsonProperty(value = "startTime")
    var startTime: String? = null,

    @ReportColumn(order = 8, columnName = "To Time")
    @PdfColumn(order = 8, columnName = "To Time")
    @Column(name = COL_END_TIME)
    @JsonProperty(value = "endTime")
    var endTime: String? = null,

    @ReportColumn(order = 9, columnName = "Duration")
    @PdfColumn(order = 9, columnName = "Duration")
    @Column(name = COL_DURATION)
    var duration: String? = null,

    @ReportColumn(order = 10, columnName = "Reason For Failure")
    @PdfColumn(order = 10, columnName = "Reason For Failure")
    @Column(name = COL_REASON_FOR_OFF_OR_DISCONNECTION)
    //@ConvertToLocalizedString
    var reasonForOffOrDisconnection: String? = null,

    @JsonIgnore
    @JsonIgnoreProperties("isDeviceDown")
    @Column(name = COL_IS_DEVICE_DOWN)
    @set:JvmName("setIsDeviceDown")
    @get:JvmName("getIsDeviceDown")
    var isDeviceDown: Boolean? = null,

    @JsonIgnore
    @JsonIgnoreProperties("reportToken")
    @Column(name = COL_REPORT_TOKEN)
    var reportToken: String? = null,

    @JsonIgnore
    @JsonIgnoreProperties("durationInSeconds")
    @Column(name = COL_DURATION_IN_SECONDS)
    var durationInSeconds: Long? = null,

    @JsonIgnore
    @JsonIgnoreProperties("scheduledUpTimeInSecond")
    @Column(name = COL_SCHEDULED_UP_TIME_IN_SECONDS)
    var scheduledUpTimeInSecond: Long? = null
) {
    companion object {
        const val COL_ID = "device_log_id"
        const val COL_DEVICE_ID = "device_id"
        const val COL_DEVICE_NAME = "device_name"
        const val COL_LOCATION_ID = "location_id"
        const val COL_LOCATION_NAME = "location_name"
        const val COL_DEVICE_GROUP_ID = "device_group_id"
        const val COL_DEVICE_GROUP_NAME = "device_group_name"
        const val COL_DATE = "date"
        const val COL_SCHEDULE_PLAYER_UP_TIME = "scheduled_player_up_time"
        const val COL_START_TIME = "start_time"
        const val COL_END_TIME = "end_time"
        const val COL_DURATION = "duration"
        const val COL_DEVICE_STATUS = "device_status"
        const val COL_REASON_FOR_OFF_OR_DISCONNECTION = "reason_for_off_or_disconnection"
        const val COL_IS_DEVICE_DOWN = "is_device_down"
        const val COL_REPORT_TOKEN = "report_token"
        const val COL_DURATION_IN_SECONDS = "duration_in_seconds"
        const val COL_SCHEDULED_UP_TIME_IN_SECONDS = "scheduled_up_time_in_second"
        const val COL_TOTAL_SUMMARY = "total_summary"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val that = other as DeviceOnOffLogReportNewDTO?
        return deviceId == that!!.deviceId
                && deviceName == that.deviceName
                && (locationId == null || locationId == that.locationId)
                && (locationName == null || locationName == that.locationName)
                && (deviceGroupId == null || deviceGroupId == that.deviceGroupId)
                && (deviceGroupName == null || deviceGroupName == that.deviceGroupName)
                && date == that.date
                && scheduledPlayerUpTime == that.scheduledPlayerUpTime
                && startTime == that.startTime
                && endTime == that.endTime
                && duration == that.duration
                && deviceStatus == that.deviceStatus
                && (reasonForOffOrDisconnection == null || reasonForOffOrDisconnection == that.reasonForOffOrDisconnection)
                && (isDeviceDown == null || isDeviceDown == that.isDeviceDown)
                && (reportToken == null || reportToken == that.reportToken)
                && (durationInSeconds == null || durationInSeconds == that.durationInSeconds)
                && (scheduledUpTimeInSecond == null || scheduledUpTimeInSecond == that.scheduledUpTimeInSecond)
    }

    override fun hashCode(): Int =
        Objects.hash(
            deviceLogId,
            deviceId,
            deviceName,
            locationId,
            locationName,
            deviceGroupId,
            deviceGroupName,
            date,
            scheduledPlayerUpTime,
            startTime,
            endTime,
            duration,
            deviceStatus,
            reasonForOffOrDisconnection,
            isDeviceDown,
            reportToken,
            durationInSeconds,
            scheduledUpTimeInSecond
        )

    fun createMyCopy(): DeviceOnOffLogReportNewDTO {
        val copy = DeviceOnOffLogReportNewDTO()
        try {
            BeanUtilsBean2.getInstance().copyProperties(copy, this)
        } catch (e: IllegalAccessException) {
            throw CopyingPropertiesException(e)
        } catch (e: InvocationTargetException) {
            throw CopyingPropertiesException(e)
        }
        return copy
    }
}