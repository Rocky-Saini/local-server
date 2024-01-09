package com.digital.signage.service

interface SnapshotQueueService {

    fun addToQueue(data: String)

    fun registerConsumer(consumer: SnapshotConsumer)

    fun gc()

    fun close()
}