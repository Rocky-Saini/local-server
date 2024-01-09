package com.digital.signage.repository

import com.digital.signage.models.CustomerLevelPushViaRabbitMq
import com.digital.signage.models.CustomerLevelPushViaRabbitMq.Companion.COL_CUSTOMER_ID
import com.digital.signage.models.CustomerLevelPushViaRabbitMq.Companion.TABLE_NAME
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface CustomerLevelPushViaRabbitMqRepository :
    CrudRepository<CustomerLevelPushViaRabbitMq, Long> {

    @Query(value = "SELECT * FROM $TABLE_NAME WHERE $COL_CUSTOMER_ID = :customerId",
        nativeQuery = true)
    fun getByCustomerId(
        @Param("customerId") customerId: Long
    ): List<CustomerLevelPushViaRabbitMq>

    @Query(value = "SELECT * FROM $TABLE_NAME WHERE $COL_CUSTOMER_ID IN (:customerIds)",
        nativeQuery = true)
    fun getByCustomerIds(
        @Param("customerIds") customerIds: Set<Long>
    ): List<CustomerLevelPushViaRabbitMq>
}