//package com.digital.signage.service.report;
//
//
//import com.digital.signage.dto.report.DeviceReportDto;
//import com.digital.signage.models.Device;
////import com.panasonic.licensemanagement.dto.Customer;
////import com.panasonic.licensemanagement.dto.report.ExpiryLicenseReportDto;
////import com.panasonic.licensemanagement.dto.report.NewLicenseReportDto;
////import com.panasonic.licensemanagement.dto.report.RenewalLicenseReportDto;
////import com.panasonic.licensemanagement.entity.LicenseEntity;
////import com.panasonic.licensemanagement.entity.User;
////import com.panasonic.licensemanagement.persistence.CustomerRepository;
////import com.panasonic.licensemanagement.persistence.UserRepository;
////import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.*;
//
//public class ReportMapper {
//
//
//    public static List<DeviceReportDto> mapLicenseEntityToDeviceExpiryLicenseReportDto(List<Device> resultList) {
//        List<DeviceReportDto> DeviceExpiryLicenseReportDtoList = new ArrayList<>();
//        for(Device license : resultList) {
//            DeviceExpiryLicenseReportDtoList.add(
//                    DeviceReportDto.builder()
//                            .MediaPlayerName(license.getDeviceName())
//                            .licenceCode(license.getLicenceCode())
//                            .status(license.getStatus())
//
//                            .build()
//            );
//        }
//        return DeviceExpiryLicenseReportDtoList;
//
//    }
//}
