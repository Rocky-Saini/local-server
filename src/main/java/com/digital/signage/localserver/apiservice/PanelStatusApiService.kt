package com.digital.signage.localserver.apiservice

import com.digital.signage.dto.DeletedDataDTO
import com.digital.signage.dto.PanelsStatusRequestDTO
import com.digital.signage.models.Response
import com.digital.signage.util.ApplicationConstants.Headers.AUTHORIZATION
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface PanelStatusApiService {

    @POST("/panel/deleted-data/all/{panelId}")
    fun panelStatusDeletedDataAll(
        @Header(AUTHORIZATION) bearToken: String,
        @Path("panelId") panelId: Long,
        @Body panelStatusLists: List<DeletedDataDTO>?
    ): Call<Response<Any?>>

    @POST("/panel/v2/status")
    fun panelStatusV2(
        @Header(AUTHORIZATION) bearToken: String,
        @Body panelStatusReqDTO: PanelsStatusRequestDTO?
    ): Call<Response<Any?>>

}