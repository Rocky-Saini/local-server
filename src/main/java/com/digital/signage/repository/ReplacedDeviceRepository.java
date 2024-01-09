package com.digital.signage.repository;

import com.digital.signage.models.ReplacedDevice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author -Ravi Kumar created on 1/28/2023 4:34 PM
 * @project - Digital Sign-edge
 */
@Repository
public interface ReplacedDeviceRepository extends CrudRepository<ReplacedDevice, Long> {
}
