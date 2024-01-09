package com.digital.signage.models

import com.digital.signage.util.ReportCacheProcessing
import java.util.Date
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Table(name = PanelReportProcessor.TABLE_NAME)
@Entity
data class PanelReportProcessor(
    @Id
    @GeneratedValue
    @Column(name = "panel_processor_id")
    var panelProcessorId: Long? = null,

    @Column(name = "device_id")
    var deviceId: Long? = null,

    @Column(name = "customer_id")
    var customerId: Long? = null,

    @Column(name = "panel_id")
    var panelId: Long? = null,

    @Column(name = "date_to_be_processed")
    var dateToBeProcessed: Date? = null,

    @Column(name = "process_status")
    var processStatus: ReportCacheProcessing? = null,

    @Column(name = "created_date")
    var createdDate: Date = Date(),

    @Column(name = "updated_date")
    var updatedDate: Date? = null
) : EntityModel {
    constructor(
        customerId: Long?,
        deviceId: Long?,
        panelId: Long?,
        timeOfStatus: Date?
    ) : this(
        null,
        deviceId,
        customerId,
        panelId,
        timeOfStatus,
        ReportCacheProcessing.UNPROCESSED
    )

    companion object {
        const val TABLE_NAME = "panel_report_processor"
    }
}
