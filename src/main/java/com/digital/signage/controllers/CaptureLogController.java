package com.digital.signage.controllers;

import com.digital.signage.constants.UrlPaths;
import com.digital.signage.dto.CurrentSnapshotRequestDTO;
import com.digital.signage.repository.DeviceSnapshotRepository;
import com.digital.signage.service.BaseService;
import com.digital.signage.service.CaptureLogService;
import com.fasterxml.jackson.core.JsonParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import com.digital.signage.models.Response;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/api")
public class CaptureLogController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(CaptureLogController.class);
    @Autowired
    private CaptureLogService captureLogService;
    @Autowired
    private DeviceSnapshotRepository deviceSnapshotRepository;
    @PreAuthorize("hasAuthority('ALL_DEVICES')")
    @RequestMapping(value = "/device-logs", method = RequestMethod.POST)
    public Response<?> singleFileUpload(
            @RequestParam(value = "file", required = false) @Nullable MultipartFile file,
            @RequestParam(value = "values", required = false) @Nullable String str,
            HttpServletRequest request,
            HttpServletResponse httpServletResponse) {
        logger.info("Calling capture log service");
        Response<?> response = captureLogService.captureLogs(file, str, request);
        updateHttpStatusCode(response, httpServletResponse);
        return response;
    }

//    @PreAuthorize("hasAuthority('ALL_USERS')")
    @RequestMapping(value = "/device-logs", method = RequestMethod.GET)
    public Response<?> downloadLogUrl(
            @RequestParam(value = "deviceId") Long deviceId,
            @RequestParam(value = "logFileStartTime") Long logFileStartTime,
            @RequestParam(value = "logFileEndTime") Long logFileEndTime,
            HttpServletRequest request,
            HttpServletResponse httpServletResponse
    ) throws ParseException, IOException {
        logger.info("Calling downloadLogByURL service ");
        Response<?> response =
                captureLogService.downloadLogByURL(deviceId, logFileStartTime, logFileEndTime, request,
                        httpServletResponse);
        updateHttpStatusCode(response, httpServletResponse);
        return response;
    }

    /*@PreAuthorize("hasAuthority('ALL_USERS')")*/
    @RequestMapping(value = "/device-snapshots", method = RequestMethod.GET)
    public Response<?> getSnapShots(
            @RequestParam(value = "deviceId", required = true) Long deviceId,
            @RequestParam(value = "snapshotStartTime", required = false) Long snapshotStartTime,
            @RequestParam(value = "snapshotEndTime", required = false) Long snapshotEndTime,
            HttpServletResponse httpServletResponse
    ) throws JsonParseException, ParseException, IOException {
        return updateHttpStatusCode(
                captureLogService.getSnapShots(
                        deviceId,
                        snapshotStartTime,
                        snapshotEndTime
                ),
                httpServletResponse
        );
    }

    @PreAuthorize("hasAuthority('ALL_DEVICES')")
    @RequestMapping(value = "/device-snapshots", method = RequestMethod.POST)
    public Response<?> uploadSnapShot(
            @RequestPart(value = "file", required = true) MultipartFile file,
            @RequestPart(value = "values", required = true) String str,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse
    ) throws IOException {
        logger.info("Calling capture log service");
        Response<?> response = captureLogService.uploadSnapShot(file, str, httpServletRequest);
        updateHttpStatusCode(response, httpServletResponse);
        return response;
    }
    //2/2/
    @RequestMapping(value = UrlPaths.PATH_THUMBNAIL_FILE
            + "{encryptedCustomerId}/{snapshotId}", method = RequestMethod.GET)
    public void downloadThumbNailFile(
            @PathVariable("encryptedCustomerId") String encryptedCustomerId,
            @PathVariable("snapshotId") Long snapshotId,
            @RequestParam(value = "download", required = false) Boolean download,
            HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws IOException {
        captureLogService.downloadThumbnail(encryptedCustomerId, snapshotId, download,
                httpServletRequest, httpServletResponse);
    }

    @RequestMapping(value = UrlPaths.PATH_SNAPSHOT_FILE
            + "{encryptedCustomerId}/{snapshotId}", method = RequestMethod.GET)
    public void downloadSnapShotFile(
            @PathVariable("encryptedCustomerId") String encryptedCustomerId,
            @PathVariable("snapshotId") Long snapshotId,
            @RequestParam(value = "download", required = false) Boolean download,
            HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws IOException {
        captureLogService.downloadSnapShot(encryptedCustomerId, snapshotId, download,
                httpServletRequest, httpServletResponse);
    }
    @RequestMapping(value = UrlPaths.PATH_LOG_FILE
            + "{encryptedCustomerId}/{logFileId}", method = RequestMethod.GET)
    public void downloadLogFile(
            @PathVariable("encryptedCustomerId") String encryptedCustomerId,
            @PathVariable("logFileId") Long logId,
            @RequestParam(value = "download", required = false) Boolean download,
            HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws IOException {
        captureLogService.downloadLogFile(encryptedCustomerId, logId, download,
                httpServletRequest, httpServletResponse);
    }
    @PreAuthorize("hasAuthority('VIEW_DEVICE')")
    @PostMapping(value = UrlPaths.PATH_DEVICE_SNAPSHOT_NOW + "push")
    public Response<?> pushDeviceCurrentSnapShot(
            @RequestBody CurrentSnapshotRequestDTO currentSnapshotRequestDTO,
            HttpServletResponse httpServletResponse
    ) {
        return updateHttpStatusCode(
                captureLogService.pushDeviceCurrentSnapShot(currentSnapshotRequestDTO),
                httpServletResponse
        );
    }
    @RequestMapping(value = UrlPaths.PATH_DEVICE_SNAPSHOT_NOW_THUMB
            + "{uniqueRequestId}", method = RequestMethod.GET)
    public Response<?> downloadCurrentImageSnapshotThumbnail(
            @PathVariable("uniqueRequestId") String uniqueRequestId,
            HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws IOException {
        Response<?> response =
                captureLogService.downloadCurrentImageSnapshotThumb(uniqueRequestId, httpServletRequest,
                        httpServletResponse);
        updateHttpStatusCode(response, httpServletResponse);
        return response;
    }
    @GetMapping(value = UrlPaths.PATH_DEVICE_SNAPSHOT_NOW_IMAGE + "{uniqueRequestId}")
    public Response<?> downloadCurrentImageSnapshot(
            @PathVariable("uniqueRequestId") String uniqueRequestId,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse
    ) throws IOException {
        return updateHttpStatusCode(
                captureLogService.downloadCurrentImageSnapshot(
                        uniqueRequestId,
                        httpServletRequest,
                        httpServletResponse
                ),
                httpServletResponse
        );
    }
    @Override
    protected BaseService getBaseService() {
        return null;
    }
}
