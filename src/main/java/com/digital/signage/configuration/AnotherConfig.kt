package com.digital.signage.configuration

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule
import com.digital.signage.annotations.NullableServerLaunchConfigParam
import com.digital.signage.dto.AgeDwellReportResponseDto
import com.digital.signage.dto.AgeReportResponseDto
import com.digital.signage.dto.ServerLaunchConfig
import com.digital.signage.dto.TotalCountByAgeAndDwellTimeDTO
import com.digital.signage.exceptions.S3ServerKeyParseException
import com.digital.signage.exceptions.ServerLaunchConfigMissingException
import com.digital.signage.exceptions.ServerLaunchConfigPropertyMissingException
import com.digital.signage.serializers.AgeDwellReportResponseDtoSerializer
import com.digital.signage.serializers.AgeReportResponseDtoSerializer
import com.digital.signage.serializers.TotalCountByAgeAndDwellTimeDtoSerializer
import com.digital.signage.util.getHostFromUrl
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.io.FileNotFoundException
import java.io.IOException
import java.nio.file.Paths

@Configuration
open class AnotherConfig @Autowired constructor(
    private val objectMapper: ObjectMapper,
    @Value("\${server.launch.config.json.path}")
    private val serverLaunchConfigJsonPath: String,
    @Value("\${spring.profiles.active}")
    private val springActiveProfileInUse: String,
    @Value("\${is.pionpremises.server}")
    private val isOnPremisesServer: Boolean,
    @Value("\${push.rebbitMq.server.ip}")
    private val pushRabbitMqServerIp: String
) {

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(AnotherConfig::class.java)
    }

    @Bean
    @Throws(
        S3ServerKeyParseException::class,
        ServerLaunchConfigMissingException::class,
        ServerLaunchConfigPropertyMissingException::class,
        FileNotFoundException::class
    )
    open fun getServerLaunchConfig(
        objectMapper: ObjectMapper
    ): ServerLaunchConfig {
        val serverLaunchConfig: ServerLaunchConfig
        return try {
            val configJsonFile = Paths.get(serverLaunchConfigJsonPath).toFile()
            serverLaunchConfig = when {
                "prod" == springActiveProfileInUse -> {
                    javaClass.classLoader.getResourceAsStream(
                        "launch-config/server-launch-config-main-server.json"
                    ).use {
                        objectMapper.readValue(it, ServerLaunchConfig::class.java)
                    }
                }

                /*configJsonFile.exists() -> {
                    objectMapper.readValue(configJsonFile, ServerLaunchConfig::class.java)
                }*/

                "dev" == springActiveProfileInUse -> {
                    javaClass.classLoader.getResourceAsStream(
                        "config/server-launch-config.json"
                    ).use {
                        objectMapper.readValue(it, ServerLaunchConfig::class.java)
                    }
                    //throw ServerLaunchConfigMissingException()
                }
                else -> {
                    javaClass.classLoader.getResourceAsStream(
                        "config/server-launch-config.json"
                    ).use {
                        objectMapper.readValue(it, ServerLaunchConfig::class.java)
                    }
                }
            }
            setRabbitMqHostPropertyIfNotSet(serverLaunchConfig, pushRabbitMqServerIp)
            logger.debug("ServerLaunchConfig = {}", serverLaunchConfig)
            val fields = ServerLaunchConfig::class.java.declaredFields
            for (i in fields.indices) {
                val field = fields[i]
                try {
                    if (!field.isAnnotationPresent(NullableServerLaunchConfigParam::class.java)) {
                        val originalAccessibility = field.isAccessible
                        field.isAccessible = true
                        val value = field[serverLaunchConfig]
                            ?: throw ServerLaunchConfigPropertyMissingException(
                                fields[i].name + " is null in " + serverLaunchConfigJsonPath
                            )
                        field.isAccessible = originalAccessibility
                    }
                } catch (e: IllegalAccessException) {
                    logger.error("", e)
                }
            }
            objectMapper.registerModule(
                SimpleModule()
                    .addSerializer(
                        AgeReportResponseDto::class.java,
                        AgeReportResponseDtoSerializer(
                            serverLaunchConfig.limitFaAgeGroupBetween20to60!!,
                            serverLaunchConfig.saveFaDataForAgeBelow20!!,
                            AgeReportResponseDto::class.java
                        )
                    )
            )
            objectMapper.registerModule(
                SimpleModule()
                    .addSerializer(
                        AgeDwellReportResponseDto::class.java,
                        AgeDwellReportResponseDtoSerializer(
                            serverLaunchConfig.limitFaAgeGroupBetween20to60!!,
                            serverLaunchConfig.saveFaDataForAgeBelow20!!,
                            AgeDwellReportResponseDto::class.java
                        )
                    )
            )
            objectMapper.registerModule(
                SimpleModule()
                    .addSerializer(
                        TotalCountByAgeAndDwellTimeDTO::class.java,
                        TotalCountByAgeAndDwellTimeDtoSerializer(
                            serverLaunchConfig.limitFaAgeGroupBetween20to60!!,
                            serverLaunchConfig.saveFaDataForAgeBelow20!!,
                            TotalCountByAgeAndDwellTimeDTO::class.java
                        )
                    )
            )

            val allServerAndAngularHosts = mutableSetOf(
                getHostFromUrl(serverLaunchConfig.baseServerUrlForDevice),
                getHostFromUrl(serverLaunchConfig.serverBaseUrlForAngularDownloadLinks),
                getHostFromUrl(serverLaunchConfig.serverBaseUrlForDeviceDownloadLinks),
                getHostFromUrl(
                    serverLaunchConfig.angularBaseUrlsToBeSentInNotificationsAndEmails
                )
            ).filterNotNull().toSet()
            serverLaunchConfig.allServerAndAngularHosts = allServerAndAngularHosts

            serverLaunchConfig
        } catch (e: IOException) {
            throw ServerLaunchConfigMissingException()
        }
    }

    private fun setRabbitMqHostPropertyIfNotSet(
        serverLaunchConfig: ServerLaunchConfig,
        pushRabbitMqServerIp: String?
    ) {
        if (pushRabbitMqServerIp == null) return
        if (serverLaunchConfig.rabbitMqHost == null) {
            serverLaunchConfig.rabbitMqHost = pushRabbitMqServerIp
        }
    }
}