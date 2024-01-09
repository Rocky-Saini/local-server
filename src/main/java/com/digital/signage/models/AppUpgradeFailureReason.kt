package com.digital.signage.models

import com.fasterxml.jackson.annotation.JsonIgnore
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "appUpgradeFailureReason")
data class AppUpgradeFailureReason(
    @Id
    @Column(name = COL_REASON_ID)
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,

    @Column(name = COL_DEVICE_ID)
    var deviceId: Long? = null,

    @Column(name = COL_CUSTOMER_ID)
    var customerId: Long? = null,

    @Column(name = COL_REASON_FOR_FAILURE)
    var reasonForFailure: String? = null,

    @Column(name = COL_TIMESTAMP_OF_FAILURE_EVENT)
    var timestampOfFailureEvent: Date? = null,

    @get:JsonIgnore
    var createdOn: Date? = null
) : EntityModel {
    companion object {
        const val COL_REASON_ID = "id"
        const val COL_DEVICE_ID = "deviceId"
        const val COL_CREATED_ON = "createdOn"
        const val COL_REASON_MAX_LENGTH = 512
        const val COL_CUSTOMER_ID = "customerId"
        const val COL_REASON_FOR_FAILURE = "reasonForFailure"
        const val COL_TIMESTAMP_OF_FAILURE_EVENT = "timestampOfFailureEvent"
        const val APP_UPGRADE_FAILURE_REASON_TABLE_NAME = "appUpgradeFailureReason"
    }
}
