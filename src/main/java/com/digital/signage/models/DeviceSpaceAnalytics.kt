package com.digital.signage.models

import com.fasterxml.jackson.annotation.JsonIgnore
import java.util.*
import javax.persistence.*

/**
 * @author -Ravi Kumar created on 1/23/2023 5:15 PM
 * @project - Digital Sign-edge
 */
@Entity
@Table(name = DeviceSpaceAnalytics.DEVICE_SPACE_ANALYTICS_TABLE_NAME)
data class DeviceSpaceAnalytics(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = COL_PRIMARY_KEY)
    var id: Long? = null,
    @Column(name = COL_DEVICE_ID)
    var deviceId: Long? = null,
    @get:JsonIgnore
    @Column(name = COL_CUSTOMER_ID)
    var customerId: Long? = null,
    @Column(name = COL_AVAILABLE_SPACE_ON_DEVICE_IN_BYTES)
    var availableSpaceOnDeviceInBytes: Long? = null,
    @Column(name = COL_TOTAL_SPACE_ON_DEVICE_IN_BYTES)
    var totalSpaceOnDeviceInBytes: Long? = null,
    @Column(name = COL_THRESHOLD_REACHED)
    var thresholdReached: Boolean? = null,
    @get:JsonIgnore
    @Column(name = COL_CREATED_ON)
    var createdOn: Date = Date(),
    @Column(name = COL_FREE_SPACE_PERCENTAGE)
    var availableSpacePercentage: Double? = null
) : EntityModel {
    companion object {
        const val THRESHOLD_PERCENTAGE = 10
        const val COL_PRIMARY_KEY = "id"
        const val COL_DEVICE_ID = "device_id"
        const val COL_CUSTOMER_ID = "customer_id"
        const val COL_CREATED_ON = "created_on"
        const val COL_FREE_SPACE_PERCENTAGE = "available_space_percentage"
        const val COL_THRESHOLD_REACHED = "is_threshold_reached"
        const val DEVICE_SPACE_ANALYTICS_TABLE_NAME = "device_space_analytics"
        const val COL_TOTAL_SPACE_ON_DEVICE_IN_BYTES = "total_space_on_device_in_bytes"
        const val COL_AVAILABLE_SPACE_ON_DEVICE_IN_BYTES = "available_space_on_device_in_bytes"
    }
}

