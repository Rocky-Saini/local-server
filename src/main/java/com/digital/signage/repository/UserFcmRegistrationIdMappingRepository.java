package com.digital.signage.repository;

import com.digital.signage.models.UserFcmRegistrationIdMapping;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserFcmRegistrationIdMappingRepository
        extends CrudRepository<UserFcmRegistrationIdMapping, Long> {
    UserFcmRegistrationIdMapping findByUserIdAndRegistrationId(Long userId, String registrationId);

    List<UserFcmRegistrationIdMapping> findByUserId(Long userId);

    List<UserFcmRegistrationIdMapping> findByRegistrationId(String registrationId);

    @Query(nativeQuery = true,
            value = "select * from user_fcm_registration_id_mapping where time_of_adding_registration_id < :expiry")
    List<UserFcmRegistrationIdMapping> selectAllStaleFirebaseIds(@Param("expiry") Date expiry);
}
