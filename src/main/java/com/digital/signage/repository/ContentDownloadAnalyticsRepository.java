package com.digital.signage.repository;

import com.digital.signage.models.ContentDownloadAnalytics;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentDownloadAnalyticsRepository
        extends CrudRepository<ContentDownloadAnalytics, Long> {

    @Query(
            nativeQuery = true,
            value = "SELECT * FROM content_download_analytics WHERE device_id = :deviceId AND batch_key IN (:batchKeys)"
    )
    List<ContentDownloadAnalytics> findByDeviceIdAndListOfBatchKey(
            @Param("deviceId") Long deviceId,
            @Param("batchKeys") List<String> batchKeys
    );

    List<ContentDownloadAnalytics> findByDeviceIdAndBatchKey(Long deviceId, String batchKey);

}
