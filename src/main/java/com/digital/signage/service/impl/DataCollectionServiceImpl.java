package com.digital.signage.service.impl;

import com.digital.signage.context.TenantContext;
import com.digital.signage.dto.DataCollectionRequestDTO;
import com.digital.signage.dto.DeletedDataDTO;
import com.digital.signage.service.*;
import com.digital.signage.dto.DeviceS3AnalyticsDTO;
import com.digital.signage.dto.DeviceSpaceAnalyticsDTO;
import com.digital.signage.exceptions.InvalidRequestDataException;
import com.digital.signage.models.*;
import com.digital.signage.repository.*;
import com.digital.signage.service.DataCollectionService;
import com.digital.signage.util.*;
import com.digital.signage.util.datacollection.DataCollectionHelper;
import com.google.common.collect.Lists;
import kotlin.Pair;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

import static com.digital.signage.util.ApplicationConstants.DEFAULT_BUILD_OS_FOR_DEVICE_OS;
import static com.digital.signage.util.ApplicationConstants.DEVICE_OS_BUILD_OS_MAP;
import static com.digital.signage.util.DataCollectionEnum.DeviceStatus.DATA_DELETED;
import static com.digital.signage.util.DataCollectionEnum.DeviceStatus.OFF;
import static com.digital.signage.util.DateTimeUtilsKt.amIAFutureDateGreaterThanNowPlus10Mins;
import static com.digital.signage.util.DateUtils.*;
import static java.util.Comparator.comparing;

@Service
public class DataCollectionServiceImpl extends BaseServiceImpl implements DataCollectionService {
    private static final Logger logger = LoggerFactory.getLogger(DataCollectionServiceImpl.class);
    // for /datacollection data is sent / collected only if it's value changes
    // or if interval to send data collection has reached
    // or if push ID 25 is received
    //
    // In case of push 25 data will be very less. as you will keep sending
    // 100 enteries per day is reasonable
    // so for 30 days it will be 3000 and to add buffer we can make it 5000
    private static final int MAX_DATA_COLLECTION_DATA_SIZE = 5000;

    @Autowired
    public DeviceConnectedStatusService deviceConnectedStatusService;

    @Autowired
    private DevicePanelProcessorHelper devicePanelProcessorHelper;

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private DeviceDataRepository deviceDataRepository;

    @Autowired
    private DeviceListenRequestRepository deviceListenRequestRepository;

    @Autowired
    private DeviceAppVersionRepository deviceAppVersionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NotificationTemplateUtils notificationTemplateUtils;




    @Autowired
    private Push push;

    @Autowired
    private PanelService panelService;

    @Autowired
    @Lazy
    private DeviceService deviceService;

    @Autowired
    @Lazy
    private ReportsUtils reportsUtils;

    @Autowired
    private DataCollectionConfigRepository dataCollectionConfigRepository;

    @Autowired
    private DataCollectionHelper helper;

   /* @Autowired
    private DeviceBandwidthManager deviceBandwidthManager;*/

    @Autowired
    public DeviceSpaceAnalyticsRepository deviceSpaceAnalyticsRepository;

    private static final Comparator<DataCollectionRequestDTO> requestDTOComparator =
            comparing(DataCollectionRequestDTO::getTimeOfStatus);

    @Override
    @NonNull
    public Response<?> sendData(
            @Nullable List<DataCollectionRequestDTO> dataCollectionList,
            @NonNull HttpServletRequest httpServletRequest
    ) {
        if (dataCollectionList != null) {
            if (dataCollectionList.size() > MAX_DATA_COLLECTION_DATA_SIZE) {
                return new ValidationErrorResponse(
                        "MaxSizeExceeded",
                        message.get(Message.DataCollection.MAX_DATA_COLLECTION_SIZE_EXCEEDED_ERROR,
                                MAX_DATA_COLLECTION_DATA_SIZE)
                );
            }
            dataCollectionList.parallelStream().forEach(
                    dataCollectionRequestDTO -> dataCollectionRequestDTO.setTimeZone(
                            DateTimeUtilsKt.getMyTimeZone())
            );
        }
        return sendDataVersion2(dataCollectionList, httpServletRequest);
    }

    private void sanitizeAndValidateRequest(List<DataCollectionRequestDTO> dataCollectionList)
            throws IllegalArgumentException {
        // sanitize timezone
        dataCollectionList.parallelStream()
                .forEach(dataCollectionRequestDTO -> {
                    if (dataCollectionRequestDTO.getTimeZone() == null
                            || dataCollectionRequestDTO.getTimeZone().isEmpty()) {
                        throw new IllegalArgumentException("Timezone is null or empty");
                    } else {
                        // validate timezone string
                        if (!DateTimeUtilsKt.isTimeZoneStringValid(dataCollectionRequestDTO.getTimeZone())) {
                            throw new IllegalArgumentException(
                                    "Timezone string '" + dataCollectionRequestDTO.getTimeZone() + "' is invalid");
                        }
                        dataCollectionRequestDTO.setTimeOfStatus(
                                DateTimeUtilsKt.changeTimeStampAccordingToTimeZone(
                                        dataCollectionRequestDTO.getTimeOfStatus(),
                                        dataCollectionRequestDTO.getTimeZone()));
                    }

                    // check if both lat and lng are present and not just one of them
                    if ((dataCollectionRequestDTO.getLatitude() != null
                            && dataCollectionRequestDTO.getLongitude() == null)
                            || (dataCollectionRequestDTO.getLatitude() == null
                            && dataCollectionRequestDTO.getLongitude() != null)) {
                        throw new IllegalArgumentException(
                                "Both latitude and longitude should be present found just one");
                    }

                    // validate lat lng
                    try {
                        LocationUtilsKt.validateLocationFetchingErrors(
                                dataCollectionRequestDTO.getLocationFetchingErrors()
                        );
                    } catch (Exception e) {
                        throw new IllegalArgumentException("Invalid Location Error");
                    }
                    if (dataCollectionRequestDTO.getLatitude() != null
                            && dataCollectionRequestDTO.getLongitude() != null
                            && dataCollectionRequestDTO.getLocationFetchingErrors() != null
                            && !dataCollectionRequestDTO.getLocationFetchingErrors().isEmpty()) {
                        // both lat long and error are present
                        throw new IllegalArgumentException("Both location and error cannot be sent together");
                    }
                });
    }

    @Override
    @NonNull
    public Response<?> sendDataVersion2(
            @Nullable List<DataCollectionRequestDTO> dataCollectionList,
            @NonNull HttpServletRequest httpServletRequest
    ) {
        long deviceId = TenantContext.getDeviceId();
        List<DeviceOs> deviceOses = deviceRepository.getDeviceOsByDeviceId(deviceId);

        if (deviceOses.size() != 1) {
            throw new IllegalStateException("This is not possible. deviceOses.size() should be 1");
        }

        DeviceOs deviceOs = deviceOses.get(0);

        if (DeviceOs.DESKTOP.equals(deviceOs)) {
            return new ValidationErrorResponse(
                    "UseDataCollectionV3",
                    "Use /dataCollection/v3 [POST] API"
            );
        }
        if (dataCollectionList != null) {
            dataCollectionList.parallelStream().forEach(
                    d -> {
                        d.setCurrentBuildVersions(null);
                        if (d.getCurrentBuildVersion() != null) {
                            d.setCurrentBuildVersions(new ArrayList<>(1));
                            DeviceAppVersion dav = new DeviceAppVersion();
                            dav.setAppVersion(d.getCurrentBuildVersion());
                            dav.setDeviceId(deviceId);
                            dav.setDeviceOs(deviceOs);
                            dav.setTimeOfStatus(d.getTimeOfStatus());
                            dav.setBuildOs(DEFAULT_BUILD_OS_FOR_DEVICE_OS.get(deviceOs));
                            d.getCurrentBuildVersions().add(dav);
                        }
                    }
            );
        }
        return sendDataVersion3(dataCollectionList, httpServletRequest);
    }

    @NotNull
    @Override
    public Response<?> sendDataVersion3(
            @Nullable List<DataCollectionRequestDTO> dataCollectionList,
            @NotNull HttpServletRequest httpServletRequest
    ) {

        Long customerId = TenantContext.getCustomerId();
        Long deviceId = TenantContext.getDeviceId();

        if (dataCollectionList == null || dataCollectionList.isEmpty()) {
            Response<Void> response = new Response<>();
            response.setHttpStatusCode(ResponseCode.VALIDATION_ERROR);
            response.setCode(ResponseCode.VALIDATION_ERROR);
            response.setName("MissingPayload");
            response.setMessage("Request data is missing");
            logger.debug("deviceId = {}, validationError : {}", deviceId, response);
            return response;
        }

        if (dataCollectionList.size() > MAX_DATA_COLLECTION_DATA_SIZE) {
            return new ValidationErrorResponse(
                    "MaxSizeExceeded",
                    message.get(Message.DataCollection.MAX_DATA_COLLECTION_SIZE_EXCEEDED_ERROR,
                            MAX_DATA_COLLECTION_DATA_SIZE)
            );
        }

        if (deviceId == null) {
            logger.debug("Device object not found for device id {} and customer id {}", deviceId,
                    customerId);
            Response<Void> response = new Response<>();
            response.setHttpStatusCode(ResponseCode.NOT_FOUND);
            response.setCode(ResponseCode.NOT_FOUND);
            response.setName("DeviceId Not Found");
            response.setMessage("Please send a valid list");
            logger.debug("deviceId = {}, notFoundError : {}", deviceId, response);
            return response;
        }

        Objects.requireNonNull(customerId);

        logger.debug("Device object found for device id {} and customer id {}",
                deviceId, customerId);

        logger.debug("data collection request size for device {} is {}",
                deviceId, dataCollectionList.size());

        try {
            sanitizeAndValidateRequest(dataCollectionList);
        } catch (IllegalArgumentException e) {
            // send error
            Response<Void> response = new Response<>();
            response.setHttpStatusCode(ResponseCode.FAILURE);
            response.setCode(400);
            response.setMessage(e.getMessage());
            response.setName("BadRequest");
            logger.debug("deviceId = {}, validationError : {}", deviceId, response);
            return response;
        }

        /*
         * sort request array first on basis of time of status in ascending order
         * find previous record form db and compare for notification
         * previous deviceData is changing from array.
         */
        if (dataCollectionList.size() > 1) {
            dataCollectionList.sort(requestDTOComparator);
        }
        @javax.annotation.Nullable
        DeviceData preDeviceDataInDb = deviceDataRepository.getLatestStatusByDeviceId(
                deviceId); // if there are parallel requests then this can be old one

        DeviceData[] preDeviceData =
                {preDeviceDataInDb == null ? null : (DeviceData) preDeviceDataInDb.clone()};

        //saving Data in Device Data
        List<DeviceData> toBeSavedDeviceDataList = new ArrayList<>(0);
        //create oneList for process data
        List<DataCollectionRequestDTO> requestDtoListForProcess =
                new ArrayList<>(dataCollectionList.size());
        //create a new list to save deviceHistory
        List<DeviceAppVersion> deviceAppVersionList = new ArrayList<>();
        Device deviceInDb = deviceRepository.findByDeviceId(deviceId);

        for (DataCollectionRequestDTO dataCollectionReq : dataCollectionList) {
            if (dataCollectionReq.getTimeOfStatus() == null) {
                Response<List<FieldMessage>> response = new Response<>();
                response.setResult(Collections.singletonList(
                        new FieldMessage(DeviceData.TIME_OF_STATUS,
                                message.get(Message.Common.COMMON_FIELD_VALIDATION_MESSAGE))));
                response.setHttpStatusCode(ResponseCode.FAILURE);
                logger.debug("deviceId = {}, validationError : {}", deviceId, response);
                return response;
            }
            if (amIAFutureDateGreaterThanNowPlus10Mins(dataCollectionReq.getTimeOfStatus())
                    || DateTimeUtilsKt.isDateBeforeDSCodeStart(dataCollectionReq.getTimeOfStatus())) {
                logger.error(
                        "deviceId = {}, dataCollection entry ignored because timeOfStatus = {}",
                        deviceId,
                        dataCollectionReq.getTimeOfStatus()
                );
                continue;
            }

            //if error occurred then create one object
            if (dataCollectionReq.getDeviceEncounteredError() != null
                    && dataCollectionReq.getDeviceEncounteredError().equals(Boolean.TRUE)) {
                logger.error(
                        "deviceId = {}, dataCollection entry ignored because deviceEncounteredError is true",
                        deviceId
                );
                continue; // we ignore rest of the info in the request
            }

            if (dataCollectionReq.getCurrentBuildVersions() != null
                    && !dataCollectionReq.getCurrentBuildVersions().isEmpty()) {
                for (DeviceAppVersion deviceAppVersion : dataCollectionReq.getCurrentBuildVersions()) {
                    if (deviceAppVersion.getBuildOs() == null) {
                        ValidationErrorResponse validationError =
                                new ValidationErrorResponse("BuildOs", "BuildOs field cannot be null");
                        logger.debug("deviceId = {}, validationError : {}", deviceId, validationError);
                        return validationError;
                    }
                    // match the deviceOs and buildOs
                    if (!DEVICE_OS_BUILD_OS_MAP.get(deviceInDb.getDeviceOs())
                            .contains(deviceAppVersion.getBuildOs())) {
                        ValidationErrorResponse validationError = new ValidationErrorResponse(
                                "BuildOs",
                                "BuildOs=" + deviceAppVersion.getBuildOs()
                                        + " does to belong to DeviceOs=" + deviceInDb.getDeviceOs());
                        logger.debug("deviceId = {}, validationError : {}", deviceId, validationError);
                        return validationError;
                    }
                    deviceAppVersion.setDeviceId(deviceInDb.getDeviceId());
                    deviceAppVersion.setDeviceOs(deviceInDb.getDeviceOs());
                    deviceAppVersion.setTimeOfStatus(dataCollectionReq.getTimeOfStatus());
                    deviceAppVersionList.add(deviceAppVersion);
                }
            }

            if (dataCollectionReq.getIsDeviceDown() == null) {
                // skip this record as we cannot do anything with it
                continue;
            }
            requestDtoListForProcess.add(dataCollectionReq);
      /* creating object to save in DB
      DeviceData toBeSavedDeviceData = new DeviceData();
      toBeSavedDeviceData.setDeviceId(deviceId);
      toBeSavedDeviceData.setIsAudioEnabled(dataCollectionReq.getIsDeviceAudioEnabled());
      toBeSavedDeviceData.setTimeOfStatus(dataCollectionReq.getTimeOfStatus());
      // if in request  isDeviceDown is true coming with additional info Week_off then set device status as Week_Off
      if (dataCollectionReq.getIsDeviceDown()) {
        if (DataCollectionEnum.AdditionalInfo.WEEK_OFF.equals(
            dataCollectionReq.getDeviceAdditionalInfo())) {
          toBeSavedDeviceData.setDeviceStatus(WEEK_OFF);
        } else {
          toBeSavedDeviceData.setDeviceStatus(OFF);
        }
      } else {
        toBeSavedDeviceData.setDeviceStatus(ON);
      }
      toBeSavedDeviceData.setCustomerId(customerId);
      toBeSavedDeviceData.setIpAddress(dataCollectionReq.getIpAddress());
      toBeSavedDeviceData.setLastSyncTime(dataCollectionReq.getLastSyncTime());
      toBeSavedDeviceData.setDeviceDataId(null);
      if (AFTER_ONBOARDING.equals(dataCollectionReq.getDeviceAdditionalInfo())) {
        toBeSavedDeviceData.setDeviceAdditionalInfo(AFTER_ONBOARDING);
      }
      toBeSavedDeviceDataList.add(toBeSavedDeviceData);

      notifyToUserForDeviceUpDownTime(toBeSavedDeviceData, preDeviceData,
          deviceInDb.getDeviceName());
      preDeviceData = (DeviceData) toBeSavedDeviceData.clone();
      }*/
        }

        deviceAppVersionRepository.saveAll(deviceAppVersionList);
        //now go for process data collection.
        if (!ObjectUtils.isEmpty(requestDtoListForProcess)) {
            //if size is one or two then don't do any thing
            if (requestDtoListForProcess.size() == 1) {
                toBeSavedDeviceDataList.add(
                        helper.createDeviceDataFromRequest(requestDtoListForProcess.get(0), deviceInDb));
            } else if (requestDtoListForProcess.size() == 2) {
                toBeSavedDeviceDataList.add(
                        helper.createDeviceDataFromRequest(requestDtoListForProcess.get(0), deviceInDb));
                toBeSavedDeviceDataList.add(
                        helper.createDeviceDataFromRequest(requestDtoListForProcess.get(1), deviceInDb));
            } else {
                // find list of date from status request.
                List<Date> timeOfStatusList =
                        requestDtoListForProcess.stream()
                                .map(DataCollectionRequestDTO::getTimeOfStatus)
                                .map(DateUtils::floorDate)
                                .distinct()
                                .sorted(Comparator.comparing(Date::getTime))
                                .collect(Collectors.toList());
                //get config map per date
                Map<String, List<DataCollectionConfig>> dataCollectionConfigMap =
                        helper.createPerDateDataCollectionConfig(timeOfStatusList, deviceId);
                // default config of this device
                List<DataCollectionConfig> defaultConfigs =
                        helper.getDefaultDeviceConfigPerUniqueDevice(deviceId);
                //saving Data in Device Data
                toBeSavedDeviceDataList =
                        helper.preProcessDataCollectionBeforeSaveAndReturn(requestDtoListForProcess, deviceInDb,
                                new Pair<>(dataCollectionConfigMap, defaultConfigs));
            }
            if (ObjectUtils.isEmpty(toBeSavedDeviceDataList)) {
                logger.debug("for deviceId::{}, deviceData list After optimisation is empty.", deviceId);
            } else {
                logger.debug("for deviceId::{}, deviceData list After optimisation is {}.", deviceId,
                        toBeSavedDeviceDataList);
                //now notify device for down time
                //iterate on list and check down
//                toBeSavedDeviceDataList.forEach(deviceData -> {
//                    notifyToUserForDeviceUpDownTime(deviceData, preDeviceData[0],
//                            deviceInDb.getDeviceName());
//                    preDeviceData[0] = (DeviceData) deviceData.clone();
//                });
                //now going to save
                deviceDataRepository.saveAll(toBeSavedDeviceDataList);
                //update preProcess db after new entry.
                devicePanelProcessorHelper.makeEntryInDeviceProcessor(toBeSavedDeviceDataList);
            }
        }

    /*comment these variable because this is not used later.
    Date lastSyncTime = null;
    String ipAddress = null;
    Boolean audioStatus = null;
    Boolean isDeviceDown = null;
    String appVersion = null;

    ///**
    // * sort request array first on basis of time of status in descending order
    // * find latest deviceData for update for device.
    // */
        //if (!ObjectUtils.isEmpty(dataCollectionList)
        //    && dataCollectionList.size() > 1) {
        //  dataCollectionList.sort(requestDTOReverseComparator);
        //}

        // by iterating and updating the fields we find the latest non-null
        // values of the fields
        //comment these codes not useful, previous we are update device table on basis of these data but now
        // DeviceAndPanel.kt class is used for this purpose..
    /*for (int i = dataCollectionList.size() - 1; i >= 0; i--) {
      DataCollectionRequestDTO dataCollectionReq = dataCollectionList.get(i);
      if (dataCollectionReq.getIsDeviceAudioEnabled() != null && audioStatus == null) {
        audioStatus = dataCollectionReq.getIsDeviceAudioEnabled();
      }
      if (dataCollectionReq.getLastSyncTime() != null && lastSyncTime == null) {
        lastSyncTime = dataCollectionReq.getLastSyncTime();
      }
      if (dataCollectionReq.getIpAddress() != null && ipAddress == null) {
        ipAddress = dataCollectionReq.getIpAddress();
      }
      if (dataCollectionReq.getIsDeviceDown() != null && isDeviceDown == null) {
        isDeviceDown = dataCollectionReq.getIsDeviceDown();
      }
      if (dataCollectionReq.getCurrentBuildVersion() != null && appVersion == null) {
        appVersion = dataCollectionReq.getCurrentBuildVersion();
      }
    }

    if (!dataCollectionList.isEmpty()) {
      DataCollectionRequestDTO dataCollectionReq =
          dataCollectionList.get(
              dataCollectionList.size() - 1); // gets is the latest one
      if (audioStatus != null) {
        // always send no matter what
        sendNotificationOfDeviceMuteStatusToListeningUser(
            deviceId,
            customerId,
            dataCollectionReq.getTimeOfStatus(),
            audioStatus
        );
      }
      */
        if (!dataCollectionList.isEmpty()) {
            dataCollectionList.sort(requestDTOComparator.reversed());
            Optional<Boolean> audioStatus = dataCollectionList.stream()
                    .map(DataCollectionRequestDTO::getIsDeviceAudioEnabled)
                    .filter(Objects::nonNull)
                    .findFirst();
            audioStatus.ifPresent(currentStatus -> {
                Optional<Date> timeOfStatus = dataCollectionList.stream()
                        .map(DataCollectionRequestDTO::getTimeOfStatus)
                        .filter(Objects::nonNull)
                        .findFirst();
                timeOfStatus.ifPresent(date ->
                        // always send no matter what
                        sendNotificationOfDeviceMuteStatusToListeningUser(
                                deviceId,
                                customerId,
                                date,
                                currentStatus
                        ));
            });
        }
      /*
      // To be saved db object
      // Device toBeSavedDbObject = new Device();
      // toBeSavedDbObject.setDeviceId(deviceId);

      //if (isInfoChanged(deviceInDb, dataCollectionReq)) {
      // updating device Object
      //if (dataCollectionReq.getLastSyncTime() != null) {
      //  // if existing last sync time is null then directly set coming time
      //  if (deviceInDb.getLastSyncTime() == null) {
      //    toBeSavedDbObject.setLastSyncTime(dataCollectionReq.getLastSyncTime());
      //  } else {
      //    if (dataCollectionReq.getLastSyncTime().compareTo(deviceInDb.getLastSyncTime()) > 0) {
      //      toBeSavedDbObject.setLastSyncTime(dataCollectionReq.getLastSyncTime());
      //    }
      //  }
      //}
      //if (dataCollectionReq.getIpAddress() != null) {
      //  toBeSavedDbObject.setIpAddress(dataCollectionReq.getIpAddress());
      //}

      //if (dataCollectionReq.getIsDeviceAudioEnabled() != null) {
      //  //if device audio data not present in db and it comes in request then send notification
      //  if ((deviceInDb.getIsAudioEnabled() == null
      //      && dataCollectionReq.getIsDeviceAudioEnabled() != null)
      //      // isAudio Enabled comes in request and it different from db status then send notification
      //      || (dataCollectionReq.getIsDeviceAudioEnabled() != null &&
      //      shouldSendPushAndUpdateDeviceObject(deviceInDb.getIsAudioEnabled(),
      //          dataCollectionReq.getIsDeviceAudioEnabled()))) {
      //    if (dataCollectionReq.getIsDeviceAudioEnabled()) {
      //      toBeSavedDbObject.setIsAudioEnabled(AudioStatusEnum.ON);
      //    } else {
      //      toBeSavedDbObject.setIsAudioEnabled(AudioStatusEnum.OFF);
      //    }
      //
      //  }
      //}
      //toBeSavedDbObject.setIsDeviceDown(deviceDataObj.getIsDeviceDown());
      //if (deviceInDb.getDeviceConnectivity() != null && !deviceInDb.getDeviceConnectivity()
      //    .equals(DeviceStatus.CONNECTED)) {
      //  toBeSavedDbObject.setDeviceConnectivity(DeviceStatus.CONNECTED);
      //}
      //if (dataCollectionReq.getTimeOfStatus() != null) {
      //  toBeSavedDbObject.setTimeOfDeviceStatus(dataCollectionReq.getTimeOfStatus());
      //}
      //if (dataCollectionReq.getCurrentBuildVersion() != null
      //    && (!(dataCollectionReq.getCurrentBuildVersion()
      //    .equals(deviceInDb.getCurrentBuildVersion())))) {
      //  toBeSavedDbObject.setCurrentBuildVersion(dataCollectionReq.getCurrentBuildVersion());
      //}
      //deviceService.saveDeviceChanges(toBeSavedDbObject);
      //}
    }*/

        deviceConnectedStatusService.saveDeviceConnectedStatus(
                new DeviceConnectedStatus(null, new Date(), deviceId, customerId));
        Response<Void> response = new Response<>();
        response.setCode(20);
        response.setName("SuccessfullySaved");
        response.setMessage(message.get(Message.DataCollection.DATA_COLLECTION_ADD_SUCCESS));
        return response;
    }

    /**
     * This Method is used for finding previous status of device is not on then send notification to user.
     */
//    private void notifyToUserForDeviceUpDownTime(DeviceData currentDeviceData,
//                                                 DeviceData preDeviceData, String mediaPlayerName, String deviceName) {
//        if ((preDeviceData == null && currentDeviceData.getDeviceStatus() != null) ||
//                (preDeviceData != null
//                        && preDeviceData.getDeviceStatus() != null
//                        && currentDeviceData.getDeviceStatus() != null
//                        && preDeviceData.getDeviceStatus() != currentDeviceData.getDeviceStatus())) {
//            if (currentDeviceData.getDeviceStatus() == OFF) {
//                logger.info("Now Device is down ,Notify User");
//                sendNotificationOfDeviceStatus(currentDeviceData, WebPushId.DEVICE_DOWNTIME, mediaPlayerName, deviceName);
//            } else {
//                logger.info("Now Device is Up ,Notify User");
//                sendNotificationOfDeviceStatus(currentDeviceData, WebPushId.DEVICE_UPTIME,mediaPlayerName, deviceName);
//            }
//            // silent push will always be connected because device is able to call this API
//            sendNotificationOfDeviceStatusToListeningUser(currentDeviceData);
//        }
//    }

    /**
     * This method create userMessage object which are sent in notification
     */
    private UserMessage createUserMessageObj(DeviceData deviceData, WebPushId webPushEnum,
                                             NotificationTemplate masterTemplate, String deviceName) {
        //It should be Change
        HashMap<String, String> values = new HashMap<>();
        UserMessage userMessage = null;
        NotificationTemplate notificationTemplate =
                notificationTemplateUtils.createTemplateClone(masterTemplate);
        Objects.requireNonNull(notificationTemplate);
        userMessage = new UserMessage();
        values.put(NotificationTemplate.KeysValuesForMap.DEVICE_NAME, deviceName);
        userMessage.setCustomerId(deviceData.getCustomerId());
        notificationTemplateUtils.replaceNotificationStrings(notificationTemplate, values);
        userMessage.setMessageString(notificationTemplate.getBody());
        userMessage.setMessageTitle(notificationTemplate.getSubject());
        userMessage.setEntityId(deviceData.getDeviceId());
        userMessage.setMessageType(webPushEnum.getValue());
        userMessage.setEntityType(EntityTypeEnum.DEVICE);
        userMessage.setSentOn(new Date());
        return userMessage;
    }

    /**
     * This is used for sending status. This is not for silent push
     */
//    private void sendNotificationOfDeviceStatus(DeviceData deviceData, WebPushId webPushId,
//                                                String deviceName , String mediaPlayerName) {
//        List<User> userList =
//                userRepository.findUsersByCustomerAndCustRepAdminAndDeviceAccessRole(
//                        deviceData.getCustomerId());
//        if (userList != null) {
//            NotificationTemplate masterTemplate =
//                    notificationTemplateUtils.getNotificationTemplate(webPushId.getValue());
//            for (User user : userList) {
//                boolean isPanasonicUser = user.getIsPanasonicCustRep() || user.getIsCustomerAdmin();
//                if (!isPanasonicUser) {
//                    List<Long> deviceList = panelService.getDeviceListByLocation(user,mediaPlayerName);
//                    if (deviceList != null
//                            && !deviceList.isEmpty()
//                            && deviceList.contains(deviceData.getDeviceId())) {
//                        UserMessage userMessage =
//                                createUserMessageObj(deviceData, webPushId, masterTemplate, deviceName);
//                        userMessage.setUserId(user.getUserId());
//                        push.sendWebPush(userMessage);
//                    }
//                } else {
//                    UserMessage userMessage =
//                            createUserMessageObj(deviceData, webPushId, masterTemplate, deviceName);
//                    userMessage.setUserId(user.getUserId());
//                    push.sendWebPush(userMessage);
//                }
//            }
//        }
//    }

    /**
     * this is for silent push
     */
    private void sendNotificationOfDeviceStatusToListeningUser(DeviceData deviceData) {
        //sending notification to those user who are listening to these device.
        List<DeviceListenRequest> deviceListenRequests = deviceListenRequestRepository
                .findAllByDeviceIdAndState(deviceData.getDeviceId());
        for (DeviceListenRequest deviceListenRequest : deviceListenRequests) {
            DeviceUpDownSilentPushDTO userMessage = new DeviceUpDownSilentPushDTO();
            userMessage.setEntityType(EntityTypeEnum.DEVICE);
            userMessage.setMessageType(WebPushId.DEVICE_UP_DOWN.getValue());
            userMessage.setEntityId(deviceData.getDeviceId());
            userMessage.setSentOn(new Date());
            userMessage.setCustomerId(deviceData.getCustomerId());
            userMessage.setUserId(deviceListenRequest.getUserId());
            userMessage.setDeviceConnectivity(DeviceStatus.CONNECTED);
            // time of status will always be now
            userMessage.setTimeOfDeviceStatus(new Timestamp(System.currentTimeMillis()));
            push.sendWebPush(userMessage, deviceListenRequest.getFbRegistrationId(), true);
            logger.debug("Print notification: {}, registrationId: {}, userId: {}",
                    userMessage.getMessageString(),
                    deviceListenRequest.getFbRegistrationId(),
                    deviceListenRequest.getUserId());
        }
    }

    private static AudioStatusEnum fromIsAudioEnabled(Boolean isAudioEnabled) {
        if (isAudioEnabled == null) return null;
        if (isAudioEnabled) {
            return AudioStatusEnum.ON;
        } else {
            return AudioStatusEnum.OFF;
        }
    }

    private boolean sendNotificationOfDeviceMuteStatusToListeningUser(
            long deviceId,
            long customerId,
            Date timeOfStatus,
            boolean isAudioEnabled
    ) {
        //sending notification to those user who are listening to these device.
        List<DeviceListenRequest> deviceListenRequests = deviceListenRequestRepository
                .findAllByDeviceIdAndState(deviceId);
        for (DeviceListenRequest deviceListenRequest : deviceListenRequests) {
            DeviceMuteUnmuteSilentPushDTO userMessage = new DeviceMuteUnmuteSilentPushDTO();
            userMessage.setEntityType(EntityTypeEnum.DEVICE);
            userMessage.setMessageType(WebPushId.DEVICE_MUTE_UNMUTED.getValue());
            userMessage.setEntityId(deviceId);
            userMessage.setSentOn(new Date());
            userMessage.setCustomerId(customerId);
            userMessage.setUserId(deviceListenRequest.getUserId());
            userMessage.setIsAudioEnabled(fromIsAudioEnabled(isAudioEnabled));
            userMessage.setTimeOfDeviceStatus(new Timestamp(timeOfStatus.getTime()));
            push.sendWebPush(userMessage, deviceListenRequest.getFbRegistrationId(), true);
            logger.info("Print notification : {}", userMessage.getMessageString());
        }
        return true;
    }

    @Override
    @NonNull
    public Response<?> sendDeletedData(DeletedDataDTO dataCollectionDTO,
                                       @NonNull HttpServletRequest request) {
        return sendDeletedData(Lists.newArrayList(dataCollectionDTO), request);
    }

    @Override
    @NonNull
    public Response<?> sendDeletedData(
            List<DeletedDataDTO> dataCollectionDTOS,
            @NonNull HttpServletRequest request
    ) {
        Device device = deviceRepository.findByDeviceId(TenantContext.getLoggedInDeviceId());
        List<DeviceData> toBeSavedDeviceDataList = new ArrayList<>();

        if (dataCollectionDTOS == null || dataCollectionDTOS.isEmpty()) {
            return Response.Builder.voidResponseBuilder()
                    .badRequest()
                    .code(1)
                    .message("Deleted data sent in request is empty")
                    .name("DeletedDataIsEmpty")
                    .build();
        }

        //add all date after reset time to find out dataCollection config in one query
        Set<Date> allDateSet = new HashSet<>(dataCollectionDTOS.size());
        dataCollectionDTOS.forEach(dto -> {
            allDateSet.add(resetTime(dto.getDeletedDataStartTime()));
            allDateSet.add(resetTime(dto.getDeletedDataEndTime()));
        });
        // find dataCollectionConfig from map then set in map
        final Map<String, List<DataCollectionConfig>> dataCollectionConfigMapPerDate =
                reportsUtils.findDataCollectionConfigFromDB(allDateSet, device.getDeviceId());
        // find defaultDataCollectionConfig from map then set in map
        final List<DataCollectionConfig> defaultDeviceConfigs =
                reportsUtils.getDefaultDeviceConfigPerUniqueDevice(device.getDeviceId());
        try {
            //create entries in deviceData table with deleted-data enum.
            for (DeletedDataDTO dto : dataCollectionDTOS) {
                helper.sanitizeAndValidateDeletedDataCollectionDTO(dto);
                List<Date> dateList = findAllDatesBetweenTwoDateRangeFromDTO(dto).stream()
                        .sorted(Date::compareTo)
                        .collect(Collectors.toList());
                dateList.forEach(dateInLoop -> {
                    //if today is not week off then go ahead
                    if (!reportsUtils.isDateInLoopAWeekOff(
                            dto.getWeekOffs(),
                            dateInLoop,
                            device.getDeviceId(),
                            dataCollectionConfigMapPerDate,
                            defaultDeviceConfigs
                    )) {
                        //create panel on time entry with data deleted status
                        Date panelOnTimeOfStatus = getDataStartDateFromDTO(dto, dateInLoop);
                        DeviceData toBeSavedDeviceDataOnTime =
                                createDeviceDataWithDeletedStatus(device, panelOnTimeOfStatus);
                        toBeSavedDeviceDataList.add(toBeSavedDeviceDataOnTime);
                        //create panel off time entry with data deleted status
                        Date panelOffTimeOfStatus = getDataEndDateFromDTO(dto, dateInLoop);
                        DeviceData toBeSavedDeviceDataOffTime =
                                createDeviceDataWithDeletedStatus(device, panelOffTimeOfStatus);
                        toBeSavedDeviceDataList.add(toBeSavedDeviceDataOffTime);
                    }
                });
            }
        } catch (InvalidRequestDataException e) {
            return new ValidationErrorResponse("InvalidRequest", e.getInvalidDataLocalizedMessage());
        }

        deviceDataRepository.saveAll(toBeSavedDeviceDataList);
        Response<Void> response = new Response<>();
        response.setCode(20);
        response.setName("SuccessfullySaved");
        response.setMessage(message.get(Message.DataCollection.DATA_COLLECTION_ADD_SUCCESS));
        return response;
    }

    private DeviceData createDeviceDataWithDeletedStatus(Device device, Date timeOfStatus) {
        DeviceData toBeSavedDeviceData = new DeviceData();
        toBeSavedDeviceData.setDeviceId(device.getDeviceId());
        toBeSavedDeviceData.setIsAudioEnabled(null);
        toBeSavedDeviceData.setTimeOfStatus(timeOfStatus);
        toBeSavedDeviceData.setDeviceStatus(DATA_DELETED);
        toBeSavedDeviceData.setCustomerId(device.getCustomerId());
        toBeSavedDeviceData.setIpAddress(device.getLocalServerIP());
        toBeSavedDeviceData.setLastSyncTime(device.getInActiveTime());
        toBeSavedDeviceData.setDeviceDataId(null);
        return toBeSavedDeviceData;
    }

    private List<Date> findAllDatesBetweenTwoDateRangeFromDTO(DeletedDataDTO dto) {
        if (Objects.nonNull(dto.getDeletedDataStartTime()) && Objects.nonNull(
                dto.getDeletedDataEndTime())) {
            return findAllDatesBetweenTwoDateRange(dto.getDeletedDataStartTime(),
                    dto.getDeletedDataEndTime(), true, false);
        } else if (!StringUtils.isEmpty(dto.getDeletedDataStartDate()) && !StringUtils.isEmpty(
                dto.getDeletedDataEndDate())) {
            return findAllDatesBetweenTwoDateRange(dto.getDeletedDataStartTime(),
                    dto.getDeletedDataEndTime(), true);
        }
        return Lists.newArrayList();
    }

    private Date getDataStartDateFromDTO(DeletedDataDTO dto, Date date) {
        Date panelOnTime = combineUtilDateAndTime(date, dto.getPanelOnTime());
        if (Objects.nonNull(dto.getDeletedDataStartTime()) && Objects.nonNull(
                dto.getDeletedDataEndTime())) {
            return isBothDateOfSameDate(date, dto.getDeletedDataStartTime()) && date.after(panelOnTime)
                    ? dto.getDeletedDataStartTime() : panelOnTime;
        } else {
            return panelOnTime;
        }
    }

    private Date getDataEndDateFromDTO(DeletedDataDTO dto, Date date) {
        Date panelOffDate = combineUtilDateAndTime(date, dto.getPanelOffTime());
        if (Objects.nonNull(dto.getDeletedDataStartTime()) && Objects.nonNull(
                dto.getDeletedDataEndTime())) {

            //add here code to start date and end date.
            return isBothDateOfSameDate(date, dto.getDeletedDataEndTime()) && date.before(panelOffDate)
                    ? dto.getDeletedDataEndTime() : panelOffDate;
        } else {
            return panelOffDate;
        }
    }

    @NotNull
    @Override
    public Response<?> saveDeviceSpaceAnalyticsData(
            @Nullable DeviceSpaceAnalyticsDTO deviceSpaceDto,
            @NotNull HttpServletRequest httpServletRequest
    ) {
        if (!TenantContext.isDevice()) {
            logger.debug("Only device can access saveDeviceSpaceAnalyticsData api.");
            return Response.createUnAuthorizedResponse();
        }
        long deviceId = TenantContext.getLoggedInDeviceId();
        long customerId = TenantContext.getCustomerId();
        logger.debug("Going to save device space analytics data for deviceId::{}", deviceId);
        try {
            if (deviceSpaceDto == null) {
                return new ValidationErrorResponse("deviceSpaceDto",
                        message.get(Message.Common.COMMON_CANNOT_BE_NULL));
            }
            if (isInValidLongValue(deviceSpaceDto.getAvailableSpaceOnDeviceInBytes())) {
                return new ValidationErrorResponse(
                        DeviceSpaceAnalyticsDTO.JSON_KEY_AVAILABLE_SPACE_ON_DEVICE_IN_BYTES,
                        message.get(
                                Message.DeviceSpaceAnalytics.AVAILABLE_SPACE_ON_DEVICE_IN_BYTES_VALIDATION_ERROR));
            }
            if (isInValidLongValue(deviceSpaceDto.getTotalSpaceOnDeviceInBytes())) {
                return new ValidationErrorResponse(
                        DeviceSpaceAnalyticsDTO.JSON_KEY_TOTAL_MEMORY_ON_DEVICE_IN_MB,
                        message.get(
                                Message.DeviceSpaceAnalytics.TOTAL_SPACE_ON_DEVICE_IN_BYTES_VALIDATION_ERROR));
            }
            if (deviceSpaceDto.getAvailableSpaceOnDeviceInBytes()
                    > deviceSpaceDto.getTotalSpaceOnDeviceInBytes()) {
                return new ValidationErrorResponse(
                        DeviceSpaceAnalyticsDTO.JSON_KEY_TOTAL_MEMORY_ON_DEVICE_IN_MB,
                        message.get(
                                Message.DeviceSpaceAnalytics.TOTAL_SPACE_NOT_LESS_THAN_AVAILABLE_VALIDATION_ERROR));
            }
            DeviceSpaceAnalytics analyticsModel = new DeviceSpaceAnalytics();
            analyticsModel.setAvailableSpaceOnDeviceInBytes(
                    deviceSpaceDto.getAvailableSpaceOnDeviceInBytes());
            analyticsModel.setTotalSpaceOnDeviceInBytes(deviceSpaceDto.getTotalSpaceOnDeviceInBytes());
            analyticsModel.setCustomerId(customerId);
            analyticsModel.setDeviceId(deviceId);
            analyticsModel.setThresholdReached(deviceSpaceDto.getThresholdReached());
            double availablePercentage =
                    CommonUtils.roundDoubleTillTwoDecimal(
                            ((double) deviceSpaceDto.getAvailableSpaceOnDeviceInBytes()
                                    / deviceSpaceDto.getTotalSpaceOnDeviceInBytes()) * 100);
            logger.debug("available percentage={} for deviceId::{}", availablePercentage, deviceId);
            analyticsModel.setAvailableSpacePercentage(availablePercentage);
            logger.debug("DeviceSpaceAnalytics for save is  :::{} ", analyticsModel);
            deviceSpaceAnalyticsRepository.save(analyticsModel);
            logger.debug("Device space analytics data is saved for deviceId::{}", deviceId);
            return new SuccessFullySavedResponse<>(null);
        } catch (Exception e) {
            logger.debug("", e);
            return Response.createInternalServerErrorResponseFromException(e);
        }
    }

    private boolean isInValidLongValue(Long aLong) {
        return aLong == null || aLong <= 0L;
    }

    @NotNull
    @Override
    public Response<?> saveDeviceS3AnalyticsData(
            @Nullable List<DeviceS3AnalyticsDTO> deviceS3AnalyticsList,
            @NotNull HttpServletRequest httpServletRequest
    ) {
        if (!TenantContext.isDevice()) {
            logger.debug("Only device can access saveDeviceSpaceAnalyticsData api.");
            return Response.createUnAuthorizedResponse();
        }
        long deviceId = TenantContext.getLoggedInDeviceId();
        logger.debug("Going to added in device consumed bandwidth for deviceId::{}", deviceId);
        try {
            if (ObjectUtils.isEmpty(deviceS3AnalyticsList)) {
                return new ValidationErrorResponse("deviceS3AnalyticsList",
                        message.get(Message.Common.COMMON_CANNOT_BE_NULL_OR_EMPTY));
            }
            for (DeviceS3AnalyticsDTO deviceS3AnalyticsDTO : deviceS3AnalyticsList) {
                if (isInValidLongValue(deviceS3AnalyticsDTO.getS3ConsumedDataInBytes()) ||
                        Objects.isNull(deviceS3AnalyticsDTO.getConsumedDate())) {
                    logger.debug("invalid consumed data value::{} for this device ::{}", deviceS3AnalyticsDTO,
                            deviceId);
                    continue;
                }
//                deviceBandwidthManager.insertOrUpdate(
//                        deviceId,
//                        DateUtils.getDateFromString(deviceS3AnalyticsDTO.getConsumedDate()),
//                        deviceS3AnalyticsDTO.getS3ConsumedDataInBytes()
//                );
//                logger.debug("added");
            }
            logger.debug("Successfully added in device consumed bandwidth for deviceId::{}", deviceId);
            return new SuccessFullySavedResponse<>(null);
        } catch (Exception e) {
            logger.debug("", e);
            return Response.createInternalServerErrorResponseFromException(e);
        }
    }
}

