package com.digital.signage.repository

import com.digital.signage.models.DeviceReportCreateLock
import com.digital.signage.util.ReportCreationStatus
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface DeviceReportCreateLockRepository : CrudRepository<DeviceReportCreateLock, Long> {

//    fun findDeviceReportCreateLockByCustomerId(customerId: Long): Optional<DeviceReportCreateLock>

    @Query(value = "select * from device_report_create_lock where report_creation_status = 0 ", nativeQuery = true)
    fun findLockByReportCreationStatus(reportCreationStatus: ReportCreationStatus
    ): Optional<DeviceReportCreateLock>
}