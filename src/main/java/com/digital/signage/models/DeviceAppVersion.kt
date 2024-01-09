package com.digital.signage.models

import com.digital.signage.util.BuildOs
import com.digital.signage.util.DeviceOs
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import com.digital.signage.models.DeviceAppVersion.Companion.TABLE_DEVICE_APP_VERSION
import com.fasterxml.jackson.annotation.JsonCreator
import lombok.Data
import java.util.*
import javax.persistence.*

/**
 * @author -Ravi Kumar created on 1/25/2023 1:28 AM
 * @project - Digital Sign-edge
 */
@Entity
@Table(name = TABLE_DEVICE_APP_VERSION)
@Data
data class DeviceAppVersion(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    @Column(name = COL_ID)
    @JsonProperty(COL_ID)
    var id: Long? = null,

    @JsonIgnore
    @Column(name = COLUMN_DEVICE_ID)
    @JsonProperty("deviceId")
    var deviceId: Long? = null,

    @Column(name = COLUMN_BUILD_OS)
    @JsonProperty("buildOs")
    var buildOs: BuildOs? = null,

    @JsonIgnore
    @JsonProperty("deviceOs")
    @Column(name = COLUMN_DEVICE_OS)
    var deviceOs: DeviceOs? = null,

    @JsonProperty("version")
    @Column(name = COLUMN_APP_VERSION)
    var appVersion: String? = null,

    @JsonIgnore
    @JsonProperty("timeOfStatus")
    @Column(name = COLUMN_TIME_OF_STATUS)
    var timeOfStatus: Date? = null
) {
    companion object {
        const val TABLE_DEVICE_APP_VERSION = "device_app_version"
        const val COLUMN_DEVICE_ID = "device_id"
        const val COLUMN_BUILD_OS = "build_os"
        const val COLUMN_DEVICE_OS = "device_os"
        const val COLUMN_APP_VERSION = "app_version"
        const val COLUMN_TIME_OF_STATUS = "time_of_status"
        const val COL_ID = "id"
    }
}

