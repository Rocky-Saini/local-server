package com.digital.signage.validators.impl;

import com.digital.signage.context.TenantContext;
import com.digital.signage.models.DeviceConfig;
import com.digital.signage.models.FieldMessageHelper;
import com.digital.signage.util.CommonUtils;
import com.digital.signage.util.Message;
import com.digital.signage.util.ObjectUtils;
import com.digital.signage.validators.DeviceConfigBaseValidator;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import com.digital.signage.validators.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeviceConfigPutApiValidator extends DeviceConfigBaseValidator {

    @Autowired
    private Message message;

    //todo
    //@Autowired
    //private TpAppModelRepository tpAppModelRepository;

    private static final Pattern URL_SET_REGEX_PATTERN = Pattern.compile(
            "^(http|https|local)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]");

    public void validate(DeviceConfig deviceConfig, FieldMessageHelper fieldMessageHelper) {
        if (ObjectUtils.eitherOneOfTheseObjectsIsNull(deviceConfig.getBusinessOffTime(),
                deviceConfig.getBusinessOnTime())) {
            // error
            fieldMessageHelper.addFieldList(DeviceConfig.JSON_B_OFF,
                    "Both sync on and off values should be present");
        }

        if (ObjectUtils.eitherOneOfTheseObjectsIsNull(deviceConfig.getPanelOffTime(),
                deviceConfig.getPanelOnTime())) {
            // error
            fieldMessageHelper.addFieldList(DeviceConfig.JSON_P_OFF,
                    "Both panel on and off values should be present");
        }

        if (ObjectUtils.eitherOneOfTheseObjectsIsNull(
                deviceConfig.getTouchScreenWebViewNoActionTimeoutInSeconds(),
                deviceConfig.getTouchScreenWebViewUrl())) {
            // error
            fieldMessageHelper.addFieldList(DeviceConfig.JSON_TOUCH_WEB_URL,
                    "Both touch url and timeout values should be present");
        }

        if (deviceConfig.getBusinessOffTime() != null) {
            // validate
            if (!CommonUtils.validateTimeFormat(deviceConfig.getBusinessOffTime())) {
                fieldMessageHelper.addFieldList(DeviceConfig.JSON_B_OFF,
                        "Invalid sync on time");
            }
        }

        if (deviceConfig.getBusinessOnTime() != null) {
            // validate
            if (!CommonUtils.validateTimeFormat(deviceConfig.getBusinessOnTime())) {
                fieldMessageHelper.addFieldList(DeviceConfig.JSON_B_ON,
                        "Invalid sync off time");
            }
        }

        if (deviceConfig.getPanelOffTime() != null) {
            // validate
            if (!CommonUtils.validateTimeFormat(deviceConfig.getPanelOffTime())) {
                fieldMessageHelper.addFieldList(DeviceConfig.JSON_P_OFF,
                        "Invalid panel off time");
            }
        }

        if (deviceConfig.getPanelOnTime() != null) {
            // validate
            if (!CommonUtils.validateTimeFormat(deviceConfig.getPanelOnTime())) {
                fieldMessageHelper.addFieldList(DeviceConfig.JSON_P_ON,
                        "Invalid panel on time");
            }
        }

        // validate business on and off gap
        validateBusinessOnOffGap(deviceConfig, message, fieldMessageHelper);

        // attempting to delete just web url
        if (deviceConfig.getTouchScreenWebViewUrl() != null
                && deviceConfig.getTouchScreenWebViewUrl().trim().isEmpty() // attempting to delete
                && deviceConfig.getTouchScreenWebViewNoActionTimeoutInSeconds() != null
                && deviceConfig.getTouchScreenWebViewNoActionTimeoutInSeconds().compareTo(0L)
                > 0 /* not attempting to delete */) {
            fieldMessageHelper.addFieldList(DeviceConfig.JSON_TOUCH_WEB_URL,
                    "Cannot delete touch web url when touch time out value is present");
        }

        // attempting to delete just touch web url time out
        if (deviceConfig.getTouchScreenWebViewUrl() != null
                && !deviceConfig.getTouchScreenWebViewUrl().trim().isEmpty()  /* not attempting to delete */
                && deviceConfig.getTouchScreenWebViewNoActionTimeoutInSeconds() != null
                && deviceConfig.getTouchScreenWebViewNoActionTimeoutInSeconds().compareTo(0L)
                < 0 /* attempting to delete */) {
            fieldMessageHelper.addFieldList(DeviceConfig.JSON_TOUCH_WEB_TIMEOUT,
                    "Cannot delete touch time out when touch web url value is present");
        }

        // both touch url and time out are not null and they are not being deleted
        // then we do this validation
        if (deviceConfig.getTouchScreenWebViewUrl() != null
                && !deviceConfig.getTouchScreenWebViewUrl().isEmpty()
                && deviceConfig.getTouchScreenWebViewNoActionTimeoutInSeconds() != null
                && deviceConfig.getTouchScreenWebViewNoActionTimeoutInSeconds().compareTo(0L) > 0) {
            Long customerId = TenantContext.getCustomerId();
            Set<String> allowedDpLinkSchema = new HashSet<>();
            //todo
            //new HashSet<>(tpAppModelRepository.findAllTpaSchemaOfThisCustomer(customerId));
            String[] allowedTouchUrls = new String[]{"http://", "https://", "local://"};
            boolean urlContainsSchema = false;
            if (!org.springframework.util.ObjectUtils.isEmpty(allowedDpLinkSchema)) {
                urlContainsSchema = allowedDpLinkSchema.contains(deviceConfig.getTouchScreenWebViewUrl());
            }
            boolean isPatternMatched = false;
            for (String url : allowedTouchUrls) {
                if (deviceConfig.getTouchScreenWebViewUrl().startsWith(url)) {
                    isPatternMatched = true;
                    break;
                }
            }
            String subErrorMsg =
                    allowedDpLinkSchema.isEmpty() ? "" : ("or one of these " + allowedDpLinkSchema);
            String errorMsg =
                    String.format("Please provide valid touch urls.Touch url Should be start with %s %s",
                            Arrays.toString(allowedTouchUrls), subErrorMsg);
            if (!urlContainsSchema && !isPatternMatched) {
                fieldMessageHelper.addFieldList(DeviceConfig.JSON_TOUCH_WEB_URL, errorMsg);
            }
        }

        if (deviceConfig.getPlanogramSyncStartTime() != null) {
            // validate
            if (!CommonUtils.validateTimeFormat(deviceConfig.getPlanogramSyncStartTime())) {
                fieldMessageHelper.addFieldList(DeviceConfig.JSON_PLANO_SYNC_START_TIME,
                        "Invalid planogram sync start time");
            }
        }

        ValidationUtil.validate(deviceConfig, DeviceConfig.class, message, fieldMessageHelper);
    }
}
