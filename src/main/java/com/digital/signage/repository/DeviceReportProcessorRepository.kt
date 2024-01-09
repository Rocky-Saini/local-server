package com.digital.signage.repository

import com.digital.signage.models.DeviceReportProcessor
import com.digital.signage.util.ReportCacheProcessing
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import javax.transaction.Transactional

@Repository
interface DeviceReportProcessorRepository : CrudRepository<DeviceReportProcessor, Long> {

    fun findAllByDeviceIdInAndProcessStatus(
        deviceIds: List<Long>,
        processStatus: ReportCacheProcessing
    ): Set<DeviceReportProcessor>

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM ${DeviceReportProcessor.TABLE_NAME} WHERE process_status = 1",
        nativeQuery = true)
    fun deleteProcessedData()

}