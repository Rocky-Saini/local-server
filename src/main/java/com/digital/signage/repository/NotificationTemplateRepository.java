package com.digital.signage.repository;

import com.digital.signage.models.NotificationTemplate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author -Ravi Kumar created on 1/23/2023 3:42 PM
 * @project - Digital Sign-edge
 */
@Repository
public interface NotificationTemplateRepository extends CrudRepository<NotificationTemplate, Long> {
    NotificationTemplate findByMessageType(Integer messageType);
}
