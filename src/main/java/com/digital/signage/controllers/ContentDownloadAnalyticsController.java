package com.digital.signage.controllers;

import com.digital.signage.constants.UrlPaths;
import com.digital.signage.dto.ContentDownloadAnalyticsAppendDTO;
import com.digital.signage.dto.ContentDownloadAnalyticsDTO;

import com.digital.signage.dto.ContentDownloadAnalyticsInProgressDTO;
import com.digital.signage.dto.VideoCodecErrorReqDto;
import com.digital.signage.dto.report.TpAppRequestDto;
import com.digital.signage.models.ContentPlaybackError;
import com.digital.signage.models.Response;
import com.digital.signage.service.BaseService;
import com.digital.signage.service.ContentDownloadAnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@Validated
@ControllerAdvice
@RestController
@RequestMapping(UrlPaths.PATH_ANALYTICS)
public class ContentDownloadAnalyticsController extends BaseController {

    @Autowired
    private ContentDownloadAnalyticsService contentDownloadAnalyticsService;


    @PreAuthorize("hasAuthority('ALL_DEVICES')")
    @PostMapping(value = "/content-download")
    public Response<?> addContentDownloadAnalyticsV1(
            @RequestBody @Valid List<ContentDownloadAnalyticsDTO> contentDownloadAnalyticsList,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        return updateHttpStatusCode(
                contentDownloadAnalyticsService.addContentDownloadAnalyticsV1(
                        contentDownloadAnalyticsList,
                        request
                ),
                response
        );
    }

    @PreAuthorize("hasAuthority('ALL_DEVICES')")
    @PostMapping(value = "/content-download/v2")
    public Response<?> addContentDownloadAnalyticsV2(
            @RequestBody @Valid List<ContentDownloadAnalyticsDTO> contentDownloadAnalyticsList,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        return updateHttpStatusCode(
                contentDownloadAnalyticsService.addContentDownloadAnalyticsV2(
                        contentDownloadAnalyticsList,
                        request
                ),
                response
        );
    }

    @PreAuthorize("hasAuthority('ALL_DEVICES')")
    @PutMapping(value = "/content-download/append")
    public Response<?> updateAnalytics(
            @RequestBody @Valid ContentDownloadAnalyticsAppendDTO contentDownloadAnalytics,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        return updateHttpStatusCode(
                contentDownloadAnalyticsService.updateContentDownloadAnalyticsAppend(
                        contentDownloadAnalytics,
                        request
                ),
                response
        );
    }

    @PreAuthorize("hasAuthority('ALL_DEVICES')")
    @PutMapping(value = "/content-download/progress")
    public Response<?> addAnalyticsProgress(
            @Valid @RequestBody ContentDownloadAnalyticsInProgressDTO contentDownloadAnalytics,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        return updateHttpStatusCode(
                contentDownloadAnalyticsService.updateContentDownloadAnalyticsProgress(
                        contentDownloadAnalytics,
                        request
                ),
                response
        );
    }


    @PreAuthorize("hasAuthority('ALL_DEVICES')")
    @PostMapping(value = "/content-playback/error")
    public Response<?> addContentPlaybackError(
            @Valid @RequestBody List<ContentPlaybackError> contentPlaybackErrorList,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        return updateHttpStatusCode(
                contentDownloadAnalyticsService.addContentPlaybackError(
                        contentPlaybackErrorList,
                        request
                ),
                response
        );
    }



    /*@PreAuthorize("hasAuthority('ALL_DEVICES')")
    @PostMapping(value = "/tpa-playback")
    public Response<?> addTpaContentPlayback(
            @Valid @RequestBody List<TpAppRequestDto> contentPlaybackTpAppRequestDtoList,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        return updateHttpStatusCode(
                contentDownloadAnalyticsService.addTpaContentPlayback(
                        contentPlaybackTpAppRequestDtoList,
                        request
                ),
                response
        );
    }*/

    @PostMapping(value = "/update/content-playback-date")
    public Response<?> putDateInContentPlaybackTable() {
        return contentDownloadAnalyticsService.putDateInContentPlaybackTable();
    }

    //analytics/checksum-encryption-failure
  /*  @PreAuthorize("hasAuthority('ALL_DEVICES')")
    @PostMapping(value = "/checksum-encryption-failure")
    public Response<?> addAnalyticsCheckSumEncryptionFailure(
            @Valid @RequestBody List<AnalyticsChecksumEncryptionFailureDTO> dtoList,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        return updateHttpStatusCode(
                contentDownloadAnalyticsService.
                        addAnalyticsCheckSumEncryptionFailureData(
                                dtoList,
                                request
                        ),
                response
        );
    }*/

    @PreAuthorize("hasAuthority('ALL_DEVICES')")
    @PostMapping(value = "/video-codec-errors")
    public Response<?> videoCodecErrors(
            @Valid @RequestBody List<VideoCodecErrorReqDto> videoCodecErrorReqDto,
            HttpServletResponse response
    ) {
        return updateHttpStatusCode(
                contentDownloadAnalyticsService.videoCodecErrors(videoCodecErrorReqDto),
                response
        );
    }


    @PreAuthorize("hasAuthority('ALL_DEVICES')")
    @PostMapping(value = "/tpa-playback")
    public Response<?> addTpaContentPlayback(
            @Valid @RequestBody List<TpAppRequestDto> contentPlaybackTpAppRequestDtoList,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        return updateHttpStatusCode(
                contentDownloadAnalyticsService.addTpaContentPlayback(
                        contentPlaybackTpAppRequestDtoList,
                        request
                ),
                response
        );
    }




    @Override
    protected BaseService getBaseService() {
        return contentDownloadAnalyticsService;
    }
}
