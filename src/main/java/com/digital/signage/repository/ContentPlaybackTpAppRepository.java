package com.digital.signage.repository;

import com.digital.signage.models.ContentPlaybackTpAppData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentPlaybackTpAppRepository
        extends CrudRepository<ContentPlaybackTpAppData, Long> {
}
