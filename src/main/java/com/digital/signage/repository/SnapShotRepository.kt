package com.digital.signage.repository

import com.digital.signage.models.SnapShot
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import org.springframework.data.repository.query.Param
import org.springframework.data.jpa.repository.Query
import java.util.*

@Repository
interface SnapShotRepository : CrudRepository<SnapShot, Long> {

    @Query(value = """SELECT s.*, d.customer_id FROM snapshot AS s
                        INNER JOIN device AS d ON d.device_id = s.device_id
                        WHERE s.device_id = :deviceId
                        AND d.customer_id = :customerId
                        AND (
                            s.snapshot_time between :queryStartTime
                            AND :queryEndTime
                        )""",
            nativeQuery = true)
    fun getSnapShotsInRange(
            @Param("deviceId") deviceId: Long,
            @Param("queryStartTime") queryStartTime: Date,
            @Param("queryEndTime") queryEndTime: Date,
            @Param("customerId") customerId: Long
    ): List<SnapShot>

    @Query(
            nativeQuery = true,
            value = "SELECT * FROM snapshot AS s WHERE s.snapshot_time < :date LIMIT :limit OFFSET :offset"
    )
    fun findSnapshotsBySnapshotTimeLessThan(
            @Param("date") date: Date,
            @Param("limit") limit: Int,
            @Param("offset") offset: Int
    ): MutableList<SnapShot>




}