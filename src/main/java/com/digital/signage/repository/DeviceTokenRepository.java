package com.digital.signage.repository;

import com.digital.signage.models.DeviceToken;
import com.digital.signage.models.DeviceAuthModel;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface DeviceTokenRepository extends CrudRepository<DeviceToken, Long> {

    DeviceToken findDeviceTokenByIdAndDeviceId(Long id, Long deviceId);

    List<DeviceToken> findDeviceTokensByExpiryDateBefore(Date date);

    List<DeviceToken> findDeviceTokenByDeviceId(Long deviceId);

    @Query(value = "SELECT * FROM device_token where device_id IN (:deviceIds)", nativeQuery = true)
    List<DeviceToken> findDeviceTokenByDeviceIds(@Param("deviceIds") List<Long> deviceIds);

    @Query(value = "SELECT id FROM device_token where device_id IN (:deviceIds)", nativeQuery = true)
    List<Long> findDeviceTokenIdByDeviceId(@Param("deviceIds") List<Long> deviceIds);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM device_token where id IN (:deviceIds)", nativeQuery = true)
    void deleteToken(@Param("deviceIds") List<Long> ids);

    @Query(value =
            "SELECT dt.id, dt.device_id, dt.expiry_date, c.customer_id, c.status, c.is_customer_onboarded FROM device_token dt "
                    + " INNER JOIN device d ON dt.device_id = d.device_id "
                    + " INNER JOIN customer c ON c.customer_id = d.customer_id "
                    + " WHERE dt.id = :id "
                    + " AND d.status = 1", nativeQuery = true)
    List<DeviceAuthModel> getDeviceTokenIfItExistsAndIfDeviceStatusActive(@Param("id") Long id);
}