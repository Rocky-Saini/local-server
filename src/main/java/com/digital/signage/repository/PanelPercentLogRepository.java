package com.digital.signage.repository;

import com.digital.signage.models.PanelLogPercentageResponseNewDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PanelPercentLogRepository extends JpaRepository<PanelLogPercentageResponseNewDTO,Long> {
    @Query(value = "select * FROM panel_log_per_report where Date(date) = Date(:currentDate)", nativeQuery = true)
    List<PanelLogPercentageResponseNewDTO> CurrentDateRecords(@Param("currentDate") Date currentDate);

}
