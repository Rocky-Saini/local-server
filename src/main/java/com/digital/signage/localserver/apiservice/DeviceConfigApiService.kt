package com.digital.signage.localserver.apiservice

import com.digital.signage.constants.UrlPaths.*
import com.digital.signage.dto.DataCollectionConfigRequestDTO
import com.digital.signage.models.Response
import com.digital.signage.util.ApplicationConstants.Headers.AUTHORIZATION
import retrofit2.Call
import retrofit2.http.*


interface DeviceConfigApiService {

    @GET("deviceConfig")
    fun getDeviceConfig(
        @Header(AUTHORIZATION) bearToken: String,
        @Query("deviceIds") deviceIds: String?
    ): Call<Response<Any?>>

    @POST(PATH_DATA_COLLECTION_CONFIG_V2)
    fun saveDataCollectionConfigV2(
        @Header(AUTHORIZATION) bearToken: String,
        @Body devicesConfigDto: DataCollectionConfigRequestDTO?
    ): Call<Response<Any?>>

    @POST(PATH_DATA_COLLECTION_CONFIG_ALL_V2)
    fun saveDataCollectionConfigV2All(
        @Header(AUTHORIZATION) bearToken: String,
        @Body dataCollectionConfigRequestDTOS: List<DataCollectionConfigRequestDTO>?
    ): Call<Response<Any?>>

    @POST(DATA_COLLECTION_CONFIG)
    fun saveDataCollectionConfigV1(
        @Header(AUTHORIZATION) bearToken: String,
        @Body devicesConfigDto: DataCollectionConfigRequestDTO?
    ): Call<Response<Any?>>

    @POST(DATA_COLLECTION_CONFIG_ALL)
    fun saveDataCollectionConfigV1All(
        @Header(AUTHORIZATION) bearToken: String,
        @Body dataCollectionConfigRequestDTOS: List<DataCollectionConfigRequestDTO>?
    ): Call<Response<Any?>>
}