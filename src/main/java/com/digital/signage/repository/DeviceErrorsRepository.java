package com.digital.signage.repository;

import com.digital.signage.models.DeviceErrors;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceErrorsRepository extends CrudRepository<DeviceErrors, Long> {
}
