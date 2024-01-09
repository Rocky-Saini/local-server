package com.digital.signage.repository

import com.digital.signage.database.FETCH_THRESHOLD_DEVICE_SPACE_DATA_QUERY
import com.digital.signage.models.DeviceSpaceAnalytics
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface DeviceSpaceAnalyticsRepository : CrudRepository<DeviceSpaceAnalytics, Long> {
    @Query(value = FETCH_THRESHOLD_DEVICE_SPACE_DATA_QUERY, nativeQuery = true)
    fun findAllThresholdReachedAnalytics(
        @Param("customerId") customerId: Long
    ): List<DeviceSpaceAnalytics>

    @Query(
        value = "SELECT distinct (d.customerId) FROM deviceSpaceAnalytics d ",
        nativeQuery = true
    )
    fun findAllCustomerIdInTable(): List<Number>
}