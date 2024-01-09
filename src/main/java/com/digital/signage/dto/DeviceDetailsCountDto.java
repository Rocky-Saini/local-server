package com.digital.signage.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DeviceDetailsCountDto {

    Long totalDeviceCount;
    Long totalTaggedDevices;
    Long totalUntaggedDevices ;
    Long activeCount ;
    Long inactiveCount ;
    Long activeTaggedDevices ;
    Long activeUnTaggedDevices;
    Long inactiveTaggedDevices ;
    Long inactiveUnTaggedDevices ;

}
