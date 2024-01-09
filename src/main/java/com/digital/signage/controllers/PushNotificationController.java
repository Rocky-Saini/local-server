package com.digital.signage.controllers;

import com.digital.signage.constants.UrlPaths;
import com.digital.signage.dto.PushMarkAsReadRequestDTO;
import com.digital.signage.dto.PushRegistrationDTO;
import com.digital.signage.models.AcknowledgeRequestModel;
import com.digital.signage.models.NotificationRequest;
import com.digital.signage.models.PanelNotificationRequest;
import com.digital.signage.models.Response;
import com.digital.signage.service.BaseService;
import com.digital.signage.service.PushNotificationService;
import com.digital.signage.util.WebPushId;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/")
public class PushNotificationController extends BaseController {
    @Autowired
    private PushNotificationService pushNotificationService;

    @Override protected BaseService getBaseService() {
        return pushNotificationService;
    }

    //@PreAuthorize("hasAuthority('ALL_DEVICES')")
    @RequestMapping(value = UrlPaths.PATH_PUSH_DEVICE, method = RequestMethod.PUT)
    public synchronized Response<String> saveDeviceRegistration(
            @RequestBody PushRegistrationDTO pushRegistrationDTO,
            HttpServletRequest request, HttpServletResponse httpServletResponse) {
        Response response =
                pushNotificationService.saveDeviceRegistration(pushRegistrationDTO, request, 1);
        updateHttpStatusCode(response, httpServletResponse);
        return response;
    }

    //@PreAuthorize("hasAuthority('ALL_DEVICES')")
    @RequestMapping(value = UrlPaths.PATH_PUSH_V2_DEVICE, method = RequestMethod.PUT)
    public synchronized Response<String> saveDeviceRegistrationV2(
            @RequestBody PushRegistrationDTO pushRegistrationDTO,
            HttpServletRequest request, HttpServletResponse httpServletResponse) {
        Response response =
                pushNotificationService.saveDeviceRegistration(pushRegistrationDTO, request, 2);
        updateHttpStatusCode(response, httpServletResponse);
        return response;
    }

    @PreAuthorize("hasAuthority('EDIT_DEVICE')")
    @RequestMapping(value = "/push/devices/messages", method = RequestMethod.POST)
    public synchronized Response<?> sendDeviceNotification(
            @RequestBody NotificationRequest notificationRequest,
            HttpServletResponse httpServletResponse) {
        return updateHttpStatusCode(
                pushNotificationService.sendDeviceNotification(notificationRequest), httpServletResponse);
    }

    @PreAuthorize("hasAuthority('EDIT_DEVICE')")
    @PostMapping(value = "/push/panels/messages")
    public synchronized Response<?> sendPanelNotification(
            @RequestBody PanelNotificationRequest panelNotificationRequest) {
        return pushNotificationService.sendPanelsNotification(panelNotificationRequest);
    }

    @PreAuthorize("hasAuthority('ALL_DEVICES')")
    @PutMapping(value = UrlPaths.PATH_PUSH_ACK)
    public synchronized Response<?> sendPushAcknowledge(
            @RequestBody AcknowledgeRequestModel acknowledgeRequestModel, HttpServletRequest request) {
        return pushNotificationService.sendPushAcknowledge(acknowledgeRequestModel, request);
    }

    @PreAuthorize("hasAuthority('ALL_USERS')")
    @RequestMapping(value = UrlPaths.PATH_WEB_NOTIFICATIONS_REGISTER, method = RequestMethod.PUT)
    public synchronized Response<?> saveWebRegistration(
            @RequestBody Map<String, String> pushWebRegsitrationParamMap,
            HttpServletResponse httpServletResponse) {
        Response response = pushNotificationService.saveWebRegistration(pushWebRegsitrationParamMap);
        updateHttpStatusCode(response, httpServletResponse);
        return response;
    }

    @PreAuthorize("hasAuthority('ALL_USERS')")
    @RequestMapping(value = UrlPaths.PATH_WEB_NOTIFICATIONS, method = RequestMethod.GET)
    public Response<?> getAllUserNotification(
            @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
            @RequestParam(value = "numPerPage", required = false) Integer numPerPage,
            HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest) {
        Response response = pushNotificationService.getAllUserNotification(pageNumber, numPerPage,httpServletRequest);
        updateHttpStatusCode(response, httpServletResponse);
        return response;
    }

    @GetMapping(value = UrlPaths.PATH_PUSH_WEB_KEYS)
    public Response<Map<String, Integer>> getWebPushEnum() {
        return new Response<>(WebPushId.keyValuemap, null, null, null, null);
    }

    @PreAuthorize("hasAuthority('ALL_USERS')")
    @RequestMapping(value = UrlPaths.PATH_WEB_NOTIFICATIONS_MARK_AS_READ, method = RequestMethod.PUT)
    public synchronized Response<?> markNotificationAsRead(
            @RequestBody PushMarkAsReadRequestDTO readRequestDTO,
            HttpServletResponse httpServletResponse) {
        Response response = pushNotificationService.notificationMarkAsRead(readRequestDTO);
        updateHttpStatusCode(response, httpServletResponse);
        return response;
    }

    @PostMapping(value = "/push/web/messages")
    public synchronized Response<?> sendWebNotification(
            @RequestParam(value = "pushId")Integer pushId,
            @RequestParam(value = "userId") Long userId,
            @RequestParam(value = "msg", required = false) String msg) {
        return pushNotificationService.sendWebNotification(pushId, userId, msg);
    }

    @PostMapping(value = "/pushlog")
    public synchronized Response<?> pushLog(
            HttpServletResponse httpServletResponse) {
        return updateHttpStatusCode(pushNotificationService.testPushExecutorIsWorking(),
                httpServletResponse);
    }

    @PostMapping(value = "/notify/isScheduler")
    public synchronized Response<?> notifySchedulerEnabledOrDesabled(
            HttpServletResponse httpServletResponse) {
        return updateHttpStatusCode(pushNotificationService.notifySchedulerEnabledOrDesabled(),
                httpServletResponse);
    }
}