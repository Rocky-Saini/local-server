package com.digital.signage.service.impl;

import com.digital.signage.context.TenantContext;
import com.digital.signage.dto.PushMarkAsReadRequestDTO;
import com.digital.signage.dto.PushRegistrationDTO;
import com.digital.signage.dto.UserMessageResponseDTO;
import com.digital.signage.models.FieldMessageHelper;
import com.digital.signage.models.*;
import com.digital.signage.repository.*;
import com.digital.signage.service.DeviceService;
import com.digital.signage.service.Push;
import com.digital.signage.service.PushNotificationService;
import com.digital.signage.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.annotation.Nullable;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static com.digital.signage.util.PushId.*;

@Service
public class PushNotificationServiceImpl extends BaseServiceImpl
        implements PushNotificationService {

    public static final Logger logger = LoggerFactory.getLogger(PushNotificationServiceImpl.class);

    public static final Set<Integer> PANEL_PUSH_ID_SET;

    public static final Set<Integer> PUSH_IDS_FOR_DEVICE_STATUS;

    public static final Set<Integer> PUSH_IDS_FOR_PANEL_PENDING_STATUS;

    private static final Set<Integer> DEVICE_PUSH_ID_SET;

    static {
        DEVICE_PUSH_ID_SET = new HashSet<>();
        DEVICE_PUSH_ID_SET.add(ID_CLIENT_DELETE_CONTENT.getValue());
        DEVICE_PUSH_ID_SET.add(ID_CLIENT_UPDATE_APP.getValue());
        DEVICE_PUSH_ID_SET.add(ID_CLIENT_DISABLE.getValue());
        DEVICE_PUSH_ID_SET.add(ID_CLIENT_REMOVE.getValue());
        DEVICE_PUSH_ID_SET.add(ID_CLIENT_TURN_OFF_AUDIO.getValue());
        DEVICE_PUSH_ID_SET.add(ID_CLIENT_FETCH_SCHEDULE_AGAIN.getValue());
        DEVICE_PUSH_ID_SET.add(ID_CLIENT_REDOWNLOAD_CONFIG.getValue());
        DEVICE_PUSH_ID_SET.add(ID_CLIENT_TURN_ON_AUDIO.getValue());
        DEVICE_PUSH_ID_SET.add(ID_CLIENT_ENABLE.getValue());
        DEVICE_PUSH_ID_SET.add(ID_CLIENT_ALL_PANELS_OF_DEVICES_ON.getValue());
        DEVICE_PUSH_ID_SET.add(ID_CLIENT_ALL_PANELS_OF_DEVICES_OFF.getValue());
        DEVICE_PUSH_ID_SET.add(ID_CLIENT_TURN_ON_ALL_PANEL_AUDIO.getValue());
        DEVICE_PUSH_ID_SET.add(ID_CLIENT_TURN_OFF_ALL_PANEL_AUDIO.getValue());
        DEVICE_PUSH_ID_SET.add(ID_CLIENT_REBOOT.getValue());
        DEVICE_PUSH_ID_SET.add(ID_CLIENT_UPDATE_YOUR_DEVICE_MODEL.getValue());
        DEVICE_PUSH_ID_SET.add(ID_CLIENT_UPLOAD_LOGS_NOW.getValue());
        DEVICE_PUSH_ID_SET.add(ID_CLIENT_SEND_A_SNAPSHOT_NOW.getValue());

        PANEL_PUSH_ID_SET = new HashSet<>();
        PANEL_PUSH_ID_SET.add(18);
        PANEL_PUSH_ID_SET.add(19);
        PANEL_PUSH_ID_SET.add(20);
        PANEL_PUSH_ID_SET.add(21);

        // Push IDS for device status
        PUSH_IDS_FOR_DEVICE_STATUS = new HashSet<>();
        //PUSH_IDS_FOR_DEVICE_STATUS.add(5);//ID_CLIENT_DISABLE
        PUSH_IDS_FOR_DEVICE_STATUS.add(ID_CLIENT_TURN_OFF_AUDIO.getValue());//ID_CLIENT_TURN_OFF_AUDIO
        PUSH_IDS_FOR_DEVICE_STATUS.add(ID_CLIENT_TURN_ON_AUDIO.getValue());//ID_CLIENT_TURN_ON_AUDIO
        PUSH_IDS_FOR_DEVICE_STATUS.add(
                ID_CLIENT_ALL_PANELS_OF_DEVICES_OFF.getValue());//ID_CLIENT_ENABLE
        PUSH_IDS_FOR_DEVICE_STATUS.add(ID_CLIENT_ALL_PANELS_OF_DEVICES_ON.getValue());
        PUSH_IDS_FOR_DEVICE_STATUS.add(ID_CLIENT_TURN_ON_ALL_PANEL_AUDIO.getValue());
        PUSH_IDS_FOR_DEVICE_STATUS.add(ID_CLIENT_TURN_OFF_ALL_PANEL_AUDIO.getValue());

        // Push IDs for panel pending status
        PUSH_IDS_FOR_PANEL_PENDING_STATUS = new HashSet<>();
        PUSH_IDS_FOR_PANEL_PENDING_STATUS.add(ID_CLIENT_TURN_ON_INDIVIDUAL_PANEL_AUDIO.getValue());
        PUSH_IDS_FOR_PANEL_PENDING_STATUS.add(ID_CLIENT_TURN_OFF_INDIVIDUAL_PANEL_AUDIO.getValue());
        PUSH_IDS_FOR_PANEL_PENDING_STATUS.add(ID_CLIENT_INDIVIDUAL_PANELS_OF_DEVICES_OFF.getValue());
        PUSH_IDS_FOR_PANEL_PENDING_STATUS.add(ID_CLIENT_INDIVIDUAL_PANELS_OF_DEVICES_ON.getValue());
    }

    // services
    @Autowired
    @Lazy
    private DeviceService deviceService;

    // repositories
    @Autowired
    private UserMessageRepository userMessageRepository;
    @Autowired
    private DeviceRepository deviceRepository;
    @Autowired
    private DeviceListenRequestRepository deviceListenRequestRepository;
    @Autowired
    private UserFcmRegistrationIdMappingRepository userFcmRegistrationIdMappingRepository;
    @Autowired
    private PanelRepository panelRepository;
    @Autowired
    private PushNotificationRepository pushNotificationRepository;
    @Autowired
    private CurrentDownloadPushRepository currentDownloadPushRepository;
    @Autowired
    private DeviceTokenRepository deviceTokenRepository;
    @Autowired
    private PushRegistrationIdRepository pushRegistrationIdRepository;
    @Autowired
    private DeviceDataRepository deviceDataRepository;
    @Autowired
    private LastApiHitTimeRepository lastApiHitTimeRepository;

    // others
    @Autowired
    private Push push;
    @Value("${push.retry.attempts}")
    private Integer pushRetryAttemtsValue;
    @Value("${push.retry.attempts.hour}")
    private Integer pushRetryBeforeHour;

    public synchronized void updatePushRegId(String regId, Long deviceId) {
        PushRegistrationIdModel regModel =
                pushRegistrationIdRepository.findPushRegistrationIdModelByDeviceId(deviceId);
        if (regModel != null) {
            regModel.setPushRegsitrationId(regId);
        } else {
            regModel = new PushRegistrationIdModel();
            regModel.setDeviceId(deviceId);
            regModel.setPushRegsitrationId(regId);
        }
        pushRegistrationIdRepository.save(regModel);
    }

    private Device isAuthenticated(String tokenHeader, String deviceId,
                                   String clientGeneratedDeviceIdentifier, int apiVersion) {
//        if (tokenHeader != null) {
//            TenantContext.getCurrentToken();
//            String tokenString = tokenHeader.replace(SecurityConstants.TOKEN_PREFIX, "");
            //JwtToken jwtToken = JwtUtils.getJwtToken(SECRET, tokenString, objectMapper);
//            if (false) {//jwtToken != null) {
                /*logger.debug("jwtToken != null is true");
                if (TokenType.DEVICE.equals(jwtToken.getTokenType())) {
                    logger.debug("jwtToken is of device");
                    String user = jwtToken.getUser();

                    if (user != null) {
                        logger.debug("user != null is true");
                        String id = jwtToken.getId();
                        DeviceToken tokenEntity = null;
                        try {
                            Optional<DeviceToken> deviceTokenOptional = deviceTokenRepository.findById(Long.valueOf(id));
                            if(deviceTokenOptional.isPresent()) {
                                tokenEntity = deviceTokenOptional.get();
                            }
                        } catch (Exception e) {
                            logger.error("", e);
                        }
                        if (null != tokenEntity && tokenEntity.getDeviceId()
                                .equals(Long.valueOf(jwtToken.getUser()))) {
                            //Device device = deviceRepository.findDeviceByDeviceIdAndStatus(Long.valueOf(user), Status.ACTIVE.getValue());
                            Device device = deviceRepository.findByDeviceId(Long.valueOf(user));
                            if (device == null) {
                                logger.debug("exiting getAuthentication; return null");
                            } else if (Status.ACTIVE.equals(device.getStatus()) || Status.INACTIVE.equals(
                                    device.getStatus())) {
                                // success
                                return device;
                            }
                        }
                    }
                }*/
//            }
//        } else {
            // device ID is required
            if (deviceId == null) return null;

            // if api version 2 then client generated device identifier is also required
            if (clientGeneratedDeviceIdentifier == null && apiVersion == 2) {
                return null;
            }

            // let's authenticate
            Long deviceIdLong = Long.valueOf(deviceId);
            Device device = deviceRepository.findByDeviceId(deviceIdLong);
            if (device == null || Status.DELETED.equals(device.getStatus())) {
                logger.debug("exiting getAuthentication; return null");
                return null;
            }

            if (apiVersion == 2) {
                // if api version 2 then validate client generated device identifier
                if (device.getDeviceKey().equals(clientGeneratedDeviceIdentifier)) {
                    return device;
                } else {
                    return null;
                }
            } else {
                // version 1 so no need to validate. Send success
                return device;
            }
        }
//    }

    @Override
    @Transactional
    public Response<?> saveDeviceRegistration(PushRegistrationDTO pushRegistrationDTO,
                                              HttpServletRequest request, int apiVersion) {
        if (pushRegistrationDTO == null || pushRegistrationDTO.getPushRegId() == null) {
            logger.debug("Request model or regId is null");
            return new ValidationErrorResponse(null, "Please provide PushRegId");
        }

        Device device = isAuthenticated(
                request.getHeader(ApplicationConstants.Headers.AUTHORIZATION),
                request.getHeader(ApplicationConstants.Headers.DEVICE_ID),
                request.getHeader(ApplicationConstants.Headers.CLIENT_GENERATED_DEVICE_IDENTIFIER),
                apiVersion);

        if (device == null) {
            return new NotFoundResponse<>("InvalidDevice", "Device not found");
        }

        if (pushRegistrationDTO.getPushRegId().trim().isEmpty()) {
            // trying to remove the empty regid
            logger.debug("trying to save empty reg id");
        }

        // saving regid in db
        logger.debug("saving reg ID = {} in PushRegistrationDb table having deviceId = {}",
                pushRegistrationDTO.getPushRegId(), device.getDeviceId());

        updatePushRegId(pushRegistrationDTO.getPushRegId(), device.getDeviceId());

        logger.debug("Saved Push Reg Id " + pushRegistrationDTO.getPushRegId());

        return new Response<>(pushRegistrationDTO.getPushRegId(), null,
                message.get(Message.Push.PUSH_DEVICE_REGISTRATION_UPDATE_KEY),
                200, message.get(Message.Push.PUSH_DEVICE_REGISTRATION_UPDATE_MESSAGE));
    }

    @Override
    public Response<?> sendDeviceNotification(NotificationRequest notificationRequest) {
        List<Long> successList = new ArrayList<>();
        List<Device> successDeviceList = new ArrayList<>();
        List<Long> notFoundList = new ArrayList<>();
        List<FieldMessage> badRequestList = new ArrayList<>();
        //List<String> androidList = new ArrayList<>();
        //List<String> windowsList = new ArrayList<>();
        List<Integer> invalidPushIdsList = new ArrayList<>();
        Long customerId = TenantContext.getCustomerId();
        if (notificationRequest.getDeviceIds() == null
                || notificationRequest.getDeviceIds().isEmpty()) {
            logger.debug("Notification device is null");
            return new ValidationErrorResponse(
                    new FieldMessageHelper()
                            .addFieldList("deviceIds", "deviceIds should be present")
                            .get(),
                    message);
        }

        if (notificationRequest.getPushIdIntegers() != null
                && !notificationRequest.getPushIdIntegers().isEmpty()) {
            notificationRequest.getPushIdIntegers().forEach(pushIdInteger -> {
                if (pushIdInteger != null) {
                    if (!DEVICE_PUSH_ID_SET.contains(pushIdInteger)) {
                        invalidPushIdsList.add(pushIdInteger);
                    }
                }
            });
        }

        if (!invalidPushIdsList.isEmpty()) {
            logger.debug("invalidPushIdList is not empty.");
            // invalid push Ids error
            return new ValidationErrorResponse(
                    new FieldMessageHelper()
                            .addFieldList("pushIds", "Invalid push Ids: "
                                    + invalidPushIdsList.stream()
                                    .map(String::valueOf)
                                    .collect(Collectors.joining(", ")))
                            .get(),
                    message);
        }
        List<PushId> pushIds = notificationRequest.getPushIds();
        if (ObjectUtils.isEmpty(pushIds)) {
            logger.debug("In notificationRequest pushId is empty.");
            return new ValidationErrorResponse(
                    new FieldMessageHelper()
                            .addFieldList("pushIds", "pushIds should be present")
                            .get(),
                    message);
        }

        // all data is valid so send push
        List<Long> deviceIdList = notificationRequest.getDeviceIds();
        logger.debug("Finding deviceList sized :" + deviceIdList.size());
        for (Long deviceId : deviceIdList) {
            Device device = deviceRepository.getByDeviceIdAndCustomerId(deviceId);
            if (device != null) {
                //if (StringUtils.isEmpty(device.getLocalServerIP())) {
                //  if (device.getDeviceOs() == DeviceOs.WINDOWS) {
                //    windowsList.add(device.getPushRegistrationId());
                //  } else if (device.getDeviceOs() == DeviceOs.ANDROID) {
                //    androidList.add(device.getPushRegistrationId());
                //  }
                //} else {
                //  // implement here Socket IO
                //}
                logger.debug("Validating device Id:" + device.getDeviceId());
                if (Status.INACTIVE.equals(device.getStatus())) {
                    logger.debug(device.getDeviceName() + ":device status is invalid.");
                    badRequestList.add(new FieldMessage("deviceId",
                            device.getDeviceName() + "is INACTIVE, so can not perform this action."));
                    //
                    //
                    // Commented below else if out as we will send push even if device is DISCONNECTED
                    //
                    //
                    //} else if (DeviceStatus.DISCONNECTED.equals(device.getDeviceConnectivity())) {
                    //  logger.debug(device.getDeviceName() + ":device  is Disconnected.");
                    //  badRequestList.add(new FieldMessage("deviceId",
                    //      device.getDeviceName() + "is DISCONNECTED, so can not perform this action."));
                } else if (Status.ACTIVE.equals(device.getStatus())) {
                    logger.debug("device status is valid.");
                    successList.add(device.getDeviceId());
                    successDeviceList.add(device);
                } else if (Status.DELETED.equals(device.getStatus())) {
                    logger.debug("device status is deleted.");
                    notFoundList.add(deviceId);
                }
            } else {
                logger.debug("device is null.");
                notFoundList.add(deviceId);
            }
        }

        if (!badRequestList.isEmpty()) {
            // there is no success list therefore send back error
            CommonMultiActionResultObject commonMultiActionResultObject =
                    new CommonMultiActionResultObject();
            commonMultiActionResultObject.setNotFound(notFoundList);
            commonMultiActionResultObject.setBadRequest(badRequestList);
            logger.debug("No device found having status Active");
            return new Response<>(commonMultiActionResultObject, null, null, ResponseCode.SUCCESS, null,
                    HttpStatus.OK.value());
        }

        for (PushId pushId : pushIds) {
            logger.debug("sending push..");
            // send push
            if (notificationRequest.getInstantAppUpgrade() == null
                    || !notificationRequest.getInstantAppUpgrade()) {
                push.sendPushToDevices(pushId, successDeviceList, TenantContext.getCurrentUserId(),
                        TenantContext.getCustomerId());
            } else {
                push.sendPushToDevices(pushId, successDeviceList, TenantContext.getCurrentUserId(),
                        notificationRequest.getInstantAppUpgrade(), TenantContext.getCustomerId());
            }
        }

        CommonMultiActionResultObject commonMultiActionResultObject =
                new CommonMultiActionResultObject();
        commonMultiActionResultObject.setSuccess(successList);
        commonMultiActionResultObject.setNotFound(notFoundList);
        return new Response<>(commonMultiActionResultObject, null, null, 200,
                message.get(Message.Device.DEVICE_PUSH_SENT_CONNECTED_MEDIA_PLAYER),
                HttpStatus.OK.value());
    }

    @Override
    public Response<?> sendPanelsNotification(PanelNotificationRequest panelNotificationRequest) {
        List<Long> successList = new ArrayList<>();
        List<Long> notFoundList = new ArrayList<>();
        Long customerId = TenantContext.getCustomerId();
        Long currentUserId = TenantContext.getCurrentUserId();
        Map<Long, List<Long>> deviceIdPanelIdsMap = new HashMap<>();
        List<PushId> invalidPushIdsList = new ArrayList<>();
        if (panelNotificationRequest.getPanelIds() == null
                || panelNotificationRequest.getPanelIds().isEmpty()) {
            logger.debug("panelNotificationRequest has empty panelIdList");
            return new ValidationErrorResponse(
                    new FieldMessageHelper()
                            .addFieldList("panelIds", "panelIds should be present")
                            .get(),
                    message);
        }

        if (panelNotificationRequest.getPushIds() == null
                || panelNotificationRequest.getPushIds().isEmpty()) {
            logger.debug("panelNotificationRequest has null pushId");
            return new ValidationErrorResponse(
                    new FieldMessageHelper()
                            .addFieldList("pushIds", "pushIds should be present")
                            .get(),
                    message);
        }

        panelNotificationRequest.getPushIds().forEach(pushId -> {
            if (!PANEL_PUSH_ID_SET.contains(pushId.getValue())) {
                invalidPushIdsList.add(pushId);
            }
        });

        if (!invalidPushIdsList.isEmpty()) {
            logger.debug("panelNotificationRequest has some Invalid pushIds");
            // invalid push Ids error
            return new ValidationErrorResponse(
                    new FieldMessageHelper()
                            .addFieldList("pushIds", "Invalid push Ids: "
                                    + invalidPushIdsList.stream()
                                    .map(String::valueOf)
                                    .collect(Collectors.joining(", ")))
                            .get(),
                    message);
        }

        List<Long> panelIds = panelNotificationRequest.getPanelIds();
        for (Long panelId : panelIds) {
            Optional<Panel> panelOptional = panelRepository.findById(panelId);
            if(panelOptional.isPresent()) {
                Panel panel = panelOptional.get();
                if (panel != null && panel.getStatus() != Status.DELETED) {
                    if (deviceIdPanelIdsMap.containsKey(panel.getDeviceId())) {
                        deviceIdPanelIdsMap.get(panel.getDeviceId()).add(panel.getId());
                    } else {
                        List<Long> panelList = new ArrayList<>();
                        panelList.add(panel.getId());
                        deviceIdPanelIdsMap.put(panel.getDeviceId(), panelList);
                    }
                    successList.add(panelId);
                }
            } else {
                notFoundList.add(panelId);
            }
        }
        CommonMultiActionResultObject commonMultiActionResultObject = null;
        if (!deviceIdPanelIdsMap.isEmpty()) {
            for (PushId pushId : panelNotificationRequest.getPushIds()) {
                commonMultiActionResultObject =
                        push.sendPush(pushId, deviceIdPanelIdsMap, currentUserId, null, null);
            }
        }
        return new Response<>(commonMultiActionResultObject, null, null, null, null);
    }

    @Override
    public Response<?> sendPushAcknowledge(AcknowledgeRequestModel acknowledgeRequestModel,
                                           HttpServletRequest request) {
        logger.debug("sendPushAcknowledge - acknowledgeRequestModel = {}", acknowledgeRequestModel);
        List<PushNotificationStatus> statusForAck = new ArrayList<>();
        statusForAck.add(PushNotificationStatus.RECEIVED);
        statusForAck.add(PushNotificationStatus.DONE);
        statusForAck.add(PushNotificationStatus.NOT_DONE);
        if (acknowledgeRequestModel == null) {
            return Response.createBadRequestResponse("RequestNull",
                    "acknowledgeRequestModel cannot be null");
        }
        Long deviceId = TenantContext.getDeviceId();
        assert deviceId != null;
        acknowledgeRequestModel.setDeviceId(deviceId);
        if (acknowledgeRequestModel.getMessageId() == null) {
            return Response.createBadRequestResponse("MessageIdRequired",
                    "Message Id is required to acknowledge");
        }
        if (acknowledgeRequestModel.getPushId() == null) {
            return Response.createBadRequestResponse("PushIdRequired",
                    "Push Id is required to acknowledge");
        }
        if (!statusForAck.contains(acknowledgeRequestModel.getAcknowledgement())) {
            return new Response<>(null, null, null, 400,
                    " acknowledgement  must be RECEIVED or DONE or NOT_DONE ",
                    HttpStatus.BAD_REQUEST.value());
        }
        PushNotification pushNotification =
                pushNotificationRepository.getById(acknowledgeRequestModel.getMessageId());
        if (pushNotification == null) {
            logger.error("Following Push record not found -> device Id:"
                    + acknowledgeRequestModel.getDeviceId()
                    + ","
                    + "Push ID :"
                    + acknowledgeRequestModel.getPushId()
                    + " and Status :SENT");
            return new NotFoundResponse<>(null, "Following Push record not found");
        }
        pushNotification.setAckTime(new Date());
        if (PushNotificationStatus.RECEIVED.equals(acknowledgeRequestModel.getAcknowledgement())) {
            //push notification status should be SENT
            if (!PushNotificationStatus.SENT.equals(pushNotification.getStatus())) {
                return new Response<>(null, null, null, null,
                        "RECEIVED Acknowledgement can not sent, already Acknowledgement with"
                                + pushNotification.getStatus() + " status", HttpStatus.BAD_REQUEST.value());
            }
        } else if (PushNotificationStatus.DONE.equals(acknowledgeRequestModel.getAcknowledgement())) {
            //push notification status should be SENT
            if (PushNotificationStatus.NOT_DONE.equals(pushNotification.getStatus())) {
                return new Response<>(null, null, null, null,
                        "DONE Acknowledgement can not sent, already Acknowledged with"
                                + pushNotification.getStatus() + " status", HttpStatus.BAD_REQUEST.value());
            }
        } else if (PushNotificationStatus.NOT_DONE.equals(
                acknowledgeRequestModel.getAcknowledgement())) {
            //push notification status should be SENT
            if (PushNotificationStatus.DONE.equals(pushNotification.getStatus())) {
                return new Response<>(null, null, null, null,
                        "NOT_DONE Acknowledgement can not sent, already Acknowledged with "
                                + pushNotification.getStatus() + " status", HttpStatus.BAD_REQUEST.value());
            }
        }

        pushNotification.setStatus(acknowledgeRequestModel.getAcknowledgement());
        pushNotificationRepository.save(pushNotification);
        //sending silent notification to listening user
        DeviceUpdateSilentPushDTO deviceUpdateSilentPushDTO = new DeviceUpdateSilentPushDTO();
        deviceUpdateSilentPushDTO.setMessageType(WebPushId.DEVICE_UPDATES.getValue());
        deviceUpdateSilentPushDTO.setCustomerId(TenantContext.getCustomerId());
        deviceUpdateSilentPushDTO.setSentOn(new Date());

        //finding list of listing firebase for this device
        List<DeviceListenRequest> deviceListenRequests = deviceListenRequestRepository
                .findAllByDeviceIdAndState(acknowledgeRequestModel.getDeviceId());

        if (!deviceListenRequests.isEmpty()) {
            @Nullable
            DeviceData deviceData = deviceDataRepository.getLatestStatusByDeviceId(deviceId);
            LastApiHitTimeModel lastApiHitTimeModel =
                    lastApiHitTimeRepository.fetchLatestApiHitTimeModel(deviceId);

            DevicePushAckDTO devicePushAckDTO = new DevicePushAckDTO(
                    acknowledgeRequestModel.getPushId().getValue(),
                    acknowledgeRequestModel.getDeviceId(),
                    pushNotification.getSendTime(),
                    pushNotification.getAckTime(),
                    pushNotification.getStatus(),
                    deviceData == null ? null : deviceData.getLastSyncTime(),
                    lastApiHitTimeModel != null ? lastApiHitTimeModel.getTimeOfLastApiHit() : null
            );

            deviceUpdateSilentPushDTO.setPushStatus(devicePushAckDTO);

            for (DeviceListenRequest deviceListenRequest : deviceListenRequests) {
                push.sendWebPush(deviceUpdateSilentPushDTO, deviceListenRequest.getFbRegistrationId(),
                        true);
            }
        }

        return new Response<>(null, null, "Acknowledged", 200,
                "Acknowledged that push was received");
    }

    @Override
    public Response<?> saveWebRegistration(Map<String, String> pushWebRegistrationParamMap) {

        User user = TenantContext.getLoggedInUser();
        Long customerId = TenantContext.getCustomerId();
        boolean isCustomerRepUser = user.getRoles().iterator().next().getName().equals(
                ApplicationConstants.ROLE_PANASONIC_CUST_REP);
        UserFcmRegistrationIdMapping userFcmRegistrationIdMapping = null;
        List<DeviceListenRequest> deviceListenUserId = null;
        List<CurrentDownloadPush> currentDownloadPushList = null;

        if (user != null) {
            if (pushWebRegistrationParamMap != null && !pushWebRegistrationParamMap.get(
                    UserFcmRegistrationIdMapping.JSON_KEY_REGISTRATION_ID).isEmpty()) {

                currentDownloadPushList = currentDownloadPushRepository.findAllByFireBaseIdAndUserId(
                        pushWebRegistrationParamMap.get(
                                UserFcmRegistrationIdMapping.JSON_KEY_REGISTRATION_ID), user.getUserId());
                if (currentDownloadPushList != null && !currentDownloadPushList.isEmpty()) {
                    currentDownloadPushRepository.deleteAll(currentDownloadPushList);
                }
                deviceListenUserId =
                        deviceListenRequestRepository.findAllByFirebaseRegistrationIdAndStateAndUserId(
                                pushWebRegistrationParamMap.get(
                                        UserFcmRegistrationIdMapping.JSON_KEY_REGISTRATION_ID), user.getUserId());
                if (deviceListenUserId != null && !deviceListenUserId.isEmpty()) {
                    deviceListenRequestRepository.deleteAll(deviceListenUserId);
                }

                userFcmRegistrationIdMapping =
                        userFcmRegistrationIdMappingRepository.findByUserIdAndRegistrationId(
                                user.getUserId(), pushWebRegistrationParamMap.get(
                                        UserFcmRegistrationIdMapping.JSON_KEY_REGISTRATION_ID));
                if (userFcmRegistrationIdMapping != null) {
                    if (isCustomerRepUser) {
                        userFcmRegistrationIdMapping.setCustomerId(
                                customerId); // customerId if null then show save as null
                    }
                    userFcmRegistrationIdMappingRepository.save(userFcmRegistrationIdMapping);
                } else {
                    List<UserFcmRegistrationIdMapping> userFcmRegistrationIdMappingList =
                            userFcmRegistrationIdMappingRepository.findByRegistrationId(
                                    pushWebRegistrationParamMap.get(
                                            UserFcmRegistrationIdMapping.JSON_KEY_REGISTRATION_ID));
                    if (!userFcmRegistrationIdMappingList.isEmpty()) {
                        userFcmRegistrationIdMappingRepository.deleteAll(userFcmRegistrationIdMappingList);
                    }
                    userFcmRegistrationIdMapping = new UserFcmRegistrationIdMapping();
                    userFcmRegistrationIdMapping.setTimeOfAddingRegistrationId(new Date());
                    userFcmRegistrationIdMapping.setUserId(user.getUserId());
                    userFcmRegistrationIdMapping.setRegistrationId(pushWebRegistrationParamMap.get(
                            UserFcmRegistrationIdMapping.JSON_KEY_REGISTRATION_ID));
                    if (isCustomerRepUser) userFcmRegistrationIdMapping.setCustomerId(customerId);
                    userFcmRegistrationIdMappingRepository.save(userFcmRegistrationIdMapping);
                }
                Response<String> successResponse = new SuccessFullySavedResponse<>(
                        pushWebRegistrationParamMap.get(UserFcmRegistrationIdMapping.JSON_KEY_REGISTRATION_ID));
                successResponse.setMessage("registrationId saved");
                return successResponse;
            } else {
                return new Response<>(null, null,
                        UserFcmRegistrationIdMapping.JSON_KEY_REGISTRATION_ID + "Required",
                        HttpStatus.BAD_REQUEST.value(),
                        message.get(Message.Push.PUSH_REGISTRATION_NOTIFICATION_WEB_MESSAGE_NOT));
            }
        } else {
            return new NotFoundResponse<>(null, message);
        }
    }

    @Transactional
    @Override
    public Response<?> getAllUserNotification(Integer pageNumber, Integer numPerPage,
                                              HttpServletRequest httpServletRequest) {

        pageNumber = pageNumber != null ? pageNumber : 1;
        numPerPage =
                (numPerPage != null
                        && numPerPage <= ApplicationConstants.MAX_ITEMS_PER_PAGE_FOR_PAGINATION)
                        ? numPerPage
                        : ApplicationConstants.MAX_ITEMS_PER_PAGE_FOR_PAGINATION;

        User user = TenantContext.getLoggedInUser();
        Long userId = user.getUserId();
        if (user.getIsPanasonicCustRep()) {
            String customerId = httpServletRequest.getHeader("customerId");
            if (null != customerId) {
                java.sql.Date sentOnPastDate =
                        new java.sql.Date(Calendar.getInstance().getTime().getTime() -
                                (TimeUnit.DAYS.toMillis(7)));
                Page<UserMessage> userMessages =
                        userMessageRepository.userMessageQuery(userId, Long.valueOf(customerId), sentOnPastDate,
                                PageRequest.of(pageNumber - 1, numPerPage,
                                        Sort.by(Sort.Direction.DESC, "sentOn")));

                int totalcount = (int) userMessages.getTotalElements();
                int totalPage = userMessages.getTotalPages();
                List<UserMessage> userMessageList = userMessages.getContent();
                Integer unreadCount =
                        userMessageRepository.countAllByUserIdAndState(userId, sentOnPastDate, false);
                Pagination pagination = PaginationUtils.getPagination(totalcount, pageNumber, numPerPage,
                        userMessageList.size());
                Response<UserMessageResponseDTO> response = new Response<>();
                response.setResult(new UserMessageResponseDTO(unreadCount, userMessageList));
                response.setPagination(pagination);
                response.setName("FetchSuccessfully");
                response.setMessage(message.get(Message.Common.COMMON_FETCH_SUCCUESS));
                response.setHttpStatusCode(HttpStatus.OK.value());
                response.setCode(ResponseCode.SUCCESS);
                return response;
            } else {
                return new Response<>(null, null, null, null, "please pass the customerId in header", null);
            }
        }
        return getAllUsers(pageNumber, numPerPage, httpServletRequest);
    }

    public Response<?> getAllUsers(Integer pageNumber, Integer numPerPage,
                                   HttpServletRequest httpServletRequest) {
        User user = TenantContext.getLoggedInUser();
        Long userId = user.getUserId();

        java.sql.Date sentOnPastDate = new java.sql.Date(Calendar.getInstance().getTime().getTime() -
                TimeUnit.DAYS.toMillis(7));

        Page<UserMessage> userMessages =
                userMessageRepository.findByUserIdAndPastDate(userId, sentOnPastDate,
                        PageRequest.of(pageNumber - 1, numPerPage, Sort.by(Sort.Direction.DESC, "sentOn")));
        int totalcount = (int) userMessages.getTotalElements();
        int totalPage = userMessages.getTotalPages();
        List<UserMessage> userMessageList = userMessages.getContent();
        Integer unreadCount =
                userMessageRepository.countAllByUserIdAndState(userId, sentOnPastDate, false);

        Pagination pagination =
                PaginationUtils.getPagination(totalcount, pageNumber, numPerPage, userMessageList.size());

        Response<UserMessageResponseDTO> response = new Response<>();
        response.setResult(new UserMessageResponseDTO(unreadCount, userMessageList));
        response.setPagination(pagination);
        response.setName("FetchSuccessfully");
        response.setMessage(message.get(Message.Common.COMMON_FETCH_SUCCUESS));
        response.setHttpStatusCode(HttpStatus.OK.value());
        response.setCode(ResponseCode.SUCCESS);
        return response;
    }

    @Override
    public Response<?> notificationMarkAsRead(PushMarkAsReadRequestDTO readRequestDTO) {
        if (readRequestDTO.getMessageId() == null && readRequestDTO.getMarkAllAsRead() == null) {
            return new ValidationErrorResponse(Collections.singletonList(
                    new FieldMessage("FieldRequired", "either message Id or markAllAsRead must pass")), null);
        }
        if (readRequestDTO.getMarkAllAsRead() != null && !readRequestDTO.getMarkAllAsRead()) {
            return new ValidationErrorResponse(Collections.singletonList(
                    new FieldMessage("markAllAsRead", "only true value supported")), null);
        }
        User user = TenantContext.getLoggedInUser();
        Long userId = user.getUserId();
        if (readRequestDTO.getMarkAllAsRead() != null && readRequestDTO.getMarkAllAsRead()) {
            Page<UserMessage> userMessages =
                    userMessageRepository.findByUserId(userId,
                            PageRequest.of(0, 9999, Sort.by(Sort.Direction.DESC, "sentOn")));
            List<UserMessage> userMessageList = userMessages.getContent();
            for (UserMessage userMessage : userMessageList) {
                userMessage.setState(true);
            }
            userMessageRepository.saveAll(userMessageList);
            //sending silent push to all browser to user about notification mark as read
            UserMessage userMessage = new UserMessage();
            userMessage.setSentOn(new Date());
            userMessage.setUserId(userId);
            userMessage.setMessageType(WebPushId.MARK_ALL_MESSAGES_AS_READ.getValue());
            push.sendWebPush(userMessage, true);

            return new Response<>(null, null, "SuccessfullySaved",
                    ResponseCode.SUCCESS, "All Message Mark as read");
        }
        if (readRequestDTO.getMessageId() != null) {
            UserMessage userMessage = userMessageRepository
                    .findAllByMessageIdAndUserId(readRequestDTO.getMessageId(), userId);
            if (userMessage != null) {
                if (!userMessage.isState()) {
                    userMessage.setState(true);
                    userMessageRepository.save(userMessage);
                    return new Response<>(readRequestDTO.getMessageId(), null, "SuccessfullySaved",
                            ResponseCode.SUCCESS, "Message read");
                } else {
                    return new ValidationErrorResponse(Collections.singletonList(
                            new FieldMessage("Message", "message has already read")), null);
                }
            } else {
                return new ValidationErrorResponse(Collections.singletonList(
                        new FieldMessage("Message", "message not found")), null);
            }
        }
        return null;
    }

    @Override
    public Response<?> sendWebNotification(Integer pushId, Long userId, String msg) {
        UserMessage userMessage = new UserMessage();
        userMessage.setMessageType(pushId);
        userMessage.setUserId(userId);
        if (msg != null) {
            userMessage.setMessageString(msg);
            userMessage.setMessageTitle("TestMessage");
        }

        userMessage.setSentOn(new Date());
        push.sendWebPush(userMessage);
        return new Response<>(null, null, null, null, "notification sent");
    }

    @Override
    public void retrySendFailedPushNotification() {
        Date conditionalQueryDate =
                DateUtils.minusHours(new Date(), pushRetryBeforeHour);
        Specification<PushNotification> specification = (root, query, cb) -> cb.and(
                cb.lessThan(root.get("attempts"), pushRetryAttemtsValue),
                cb.isNull(root.get("ackTime")),
                cb.greaterThanOrEqualTo(root.get("sendTime"), conditionalQueryDate)
        );
        List<PushNotification> failedPushNotification =
                pushNotificationRepository.findAll(specification);
        if (!ObjectUtils.isEmpty(failedPushNotification)) {
            logger.debug("failedNotification is found regarding ::{}", conditionalQueryDate.toString());
            failedPushNotification.forEach(pushNotification -> push.retryFailedPush(pushNotification));
        }
    }

    @Override
    public Response<?> testPushExecutorIsWorking() {
        push.testPushExecutorIsWorking();
        Response<String> r = new Response<>();
        r.setResult("called async");
        r.setName("Async");
        r.setMessage("called async");
        r.setCode(10);
        return r;
    }

    @Override
    public Response<?> notifySchedulerEnabledOrDesabled() {

        List<Long> deviceIdsLists = deviceRepository.getDeviceIdsByCustomerId();
        NotificationRequest notificationRequest = new NotificationRequest();
        notificationRequest.setDeviceIds(deviceIdsLists);
        notificationRequest.setPushIdIntegers(Collections.singletonList(PushId.ID_CLIENT_UPDATE_YOUR_DEVICE_MODEL.getValue()));
        Response response = sendDeviceNotification(notificationRequest);
        return response;
    }
}
