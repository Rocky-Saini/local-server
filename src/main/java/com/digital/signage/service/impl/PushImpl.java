package com.digital.signage.service.impl;

import com.digital.signage.dto.HandlePushServiceDTO;
import com.digital.signage.models.*;
import com.digital.signage.service.Push;
import com.digital.signage.localserver.push.RabbitMqSendMessage;
import com.digital.signage.localserver.push.RabbitMqWebPushSendMessage;
import com.digital.signage.service.WNSClient;
import com.digital.signage.util.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.AndroidConfig;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.digital.signage.dto.ServerLaunchConfig;
import com.digital.signage.dto.TempDataForPanelStatusForPushImpl;
import com.digital.signage.dto.UserIdFullNameRoleIdAndRoleName;
import com.digital.signage.database.rowmappers.NativeQueryResultsMapper;
import com.digital.signage.models.CustomerLevelPushViaRabbitMq;
import com.digital.signage.repository.CustomerLevelPushViaRabbitMqRepository;
import com.digital.signage.repository.DeviceListenRequestRepository;
import com.digital.signage.repository.DeviceRepository;
import com.digital.signage.repository.PanelRepository;
import com.digital.signage.repository.PanelStatusRepository;
import com.digital.signage.repository.PendingDeviceAndPanelStatusRepository;
import com.digital.signage.repository.PushNotificationRepository;
import com.digital.signage.repository.PushRegistrationIdRepository;
import com.digital.signage.repository.SilentPushEntityRepository;
import com.digital.signage.repository.UserFcmRegistrationIdMappingRepository;
import com.digital.signage.repository.UserMessageRepository;
import com.digital.signage.repository.UserMessageStatusRepository;
import com.digital.signage.repository.UserRepository;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.lang.Nullable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.digital.signage.util.ApplicationConstants.ROLE_PANASONIC_CUST_REP;

@Service
public class PushImpl implements Push {
    public static final Logger logger = LoggerFactory.getLogger(PushImpl.class);

    @Value("${spring.profiles.active}")
    private String activeProfile;

    @Value("${firebase.json.path}")
    private String firebaseJsonPath;

    @Autowired
    private ServerLaunchConfig serverLaunchConfig;

    @Autowired
    private WNSClient wnsClient;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMessageRepository userMessageRepository;

    @Autowired
    private UserMessageStatusRepository userMessageStatusRepository;

    @Autowired
    private DeviceListenRequestRepository deviceListenRequestRepository;

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private PanelRepository panelRepository;

    @Autowired
    private PushNotificationRepository pushNotificationRepository;

    @Autowired
    private PendingDeviceAndPanelStatusRepository pendingDeviceAndPanelStatusRepository;

    @Autowired
    private RabbitMqSendMessage rabbitMqSendMessage;

    @Autowired
    private FcmPushServiceImpl fcmPushServiceImpl;

    @Autowired
    private UserFcmRegistrationIdMappingRepository userFcmRegistrationIdMappingRepository;

    private String firebaseAccount;

    @Autowired
    private SilentPushEntityRepository silentPushEntityRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PushRegistrationIdRepository pushRegistrationIdRepository;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    @Lazy
    private PushNotificationServiceImpl pushNotificationService;

    @Autowired
    private NativeQueryResultsMapper nativeQueryResultsMapper;

    @Autowired
    private PanelStatusRepository panelStatusRepository;

    @Autowired
    private CustomerLevelPushViaRabbitMqRepository customerLevelPushViaRabbitMqRepository;

    @Autowired
    private RabbitMqWebPushSendMessage rabbitMqWebPushSendMessage;

    private boolean useOnlyRabitMqForPushOnDevices() {
        return (ApplicationConstants.SET_ALL_ON_PREMISES_PROFILES.contains(activeProfile)
                || "piqa1".equals(activeProfile))
                && serverLaunchConfig.getUseRabbitMqOnlyForPushOnOnPremiseEnv();
    }

    public boolean initializeFirebase() {
        firebaseAccount = "firebase.json";
        ClassLoader classLoader = getClass().getClassLoader();
        File firebaseJsonFile = new File(firebaseJsonPath);
        boolean isFireBaseInitialized = false;
        boolean isFirebaseJsonFileExists = /*firebaseJsonFile.exists()*/false;
        boolean isOnPremisesServer = "pionpremises2".equalsIgnoreCase(activeProfile);
        // check if file is present
        if (isFirebaseJsonFileExists) {
            logger.info("File found at path = {}", firebaseJsonPath);
            logger.info("initializing firebase App with json @ {}", firebaseJsonPath);
        } else {
            logger.info("firebase json file not found @ {}", firebaseJsonPath);
        }
        if (/*isFirebaseJsonFileExists || */!isOnPremisesServer) {
            try (InputStream is = isFirebaseJsonFileExists ? new FileInputStream(firebaseJsonFile)
                    : classLoader.getResourceAsStream("config/" + firebaseAccount)) {
                Objects.requireNonNull(is);
                if (isFirebaseJsonFileExists) {
                    logger.info("initializing firebase App with json = {}", firebaseJsonFile.getAbsolutePath());
                } else {
                    logger.info("initializing firebase App with fallback json = {}", firebaseAccount);
                }
                FirebaseOptions options = new FirebaseOptions.Builder()
                        .setCredentials(GoogleCredentials.fromStream(is))
                        .build();
                FirebaseApp.initializeApp(options);
                logger.info("firebase App initialized");
                isFireBaseInitialized = true;
            } catch (NullPointerException | IOException e) {
                logger.error("firebase json not present in resources either for this profile : {}",
                        firebaseAccount);
            }
        }
        return isFireBaseInitialized;
    }

    @Override
    public CommonMultiActionResultObject sendPush(
            PushId pushId,
            Map<Long, List<Long>> devicePanels,
            Long currentUserId,
            Long contentId,
            @Nullable Long contentVersion
    ) {
        return sendPush(
                pushId,
                devicePanels,
                currentUserId,
                contentId,
                contentVersion,
                false
        );
    }

    @Override
    public CommonMultiActionResultObject sendPush(
            PushId pushId,
            Map<Long, List<Long>> devicePanels,
            Long currentUserId,
            Long contentId,
            @Nullable Long contentVersion,
            Boolean isAppUpgrade
    ) {
        return sendPush(pushId, devicePanels, currentUserId, contentId, contentVersion, isAppUpgrade,
                null);
    }

    @Override
    public CommonMultiActionResultObject sendPush(
            PushId pushId,
            Map<Long, List<Long>> devicePanels,
            Long currentUserId,
            Long contentId,
            @Nullable Long contentVersion,
            Boolean isAppUpgrade,
            @Nullable BuildOs buildOs
    ) {
        return sendPushForDeviceBuildDownload(
                pushId,
                devicePanels,
                currentUserId,
                contentId,
                contentVersion,
                isAppUpgrade,
                buildOs
        );
    }

    @Async(ApplicationConstants.ASYNC_PUSH_WORKERS)
    @Override
    public void sendPushAsync(
            PushId pushId,
            Map<Long, List<Long>> devicePanels,
            Long currentUserId,
            Long contentId,
            @Nullable Long contentVersion,
            Boolean isAppUpgrade,
            @Nullable BuildOs buildOs
    ) {
        sendPushForDeviceBuildDownload(
                pushId,
                devicePanels,
                currentUserId,
                contentId,
                contentVersion,
                isAppUpgrade,
                buildOs
        );
    }

    private CommonMultiActionResultObject sendPushForDeviceBuildDownload(
            PushId pushId,
            Map<Long, List<Long>> devicePanels,
            Long currentUserId,
            Long contentId,
            @Nullable Long contentVersion,
            Boolean isAppUpgrade,
            @Nullable BuildOs buildOs
    ) {
        List<Long> successList = new ArrayList<>();
        List<Long> notFoundList = new ArrayList<>();
        Map<Device, List<Long>> androidList = new HashMap<>();
        Map<Device, List<Long>> windowsList = new HashMap<>();
        Map<Device, List<Long>> localDevicesList = new HashMap<>();

        List<Long> deviceIds = new ArrayList<>(devicePanels.keySet());

        List<Device> devices = deviceRepository.getDeviceByDeviceIds(deviceIds);

        List<Long> customerIdsOnWhichAndroidPushesNeedToGoViaRabbitMq
                = getCustomerIdsOnWhichAndroidPushesNeedToGoViaRabbitMq(devices);

        Map<Long, Device> mapOfDeviceIdToDevice =
                DeviceAndPanelUtilsKt.convertDeviceListToDeviceIdToDeviceMap(devices);

        for (Map.Entry<Long, List<Long>> devicePanel : devicePanels.entrySet()) {

            Device device = mapOfDeviceIdToDevice.get(devicePanel.getKey());
            if (device != null) {
                if (useOnlyRabitMqForPushOnDevices()
                        || !StringUtils.isEmpty(device.getLocalServerIP())
                        || DeviceOs.DESKTOP.equals(device.getDeviceOs())) {
                    localDevicesList.put(device, devicePanel.getValue());
                } else {
                    if (DeviceOs.WINDOWS.equals(device.getDeviceOs())) {
                        windowsList.put(device, devicePanel.getValue());
                    } else if (DeviceOs.ANDROID.equals(device.getDeviceOs())
                            || DeviceOs.ANDROID_TV.equals(device.getDeviceOs())) {
                        if (customerIdsOnWhichAndroidPushesNeedToGoViaRabbitMq.contains(
                                device.getCustomerId())) {
                            localDevicesList.put(device, devicePanel.getValue());
                        } else {
                            androidList.put(device, devicePanel.getValue());
                        }
                    }
                }
                successList.add(devicePanel.getKey());
            } else {
                notFoundList.add(devicePanel.getKey());
            }
        }
        try {
            if (!androidList.isEmpty()) {
                sendAndroidPush(
                        pushId,
                        androidList,
                        currentUserId,
                        null,
                        contentId,
                        contentVersion,
                        false,
                        null,
                        buildOs
                );
            }
            if (!windowsList.isEmpty()) {
                sendWindowsMultiplePush(
                        pushId,
                        windowsList,
                        currentUserId,
                        contentId,
                        contentVersion,
                        false,
                        null,
                        buildOs
                );
            }
            if (!localDevicesList.isEmpty()) {
                sendLocalMultiplePushToDevice(
                        pushId,
                        localDevicesList,
                        currentUserId,
                        null,
                        null,
                        contentId,
                        contentVersion,
                        false,
                        buildOs
                );
            }
        } catch (Exception e) {
            logger.error("", e);
        }
        CommonMultiActionResultObject commonMultiActionResultObject =
                new CommonMultiActionResultObject();
        commonMultiActionResultObject.setSuccess(successList);
        commonMultiActionResultObject.setNotFound(notFoundList);
        return commonMultiActionResultObject;
    }

    private List<Long> getCustomerIdsOnWhichAndroidPushesNeedToGoViaRabbitMq(List<Device> devices) {
        if (serverLaunchConfig.getSendAndroidPushViaRabbitMqOnly() && !devices.isEmpty()) {
            return customerLevelPushViaRabbitMqRepository.getByCustomerIds(
                            devices.parallelStream()
                                    .map(Device::getCustomerId)
                                    .collect(Collectors.toSet())
                    ).stream()
                    .map(CustomerLevelPushViaRabbitMq::getCustomerId)
                    .collect(Collectors.toList());
        } else {
            return new ArrayList<>(0);
        }
    }

    @Override
    @Async(ApplicationConstants.ASYNC_PUSH_WORKERS)
    public void sendPushToDevices(
            PushId pushId,
            List<Device> devices,
            Long currentUserId,
            Long customerId
    ) {
        Map<Device, List<Long>> androidList = new HashMap<>();
        Map<Device, List<Long>> windowsList = new HashMap<>();
        Map<Device, List<Long>> localDevicesList = new HashMap<>();

        List<Long> customerIdsOnWhichAndroidPushesNeedToGoViaRabbitMq
                = getCustomerIdsOnWhichAndroidPushesNeedToGoViaRabbitMq(devices);

        for (Device device : devices) {
            if (device != null) {
                if (useOnlyRabitMqForPushOnDevices()
                        || !StringUtils.isEmpty(device.getLocalServerIP())
                        || DeviceOs.DESKTOP.equals(device.getDeviceOs())) {
                    localDevicesList.put(device, null);
                } else {
                    if (DeviceOs.ANDROID.equals(device.getDeviceOs())
                            || DeviceOs.ANDROID_TV.equals(device.getDeviceOs())) {
                        if (customerIdsOnWhichAndroidPushesNeedToGoViaRabbitMq.contains(
                                device.getCustomerId())) {
                            localDevicesList.put(device, null);
                        } else {
                            androidList.put(device, null);
                        }
                    } else if (DeviceOs.WINDOWS.equals(device.getDeviceOs())) {
                        windowsList.put(device, null);
                    }
                }
            }
        }
        try {
            if (!windowsList.isEmpty()) {
                sendWindowsMultiplePush(
                        pushId,
                        windowsList,
                        currentUserId,
                        null,
                        null,
                        false,
                        null,
                        null
                );
            }
            if (!androidList.isEmpty()) {
                sendAndroidPush(
                        pushId,
                        androidList,
                        currentUserId,
                        null,
                        null,
                        null,
                        false,
                        null,
                        null
                );
            }
            if (!localDevicesList.isEmpty()) {
                sendMultipleLocalPush(
                        pushId,
                        localDevicesList,
                        currentUserId,
                        null, null,
                        customerId,
                        false
                );
            }
        } catch (Exception e) {
            logger.error("", e);
        }
    }

    @Async(ApplicationConstants.ASYNC_PUSH_WORKERS)
    @Override
    public void sendPushToDevices(
            PushId pushId,
            List<Device> devices,
            Long currentUserId,
            Boolean instantAppUpgrade,
            Long customerId
    ) {
        sendPushToDevices(
                pushId,
                devices,
                currentUserId,
                instantAppUpgrade,
                customerId,
                null
        );
    }

    @Override
    @Async(ApplicationConstants.ASYNC_PUSH_WORKERS)
    public void sendPushToDevices(
            PushId pushId,
            List<Device> devices,
            Long currentUserId,
            Boolean instantAppUpgrade,
            Long customerId,
            Long tpappId
    ) {
        Map<Device, List<Long>> androidList = new HashMap<>();
        Map<Device, List<Long>> windowsList = new HashMap<>();
        Map<Device, List<Long>> localDevicesList = new HashMap<>();

        List<Long> customerIdsOnWhichAndroidPushesNeedToGoViaRabbitMq
                = getCustomerIdsOnWhichAndroidPushesNeedToGoViaRabbitMq(devices);

        for (Device device : devices) {
            if (device != null) {
                Map<Long, List<Long>> devicePanelMap = new HashMap<>();
                devicePanelMap.put(device.getDeviceId(), null);

                if (useOnlyRabitMqForPushOnDevices()
                        || !StringUtils.isEmpty(device.getLocalServerIP())
                        || DeviceOs.DESKTOP.equals(device.getDeviceOs())) {
                    localDevicesList.put(device, null);
                } else {
                    if (DeviceOs.ANDROID.equals(device.getDeviceOs())
                            || DeviceOs.ANDROID_TV.equals(device.getDeviceOs())) {
                        if (customerIdsOnWhichAndroidPushesNeedToGoViaRabbitMq.contains(
                                device.getCustomerId())) {
                            localDevicesList.put(device, null);
                        } else {
                            androidList.put(device, null);
                        }
                    } else if (DeviceOs.WINDOWS.equals(device.getDeviceOs())) {
                        windowsList.put(device, null);
                    }
                }
            }
        }
        try {
            if (!windowsList.isEmpty()) {
                sendWindowsMultiplePush(
                        pushId,
                        windowsList,
                        currentUserId,
                        null,
                        null,
                        instantAppUpgrade,
                        tpappId,
                        null
                );
            }
            if (!androidList.isEmpty()) {
                sendAndroidPush(
                        pushId,
                        androidList,
                        currentUserId,
                        null,
                        null,
                        null,
                        instantAppUpgrade,
                        tpappId,
                        null
                );
            }
            if (!localDevicesList.isEmpty()) {
                sendMultipleLocalPush(
                        pushId,
                        localDevicesList,
                        currentUserId,
                        null,
                        null,
                        customerId,
                        instantAppUpgrade,
                        tpappId
                );
            }
        } catch (Exception e) {
            logger.error("error", e);
        }
    }

    @Override
    public CommonMultiActionResultObject sendPush(PushId pushId, List<Long> deviceList,
                                                  Long currentUserId) {
        if (!PushNotificationServiceImpl.PANEL_PUSH_ID_SET.contains(pushId.getValue())) {
            Map<Long, List<Long>> devicePanelList = new HashMap<>();
            //for (Long deviceId : deviceList) {
            //  devicePanelList.put(deviceId, null);
            //}
            deviceList.parallelStream().forEach(deviceId -> devicePanelList.put(deviceId, null));
            return sendPush(pushId, devicePanelList, currentUserId, null, (Long) null);
        } else {
            CommonMultiActionResultObject commonMultiActionResultObject =
                    new CommonMultiActionResultObject();
            commonMultiActionResultObject.setBadRequest
                    (Collections.singletonList(
                            new FieldMessage("PushId", "Panel Push not supported by this api")));
            return commonMultiActionResultObject;
        }
    }

    @Override
    @Async(ApplicationConstants.ASYNC_PUSH_WORKERS)
    public void sendPush(PushId pushId, List<Long> deviceList, Long currentUserId, Long contentId,
                         Long contentVersion) {
        if (!PushNotificationServiceImpl.PANEL_PUSH_ID_SET.contains(pushId.getValue())) {
            Map<Long, List<Long>> devicePanelList = new HashMap<>();
            for (Long deviceId : deviceList) {
                devicePanelList.put(deviceId, null);
            }
            sendPush(pushId, devicePanelList, currentUserId, contentId, contentVersion);
        }
    }

    @Override
    @Async(ApplicationConstants.ASYNC_PUSH_WORKERS)
    public void sendPush(PushId pushId, Long deviceId, Long panelId, Long currentUserId) {
        Device device = deviceRepository.findByDeviceId(deviceId);
        sendPush(pushId, device, panelId, currentUserId);
    }

    @Override
    @Async(ApplicationConstants.ASYNC_PUSH_WORKERS)
    public void sendPush(PushId pushId, Device device, Long panelId, Long currentUserId) {
        sendPush(pushId, device, panelId, currentUserId, null);
    }

    private boolean useRabbitMq(Device device) {
        boolean useRabbitMq = useOnlyRabitMqForPushOnDevices()
                || !StringUtils.isEmpty(device.getLocalServerIP())
                || DeviceOs.DESKTOP.equals(device.getDeviceOs());

        if (!useRabbitMq) {
            if ((DeviceOs.ANDROID.equals(device.getDeviceOs())
                    || DeviceOs.ANDROID_TV.equals(device.getDeviceOs()))
                    && serverLaunchConfig.getSendAndroidPushViaRabbitMqOnly()) {
                List<CustomerLevelPushViaRabbitMq> temp
                        = customerLevelPushViaRabbitMqRepository.getByCustomerId(device.getCustomerId());
                if (!temp.isEmpty()) {
                    useRabbitMq = true;
                }
            }
        }
        return useRabbitMq;
    }

    @Override
    @Async(ApplicationConstants.ASYNC_PUSH_WORKERS)
    public void sendPush(
            PushId pushId,
            Device device,
            Long panelId,
            Long currentUserId,
            @Nullable String uniqueRequestId
    ) {
        if (device != null) {

            boolean useRabbitMq = useRabbitMq(device);

            if (!useRabbitMq) {
                PushRegistrationIdModel pushRegistrationIdModel =
                        pushRegistrationIdRepository.findPushRegistrationIdModelByDeviceId(
                                device.getDeviceId());

                if (pushRegistrationIdModel != null
                        && pushRegistrationIdModel.getPushRegsitrationId() != null) {
                    String registrationId = pushRegistrationIdModel.getPushRegsitrationId();
                    if (DeviceOs.ANDROID.equals(device.getDeviceOs())
                            || DeviceOs.ANDROID_TV.equals(device.getDeviceOs())) {
                        Map<Device, List<Long>> deviceDetailMap = new HashMap<>();
                        deviceDetailMap.put(device, Collections.singletonList(panelId));
                        sendAndroidPush(
                                pushId,
                                deviceDetailMap,
                                currentUserId,
                                uniqueRequestId,
                                null,
                                null,
                                false,
                                null,
                                null
                        );
                    } else if (DeviceOs.WINDOWS.equals(device.getDeviceOs())) {
                        sendWindowsSinglePush(
                                pushId,
                                device,
                                panelId,
                                currentUserId,
                                uniqueRequestId
                        );
                    }
                }
            } else {
                sendLocalPush(
                        pushId,
                        device,
                        panelId,
                        currentUserId,
                        uniqueRequestId,
                        null,
                        null,
                        null,
                        null,
                        null
                );
            }
        }
    }

    @Override
    @Async(ApplicationConstants.ASYNC_WEB_PUSH_WORKERS)
    public void sendWebPush(UserMessage userMessage) {
        sendWebPush(userMessage, false);
    }

    @Override
    @Async(ApplicationConstants.ASYNC_WEB_PUSH_WORKERS)
    public void sendWebPush(UserMessage userMessage, String registrationId) {
        sendWebPush(userMessage, registrationId, false);
    }

    @Override
    @Async(ApplicationConstants.ASYNC_WEB_PUSH_WORKERS)
    public void sendWebPush(UserMessage userMessage, boolean isSilentPush) {
        try {
            if (isRabbitMqApplicableForWebPush(userMessage.getCustomerId())) {
                logger.debug("going to send web push for this customer::{}", userMessage.getCustomerId());
                rabbitMqWebPushSendMessage.sendWebPushThroughRabbitMq(userMessage, isSilentPush);
                return;
            }
            //fetching all session detail
            userMessage.setState(false);
            List<UserFcmRegistrationIdMapping> userFcmRegistrationIdMappings =
                    userFcmRegistrationIdMappingRepository.findByUserId(userMessage.getUserId());
            List<UserMessageStatus> userMessageStatuses = new ArrayList<>();
            List<UserFcmRegistrationIdMapping> toBeDeletedFcmTokens = new ArrayList<>();
            for (UserFcmRegistrationIdMapping userFcmRegistrationIdMapping : userFcmRegistrationIdMappings) {

                List<UserIdFullNameRoleIdAndRoleName> userAndTheirRoleNames = userRepository
                        .getUserIdFullNameRoleIdAndRoleNameByUserIdAndNotDeleted(userMessage.getUserId());
                if (userAndTheirRoleNames.isEmpty()) {
                    logger.error("User not exist to sent push {}", userMessage.getUserId());
                    return;
                }
                boolean isPanasonicCustRep = userAndTheirRoleNames
                        .stream()
                        .anyMatch(u -> ROLE_PANASONIC_CUST_REP.equals(u.getRoleName()));
                FcmPushResponseModel fcmPushResponseModel = null;
                //in case of panasonicCustRep push should not be sent but userMessage object save in db.
                if (!isPanasonicCustRep ||
                        (userMessage.getCustomerId() != null
                                && userFcmRegistrationIdMapping.getCustomerId() != null
                                && userFcmRegistrationIdMapping.getCustomerId()
                                .equals(userMessage.getCustomerId()))) {
                    UserMessageStatus userMessageStatus = new UserMessageStatus();
                    userMessageStatus.setWebRegistrationId(
                            userFcmRegistrationIdMapping.getRegistrationId());
                    userMessageStatus.setStatus(PushNotificationStatus.SENDING.ordinal());
                    //sending push notification
                    FcmPushServiceImpl.FcmPushResponseData data = fcmPushServiceImpl.sendPush(
                            userFcmRegistrationIdMapping.getRegistrationId(),
                            userMessage,
                            isSilentPush
                    );
                    fcmPushResponseModel = data.fcmPushResponseModel;
                    if (data.silentPushEntity != null) {
                        silentPushEntityRepository.save(data.silentPushEntity);
                    }
                    if (fcmPushResponseModel != null) {
                        if (fcmPushResponseModel.getMessageId() != null) {
                            userMessageStatus.setStatus(PushNotificationStatus.SENT.ordinal());
                        } else if (fcmPushResponseModel.isRegistrationTokenInvalid()) {
                            // added to toBeDeletedFcmTokens
                            toBeDeletedFcmTokens.add(userFcmRegistrationIdMapping);
                            userMessageStatus.setStatus(PushNotificationStatus.FAILURE.ordinal());
                        } else if (fcmPushResponseModel.isFailure()) {
                            userMessageStatus.setStatus(PushNotificationStatus.FAILURE.ordinal());
                        }
                    } else {
                        userMessageStatus.setStatus(PushNotificationStatus.FAILURE.ordinal());
                    }
                    userMessageStatuses.add(userMessageStatus);
                }
            }
            userFcmRegistrationIdMappingRepository.deleteAll(toBeDeletedFcmTokens);
            if (!isSilentPush) {
                userMessageRepository.save(userMessage);
                userMessageStatuses.forEach(f -> f.setUserMessageId(userMessage.getMessageId()));
                userMessageStatusRepository.saveAll(userMessageStatuses);
            }
        } catch (JsonProcessingException e) {
            logger.error("JsonProcessingException", e);
        }
    }

    @Override
    @Async(ApplicationConstants.ASYNC_WEB_PUSH_WORKERS)
    public void sendWebPush(UserMessage userMessage, String registrationId, boolean isSilentPush) {
        if (isRabbitMqApplicableForWebPush(userMessage.getCustomerId())) {
            rabbitMqWebPushSendMessage.sendWebPushThroughRabbitMq(userMessage, isSilentPush);
        } else if (registrationId != null) {
            try {
                UserMessageStatus userMessageStatus = new UserMessageStatus();
                userMessageStatus.setWebRegistrationId(registrationId);
                userMessageStatus.setStatus(PushNotificationStatus.SENDING.ordinal());
                userMessage.setState(false);
                if (!isSilentPush) {
                    userMessageRepository.save(userMessage);
                    userMessageStatusRepository.save(userMessageStatus);
                }
                FcmPushServiceImpl.FcmPushResponseData data = fcmPushServiceImpl.sendPush(
                        registrationId, userMessage, isSilentPush);
                FcmPushResponseModel fcmPushResponseModel = data.fcmPushResponseModel;
                if (data.silentPushEntity != null) {
                    silentPushEntityRepository.save(data.silentPushEntity);
                }
                if (fcmPushResponseModel != null) {
                    if (fcmPushResponseModel.getMessageId() != null) {
                        userMessageStatus.setStatus(PushNotificationStatus.SENT.ordinal());
                    } else if (fcmPushResponseModel.isRegistrationTokenInvalid()) {
                        // added to toBeDeletedFcmTokens
                        List<UserFcmRegistrationIdMapping> list =
                                userFcmRegistrationIdMappingRepository.findByRegistrationId(registrationId);
                        if (list != null && !list.isEmpty()) {
                            userFcmRegistrationIdMappingRepository.deleteAll(list);
                        }
                        userMessageStatus.setStatus(PushNotificationStatus.FAILURE.ordinal());
                    } else if (fcmPushResponseModel.isFailure()) {
                        userMessageStatus.setStatus(PushNotificationStatus.FAILURE.ordinal());
                    }
                }
                if (!isSilentPush) {
                    userMessageStatusRepository.save(userMessageStatus);
                }
            } catch (JsonProcessingException e) {
                logger.error("JsonProcessingException", e);
            }
        }
    }

    @Override
    @Async(ApplicationConstants.ASYNC_PUSH_WORKERS)
    public void sendLocalPush(PushId pushId, Long deviceId, Long panelId, Long currentUserId) {
        Device device = deviceRepository.findByDeviceId(deviceId);
        if (device != null) {
            sendLocalPush(pushId, device, panelId, currentUserId, null, null, null, null, null, null);
        }
    }

    private void sendWindowsSinglePush(PushId pushId, Device device, Long panelId,
                                       Long currentUserId, String uniqueRequestId) {
        //OAuthToken oAuthToken = wnsClient.getOAuthToken(secret, sid);

        PushNotification pushNotification =
                createPushData(pushId, device.getDeviceId(), device.getCustomerId(), currentUserId,
                        uniqueRequestId, null, null, null);
        PushPayload pushPayload =
                PushUtils.createPushPayloadObject(pushNotification.getId(), pushId, device.getDeviceId(),
                        panelId, uniqueRequestId, null, null, false, null);
        //int status = wnsClient.postToWns(oAuthToken, uri, message);
        PushRegistrationIdModel pushRegistrationIdModel =
                pushRegistrationIdRepository.findPushRegistrationIdModelByDeviceId(device.getDeviceId());
        WnsPushStatus status = null;
        if (pushRegistrationIdModel != null
                && pushRegistrationIdModel.getPushRegsitrationId() != null) {
            status =
                    wnsClient.sendPush(pushRegistrationIdModel.getPushRegsitrationId(), pushPayload);
        } else {
            status = new WnsPushStatus(WnsPushStatus.WNS_PUSH_REG_ID_NOT_FOUND_IN_DB, null);
        }
        handlePushServiceResponse(status, device, pushPayload, pushNotification, false);
    }

    private void handlePushServiceResponseForMultipleDevice(
            List<HandlePushServiceDTO> handlePushServiceDTOS, boolean isTestPush) {

        List<Device> deviceList = new ArrayList<>();
        for (HandlePushServiceDTO handlePushServiceDTO : handlePushServiceDTOS) {
            PushNotification pushNotification = handlePushServiceDTO.getPushNotification();
            WnsPushStatus status = handlePushServiceDTO.getWnsPushStatus();
            Device device = handlePushServiceDTO.getDevice();
            PushPayload pushPayload = handlePushServiceDTO.getPushPayload();
            pushNotification.setStatus(PushNotificationStatus.FAILURE);
            pushNotification.setFailureReason(status.toString());
            switch (status.getStatus()) {
                case WnsPushStatus.DELETE_CLIENT_URI:
                    if (!isTestPush) {
                        PushRegistrationIdModel pushRegistrationIdModel =
                                pushRegistrationIdRepository.findPushRegistrationIdModelByDeviceId(
                                        device.getDeviceId());
                        if (pushRegistrationIdModel != null
                                && pushRegistrationIdModel.getPushRegsitrationId() != null) {
                            pushNotificationService.updatePushRegId(null, device.getDeviceId());
                        }
                    }
                    break;
                case WnsPushStatus.WNS_SERVER_ERROR:
                case WnsPushStatus.FAILURE:
                    logger.error("WNS could not send message");
                    break;
                case WnsPushStatus.REQUEST_TOO_LONG:
                    logger.error("WNS REQUEST_TOO_LONG");
                    break;
                case WnsPushStatus.SUCCESS:
                    if (!isTestPush) {
                        pushNotification.setStatus(PushNotificationStatus.SENT);
                    }
                    break;
                case WnsPushStatus.THROTTLE_LIMIT_EXCEEDED:
                    logger.error("WNS THROTTLE_LIMIT_EXCEEDED");
                    break;
                case WnsPushStatus.UNKNOWN:
                    logger.error("WNS Unknown error");
                    break;
                case WnsPushStatus.UNAUTHORIZED:
                    logger.error("WNS more than one Unauthorized response received");
                    break;
            }
            if (!isTestPush) {
                //updatePendingStatusForDevice(device, pushPayload.getPushId());
                deviceList.add(device);
                updatePendingStatusForPanels(pushPayload, device.getCustomerId());
                pushNotificationRepository.save(pushNotification);
            }
        }
        updatePendingStatusForMultipleDevice(deviceList,
                handlePushServiceDTOS.get(0).getPushPayload().getPushId());
    }

    private void handlePushServiceResponse(WnsPushStatus status, Device device,
                                           PushPayload pushPayload, PushNotification pushNotification, boolean isTestPush) {
        pushNotification.setStatus(PushNotificationStatus.FAILURE);
        pushNotification.setFailureReason(status.toString());
        switch (status.getStatus()) {
            case WnsPushStatus.DELETE_CLIENT_URI:
                if (!isTestPush) {
                    PushRegistrationIdModel pushRegistrationIdModel =
                            pushRegistrationIdRepository.findPushRegistrationIdModelByDeviceId(
                                    device.getDeviceId());
                    if (pushRegistrationIdModel != null
                            && pushRegistrationIdModel.getPushRegsitrationId() != null) {
                        pushNotificationService.updatePushRegId(null, device.getDeviceId());
                    }
                }
                break;
            case WnsPushStatus.WNS_SERVER_ERROR:
            case WnsPushStatus.FAILURE:
                logger.error("WNS could not send message");
                break;
            case WnsPushStatus.REQUEST_TOO_LONG:
                logger.error("WNS REQUEST_TOO_LONG");
                break;
            case WnsPushStatus.SUCCESS:
                if (!isTestPush) {
                    pushNotification.setStatus(PushNotificationStatus.SENT);
                }
                break;
            case WnsPushStatus.THROTTLE_LIMIT_EXCEEDED:
                logger.error("WNS THROTTLE_LIMIT_EXCEEDED");
                break;
            case WnsPushStatus.UNKNOWN:
                logger.error("WNS Unknown error");
            case WnsPushStatus.DS_SERVER_ERROR:
                logger.error("DS SERVER ERROR");
                break;
        }
        if (!isTestPush) {
            updatePendingStatusForDevice(device, pushPayload.getPushId());
            updatePendingStatusForPanels(pushPayload, device.getCustomerId());
            pushNotificationRepository.save(pushNotification);
        }
    }

    private void sendWindowsMultiplePush(
            PushId pushId,
            Map<Device, List<Long>> devicePanelMap,
            Long currentUserId,
            Long contentId,
            @Nullable Long contentVersion,
            Boolean instantAppUpgrade,
            Long tpappId,
            @Nullable BuildOs buildOs
    ) {
        //OAuthToken oAuthToken = wnsClient.getAuthToken(secret, sid);
        Map<Device, PushPayload> pushPayloadMap =
                createPushPayload(pushId, devicePanelMap, null, contentId, contentVersion,
                        instantAppUpgrade, tpappId, buildOs);
        List<HandlePushServiceDTO> handlePushServiceDTOS = new ArrayList<>();
        for (Map.Entry<Device, PushPayload> pushPayloadEntry : pushPayloadMap.entrySet()) {
            PushPayload pushPayload = pushPayloadEntry.getValue();
            Device device = pushPayloadEntry.getKey();
            PushNotification pushNotification =
                    createPushData(pushId, pushPayload.getDeviceId(), device.getCustomerId(), currentUserId,
                            null, null, null, pushPayload);
            pushPayload.setMessageId(pushNotification.getId());
            //int status = wnsClient.postToWns(oAuthToken, uri, message);
            WnsPushStatus status = null;
            PushRegistrationIdModel pushRegistrationIdModel =
                    pushRegistrationIdRepository.findPushRegistrationIdModelByDeviceId(
                            device.getDeviceId());
            if (pushRegistrationIdModel != null
                    && pushRegistrationIdModel.getPushRegsitrationId() != null) {
                status = wnsClient.sendPush(pushRegistrationIdModel.getPushRegsitrationId(), pushPayload);
            } else {
                status = new WnsPushStatus(WnsPushStatus.WNS_PUSH_REG_ID_NOT_FOUND_IN_DB, null);
            }
            handlePushServiceDTOS.add(
                    getHandlePushServiceObject(status, device, pushPayload, pushNotification));
            //handlePushServiceResponse(status, device, pushPayload, pushNotification, false);
        }
        handlePushServiceResponseForMultipleDevice(handlePushServiceDTOS, false);
    }

    private HandlePushServiceDTO getHandlePushServiceObject(WnsPushStatus status,
                                                            Device device, PushPayload pushPayload,
                                                            PushNotification pushNotification) {
        return new HandlePushServiceDTO.HandlePushServiceDTOBuilder()
                .setWnsPushStatus(status)
                .setDevice(device)
                .setPushNotification(pushNotification)
                .setPushPayload(pushPayload)
                .build();
    }

    @Override
    public void testAndroidPush(String registrationId, PushPayload pushPayload) {
        String msgData = PushUtils.createPushPayload(pushPayload, objectMapper);
        Message message = Message.builder()
                .putData("payload", msgData)
                .setToken(registrationId)
                .build();

        String response = null;
        try {
            response = FirebaseMessaging.getInstance().sendAsync(message).get();
            if (response != null) {
                List<String> responseAsList = Arrays.stream(response.split("/"))
                        .collect(Collectors.toList());
            }
        } catch (InterruptedException | ExecutionException e) {
            logger.error("", e);
        }
    }

    @Override
    public void testWindowsPush(
            String registrationId,
            PushPayload pushPayload,
            Long customerId,
            Long currentUserId
    ) {
        WnsPushStatus status = wnsClient.sendPush(registrationId, pushPayload);
        Device device = new Device();
        device.setDeviceId(pushPayload.getDeviceId());
        handlePushServiceResponse(
                status,
                device,
                pushPayload,
                new PushNotification(),
                true
        );
    }

    /**
     * use for test API only
     */
    @Override
    public void testForceSendDownloadConfigPushToAndroidPushViaFirebase(
            long customerId
    ) {
        List<Device> allAndroidDevices = deviceRepository.getActiveDevicesByCustomerId();
        if (!allAndroidDevices.isEmpty()) {
            Map<Device, List<Long>> androidList = new HashMap<>(allAndroidDevices.size());
            allAndroidDevices.forEach(device -> androidList.put(device, new ArrayList<>(0)));
            sendAndroidPush(
                    PushId.ID_CLIENT_REDOWNLOAD_CONFIG,
                    androidList,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
            );
        }
    }

    /**
     * use for test API only
     */
    @Override
    public void testForceSendDownloadConfigPushToAndroidPushViaRabbitMq(
            long customerId
    ) {
        List<Device> allAndroidDevices = deviceRepository.getActiveDevicesByCustomerId();
        if (!allAndroidDevices.isEmpty()) {
            allAndroidDevices.forEach(device -> {
                sendLocalPush(
                        PushId.ID_CLIENT_REDOWNLOAD_CONFIG,
                        device,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null
                );
            });
        }
    }

    private void sendAndroidPush(
            PushId pushId,
            Map<Device, List<Long>> androidList,
            Long currentUserId,
            String uniqueRequestId,
            Long contentId,
            @Nullable Long contentVersion,
            Boolean instantAppUpgrade,
            Long tpappId,
            @Nullable BuildOs buildOs
    ) {
        Map<Device, PushPayload> pushPayloadMap = createPushPayload(
                pushId,
                androidList,
                uniqueRequestId,
                contentId,
                contentVersion,
                instantAppUpgrade,
                tpappId,
                buildOs
        );
        List<Device> deviceList = new ArrayList<>();
        for (Map.Entry<Device, PushPayload> pushPayloadEntry : pushPayloadMap.entrySet()) {
            PushPayload pushPayload = pushPayloadEntry.getValue();
            Device device = pushPayloadEntry.getKey();
            PushNotification pushNotification = createPushData(
                    pushId,
                    pushPayload.getDeviceId(),
                    pushPayload.getCustomerId(),
                    currentUserId,
                    uniqueRequestId,
                    null,
                    contentId,
                    pushPayload
            );
            // registrationId will use to send push
            try {
                PushRegistrationIdModel pushRegistrationIdModel =
                        pushRegistrationIdRepository.findPushRegistrationIdModelByDeviceId(
                                device.getDeviceId());
                if (pushRegistrationIdModel != null
                        && pushRegistrationIdModel.getPushRegsitrationId() != null) {
                    String registrationId = pushRegistrationIdModel.getPushRegsitrationId();
                    pushPayload.setMessageId(pushNotification.getId());
                    String msgData = PushUtils.createPushPayload(pushPayload, objectMapper);
                    Message message = Message.builder()
                            //.setNotification(new Notification("title:" + System.currentTimeMillis(),
                            //    "body:" + System.currentTimeMillis()))
                            .putData("payload", msgData)
                            .setAndroidConfig(AndroidConfig.builder()
                                    .setPriority(AndroidConfig.Priority.HIGH)
                                    .build())
                            .setToken(registrationId)
                            .build();

                    String response = FirebaseMessaging.getInstance().sendAsync(message).get();
                    String fcmMessageId = null;
                    if (response != null) {
                        List<String> responseAsList = Arrays.stream(response.split("/"))
                                .collect(Collectors.toList());
                        if (responseAsList.size() == 4) fcmMessageId = responseAsList.get(3);
                    }
                    pushNotification.setStatus(PushNotificationStatus.SENT);
                    pushNotification.setFailureReason("success: " + response);
                    //update device status as pending if applicable push come

                    //set Pending status for device & panels
                    //updatePendingStatusForDevice(device, pushId.getValue());
                    deviceList.add(device);

                    updatePendingStatusForPanels(pushPayload, device.getCustomerId());
                } else {

                    pushNotification.setStatus(PushNotificationStatus.FAILURE);
                    pushNotification.setFailureReason("Push reg id not found in DB");
                }
            } catch (Exception e) {
                if (e.getCause() instanceof FirebaseMessagingException) {
                    FirebaseMessagingException f = (FirebaseMessagingException) e.getCause();
                    if (f.getCause() instanceof com.google.api.client.http.HttpResponseException) {
                        com.google.api.client.http.HttpResponseException h =
                                (com.google.api.client.http.HttpResponseException) f.getCause();
                        if (h.getStatusCode() == 404) {
                            //delete the pushId from db
                            pushNotificationService.updatePushRegId(null, device.getDeviceId());
                        }
                        // commented out because a wrong sender id on server will delete all the reg tokens from DB
                        //if (h.getStatusCode() == 403) {
                        //  // sender id mismatch
                        //  pushNotificationService.updatePushRegId(null, device.getDeviceId());
                        //}
                    }
                }
                pushNotification.setStatus(PushNotificationStatus.FAILURE);
                pushNotification.setFailureReason(e.getMessage());
                logger.error("", e);
            } finally {
                pushNotificationRepository.save(pushNotification);
            }
        }
        if (!deviceList.isEmpty()) {
            updatePendingStatusForMultipleDevice(deviceList, pushId.getValue());
        }
    }

    private void updatePendingStatusForPanels(PushId pushId, Long panelId, Long deviceId,
                                              Long customerId) {
        PushPayload pushPayload = new PushPayload();
        pushPayload.setCustomerId(customerId);
        pushPayload.setPanelId(panelId);
        pushPayload.setDeviceId(deviceId);
        pushPayload.setPushId(pushId.getValue());
        updatePendingStatusForPanels(pushPayload, customerId);
    }

    private void updatePendingStatusForPanels(PushPayload pushPayload, Long customerId) {
        if (PushNotificationServiceImpl.PUSH_IDS_FOR_PANEL_PENDING_STATUS.contains(
                pushPayload.getPushId())) {
            if (pushPayload.getPanelId() != null) {
                Panel panel =
                        panelRepository.findPanelByIdAndCustomerId(pushPayload.getPanelId(), customerId);
                if (panel != null) {
                    PanelStatus latestPanelStatus =
                            panelStatusRepository.getLatestStatusByPanelId(panel.getId());
                    switch (pushPayload.getPushId()) {
                        case 18://ID_CLIENT_INDIVIDUAL_PANELS_OF_DEVICES_ON
                        case 19://ID_CLIENT_INDIVIDUAL_PANELS_OF_DEVICES_OFF
                            notifyToListeningUsersForPanelPendingStatus(
                                    panel,
                                    DataCollectionEnum.PanelStatus.PENDING,
                                    DeviceAndPanelUtilsKt.panelAudioStatusEnumFromLatestPanelStatus(
                                            latestPanelStatus,
                                            pendingDeviceAndPanelStatusRepository.fetchLatestByPanelIdAndPendingDataType(
                                                    panel.getId(),
                                                    DeviceAndPanelPendingType.DB_VALUE_PANEL_AUDIO)
                                    ), panel.getCustomerId(), latestPanelStatus.getTimeOfStatus());
                            break;
                        case 20://ID_CLIENT_TURN_ON_INDIVIDUAL_PANEL_AUDIO
                        case 21://ID_CLIENT_TURN_OFF_INDIVIDUAL_PANEL_AUDIO
                            notifyToListeningUsersForPanelPendingStatus(
                                    panel,
                                    latestPanelStatus.getPanelStatus(),
                                    AudioStatusEnum.PENDING,
                                    customerId,
                                    latestPanelStatus.getTimeOfStatus()
                            );
                            break;
                    }
                }
            }
        }
    }

    @Override
    @Async(ApplicationConstants.ASYNC_PUSH_WORKERS)
    public void sendLocalPush(PushId pushId, Device device, Long panelId, Long currentUserId,
                              String uniqueRequestId, String batchId, Long contentId, @Nullable Long contentVersion,
                              Boolean isInstantAppUpgarde, BuildOs buildOs) {
        PushNotification pushNotification =
                createPushData(pushId, device.getDeviceId(), device.getCustomerId(), currentUserId,
                        uniqueRequestId, batchId, contentId, null);
        updatePendingStatusForDevice(device, pushId.getValue());
        updatePendingStatusForPanels(pushId, panelId, device.getDeviceId(), device.getCustomerId());

        rabbitMqSendMessage.sendLocalPush(pushId, device.getCustomerId(), device.getDeviceId(),
                panelId, uniqueRequestId, contentId, contentVersion, pushNotification,
                isInstantAppUpgarde,
                null, buildOs);

        //rabbitMqSendMessage.sendLocalPush(pushId, pushNotification, device.getDeviceId(), panelId,
        //    uniqueRequestId, contentId, device.getCustomerId());
    }

    @Override
    @Async(ApplicationConstants.ASYNC_PUSH_WORKERS)
    public void sendMultipleLocalPush(
            PushId pushId, Map<Device, List<Long>> deviceIdPanelIdsMap,
            Long currentUserId,
            Long contentId,
            Long contentVersion,
            Long customerId,
            Boolean instantAppUpgrade
    ) {
        sendMultipleLocalPush(
                pushId,
                deviceIdPanelIdsMap,
                currentUserId,
                contentId,
                contentVersion,
                customerId,
                instantAppUpgrade,
                null
        );
    }

    @Override
    @Async(ApplicationConstants.ASYNC_PUSH_WORKERS)
    public void sendMultipleLocalPush(
            PushId pushId,
            Map<Device, List<Long>> deviceIdPanelIdsMap,
            Long currentUserId,
            Long contentId,
            @Nullable Long contentVersion,
            Long customerId,
            Boolean instantAppUpgrade,
            Long tpappId
    ) {
        List<LocalPushRequestModel> models = new ArrayList<>();
        List<Device> deviceList = new ArrayList<>();
        deviceIdPanelIdsMap.forEach((device, panelIds) -> {
            if (panelIds == null || panelIds.size() == 0) {
                // no panels
                models.add(new LocalPushRequestModel(
                        device.getDeviceId(),
                        null,
                        null,
                        contentId,
                        contentVersion,
                        createPushData(
                                pushId,
                                device.getDeviceId(),
                                customerId,
                                currentUserId,
                                null,
                                null,
                                contentId,
                                null
                        ),
                        instantAppUpgrade,
                        tpappId,
                        null
                ));
            } else {
                // one or more panels
                panelIds.forEach(panelId -> {
                            models.add(new LocalPushRequestModel(
                                    device.getDeviceId(),
                                    null,
                                    null,
                                    contentId,
                                    contentVersion,
                                    createPushData(
                                            pushId,
                                            device.getDeviceId(),
                                            customerId,
                                            currentUserId,
                                            null,
                                            null,
                                            contentId,
                                            null
                                    ),
                                    instantAppUpgrade,
                                    tpappId,
                                    null
                            ));
                            updatePendingStatusForPanels(
                                    pushId,
                                    panelId,
                                    device.getDeviceId(),
                                    customerId
                            );
                        }
                );
            }
            deviceList.add(device);
            //updatePendingStatusForDevice(device, pushId.getValue());
        });
        updatePendingStatusForMultipleDevice(deviceList, pushId.getValue());
        rabbitMqSendMessage.sendMultipleLocalPushes(pushId, customerId, models);
    }

    private void sendLocalMultiplePushToDevice(
            PushId pushId,
            Map<Device, List<Long>> localDevicesList,
            Long currentUserId,
            String uniqueRequestId,
            String batchId,
            Long contentId,
            @Nullable Long contentVersion,
            Boolean instantAppUpgrade,
            BuildOs buildOs
    ) {
        for (Map.Entry<Device, List<Long>> devicePanel : localDevicesList.entrySet()) {
            Device device = devicePanel.getKey();
            if (device != null) {
                if (devicePanel.getValue() == null || devicePanel.getValue().isEmpty()) {
                    sendLocalPush(
                            pushId,
                            device,
                            null,
                            currentUserId,
                            uniqueRequestId,
                            batchId,
                            contentId,
                            contentVersion,
                            instantAppUpgrade,
                            buildOs
                    );
                } else {
                    for (Long panelId : devicePanel.getValue()) {
                        sendLocalPush(
                                pushId, device,
                                panelId,
                                currentUserId,
                                uniqueRequestId,
                                batchId,
                                contentId,
                                contentVersion,
                                instantAppUpgrade,
                                buildOs
                        );
                    }
                }
            }
        }
    }

    private PushNotification createPushData(
            PushId pushId,
            Long deviceId,
            Long customerId,
            Long currentUserId,
            String uniqueRequestId,
            String batchId,
            Long contentId,
            PushPayload pushPayload
    ) {
        PushNotification pushNotification = new PushNotification();
        pushNotification.setPushId(pushId.getValue());
        pushNotification.setDeviceId(deviceId);
        pushNotification.setSendTime(new Date());
        pushNotification.setSendBy(currentUserId);
        pushNotification.setStatus(PushNotificationStatus.SENT);
        pushNotification.setAttempts(1);
        pushNotification.setCustomerId(customerId);
        pushNotification.setContentId(contentId);
        pushNotification.setUniqueRequestId(uniqueRequestId);
        pushNotification.setBatchId(batchId);
        pushNotification = pushNotificationRepository.save(pushNotification);
        if (pushPayload != null) {
            pushPayload.setMessageId(pushNotification.getId());
            String payloadJson = PushUtils.createPushPayload(pushPayload, objectMapper);
            pushNotification.setPushPayloadJson(payloadJson);
            pushNotificationRepository.save(pushNotification);
        }
        return pushNotification;
    }

    private Map<Device, PushPayload> createPushPayload(
            PushId pushId,
            Map<Device, List<Long>> androidList,
            String uniqueRequestId,
            Long contentId,
            @Nullable Long contentVersion,
            Boolean instantAppUpgrade,
            Long tpAppId,
            BuildOs buildOs
    ) {
        Map<Device, PushPayload> pushPayloadMap = new HashMap<>();
        // map of per device and registration Id with panel list
        for (Map.Entry<Device, List<Long>> devicePanelMap : androidList.entrySet()) {
            Device device = devicePanelMap.getKey();
            //Iterate map for device and panel
            if (devicePanelMap.getValue() != null && !devicePanelMap.getValue().isEmpty()) {
                for (Long panelId : devicePanelMap.getValue()) {
                    PushPayload pushPayload = PushUtils.createPushPayloadObject(
                            null,
                            pushId,
                            device.getDeviceId(),
                            panelId,
                            uniqueRequestId,
                            contentId,
                            contentVersion,
                            instantAppUpgrade,
                            buildOs
                    );
                    pushPayload.setCustomerId(device.getCustomerId());
                    pushPayloadMap.put(device, pushPayload);
                }
            } else {
                PushPayload pushPayload = PushUtils.createPushPayloadObject(
                        null,
                        pushId,
                        device.getDeviceId(),
                        null,
                        uniqueRequestId,
                        contentId,
                        contentVersion,
                        instantAppUpgrade,
                        buildOs
                );
                pushPayload.setCustomerId(device.getCustomerId());
                pushPayloadMap.put(device, pushPayload);
            }
        }
        return pushPayloadMap;
    }

    private void updatePendingStatusForDevice(Device device, Integer pushId) {
        if (PushNotificationServiceImpl.PUSH_IDS_FOR_DEVICE_STATUS.contains(pushId)) {
            switch (pushId) {
                case 13: //ID_CLIENT_ALL_PANELS_OF_DEVICES_ON
                case 14: //ID_CLIENT_ALL_PANELS_OF_DEVICES_OFF
                {
                    List<Panel> panels = panelRepository.findAllByCustomerIdAndDeviceId(
                            device.getDeviceId()
                    );
                    Map<Long, PanelStatus> panelIdToPanelStatusMap = GenericUtilsKt.convertListToMap(
                            panelStatusRepository.getForDeviceListing(panels.parallelStream()
                                    .map(Panel::getId)
                                    .collect(Collectors.toSet())),
                            PanelStatus::getPanelId
                    );
                    List<PendingDeviceAndPanelStatus> pendingDeviceAndPanelStatusList = new ArrayList<>();
                    panels.forEach(panel -> {
                        PanelStatus latestPanelStatus = panelIdToPanelStatusMap.get(panel.getId());
                        notifyToListeningUsersForPanelPendingStatus(
                                panel,
                                DataCollectionEnum.PanelStatus.PENDING,
                                DeviceAndPanelUtilsKt.panelAudioStatusEnumFromLatestPanelStatus(
                                        latestPanelStatus, null
                                ),
                                device.getCustomerId(),
                                latestPanelStatus.getTimeOfStatus()
                        );
                        pendingDeviceAndPanelStatusList.add(new PendingDeviceAndPanelStatus(
                                null,
                                null,
                                panel.getId(),
                                DeviceAndPanelPendingType.PANEL_ON_OFF,
                                new Date()
                        ));
                    });
                    pendingDeviceAndPanelStatusRepository.saveAll(pendingDeviceAndPanelStatusList);
                }
                break;
                case 15: //ID_CLIENT_TURN_ON_ALL_PANEL_AUDIO
                case 16: //ID_CLIENT_TURN_OFF_ALL_PANEL_AUDIO
                {
                    List<Panel> panels =
                            panelRepository.findAllByDeviceIds(Collections.singleton(device.getDeviceId()));
                    Set<Long> panelIds = panels.parallelStream()
                            .map(Panel::getId)
                            .collect(Collectors.toSet());
                    Map<Long, PanelStatus> panelIdToPanelStatusMap = GenericUtilsKt.convertListToMap(
                            panelStatusRepository.getForDeviceListing(panelIds),
                            PanelStatus::getPanelId
                    );
                    List<PendingDeviceAndPanelStatus> pendingDeviceAndPanelStatusList = new ArrayList<>();
                    panels.forEach(panel -> {
                        PanelStatus latestPanelStatus = panelIdToPanelStatusMap.get(panel.getId());
                        notifyToListeningUsersForPanelPendingStatus(
                                panel,
                                latestPanelStatus.getPanelStatus(),
                                AudioStatusEnum.PENDING,
                                device.getCustomerId(),
                                latestPanelStatus.getTimeOfStatus()
                        );
                        pendingDeviceAndPanelStatusList.add(new PendingDeviceAndPanelStatus(
                                null,
                                null,
                                panel.getId(),
                                DeviceAndPanelPendingType.PANEL_AUDIO,
                                new Date()
                        ));
                    });
                    pendingDeviceAndPanelStatusRepository.saveAll(pendingDeviceAndPanelStatusList);
                }
                break;
                //for device Audio ON/OFF
                case 11://ID_CLIENT_TURN_ON_AUDIO
                case 8: //ID_CLIENT_TURN_OFF_AUDIO
                {
                    pendingDeviceAndPanelStatusRepository.save(
                            new PendingDeviceAndPanelStatus(
                                    null,
                                    device.getDeviceId(),
                                    null,
                                    DeviceAndPanelPendingType.DEVICE_AUDIO,
                                    new Date()
                            )
                    );
                    sendNotificationOfDeviceMuteStatusPendingToListeningUser(device);
                }
                break;
            }
        }
    }

    private TempDataForPanelStatusForPushImpl getTempDataForPanel(List<Device> deviceList) {
        Set<Long> deviceListIds = deviceList
                .stream()
                .map(Device::getDeviceId)
                .collect(Collectors.toSet());
        Map<Long, Device> deviceIdToDeviceMap = GenericUtilsKt.convertListToMap(
                deviceList,
                Device::getDeviceId
        );
        List<Panel> panels = panelRepository.findAllByDeviceIds(deviceListIds);
        Map<Long, Long> panelIdToDeviceIdMap = GenericUtilsKt.convertListToMap(
                panels,
                Panel::getId,
                Panel::getDeviceId
        );
        Map<Long, PanelStatus> panelIdToPanelStatusMap;
        if (panels.size() > 0) {
            Set<Long> allPanelIds = panels.parallelStream()
                    .map(Panel::getId)
                    .collect(Collectors.toSet());
            panelIdToPanelStatusMap = GenericUtilsKt.convertListToMap(
                    panelStatusRepository.getForDeviceListing(allPanelIds),
                    PanelStatus::getPanelId
            );
        } else {
            panelIdToPanelStatusMap = new HashMap<>(0);
        }
        return new TempDataForPanelStatusForPushImpl(
                panels,
                deviceIdToDeviceMap,
                panelIdToDeviceIdMap,
                panelIdToPanelStatusMap
        );
    }

    @Transactional
    public void updatePendingStatusForMultipleDevice(List<Device> deviceList, Integer pushId) {
        if (deviceList == null || deviceList.isEmpty()) {
            return;
        }
        if (PushNotificationServiceImpl.PUSH_IDS_FOR_DEVICE_STATUS.contains(pushId)) {
            switch (pushId) {
                case 13: //ID_CLIENT_ALL_PANELS_OF_DEVICES_ON
                case 14: //ID_CLIENT_ALL_PANELS_OF_DEVICES_OFF
                {
                    TempDataForPanelStatusForPushImpl tempData = getTempDataForPanel(deviceList);
                    List<PendingDeviceAndPanelStatus> pendingDeviceAndPanelStatusList = new ArrayList<>();
                    for (Panel panel : tempData.getPanels()) {
                        try {
                            Device currentDevice = tempData.getDeviceIdToDeviceMap()
                                    .get(tempData.getPanelIdToDeviceIdMap().get(panel.getId()));
                            if (currentDevice != null) {
                                PanelStatus latestPanelStatus =
                                        tempData.getPanelIdToPanelStatusMap().get(panel.getId());
                                AudioStatusEnum audioStatusEnum;
                                if (latestPanelStatus == null || latestPanelStatus.getIsAudioEnabled() == null) {
                                    audioStatusEnum = null;
                                } else {
                                    if (latestPanelStatus.getIsAudioEnabled()) {
                                        audioStatusEnum = AudioStatusEnum.ON;
                                    } else {
                                        audioStatusEnum = AudioStatusEnum.OFF;
                                    }
                                }
                                notifyToListeningUsersForPanelPendingStatus(panel.getId(),
                                        currentDevice.getDeviceId(),
                                        DataCollectionEnum.PanelStatus.PENDING,
                                        audioStatusEnum,
                                        currentDevice.getCustomerId(),
                                        latestPanelStatus == null ? null : latestPanelStatus.getTimeOfStatus()
                                );
                                pendingDeviceAndPanelStatusList.add(new PendingDeviceAndPanelStatus(
                                        null,
                                        null,
                                        panel.getId(),
                                        DeviceAndPanelPendingType.PANEL_ON_OFF,
                                        new Date()
                                ));
                            }
                        } catch (Exception e) {
                            logger.error("", e);
                        }
                    }
                    if (pendingDeviceAndPanelStatusList.size() > 0) {
                        pendingDeviceAndPanelStatusRepository.saveAll(pendingDeviceAndPanelStatusList);
                    }
                }
                break;
                case 15: //ID_CLIENT_TURN_ON_ALL_PANEL_AUDIO
                case 16: //ID_CLIENT_TURN_OFF_ALL_PANEL_AUDIO
                {
                    List<PendingDeviceAndPanelStatus> pendingDeviceAndPanelStatusList = new ArrayList<>();
                    TempDataForPanelStatusForPushImpl tempData = getTempDataForPanel(deviceList);
                    for (Panel panel : tempData.getPanels()) {
                        try {
                            Device currentDevice = panel.getDevice();
                            PanelStatus latestPanelStatus =
                                    tempData.getPanelIdToPanelStatusMap().get(panel.getId());
                            DataCollectionEnum.PanelStatus panelStatus;
                            if (latestPanelStatus == null || latestPanelStatus.getPanelStatus() == null) {
                                panelStatus = null;
                            } else {
                                panelStatus = latestPanelStatus.getPanelStatus();
                            }
                            notifyToListeningUsersForPanelPendingStatus(
                                    panel.getId(),
                                    currentDevice.getDeviceId(),
                                    panelStatus,
                                    AudioStatusEnum.PENDING,
                                    currentDevice.getCustomerId(),
                                    latestPanelStatus == null ? null : latestPanelStatus.getTimeOfStatus()
                            );
                            pendingDeviceAndPanelStatusList.add(new PendingDeviceAndPanelStatus(
                                    null,
                                    null,
                                    panel.getId(),
                                    DeviceAndPanelPendingType.PANEL_AUDIO,
                                    new Date()
                            ));
                        } catch (Exception e) {
                            logger.error("", e);
                        }
                    }
                    if (pendingDeviceAndPanelStatusList.size() > 0) {
                        pendingDeviceAndPanelStatusRepository.saveAll(pendingDeviceAndPanelStatusList);
                    }
                }
                break;
                // for device Audio ON/OFF
                case 11://ID_CLIENT_TURN_ON_AUDIO
                case 8: //ID_CLIENT_TURN_OFF_AUDIO
                {
                    List<PendingDeviceAndPanelStatus> pendingDeviceAndPanelStatusList = new ArrayList<>();
                    deviceList.forEach(device -> {
                        pendingDeviceAndPanelStatusList.add(new PendingDeviceAndPanelStatus(
                                null,
                                device.getDeviceId(),
                                null,
                                DeviceAndPanelPendingType.DEVICE_AUDIO,
                                new Date()
                        ));
                        sendNotificationOfDeviceMuteStatusPendingToListeningUser(device);
                    });
                    if (pendingDeviceAndPanelStatusList.size() > 0) {
                        pendingDeviceAndPanelStatusRepository.saveAll(pendingDeviceAndPanelStatusList);
                    }
                }
                break;
            }
        }
    }

  /*private List<DevicePanelMapDTO> getDevicePanelMapFromDB(
      boolean isAudioEnabledRequired,
      Set<Long> deviceListIds
  ) {
    String queryString = null;
    List<Panel> panels = panelRepository.findAllByDeviceIds(deviceListIds);
    Map<Long, Map<Long, PanelStatus>> data = new HashMap<>();
    Map<Long, PanelStatus> panelIdToPanelStatusMap = null;
    if (panels.size() > 0) {
      Set<Long> allPanelIds = panels.parallelStream()
          .map(Panel::getId)
          .collect(Collectors.toSet());
      panelIdToPanelStatusMap = GenericUtilsKt.convertListToMap(
          panelStatusRepository.getForDeviceListing(allPanelIds),
          PanelStatus::getPanelId
      );
    } else {
      panelIdToPanelStatusMap = new HashMap<>(0);
    }
    for (Panel panel : panels) {
      Map<Long, PanelStatus> panelStatusMap =
          data.getOrDefault(panel.getDeviceId(), new HashMap<>());
    }
    if (isAudioEnabledRequired) {
      queryString =
          "SELECT d.deviceId, JSON_OBJECTAGG(p.panelId, p.isAudioEnabled) AS json FROM device d, panel p "
              + " WHERE d.deviceId IN (:deviceIds) AND p.customerId = d.customerId AND d.deviceId=p.deviceId AND p.status != 3 "
              + " GROUP BY d.deviceId";
    } else {
      queryString =
          "SELECT d.deviceId, JSON_OBJECTAGG(p.panelId,p.panelStatus) AS json FROM device d,panel p "
              + " WHERE d.deviceId IN (:deviceIds) AND p.customerId = d.customerId AND d.deviceId=p.deviceId AND p.status != 3 "
              + " GROUP BY d.deviceId";
    }

    Query query = entityManager.createNativeQuery(queryString);
    query.setParameter("deviceIds", deviceListIds);

    return (List<DevicePanelMapDTO>) nativeQueryResultsMapper.map(query.getResultList(),
        DevicePanelMapDTO.class);
  }*/

    /**
     * sets pending status either on panel audio
     * or panel (on/off)
     */
    private void notifyToListeningUsersForPanelPendingStatus(Panel panelObj,
                                                             DataCollectionEnum.PanelStatus panelStatus, AudioStatusEnum audioStatusEnum,
                                                             Long customerId, Date latestPanelTimeOfStatus) {
        //sort panel status on the basis of timestamp to find latest status
        PanelUpdateSilentPushDTO userMessage = new PanelUpdateSilentPushDTO();
        userMessage.setMessageType(WebPushId.PANEL_UPDATES.getValue());
        userMessage.setCustomerId(customerId);
        userMessage.setSentOn(new Date());

        PanelStatusPushDTO panelStatusPushDTO = new PanelStatusPushDTO(
                panelObj.getDeviceId(),
                panelObj.getId(),
                latestPanelTimeOfStatus,
                audioStatusEnum,
                panelStatus);
        userMessage.setPanelStatus(panelStatusPushDTO);

        List<DeviceListenRequest> deviceListenRequests = deviceListenRequestRepository
                .findAllByDeviceIdAndState(panelObj.getDevice().getDeviceId());
        for (DeviceListenRequest deviceListenRequest : deviceListenRequests) {
            sendWebPush(userMessage, deviceListenRequest.getFbRegistrationId(), true);
        }
    }

    /**
     * notifies only pending status
     */
    private void notifyToListeningUsersForPanelPendingStatus(Long panelId, Long deviceId,
                                                             DataCollectionEnum.PanelStatus panelStatus, AudioStatusEnum audioStatusEnum,
                                                             Long customerId, Date latestTimeOfPanelStatus) {
        //sort panel status on the basis of timestamp to find latest status
        PanelUpdateSilentPushDTO userMessage = new PanelUpdateSilentPushDTO();
        userMessage.setMessageType(WebPushId.PANEL_UPDATES.getValue());
        userMessage.setCustomerId(customerId);
        userMessage.setSentOn(new Date());

        PanelStatusPushDTO panelStatusPushDTO = new PanelStatusPushDTO(
                deviceId,
                panelId,
                latestTimeOfPanelStatus,
                audioStatusEnum,
                panelStatus);
        userMessage.setPanelStatus(panelStatusPushDTO);

        List<DeviceListenRequest> deviceListenRequests = deviceListenRequestRepository
                .findAllByDeviceIdAndState(deviceId);
        for (DeviceListenRequest deviceListenRequest : deviceListenRequests) {
            sendWebPush(userMessage, deviceListenRequest.getFbRegistrationId(), true);
        }
    }

    private boolean sendNotificationOfDeviceMuteStatusPendingToListeningUser(Device device) {
        //sending notification to those user who are listening to these device.
        List<DeviceListenRequest> deviceListenRequests = deviceListenRequestRepository
                .findAllByDeviceIdAndState(device.getDeviceId());
        for (DeviceListenRequest deviceListenRequest : deviceListenRequests) {
            DeviceMuteUnmuteSilentPushDTO userMessage = new DeviceMuteUnmuteSilentPushDTO();
            userMessage.setEntityType(EntityTypeEnum.DEVICE);
            userMessage.setMessageType(WebPushId.DEVICE_MUTE_UNMUTED.getValue());
            userMessage.setEntityId(device.getDeviceId());
            userMessage.setSentOn(new Date());
            userMessage.setCustomerId(device.getCustomerId());
            userMessage.setUserId(deviceListenRequest.getUserId());
            userMessage.setIsAudioEnabled(AudioStatusEnum.PENDING);
            userMessage.setTimeOfDeviceStatus(new Timestamp(new Date().getTime()));
            sendWebPush(userMessage, deviceListenRequest.getFbRegistrationId(), true);
        }
        return true;
    }

    @Override
    public void retryFailedPush(PushNotification pushNotification) {
        try {
            PushPayload pushPayload =
                    objectMapper.readValue(pushNotification.getPushPayloadJson(), PushPayload.class);
            Device device = deviceRepository.findByDeviceId(pushNotification.getDeviceId());
            if (device == null) return;

            boolean useRabbitMq = useRabbitMq(device);

            if (!useRabbitMq) {
                PushRegistrationIdModel pushRegistrationIdModel =
                        pushRegistrationIdRepository.findPushRegistrationIdModelByDeviceId(
                                device.getDeviceId());

                if (pushRegistrationIdModel != null
                        && pushRegistrationIdModel.getPushRegsitrationId() != null) {
                    String registrationId = pushRegistrationIdModel.getPushRegsitrationId();
                    if (DeviceOs.ANDROID.equals(device.getDeviceOs())
                            || DeviceOs.ANDROID_TV.equals(device.getDeviceOs())) {
                        retryAndroidPushMessage(pushPayload, registrationId, pushNotification);
                    } else if (DeviceOs.WINDOWS.equals(device.getDeviceOs())) {
                        retryWindowsPushMessage(registrationId, pushPayload, pushNotification);
                    }
                } else {
                    pushNotification.setStatus(PushNotificationStatus.FAILURE);
                    pushNotification.setFailureReason("Push reg id not found in DB");
                }
            } else {
                retryLocalPush(pushNotification, device, pushPayload);
            }
        } catch (Exception e) {
            pushNotification.setStatus(PushNotificationStatus.FAILURE);
            pushNotification.setFailureReason(e.getMessage());
        } finally {
            Integer noOfAttempts = pushNotification.getAttempts();
            noOfAttempts = (noOfAttempts == null) ? 1 : ++noOfAttempts;
            pushNotification.setAttempts(noOfAttempts);
            pushNotificationRepository.save(pushNotification);
        }
    }

    @Override
    public void testPushExecutorIsWorking() {
        logger.debug("push-executor-is-working - threadName={}", Thread.currentThread().getName());
    }

    private void retryAndroidPushMessage(PushPayload pushPayload, String registrationId,
                                         PushNotification pushNotification) throws ExecutionException, InterruptedException {
        String msgData = PushUtils.createPushPayload(pushPayload, objectMapper);
        Message message = Message.builder()
                .putData("payload", msgData)
                .setAndroidConfig(AndroidConfig.builder()
                        .setPriority(AndroidConfig.Priority.HIGH)
                        .build())
                .setToken(registrationId)
                .build();
        String response = FirebaseMessaging.getInstance().sendAsync(message).get();
        String fcmMessageId = null;
        if (response != null) {
            List<String> responseAsList = Arrays.stream(response.split("/"))
                    .collect(Collectors.toList());
            if (responseAsList.size() == 4) fcmMessageId = responseAsList.get(3);
        }
        pushNotification.setStatus(PushNotificationStatus.SENT);
        pushNotification.setFailureReason("success: " + response);
        //update device status as pending if applicable push come
    }

    private void retryWindowsPushMessage(String registrationId, PushPayload pushPayload,
                                         PushNotification pushNotification) {
        WnsPushStatus status = wnsClient.sendPush(registrationId, pushPayload);
        if (!org.springframework.util.StringUtils.isEmpty(registrationId)) {
            if (WnsPushStatus.SUCCESS == status.getStatus()) {
                pushNotification.setStatus(PushNotificationStatus.SENT);
                pushNotification.setFailureReason("Success");
            } else {
                pushNotification.setStatus(PushNotificationStatus.FAILURE);
                pushNotification.setFailureReason(status.toString());
            }
        } else {
            pushNotification.setStatus(PushNotificationStatus.FAILURE);
            pushNotification.setFailureReason(
                    new WnsPushStatus(WnsPushStatus.WNS_PUSH_REG_ID_NOT_FOUND_IN_DB, null).toString());
        }
    }

    private void retryLocalPush(
            PushNotification pushNotification,
            Device device,
            PushPayload pushPayload
    ) {
        PushId pushId = PushId.valueOf(pushNotification.getPushId());
        updatePendingStatusForDevice(device, pushNotification.getPushId());
        updatePendingStatusForPanels(pushId, pushPayload.getPanelId(),
                device.getDeviceId(), device.getCustomerId());
        LocalPushRequestModel localPushRequestModel =
                new LocalPushRequestModel(
                        pushNotification.getDeviceId(),
                        pushPayload.getPanelId(),
                        pushNotification.getUniqueRequestId(),
                        pushNotification.getContentId(),
                        pushPayload.getContentVersion(),
                        pushNotification,
                        pushPayload.getInstantAppUpgrade(),
                        null,
                        pushPayload.getBuildOs()
                );
        String msg = PushUtils.createPushPayload(pushPayload, objectMapper);
        if (msg == null) throw new RuntimeException("Message cannot be null");
        rabbitMqSendMessage.sendLocalPush(pushId, device.getCustomerId(), localPushRequestModel, msg);
    }

    private boolean isRabbitMqApplicableForWebPush(@Nullable Long customerId) {
        return PushModeForAngular.RABBIT_MQ.equals(serverLaunchConfig.getPushModeForAngular());
    }
}

