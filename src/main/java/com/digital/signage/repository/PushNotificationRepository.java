package com.digital.signage.repository;

import com.digital.signage.models.PushNotification;
import com.digital.signage.util.PushId;
import com.digital.signage.util.PushNotificationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * @author -Ravi Kumar created on 1/25/2023 1:14 AM
 * @project - Digital Sign-edge
 */
@Repository
public interface PushNotificationRepository extends JpaSpecificationExecutor<PushNotification>,
        JpaRepository<PushNotification, Long> {

    //for acknowledgement api, will remove when acknowledge API change done according msgId
    PushNotification getAllByDeviceIdAndPushIdAndStatus(
            Long deviceId,
            PushId pushId,
            PushNotificationStatus pushNotificationStatus
    );

    PushNotification getById(Long messageId);

    List<PushNotification> getByDeviceIdAndPushIdAndStatus(
            Long deviceId,
            Integer pushId,
            PushNotificationStatus pushNotificationStatus
    );

    //status 2->SENT and 3-> FAILURE
    //PushId 8-> CLIENT AUDIO OFF and 11-> CLIENT AUDIO ON
    @Query(value = "SELECT * FROM push_notification pn "
            + " WHERE pn.device_id = :deviceId "
            + " AND pn.push_id IN (8,11) "
            + " AND pn.status IN (2,3) ", nativeQuery = true)
    List<PushNotification> getAllDeviceAudioNotificationByDeviceId(
            @Param(value = "deviceId") Long deviceId);

    @Query(value = "SELECT * FROM push_notification pn "
            + " WHERE pn.device_id IN (:deviceIds) "
            + " AND pn.push_id IN (8,11) "
            + " AND pn.status IN (2,3) ", nativeQuery = true)
    List<PushNotification> getAllDeviceAudioNotificationByDeviceId(
            @Param(value = "deviceIds") Set<Long> deviceIds);

    List<PushNotification> getByDeviceIdAndPushIdInAndStatusIn(
            Long deviceId,
            List<Integer> pushId,
            List<PushNotificationStatus> pushNotificationStatus
    );

    @Query(value = "SELECT * FROM push_notification pn "
            + " WHERE pn.device_id IN (:deviceIds) "
            + " AND pn.push_id IN (:pushIds) "
            + " AND pn.status IN (:pushNotificationStatuses) ", nativeQuery = true)
    List<PushNotification> getByDeviceIdsAndPushIdInAndStatusIn(
            @Param(value = "deviceIds") Set<Long> deviceIds,
            @Param(value = "pushIds") List<Integer> pushIds,
            @Param(value = "pushNotificationStatuses")
            List<Integer> pushNotificationStatuses
    );
}

