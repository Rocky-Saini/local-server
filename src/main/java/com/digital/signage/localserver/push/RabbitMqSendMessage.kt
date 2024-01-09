package com.digital.signage.localserver.push

import com.fasterxml.jackson.databind.ObjectMapper
import com.rabbitmq.client.Channel
import com.rabbitmq.client.Connection
import com.digital.signage.dto.OnPremiseMessageDTO
import com.digital.signage.dto.PushDataLocalServer
import com.digital.signage.dto.ServerLaunchConfig
import com.digital.signage.models.LocalPushRequestModel
import com.digital.signage.models.PushNotification
import com.digital.signage.repository.PushNotificationRepository
import com.digital.signage.util.BuildOs
import com.digital.signage.util.PushId
import com.digital.signage.util.PushNotificationStatus
import com.digital.signage.util.PushUtils
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.nio.charset.StandardCharsets

//import com.digital.signage.util.ON_PREMISES_RABBIT_MQ_EXCHANGE_NAME;
//import com.digital.signage.util.newConnection

@Service
class RabbitMqSendMessage {

    @Autowired
    private lateinit var serverLaunchConfig: ServerLaunchConfig

    @Value("\${my.rabbit.mq.username}")
    private lateinit var myRabbitMqServerUsername: String

    @Value("\${my.rabbit.mq.password}")
    private lateinit var myRabbitMqServerPassword: String

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Autowired
    private lateinit var pushNotificationRepository: PushNotificationRepository

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(RabbitMqSendMessage::class.java)
        private const val EXCHANGE_NAME_PUSH_COMMON = "push_common"
    }

    private fun newConnection(): Connection = newConnection(
        username = myRabbitMqServerUsername,
        password = myRabbitMqServerPassword,
        rabbitMQServerIp = serverLaunchConfig.rabbitMqHost!!
    )

    fun sendOnPremiseServerPush(onPremiseMessageDTO: OnPremiseMessageDTO) {
        var connection: Connection? = null
        var channel: Channel? = null
        try {
            connection = newConnection()
            channel = connection.createChannel()

            // same exchange name should use at the time of received.
            channel!!.exchangeDeclare(
                ON_PREMISES_RABBIT_MQ_EXCHANGE_NAME,
                "fanout"
            )
            val msg = PushUtils.createOnPremiseServerPayload(onPremiseMessageDTO, objectMapper)
            channel.basicPublish(
                ON_PREMISES_RABBIT_MQ_EXCHANGE_NAME,
                "",
                null,
                msg!!.toByteArray(StandardCharsets.UTF_8)
            )
            logger.info("Push send to on premise server: $msg")
            channel.close()
            connection.close()
        } catch (e: Exception) {
            logger.error("error in sending push")
            logger.error("", e);
        } finally {
            try {
                if (channel != null && channel.isOpen) {
                    channel.close()
                }
                if (connection != null && connection.isOpen) {
                    connection.close()
                }
            } catch (e: Exception) {
                logger.error("error in closing connection/Channel", e.message)
            }
        }
    }

    @Throws(RuntimeException::class)
    fun sendLocalPush(
        pushId: PushId,
        customerId: Long,
        deviceId: Long,
        panelId: Long? = null,
        uniqueRequestId: String? = null,
        contentId: Long? = null,
        contentVersion: Long? = null,
        pushNotification: PushNotification,
        instantUpgrade: Boolean? = null,
        tpappId: Long? = null,
        buildOs: BuildOs? = null
    ) {
        val localPushRequestModel = LocalPushRequestModel(
            deviceId,
            panelId,
            uniqueRequestId,
            contentId,
            contentVersion,
            pushNotification,
            instantUpgrade,
            tpappId
        )
        val pushPayload = PushUtils.createPushPayloadObject(
            localPushRequestModel.pushNotification.id,
            pushId,
            localPushRequestModel.deviceId,
            localPushRequestModel.panelId,
            localPushRequestModel.uniqueRequestId,
            localPushRequestModel.contentId,
            localPushRequestModel.contentVersion,
            localPushRequestModel.instantUpgrade,
            buildOs
        )

        val msg = PushUtils.createPushPayload(pushPayload, objectMapper)
            ?: throw RuntimeException("Message cannot be null")
        savePushDataMapper(
            sendLocalPush(
                pushId,
                customerId,
                localPushRequestModel,
                msg
            ),
            pushPayload = msg
        )
    }

    @Throws(RuntimeException::class)
    fun justCreateACustomerExchange(customerId: Long) {
        val exchangeName = "push_$customerId"
        try {
            val connection = newConnection()
            val channel = connection.createChannel()

            channel.exchangeDeclare(exchangeName, "fanout")

            // sending test message just to add a customerId on exchange
            channel.basicPublish(exchangeName, "", null,
                "ToAddCustomerIDOnExchange".toByteArray(StandardCharsets.UTF_8))

            logger.debug("Push Send to local Server: ToAddCustomerIDOnExchange")

            channel.close()
            connection.close()
        } catch (e: Exception) {
            logger.error("error creating customer exchange", e.message)
        }
    }

    @Throws(RuntimeException::class)
    fun sendToAllLocalServersRegardlessOfCustomer(
        pushDataLocalServer: PushDataLocalServer
    ): PushNotification {
        try {
            val pushPayload = objectMapper.writeValueAsString(pushDataLocalServer)
            pushDataLocalServer.pushNotification.pushPayloadJson = pushPayload
            val connection = newConnection()
            val channel = connection.createChannel()
            // same exchange name should use at the time of received.
            channel.exchangeDeclare(EXCHANGE_NAME_PUSH_COMMON, "fanout")
            channel.basicPublish(EXCHANGE_NAME_PUSH_COMMON,
                "",
                null,
                pushPayload.toByteArray(StandardCharsets.UTF_8)
            )
            logger.debug("Push Send to local Server: $pushPayload")
            pushDataLocalServer.pushNotification.failureReason = "success: localserver"
            pushDataLocalServer.pushNotification.status = PushNotificationStatus.SENT
            channel.close()
            connection.close()
        } catch (e: Exception) {
            logger.error("error in sending push", e.message)
            pushDataLocalServer.pushNotification.status = PushNotificationStatus.FAILURE
            pushDataLocalServer.pushNotification.failureReason = e.message
        } finally {
            return pushNotificationRepository.save(pushDataLocalServer.pushNotification)
        }
    }

    @Throws(RuntimeException::class)
    fun sendLocalPush(
        pushId: PushId,
        customerId: Long,
        localPushRequestModel: LocalPushRequestModel,
        msg: String
    ): PushNotification {
        val exchangeName = "push_$customerId"
        try {
            val connection = newConnection()
            val channel = connection.createChannel()
            // same exchange name should use at the time of received.
            channel.exchangeDeclare(exchangeName, "fanout")
            channel.basicPublish(exchangeName, "", null,
                msg.toByteArray(StandardCharsets.UTF_8))
            logger.debug("Push Send to local Server: $msg")
            //updating panel & device status pending
            // push.updatePendingStatusForDevice(device,pushId.getValue());
            // push.updatePendingStatusForPanels(pushPayload,device.getCustomerId());
            localPushRequestModel.pushNotification.failureReason = "success: localserver"
            localPushRequestModel.pushNotification.status = PushNotificationStatus.SENT
            channel.close()
            connection.close()
        } catch (e: Exception) {
            localPushRequestModel.pushNotification.status = PushNotificationStatus.FAILURE
            localPushRequestModel.pushNotification.failureReason = e.message
            logger.error("error in sending push", e.message)
        } finally {
            return pushNotificationRepository.save(localPushRequestModel.pushNotification)
        }
    }

    @Throws(RuntimeException::class)
    fun sendMultipleLocalPushes(pushId: PushId, customerId: Long,
                                localPushRequestModels: List<LocalPushRequestModel>) {
        val exchangeName = "push_$customerId"
        logger.debug("local Push request Model $localPushRequestModels")
        logger.debug("this = $this")
        val listOfPushNotifications: MutableList<PushNotification> = mutableListOf()

        try {
            val connection = newConnection()
            val channel = connection.createChannel()
            // same exchange name should use at the time of received.
            channel.exchangeDeclare(exchangeName, "fanout")

            localPushRequestModels.forEach {
                val pushPayload = PushUtils.createPushPayloadObject(
                    it.pushNotification.id,
                    pushId,
                    it.deviceId,
                    it.panelId,
                    it.uniqueRequestId,
                    it.contentId,
                    it.contentVersion,
                    it.instantUpgrade,
                    it.buildOs
                )
                //updating panel & device status pending
                // push.updatePendingStatusForDevice(device,pushId.getValue());
                // push.updatePendingStatusForPanels(pushPayload,device.getCustomerId());
                val msg = PushUtils.createPushPayload(pushPayload, objectMapper)
                    ?: throw RuntimeException("Message cannot be null")
                channel.basicPublish(exchangeName, "", null,
                    msg.toByteArray(StandardCharsets.UTF_8))
                logger.info("Push Send to local Server : $msg")
                it.pushNotification.failureReason = "success: localserver"
                it.pushNotification.status = PushNotificationStatus.SENT
                logger.debug(
                    "pushlog: pushId = $pushId, deviceId = ${it.deviceId} on localserver is success")
                listOfPushNotifications.add(it.pushNotification)
            }
            channel.close()
            connection.close()

        } catch (e: Exception) {
            localPushRequestModels.forEach {
                it.pushNotification.status = PushNotificationStatus.FAILURE
                it.pushNotification.failureReason = e.message
                listOfPushNotifications.add(it.pushNotification)
                logger.debug(
                    "pushlog: pushId = $pushId, deviceId = ${it.deviceId} on localserver is failure")
            }
            logger.error("error in sending push", e.message)
        } finally {
            pushNotificationRepository.saveAll(listOfPushNotifications)
        }
    }

    private fun savePushDataMapper(pushNotification: PushNotification, pushPayload: String) {
        pushNotification.pushPayloadJson = pushPayload
        pushNotificationRepository.save(pushNotification)
    }
}