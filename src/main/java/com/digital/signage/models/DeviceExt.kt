package com.digital.signage.models

import com.digital.signage.annotations.PdfColumn
import com.digital.signage.annotations.PdfTitle
import com.digital.signage.annotations.ReportColumn
import com.digital.signage.annotations.ReportSimpleDateFormat
import com.digital.signage.util.AudioStatusEnum
import com.digital.signage.util.DeviceStatus
import com.digital.signage.util.ReportConstants
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*
import javax.persistence.Transient

/**
 * @author -Ravi Kumar created on 1/25/2023 1:35 AM
 * @project - Digital Sign-edge
 */
@PdfTitle(title = ReportConstants.DEVICE_MODEL_TITLE)
data class DeviceExt(

    @ReportSimpleDateFormat(value = ReportConstants.VALID_DATE_TIME_PATTERN)
    @PdfColumn(order = 7, columnName = "Last Sync")
    @ReportColumn(order = 7, columnName = "Last Sync")
    var lastSyncTime: Date? = null,

    @ReportSimpleDateFormat(value = ReportConstants.VALID_DATE_TIME_PATTERN)
    @PdfColumn(order = 9, columnName = "Last Connectivity")
    @ReportColumn(order = 9, columnName = "Last Connectivity")
    var timeOfDeviceStatus: Date? = null,

    @PdfColumn(order = 8, columnName = "Media Player Connectivity")
    @ReportColumn(order = 8, columnName = "Media Player Connectivity")
    var deviceConnectivity: DeviceStatus? = null,

    @JsonProperty("isAudioEnabled")
    var audioStatusEnum: AudioStatusEnum? = null,

    @JsonProperty("appVersion")
    @PdfColumn(order = 5, columnName = "App Version")
    @ReportColumn(order = 5, columnName = "App Version")
    var appVersion: String? = null,

    @JsonProperty("appVersions")
    @Transient
    var deviceAppVersionList: List<DeviceAppVersion>? = null,

    @JsonProperty("ipAddress")
    var ipAddress: String? = null,

    @JsonProperty("latitude")
    var latitude: Double? = null,

    @JsonProperty("longitude")
    var longitude: Double? = null,

    @JsonProperty("latitudeDMS")
    var latitudeDMS: String? = null,

    @JsonProperty("longitudeDMS")
    var longitudeDMS: String? = null,

    @JsonProperty("locationFetchingErrors")
    var locationFetchingErrors: List<LocationFetchingErrors>? = null

//        @JsonProperty("lastAccess")
//        var lastAccess: Date? = null

) : Device()