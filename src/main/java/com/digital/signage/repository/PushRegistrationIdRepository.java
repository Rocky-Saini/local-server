package com.digital.signage.repository;

import com.digital.signage.models.PushRegistrationIdModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PushRegistrationIdRepository
        extends CrudRepository<PushRegistrationIdModel, Long> {

    PushRegistrationIdModel findPushRegistrationIdModelByDeviceId(Long deviceId);
}
