package com.digital.signage.util;

/**
 * @author -Ravi Kumar created on 12/27/2022 11:55 PM
 * @project - Digital Sign-edge
 */
public interface MessageUtils {

    public interface Device {

        String SUCESSFULLY_SAVED = "Device saved successfully";
        String SUCESSFULLY_UPDATED = "Device updated successfully";
        String SUCESSFULLY_DELETED = "Device deleted successfully";
        String SUCESSFULLY_FETCHED = "Device data fetched successfully";

    }

    public interface DeviceConfig {

        String SUCESSFULLY_SAVED = "Device Config saved successfully";
        String SUCESSFULLY_UPDATED = "Device Config updated successfully";
        String SUCESSFULLY_DELETED = "Device Config deleted successfully";
        String SUCESSFULLY_FETCHED = "Device Config data fetched successfully";
    }

    public interface DeviceGroup {

        String SUCESSFULLY_SAVED = "Device Group saved successfully";
        String SUCESSFULLY_UPDATED = "Device Group updated successfully";
        String SUCESSFULLY_DELETED = "Device Group deleted successfully";
        String SUCESSFULLY_FETCHED = "Device Group data fetched successfully";
    }
}
