package com.digital.signage.service.impl

import com.digital.signage.context.TenantContext
import com.digital.signage.models.Camera
import com.digital.signage.models.ForbiddenResponse
import com.digital.signage.models.Response
import com.digital.signage.models.ResponseExt
import com.digital.signage.repository.CameraRepository
import com.digital.signage.repository.DeviceRepository
import com.digital.signage.service.CameraService
import com.digital.signage.service.Push
import com.digital.signage.util.CameraPurpose
import com.digital.signage.util.CameraType
import com.digital.signage.util.Message
import com.digital.signage.util.PushId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.Optional

/**
 * @author -Ravi Kumar created on 1/28/2023 4:43 PM
 * @project - Digital Sign-edge
 */
@Service
class CameraServiceImpl : BaseFaServiceImpl(), CameraService {

    @Autowired
    private lateinit var cameraRepository: CameraRepository

    @Autowired
    private lateinit var deviceRepository: DeviceRepository

    //@Autowired
    //private lateinit var customerService: CustomerService

    @Autowired
    private lateinit var push: Push

    override fun validateAddEditCameraRequest(cameraRequest: Camera?): Response<out Any?>? {
        val customerId = TenantContext.getCustomerId()

        // check if demography is enabled
        if (!isFaEnabled(customerId)) {
            return ResponseExt(
                result = null,
                httpStatusCode = 400,
                message = message.get(
                    Message.Demography.DEMOGRAPHY_NOT_ENABLED_CANNOT_PERFORM_OPERATION
                ),
                code = 7,
                name = "DemographyNotEnabled"
            )
        }

        if (cameraRequest == null) {
            return ResponseExt(
                result = null,
                httpStatusCode = 400,
                message = message.get(Message.Camera.CAMERA_IS_NULL),
                code = 1,
                name = "CameraNull"
            )
        }
        var cameraInDb: Camera? = null
        if (cameraRequest.cameraId != null) {
            // check camera in db
            var cam: Optional<Camera>? = null;
            cam = cameraRepository.findById(cameraRequest.cameraId)
            if (cam.isPresent) {
                cameraInDb = cam.get()
                    ?: return ResponseExt(
                        result = null,
                        httpStatusCode = 404,
                        message = message.get(Message.Camera.CAMERA_NOT_FOUND),
                        code = 1,
                        name = "CameraNotFound"
                    )
            }

        }
        /** Below code commented out because we don't want to validate this as we are
         * calling the APIs code internally from device API
        if (cameraRequest.deviceId != null) {
        deviceRepository.getByDeviceIdAndCustomerId(cameraRequest.deviceId, customerId)
        ?: return ResponseExt(
        result = null,
        httpStatusCode = 404,
        message = message.get(Message.Device.DEVICE_FETCH_NOT_FOUND),
        code = 2,
        name = "DeviceNotFound"
        )
        }
         */
        if (cameraRequest.deviceId != null && cameraRequest.cameraId != null) {
            // both not null then check if they match
            if (cameraInDb!!.deviceId != cameraRequest.deviceId) {
                return ResponseExt(
                    result = null,
                    httpStatusCode = 400,
                    message = message.get(Message.Camera.CAMERA_AND_DEVICE_MISMATCH),
                    code = 2,
                    name = "CameraAndDeviceMismatch"
                )
            }
        }
        if (cameraRequest.cameraPurpose == null) {
            return ResponseExt(
                result = null,
                httpStatusCode = 400,
                message = message.get(Message.Camera.CAMERA_PURPOSE_EMPTY),
                code = 7,
                name = "CameraTypeEmpty"
            )
        }
        if (cameraRequest.cameraType == null) {
            return ResponseExt(
                result = null,
                httpStatusCode = 400,
                message = message.get(Message.Camera.CAMERA_TYPE_EMPTY),
                code = 3,
                name = "CameraTypeEmpty"
            )
        } else {
            when (cameraRequest.cameraType) {
                CameraType.USB -> {
                    if (cameraRequest.cameraIp != null && cameraRequest.cameraIp!!.trim().isNotEmpty()) {
                        return ResponseExt(
                            result = null,
                            httpStatusCode = 400,
                            message = message.get(
                                Message.Camera.CAMERA_TYPE_USB_HAVING_IP_ERROR
                            ),
                            code = 4,
                            name = "CameraTypeUSBHavingIP"
                        )
                    }
                }

                CameraType.IP -> {
                    if (cameraRequest.cameraIp == null || cameraRequest.cameraIp!!.trim().isEmpty()) {
                        return ResponseExt(
                            result = null,
                            httpStatusCode = 400,
                            message = message.get(
                                Message.Camera.CAMERA_TYPE_IP_HAVING_NO_IP_ERROR
                            ),
                            code = 5,
                            name = "CameraTypeIPHavingNoIP"
                        )
                    }
                }

                CameraType.NO_CAM -> {
                    return ResponseExt(
                        result = null,
                        httpStatusCode = 400,
                        message = message.get(Message.Camera.CAMERA_TYPE_INVALID),
                        code = 6,
                        name = "CameraTypeInvalid"
                    )
                }
                else -> {
                    return null
                }
            }
        }
        return null
    }

    override fun addOrUpdateCamera(
        cameraRequest: Camera?,
        isValidationDone: Boolean
    ): Response<out Any?> {
        if (!isFaEnabled(TenantContext.getCustomerId())) {
            return ForbiddenResponse("Demography not enabled")
        }
        if (!isValidationDone) {
            // perform validation
            val validationResponse = validateAddEditCameraRequest(cameraRequest)
            if (validationResponse != null) {
                return validationResponse
            }
        }

        if (cameraRequest!!.customerId == null) {
            cameraRequest.customerId = TenantContext.getCustomerId()
        }

        // validation is success so proceed to saving
        if (cameraRequest.cameraId != null) {
            var cam: Optional<Camera>? = null;
            cam = cameraRepository.findById(cameraRequest.cameraId)
            if (cam.isPresent) {
                cameraRequest.deviceId = cam.get().deviceId
            }

        }

        // check if camera exists by deviceId
        if (cameraRequest.deviceId != null) {
            val cameraInDb = cameraRepository.getCameraByDeviceId(cameraRequest.deviceId!!)
            if (cameraInDb != null) {
                sendPushOnCameraStateChanged(cameraInDb, cameraRequest)
                cameraRequest.cameraId = cameraInDb.cameraId
            }
        }

        return ResponseExt(
            result = cameraRepository.save(cameraRequest),
            code = 1,
            message = message.get(Message.Camera.CAMERA_ADDED),
            name = "SuccessfullyAdded"
        )
    }

    override fun fetchCameraInternal(cameraId: Long?, deviceId: Long?): Camera? {
        var camera: Camera? = null
        if (cameraId != null) {
            var cam: Optional<Camera>? = null;
            cam = cameraRepository.findById(cameraId)
            if (cam.isPresent) {
                camera = cam.get()
            }
        }
        if (camera != null && deviceId != null) {
            if (camera.deviceId != deviceId) {
                return null
            }
        }
        if (camera == null && deviceId != null) {
            camera = cameraRepository.getCameraByDeviceId(deviceId)
        }
        return camera
    }

    override fun fetchCamera(cameraId: Long?, deviceId: Long?): Response<out Any?> {
        if (!isFaEnabled(TenantContext.getCustomerId())) {
            return ForbiddenResponse("Demography not enabled")
        }
        if (cameraId == null && deviceId == null) {
            return ResponseExt(
                result = null,
                code = 1,
                message = message.get(
                    Message.Camera.CAMERA_BOTH_CAMERA_ID_AND_DEVICE_ID_MISSING
                ),
                name = "BothCameraIdAndDeviceIdMissing",
                httpStatusCode = 400
            )
        }
        var camera: Camera? = null
        if (cameraId != null) {
            var cam: Optional<Camera>? = null;
            cam = cameraRepository.findById(cameraId)
            if (cam.isPresent) {
                camera = cam.get()
            }
        }
        if (camera != null && deviceId != null) {
            if (camera.deviceId != deviceId) {
                return ResponseExt(
                    result = null,
                    httpStatusCode = 400,
                    message = message.get(Message.Camera.CAMERA_AND_DEVICE_MISMATCH),
                    code = 1,
                    name = "CameraAndDeviceMismatch"
                )
            }
        }
        if (camera == null && deviceId != null) {
            camera = cameraRepository.getCameraByDeviceId(deviceId)
        }
        return if (camera == null) {
            ResponseExt(
                result = null,
                httpStatusCode = 404,
                message = message.get(Message.Camera.CAMERA_NOT_FOUND),
                code = 1,
                name = "CameraNotFound"
            )
        } else {
            return ResponseExt(
                result = camera,
                httpStatusCode = 200
            )
        }
    }

    override fun validateDeleteCameraRequest(deleteRequest: Camera?): Response<out Any?>? {
        if (deleteRequest == null
            || (deleteRequest.deviceId == null && deleteRequest.cameraId == null)
        ) {
            return ResponseExt(
                result = null,
                httpStatusCode = 400,
                message = message.get(Message.Camera.CAMERA_REQUEST_IS_NULL),
                code = 1,
                name = "CameraRequestNull"
            )
        }
        var camera: Camera? = null
        if (deleteRequest.cameraId != null) {
            var cam: Optional<Camera>? = null;
            cam = cameraRepository.findById(deleteRequest.cameraId)
            if (cam.isPresent) {
                camera = cam.get()
            }
        }
        if (camera != null && deleteRequest.deviceId != null) {
            if (camera.deviceId != deleteRequest.deviceId) {
                return ResponseExt(
                    result = null,
                    httpStatusCode = 400,
                    message = message.get(Message.Camera.CAMERA_AND_DEVICE_MISMATCH),
                    code = 1,
                    name = "CameraAndDeviceMismatch"
                )
            }
        }
        /** Below code commented out because we don't want to validate this as we are
         * calling the APIs code internally from device API

        if (camera == null && deleteRequest.deviceId != null) {
        camera = cameraRepository.getCameraByDeviceId(deleteRequest.deviceId!!)
        }
        return if (camera == null) {
        ResponseExt(
        result = null,
        httpStatusCode = 404,
        message = message.get(Message.Camera.CAMERA_NOT_FOUND),
        code = 1,
        name = "CameraNotFound"
        )
        } else {
        null
        }
         */
        return null
    }

    override fun deleteCamera(
        deleteRequest: Camera?,
        isValidationDone: Boolean
    ): Response<out Any?> {
        if (!isFaEnabled(TenantContext.getCustomerId())) {
            return ForbiddenResponse("Demography not enabled")
        }
        if (!isValidationDone) {
            val deleteCameraValidationResponse = validateDeleteCameraRequest(deleteRequest)
            if (deleteCameraValidationResponse != null) {
                return deleteCameraValidationResponse
            }
        }
        if (deleteRequest!!.cameraId != null) {
            cameraRepository.delete(deleteRequest)
        } else {
            val camera: Camera? = cameraRepository.getCameraByDeviceId(deleteRequest.deviceId!!)
            if (camera != null) {
                sendPushOnCameraStateChanged(camera, deleteRequest)
                cameraRepository.delete(camera)
            }
        }
        return ResponseExt(
            result = message.get(Message.Camera.CAMERA_DELETED),
            message = message.get(Message.Camera.CAMERA_DELETED),
            httpStatusCode = 200,
            code = 1,
            name = "SuccessfullyDeleted"
        )
    }

    fun isCameraPushRequired(
        cameraTypeInDb: CameraType,
        cameraTypeInRequest: CameraType,
        cameraPurposeInDb: CameraPurpose?,
        cameraPurposeInRequest: CameraPurpose?
    ): Boolean {
        var sendPush = false
        when (cameraTypeInDb) {
            CameraType.NO_CAM -> {
                if (cameraTypeInRequest != CameraType.NO_CAM && cameraPurposeInRequest == CameraPurpose.COLLECT_DATA_AND_RUN_DEMOGRAPHIC_CAMPAIGNS) {
                    sendPush = true
                }
            }

            CameraType.USB -> {
                if (cameraTypeInRequest != CameraType.NO_CAM && cameraPurposeInDb != cameraPurposeInRequest
                    || cameraTypeInRequest == CameraType.NO_CAM && cameraPurposeInDb != CameraPurpose.ONLY_COLLECT_DATA
                ) {
                    sendPush = true
                }
            }

            CameraType.IP -> {
                if (cameraTypeInRequest != CameraType.NO_CAM && cameraPurposeInDb != cameraPurposeInRequest
                    || cameraTypeInRequest == CameraType.NO_CAM && cameraPurposeInDb != CameraPurpose.ONLY_COLLECT_DATA
                ) {
                    sendPush = true
                }
            }
        }
        return sendPush
    }

    private fun sendPushOnCameraStateChanged(cameraInDB: Camera, cameraRequest: Camera) {
        if (isCameraPushRequired(
                cameraInDB.cameraType!!,
                cameraRequest.cameraType!!,
                cameraInDB.cameraPurpose,
                cameraRequest.cameraPurpose
            )
        ) {
            //todo
            push.sendPush(PushId.ID_CLIENT_DEMOGRAPHY_RULE_CHANGED, mutableListOf(cameraRequest.deviceId), TenantContext.getCurrentUserId())
        }
    }
}