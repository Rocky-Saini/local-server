package com.digital.signage.validators;

import com.digital.signage.models.DeviceConfig;
import com.digital.signage.models.FieldMessageHelper;
import com.digital.signage.util.Message;

import java.util.concurrent.TimeUnit;

public abstract class DeviceConfigBaseValidator {
    /*

    B-Off      B-On      Off-On   actual-gap    isValid
    ------------------------------------------------------
    18:00  >  17:00      1 hr      23 hrs        valid
    18:00  >  12:00      6 hr      18 hr         valid
    23:30  >  00:30     23 hr      1 hr          valid
    23:50  >  00:00     23:50 hr   10 min        invalid
    22:00  <  23:00     -1 hr      1 hr          valid
    11:00  <  18:00     -7 hr      7 hr          valid
    15:00  <  15:30     -30 min    30 min        invalid

    */
    protected void validateBusinessOnOffGap(DeviceConfig deviceConfig, Message message,
                                            FieldMessageHelper helper) {
        if (deviceConfig.getBusinessOffTime() != null && deviceConfig.getBusinessOnTime() != null) {
            long diffSeconds = deviceConfig.getBusinessOffTime().toLocalTime().toSecondOfDay()
                    - deviceConfig.getBusinessOnTime().toLocalTime().toSecondOfDay();

            if (diffSeconds < 0) {
                diffSeconds = diffSeconds * -1;
            } else {
                diffSeconds = TimeUnit.HOURS.toSeconds(24L) - diffSeconds;
            }

            if (diffSeconds < TimeUnit.HOURS.toSeconds(1L)) {
                // diff cannot be less than an hour
                helper.addFieldList(DeviceConfig.JSON_B_OFF,
                        message.get(Message.DeviceConfig.DEVICECONFIG_BUSSINESS_TIMES_GAP_LESS_ERROR,
                                "1 hour"));
            }
        }
    }
}

