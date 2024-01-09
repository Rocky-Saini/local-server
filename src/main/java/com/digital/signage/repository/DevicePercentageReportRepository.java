package com.digital.signage.repository;

import com.digital.signage.models.DeviceLogPercentageReportNewDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface DevicePercentageReportRepository extends JpaRepository<DeviceLogPercentageReportNewDTO, Long> {

    @Modifying
    @Query(value = "select * FROM device_per_report where Date(date) = Date(:currentDate)", nativeQuery = true)
    List<DeviceLogPercentageReportNewDTO> CurrentDateRecords(@Param("currentDate") Date currentDate);

}