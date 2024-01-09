package com.digital.signage.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceCountDto {

    Long totalDeviceCount;
    Long activeTaggedCount;
    Long activeUntaggedCount;
    Long inactiveTaggedCount;
    Long inactiveUntaggedCount;
}
