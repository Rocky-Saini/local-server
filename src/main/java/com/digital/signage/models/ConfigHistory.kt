package com.digital.signage.models

import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

/**
 * @author -Ravi Kumar created on 1/19/2023 10:15 AM
 * @project - Digital Sign-edge
 */
@Entity(name = "config_history")
data class ConfigHistory(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "config_history_id")
    var configHistoryId: Long? = null,
    @Column(name = "device_id")
    var deviceId: Long? = null,
    @Column(name = "config_date")
    var configDate: Date? = null,
    @Column(name = "panel_on_time")
    var panelOnTime: String? = null,
    @Column(name = "panel_off_time")
    var panelOffTime: String? = null,
    @Column(name = "modified_on")
    var modifiedOn: Date? = null
) {
    constructor(
        deviceId: Long?,
        configDate: Date?,
        panelOnTime: String?,
        panelOffTime: String?,
        modifiedOn: Date?
    ) : this(
        null,
        deviceId,
        configDate,
        panelOnTime,
        panelOffTime,
        modifiedOn
    )
}