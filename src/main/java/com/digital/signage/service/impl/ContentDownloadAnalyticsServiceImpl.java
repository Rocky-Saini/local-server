package com.digital.signage.service.impl;

import com.digital.signage.dto.ContentDownloadAnalyticsAppendDTO;
import com.digital.signage.dto.ContentDownloadAnalyticsDTO;
import com.digital.signage.dto.ContentDownloadAnalyticsInProgressDTO;
import com.digital.signage.dto.VideoCodecErrorReqDto;
import com.digital.signage.dto.report.TpAppRequestDto;
import com.digital.signage.dto.ContentDownloadAnalyticsDTO;
import com.digital.signage.exceptions.NotRequiredForLocalServerException;
import com.digital.signage.localserver.apiservice.ContentAnalyticsApiService;
//import com.digital.signage.localserver.apiservice.RetrofitHelper;
import com.digital.signage.localserver.apiservice.RetrofitHelper;
import com.digital.signage.models.ContentPlaybackError;
import com.digital.signage.models.Response;
import com.digital.signage.service.ContentDownloadAnalyticsService;
import com.digital.signage.service.impl.BaseServiceImpl;
import com.digital.signage.util.ApplicationConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@Service
public class ContentDownloadAnalyticsServiceImpl extends BaseServiceImpl
        implements ContentDownloadAnalyticsService {
    private static final Logger logger = LoggerFactory.getLogger(com.digital.signage.service.impl.ContentDownloadAnalyticsServiceImpl.class);
    @Autowired
    private RetrofitHelper retrofitHelper;
    @Override
    public Response<?> addContentDownloadAnalyticsV1(List<ContentDownloadAnalyticsDTO> contentDownloadAnalyticsDTOList, HttpServletRequest httpServletRequest) {
        logger.debug("addContentDownloadAnalytics deviceToken: {}",
                httpServletRequest.getHeader(ApplicationConstants.Headers.AUTHORIZATION));
        try {
            retrofit2.Response<Response<String>> resp = retrofitHelper.newMainServerRetrofit()
                    .create(ContentAnalyticsApiService.class)
                    .addContentDownloadAnalyticsV1(
                            httpServletRequest.getHeader(ApplicationConstants.Headers.AUTHORIZATION),
                            contentDownloadAnalyticsDTOList
                    )
                    .execute();
            return RetrofitHelper.processRetrofitResponse2DsResponse(resp, objectMapper);
        } catch (IOException e) {
            logger.error("", e);
            return Response.createInternalServerErrorResponseFromException(e);
        }
//        throw new NotRequiredForLocalServerException();
    }

    @Override
    public Response<?> addTpaContentPlayback(List<TpAppRequestDto> contentPlaybackTpAppRequestDtoList, HttpServletRequest httpServletRequest) {
        throw new NotRequiredForLocalServerException();
    }

    @Override
    public Response<?> addContentPlaybackError(List<ContentPlaybackError> contentPlaybackErrorList, HttpServletRequest httpServletRequest) {
        throw new NotRequiredForLocalServerException();
    }

    @Override
    public Response<?> addContentDownloadAnalyticsV2(List<ContentDownloadAnalyticsDTO> contentDownloadAnalyticsDTOList, HttpServletRequest httpServletRequest) {
        logger.debug("addContentDownloadAnalytics deviceToken: {}",
                httpServletRequest.getHeader(ApplicationConstants.Headers.AUTHORIZATION));
        try {
            retrofit2.Response<Response<String>> resp = retrofitHelper.newMainServerRetrofit()
                    .create(ContentAnalyticsApiService.class)
                    .addContentDownloadAnalyticsV2(
                            httpServletRequest.getHeader(ApplicationConstants.Headers.AUTHORIZATION),
                            contentDownloadAnalyticsDTOList
                    )
                    .execute();
            return RetrofitHelper.processRetrofitResponse2DsResponse(resp, objectMapper);
        } catch (IOException e) {
            logger.error("", e);
            return Response.createInternalServerErrorResponseFromException(e);
        }
//        throw new NotRequiredForLocalServerException();
    }

    @Override
    public Response<?> updateContentDownloadAnalyticsAppend(ContentDownloadAnalyticsAppendDTO contentDownloadAnalyticsAppendDTO, HttpServletRequest httpServletRequest) {
        logger.debug("addContentDownloadAnalytics deviceToken : {}",
                httpServletRequest.getHeader(ApplicationConstants.Headers.AUTHORIZATION));
        try {
            retrofit2.Response<Response<String>> resp = retrofitHelper.newMainServerRetrofit()
                    .create(ContentAnalyticsApiService.class)
                    .appendContentDownload(
                            httpServletRequest.getHeader(ApplicationConstants.Headers.AUTHORIZATION),
                            contentDownloadAnalyticsAppendDTO)
                    .execute();
            return RetrofitHelper.processRetrofitResponse2DsResponse(resp, objectMapper);
        } catch (IOException e) {
            logger.error("", e);
            return Response.createInternalServerErrorResponseFromException(e);
        }
//        throw new NotRequiredForLocalServerException();
    }

    @Override
    public Response<?> updateContentDownloadAnalyticsProgress(ContentDownloadAnalyticsInProgressDTO contentDownloadAnalyticsInProgressDTO, HttpServletRequest httpServletRequest) {
        logger.debug("updateContentDownloadAnalyticsProgress deviceToken : {}",
                httpServletRequest.getHeader(ApplicationConstants.Headers.AUTHORIZATION));
        try {
            retrofit2.Response<Response<String>> resp = retrofitHelper.newMainServerRetrofit()
                    .create(ContentAnalyticsApiService.class)
                    .updateContentDownloadAnalytics(
                            httpServletRequest.getHeader(ApplicationConstants.Headers.AUTHORIZATION),
                            contentDownloadAnalyticsInProgressDTO
                    )
                    .execute();
            return RetrofitHelper.processRetrofitResponse2DsResponse(resp, objectMapper);
        } catch (IOException e) {
            logger.error("", e);
            return Response.createInternalServerErrorResponseFromException(e);
        }
//        throw new NotRequiredForLocalServerException();
    }

    @Override
    public Response<?> putDateInContentPlaybackTable() {
        throw new NotRequiredForLocalServerException();
    }

    @Override
    public Response<?> videoCodecErrors(List<VideoCodecErrorReqDto> videoCodecErrorReqDtos) {
        throw new NotRequiredForLocalServerException();
    }
}
