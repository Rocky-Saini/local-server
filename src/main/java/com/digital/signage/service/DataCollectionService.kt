package com.digital.signage.service

import com.digital.signage.dto.DataCollectionRequestDTO
import com.digital.signage.dto.DeletedDataDTO
import com.digital.signage.dto.DeviceS3AnalyticsDTO
import com.digital.signage.dto.DeviceSpaceAnalyticsDTO
import com.digital.signage.models.Response
import org.springframework.stereotype.Service
import javax.servlet.http.HttpServletRequest

@Service
interface DataCollectionService : BaseService {
    fun sendData(
        dataCollectionList: MutableList<DataCollectionRequestDTO>?,
        httpServletRequest: HttpServletRequest
    ): Response<out Any>

    fun sendDataVersion2(
        dataCollectionList: MutableList<DataCollectionRequestDTO>?,
        httpServletRequest: HttpServletRequest
    ): Response<out Any>

    fun sendDataVersion3(
        dataCollectionDTOS: MutableList<DataCollectionRequestDTO>?,
        httpServletRequest: HttpServletRequest
    ): Response<out Any>

    fun sendDeletedData(
        dataCollectionDTOS: List<DeletedDataDTO>?,
        httpServletRequest: HttpServletRequest
    ): Response<out Any>

    fun sendDeletedData(
        dataCollectionDTO: DeletedDataDTO?,
        httpServletRequest: HttpServletRequest
    ): Response<out Any>

    fun saveDeviceSpaceAnalyticsData(
        deviceSpaceDto: DeviceSpaceAnalyticsDTO?,
        httpServletRequest: HttpServletRequest
    ): Response<out Any>

    fun saveDeviceS3AnalyticsData(
        deviceS3AnalyticsList: List<DeviceS3AnalyticsDTO>?,
        httpServletRequest: HttpServletRequest
    ): Response<out Any>
}
