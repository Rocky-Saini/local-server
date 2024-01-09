package com.digital.signage.dto

import com.digital.signage.annotations.NullableServerLaunchConfigParam
import com.digital.signage.util.PushModeForAngular

/**
 * @author -Ravi Kumar created on 2/1/2023 1:02 AM
 * @project - Digital Sign-edge
 */
data class ServerLaunchConfig(
    var isS3Enabled: Boolean? = false,
    var s3BucketName: String? = "panasonic-digital-signage",
    var s3BucketNameForJenkins: String? = "ds-builds-bucket",
    var s3Region: String? = "ap-south-1",
    var isSesEnabled: Boolean? = false,
    @NullableServerLaunchConfigParam
    var sesRegion: String? = "us-east-1",
    @NullableServerLaunchConfigParam
    var enableTlsOnEmail: Boolean? = true,
    var isAdvertisementEnabled: Boolean? = false,
    var useRabbitMqOnlyForPushOnOnPremiseEnv: Boolean? = true,
    var baseServerUrlForDevice: String? = null,
    @NullableServerLaunchConfigParam
    var mailSmtpSslTrust: String? = null,
    @NullableServerLaunchConfigParam
    var ffProbePath: String? = null,
    @NullableServerLaunchConfigParam
    var ffMpegPath: String? = null,
    var serverBaseUrlForAngularDownloadLinks: String? = null, // for setting download links for angular user
    var serverBaseUrlForDeviceDownloadLinks: String? = null, // for setting download links for devices
    var angularBaseUrlsToBeSentInNotificationsAndEmails: String? = null,  // for setting links to Angular site in emails and notifications
    var useUntrustedOkHttpClient: Boolean = false,
    var enableDemographic: Boolean = false,
    var enableDesktopRegistration: Boolean = false,
    @NullableServerLaunchConfigParam
    var limitFaAgeGroupBetween20to60: Boolean? = false,
    var sendAndroidPushViaRabbitMqOnly: Boolean = false,
    @NullableServerLaunchConfigParam
    var rabbitMqHostForDevices: String? = null,
    @NullableServerLaunchConfigParam
    var rabbitMqHost: String? = null,
    var saveFaDataForAgeBelow20: Boolean? = false,
    @NullableServerLaunchConfigParam
    var enableLinuxDevice: Boolean? = false,
    var pushModeForAngular: PushModeForAngular? = PushModeForAngular.FIREBASE,
    @NullableServerLaunchConfigParam
    var allServerAndAngularHosts: Set<String>? = null
)
