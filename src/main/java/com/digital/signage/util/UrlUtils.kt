package com.digital.signage.util

import com.digital.signage.constants.UrlPaths.PATH_BUILD_FILE
import com.digital.signage.constants.UrlPaths.PREFIX
import com.digital.signage.constants.UrlPaths.QueryParams.*
import com.digital.signage.dto.ServerLaunchConfig
import org.springframework.lang.NonNull
import java.net.MalformedURLException
import java.net.URL

fun appendSlashToEndOfUrlIfRequired(@NonNull url: String) = if (url.endsWith("/")) url else "$url/"

fun removeSlashFromEndOfUrlIfRequired(@NonNull url: String) = if (url.endsWith("/")) url.substring(
        0, url.length - 1) else url

fun removeBeginningSlashIfPresent(@NonNull url: String) = if (url.startsWith("/")) url.replaceFirst(
        "/".toRegex(), "") else url

fun addHashAtEndOfUrlIfNotPresent(@NonNull url: String): String =
        if (url.endsWith("/#")) {
            url
        } else {
            "$url/#"
        }

fun getServerUrlWithoutSlash(
        isDevice: Boolean,
        isOnPremiseServer: Boolean,
        devicePublicBaseUrl: String,
        serverPublicBaseUrl: String
): String {
    val tempString = if (isDevice && isOnPremiseServer) devicePublicBaseUrl else serverPublicBaseUrl
    return if (tempString.endsWith("/"))
        tempString.substring(0, tempString.length - 1)
    else
        tempString
}

fun generateBuildDownloadUrl(
        buildOs: BuildOs,
        encryptedCode: String,
        version: String,
        onPremiseId: Long?,
        serverLaunchConfig: ServerLaunchConfig,
        isDevice: Boolean,
        isOnPremiseServer: Boolean
): String {
    val path = "$PREFIX$PATH_BUILD_FILE?$ENCRYPTED_CODE=$encryptedCode&$DEVICE_OS=${buildOs.name}&$VERSION=$version".plus(
            if (onPremiseId == null) {
                ""
            } else {
                "&$PREMISE_ID=$onPremiseId"
            }
    )
    return if (isDevice && isOnPremiseServer) {
        "${removeSlashFromEndOfUrlIfRequired(
                serverLaunchConfig.serverBaseUrlForDeviceDownloadLinks!!)}$path"
    } else {
        "${removeSlashFromEndOfUrlIfRequired(
                serverLaunchConfig.serverBaseUrlForAngularDownloadLinks!!)}$path"
    }
}

@Throws(MalformedURLException::class)
fun getHostFromUrl(urlString: String?): String? {
    return if (urlString != null) {
        URL(urlString).host
    } else {
        null
    }
}