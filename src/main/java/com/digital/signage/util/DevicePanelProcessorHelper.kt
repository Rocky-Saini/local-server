package com.digital.signage.util

import com.digital.signage.models.DeviceData
import com.digital.signage.models.DeviceReportProcessor
import com.digital.signage.models.PanelReportProcessor
import com.digital.signage.models.PanelStatus
import com.digital.signage.repository.DeviceReportProcessorRepository
import com.digital.signage.repository.PanelReportProcessorRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.*
import java.util.stream.Collectors

@Component
class DevicePanelProcessorHelper {

    @Autowired
    private lateinit var deviceReportProcessorRepository: DeviceReportProcessorRepository

    @Autowired
    private lateinit var panelReportProcessorRepository: PanelReportProcessorRepository

    fun makeEntryInDeviceProcessor(deviceDataList: List<DeviceData>) {
        if (deviceDataList.isNullOrEmpty()) return
        val deviceData = deviceDataList.stream().findFirst()
        val deviceReportProcessors = mutableListOf<DeviceReportProcessor>()
        deviceData.ifPresent {
            val dateList = deviceDataList.stream().map { t ->
                DateUtils.resetTime(t.timeOfStatus)
            }.distinct().collect(Collectors.toList())
            if (dateList.isNotEmpty()) {
                dateList.forEach { date: Date? ->
                    deviceReportProcessors.add(
                        DeviceReportProcessor(it.customerId, it.deviceId, date))
                }
            }
        }
        if (deviceReportProcessors.isNotEmpty()) {
            deviceReportProcessorRepository.saveAll(deviceReportProcessors)
        }
    }

    fun makeEntryInPanelProcessor(panelStatusList: List<PanelStatus>) {
        if (panelStatusList.isNullOrEmpty()) return
        val panelStatus = panelStatusList.stream().findFirst()
        val panelReportProcessors = mutableListOf<PanelReportProcessor>()
        panelStatus.ifPresent {
            val dateList = panelStatusList.stream().map { t ->
                DateUtils.resetTime(t.timeOfStatus)
            }.distinct().collect(Collectors.toList())
            if (dateList.isNotEmpty()) {
                dateList.forEach { date: Date? ->
                    panelReportProcessors.add(
                        PanelReportProcessor(it.customerId, it.device.deviceId, it.panelId,
                            date))
                }
            }
        }
        if (panelReportProcessors.isNotEmpty()) {
            panelReportProcessorRepository.saveAll(panelReportProcessors)
        }
    }

}