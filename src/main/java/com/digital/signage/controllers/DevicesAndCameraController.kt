package com.digital.signage.controllers

import com.digital.signage.constants.UserConstants
import com.digital.signage.models.Privilege.ALL_USERS
import com.digital.signage.models.Response
import com.digital.signage.service.BaseService
import com.digital.signage.service.DeviceService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Lazy
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletResponse

/**
 * @author -Ravi Kumar created on 1/25/2023 1:45 AM
 * @project - Digital Sign-edge
 */
@RestController
@RequestMapping(value = ["/api"])
open class DevicesAndCameraController : BaseController() {

    @Autowired
    @Lazy
    private lateinit var deviceService: DeviceService

    @PreAuthorize("hasAuthority('$ALL_USERS')")
    @GetMapping("/devices-with-camera")
    private fun devicesWithCamera(
        httpServletResponse: HttpServletResponse
    ): Response<out Any> = updateHttpStatusCode(
        deviceService.devicesWithCamera(),
        httpServletResponse
    )

    @PreAuthorize("hasAuthority('$ALL_USERS')")
    @GetMapping("/devices-with-camera-playback")
    fun devicesWithCameraPlayback(
        @RequestParam("locationIds") locationIds: List<Long>?,
        httpServletResponse: HttpServletResponse
    ): Response<out Any> = updateHttpStatusCode(
        deviceService.devicesWithCameraPlayback(locationIds),
        httpServletResponse
    )

    override fun getBaseService(): BaseService = deviceService
}