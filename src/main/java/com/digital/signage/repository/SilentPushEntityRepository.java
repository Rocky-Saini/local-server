package com.digital.signage.repository;

import com.digital.signage.models.SilentPushEntity;
import java.util.Date;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface SilentPushEntityRepository extends CrudRepository<SilentPushEntity, Long> {
    @Transactional
    @Modifying
    @Query(value = "delete from silent_push_entity where time_of_sending_push < :timeOfSendingPush or time_of_sending_push is null",
            nativeQuery = true)
    int deleteStaleSilentPushOlderThan(@Param("timeOfSendingPush") Date date);
}
