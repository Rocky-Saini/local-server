package com.digital.signage.database.migration

import com.digital.signage.models.DeviceAppVersion
import com.digital.signage.repository.DeviceDataRepository
import com.digital.signage.util.BuildOs
import com.digital.signage.util.DeviceOs
import com.digital.signage.repository.DeviceAppVersionRepository
import java.util.*
import java.util.stream.Collectors

/**
 * @author -Ravi Kumar created on 1/25/2023 1:21 AM
 * @project - Digital Sign-edge
 */
class MigrateDeviceVersionFromDeviceDataToDAV constructor(
    private val deviceDataRepository: DeviceDataRepository,
    private val deviceAppVersionRepository: DeviceAppVersionRepository
) {
    companion object {
        const val MIGRATION_SQL = """SELECT
                                            *
                                        FROM
                                            (
                                            SELECT
                                                d.device_id,
                                                d1.app_version,
                                                d.device_os
                                            FROM
                                                device AS d
                                            INNER JOIN device_data AS d1 ON
                                                d.device_id = d1.device_id
                                            INNER JOIN (
                                                SELECT
                                                    MAX(dd.time_of_status) AS maxTimeOfStatus,
                                                    dd.device_id
                                                FROM
                                                    device_data AS dd
                                                GROUP BY
                                                    dd.device_id ) d2 ON
                                                d1.device_id = d2.device_id
                                                AND d1.time_of_status` = d2.maxTimeOfStatus
                                            GROUP BY
                                                d1.device_id) aa
                                        WHERE
                                            aa.app_version IS NOT NULL;"""
    }

    fun migrate() {
        val list = deviceDataRepository.allDeviceIdAndVersionForMigration
        val now = Date()
        val dav = list.stream()
            .map {
                DeviceAppVersion().apply {
                    deviceId = it.getDeviceId()
                    timeOfStatus = now
                    appVersion = it.getAppVersion()
                    deviceOs = DeviceOs.valueOf(it.getDeviceOs()!!)
                    buildOs = when (deviceOs) {
                        DeviceOs.ANDROID -> BuildOs.ANDROID
                        DeviceOs.ANDROID_TV -> BuildOs.ANDROID_TV
                        DeviceOs.WINDOWS -> BuildOs.WINDOWS
                        DeviceOs.DESKTOP -> BuildOs.DESKTOP_APP_NATIVE
                        null -> null
                    }
                }
            }.filter {
                it.appVersion != null
                        && it.buildOs != null
                        && it.deviceId != null
                        && it.deviceOs != null
                        && it.timeOfStatus != null
            }.collect(Collectors.toList())
        deviceAppVersionRepository.saveAll(dav)
    }
}