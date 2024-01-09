package com.digital.signage.models

import com.digital.signage.util.Status
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import javax.persistence.*

/**
 * @author -Ravi Kumar created on 1/23/2023 4:46 PM
 * @project - Digital Sign-edge
 */
@Entity
@Table(name = "aspect_ratio")
data class AspectRatio(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = ASPECT_RATIO_ID)
    var aspectRatioId: Long? = null,

    @Column(name = COL_ASPECT_RATIO)
    var aspectRatio: String? = null,

    @JsonIgnore
    @Column(name = "customer_id")
    var customerId: Long? = null,

    @Column(name = COL_STATUS)
    var status: Status = Status.ACTIVE,

    @Column(name = COL_DEFAULT_WIDTH_IN_PIXEL)
    var defaultWidthInPixel: Long? = null,

    @Column(name = COL_DEFAULT_HEIGHT_IN_PIXEL)
    var defaultHeightInPixel: Long? = null,

    @Transient
    @get:JsonProperty("isEditable")
    var isEditable: Boolean = true,
    @Transient
    var isDefaultAspectRatio: Boolean = false,
    @Transient
    var actualWidthInPixel: Long? = null,
    @Transient
    var actualHeightInPixel: Long? = null
) : EntityModel {
    companion object {
        const val ASPECT_RATIO_ID = "aspect_ratio_id"
        const val COL_ASPECT_RATIO = "aspect_ratio"
        const val COL_STATUS = "status"
        const val COL_DEFAULT_WIDTH_IN_PIXEL = "default_width_in_pixel"
        const val COL_DEFAULT_HEIGHT_IN_PIXEL = "default_height_in_pixel"
    }
}
