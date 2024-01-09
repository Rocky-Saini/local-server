package com.digital.signage.service

import com.digital.signage.models.Camera
import com.digital.signage.models.Response
import org.springframework.stereotype.Service

/**
 * @author -Ravi Kumar created on 1/28/2023 4:43 PM
 * @project - Digital Sign-edge
 */
@Service
interface CameraService : BaseService {

    fun addOrUpdateCamera(cameraRequest: Camera?, isValidationDone: Boolean): Response<out Any?>

    fun fetchCamera(cameraId: Long?, deviceId: Long?): Response<out Any?>

    /**
     * fetches camera by either cameraId or by deviceId.
     * For inter-API use only
     */
    fun fetchCameraInternal(cameraId: Long?, deviceId: Long?): Camera?

    fun deleteCamera(deleteRequest: Camera?, isValidationDone: Boolean): Response<out Any?>

    fun validateAddEditCameraRequest(cameraRequest: Camera?): Response<out Any?>?

    fun validateDeleteCameraRequest(deleteRequest: Camera?): Response<out Any?>?
}