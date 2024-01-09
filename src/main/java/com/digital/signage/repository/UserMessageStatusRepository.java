package com.digital.signage.repository;

import com.digital.signage.models.UserMessageStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMessageStatusRepository extends CrudRepository<UserMessageStatus, Long> {
}
