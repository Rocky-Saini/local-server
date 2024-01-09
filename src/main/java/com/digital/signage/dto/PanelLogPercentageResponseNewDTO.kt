package com.digital.signage.dto

import com.digital.signage.annotations.*
import com.digital.signage.models.EntityModel
import com.digital.signage.util.ReportConstants.PANEL_ON_OFF_PERCENTAGE_REPORT_TITLE
import com.fasterxml.jackson.annotation.*
import java.util.*
import javax.persistence.*

@JsonPropertyOrder(
    "deviceId",
    "deviceName",
    "locationId",
    "locationName",
    "date",
    "scheduledPanelUpTime",
    "panelId",
    "panelName",
    "panelIp",
    "onHrs",
    "offHrs",
    "dataDeletedHrs",
    "notApplicableHrs",
    "dataNotAvailableHrs",
    "weekOffHrs",
    "inActiveHrs",
    "disconnectedHrs",
    "onPercentage",
    "offPercentage",
    "dataDeletedPercentage",
    "notApplicablePercentage",
    "dataNotAvailablePercentage",
    "weekOffPercentage",
    "inActivePercentage",
    "disconnectedPercentage"
)
@Entity
@PdfTitle(title = PANEL_ON_OFF_PERCENTAGE_REPORT_TITLE)
data class PanelLogPercentageResponseNewDTO(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = ID_KEY)
    @JsonIgnore
    @JsonIgnoreProperties(ID_KEY)
    var panelLogId: Long? = null,

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

    @ReportColumn(order = 3, columnName = "Date")
    @PdfColumn(order = 3, columnName = "Date")
    @Column(name = COL_DATE)
    @ReportSimpleDateFormat
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "IST")
    var date: Date? = null,

    @Column(name = COL_SCHEDULE_UP_TIME)
    @JsonProperty(value = "scheduledPanelUpTime")
    var scheduledPlayerUpTime: String? = null,

    @Column(name = COL_PANEL_ID)
    var panelId: Long? = null,

    @ReportColumn(order = 4, columnName = "Panel name")
    @PdfColumn(order = 4, columnName = "Panel name")
    @Column(name = COL_PANEL_NAME)
    var panelName: String? = null,

    @ReportColumn(order = 5, columnName = "Panel IP")
    @PdfColumn(order = 5, columnName = "Panel IP")
    @Column(name = COL_PANEL_IP)
    @JsonProperty(value = "panelIp")
    var panelIp: String? = null,

    @ReportColumn(order = 6, columnName = "On Hrs")
    @PdfColumn(order = 6, columnName = "On Hrs")
    @Column(name = COL_ON_HRS)
    @JsonProperty(value = "onHrs")
    var onHours: String? = null,

    @ReportColumn(order = 7, columnName = "On %")
    @PdfColumn(order = 7, columnName = "On %")
    @JsonProperty(value = "onPercentage")
    @Column(name = COL_ON_PER)
    var onPercentage: String? = null,

    @ReportColumn(order = 8, columnName = "Off Hrs")
    @PdfColumn(order = 8, columnName = "Off Hrs")
    @Column(name = COL_OFF_HRS)
    @JsonProperty(value = "offHrs")
    var offHours: String? = null,

    @Column(name = COL_OFF_PER)
    @JsonProperty(value = "offPercentage")
    @ReportColumn(order = 9, columnName = "Off %")
    @PdfColumn(order = 9, columnName = "Off %")
    var offPercentage: String? = null,

    @ReportColumn(order = 10, columnName = "InActive Hrs")
    @PdfColumn(order = 10, columnName = "InActive Hrs")
    @Column(name = COL_INACTIVE_HRS)
    @JsonProperty(value = "inActiveHrs")
    var inActiveHours: String? = null,

    @Column(name = COL_INACTIVE_PER)
    @JsonProperty(value = "inActivePercentage")
    @ReportColumn(order = 11, columnName = "InActive  %")
    @PdfColumn(order = 11, columnName = "InActive %")
    var inActivePercentage: String? = null,

    @ReportColumn(order = 12, columnName = "Data Deleted Hrs")
    @PdfColumn(order = 12, columnName = "Data Deleted Hrs")
    @Column(name = COL_DATA_DELETED_HRS)
    @JsonProperty(value = "dataDeletedHrs")
    @ReportTimeFormat(beginIndex = 0, lastIndex = 8)
    var dataDeletedHours: String? = null,

    @Column(name = COL_DATA_DELETED_PER)
    @JsonProperty(value = "dataDeletedPercentage")
    @ReportColumn(order = 13, columnName = "Data Deleted  %")
    @PdfColumn(order = 13, columnName = "Data Deleted  %")
    var dataDeletedPercentage: String? = null,

    @ReportColumn(order = 14, columnName = "Not Applicable Hrs")
    @PdfColumn(order = 14, columnName = "Not Applicable Hrs")
    @Column(name = COL_NOT_APPLICABLE_HRS)
    @JsonProperty(value = "notApplicableHrs")
    var notApplicableHours: String? = null,


    @Column(name = COL_NOT_APPLICABLE_PER)
    @JsonProperty(value = "notApplicablePercentage")
    @ReportColumn(order = 15, columnName = "Not Applicable  %")
    @PdfColumn(order = 15, columnName = "Not Applicable %")
    var notApplicablePercentage: String? = null,

    @ReportColumn(order = 16, columnName = "Data Not Available Hrs")
    @PdfColumn(order = 16, columnName = "Data Not Available Hrs")
    @Column(name = COL_DATA_NOT_AVAILABLE_HRS)
    @JsonProperty(value = "dataNotAvailableHrs")
    var dataNotAvailableHours: String? = null,

    @Column(name = COL_DATA_NOT_AVAILABLE_PER)
    @JsonProperty(value = "dataNotAvailablePercentage")
    @ReportColumn(order = 17, columnName = "Data Not Available  %")
    @PdfColumn(order = 17, columnName = "Data Not Available %")
    var dataNotAvailablePercentage: String? = null,

    @ReportColumn(order = 18, columnName = "Week Off Hrs")
    @PdfColumn(order = 18, columnName = "Week Off Hrs")
    @Column(name = COL_WEEK_OFF_HRS)
    @JsonProperty(value = "weekOffHrs")
    var weekOffHours: String? = null,

    @Column(name = COL_WEEK_OFF_PER)
    @JsonProperty(value = "weekOffPercentage")
    @ReportColumn(order = 19, columnName = "Week Off %")
    @PdfColumn(order = 19, columnName = "Week Off %")
    var weekOffPercentage: String? = null,

    @ReportColumn(order = 20, columnName = "Disconnected Hrs")
    @PdfColumn(order = 20, columnName = "Disconnected Hrs")
    @Column(name = COL_DISCONN_HRS)
    @JsonProperty(value = "disconnectedHrs")
    var disconnectedHours: String? = null,

    @ReportColumn(order = 21, columnName = "Disconnected %")
    @PdfColumn(order = 21, columnName = "Disconnected %")
    @Column(name = COL_DISCONN_PER)
    var disconnectedPercentage: String? = null,

    @JsonIgnore
    @JsonIgnoreProperties("reportToken")
    @Column(name = COL_REPORT_TOKEN)
    var reportToken: String? = null
) : EntityModel {

//    constructor(
//            deviceId: Long?,
//            deviceName: String,
//            locationId: Long?,
//            locationName: String,
//            date: Date,
//            scheduledPlayerUpTime: String,
//            panelId: Long?,
//            panelName: String,
//            panelIp: String,
//            onHours: String,
//            offHours: String,
//            weekOffHours: String,
//            notApplicableHours: String,
//            dataNotAvailableHours: String,
//            inActiveHours: String,
//            dataDeletedHours: String,
//            disconnectedHours: String,
//            onPercentage: String,
//            offPercentage: String,
//            weekOffPercentage: String,
//            notApplicablePercentage: String,
//            dataNotAvailablePercentage: String,
//            inActivePercentage: String,
//            dataDeletedPercentage: String,
//            disconnectedPercentage: String,
//            reportToken: String
//    ) {
//        this.deviceId = deviceId
//        this.deviceName = deviceName
//        this.locationId = locationId
//        this.locationName = locationName
//        this.date = date
//        this.scheduledPlayerUpTime = scheduledPlayerUpTime
//        this.panelId = panelId
//        this.panelName = panelName
//        this.panelIp = panelIp
//        this.onHours = onHours
//        this.offHours = offHours
//        this.weekOffHours = weekOffHours
//        this.notApplicableHours = notApplicableHours
//        this.dataNotAvailableHours = dataNotAvailableHours
//        this.dataDeletedHours = dataDeletedHours
//        this.inActiveHours = inActiveHours
//        this.disconnectedHours = disconnectedHours
//        this.onPercentage = onPercentage
//        this.offPercentage = offPercentage
//        this.weekOffPercentage = weekOffPercentage
//        this.notApplicablePercentage = notApplicablePercentage
//        this.dataNotAvailablePercentage = dataNotAvailablePercentage
//        this.inActivePercentage = inActivePercentage
//        this.dataDeletedPercentage = dataDeletedPercentage
//        this.disconnectedPercentage = disconnectedPercentage
//        this.reportToken = reportToken
//    }

    companion object {
        const val ID_KEY = "panelLogId"
        const val COL_SCHEDULE_UP_TIME = "scheduledPanelUpTime"
        const val COL_DEVICE_ID = "deviceId"
        const val COL_DEVICE_NAME = "deviceName"
        const val COL_LOCATION_ID = "locationId"
        const val COL_LOCATION_NAME = "locationName"
        const val COL_DATE = "date"
        const val COL_PANEL_ID = "panelId"
        const val COL_PANEL_NAME = "panelName"
        const val COL_PANEL_IP = "panelIp"
        const val COL_ON_HRS = "onHrs"
        const val COL_OFF_HRS = "offHrs"
        const val COL_WEEK_OFF_HRS = "weekOffHrs"
        const val COL_NOT_APPLICABLE_HRS = "notApplicableHrs"
        const val COL_DATA_NOT_AVAILABLE_HRS = "dataNotAvailableHrs"
        const val COL_DATA_DELETED_HRS = "dataDeletedHrs"
        const val COL_INACTIVE_HRS = "inActiveHrs"
        const val COL_DISCONN_HRS = "disconnectedHrs"
        const val COL_ON_PER = "onPercentage"
        const val COL_OFF_PER = "offPercentage"
        const val COL_WEEK_OFF_PER = "weekOffPercentage"
        const val COL_NOT_APPLICABLE_PER = "notApplicablePercentage"
        const val COL_DATA_NOT_AVAILABLE_PER = "dataNotAvailablePercentage"
        const val COL_INACTIVE_PER = "inActivePercentage"
        const val COL_DATA_DELETED_PER = "dataDeletedPercentage"
        const val COL_DISCONN_PER = "disconnectedPercentage"
        const val COL_REPORT_TOKEN = "reportToken"
    }
}
