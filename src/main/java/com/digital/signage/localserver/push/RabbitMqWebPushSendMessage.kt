package com.digital.signage.localserver.push

import com.fasterxml.jackson.databind.ObjectMapper
import com.rabbitmq.client.Channel
import com.rabbitmq.client.Connection
import com.digital.signage.dto.ServerLaunchConfig
import com.digital.signage.models.UserMessage
import com.digital.signage.models.UserMessageStatus
import com.digital.signage.repository.UserMessageRepository
import com.digital.signage.repository.UserMessageStatusRepository
import com.digital.signage.repository.UserRepository
import com.digital.signage.util.PushNotificationStatus.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.nio.charset.StandardCharsets

//import com.digital.signage.util.newConnection

@Service
class RabbitMqWebPushSendMessage {

    @Autowired
    private lateinit var serverLaunchConfig: ServerLaunchConfig

    @Value("\${my.rabbit.mq.username}")
    private lateinit var myRabbitMqServerUsername: String

    @Value("\${my.rabbit.mq.password}")
    private lateinit var myRabbitMqServerPassword: String

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Autowired
    private lateinit var userMessageRepository: UserMessageRepository

    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var userMessageStatusRepository: UserMessageStatusRepository

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(RabbitMqWebPushSendMessage::class.java)
    }

    private val webRegistrationId: String = "throughRabbitMqWebPush"
    private val routingKey: String = ""

    private val buildInExchangeType = "direct"

    private fun newConnection(): Connection = newConnection(
        username = myRabbitMqServerUsername,
        password = myRabbitMqServerPassword,
        rabbitMQServerIp = serverLaunchConfig.rabbitMqHost!!
    )

    fun createPermanentWebPushChannelForMultipleUsers(userIds: List<Number>) {
        if (userIds.isNullOrEmpty()) return
        try {
            newConnection().use {
                userIds.forEach { userId ->
                    val channel = it.createChannel()
                    channel.exchangeDeclare(
                        "push_web_$userId",
                        buildInExchangeType,
                        true,
                        false,
                        null
                    )
                    channel.close()
                }
            }
        } catch (e: Exception) {
            logger.error("", e)
        }
    }

    fun createPermanentWebPushChannelForUser(userId: String) {
        try {
            newConnection().use {
                val channel = it.createChannel()
                channel.exchangeDeclare(
                    "push_web_$userId",
                    buildInExchangeType,
                    true,
                    false,
                    null
                )
                channel.close()
            }
        } catch (e: Exception) {
            logger.error("", e)
        }
    }

    @Throws(RuntimeException::class)
    fun sendWebPushThroughRabbitMq(
        userMessage: UserMessage,
        isSilentPush: Boolean
    ) {
        val msg = objectMapper.writeValueAsString(userMessage)
            ?: throw RuntimeException("Message cannot be null")
        userMessage.isState = false
        val user = userRepository.findUserByUserId(userMessage.userId)
        if (user == null) {
            logger.error("User not exist to sent push {}", userMessage.userId)
            return
        }
        val exchangeName = "push_web_${userMessage.userId}"
        var channel: Channel? = null
        val userMessageStatus = UserMessageStatus()
        userMessageStatus.webRegistrationId = webRegistrationId
        userMessageStatus.status = SENDING.ordinal
        try {
            newConnection().use {
                channel = it.createChannel()
                // same exchange name should use at the time of received.
                channel?.exchangeDeclare(
                    exchangeName,
                    buildInExchangeType,
                    true,
                    false,
                    null
                )
                channel?.basicPublish(
                    exchangeName,
                    routingKey,
                    null,
                    msg.toByteArray(StandardCharsets.UTF_8)
                )
                logger.debug("Push Send to local Server for exchange $exchangeName : $msg")
                userMessageStatus.status = SENT.ordinal
            }
        } catch (e: Exception) {
            userMessageStatus.status = FAILURE.ordinal
            logger.error("error in sending push on channel $exchangeName", e)
        } finally {
            if (channel != null && channel!!.isOpen) {
                channel?.close()
            }
            saveUserMessageStatus(
                userMessage = userMessage,
                isSilentPush = isSilentPush,
                userMessageStatus = userMessageStatus
            )
        }
    }

    private fun saveUserMessageStatus(
        userMessage: UserMessage,
        userMessageStatus: UserMessageStatus,
        isSilentPush: Boolean) {
        if (!isSilentPush) {
            userMessageRepository.save(userMessage)
            userMessageStatus.userMessageId = userMessage.messageId
            userMessageStatusRepository.save(userMessageStatus)
        }
    }
}