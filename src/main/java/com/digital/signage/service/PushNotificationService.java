package com.digital.signage.service;

import com.digital.signage.dto.PushMarkAsReadRequestDTO;
import com.digital.signage.dto.PushRegistrationDTO;
import com.digital.signage.models.AcknowledgeRequestModel;
import com.digital.signage.models.NotificationRequest;
import com.digital.signage.models.PanelNotificationRequest;
import com.digital.signage.models.Response;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

@Service
public interface PushNotificationService extends BaseService {

    Response<?> saveDeviceRegistration(PushRegistrationDTO pushRegistrationDTO,
                                       HttpServletRequest request, int apiVersion);

    Response<?> sendDeviceNotification(NotificationRequest notificationRequest);

    Response<?> sendPanelsNotification(PanelNotificationRequest panelNotificationRequest);

    Response<?> sendPushAcknowledge(AcknowledgeRequestModel acknowledgeRequestModel,
                                    HttpServletRequest request);

    Response<?> saveWebRegistration(Map<String, String> pushWebRegistrationParamMap);

    Response<?> getAllUserNotification(Integer pageNumber, Integer numPerPage,
                                       HttpServletRequest httpServletRequest);

    Response<?> notificationMarkAsRead(PushMarkAsReadRequestDTO readRequestDTO);

    Response<?> sendWebNotification(Integer pushId, Long userId, String msg);

    void retrySendFailedPushNotification();

    Response<?> testPushExecutorIsWorking();

    Response<?> notifySchedulerEnabledOrDesabled();
}