package com.digital.signage.service

interface SnapshotConsumer {
    fun processQueueId(queueId: String)
}