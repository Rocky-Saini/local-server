package com.digital.signage.repository

import com.digital.signage.models.DeviceAndPanelPendingType
import com.digital.signage.models.PendingDeviceAndPanelStatus
import com.digital.signage.models.PendingDeviceAndPanelStatus.Companion.COLUMN_DEVICE_ID
import com.digital.signage.models.PendingDeviceAndPanelStatus.Companion.COLUMN_PANEL_ID
import com.digital.signage.models.PendingDeviceAndPanelStatus.Companion.COLUMN_PENDING_DATA_TYPE
import com.digital.signage.models.PendingDeviceAndPanelStatus.Companion.COLUMN_TIME_OF_PENDING_STATUS
import com.digital.signage.models.PendingDeviceAndPanelStatus.Companion.TABLE_NAME_PENDING_DEVICE_AND_PANEL_STATUSES
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

/**
 * @author -Ravi Kumar created on 1/23/2023 3:46 PM
 * @project - Digital Sign-edge
 */
@Repository
interface PendingDeviceAndPanelStatusRepository :
    CrudRepository<PendingDeviceAndPanelStatus, Long> {

    @Query(
        value = """SELECT * FROM $TABLE_NAME_PENDING_DEVICE_AND_PANEL_STATUSES
                        WHERE
                            $COLUMN_PANEL_ID = :panelId 
                            AND $COLUMN_PENDING_DATA_TYPE = :pendingDataType
                        ORDER BY $COLUMN_TIME_OF_PENDING_STATUS DESC
                        LIMIT 1
                        """,
        nativeQuery = true
    )
    fun fetchLatestByPanelIdAndPendingDataType(
        @Param("panelId")
        panelId: Long,
        @Param("pendingDataType")
        pendingDataType: Int
    ): PendingDeviceAndPanelStatus?

    @Query(
        value = """SELECT * FROM $TABLE_NAME_PENDING_DEVICE_AND_PANEL_STATUSES
                        WHERE
                            $COLUMN_DEVICE_ID = :deviceId 
                            AND $COLUMN_PENDING_DATA_TYPE = :pendingDataType
                        ORDER BY $COLUMN_TIME_OF_PENDING_STATUS DESC
                        LIMIT 1
                        """,
        nativeQuery = true
    )
    fun fetchLatestByDeviceIdAndPendingDataType(
        @Param("deviceId")
        deviceId: Long,
        @Param("pendingDataType")
        pendingDataType: Int
    ): PendingDeviceAndPanelStatus?

    @Query(
        value = """SELECT * FROM $TABLE_NAME_PENDING_DEVICE_AND_PANEL_STATUSES
                        WHERE
                            $COLUMN_PANEL_ID = :panelId
                            AND $COLUMN_PENDING_DATA_TYPE = :pendingDataType
                        ORDER BY $COLUMN_TIME_OF_PENDING_STATUS DESC
                        LIMIT 100
                        """,
        nativeQuery = true
    )
    fun fetchLatest100ByPanelIdAndPendingDataType(
        @Param("panelId")
        panelId: Long,
        @Param("pendingDataType")
        pendingDataType: Int
    ): List<PendingDeviceAndPanelStatus>

    @Query(
        value = """SELECT * FROM $TABLE_NAME_PENDING_DEVICE_AND_PANEL_STATUSES
                        WHERE
                            $COLUMN_DEVICE_ID = :deviceId 
                            AND $COLUMN_PENDING_DATA_TYPE = :pendingDataType
                        ORDER BY $COLUMN_TIME_OF_PENDING_STATUS DESC
                        LIMIT 100
                        """,
        nativeQuery = true
    )
    fun fetchLatest100ByDeviceIdAndPendingDataType(
        @Param("deviceId")
        deviceId: Long,
        @Param("pendingDataType")
        pendingDataType: Int
    ): List<PendingDeviceAndPanelStatus>

    @Query(
        value = """SELECT * FROM $TABLE_NAME_PENDING_DEVICE_AND_PANEL_STATUSES
                        WHERE
                            $COLUMN_PENDING_DATA_TYPE IN (${DeviceAndPanelPendingType.DB_VALUE_DEVICE_AUDIO})
                            AND $COLUMN_TIME_OF_PENDING_STATUS < :olderThanDate
                            AND $COLUMN_DEVICE_ID IS NOT NULL
                        ORDER BY $COLUMN_TIME_OF_PENDING_STATUS DESC
                        """,
        nativeQuery = true
    )
    fun fetchAllStaleDevicePendingStatusesOlderThan(
        @Param("olderThanDate")
        olderThanDate: Date
    ): List<PendingDeviceAndPanelStatus>

    @Query(
        value = """SELECT * FROM $TABLE_NAME_PENDING_DEVICE_AND_PANEL_STATUSES
                        WHERE
                            $COLUMN_PANEL_ID IS NOT NULL
                            AND $COLUMN_PENDING_DATA_TYPE IN (${DeviceAndPanelPendingType.DB_VALUE_PANEL_AUDIO}, ${DeviceAndPanelPendingType.DB_VALUE_PANEL_ON_OFF})
                            AND $COLUMN_TIME_OF_PENDING_STATUS < :olderThanDate
                        ORDER BY $COLUMN_TIME_OF_PENDING_STATUS DESC
                        """,
        nativeQuery = true
    )
    fun fetchAllStalePanelPendingStatusesOlderThan(
        @Param("olderThanDate")
        olderThanDate: Date
    ): List<PendingDeviceAndPanelStatus>

    @Query(
        value = """
            SELECT * FROM pending_device_and_panel_statues AS pen1
            INNER JOIN (
                SELECT MAX(pen.time_of_pending_status) AS maxTimeOfStatus, pen.device_id
                FROM pending_device_and_panel_statues AS pen
                WHERE pen.device_id IS NOT NULL 
                AND pen.device_id IN (:deviceIds) 
                AND pen.pending_data_type = :pendingDataType 
                GROUP BY pen.device_id
            ) pen2
            ON pen1.device_id = pen2.device_id
            AND pen1.time_of_pending_status = pen2.maxTimeOfStatus
            WHERE pen1.device_id IS NOT NULL
            AND pen1.device_id IN (:deviceIds)
            AND pen1.pending_data_type = :pendingDataType
            """,
        nativeQuery = true
    )
    fun fetchLatestByDeviceIdsAndPendingDataType(
        @Param("deviceIds")
        deviceIds: Set<Long>,
        @Param("pendingDataType")
        pendingDataType: Int
    ): List<PendingDeviceAndPanelStatus>

    @Query(
        value = """
            SELECT * FROM pending_device_and_panel_statues AS pen1
            INNER JOIN (
                SELECT MAX(pen.time_of_pending_status) AS maxTimeOfStatus, pen.panel_id
                FROM pending_device_and_panel_statues AS pen
                WHERE pen.panel_id IS NOT NULL 
                AND pen.panel_id IN (:panelIds) 
                AND pen.pending_data_type = :pendingDataType 
                GROUP BY pen.panel_id
            ) pen2
            ON pen1.panel_id = pen2.panel_id
            AND pen1.time_of_pending_status = pen2.maxTimeOfStatus
            WHERE pen1.panel_id IS NOT NULL
            AND pen1.panel_id IN (:panelIds) 
            AND pen1.pending_data_type = :pendingDataType
            """,
        nativeQuery = true
    )
    fun fetchLatestByPanelIdsAndPendingDataType(
        @Param("panelIds")
        panelIds: Set<Long>,
        @Param("pendingDataType")
        pendingDataType: Int
    ): List<PendingDeviceAndPanelStatus>
}