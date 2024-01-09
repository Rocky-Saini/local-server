package com.digital.signage.models

import com.digital.signage.models.LastApiHitTimeModel.Companion.TABLE_NAME_LAST_API_HIT_TIME
import java.util.*
import javax.persistence.*

/**
 * @author -Ravi Kumar created on 1/23/2023 12:14 AM
 * @project - Digital Sign-edge
 */
@Entity
@Table(name = TABLE_NAME_LAST_API_HIT_TIME)
data class LastApiHitTimeModel(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = COLUMN_ID)
    var id: Long? = null,

    @Column(name = COLUMN_DEVICE_ID)
    var deviceId: Long? = null,

    @Column(name = COLUMN_TIME_OF_LAST_API_HIT)
    var timeOfLastApiHit: Date? = null
) {
    companion object {
        const val TABLE_NAME_LAST_API_HIT_TIME = "last_api_hit_time"
        const val COLUMN_DEVICE_ID = "device_id"
        const val COLUMN_ID = "id"
        const val COLUMN_TIME_OF_LAST_API_HIT = "time_of_last_api_hit"
    }
}
