package com.digital.signage.repository;

import com.digital.signage.models.DeviceErrorHistory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceErrorHistoryRepository extends CrudRepository<DeviceErrorHistory, Long> {
}
