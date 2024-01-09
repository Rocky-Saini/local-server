package com.digital.signage.repository;

import com.digital.signage.models.PremisesId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PremisesIdRepository extends CrudRepository<PremisesId, Long> {

    @Query(value = "SELECT * FROM premisesid WHERE pk_id = 1", nativeQuery = true)
    PremisesId findAllByPkID();
}
