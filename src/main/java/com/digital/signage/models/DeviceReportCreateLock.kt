package com.digital.signage.models

import com.digital.signage.util.ReportCreationStatus
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "device_report_create_lock")
data class DeviceReportCreateLock(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "lock_id")
    var lockId: Long? = null,
    @Column(name = "customer_id")
    var customerId: Long? = null,
    @Column(name = "report_creation_status")
    var reportCreationStatus: ReportCreationStatus? = ReportCreationStatus.UNDER_PROCESS,
    @Column(name = "lock_acquired_time")
    var lockAcquiredTime: Date? = Date()
) : EntityModel