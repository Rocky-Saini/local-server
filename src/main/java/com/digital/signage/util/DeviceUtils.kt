package com.digital.signage.util

/**
 * @author -Ravi Kumar created on 1/25/2023 12:18 AM
 * @project - Digital Sign-edge
 */
fun isAndroidOs(deviceOs: DeviceOs): Boolean =
    DeviceOs.ANDROID == deviceOs || DeviceOs.ANDROID_TV == deviceOs