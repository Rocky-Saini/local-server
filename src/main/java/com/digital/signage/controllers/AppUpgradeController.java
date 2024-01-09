package com.digital.signage.controllers;

import com.digital.signage.constants.UrlPaths;
import com.digital.signage.dto.AppUpgradeFailureReasonDTO;
import com.digital.signage.dto.AppUpgradeNotifyModel;
import com.digital.signage.models.Response;
import com.digital.signage.service.AppUpgradeService;
import com.digital.signage.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@ControllerAdvice
@RestController
@RequestMapping(UrlPaths.PATH_APP_UPGRADE)
public class AppUpgradeController extends BaseController {
    @Autowired
    private AppUpgradeService appUpgradeService;

//    @PostMapping(value = UrlPaths.PATH_NOTIFY)
//    public Response<?> notify(
//            @RequestBody(required = true) AppUpgradeNotifyModel appUpgradeNotifyModel,
//            HttpServletRequest httpServletRequest,
//            HttpServletResponse httpServletResponse) throws IOException {
//        return updateHttpStatusCode(
//                appUpgradeService.appUpgradeNotify(appUpgradeNotifyModel, httpServletRequest),
//                httpServletResponse
//        );
//    }

    @PreAuthorize("hasAuthority('ALL_DEVICES')")
    @GetMapping(value = UrlPaths.PATH_DOWNLOAD_2)
    public Response<?> download(
            @RequestParam(defaultValue = "false") boolean isGeneric,
            @RequestParam(required = false) String os,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse
    ) throws IOException {
        return updateHttpStatusCode(
                appUpgradeService.getLatestApp(isGeneric, os, httpServletRequest),
                httpServletResponse
        );
    }



    @PreAuthorize("hasAuthority('ALL_DEVICES')")
    @PostMapping(UrlPaths.PATH_APP_UPGRADE_FAILURE_REASONS_URL)
    public Response<?> saveAppUpgradeFailureReason(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            @RequestBody List<AppUpgradeFailureReasonDTO> dtoList
    ) {
        return updateHttpStatusCode(
                appUpgradeService.addAppUpgradeFailureReasons(httpServletRequest, dtoList),
                httpServletResponse
        );
    }

//    @PostMapping("/md5")
//    public String md5(
//            HttpServletRequest httpServletRequest,
//            HttpServletResponse httpServletResponse,
//            @RequestParam(value = "filepath") String file
//            ) {
//        return appUpgradeService.md5(httpServletRequest, file);
//
//    }

    @Override
    protected BaseService getBaseService() {
        return (BaseService) appUpgradeService;
    }
}
