package com.digital.signage.repository;

import com.digital.signage.models.CurrentDownloadPush;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author -Ravi Kumar created on 1/25/2023 1:11 AM
 * @project - Digital Sign-edge
 */
@Repository
public interface CurrentDownloadPushRepository extends CrudRepository<CurrentDownloadPush, Long> {

    @Query(value = "SELECT * from current_download_device_push as cdp where cdp.TIME <=:dateTimeHalfAnhourBeforeCurrentTime", nativeQuery = true)
    List<CurrentDownloadPush> findAllByTime(
            @Param("dateTimeHalfAnhourBeforeCurrentTime") Date dateTimeHalfAnhourBeforeCurrentTime);

    @Query(value = "SELECT * from current_download_device_push as cdp where cdp.device_id =:deviceId", nativeQuery = true)
    List<CurrentDownloadPush> findAllByDeviceId(@Param("deviceId") Long deviceId);

    @Query(value = "SELECT * from current_download_device_push as cdp where cdp.device_id in (:deviceIds)", nativeQuery = true)
    List<CurrentDownloadPush> findAllByDeviceIds(@Param("deviceIds") List<Long> deviceIds);

    @Query(value = "SELECT * from current_download_device_push as cdp where "
            + "cdp.firebase_registration_id =:fireBaseRegId GROUP By cdp.device_id", nativeQuery = true)
    List<CurrentDownloadPush> findAllByFireBaseId(@Param("fireBaseRegId") String fireBaseRegId);

    @Query(value = "SELECT * from current_download_device_push as cdp where "
            + "cdp.firebase_registration_id =:fireBaseRegId  and cdp.user_id =:userId  ", nativeQuery = true)
    List<CurrentDownloadPush> findAllByFireBaseIdAndUserId(
            @Param("fireBaseRegId") String fireBaseRegId, @Param("userId") Long userId);


    @Query(nativeQuery = true, value = "SELECT * FROM current_download_device_push")
    List<CurrentDownloadPush> getAll();
}

