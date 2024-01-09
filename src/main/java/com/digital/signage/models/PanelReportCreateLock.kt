package com.digital.signage.models

import java.util.Date
import javax.persistence.*

@Entity
@Table(name = "panelReportCreateLock")
data class PanelReportCreateLock(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var lockId: Long? = null,
    var customerId: Long? = null,
    //var reportCreationStatus: ReportCreationStatus? = ReportCreationStatus.UNDER_PROCESS,
    var lockAcquiredTime: Date? = Date()
) : EntityModel