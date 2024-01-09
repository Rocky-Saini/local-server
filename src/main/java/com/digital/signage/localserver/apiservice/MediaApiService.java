package com.digital.signage.localserver.apiservice;


import com.digital.signage.dto.LocalServerContentDTO;
import com.digital.signage.localserver.Response.ApiResponsee;
import com.digital.signage.models.Response;
import com.digital.signage.util.ApplicationConstants;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.LinkedHashMap;


public interface MediaApiService {
    @GET("/content/{contentId}")
    Call<Response<LocalServerContentDTO>> getContent(@Path("contentId") Long contentId,
                                                     @Header(ApplicationConstants.Headers.AUTHORIZATION) String bearerToken);

    @GET("/content/{contentId}")
    Call<String> getContentAsString(@Path("contentId") Long contentId,
                                    @Header(ApplicationConstants.Headers.AUTHORIZATION) String bearerToken);

//    @GET(UrlPaths.PATH_CONTENT_LATEST_VERSION
//            + "{encryptedCustomerId}/{mediaDetailId}")
    @GET("/content-management/cms/{slugId}/v1/media/version")
    Call<ApiResponsee<LinkedHashMap<String, Object>>> getContentVersion(
            @Path("slugId") String tenantId,
            @Query("mediaDetailId") Long mediaDetailId,
            @Header(ApplicationConstants.Headers.AUTHORIZATION) String bearerToken);
}
