package com.digital.signage.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DeviceLocalServerIPRequestDTO {
    public static final String JSON_LOCAL_SERVER_IP = "localServerIP";

    @JsonProperty(JSON_LOCAL_SERVER_IP)
    private String localServerIP;

    public String getLocalServerIP() {
        return localServerIP;
    }

    public void setLocalServerIP(String localServerIP) {
        this.localServerIP = localServerIP;
    }

    @Override
    public String toString() {
        return "DeviceLocalServerIPRequestDTO{" +
                "localServerIP='" + localServerIP + '\'' +
                '}';
    }
}
