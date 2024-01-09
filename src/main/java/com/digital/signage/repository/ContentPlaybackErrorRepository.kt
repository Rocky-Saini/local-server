package com.digital.signage.repository;

import com.digital.signage.models.ContentPlaybackError
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository


@Repository
interface ContentPlaybackErrorRepository : CrudRepository<ContentPlaybackError, Long> {
}
