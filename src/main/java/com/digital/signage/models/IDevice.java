package com.digital.signage.models;

import com.digital.signage.util.DeviceOs;

/**
 * @author -Ravi Kumar created on 12/15/2022 2:15 AM
 * @project - Digital Sign-edge
 */
public interface IDevice {

    String JSON_KEY_CLIENT_GENERATED_DEVICE_IDENTIFIER = "clientGeneratedDeviceIdentifier";
    String JSON_DEVICE_OS = "deviceOs";
    String JSON_DEVICE_WIFI_MAC = "deviceWifiMacAddress";
    String JSON_DEVICE_ETHERNET_MAC = "deviceEthernetMacAddress";
    String JSON_UNREGISTERED_DEVICE_ID = "unregisteredDeviceId";
    String COLUMN_WIFI_MAC = "wifi_mac_address";
    String COLUMN_ETHERNET_MAC = "ethernet_mac_address";
    String COLUMN_DEVICE_KEY = "device_key";
    String COLUMN_DEVICE_OS = "device_os";

    String LICENSE_CODE = "license_code";

    String getWifiMacAddress();

    String getEthernetMacAddress();

    void setWifiMacAddress(String wifiMacAddress);

    void setEthernetMacAddress(String ethernetMacAddress);

    DeviceOs getDeviceOs();

    void setDeviceOs(DeviceOs deviceOs);

    String getDeviceKey();

    void setDeviceKey(String deviceKey);
}
