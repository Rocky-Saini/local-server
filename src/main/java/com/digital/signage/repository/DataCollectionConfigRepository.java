package com.digital.signage.repository;

import com.digital.signage.models.DataCollectionConfig;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DataCollectionConfigRepository extends CrudRepository<DataCollectionConfig, Long> {

    @Query(value = "SELECT * FROM data_collection_config WHERE DATE(config_date) = :configDate AND device_id = :deviceId", nativeQuery = true)
    List<DataCollectionConfig> getDeviceConfigByConfigDate(
            @Param("configDate") Date configDate,
            @Param("deviceId") Long deviceId
    );

    @Query(value = "SELECT * FROM data_collection_config WHERE config_date >= :configStartDate AND config_date <= :configEndDate AND device_id IN (:deviceIds) ORDER BY device_id, config_date DESC ", nativeQuery = true)
    List<DataCollectionConfig> getDeviceConfigBetweenTwoConfigDates(
            @Param("configStartDate") Date configStartDate,
            @Param("configEndDate") Date configEndDate,
            @Param("deviceIds") List<Long> deviceIds
    );

    @Query(value = "SELECT * FROM data_collection_config WHERE DATE(config_date) IN(:configDates) AND device_id = :deviceId", nativeQuery = true)
    List<DataCollectionConfig> getDeviceConfigOfAllConfigDate(
            @Param("configDates") List<Date> configDates,
            @Param("deviceId") Long deviceId
    );
}
