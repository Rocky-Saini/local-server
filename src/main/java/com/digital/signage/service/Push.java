package com.digital.signage.service;

import com.digital.signage.models.*;
import com.digital.signage.util.ApplicationConstants;
import com.digital.signage.util.BuildOs;
import com.digital.signage.util.PushId;
import org.springframework.scheduling.annotation.Async;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;

/**
 * @author -Ravi Kumar created on 12/28/2022 2:01 AM
 * @project - Digital Sign-edge
 */
public interface Push {

    boolean initializeFirebase();

    /**
     * @param pushId one of the pushIds from {@link PushId}
     * @param devicePanels Map of deviceIds with panel list
     */
    CommonMultiActionResultObject sendPush(PushId pushId, Map<Long, List<Long>> devicePanels,
                                           Long currentUserId, Long contentId, @Nullable Long contentVersion);

    CommonMultiActionResultObject sendPush(PushId pushId, Map<Long, List<Long>> devicePanels,
                                           Long currentUserId, Long contentId, @Nullable Long contentVersion, Boolean isAppUpgrade);

    CommonMultiActionResultObject sendPush(PushId pushId, Map<Long, List<Long>> devicePanels,
                                           Long currentUserId, Long contentId, @Nullable Long contentVersion, Boolean isAppUpgrade,
                                           BuildOs buildOs);

    @Async(ApplicationConstants.ASYNC_PUSH_WORKERS)
    void sendPushAsync(PushId pushId, Map<Long, List<Long>> devicePanels,
                       Long currentUserId, Long contentId, @Nullable Long contentVersion, Boolean isAppUpgrade,
                       BuildOs buildOs);

    CommonMultiActionResultObject sendPush(PushId pushId, List<Long> deviceList,
                                           Long currentUserId);

    @Async(ApplicationConstants.ASYNC_PUSH_WORKERS)
    void sendPush(PushId pushId, List<Long> deviceList, Long currentUserId, Long contentId,
                  Long contentVersion);

    @Async(ApplicationConstants.ASYNC_PUSH_WORKERS)
    void sendPushToDevices(PushId pushId, List<Device> devices, Long currentUserId, Long customerId);

    @Async(ApplicationConstants.ASYNC_PUSH_WORKERS)
    void sendPushToDevices(PushId pushId, List<Device> devices, Long currentUserId,
                           Boolean instantAppUpgrade, Long customerId);

    @Async(ApplicationConstants.ASYNC_PUSH_WORKERS)
    void sendPushToDevices(PushId pushId, List<Device> devices, Long currentUserId,
                           Boolean instantAppUpgrade, Long customerId, Long tpappId);

    /**
     * @param pushId one of the pushIds from {@link PushId}
     *  * @param deviceIds List of deviceIds
     */
    //void retryFailedPush(int pushId, Integer... deviceIds);
    @Async(ApplicationConstants.ASYNC_PUSH_WORKERS)
    void sendPush(PushId pushId, Long deviceId, Long panelId, Long currentUserId);

    @Async(ApplicationConstants.ASYNC_PUSH_WORKERS)
    void sendPush(PushId pushId, Device device, Long panelId, Long currentUserId);

    @Async(ApplicationConstants.ASYNC_PUSH_WORKERS)
    void sendPush(PushId pushId, Device device, Long panelId, Long currentUserId,
                  String uniqueRequestId);

    @Async(ApplicationConstants.ASYNC_WEB_PUSH_WORKERS)
    void sendWebPush(UserMessage userMessage);

    @Async(ApplicationConstants.ASYNC_WEB_PUSH_WORKERS)
    void sendWebPush(UserMessage userMessage, boolean isSilentPush);

    @Async(ApplicationConstants.ASYNC_WEB_PUSH_WORKERS)
    void sendWebPush(UserMessage userMessage, String registrationId);

    @Async(ApplicationConstants.ASYNC_WEB_PUSH_WORKERS)
    void sendWebPush(UserMessage userMessage, String registrationId, boolean isSilentPush);

    @Async(ApplicationConstants.ASYNC_PUSH_WORKERS)
    void sendLocalPush(PushId pushId, Long deviceId, Long panelId, Long currentUserId);

    @Async(ApplicationConstants.ASYNC_PUSH_WORKERS)
    void sendLocalPush(PushId pushId, Device device, Long panelId, Long currentUserId,
                       String uniqueRequestId, String batchId, Long contentId, @Nullable Long contentVersion,
                       Boolean isInstantAppUpgrade, BuildOs buildOs);

    @Async(ApplicationConstants.ASYNC_PUSH_WORKERS)
    void sendMultipleLocalPush(PushId pushId, Map<Device, List<Long>> deviceIdPanelIdsMap,
                               Long currentUserId, Long contentId, @Nullable Long contentVersion, Long customerId,
                               Boolean isInstantAppUpgrade);

    @Async(ApplicationConstants.ASYNC_PUSH_WORKERS)
    void sendMultipleLocalPush(PushId pushId, Map<Device, List<Long>> deviceIdPanelIdsMap,
                               Long currentUserId, Long contentId, @Nullable Long contentVersion, Long customerId,
                               Boolean instantAppUpgrade, Long tpappId);

    @Async(ApplicationConstants.ASYNC_PUSH_WORKERS)
    void testAndroidPush(String registrationId, PushPayload pushPayload);

    @Async(ApplicationConstants.ASYNC_PUSH_WORKERS)
    void testWindowsPush(String registrationId, PushPayload pushPayload, Long customerId,
                         Long currentUserId);

    @Async(ApplicationConstants.ASYNC_PUSH_WORKERS)
    void retryFailedPush(PushNotification pushNotification);

    @Async(ApplicationConstants.ASYNC_PUSH_WORKERS)
    void testPushExecutorIsWorking();

    void testForceSendDownloadConfigPushToAndroidPushViaFirebase(long customerId);

    void testForceSendDownloadConfigPushToAndroidPushViaRabbitMq(long customerId);
}
