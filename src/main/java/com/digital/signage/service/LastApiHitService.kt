package com.digital.signage.service

import com.digital.signage.constants.UserConstants
import com.digital.signage.models.LastApiHitTimeModel
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service

@Service
interface LastApiHitService {

//    @Async(UserConstants.ASYNC_LAST_API_HIT_WORKERS)
    fun saveLastApiHit(lastApiHitTimeModel: LastApiHitTimeModel)
}