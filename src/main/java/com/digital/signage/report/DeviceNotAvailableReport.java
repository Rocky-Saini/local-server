package com.digital.signage.report;

import com.digital.signage.dto.ConfigTimeDTO;
import com.digital.signage.models.DeviceOnOffLogReportNewDTO;
import com.digital.signage.models.DataCollectionConfig;
import com.digital.signage.models.Device;
import com.digital.signage.models.DeviceData;
import com.digital.signage.repository.DeviceDataRepository;
import com.digital.signage.repository.DeviceRepository;
import com.digital.signage.util.DataCollectionEnum;
import com.digital.signage.util.DateUtils;
import com.digital.signage.util.ReportsUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

import static com.digital.signage.util.CommonUtils.isNullSafeLessTimeCompare;
import static com.digital.signage.util.DataCollectionEnum.AdditionalInfo.AFTER_ONBOARDING;
import static com.digital.signage.util.DateUtils.*;

@Component
public class DeviceNotAvailableReport extends ReportsUtils {
    @Autowired
    private DeviceRepository deviceRepository;
    @Autowired
    private DeviceDataRepository deviceDataRepository;
    private static final Logger logger = LoggerFactory.getLogger(DeviceNotAvailableReport.class);
    private static final boolean TEMP = true;

    public List<DeviceOnOffLogReportNewDTO> addNotAvailableEntryInResponseDTOS(
            List<Date> filterDateRange, String reportToken, List<Long> validDeviceIds,
            List<DeviceOnOffLogReportNewDTO> responseDTOS, boolean isCallFromPercentageReport,
            final Map<String, List<DataCollectionConfig>> dataCollectionConfigMapPerDay,
            final Map<Long, List<DataCollectionConfig>> defaultDataCollectionConfigMap) {
        responseDTOS.sort(Comparator.comparing(DeviceOnOffLogReportNewDTO::getDeviceId));
        List<Device> deviceList = deviceRepository.findByDeviceIds(validDeviceIds);
        if (!ObjectUtils.isEmpty(deviceList)) {
            if (!ObjectUtils.isEmpty(filterDateRange)) {
                final Map<String, List<DeviceOnOffLogReportNewDTO>> entryDateWiseMap =
                        getEntryDateWiseHashtableOfDeviceDTO(responseDTOS, deviceList);

                //create entry for whole date which date data is not available.
                final List<DeviceOnOffLogReportNewDTO> dtoListWithFullNA = new ArrayList<>();
                final List<DeviceOnOffLogReportNewDTO> dtoWithPartialDayNA = new ArrayList<>();
                if (!ObjectUtils.isEmpty(deviceList) && !ObjectUtils.isEmpty(filterDateRange)) {
                    filterDateRange.forEach(date ->
                            deviceList.forEach(deviceObj -> {
                                String keyWithDeviceId = getKeysForHashMap(deviceObj.getDeviceId(), date);
                                List<DataCollectionConfig> currentDateConfigs =
                                        chooseConfigFromMapOrDefault(keyWithDeviceId, dataCollectionConfigMapPerDay,
                                                defaultDataCollectionConfigMap);
                                for (DataCollectionConfig currentDateConfig : currentDateConfigs) {
                                    //No any entry of that date in list then add full NA entry
                                    if (!entryDateWiseMap.containsKey(keyWithDeviceId)) {
                                        addFullDayNotAvailableEntryInList(currentDateConfig, date, deviceObj,
                                                reportToken, dtoListWithFullNA, isCallFromPercentageReport);
                                    } else {
                                        List<DeviceOnOffLogReportNewDTO> entriesOfThatDay =
                                                entryDateWiseMap.get(keyWithDeviceId);
                                        if (TEMP) {
                                            for (int i = 0; i < entriesOfThatDay.size(); i++) {
                                                DeviceOnOffLogReportNewDTO e = entriesOfThatDay.get(i);
                                                if (e == null) {
                                                    logger.error(
                                                            "eeeeenullerror1 DeviceOnOffLogReportNewDTO is NULL @ index = {}", i);
                                                }
                                            }
                                        }
                                        if (!ObjectUtils.isEmpty(entriesOfThatDay)) {
                                            entriesOfThatDay = entriesOfThatDay.stream()
                                                    .filter((DeviceOnOffLogReportNewDTO dto) ->
                                                            isaTimeBetweenTheseTimes(dto.getStartTime(),
                                                                    currentDateConfig.getPanelOnTime(),
                                                                    currentDateConfig.getPanelOffTime(), true)
                                                    )
                                                    .filter((DeviceOnOffLogReportNewDTO dto) ->
                                                            isaTimeBetweenTheseTimes(dto.getEndTime(),
                                                                    currentDateConfig.getPanelOnTime(),
                                                                    currentDateConfig.getPanelOffTime(), true)
                                                    )
                                                    .collect(Collectors.toList());
                                            if (ObjectUtils.isEmpty(entriesOfThatDay)) {
                                                addFullDayNotAvailableEntryInList(currentDateConfig, date, deviceObj,
                                                        reportToken, dtoListWithFullNA, isCallFromPercentageReport);
                                            } else {
                                                addPartialDayNotAvailableEntryInDtoList(entriesOfThatDay, currentDateConfig,
                                                        date, deviceObj, reportToken, dtoWithPartialDayNA,
                                                        isCallFromPercentageReport);
                                            }
                                        }
                                    }
                                }
                            })
                    );
                }
                if (!ObjectUtils.isEmpty(dtoWithPartialDayNA)) {
                    responseDTOS.addAll(dtoWithPartialDayNA);
                }
                if (!ObjectUtils.isEmpty(dtoListWithFullNA)) {
                    responseDTOS.addAll(dtoListWithFullNA);
                }
            }
        }
        return responseDTOS;
    }

    private void addFullDayNotAvailableEntryInList(DataCollectionConfig currentDateConfig, Date date,
                                                   Device deviceObj, String reportToken, List<DeviceOnOffLogReportNewDTO> dtoListWithFullNA,
                                                   boolean isCallFromPercentageReport) {
        Date startTime, endTime, currentDate;
        currentDate = new Date();
        if (currentDateConfig != null && currentDateConfig.getPanelOnTime() != null) {
            startTime = combineUtilDateAndTime(date, currentDateConfig.getPanelOnTime());
        } else {
            startTime = floorDate(date);
        }
        if (isCurrentTimeLessThanConfigForToday(date, currentDateConfig)) {
            endTime = currentDate;
        } else if (currentDateConfig != null
                && currentDateConfig.getPanelOffTime() != null) {
            endTime = combineUtilDateAndTime(date, currentDateConfig.getPanelOffTime());
        } else {
            endTime = ceilDate(date);
        }
        boolean isUnderConfig =
                deviceObj.getDeletedOn() != null ? (
                        date.after(deviceObj.getCreatedOn())
                                && date.before(deviceObj.getDeletedOn())) :
                        date.after(deviceObj.getCreatedOn());
        if (isUnderConfig && isThisPastDate(startTime) && isThisPastDate(endTime)) {
            DeviceOnOffLogReportNewDTO newDto =
                    createNotAvailableEntry(deviceObj, startTime, endTime, reportToken,
                            isCallFromPercentageReport);
            if (Objects.nonNull(newDto)) {
                dtoListWithFullNA.add(newDto);
            }
        }
    }

    private void addPartialDayNotAvailableEntryInDtoList(
            List<DeviceOnOffLogReportNewDTO> entriesInThisDate, DataCollectionConfig currentDateConfig,
            Date date,
            Device deviceObj, String reportToken, List<DeviceOnOffLogReportNewDTO> dtoWithPartialDayNA,
            boolean isCallFromPercentageReport) {
        //create default timeInConfig and if config found then set it.
        final ConfigTimeDTO timeInConfig = new ConfigTimeDTO("00:00:00", "23:59:59");
        if (currentDateConfig != null && currentDateConfig.getPanelOnTime() != null) {
            timeInConfig.setStartTime(currentDateConfig.getPanelOnTime());
        }
        //here check is panelOff time is before current time then set end time as panel off time.
        if (date.equals(getCurrentDateWithoutTime())
                && isCurrentTimeLessThanConfigForToday(date, currentDateConfig)) {
            timeInConfig.setEndTime(getCurrentTimeWithoutDate());
        } else if (currentDateConfig != null
                && currentDateConfig.getPanelOffTime() != null) {
            timeInConfig.setEndTime(currentDateConfig.getPanelOffTime());
        }
        if (!ObjectUtils.isEmpty(entriesInThisDate)) {
            //sort entries to process
            entriesInThisDate.sort(
                    Comparator.comparing(DeviceOnOffLogReportNewDTO::getStartTime));
            //create a configTimeDto object to store old previous config data.
            final ConfigTimeDTO preConfigDto = new ConfigTimeDTO();
            final ConfigTimeDTO timeInDto = new ConfigTimeDTO();
            entriesInThisDate.forEach(onOffLogDTO -> {
                /*
                 * check is previous config endTime is equal or not to current config startTime
                 *if not equal and start time is after end time then fill this gap with one config object.
                 */
                if (preConfigDto.getEndTime() != null && onOffLogDTO.getStartTime() != null) {
                    LocalTime endTimeOfPre = LocalTime.parse(preConfigDto.getEndTime());
                    LocalTime startTimeOfCurrent = LocalTime.parse(onOffLogDTO.getStartTime());
                    if (!endTimeOfPre.equals(startTimeOfCurrent) && endTimeOfPre.isBefore(
                            startTimeOfCurrent)) {
                        //Add not available entry in mid of missing entries.
                        DeviceOnOffLogReportNewDTO newDto =
                                createNotAvailableEntry(deviceObj,
                                        combineUtilDateAndTime(date, preConfigDto.getEndTime()),
                                        combineUtilDateAndTime(date, onOffLogDTO.getStartTime()),
                                        reportToken, isCallFromPercentageReport);
                        if (Objects.nonNull(newDto)) {
                            dtoWithPartialDayNA.add(newDto);
                        }
                    }
                }
                //update preConfig object on each iteration to store previous config time.
                preConfigDto.setStartTime(onOffLogDTO.getStartTime());
                preConfigDto.setEndTime(onOffLogDTO.getEndTime());
                /*
                 * Below code goes to add Not Available entries to border of list, means starting and ending to list
                 */
                if (timeInDto.getStartTime() == null) {
                    timeInDto.setStartTime(onOffLogDTO.getStartTime());
                } else {
                    if (timeInDto.getStartTime().compareTo(onOffLogDTO.getStartTime()) > 0) {
                        timeInDto.setStartTime(onOffLogDTO.getStartTime());
                    }
                }
                if (timeInDto.getEndTime() == null) {
                    timeInDto.setEndTime(onOffLogDTO.getEndTime());
                } else {
                    if (timeInDto.getEndTime().compareTo(onOffLogDTO.getEndTime()) < 0) {
                        timeInDto.setEndTime(onOffLogDTO.getEndTime());
                    }
                }
            });
            if (isNullSafeLessTimeCompare(timeInConfig.getStartTime(), timeInDto.getStartTime())) {
                DeviceOnOffLogReportNewDTO newDto =
                        createNotAvailableEntry(deviceObj,
                                combineUtilDateAndTime(date, timeInConfig.getStartTime()),
                                combineUtilDateAndTime(date, timeInDto.getStartTime()),
                                reportToken, isCallFromPercentageReport);
                if (Objects.nonNull(newDto) && isDtoValidToAdd(newDto)) {
                    dtoWithPartialDayNA.add(newDto);
                }
            }
            if (isNullSafeLessTimeCompare(timeInDto.getEndTime(), timeInConfig.getEndTime())) {
                DeviceOnOffLogReportNewDTO newDto =
                        createNotAvailableEntry(deviceObj,
                                combineUtilDateAndTime(date, timeInDto.getEndTime()),
                                combineUtilDateAndTime(date, timeInConfig.getEndTime()),
                                reportToken, isCallFromPercentageReport);
                if (Objects.nonNull(newDto) && isDtoValidToAdd(newDto)) {
                    dtoWithPartialDayNA.add(newDto);
                }
            }
        }
    }

    private boolean isAfterOnBoardNotAvailableEntry(long deviceId, Date endTime) {
        Optional<DeviceData> deviceData =
                deviceDataRepository.findFirstByDeviceIdAndTimeOfStatusGreaterThanEqualOrderByTimeOfStatus(
                        deviceId, endTime);
        return deviceData.isPresent() && AFTER_ONBOARDING
                .equals(deviceData.get().getDeviceAdditionalInfo());
    }

    private DeviceOnOffLogReportNewDTO createNotAvailableEntry(Device deviceObj, Date stateTime,
                                                               Date endTime, String reportToken, boolean isCallFromPercentageReport) {
        return stateTime.equals(endTime) ? null : isCallFromPercentageReport ?
                createResponseDTORow(deviceObj, stateTime, endTime, reportToken,
                        DataCollectionEnum.OutputStatus.NOT_AVAILABLE,null) :
                createResponseDTORow(deviceObj, stateTime, endTime, reportToken,
                        DataCollectionEnum.OutputStatus.NOT_AVAILABLE,
                        isAfterOnBoardNotAvailableEntry(deviceObj.getDeviceId(), endTime)
                                ? AFTER_ONBOARDING.name()
                                : null);
    }
//    boolean isDtoValidToAdd(DeviceOnOffLogReportNewDTO dto) {
//        return isThisPastDate(combineUtilDateAndTime(dto.getDate(), dto.getStartTime()))
//                && isThisPastDate(combineUtilDateAndTime(dto.getDate(), dto.getEndTime()));
//    }

}

