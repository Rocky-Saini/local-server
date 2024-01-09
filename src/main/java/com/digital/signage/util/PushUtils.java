package com.digital.signage.util;

import com.digital.signage.dto.OnPremiseMessageDTO;
import com.digital.signage.models.PushPayload;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author -Ravi Kumar created on 1/25/2023 1:07 AM
 * @project - Digital Sign-edge
 */
public class PushUtils {
    private PushUtils() {
        // Throw an exception if this ever is called
        throw new AssertionError("Instantiating utility class not allowed.");
    }

    private static final Logger logger = LoggerFactory.getLogger(PushUtils.class);
    public static final Map<Integer, Long> PUSH_EXPIRY;

    static {
        //missing ID are either for lifetime or not exist.
        Map<Integer, Long> temp = new HashMap<>();
        temp.put(1, 5 * 60 * 1000L);
        temp.put(8, 5 * 60 * 1000L);
        temp.put(9, 8 * 60 * 60 * 1000L);
        //change config should expire in  24 hours
        temp.put(10, 24 * 60 * 60 * 1000L);
        temp.put(11, 5 * 60 * 1000L);
        temp.put(13, 5 * 60 * 1000L);
        temp.put(14, 5 * 60 * 1000L);
        temp.put(15, 5 * 60 * 1000L);
        temp.put(16, 5 * 60 * 1000L);
        temp.put(17, 5 * 60 * 1000L);
        temp.put(18, 5 * 60 * 1000L);
        temp.put(19, 5 * 60 * 1000L);
        temp.put(20, 5 * 60 * 1000L);
        temp.put(21, 5 * 60 * 1000L);
        temp.put(22, 5 * 60 * 1000L);
        temp.put(23, 5 * 60 * 1000L);
        temp.put(24, 5 * 60 * 1000L);
        temp.put(25, 30 * 60 * 1000L);
        temp.put(26, 5 * 60 * 1000L);

        PUSH_EXPIRY = Collections.unmodifiableMap(temp);
    }

    public static String createPushPayload(Long messageId, PushId pushId, Long DeviceId, Long PanelId,
                                           ObjectMapper objectMapper) {
        try {
            PushPayload pushPayload = new PushPayload();
            pushPayload.setMessageId(messageId);
            pushPayload.setPushId(pushId.getValue());
            pushPayload.setDeviceId(DeviceId);
            pushPayload.setPanelId(PanelId);
            pushPayload.setSentOn(new Date());
            if (PUSH_EXPIRY.containsKey(pushId.getValue())) {
                pushPayload.setExpiryTime(PUSH_EXPIRY.get(pushId.getValue()));
            }
            return objectMapper.writeValueAsString(pushPayload);
        } catch (JsonProcessingException e) {
            logger.error("", e);
            return null;
        }
    }

    public static PushPayload createPushPayloadObject(Long messageId, PushId pushId, Long deviceId,
                                                      Long panelId, String uniqueRequestId, Long contentId, @Nullable Long contentVersion,
                                                      Boolean instantAppUpgrade, BuildOs buildOs) {
        PushPayload pushPayload = new PushPayload();
        pushPayload.setPushId(pushId.getValue());
        pushPayload.setMessageId(messageId);
        pushPayload.setDeviceId(deviceId);
        pushPayload.setPanelId(panelId);
        pushPayload.setSentOn(new Date());
        pushPayload.setUniqueRequestId(uniqueRequestId);
        pushPayload.setContentId(contentId);
        pushPayload.setContentVersion(contentVersion);
        pushPayload.setInstantAppUpgrade(instantAppUpgrade);
        pushPayload.setBuildOs(buildOs);
        if (PUSH_EXPIRY.containsKey(pushId.getValue())) {
            pushPayload.setExpiryTime(PUSH_EXPIRY.get(pushId.getValue()));
        }
        return pushPayload;
    }

    public static PushPayload createPushPayloadObject(
            Long messageId,
            PushId pushId,
            Long deviceId,
            Long panelId,
            Long contentId
    ) {
        PushPayload pushPayload = new PushPayload();
        pushPayload.setPushId(pushId.getValue());
        pushPayload.setMessageId(messageId);
        pushPayload.setDeviceId(deviceId);
        pushPayload.setPanelId(panelId);
        pushPayload.setSentOn(new Date());
        pushPayload.setContentId(contentId);
        if (PUSH_EXPIRY.containsKey(pushId.getValue())) {
            pushPayload.setExpiryTime(PUSH_EXPIRY.get(pushId.getValue()));
        }
        return pushPayload;
    }

    public static String createPushPayload(PushPayload pushPayload, ObjectMapper objectMapper) {
        try {
            return objectMapper.writeValueAsString(pushPayload);
        } catch (JsonProcessingException e) {
            logger.error("", e);
            return null;
        }
    }

    public static String createOnPremiseServerPayload(OnPremiseMessageDTO onPremiseMessageDTO,
                                                      ObjectMapper objectMapper) {
        try {
            return objectMapper.writeValueAsString(onPremiseMessageDTO);
        } catch (JsonProcessingException e) {
            logger.error("", e);
            return null;
        }
    }
}
