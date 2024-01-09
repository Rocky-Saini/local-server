package com.digital.signage.localserver.push

import com.rabbitmq.client.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.io.IOException
import java.nio.charset.StandardCharsets
import java.util.concurrent.TimeoutException


@Service
class RabbitMqReceivePushOnLocalServer {
    @Value("\${main.server.rebbitMq.ip}")
    private lateinit var rabbitMqServerIp: String

    @Value("\${main.server.rebbitMq.userName}")
    private lateinit var rabbitMqUserName: String

    @Value("\${main.server.rebbitMq.password}")
    private lateinit var rabbitMqPassword: String

    private var connection: Connection? = null

    @Autowired
    private lateinit var rabbitMqSendLocalMessage: RabbitMqSendLocalMessage

    @Throws(IOException::class, TimeoutException::class)
    private fun connectToRabbitMqMainServer(): Connection? {
        logger.debug("connectToRabbitMqMainServer")
        val factory = ConnectionFactory().apply {
            username = rabbitMqUserName
            password = rabbitMqPassword
            host = rabbitMqServerIp // RabbitMq main server Ip
        }
        logger.debug("creating connection with RabbitMq Main server: $rabbitMqServerIp")
        return factory.newConnection()
    }

    @Throws(IOException::class, TimeoutException::class)
    fun justConnectToRabbitMqMainServerAndClose() {
        logger.debug("justConnectToRabbitMqMainServerAndCheck")
        val connection: Connection? = connectToRabbitMqMainServer()
        connection?.close()
    }

    @Synchronized
    fun receivePush(customerId: Long): Boolean {
        val exchangeName = "push_$customerId"
        logger.debug(
            "RabbitMq: creating exchange connection with rabbitMq , exchange Name: $exchangeName"
        )
        logger.debug("current Thread $this")
        if (connection == null) {
            try {
                connection = connectToRabbitMqMainServer()
            } catch (e: IOException) {
                e.printStackTrace()
                logger.error("Could not connect to Rabbit Mq")
                // could not connect so return
                connection = null
                return false
            } catch (e: TimeoutException) {
                e.printStackTrace()
                logger.error("Could not connect to Rabbit Mq")
                connection = null
                return false
            }
            logger.debug("connection created $connection")
            return try {
                val channel = connection!!.createChannel()
                logger.debug("RabbitMq: channel created")
                channel.exchangeDeclare(exchangeName, "fanout")
                logger.debug("RabbitMq: exchange $exchangeName declared")
                val queueName = channel.queueDeclare().queue
                logger.debug("RabbitMq: queue having name $queueName declared")
                channel.queueBind(queueName, exchangeName, "")
                logger.debug("RabbitMq: queue $queueName bound to exchange $exchangeName")
                val consumer = object : DefaultConsumer(channel) {
                    @Throws(IOException::class)
                    override fun handleDelivery(
                        consumerTag: String,
                        envelope: Envelope,
                        properties: AMQP.BasicProperties,
                        body: ByteArray
                    ) {
                        val message = String(body, StandardCharsets.UTF_8)
                        logger.debug("Push Received $message")
                        //send to local devices
                        rabbitMqSendLocalMessage.sendLocalPush(message)
                    }
                }
                channel.basicConsume(queueName, true, consumer)
                logger.debug("RabbitMq: consumer (listener), $consumer, added")
                true
            } catch (e: IOException) {
                e.printStackTrace()
                try {
                    connection!!.close()
                } catch (e1: IOException) {
                    e1.printStackTrace()
                } finally {
                    connection = null
                }
                logger.error("RabbitMq: exception occurred, ", e)
                false
            }
        }
        return true
    }

    fun isConnectedToRabbitMqMainServer(): Boolean {
        return if (connection == null) false else connection!!.isOpen
    }

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(
            RabbitMqReceivePushOnLocalServer::class.java
        )
    }
}