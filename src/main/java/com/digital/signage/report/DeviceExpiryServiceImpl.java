package com.digital.signage.report;
import com.digital.signage.context.TenantContext;
import com.digital.signage.dto.BaseReportRequestDTO;
import com.digital.signage.dto.report.DeviceReportDto;
import com.digital.signage.models.*;
import com.digital.signage.models.Pagination;
import com.digital.signage.models.Response;
import com.digital.signage.repository.LocationRepo;
import com.digital.signage.service.WebClientService;
import com.digital.signage.service.impl.BaseServiceImpl;
import com.digital.signage.util.PaginationUtils;
import com.digital.signage.util.Status;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

import static com.digital.signage.util.ApplicationConstants.MAX_ITEMS_PER_PAGE_FOR_PAGINATION;

@Service
@Slf4j
public class DeviceExpiryServiceImpl extends BaseServiceImpl implements DeviceExpiryService {
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private LocationRepo locationRepository;
    @Autowired
    private WebClientService webClientService;
    @Autowired
    private XlsHelper xlsHelper;

    @Transactional
    @Override
    public Response<?> getDeviceLicenseExpiryReports(Long fromDate, Long toDate,String deviceName, String location, Status status, Integer currentPage, Integer noPerPage) {
        currentPage = currentPage != null ? currentPage : 1;
        noPerPage =
                (noPerPage != null && noPerPage <= MAX_ITEMS_PER_PAGE_FOR_PAGINATION)
                        ? noPerPage
                        : MAX_ITEMS_PER_PAGE_FOR_PAGINATION;
        List<String> licenseCodes = new ArrayList<>();
        //WebClient
        List<LicenseDto> licenseResponse = new ArrayList<>();
        try{
        com.digital.signage.report.Response<List<LicenseDto>> license =
                (com.digital.signage.report.Response<List<LicenseDto>>)webClientService.getAllLicenseList("/license-management/lms/v1/license/expire/{slugId}", TenantContext.getTenantId());

          licenseResponse.addAll(license.getData());
         } catch (Exception e) {
        // Handle any exceptions that might occur during the WebClient call or data processing
        System.err.println("An error occurred: " + e.getMessage());
        e.printStackTrace(); // Print the stack trace for debugging purposes
        // Add any additional error handling logic as needed
    }
        Map<String,LicenseDto> maps = new HashMap<>();
        licenseResponse.forEach(res->{
            maps.put(res.getLicenseCode(),res);
        });
//        for(int i=0;i<licenseResponse.size();i++){
////            if(licenseResponse.get(i).getStatus()==Status.INACTIVE)
//                licenseCodes.add(licenseResponse.get(i).getLicenseCode());
//        }
        for (int i = 0; i < licenseResponse.size(); i++) {
            LicenseDto license = licenseResponse.get(i);
            Date expiryDate = license.getExpiryDate();

            if (expiryDate != null) {
//                long expiryTime = expiryDate.getTime();
                LocalDate expiryLocalDate = expiryDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                // Convert fromDate and toDate to LocalDate
                LocalDate fromLocalDate = new Date(fromDate).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate toLocalDate = new Date(toDate).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                // Check if expiryDate is within the specified date range
                if (!expiryLocalDate.isBefore(fromLocalDate) && !expiryLocalDate.isAfter(toLocalDate)) {
                    licenseCodes.add(license.getLicenseCode());
                }
            }
        }
        // Filter licenses based on expiry date within the specified range
//        List<LicenseDto> filteredLicenses =  licenseResponse.stream()
//                .filter(license -> {
//                    Date expiryDate = license.getExpiryDate();
//                    if (expiryDate != null) {
//                        long expiryTime = expiryDate.getTime();
//                        return expiryTime >= fromDate && expiryTime <= toDate;
//                    }
//                    return false;
//                })
//                .collect(Collectors.toList());
//
//        licenseCodes = filteredLicenses.stream()
//                .map(LicenseDto::getLicenseCode)
//                .collect(Collectors.toList());

        StringBuilder query = new StringBuilder();
        query.append("SELECT *,l.location_name FROM device d LEFT JOIN  location l on  d.location_id=l.location_id WHERE d.licence_code in (:licenseCodes) ");
        if(status!=null){
            query.append(" and d.status = :status ");
        }else{
            query.append(" and d.status != 3 ");
        }
        if(location!=null){
            query.append(" and l.location_name LIKE :location ");
        }
        if(deviceName!=null){
            query.append(" and d.device_name LIKE :deviceName ");
        }
        Query qry = entityManager.createNativeQuery(query.toString(), Device.class);
        qry.setParameter("licenseCodes",licenseCodes);
        if(status!=null){
            qry.setParameter("status",status.getValue());
        }
        if(location!=null){
            qry.setParameter("location","%" +location + "%");
        }
        if(deviceName!=null){
            qry.setParameter("deviceName","%" +deviceName+ "%");
        }

        Integer totalCount = qry.getResultList().size();
        List<Device> resultList = qry.getResultList();
        qry.setFirstResult((currentPage - 1) * noPerPage);
        qry.setMaxResults(noPerPage);

        List<Device> resultList2 = qry.getResultList();

        Set<Long> ids = resultList.stream().map(Device::getLocationId).collect(Collectors.toSet());
        List<LocationEntity> locations = locationRepository.findAllByLocationIds(ids);
        Map<Long,String> loctionmap = new HashMap<>();
        locations.forEach(r->{
            loctionmap.put(r.getLocationId(),r.getLocationName());
        });
        List<DeviceReportDto> deviceReportDtos = new ArrayList<>();
        for(int i=0; i< resultList.size();i++){
            DeviceReportDto deviceReportDto = new DeviceReportDto();
            deviceReportDto.setMediaPlayerName(resultList.get(i).getDeviceName());
            deviceReportDto.setLicenceCode(resultList.get(i).getLicenceCode());
            deviceReportDto.setStatus(maps.get(resultList.get(i).getLicenceCode()).getStatus());
            deviceReportDto.setLicenceStartDate(maps.get(resultList.get(i).getLicenceCode()).getStartDate());
            deviceReportDto.setLicenceEndDate(maps.get(resultList.get(i).getLicenceCode()).getExpiryDate());
            deviceReportDto.setLocation(loctionmap.get(resultList.get(i).getLocationId()));
            deviceReportDtos.add(deviceReportDto);
        }

        List<DeviceReportDto> deviceReportDtos2 = new ArrayList<>();
        for(int i = 0; i < resultList2.size(); i++) {
            DeviceReportDto deviceReportDto = new DeviceReportDto();
            deviceReportDto.setMediaPlayerName(resultList2.get(i).getDeviceName());
            deviceReportDto.setLicenceCode(resultList2.get(i).getLicenceCode());
            deviceReportDto.setStatus(maps.get(resultList2.get(i).getLicenceCode()).getStatus());
            deviceReportDto.setLicenceStartDate(maps.get(resultList2.get(i).getLicenceCode()).getStartDate());
            deviceReportDto.setLicenceEndDate(maps.get(resultList2.get(i).getLicenceCode()).getExpiryDate());
            deviceReportDto.setLocation(loctionmap.get(resultList2.get(i).getLocationId()));
            deviceReportDtos2.add(deviceReportDto);
        }

//            List<DeviceReportDto> DeviceExpiryLicenseReportDto = ReportMapper.mapLicenseEntityToDeviceExpiryLicenseReportDto(resultList);
        LicenseReportResponseDto<DeviceReportDto> licenseReportResponseDto = new LicenseReportResponseDto();
        licenseReportResponseDto.setData(deviceReportDtos2);

        CachedReport<DeviceReportDto, BaseReportRequestDTO> cachedReport = null;
        try {
            cachedReport = xlsHelper.cacheReportAndGenerateXls(deviceReportDtos, DeviceReportDto.class);
        }
        catch (IOException ex) {
            log.error("Error while generating new license report: " + ex);
        }
        if(cachedReport != null) {
            licenseReportResponseDto.setReportToken(cachedReport.getReportToken());
            licenseReportResponseDto.setReportDownloadUrl(cachedReport.getDownloadbleLinkToXlsReport());
            licenseReportResponseDto.setPdfDownloadUrl(cachedReport.getDownloadableLinkToPdfReport());
            licenseReportResponseDto.setIsReportCompleted(cachedReport.getIsReportCompleted());
        }

        Pagination pagination = PaginationUtils.getPagination(totalCount, currentPage, noPerPage, resultList.size());
        Response<LicenseReportResponseDto<DeviceReportDto>> response = new Response.Builder<LicenseReportResponseDto<DeviceReportDto>>()
                .result(licenseReportResponseDto)
                .pagination(pagination)
                .build();
        return response;
//        return new Response.Builder<List<DeviceReportDto>>().result(deviceReportDtos).pagination(pagination).build();
    }





    }
