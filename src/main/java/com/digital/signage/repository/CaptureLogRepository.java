package com.digital.signage.repository;

import com.digital.signage.models.Logs;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CaptureLogRepository extends CrudRepository<Logs, Long> {

    @Query(value =
            "select l.* from logs l ,device d where l.device_id=d.device_id and l.device_id = :deviceId and (l.logs_update_status is null or l.logs_update_status = true) and ((l.log_file_start_time between :queryStartTime and :queryEndTime)"
                    + "or (l.log_file_end_time between :queryStartTime and :queryEndTime))", nativeQuery = true)
    List<Logs> getLogsFilesInRange(@Param("deviceId") Long deviceId,
                                   @Param("queryStartTime") Date queryStartTime, @Param("queryEndTime") Date queryEndTime);

}
