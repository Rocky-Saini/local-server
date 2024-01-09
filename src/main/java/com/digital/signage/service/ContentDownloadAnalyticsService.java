package com.digital.signage.service;

import com.digital.signage.dto.ContentDownloadAnalyticsAppendDTO;
import com.digital.signage.dto.ContentDownloadAnalyticsDTO;
import com.digital.signage.dto.ContentDownloadAnalyticsInProgressDTO;

import com.digital.signage.dto.VideoCodecErrorReqDto;
import com.digital.signage.dto.report.TpAppRequestDto;
import com.digital.signage.models.ContentPlaybackError;
import com.digital.signage.models.Response;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public interface ContentDownloadAnalyticsService extends BaseService {

    Response<?> addContentDownloadAnalyticsV1(
            List<ContentDownloadAnalyticsDTO> contentDownloadAnalyticsDTOList,
            HttpServletRequest httpServletRequest
    );

    Response<?> addTpaContentPlayback(
            List<TpAppRequestDto> contentPlaybackTpAppRequestDtoList,
            HttpServletRequest httpServletRequest
    );


    Response<?> addContentPlaybackError(
            List<ContentPlaybackError> contentPlaybackErrorList,
            HttpServletRequest httpServletRequest
    );

    Response<?> addContentDownloadAnalyticsV2(
            List<ContentDownloadAnalyticsDTO> contentDownloadAnalyticsDTOList,
            HttpServletRequest httpServletRequest
    );

    Response<?> updateContentDownloadAnalyticsAppend(
            ContentDownloadAnalyticsAppendDTO contentDownloadAnalyticsAppendDTO,
            HttpServletRequest httpServletRequest
    );

    Response<?> updateContentDownloadAnalyticsProgress(
            ContentDownloadAnalyticsInProgressDTO contentDownloadAnalyticsInProgressDTO,
            HttpServletRequest httpServletRequest
    );


    Response<?> putDateInContentPlaybackTable();

    Response<?> videoCodecErrors(List<VideoCodecErrorReqDto> videoCodecErrorReqDtos);
}
