package com.digital.signage.models;

import lombok.Data;

import java.util.List;

/**
 * @author -Ravi Kumar created on 1/16/2023 4:50 PM
 * @project - Digital Sign-edge
 */
@Data
public class DeviceResponseDTO {

    private Integer availableDevices;
    private Integer numberOfDevices;
    private Integer consumedNumberOfDevices;
    private List<DeviceExt> devices;
    private List<Long> notFound;
}
