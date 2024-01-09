package com.digital.signage.repository;

import com.digital.signage.models.UserActivity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @author -Ravi Kumar created on 1/21/2023 9:57 PM
 * @project - Digital Sign-edge
 */
@Repository
public interface UserActivityRepository extends JpaSpecificationExecutor<UserActivity>,
        PagingAndSortingRepository<UserActivity, Long> {
}
