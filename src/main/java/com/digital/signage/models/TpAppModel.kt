package com.digital.signage.models;


import com.digital.signage.util.TpaOs
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty

import javax.persistence.*

@Entity(name = "tpAppModel")
data class TpAppModel(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "tpAppId")
    @JsonProperty(TPAPP_ID_JSON_KEY)
    var tpAppId: Long? = null,

    @Column(name = "customerId")
    var customerId: Long? = null,

    @Column(name = "tpAppName")
    @JsonProperty(TPAPP_NAME_JSON_KEY)
    var tpAppName: String? = null,

    @Column(name = "tpAppDescription")
    @JsonProperty(TPAPP_DISCRIPTION_JSON_KEY)
    var tpAppDescription: String? = null,

    @Column(name = "appOs")
    @JsonProperty(TPAPP_OS_JSON_KEY)
    var appOs: TpaOs? = null,

    @Column(name = "deepLinkSchema")
    @JsonProperty(TPAPP_SCHEMA_JSON_KEY)
    var deepLinkSchema: String? = null,

    @Column(name = "windowsDirectoryToDownloadTPABuilds")
    @JsonProperty(TPAPP_WINDOWS_DIR_JSON_KEY)
    var windowsDirectoryToDownloadTPABuilds: String? = null,

    @Column(name = "androidAppApplicationId")
    @JsonProperty(TPAPP_ANDROID_APP_ID_JSON_KEY)
    var androidAppApplicationId: String? = null,

    @Column(name = "downloadUrl")
    @JsonIgnore
    var downloadUrl: String? = null
) {
    constructor() : this(
        tpAppId = null,
        customerId = null,
        tpAppName = null,
        tpAppDescription = null,
        appOs = null,
        deepLinkSchema = null,
        windowsDirectoryToDownloadTPABuilds = null,
        androidAppApplicationId = null,
        downloadUrl = null
    )

    companion object {
        const val TPAPP_ID_JSON_KEY = "tpappId"
        const val TPAPP_NAME_JSON_KEY = "tpappName"
        const val TPAPP_UPDATE_TYPE_JSON_KEY = "updateType"
        const val TPAPP_DISCRIPTION_JSON_KEY = "tpappDescription"
        const val TPAPP_OS_JSON_KEY = "appOs"
        const val TPAPP_SCHEMA_JSON_KEY = "deeplinkSchema"
        const val TPAPP_WINDOWS_DIR_JSON_KEY = "windowsDirectoryToDownloadTPABuilds"
        const val TPAPP_ANDROID_APP_ID_JSON_KEY = "androidAppApplicationId"
    }
}
