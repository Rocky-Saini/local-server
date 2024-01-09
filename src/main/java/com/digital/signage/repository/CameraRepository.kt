package com.digital.signage.repository

import com.digital.signage.models.Camera
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface CameraRepository : JpaRepository<Camera, Long> {

    @Query("SELECT * FROM camera WHERE device_id IN (:deviceIds)", nativeQuery = true)
    fun getCamerasByDeviceIds(@Param("deviceIds") deviceIds: Set<Long>): List<Camera>

    @Query("SELECT * FROM camera WHERE device_id = :deviceId", nativeQuery = true)
    fun getCameraByDeviceId(@Param("deviceId") deviceId: Long): Camera?

    @Query(
        value = "SELECT COUNT(cm.camera_id) "
                + " FROM camera AS cm "
                + " LEFT JOIN device AS d ON d.device_id = cm.device_id "
                + " INNER JOIN customer AS ct ON ct.customer_id = d.customer_id "
                + " WHERE "
                + " d.device_id = :deviceId "
                + " AND d.device_os IN (0,1) "
                + " AND ct.enable_demographic = 1 "
                + " AND d.status IN (1,2) ",
        nativeQuery = true
    )
    fun countCameraForShowUploadDemographicDataInterval(
        @Param("deviceId") deviceId: Long?
    ): Int

}