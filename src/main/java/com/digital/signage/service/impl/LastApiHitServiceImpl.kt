package com.digital.signage.service.impl

import com.digital.signage.constants.UserConstants
import com.digital.signage.models.LastApiHitTimeModel
import com.digital.signage.repository.LastApiHitTimeRepository
import com.digital.signage.service.LastApiHitService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class LastApiHitServiceImpl @Autowired constructor(
    private val lastApiHitTimeRepository: LastApiHitTimeRepository
) : LastApiHitService {

//    @Async(UserConstants.ASYNC_LAST_API_HIT_WORKERS)


    override fun saveLastApiHit(lastApiHitTimeModel: LastApiHitTimeModel) {
        // save last hit
    val existingRecord = lastApiHitTimeRepository.findByDeviceId(lastApiHitTimeModel.deviceId)

    if (existingRecord.isNotEmpty()) {
        // Update existing record
        //val existingRecordEntity = existingRecord.get()
        lastApiHitTimeRepository.deleteAll(existingRecord);
        // Update fields with new values from lastApiHitTimeModel
//        existingRecordEntity.deviceId = lastApiHitTimeModel.deviceId
        //existingRecordEntity.timeOfLastApiHit = lastApiHitTimeModel.timeOfLastApiHit
        // Update other fields as needed

        // Save the updated record
        //lastApiHitTimeRepository.save(existingRecordEntity)

    } else {
        // Save new record if it does not exist
        //lastApiHitTimeRepository.save(lastApiHitTimeModel)
    }
        lastApiHitTimeRepository.save(lastApiHitTimeModel);
}
    }
