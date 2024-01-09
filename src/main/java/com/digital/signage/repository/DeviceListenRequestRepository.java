package com.digital.signage.repository;

import com.digital.signage.models.DeviceListenRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author -Ravi Kumar created on 12/28/2022 1:47 AM
 * @project - Digital Sign-edge
 */
@Repository
public interface DeviceListenRequestRepository extends JpaRepository<DeviceListenRequest, Long> {

    @Query(value = "select * from device_listen_request as dl where dl.fb_registration_id <>:fbRegistrationId and dl.user_id =:userId and  dl.state = 0", nativeQuery = true)
    List<DeviceListenRequest> findAllByFirebaseRegistrationIdAndStateAndUserId(@Param("fbRegistrationId") String fbRegistrationId, @Param("userId") Long userId);

    @Query(value = "select * from device_listen_request as dl where dl.fb_registration_id =:fbRegistrationId and  dl.state = 0", nativeQuery = true)
    List<DeviceListenRequest> findAllByFirebaseRegistrationIdAndState(
            @Param("fbRegistrationId") String fbRegistrationId);

    @Query(value = "select * from device_listen_request as dl where dl.state = 0", nativeQuery = true)
    List<DeviceListenRequest> findAllByState();

    @Query(value = "select dl.device_id from device_listen_request as dl where dl.fb_registration_id<>:fbRegistrationId "
            + "and dl.state = 0 group by dl.device_id", nativeQuery = true)
    List<BigInteger> findAllStartOfOtherFirebaseIds(@Param("fbRegistrationId") String fbRegistrationId);

    @Query(value = "select * from device_listen_request as dl where dl.state = 0 and "
            + " dl.start_time <=NOW() - INTERVAL 1 MINUTE group by dl.device_id", nativeQuery = true)
    List<DeviceListenRequest> findAllByStateAndBefore2MinAnd4Minute();

    @Query(value = "select * from device_listen_request as dl where dl.state = 0", nativeQuery = true)
    List<DeviceListenRequest> findAllByStateStarted();

    @Query(value = "select * from device_listen_request as dl where dl.device_id = :deviceId and  dl.state = 0", nativeQuery = true)
    List<DeviceListenRequest> findAllByDeviceIdAndState(@Param("deviceId") Long deviceId);

    @Query(value = "select * from device_listen_request as dl where dl.device_id IN (:deviceIds) and  dl.state = 0", nativeQuery = true)
    List<DeviceListenRequest> findAllByDeviceIdsAndState(@Param("deviceIds") Set<Long> deviceIds);

    @Query(value = "SELECT * FROM device_listen_request AS dl WHERE dl.device_id = :deviceId "
            + " AND dl.fb_registration_id <> :fbRegistrationId "
            + " AND dl.start_time > :expiryDate "
            + " AND dl.state = 0", nativeQuery = true)
    List<DeviceListenRequest> findAllByDeviceIdAndStateAndFirebaseNotAndTimeNotExpired(
            @Param("deviceId") Long deviceId,
            @Param("fbRegistrationId") String fbRegistrationId,
            @Param("expiryDate") Date expiryDate);
}
