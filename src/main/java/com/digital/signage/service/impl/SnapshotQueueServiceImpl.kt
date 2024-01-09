package com.digital.signage.service.impl

import com.digital.signage.service.SnapshotConsumer
import com.digital.signage.service.SnapshotQueueService
import com.leansoft.bigqueue.BigQueueImpl
import com.leansoft.bigqueue.IBigQueue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.nio.file.Paths
import javax.annotation.PostConstruct


@Service
class SnapshotQueueServiceImpl : SnapshotQueueService {
    private lateinit var bigQueue: IBigQueue

    private lateinit var snapshotConsumerCoroutineScope: CoroutineScope

    @Value("\${big.queue.base.dir}")
    private lateinit var queueDir: String

    companion object {
        val logger: Logger = LoggerFactory.getLogger(SnapshotQueueServiceImpl::class.java)
        private const val WAIT_FOR_NEXT_CYCLE_IN_MILLS = 30L * 1000L // 30s
    }

    @PostConstruct
    fun onCreate() {
        val queueName = "snapshot-temp"
        val path = Paths.get(queueDir, queueName)
        bigQueue = BigQueueImpl(path.toString(), queueName)
        snapshotConsumerCoroutineScope = CoroutineScope(context = Dispatchers.IO)
    }

    override fun registerConsumer(consumer: SnapshotConsumer) {
        runJob(consumer)
    }

    override fun gc() {
        bigQueue.gc()
    }

    override fun addToQueue(data: String) {
        bigQueue.enqueue(data.toByteArray(Charsets.UTF_8))
    }

    override fun close() {
        bigQueue.close()
    }

    fun runJob(consumer: SnapshotConsumer) {
        snapshotConsumerCoroutineScope.launch {
            logger.debug("SnapshotConsumer in runJob")
            while (true) {
                if (bigQueue.isEmpty) {
                    bigQueue.gc()
                    delay(WAIT_FOR_NEXT_CYCLE_IN_MILLS)
                    continue
                }
                while (true) {
                    logger.debug("SnapshotConsumer in inner while true loop!!")
                    logger.debug("snapshot-temp-big-queue has {} elements.", bigQueue.size())
                    val byteArray = bigQueue.dequeue()
                    if (byteArray == null) {
                        // no data breaking inner loop
                        logger.debug("no data breaking inner loop")
                        break
                    }
                    val queueId = String(byteArray, Charsets.UTF_8)
                    logger.debug("queueId = {}", queueId)
                    consumer.processQueueId(queueId)
                    bigQueue.gc()
                }
                logger.debug("SnapshotConsumer in outer while end of iteration")
            }
        }
    }
}