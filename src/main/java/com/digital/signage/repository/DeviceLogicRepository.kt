package com.digital.signage.repository

import com.digital.signage.dto.JustLogic
import com.digital.signage.models.Logic
import org.springframework.data.jpa.repository.Query
import org.springframework.data.jpa.repository.query.Procedure
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param

interface DeviceLogicRepository : CrudRepository<Logic, Long> {

//    @Query(value = "FROM deviceGroup WHERE customerId = :customerId AND planogramId= :planogramId AND Status != 3")
//    fun findAllLogicByPlanogramIdAndCustomerId(
//            @Param("planogramId") planogramId: Long,
//            @Param("customerId") customerId: Long
//    ): List<Logic>

    @Query(value = "FROM logic where planogramId = :planogramId")
    fun findAllLogicByPlanogramId(@Param("planogramId") planogramId: Long?): List<Logic?>?

    @Query(
        value = """SELECT
                        p.logicType,
                        p.planogramId,
                        p.customerId,
                        p.status,
                        p.id,
                        dlm.deviceId AS dlmDeviceId,
                        d.deviceName AS dlmDeviceName,
                        llm.locationId AS llmLocationId,
                        l1.locationName AS llmLocationName,
                        dglm.deviceGroupId AS dglmDeviceGroupId,
                        dg1.deviceGroupName AS dglmDeviceGroupName,
                        lwdg.locationId AS lwdgLocationId,
                        l2.locationName AS lwdgLocationName,
                        dgm.deviceGroupId AS lwdgDeviceGroupId,
                        dg2.deviceGroupName AS lwdgDeviceGroupName
                    FROM
                        planogramDeviceLogic p
                    LEFT JOIN deviceLogicMapping dlm ON
                        p.id = dlm.id
                        AND p.logicType = 0
                    LEFT JOIN locationLogicMapping llm ON
                        p.id = llm.id
                        AND p.logicType = 1
                    LEFT JOIN deviceGroupLogicMapping dglm ON
                        p.id = dglm.id
                        AND p.logicType = 2
                    LEFT JOIN locationWithDeviceGroup lwdg ON
                        p.id = lwdg.deviceGroupLocationLogicId
                        AND p.logicType = 3
                    LEFT JOIN deviceGroupMapping dgm ON
                        lwdg.id = dgm.id
                    LEFT JOIN device d ON
                        dlm.deviceId = d.deviceId
                    LEFT JOIN location l1 ON
                        llm.locationId = l1.locationId
                    LEFT JOIN deviceGroup dg1 ON
                        dglm.deviceGroupId = dg1.deviceGroupId
                    LEFT JOIN location l2 ON
                        lwdg.locationId = l2.locationId
                    LEFT JOIN deviceGroup dg2 ON
                        dgm.deviceGroupId = dg2.deviceGroupId
                    WHERE
                        p.planogramId = :planogramId""",
        nativeQuery = true
    )
    fun findAllJustLogicByPlanogramId(@Param("planogramId") planogramId: Long): List<JustLogic>

    @Query(
        nativeQuery = true,
        value = """SELECT a.planogramId FROM (SELECT
                        p.planogramId
                    FROM
                        planogramDeviceLogic p
                    LEFT JOIN deviceLogicMapping dlm ON
                        p.id = dlm.id
                        AND p.logicType = 0
                    WHERE
                        p.customerId = :customerId
                        AND p.status = 1
                        AND dlm.deviceId = :deviceId) AS a
                    
                    UNION
                    
                    SELECT b.planogramId FROM (SELECT
                        p.planogramId
                    FROM
                        planogramDeviceLogic p
                    LEFT JOIN deviceGroupLogicMapping dglm ON
                        p.id = dglm.id
                        AND p.logicType = 2
                    WHERE
                        p.customerId = :customerId
                        AND p.status = 1
                        AND dglm.deviceGroupId = (
                        SELECT
                            ddd.deviceGroupId
                        FROM
                            device ddd
                        WHERE
                            ddd.deviceId = :deviceId)) AS b
                            
                    UNION
                        
                    SELECT c.planogramId FROM (SELECT
                        p.planogramId
                    FROM
                        planogramDeviceLogic p
                    LEFT JOIN locationLogicMapping llm ON
                        p.id = llm.id
                        AND p.logicType = 1
                    WHERE
                        p.customerId = :customerId
                        AND p.status = 1
                        AND llm.locationId in (:parentLocationIds)) AS c
                    
                    UNION
                        
                    SELECT
                        d.planogramId
                    FROM
                        (
                        SELECT
                            p.planogramId
                        FROM
                            planogramDeviceLogic p
                        LEFT JOIN locationWithDeviceGroup lwdg ON
                            p.id = lwdg.deviceGroupLocationLogicId
                            AND p.logicType = 3
                        LEFT JOIN deviceGroupMapping dgm ON
                            lwdg.id = dgm.id
                        WHERE
                            p.customerId = :customerId
                            AND p.status = 1
                            AND lwdg.locationId in (:parentLocationIds)
                            AND dgm.deviceGroupId = (
                            SELECT
                                ddd2.deviceGroupId
                            FROM
                                device ddd2
                            WHERE
                                ddd2.deviceId = :deviceId)) AS d"""
    )
    fun findPlanogramForADevice(
        @Param("deviceId") deviceId: Long,
        @Param("parentLocationIds") parentLocationIds: List<Long>,
        @Param("customerId") customerId: Long
    ): List<Number>

    @Procedure(procedureName = "getPlanogramIdsForDevice")
    fun getPlanogramsForADeviceByStoredProcedure(deviceId: Long, customerId: Long): List<Number>
}