package com.digital.signage.models

import com.digital.signage.models.PendingDeviceAndPanelStatus.Companion.TABLE_NAME_PENDING_DEVICE_AND_PANEL_STATUSES
import java.util.*
import javax.persistence.*

/**
 * @author -Ravi Kumar created on 1/22/2023 11:37 PM
 * @project - Digital Sign-edge
 */
@Entity
@Table(name = TABLE_NAME_PENDING_DEVICE_AND_PANEL_STATUSES)
data class PendingDeviceAndPanelStatus(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,

    @Column(name = COLUMN_DEVICE_ID)
    var deviceId: Long? = null,

    @Column(name = COLUMN_PANEL_ID)
    var panelId: Long? = null,

    @Column(name = COLUMN_PENDING_DATA_TYPE)
    var pendingDataType: DeviceAndPanelPendingType? = null,

    @Column(name = COLUMN_TIME_OF_PENDING_STATUS)
    var timeOfPendingStatus: Date? = null
) {
    companion object {
        const val TABLE_NAME_PENDING_DEVICE_AND_PANEL_STATUSES = "pending_device_and_panel_statues"
        const val COLUMN_DEVICE_ID = "device_id"
        const val COLUMN_PANEL_ID = "panel_id"
        const val COLUMN_PENDING_DATA_TYPE = "pending_data_type"
        const val COLUMN_TIME_OF_PENDING_STATUS = "time_of_pending_status"
    }
}
