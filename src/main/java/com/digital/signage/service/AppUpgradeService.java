package com.digital.signage.service;

import com.digital.signage.dto.AppUpgradeFailureReasonDTO;
import com.digital.signage.dto.AppUpgradeNotifyModel;
import com.digital.signage.models.Response;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

public interface AppUpgradeService {


    Response<?> addAppUpgradeFailureReasons(
            HttpServletRequest request,
            List<AppUpgradeFailureReasonDTO> appUpgradeFailureReasonDtoList
    );
//    Response<?> appUpgradeNotify(
//            AppUpgradeNotifyModel appUpgradeNotifyModel,
//            HttpServletRequest httpServletRequest
//    ) throws IOException;
    Response<?> getLatestApp(
            boolean isGeneric,
            String os,
            HttpServletRequest httpServletRequest
    ) throws IOException;

//    String md5(
//            HttpServletRequest request,
//            String filePath
//    );
}
