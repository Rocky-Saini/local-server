package com.digital.signage.dto

import com.digital.signage.util.Week
import java.util.*

/**
 * @author -Ravi Kumar created on 1/22/2023 11:16 PM
 * @project - Digital Sign-edge
 */
data class DeletedDataDTO(
    val deletedDataStartDate: String? = null,
    val deletedDataEndDate: String? = null,
    var deletedDataStartTime: Date? = null,
    var deletedDataEndTime: Date? = null,
    val panelOnTime: String,
    val panelOffTime: String,
    var weekOffs: List<Week?>? = null
)
