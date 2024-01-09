package com.digital.signage.repository

import com.digital.signage.models.PanelReportProcessor
import com.digital.signage.models.PanelReportProcessor.Companion.TABLE_NAME
import com.digital.signage.util.ReportCacheProcessing
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import javax.transaction.Transactional

@Repository
interface PanelReportProcessorRepository : CrudRepository<PanelReportProcessor, Long> {

    fun findAllByPanelIdInAndProcessStatus(
        panelIds: List<Long>,
        processStatus: ReportCacheProcessing
    ): Set<PanelReportProcessor>

    fun findAllByProcessStatus(processStatus: ReportCacheProcessing
    ): Set<PanelReportProcessor>

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM $TABLE_NAME WHERE process_status = 1", nativeQuery = true)
    fun deleteProcessedData()
}