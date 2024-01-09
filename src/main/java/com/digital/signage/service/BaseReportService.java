package com.digital.signage.service;


import com.digital.signage.dto.BaseReportRequestDTO;
import com.digital.signage.service.impl.BaseServiceWithServerLaunchConfigImpl;
import com.digital.signage.util.DateUtils;
import com.digital.signage.util.ReportConstants;
import com.digital.signage.util.ResponseCode;
import org.springframework.stereotype.Service;
import org.springframework.lang.NonNull;
import com.digital.signage.models.Response;


import java.util.*;
import java.util.concurrent.TimeUnit;



@Service
public abstract class BaseReportService extends BaseServiceWithServerLaunchConfigImpl {

    protected void updateEndDate(BaseReportRequestDTO baseReportRequestDTO) {
        updateEndDate(baseReportRequestDTO, false);
    }

    void updateEndDate(BaseReportRequestDTO baseReportRequestDTO, Boolean isFutureReport) {
        int noOfDays = getMaxDaysForReport();
        int noOfDaysWhenToDateIsNull = 10;
        if (baseReportRequestDTO.getToDate() != null) {
            long diffDays = TimeUnit.DAYS.convert(
                    (baseReportRequestDTO.getToDate().getTime() - baseReportRequestDTO.getFromDate()
                            .getTime())
                    , TimeUnit.MILLISECONDS);
            if (diffDays > noOfDays) {
//                baseReportRequestDTO.setToDate(
//                        org.apache.commons.lang.time.DateUtils.addDays(baseReportRequestDTO.getFromDate(),
//                                noOfDays));
            }
        } else if (!isFutureReport) {
            long diffDays = TimeUnit.DAYS.convert(
                    (DateUtils.getCurrentTime().getTime() - baseReportRequestDTO.getFromDate().getTime())
                    , TimeUnit.MILLISECONDS);
            int noOfDaysToAdd =
                    (diffDays < noOfDaysWhenToDateIsNull) ? (int) diffDays : noOfDaysWhenToDateIsNull;
//            baseReportRequestDTO.setToDate(
//                    org.apache.commons.lang.time.DateUtils.addDays(baseReportRequestDTO.getFromDate(),
//                            noOfDaysToAdd));
        } else {
//            baseReportRequestDTO.setToDate(
//                    org.apache.commons.lang.time.DateUtils.addDays(baseReportRequestDTO.getFromDate(),
//                            noOfDaysWhenToDateIsNull));
            //baseReportRequestDTO.setToDateOperator("lte");
        }
        baseReportRequestDTO.setToDateOperator("lte");
    }

//    private List<Long> validateLocation(Long locationId, LocationRepository locationRepository,
//                                        LocationHierarchyService locationHierarchyService, DeviceRepository deviceRepository) {
//        List<Long> validDeviceIds = new ArrayList<>();
//        if (locationId != null) {
//            // if only locationId present
//            Location location = locationRepository.findOne(locationId);
//            if (location != null && !Status.DELETED.equals(location.getStatus())) {
//                // location Is valid
//                Set<Long> locationIds = new HashSet<>();
//                locationHierarchyService.getAllChildLeafLocation(locationId,
//                        locationIds);
//                if (!locationIds.isEmpty()) {
//                    List<Long> deviceIds =
//                            deviceRepository.findAllDeviceIdsByLocationIdsAndCustomerId(locationIds,
//                                            TenantContext.getCustomerId())
//                                    .stream()
//                                    .map(Device::getDeviceId)
//                                    .collect(
//                                            Collectors.toList());
//                    validDeviceIds.addAll(deviceIds);
//                }
//            }
//        }
//        return validDeviceIds;
//    }

//    List<Long> validateDevice(DeviceRepository deviceRepository,
//                              LocationRepository locationRepository,
//                              LocationHierarchyService locationHierarchyService,
//                              BaseReportRequestDTO baseReportRequestDTO) {
//        return validateDevice(deviceRepository, locationRepository, locationHierarchyService,
//                baseReportRequestDTO, TenantContext.getCustomerId());
//    }

//    List<Long> validateDevice(DeviceRepository deviceRepository,
//                              LocationRepository locationRepository,
//                              LocationHierarchyService locationHierarchyService,
//                              BaseReportRequestDTO baseReportRequestDTO, Long customerId) {
//
//        List<Long> validDeviceIdsForRequest = new ArrayList<>();
//
//        // is device present
//        boolean d = baseReportRequestDTO.getDeviceList() != null
//                && !baseReportRequestDTO.getDeviceList().isEmpty();
//
//        // is location present
//        boolean l = baseReportRequestDTO.getLocationId() != null;
//
//        if (d && !l) {
//            // if only deviceIds present
//            // 10
//            List<? extends Number> validDeviceIds =
//                    deviceRepository.validateDeviceIdsByDeviceIdsAndCustomerId(
//                            baseReportRequestDTO.getDeviceList());
//            if (validDeviceIds != null && !validDeviceIds.isEmpty()) {
//                validDeviceIdsForRequest.addAll(validDeviceIds.stream()
//                        .map(Number::longValue)
//                        .collect(Collectors.toList()));
//            }
//        }
//
//        if (!d && l) {
//            // if only locationId present
//            // 01
//            validDeviceIdsForRequest.addAll(
//                    validateLocation(baseReportRequestDTO.getLocationId(), locationRepository,
//                            locationHierarchyService, deviceRepository));
//        }
//
//        if (!d && !l) {
//            // both location and device filter is not there
//            // all customer devices need to be selected
//            // 00
//            List<? extends Number> deviceIds =
//                    deviceRepository.getDeviceIdsLongByCustomerIdIncludingDeletedOnes();
//            if (deviceIds != null && !deviceIds.isEmpty()) {
//                validDeviceIdsForRequest.addAll(deviceIds.stream()
//                        .map(Number::longValue)
//                        .collect(Collectors.toList()));
//            }
//        }
//
//        if (d && l) {
//            // both device and location filter is present
//            // 11
//            List<? extends Number> validDeviceIds =
//                    deviceRepository.validateDeviceIdsByDeviceIdsAndCustomerId(
//                            baseReportRequestDTO.getDeviceList());
//
//            if (validDeviceIds != null && !validDeviceIds.isEmpty()) {
//                // check if valid device Ids fall under given location
//                List<Long> common = validDeviceIds.stream()
//                        .map(Number::longValue).collect(Collectors.toList());
//                List<Long> validDevicesIdsByLocation =
//                        validateLocation(baseReportRequestDTO.getLocationId(), locationRepository,
//                                locationHierarchyService, deviceRepository);
//                common.retainAll(validDevicesIdsByLocation);
//                validDeviceIdsForRequest.addAll(common);
//            }
//        }
//
//        return validDeviceIdsForRequest;
//    }

    Response<?> validateFromDate(BaseReportRequestDTO baseReportRequestDTO) {
        if (baseReportRequestDTO.getFromDate() == null
                || baseReportRequestDTO.getFromDateOperator() == null) {
            return new Response<Void>(null, null, "BadRequest", ResponseCode.VALIDATION_ERROR,
                    "From Date Required", ResponseCode.FAILURE);
        }
        return null;
    }

    String validateScheduleDates(Date fromDate, Date toDate, boolean isFutureReport) {

        if (fromDate == null) {
            return "StartDate is invalid.";
        }
        if (toDate == null) {
            return "endDate is invalid.";
        }
        if (fromDate.after(toDate)) {
            return "StartDate can not be after EndDate.";
        }
        long diff = toDate.getTime() - fromDate.getTime();
        diff = Math.toIntExact(TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS)) + 1;
        if (diff > 30) {
            return "You can only fetch report of maximum 30 days.";
        }

        fromDate = DateUtils.resetTime(fromDate);
        toDate = DateUtils.resetTime(toDate);
        if (isFutureReport) {

            Date todayDate = DateUtils.getCurrentUtcDate();
            todayDate = DateUtils.resetTime(todayDate);
            if (fromDate.compareTo(todayDate) < 0) {
                return "StartDate can not be a Past date";
            }
        }
        //Past Date Report
        else {

            Date todayDate = DateUtils.getTodayDate();
            todayDate = DateUtils.resetTime(todayDate);
            if (fromDate.compareTo(todayDate) > 0) {
                return "StartDate can not be a future date";
            }
            if (toDate.compareTo(todayDate) > 0) {
                return "endDate can not be a future date";
            }
        }
        // no error found
        return "";
    }

    int getMaxDaysForReport() {
        return ReportConstants.MAX_DAY_LIMIT_FOR_REPORT_OLD;
    }

    @NonNull
    protected DatesValidation validateScheduleDatesNoDaysLimit(Date fromDate, Date toDate,
                                                               boolean isFutureReport) {

        if (fromDate == null) {
            return new DatesValidation("StartDate is invalid.", null);
        }
        if (toDate == null) {
            return new DatesValidation("endDate is invalid.", null);
        }
        if (fromDate.after(toDate)) {
            return new DatesValidation("StartDate can not be after EndDate.", null);
        }
        long diff = toDate.getTime() - fromDate.getTime();
        diff = Math.toIntExact(TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS)) + 1;

        //validate no of days from report constant
        if (diff > getMaxDaysForReport()) {
            return new DatesValidation(String.format("You can only fetch report of maximum %s days.",
                    getMaxDaysForReport()), null);
        }

        fromDate = DateUtils.resetTime(fromDate);
        toDate = DateUtils.resetTime(toDate);
        if (isFutureReport) {

            Date todayDate = DateUtils.getCurrentUtcDate();
            todayDate = DateUtils.resetTime(todayDate);
            if (fromDate.compareTo(todayDate) < 0) {
                return new DatesValidation("StartDate can not be a Past date", diff);
            }
        }
        //Past Date Report
        else {

            Date todayDate = new Date();
            todayDate = DateUtils.resetTime(todayDate);
            if (fromDate.compareTo(todayDate) > 0) {
                return new DatesValidation("StartDate can not be a future date", diff);
            }
            if (toDate.compareTo(todayDate) > 0) {
                return new DatesValidation("endDate can not be a future date", diff);
            }
        }
        // no error found
        return new DatesValidation("", diff);
    }

//    @NonNull
//    DatesValidation validateDatesNoDaysLimit(Date fromDate, Date toDate) {
//        if (fromDate == null) {
//            return new DatesValidation("StartDate is invalid.", null);
//        }
//        if (toDate == null) {
//            return new DatesValidation("endDate is invalid.", null);
//        }
//        if (fromDate.after(toDate)) {
//            return new DatesValidation("StartDate can not be after EndDate.", null);
//        }
//        long diff = toDate.getTime() - fromDate.getTime();
//        diff = Math.toIntExact(TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS)) + 1;
//
//        fromDate = DateUtils.resetTime(fromDate);
//        toDate = DateUtils.resetTime(toDate);
//
//        //Past Date Report
//        Date todayDate = new Date();
//        todayDate = DateUtils.resetTime(todayDate);
//        if (fromDate.compareTo(todayDate) > 0) {
//            return new DatesValidation("StartDate can not be a future date", diff);
//        }
//        // no error found
//        return new DatesValidation("", diff);
//    }

//    public String validateReportToken(String reportToken, XlsHelper xlsHelper) {
//        return ReportUtilsKt.sqlTableFriendlyReportToken(reportToken, xlsHelper);
//    }

    protected static class DatesValidation {
        public String message;
        Long diff;

        DatesValidation(String message, Long diff) {
            this.message = message;
            this.diff = diff;
        }
    }
}
