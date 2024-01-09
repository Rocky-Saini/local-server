package com.digital.signage.service.impl;

import com.amazonaws.services.iot.model.LogLevel;
import com.digital.signage.context.TenantContext;

import com.digital.signage.dto.AppUpgradeFailureReasonDTO;
import com.digital.signage.dto.AppUpgradeNotifyModel;
import com.digital.signage.dto.UnauthorizedResponse;
import com.digital.signage.models.*;
import com.digital.signage.models.FieldMessageHelper;
import com.digital.signage.repository.AppUpgradeFailureReasonRepository;
import com.digital.signage.repository.DeviceRepository;
import com.digital.signage.service.AppUpgradeService;
import com.digital.signage.util.*;
import org.apache.http.util.TextUtils;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

import static com.digital.signage.dto.AppUpgradeFailureReasonDTO.REASON_FOR_FAILURE_JSON_KEY;
import static com.digital.signage.dto.AppUpgradeFailureReasonDTO.TIMESTAMP_OF_FAILURE_EVENT_JSON_KEY;
import static com.digital.signage.util.Message.Common.COMMON_CANNOT_BE_NULL;
import static com.digital.signage.util.Message.Common.COMMON_INVALID_VALUE_FOR_KEY_ERROR;
import static com.digital.signage.util.Message.ResponseMessages.COMMON_CANNOT_BE_NULL_OR_EMPTY;

@Service
public class AppUpgradeServiceImpl extends BaseJenkinsServiceImpl implements AppUpgradeService {
    public static final Logger logger = LoggerFactory.getLogger(AppUpgradeServiceImpl.class);
    @Autowired
    private AppUpgradeFailureReasonRepository appUpgradeFailureReasonRepository;

    @Autowired
    private DeviceRepository deviceRepository;
    @Override
    public Response<?> addAppUpgradeFailureReasons(
            HttpServletRequest request,
            List<AppUpgradeFailureReasonDTO> appUpgradeFailureReasonDtoList
    ) {
        if (!TenantContext.isDevice()) {
            return Response.createUnAuthorizedResponse();
        }
        Long deviceId = TenantContext.getLoggedInDeviceId();
        Long customerId = TenantContext.getCustomerId();
        if (ObjectUtils.isEmpty(appUpgradeFailureReasonDtoList)) {
            return new ValidationErrorResponse(
                    "appUpgradeFailureReasonDtoList",
                    message.get(COMMON_CANNOT_BE_NULL_OR_EMPTY)
            );
        }
        FieldMessageHelper helper = new FieldMessageHelper();
        List<AppUpgradeFailureReason> reasonList =
                new ArrayList<>(appUpgradeFailureReasonDtoList.size());
        try {
            long currentTime = System.currentTimeMillis();
            appUpgradeFailureReasonDtoList.forEach(dto -> {
                if (Strings.isBlank(dto.getReasonForFailure())) {
                    helper.addFieldList(REASON_FOR_FAILURE_JSON_KEY,
                            message.get(COMMON_CANNOT_BE_NULL_OR_EMPTY));
                } else if (dto.getReasonForFailure().length()
                        > AppUpgradeFailureReason.COL_REASON_MAX_LENGTH) {
                    helper.addFieldList(REASON_FOR_FAILURE_JSON_KEY,
                            message.get(
                                    Message.ValidationMessages.MAX,
                                    REASON_FOR_FAILURE_JSON_KEY,
                                    AppUpgradeFailureReason.COL_REASON_MAX_LENGTH
                            )
                    );
                }
                if (Objects.isNull(dto.getTimestampOfFailureEvent())) {
                    helper.addFieldList(TIMESTAMP_OF_FAILURE_EVENT_JSON_KEY,
                            message.get(COMMON_CANNOT_BE_NULL));
                } else if (DateTimeUtilsKt.amIAFutureDateGreaterThanNowPlus10Mins(
                        dto.getTimestampOfFailureEvent())
                        || DateTimeUtilsKt.isDateBeforeDSCodeStart(dto.getTimestampOfFailureEvent())) {
                    helper.addFieldList(TIMESTAMP_OF_FAILURE_EVENT_JSON_KEY,
                            message.get(
                                    COMMON_INVALID_VALUE_FOR_KEY_ERROR,
                                    dto.getTimestampOfFailureEvent(),
                                    TIMESTAMP_OF_FAILURE_EVENT_JSON_KEY));
                }
                reasonList.add(
                        new AppUpgradeFailureReason(
                                null,
                                deviceId,
                                customerId,
                                dto.getReasonForFailure(),
                                dto.getTimestampOfFailureEvent(),
                                new Date(currentTime)
                        )
                );
            });
            if (!ObjectUtils.isEmpty(helper.get())) {
                return new ValidationErrorResponse(helper.get(), message);
            }
            appUpgradeFailureReasonRepository.saveAll(reasonList);
            return new SuccessFullySavedResponse<>(null);
        } catch (Exception e) {
            return Response.createInternalServerErrorResponseFromException(e);
        }
    }

    //    @Override
//    @Transactional
//    public Response<?> appUpgradeNotify(
//            AppUpgradeNotifyModel appUpgradeNotifyModel,
//            HttpServletRequest httpServletRequest
//    ) throws IOException {
//        logger.debug("POST ::  App Upgrade Notification with appUpgradeNotifyModel = {}",
//                appUpgradeNotifyModel);
//        if (isOnPremisesServer) {
//            return new ForbiddenResponse<>(
//                    "You can only call this API from the main server. This is not a main server");
//        }
//
//        if (!isJenkinsRequestAuthorized(httpServletRequest)) {
//            return new UnauthorizedResponse<>();
//        }
//
//        sanitizeApp(appUpgradeNotifyModel);
//
//        Response<CommonMultiActionResultObject> response = new Response<>();
//
//        FieldMessageHelper helper = new FieldMessageHelper();
//        if (appUpgradeNotifyModel.getBuildOs() == null) {
//            helper.addFieldList(AppUpgradeNotifyModel.JSON_KEY_DEVICE_OS,
//                    "Please provide valid deviceOs");
//        }
//
//        if (appUpgradeNotifyModel.getBuildDownloadUrl() == null
//                || appUpgradeNotifyModel.getBuildDownloadUrl().isEmpty()) {
//            helper.addFieldList(AppUpgradeNotifyModel.JSON_KEY_BUILD_DOWNLOAD_URL,
//                    "Please provide valid download url");
//        } else {
//            try {
//                new URL(appUpgradeNotifyModel.getBuildDownloadUrl());
//            } catch (MalformedURLException e) {
//                helper.addFieldList(AppUpgradeNotifyModel.JSON_KEY_BUILD_DOWNLOAD_URL,
//                        "Url is malformed: " + appUpgradeNotifyModel.getBuildDownloadUrl());
//            }
//        }
//
//        if (!helper.get().isEmpty()) {
//            return new ValidationErrorResponse(helper.get(), message);
//        }
//
//        logger.debug("appUpgradeNotifyModel.getOnPremiseId() = {}",
//                appUpgradeNotifyModel.getOnPremiseId());
//
//        Optional<JenkinsUrl> optionalJenkinsUrl;
//        if (appUpgradeNotifyModel.getOnPremiseId() != null) {
//            logger.debug("onPremiseId is not null so build is for on premise server");
//            // save the jenkins url in DB
//            int count =
//                    onPremisesEnvRepository.conutIfOnPremServerIdIsValid(
//                            appUpgradeNotifyModel.getOnPremiseId());
//
//            if (count == 0) {
//                helper.addFieldList(AppUpgradeNotifyModel.JSON_KEY_ON_PREM_ID,
//                        "Please provide valid onPremisesId");
//                return new ValidationErrorResponse(helper.get(), message);
//            }
//            optionalJenkinsUrl = jenkinsUrlRepository.getByBuildOsAndOnPremisesId(
//                    appUpgradeNotifyModel.getBuildOs().getDbValue(),
//                    appUpgradeNotifyModel.getOnPremiseId()
//            );
//        } else {
//            logger.debug("onPremiseId is null. so the build is for main server");
//            // save the jenkins url for main server in DB
//            // find if jekins Url is there in db
//            optionalJenkinsUrl = jenkinsUrlRepository.getByBuildOsForMainServer(
//                    appUpgradeNotifyModel.getBuildOs().getDbValue());
//        }
//        JenkinsUrl jenkinsUrl = optionalJenkinsUrl.orElseGet(JenkinsUrl::new);
//        jenkinsUrl.setOnPremisesId(
//                appUpgradeNotifyModel.getOnPremiseId() == null
//                        ? -1
//                        : appUpgradeNotifyModel.getOnPremiseId()
//        );
//        jenkinsUrl.setBuildOs(appUpgradeNotifyModel.getBuildOs());
//        jenkinsUrl.setLatestJenkinsBuildUrl(appUpgradeNotifyModel.getBuildDownloadUrl());
//
//        jenkinsUrlRepository.save(jenkinsUrl);
//
//        // download the build from jenkins and notify devices
//        downloadFromJenkinsAndNotifyService.downloadBuildFromJenkinsAndNotifyDevices(
//                appUpgradeNotifyModel.getBuildOs(),
//                appUpgradeNotifyModel.getBuildDownloadUrl(),
//                appUpgradeNotifyModel.getOnPremiseId()
//        );
//
//        response.setName(message.get(Message.Common.COMMON_SUCCESSFULLY_SAVED_KEY));
//        response.setResult(null);
//        response.setCode(ResponseCode.SUCCESS);
//        response.setMessage(
//                message.get(
//                        Message.Push.PUSH_UPGRADE_API_NOTIFICATION_SENT_TO_FOUND_DEVICES_SUCCESSFULLY));
//        return response;
//    }
    private void sanitizeApp(AppUpgradeNotifyModel appUpgradeNotifyModel) {
        if (appUpgradeNotifyModel.getDeviceOs() != null) {
            if ("LINUX_SERVER".equalsIgnoreCase(appUpgradeNotifyModel.getDeviceOs().trim())) {
                appUpgradeNotifyModel.setBuildOs(BuildOs.DESKTOP_APP_SERVER);
            } else if ("LINUX_CLIENT".equalsIgnoreCase(appUpgradeNotifyModel.getDeviceOs().trim())) {
                appUpgradeNotifyModel.setBuildOs(BuildOs.DESKTOP_APP_CLIENT);
            } else if ("LINUX_NATIVE".equalsIgnoreCase(appUpgradeNotifyModel.getDeviceOs().trim())) {
                appUpgradeNotifyModel.setBuildOs(BuildOs.DESKTOP_APP_NATIVE);
            } else if ("ANDROID".equalsIgnoreCase(appUpgradeNotifyModel.getDeviceOs().trim())) {
                appUpgradeNotifyModel.setBuildOs(BuildOs.ANDROID);
            } else if ("ANDROID_WATCH_DOG".equalsIgnoreCase(appUpgradeNotifyModel.getDeviceOs().trim())) {
                appUpgradeNotifyModel.setBuildOs(BuildOs.ANDROID_WATCH_DOG);
            } else if ("ANDROID_TV".equalsIgnoreCase(appUpgradeNotifyModel.getDeviceOs().trim())) {
                appUpgradeNotifyModel.setBuildOs(BuildOs.ANDROID_TV);
            } else if ("WINDOWS".equalsIgnoreCase(appUpgradeNotifyModel.getDeviceOs().trim())) {
                appUpgradeNotifyModel.setBuildOs(BuildOs.WINDOWS);
            }
        }
    }

    @Override
    public Response<?> getLatestApp(
            boolean isGeneric,
            String os,
            HttpServletRequest httpServletRequest
    ) throws IOException {

        if (!TenantContext.isDevice()) {
            return new ForbiddenResponse<>();
        }

        Long deviceId = TenantContext.getDeviceId();

        BuildOs buildOs = getBuildOsFromParam(os);
        if (buildOs == null) {
            return new ValidationErrorResponse("InvalidBuildOs", "Invalid build OS " + os);
        }

        Long onPremiseId = null;
//        if (isPiOnPremisesServer) {
//            PremisesId premisesId = premisesIdRepository.findAllByPkID();
//
//            if (premisesId != null && premisesId.getPremiseid() != null) {
//                onPremiseId = premisesId.getPremiseid();
//            }
//        }
        logger.debug("GET  :: Get Latest APP :: Device Logged In ");

        List<DeviceOs> deviceOses = deviceRepository.getDeviceOsByDeviceId(deviceId);
        if (deviceOses.size() == 1) {
            logger.debug("Get Latest APP :: {}", deviceId);
            DeviceOs deviceOsFromDb = deviceOses.get(0);
            if (deviceOsFromDb != null) {
//                    return onPremisesBuildService.fetchJenkinsBuildVersionDTO(
//                            onPremiseId,
//                            buildOs,
//                            true,
//                            deviceId,
//                            null
//                    );
                System.out.println("TODO");
                return null;
            } else {
                throw new IllegalArgumentException("Invalid device OS");
            }
        } else {
            return new NotFoundResponse<>("DeviceNotFound", "Device Not Found");
        }
    }
    private BuildOs getBuildOsFromParam(String os) {
        if (os != null && !os.trim().isEmpty()) {
            return BuildOs.jsonCreate(os.trim().toUpperCase());
        }
        return null;
    }


//    @Override
//    public  String md5(HttpServletRequest request,String filePath) {
//        InputStream inputStream = null;
//        if (TextUtils.isEmpty(filePath)) {
////            log(LogLevel.DEBUG, "md5Encryption return null since file path is : " + filePath);
//            return null;
//        }
//        try {
//            inputStream = new FileInputStream(filePath);
//            byte[] buffer = new byte[1024 * 1024];
//            MessageDigest digest = MessageDigest.getInstance("MD5");
//            int numRead = 0;
//            while (numRead != -1) {
//                numRead = inputStream.read(buffer);
//                if (numRead > 0)
//                    digest.update(buffer, 0, numRead);
//            }
//            byte[] md5Bytes = digest.digest();
//            String returnVal = "";
//            for (int i = 0; i < md5Bytes.length; i++) {
//                returnVal += Integer.toString((md5Bytes[i] & 0xff) + 0x100, 16).substring(1);
//            }
//            Path filePath2 = Paths.get(filePath);
//            String hex =
//                    null;
//            try(InputStream fis = Files.newInputStream(filePath2)) {
//                hex = DigestUtils.md5DigestAsHex(fis);
//                System.out.println("hex");
//            }
////            log(LogLevel.DEBUG, "md5Encryption MD5 calculated value of downloaded file : " + returnVal);
//            return hex+" ===  "+returnVal;
//        } catch (Exception e) {
////            log(LogLevel.ERROR, "md5Encryption error  occurred while calculating MD5 value of " +
////                    "downloaded file : " + ExceptionUtils.getStackTrace(e));
//            return null;
//        } finally {
//            if (inputStream != null) {
//                try {
//                    inputStream.close();
//                } catch (Exception e) {
//                }
//            }
//        }
//    }
}
