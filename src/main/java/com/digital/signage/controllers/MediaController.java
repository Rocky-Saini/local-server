package com.digital.signage.controllers;



import com.digital.signage.constants.MediaConstant;
import com.digital.signage.dto.MediaDetailResponse;
import com.digital.signage.dto.MediaVersionResponse;
import com.digital.signage.exceptions.InvalidJwtException;
import com.digital.signage.handler.ApiResponse;
import com.digital.signage.jwt.JwtCompatForSignedUrls;
import com.digital.signage.service.BaseService;
import com.digital.signage.service.MediaService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "/api/media")
public class MediaController extends BaseController {
    @Autowired
    private MediaService mediaService;



    @GetMapping("/v1/media/{mediaDetailId}")
    public ApiResponse<MediaDetailResponse> mediaDetail(@PathVariable("mediaDetailId") Long mediaDetailId,
                                                        @RequestParam(value = "mediaVersion", required = false) Integer mediaVersion) {
        return new ApiResponse.ApiResponseBuilder<MediaDetailResponse>()
                .data(MediaDetailResponse.builder()
                        .mediaDetails(List.of(mediaService.mediaDetail(mediaDetailId, Objects.isNull(mediaVersion) ? 0 : mediaVersion))).build())
                .status(HttpStatus.OK)
                .message(MediaConstant.SUCCESS)
                .occurredOn(LocalDateTime.now()).build();
    }



    @GetMapping(value = MediaConstant.GET_MEDIA_LATEST_VERSION)
    public ApiResponse<MediaVersionResponse> mediaLatestVersion(
            @RequestParam("mediaDetailId") Long mediaDetailId,
            @RequestParam(value = "encryptedCustomerId", required = false) String encryptedCustomerId) throws InvalidJwtException {
        Long customerId = 1L;//TODO get from tenant context
        if(StringUtils.isNotBlank(encryptedCustomerId)) {
            JwtCompatForSignedUrls.CustomerIdMediaDetailIdModel m =
                    JwtCompatForSignedUrls.decryptCompatJwtForSignedUrl(encryptedCustomerId);
            customerId = m.customerId;//TODO get customer id from jwt
        }
        return new ApiResponse.ApiResponseBuilder<MediaVersionResponse>()
                .data(MediaVersionResponse.builder().mediaVersionDTO(mediaService.mediaLatestVersion(mediaDetailId/*, customerId*/)).build())
                .status(HttpStatus.OK)
                .occurredOn(LocalDateTime.now()).build();
    }



    @GetMapping(value = MediaConstant.GET_MEDIA_FILES)
    public void mediaFile(
            @RequestParam(value = "encryptedCustomerId", required = false) String encryptedCustomerId,
            @RequestParam("mediaDetailId") Long mediaDetailId,
            @RequestParam(value = "download", required = false) Boolean download, HttpServletRequest request, HttpServletResponse response) throws IOException, InvalidJwtException {
        mediaService.mediaFile(mediaDetailId, encryptedCustomerId, download, request, response);
    }



    @Override
    protected BaseService getBaseService() {
        return mediaService;
    }
}
