package digital.signage.util

import com.digital.signage.dto.ContentDownloadAnalyticsDTO
import com.digital.signage.models.ContentDownloadAnalytics
import com.digital.signage.util.dateIn_HH_mm_ss


fun createContentAnalyticsModelDataFromDto(
        dto: ContentDownloadAnalyticsDTO?): ContentDownloadAnalytics {
    val model = ContentDownloadAnalytics()
    dto.let {
        if (it != null) {
            model.batchKey = it.batchKey
            model.event = it.event
            model.contentIds = it.contentIds
            model.reasonForFailure = it.reasonForFailure
            model.downloadStatusInPercentage = it.downloadStatusInPercentage
            model.isLastBatchBeforeStoppingRetries = it.isLastBatchBeforeStoppingRetries
            model.progressPercent = it.progressPercent
            model.failedContents = it.failedContents
            model.timeOfEvent = it.timestampOfEvent?.let { it1 -> dateIn_HH_mm_ss(it1) }
            model.internetSpeedInBitsPerSeconds = it.internetSpeedInBitsPerSeconds
        }
    }
    return model
}