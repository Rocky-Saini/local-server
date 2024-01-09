package com.digital.signage.models

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "device_connected_status")
data class DeviceConnectedStatus(
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,

    @Column(name = "time_stamp")
    var timeStamp: Date? = null,

    @Column(name = "device_id")
    var deviceId: Long? = null,

    @Column(name = "customer_id")
    var customerId: Long? = null
)