package com.digital.signage.configuration.interceptor;

import com.digital.signage.context.TenantContext;
import com.digital.signage.util.ApplicationConstants;
import com.digital.signage.util.NumberUtils;
import com.digital.signage.util.UrlUtilsKt;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Slf4j
public class DeviceBandwidthInterceptor extends HandlerInterceptorAdapter {
    private final DeviceBandwidthManager deviceBandwidthManager;

    public DeviceBandwidthInterceptor(DeviceBandwidthManager deviceBandwidthManager) {
        this.deviceBandwidthManager = deviceBandwidthManager;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        try {
            long bandwidthSizeOfPayloadInBytes = getBandWidthSizeOfPayload(request, response);
            if (bandwidthSizeOfPayloadInBytes <= 0L) {
                String requestUrl = sanitizeRequestUrl(request.getRequestURI());
                log.debug("bandwidthSizeOfPayloadInBytes is zero for requested url::{}.", requestUrl);
                return;
            }
            /*if (SecurityContextHolder.getContext() == null
                    || SecurityContextHolder.getContext().getAuthentication() == null) {
                unauthenticated(request, response);
            } else {*/
            authenticated(request, bandwidthSizeOfPayloadInBytes);
            //}
        } catch (Exception e) {
            log.error("", e);
        }
    }

    private String sanitizeRequestUrl(String requestUrl) {
        requestUrl = UrlUtilsKt.removeSlashFromEndOfUrlIfRequired(requestUrl);
        return UrlUtilsKt.removeBeginningSlashIfPresent(requestUrl);
    }

    private void authenticated(
            @NotNull HttpServletRequest request,
            long bandwidthSizeOfPayloadInBytes
    ) throws Exception {
        if (TenantContext.getIsDevice()) {
            Long deviceId = TenantContext.getDeviceId();
            Objects.requireNonNull(deviceId);
            deviceBandwidthManager.insertOrUpdate(deviceId, bandwidthSizeOfPayloadInBytes);
        }
    }

    /**
     * @return length of bandwidth used in payload
     */
    private long getBandWidthSizeOfPayload(HttpServletRequest request, HttpServletResponse response) {
        long bandWidthSize = 0L;
        if (!(HttpMethod.GET.name().equalsIgnoreCase(request.getMethod()))) {
            bandWidthSize = request.getContentLength();
        }

        // Added Custom Header in base Controller , retrieve the value and set it to bandwidth size
        String responseLengthHeader = response.getHeader(ApplicationConstants.Headers.RESPONSE_LENGTH);

        if (responseLengthHeader != null && !responseLengthHeader.isEmpty()) {
            Long responseLength = NumberUtils.parseLongOrNull(responseLengthHeader);
            if (responseLength != null) {
                bandWidthSize += responseLength;
            }
        }
        return bandWidthSize;
    }
}
