package com.digital.signage.repository

import com.digital.signage.models.ExceptionEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

/**
 * @author -Ravi Kumar created on 1/29/2023 9:01 PM
 * @project - Digital Sign-edge
 */
@Repository
interface AllExceptionsRepository : CrudRepository<ExceptionEntity, Long>