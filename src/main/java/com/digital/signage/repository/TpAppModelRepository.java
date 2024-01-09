package com.digital.signage.repository;

import com.digital.signage.models.TpAppModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface TpAppModelRepository extends JpaRepository<TpAppModel, Long> {

    List<TpAppModel> findAllByTpAppIdIn(Set<Long> tpAppId);
}
