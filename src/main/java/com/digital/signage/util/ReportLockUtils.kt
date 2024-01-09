package com.digital.signage.util

import com.digital.signage.repository.DeviceReportCreateLockRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ReportLockUtils {

    @Autowired
    lateinit var deviceReportCreateLockRepository: DeviceReportCreateLockRepository

//    @Autowired
//    lateinit var panelReportCreateLockRepository: PanelReportCreateLockRepository

//    fun acquiredDeviceReportLock(customerId: Long?) {
//        if (customerId == null) {
//            throw IllegalArgumentException("customer should not be null.")
//        }
//        logger.debug("acquiring lock for customerId::{}", customerId)
//        val lockOptional =
//            deviceReportCreateLockRepository.findDeviceReportCreateLockByCustomerId(
//                customerId)
//        val deviceReportCreateLock: DeviceReportCreateLock = lockOptional.orElse(
//            DeviceReportCreateLock(customerId = customerId))
//        deviceReportCreateLock.reportCreationStatus = ReportCreationStatus.UNDER_PROCESS
//        deviceReportCreateLock.lockAcquiredTime = Date()
//        deviceReportCreateLockRepository.save(deviceReportCreateLock)
//        logger.debug("acquired lock for customerId::{}", customerId)
//    }
//
//    fun releaseDeviceReportLock(customerId: Long?) {
//        if (customerId == null) {
//            throw IllegalArgumentException("customer should not be null")
//        }
//        logger.debug("releasing lock for customerId::{}", customerId)
//        val lockOptional =
//            deviceReportCreateLockRepository.findLockByCustomerIdAndReportCreationStatus(
//                customerId, ReportCreationStatus.UNDER_PROCESS)
//        val acquiredLock =
//            lockOptional.orElseThrow {
//                IllegalStateException("This customer has not acquired any lock.")
//            }
//        acquiredLock.reportCreationStatus = ReportCreationStatus.COMPLETED
//        acquiredLock.lockAcquiredTime = Date()
//        deviceReportCreateLockRepository.save(acquiredLock)
//        logger.debug("released lock for customerId::{}", customerId)
//    }

    fun isDeviceReportGenerationUnderProcess(customerId: Long?): Boolean {
        if (customerId == null) {
            throw IllegalArgumentException("customer should not be null")
        }
        val lockOptional =
            deviceReportCreateLockRepository.findLockByReportCreationStatus(ReportCreationStatus.UNDER_PROCESS)
        return lockOptional.isPresent
    }

//    fun acquiredPanelReportLock(customerId: Long?) {
//        if (customerId == null) {
//            throw IllegalArgumentException("customer should not be null.")
//        }
//        logger.debug("acquiring lock for customerId::{}", customerId)
//        val lockOptional =
//            panelReportCreateLockRepository.findPanelReportCreateLockByCustomerId(
//                customerId)
//        val reportCreateLock: PanelReportCreateLock = lockOptional.orElse(
//            PanelReportCreateLock(customerId = customerId))
//        reportCreateLock.reportCreationStatus = ReportCreationStatus.UNDER_PROCESS
//        reportCreateLock.lockAcquiredTime = Date()
//        panelReportCreateLockRepository.save(reportCreateLock)
//        logger.debug("acquired lock for customerId::{}", customerId)
//    }
//
//    fun releasePanelReportLock(customerId: Long?) {
//        if (customerId == null) {
//            throw IllegalArgumentException("customer should not be null")
//        }
//        logger.debug("releasing lock for customerId::{}",customerId)
//        val lockOptional =
//            panelReportCreateLockRepository.findLockByCustomerIdAndReportCreationStatus(
//                customerId, ReportCreationStatus.UNDER_PROCESS)
//        val acquiredLock =
//            lockOptional.orElseThrow {
//                IllegalStateException("This customer has not acquired any lock.")
//            }
//        acquiredLock.lockAcquiredTime = Date()
//        acquiredLock.reportCreationStatus = ReportCreationStatus.COMPLETED
//        panelReportCreateLockRepository.save(acquiredLock)
//        logger.debug("released lock for customerId::{}", customerId)
//    }
//
//    fun isPanelReportGenerationUnderProcess(customerId: Long?): Boolean {
//        if (customerId == null) {
//            throw IllegalArgumentException("customer should not be null")
//        }
//        val lockOptional =
//            panelReportCreateLockRepository.findLockByCustomerIdAndReportCreationStatus(
//                customerId, ReportCreationStatus.UNDER_PROCESS)
//        return lockOptional.isPresent
//    }
//
//    companion object {
//        val logger = LoggerFactory.getLogger(ReportLockUtils::class.java)!!
//    }
}