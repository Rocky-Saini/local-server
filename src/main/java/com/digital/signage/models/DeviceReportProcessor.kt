package com.digital.signage.models

import com.digital.signage.util.ReportCacheProcessing
import java.util.*
import javax.persistence.*

@Table(name = DeviceReportProcessor.TABLE_NAME)
@Entity
data class DeviceReportProcessor(
    @Id
    @GeneratedValue
    @Column(name = "device_processor_id")
    var deviceProcessorId: Long? = null,

    @Column(name = "device_id")
    var deviceId: Long? = null,

    @Column(name = "customer_id")
    var customerId: Long? = null,

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
        timeOfStatus: Date?
    ) : this(
        null,
        deviceId,
        customerId,
        timeOfStatus,
        ReportCacheProcessing.UNPROCESSED
    )

    companion object{
        const val TABLE_NAME = "device_report_processor"
    }
}
