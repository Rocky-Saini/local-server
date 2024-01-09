package com.digital.signage.repository

import com.digital.signage.models.ConfigHistory
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

/**
 * @author -Ravi Kumar created on 1/19/2023 10:17 AM
 * @project - Digital Sign-edge
 */
@Repository
interface ConfigHistoryRepository : CrudRepository<ConfigHistory, Long>