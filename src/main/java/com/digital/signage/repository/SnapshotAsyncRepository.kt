package com.digital.signage.repository

import com.digital.signage.models.SnapshotAsyncModel
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface SnapshotAsyncRepository : CrudRepository<SnapshotAsyncModel, Long>{

}