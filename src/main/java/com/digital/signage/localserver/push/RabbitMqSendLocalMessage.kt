package com.digital.signage.localserver.push

import com.digital.signage.localserver.config.LocalServerData
import com.rabbitmq.client.Connection
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.nio.charset.StandardCharsets

/**
 * Local server uses this service to
 * sends message to all its devices
 */
@Service
class RabbitMqSendLocalMessage {
    companion object {
        private val logger: Logger = LoggerFactory.getLogger(RabbitMqSendLocalMessage::class.java)
    }

    @Value("\${local.server.rebbitMq.userName}")
    private lateinit var localRabbitMqUserName: String

    @Value("\${local.server.rebbitMq.password}")
    private lateinit var localRabbitMQPassword: String

    @Autowired
    private lateinit var localServerData: LocalServerData

    private fun newConnection(): Connection = newConnection(
        username = localRabbitMqUserName,
        password = localRabbitMQPassword,
        rabbitMQServerIp = "localhost"
    )

    fun sendLocalPush(pushPayload: String) {
        if (localServerData.customerId != null) {
            val customerId = localServerData.customerId
            val exchangeName = "push_${customerId!!}"
            try {
                logger.debug(
                    "creating connection with local rabbitMq...with exchange name $exchangeName")
                val connection = newConnection()
                logger.debug("this = $this")
                logger.info("connection created... $connection")
                val channel = connection.createChannel()
                // same exchange name should use at the time of received.
                channel.exchangeDeclare(exchangeName, "fanout")
                channel.basicPublish(
                    exchangeName,
                    "",
                    null,
                    pushPayload.toByteArray(StandardCharsets.UTF_8)
                )
                logger.info("Push Send to devices: $pushPayload")
                channel.close()
                connection.close()
            } catch (e: Exception) {
                logger.error("error in sending push to devices", e)
            }
        } else {
            logger.info("no local data available to create exchange")
        }
    }
}