package com.digital.signage.service;

import com.digital.signage.models.Response;
import com.digital.signage.models.UnregisteredDevice;
import com.digital.signage.util.DeviceOs;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Service
public interface UnregisteredDeviceService extends BaseService {

    Response<?> addUnregisteredDevice(UnregisteredDevice unregisteredDevice,String licenseCode,
                                      HttpServletRequest request);

    Response<List<UnregisteredDevice>> getAllUnregisteredDevices(String clientIdentifier, String ethernetMacAddress, String wifiMacAddress, DeviceOs os, Integer currentPage, Integer numPerPage);

    Response<UnregisteredDevice> getUnregisteredDeviceById(long deviceId);
}
