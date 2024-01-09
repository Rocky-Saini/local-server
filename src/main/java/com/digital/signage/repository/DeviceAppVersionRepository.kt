package com.digital.signage.repository

import com.digital.signage.models.DeviceAppVersion
import com.digital.signage.dto.AppVersionUpgradeDeviceDto
import com.digital.signage.models.DeviceAppVersion.Companion.COLUMN_BUILD_OS
import com.digital.signage.models.DeviceAppVersion.Companion.COLUMN_DEVICE_ID
import com.digital.signage.models.DeviceAppVersion.Companion.COLUMN_TIME_OF_STATUS
import com.digital.signage.models.DeviceAppVersion.Companion.TABLE_DEVICE_APP_VERSION
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param

interface DeviceAppVersionRepository : CrudRepository<DeviceAppVersion, Long> {

    @Query(
        value = """SELECT * FROM $TABLE_DEVICE_APP_VERSION
                     WHERE
                       $COLUMN_BUILD_OS = :buildOsDbValue
                       AND $COLUMN_DEVICE_ID = :deviceId
                     ORDER BY $COLUMN_TIME_OF_STATUS DESC
                     LIMIT 1
                     """,
        nativeQuery = true
    )
    fun findLatestDeviceAppVersionByDeviceIdAndBuildOs(
        @Param("buildOsDbValue") buildOsDbValue: Int,
        @Param("deviceId") deviceId: Long
    ): DeviceAppVersion?

    @Query(
        value = "with cte as ( SELECT *,RANK () OVER ( " +
                "          PARTITION BY dav1.device_id " +
                "          ORDER BY id " +
                "          ) as d_rank FROM device_app_version as dav1 " +
                "        INNER JOIN ( " +
                "            SELECT MAX(dav.time_of_status) AS maxTimeOfStatus, dav.device_id, dav.build_os " +
                "            FROM device_app_version as dav " +
                "            WHERE dav.device_id IN (:deviceIds) " +
                "            GROUP BY dav.device_id, dav.build_os " +
                "        ) dav2 " +
                "        ON dav1.device_id = dav2.device_id " +
                "        AND dav1.time_of_status = dav2.maxTimeOfStatus " +
                "        AND dav1.build_os = dav2.build_os " +
                "        WHERE dav1.device_id IN (:deviceIds))"
                + "    select * from cte where d_rank = 1", nativeQuery = true
    )
    fun findLatestDeviceAppVersionByDeviceIds(
        @Param("deviceIds") deviceIds: Set<Long>
    ): List<DeviceAppVersion>

    @Query(
        nativeQuery = true,
        value = """SELECT
                            dev.deviceId,
                            dev.deviceName,
                            dev.deviceOs,
                            dev.customerId,
                            res1.appVersion
                        FROM
                            (
                                SELECT
                                    d.deviceId,
                                    d.deviceName,
                                    d.deviceOs,
                                    d.customerId,
                                    d1.appVersion
                                FROM
                                    device AS d
                                INNER JOIN `deviceAppVersion` AS d1 ON
                                    d.deviceId = d1.deviceId
                                INNER JOIN (
                                            SELECT
                                                MAX(dd.`timeOfStatus`) AS `maxTimeOfStatus`,
                                                dd.`deviceId`
                                            FROM
                                                `deviceAppVersion` AS dd
                                            WHERE
                                                dd.`deviceId` IN (:deviceIds)
                                                AND dd.`buildOs` = :buildOsInt
                                            GROUP BY dd.`deviceId`
                                    ) d2 ON
                                    d1.`deviceId` = d2.`deviceId`
                                    AND d1.`timeOfStatus` = d2.`maxTimeOfStatus`
                                WHERE
                                    d.deviceId IN (:deviceIds)
                                    AND d1.deviceOs = :deviceOsInt
                                    AND d.status=1
                                GROUP BY d1.`deviceId`
                            ) AS res1
                        RIGHT JOIN device AS dev ON
                            dev.deviceId = res1.deviceId
                             WHERE
                                dev.deviceOs = :deviceOsInt
                                AND dev.status =1
                                AND (res1.appVersion IS NULL OR res1.appVersion != :appVersion)
                            ORDER BY dev.deviceId"""
    )
    fun getAllOlderVersionDeviceDetailsByBuildOs(
        @Param("deviceIds") deviceIds: List<Long>,
        @Param("appVersion") appVersion: String,
        @Param("buildOsInt") buildOsInt: Int,
        @Param("deviceOsInt") deviceOsInt: Int
    ): MutableList<AppVersionUpgradeDeviceDto>
}
