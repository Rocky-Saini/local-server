package com.digital.signage.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
public class DeviceWithCustomerIdAndName {
    private Long deviceId;
    private Long customerId;
    private String deviceName;

    public DeviceWithCustomerIdAndName(Long deviceId, Long customerId, String deviceName) {
        this.deviceId = deviceId;
        this.customerId = customerId;
        this.deviceName = deviceName;
    }

    // Getters and setters
}