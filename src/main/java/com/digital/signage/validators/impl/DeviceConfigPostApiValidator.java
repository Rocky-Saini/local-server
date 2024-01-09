package com.digital.signage.validators.impl;

import com.digital.signage.models.DeviceConfig;
import com.digital.signage.models.FieldMessageHelper;
import com.digital.signage.util.Message;
import com.digital.signage.validators.DeviceConfigBaseValidator;
import com.digital.signage.validators.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeviceConfigPostApiValidator extends DeviceConfigBaseValidator {

    @Autowired
    private Message message;

    public void validate(DeviceConfig deviceConfig, FieldMessageHelper helper) {

        // panel on and off time must not be null
        if ((deviceConfig.getPanelOnTime() != null) && (deviceConfig.getPanelOffTime() != null)) {

            if ((deviceConfig.getPanelOnTime().compareTo(deviceConfig.getPanelOffTime())) == 0) {
                helper.addFieldList("PanelTimeEqualError",
                        message.get(Message.DeviceConfig.DEVICECONFIG_PANEL_TIMES_SAME_ERROR));
            }
        } else {
            helper.addFieldList("PanelOnOffTimeNull",
                    message.get(Message.DeviceConfig.DEVICECONFIG_PANEL_ON_OFF_TIME_NULL_ERROR));
        }

        // validate business on and off gap
        validateBusinessOnOffGap(deviceConfig, message, helper);

        // business on and off time
        if ((deviceConfig.getBusinessOnTime() != null) && (deviceConfig.getBusinessOffTime() != null)) {

            if ((deviceConfig.getBusinessOnTime().compareTo(deviceConfig.getBusinessOffTime())) == 0) {
                helper.addFieldList("BussinessTimeEqualError",
                        message.get(Message.DeviceConfig.DEVICECONFIG_BUSSINESS_TIMES_SAME_ERROR));
            }
        } else {
            helper.addFieldList("BussinessOnOffTimeNull",
                    message.get(Message.DeviceConfig.DEVICECONFIG_BUSSINESS_TIMES_NULL_ERROR));
        }

        // customerId
        if (deviceConfig.getCustomerId() == null) {
            helper.addFieldList("customerIdNull",
                    message.get(Message.DeviceConfig.DEVICECONFIG_GLOBAL_CONFIG_MISSING_CUSTID));
        }

        ValidationUtil.validate(deviceConfig, DeviceConfig.class, message, helper);
    }
}
