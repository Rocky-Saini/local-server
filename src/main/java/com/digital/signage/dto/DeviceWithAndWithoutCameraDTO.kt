package com.digital.signage.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigInteger

data class DeviceWithAndWithoutCameraDTO(
    var deviceWithCamera: BigInteger,
    var totalDevices: BigInteger
) {
    @JsonProperty("deviceWithoutCamera")
    fun getDeviceWithoutCamera(): BigInteger {
        return this.totalDevices.minus(this.deviceWithCamera)
    }

    @JsonProperty("isDataFullyEmpty")
    fun fullyEmpty(): Boolean = BigInteger.ZERO == totalDevices
}