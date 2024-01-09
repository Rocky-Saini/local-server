package com.digital.signage.localserver.apiservice;


import com.digital.signage.dto.ContentDownloadAnalyticsAppendDTO;
import com.digital.signage.dto.ContentDownloadAnalyticsDTO;
import com.digital.signage.dto.ContentDownloadAnalyticsInProgressDTO;
import com.digital.signage.dto.report.TpAppRequestDto;
import com.digital.signage.models.Response;
import com.digital.signage.util.ApplicationConstants;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;

import java.util.List;

public interface ContentAnalyticsApiService {

    @POST("analytics/content-download")
    Call<Response<String>> addContentDownloadAnalyticsV1(
            @Header(ApplicationConstants.Headers.AUTHORIZATION) String bearerToken,
            @Body List<ContentDownloadAnalyticsDTO> contentDownloadAnalyticsList
    );

    @POST("analytics/content-download/v2")
    Call<Response<String>> addContentDownloadAnalyticsV2(
            @Header(ApplicationConstants.Headers.AUTHORIZATION) String bearerToken,
            @Body List<ContentDownloadAnalyticsDTO> contentDownloadAnalyticsList
    );

    @PUT("analytics/content-download/progress")
    Call<Response<String>> updateContentDownloadAnalytics(
            @Header(ApplicationConstants.Headers.AUTHORIZATION) String bearerToken,
            @Body ContentDownloadAnalyticsInProgressDTO inputContentDownloadAnalytics
    );

    @POST("analytics/content-download/append")
    Call<Response<String>> appendContentDownload(
            @Header(ApplicationConstants.Headers.AUTHORIZATION) String bearerToken,
            @Body ContentDownloadAnalyticsAppendDTO contentDownloadAnalyticsList
    );

//    @POST("analytics/content-playback")
//    Call<Response<String>> addContentPlayback(
//            @Header(ApplicationConstants.Headers.AUTHORIZATION) String bearerToken,
//            @Body List<ContentPlaybackDto> contentPlaybackRequestDtoList
//    );

//    @POST("analytics/content-playback/v2")
//    Call<Response<String>> addContentPlayback2(
//            @Header(ApplicationConstants.Headers.AUTHORIZATION) String bearerToken,
//            @Body List<ContentPlaybackDtoV2> contentPlaybackRequestDtoList
//    );

//    @POST("analytics/content-playback/error")
//    Call<Response<String>> addContentPlaybackError(
//            @Header(ApplicationConstants.Headers.AUTHORIZATION) String bearerToken,
//            @Body List<ContentPlaybackError> contentPlaybackErrors
//    );

    @POST("/analytics/tpa-playback")
    Call<Response<String>> addTPAPlayback(
            @Header(ApplicationConstants.Headers.AUTHORIZATION) String bearerToken,
            @Body List<TpAppRequestDto> contentPlaybackTpAppRequestDtoList
    );

//    @POST("/analytics/checksum-encryption-failure")
//    Call<Response<String>> addCheckSumEncryptionFailureData(
//            @Header(ApplicationConstants.Headers.AUTHORIZATION) String bearerToken,
//            @Body List<AnalyticsCechksumEncryptionFailureDTO> analyticsChecksumEncryptionFailureDTOList
//    );
}
