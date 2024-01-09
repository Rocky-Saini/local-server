package com.digital.signage.models

import com.digital.signage.annotations.PdfColumn
import com.digital.signage.annotations.PdfTitle
import com.digital.signage.annotations.ReportColumn
import com.digital.signage.annotations.ReportSimpleDateFormat
import com.digital.signage.exceptions.CopyingPropertiesException
import com.digital.signage.report.ConvertToLocalizedString
import com.digital.signage.report.NewLineConverter
import com.digital.signage.util.DataCollectionEnum
import com.digital.signage.util.ReportConstants
import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import org.apache.commons.beanutils.BeanUtilsBean2
import java.lang.reflect.InvocationTargetException
import java.util.*
import javax.persistence.*

@Entity(name = "panel_log_report")
@Table(name = "panel_log_report")
@PdfTitle(title = ReportConstants.PANEL_ON_OFF_REPORT_TITLE)
data class PanelLogReportResponseNewDTO(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = COL_PANEL_LOG_ID)
    @JsonIgnore
    @JsonIgnoreProperties(COL_PANEL_LOG_ID)
    var panelLogId: Long? = null,

    @Column(name = COL_DEVICE_ID)
    var deviceId: Long? = null,

    @ReportColumn(order = 1, columnName = "Panel name")
    @PdfColumn(order = 1, columnName = "Panel name")
    @Column(name = COL_PANEL_NAME)
    var panelName: String? = null,

    @ReportColumn(order = 2, columnName = "MP Name")
    @PdfColumn(order = 2, columnName = "MP Name")
    @Column(name = COL_DEVICE_NAME)
    var deviceName: String? = null,

    @Column(name = COL_LOCATION_ID)
    var locationId: Long? = null,

    @ReportColumn(order = 3, columnName = "Location")
    @PdfColumn(order = 3, columnName = "Location")
    @Column(name = COL_LOCATION_NAME)
    var locationName: String? = null,

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "IST")
    @ReportColumn(order = 4, columnName = "Date")
    @PdfColumn(order = 4, columnName = "Date")
    @Column(name = COL_DATE)
    @ReportSimpleDateFormat
    var date: Date? = null,

    @ReportColumn(order = 5, columnName = "Scheduled Panel UpTime")
    @PdfColumn(order = 5, columnName = "Scheduled Panel UpTime")
    @Column(name = COL_SCHEDULED_PLAYER_UP_TIME)
    @JsonProperty("scheduledPanelUpTime")
    var scheduledPlayerUpTime: String? = null,

    @ReportColumn(order = 6, columnName = "Panel Status Summary")
    @PdfColumn(order = 6, columnName = "Panel Status Summary")
    @NewLineConverter
    @Column(name = COL_TOTAL_SUMMARY)
    var totalSummary: String? = null,

    @ReportColumn(order = 7, columnName = "Panel Status")
    @PdfColumn(order = 7, columnName = "Panel Status")
    @NewLineConverter
    @Column(name = COL_PANEL_STATUS)
    @Enumerated(EnumType.STRING)
    var panelStatus: DataCollectionEnum.OutputStatus? = null,

    @ReportColumn(order = 8, columnName = "From Time")
    @PdfColumn(order = 8, columnName = "From Time")
    @Column(name = COL_START_TIME)
    @JsonProperty(value = "startTime")
    var startTime: String? = null,

    @ReportColumn(order = 9, columnName = "To Time")
    @PdfColumn(order = 9, columnName = "To Time")
    @Column(name = COL_END_TIME)
    @JsonProperty(value = "endTime")
    var endTime: String? = null,

    @ReportColumn(order = 10, columnName = "Duration")
    @PdfColumn(order = 10, columnName = "Duration")
    @Column(name = COL_DURATION)
    var duration: String? = null,

    @ReportColumn(order = 11, columnName = "Panel IP")
    @PdfColumn(order = 11, columnName = "Panel IP")
    @Column(name = COL_PANEL_IP)
    @JsonProperty(value = "panelIp")
    var panelIp: String? = null,

    @Column(name = COL_PANEL_ID)
    var panelId: Long? = null,

    @JsonIgnore
    @ReportColumn(order = 12, columnName = "Reason for failure")
    @PdfColumn(order = 12, columnName = "Reason for failure")
    @Column(name = COL_REASON_FOR_FAILURE)
    @ConvertToLocalizedString
    var reasonForFailure: String? = null,

    @JsonIgnore
    @JsonIgnoreProperties("durationInSeconds")
    @Column(name = COL_DURATION_IN_SECONDS)
    var durationInSeconds: Long? = null,

    @JsonIgnore
    @JsonIgnoreProperties("scheduledTimeInSeconds")
    @Column(name = COL_SCHEDULED_TIME_IN_SECONDS)
    var scheduledTimeInSeconds: Long? = null,

    @JsonIgnore
    @JsonIgnoreProperties("reportToken")
    @Column(name = COL_REPORT_TOKEN)
    var reportToken: String? = null
) {

    @JsonProperty(value = "reasonForFailure")
    fun getReasonForFailureForJson(): List<String> {
        val x: String = if (reasonForFailure == null) {
            ""
        } else {
            reasonForFailure!!
        }
        return x.split(",")
            .filter { it.isNotEmpty() }
            .toList()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val that = other as PanelLogReportResponseNewDTO?
        return deviceId == that!!.deviceId &&
                deviceName == that.deviceName &&
                (locationId == null || locationId == that.locationId) &&
                (locationName == null || locationName == that.locationName) &&
                date == that.date &&
                scheduledPlayerUpTime == that.scheduledPlayerUpTime &&
                startTime == that.startTime &&
                endTime == that.endTime &&
                duration == that.duration &&
                panelId == that.panelId &&
                panelName == that.panelName &&
                (panelIp == null || panelIp == that.panelIp) &&
                panelStatus == that.panelStatus &&
                (reasonForFailure == null || reasonForFailure == that.reasonForFailure) &&
                durationInSeconds == that.durationInSeconds &&
                scheduledTimeInSeconds == that.scheduledTimeInSeconds &&
                reportToken == that.reportToken
    }

    fun createMyCopy(): PanelLogReportResponseNewDTO {
        val copy = PanelLogReportResponseNewDTO()
        try {
            BeanUtilsBean2.getInstance().copyProperties(copy, this)
        } catch (e: IllegalAccessException) {
            throw CopyingPropertiesException(e)
        } catch (e: InvocationTargetException) {
            throw CopyingPropertiesException(e)
        }
        return copy
    }

    override fun hashCode(): Int = Objects.hash(
        panelLogId,
        deviceId,
        deviceName,
        locationId,
        locationName,
        date,
        scheduledPlayerUpTime,
        startTime,
        endTime,
        duration,
        panelId,
        panelName,
        panelIp,
        panelStatus,
        reasonForFailure,
        durationInSeconds,
        scheduledTimeInSeconds,
        reportToken,
        totalSummary
    )

    companion object {
        const val COL_PANEL_LOG_ID = "panel_log_id"
        const val COL_DEVICE_ID = "device_id"
        const val COL_DEVICE_NAME = "device_name"
        const val COL_LOCATION_ID = "location_id"
        const val COL_LOCATION_NAME = "location_name"
        const val COL_DATE = "date"
        const val COL_SCHEDULED_PLAYER_UP_TIME = "scheduled_up_time"
        const val COL_START_TIME = "start_time"
        const val COL_END_TIME = "end_time"
        const val COL_DURATION = "duration"
        const val COL_PANEL_ID = "panel_id"
        const val COL_PANEL_NAME = "panel_name"
        const val COL_PANEL_IP = "panel_ip"
        const val COL_PANEL_STATUS = "panel_status"
        const val COL_REASON_FOR_FAILURE = "reason_for_failure"
        const val COL_DURATION_IN_SECONDS = "duration_in_seconds"
        const val COL_SCHEDULED_TIME_IN_SECONDS = "scheduled_time_in_seconds"
        const val COL_REPORT_TOKEN = "report_token"
        const val COL_TOTAL_SUMMARY = "total_summary"
    }
}