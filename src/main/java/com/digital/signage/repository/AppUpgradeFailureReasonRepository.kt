package com.digital.signage.repository;

import com.digital.signage.models.AppUpgradeFailureReason
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface AppUpgradeFailureReasonRepository : CrudRepository<AppUpgradeFailureReason, Long> {
    @Query(
        name = """SELECT
                    *
                    FROM
                        (
                        SELECT
                            t1.*
                        FROM
                            appUpgradeFailureReason t1
                        INNER JOIN (
                            SELECT
                                `deviceId`,
                                MAX(`createdOn`) AS max_time
                            FROM
                                `appUpgradeFailureReason` WHERE deviceId IN(:deviceIds)
                            GROUP BY `deviceId`) t2 ON 
                                t1.`deviceId` = t2.`deviceId`
                                AND t1.`createdOn` = t2.max_time 
                            WHERE t1.deviceId IN (:deviceIds)) AS temp""",
        nativeQuery = true
    )
    fun findAllByDeviceIdInOrderByCreatedOnDesc(@Param("deviceIds") deviceIds: List<Long>): List<AppUpgradeFailureReason>
}
