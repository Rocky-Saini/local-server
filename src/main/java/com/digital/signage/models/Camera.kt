package com.digital.signage.models

import com.digital.signage.util.CameraPurpose
import com.digital.signage.util.CameraType
import com.fasterxml.jackson.annotation.JsonInclude
import javax.persistence.*

/**
 * @author -Ravi Kumar created on 1/17/2023 5:49 PM
 * @project - Digital Sign-edge
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity(name = "camera")
data class Camera(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var cameraId: Long? = null,
    var deviceId: Long? = null,
    var createdOn: Long? = null,
    var customerId: Long? = null,
    @Enumerated(EnumType.STRING)
    var cameraType: CameraType? = null,
    var cameraIp: String? = null,
    @Enumerated(EnumType.STRING)
    var cameraPurpose: CameraPurpose? = null
) {
    companion object {
        val NO_CAM: Camera = Camera(cameraType = CameraType.NO_CAM)
    }
}
