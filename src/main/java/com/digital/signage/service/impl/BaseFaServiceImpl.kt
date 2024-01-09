package com.digital.signage.service.impl

import com.digital.signage.dto.FaEnabledAndCustomerContainer
import org.springframework.beans.factory.annotation.Autowired

/**
 * @author -Ravi Kumar created on 1/25/2023 1:58 AM
 * @project - Digital Sign-edge
 */
open class BaseFaServiceImpl : BaseServiceWithServerLaunchConfigImpl() {

    //@Autowired
    //private lateinit var customerService: CustomerService

    protected fun isFaEnabled(customerId: Long): Boolean = serverLaunchConfig.enableDemographic

    //todo get data from customer-service
    //&& customerService.isDemographyEnabled(customerId)

    protected fun isFaEnabledWithCustomerObject(
        customerId: Long
    ): FaEnabledAndCustomerContainer =
        if (!serverLaunchConfig.enableDemographic) {
            FaEnabledAndCustomerContainer(false, null)
        } else {
            //todo get data from customer-service
            FaEnabledAndCustomerContainer(false, null)
            //customerService.isDemographyEnabledAndGetCustomerObject(customerId)
        }
}
