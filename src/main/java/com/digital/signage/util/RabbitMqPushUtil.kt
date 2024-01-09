package com.digital.signage.util

import com.rabbitmq.client.Connection
import com.rabbitmq.client.ConnectionFactory
import java.util.concurrent.TimeUnit

val RABBIT_MQ_CONNECTION_TIMEOUT_IN_MILLIS: Int = TimeUnit.MINUTES.toMillis(1).toInt()
const val ON_PREMISES_RABBIT_MQ_EXCHANGE_NAME = "on_premises_notify"

fun newConnection(
    username: String,
    password: String,
    rabbitMQServerIp: String,
    rabbitMQConnectionTimeoutInMillis: Int = RABBIT_MQ_CONNECTION_TIMEOUT_IN_MILLIS
): Connection = ConnectionFactory().apply {
    this.username = username
    this.password = password
    this.host = rabbitMQServerIp
    this.connectionTimeout = rabbitMQConnectionTimeoutInMillis
}.newConnection()