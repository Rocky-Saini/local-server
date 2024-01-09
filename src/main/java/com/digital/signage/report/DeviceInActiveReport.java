package com.digital.signage.report;

import com.digital.signage.dto.DeviceLogReportRequestDTO;
import com.digital.signage.models.DeviceOnOffLogReportNewDTO;
import com.digital.signage.models.DataCollectionConfig;
import com.digital.signage.models.Device;
import com.digital.signage.models.DeviceStatusHistory;
import com.digital.signage.repository.DeviceRepository;
import com.digital.signage.repository.DeviceStatusHistoryRepository;
import com.digital.signage.util.BeanCopyUtil;
import com.digital.signage.util.DataCollectionEnum;
import com.digital.signage.util.ReportsUtils;
import com.digital.signage.util.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.*;
import java.util.stream.Collectors;

import static com.digital.signage.util.CommonUtils.isThisTimeBetweenTheseTimes;
import static com.digital.signage.util.DateUtils.*;

@Component
public class DeviceInActiveReport extends ReportsUtils {
    @Autowired
    private DeviceRepository deviceRepository;
    @Autowired
    private DeviceStatusHistoryRepository historyRepository;

    public List<DeviceOnOffLogReportNewDTO> findAllInActiveEntryWithForDeviceReport(
            DeviceLogReportRequestDTO requestDTO, String reportToken, List<Long> validDeviceIds,
            List<DeviceOnOffLogReportNewDTO> alreadyAddedEntriesInList,
            final Map<String, List<DataCollectionConfig>> dataCollectionConfigMapPerDay,
            final Map<Long, List<DataCollectionConfig>> defaultDataCollectionConfigMap) {
        List<Device> deviceList = deviceRepository.findByDeviceIds(validDeviceIds);
        Map<Long, List<DeviceStatusHistory>> historyMap =
                getInActiveStatusFromDBUnderConfig(deviceList, requestDTO.getFromDate(),
                        requestDTO.getToDate());
        List<Date> validDatesList =
                findAllDatesBetweenTwoDateRange(requestDTO.getFromDate(), requestDTO.getToDate(), true);
        // make hast table to perform searching fast
        final Map<String, List<DeviceOnOffLogReportNewDTO>> entryDateWiseMap =
                getEntryDateWiseHashtableOfDeviceDTO(alreadyAddedEntriesInList, deviceList);
        List<DeviceOnOffLogReportNewDTO> freshDtoList = new ArrayList<>();
        if (!ObjectUtils.isEmpty(deviceList)) {
            deviceList.forEach(device ->
                    validDatesList.forEach(date -> {
                        String keysForHashTable = getKeysForHashMap(device.getDeviceId(), date);
                        List<DataCollectionConfig> currentConfigs =
                                chooseConfigFromMapOrDefault(keysForHashTable, dataCollectionConfigMapPerDay,
                                        defaultDataCollectionConfigMap);
                        List<DeviceStatusHistory> statusHistories = historyMap.get(device.getDeviceId());
                        List<DeviceOnOffLogReportNewDTO> logReportDTOSInList =
                                entryDateWiseMap.get(getKeysForHashMap(device.getDeviceId(), date));
                        if (!ObjectUtils.isEmpty(logReportDTOSInList)) {
                            logReportDTOSInList.sort(
                                    Comparator.comparing(DeviceOnOffLogReportNewDTO::getStartTime));
                        } else {
                            logReportDTOSInList = new ArrayList<>();
                        }
                        if (!ObjectUtils.isEmpty(statusHistories)) {
                            for (DataCollectionConfig currentConfig : currentConfigs) {
                                List<DeviceStatusHistory> statusHistoriesPerDays =
                                        getPerDeviceDateWiseStatus(date, statusHistories, currentConfig);
                                List<DeviceOnOffLogReportNewDTO> logReportNewDTOSUnderConfig =
                                        logReportDTOSInList.stream()
                                                .filter(
                                                        dto -> dto.getStartTime().compareTo(currentConfig.getPanelOnTime())
                                                                >= 0
                                                                && dto.getEndTime().compareTo(currentConfig.getPanelOffTime())
                                                                <= 0
                                                )
                                                .collect(Collectors.toList());
                                if (!ObjectUtils.isEmpty(statusHistoriesPerDays)) {
                                    freshDtoList.addAll(
                                            addInActiveEntryInResponseDTOS(device, logReportNewDTOSUnderConfig,
                                                    statusHistoriesPerDays, reportToken));
                                } else {
                                    freshDtoList.addAll(logReportNewDTOSUnderConfig);
                                }
                            }
                        } else {
                            freshDtoList.addAll(logReportDTOSInList);
                        }
                    }));
        }
        return freshDtoList.stream().distinct().collect(Collectors.toList());
    }

    private List<DeviceOnOffLogReportNewDTO> addInActiveEntryInResponseDTOS(
            Device currentDevice, List<DeviceOnOffLogReportNewDTO> alreadyAddedPerDeviceList,
            List<DeviceStatusHistory> historiesList, String reportToken) {
        List<DeviceOnOffLogReportNewDTO> newFreshDtoList = new ArrayList<>();
        List<DeviceOnOffLogReportNewDTO> tempDtoList = new ArrayList<>();
        historiesList.sort(Comparator.comparing(DeviceStatusHistory::getDeviceId)
                .thenComparing(DeviceStatusHistory::getInActiveStartTime));
        if (!ObjectUtils.isEmpty(historiesList) || !ObjectUtils.isEmpty(alreadyAddedPerDeviceList)) {
            findInActiveReportRecursively(alreadyAddedPerDeviceList, tempDtoList, historiesList,
                    reportToken, currentDevice, newFreshDtoList);
        }
        return newFreshDtoList.stream().distinct().collect(Collectors.toList());
    }

    private void findInActiveReportRecursively(
            List<DeviceOnOffLogReportNewDTO> alreadyAddedPerDeviceList,
            List<DeviceOnOffLogReportNewDTO> tempDtoList, List<DeviceStatusHistory> historiesList,
            String reportToken, Device currentDevice, List<DeviceOnOffLogReportNewDTO> newFreshDtoList) {
        int indexOfNewDto = 0, indexOfHistoryStatus = 0;
        List<DeviceStatusHistory> historiesTempList = new ArrayList<>();
        DeviceOnOffLogReportNewDTO currentInLoopDto;
        //create iterator of history list to iterate and remove after used.
        Iterator<DeviceStatusHistory> historyIterator = historiesList.listIterator();
        outerHistoryLoop:
        while (historyIterator.hasNext()) {
            DeviceStatusHistory history = historyIterator.next();
            Iterator<DeviceOnOffLogReportNewDTO> dtoIterator = alreadyAddedPerDeviceList.listIterator();
            while (dtoIterator.hasNext()) {
                DeviceOnOffLogReportNewDTO data = dtoIterator.next();
                //clear first tempList
                tempDtoList = new ArrayList<>();
                currentInLoopDto = data.createMyCopy();
                indexOfNewDto = alreadyAddedPerDeviceList.indexOf(currentInLoopDto);
                indexOfHistoryStatus = historiesList.indexOf(history);
                //in case when history is before data then remove history also
                if (combineUtilDateAndTime(data.getDate(), data.getStartTime()).after(
                        history.getInActiveEndTime())) {
                    DeviceOnOffLogReportNewDTO toBeAddInActiveDTO =
                            addOneInActiveEntry(currentDevice, reportToken, history);
                    newFreshDtoList.add(toBeAddInActiveDTO);
                    historyIterator.remove();
                    break;
                } else if (isFullyImmersed(data, history)) {
                    //remove data
                    dtoIterator.remove();
                } else if (isPartiallyAbove(data, history)) {
                    //split //add //remove data
                    dtoIterator.remove();
                    DeviceOnOffLogReportNewDTO toBeAddInListDTO = createResponseDTORow(currentDevice,
                            combineUtilDateAndTime(data.getDate(), data.getStartTime()),
                            history.getInActiveStartTime(), reportToken,
                            data.getDeviceStatus(),null);
                    newFreshDtoList.add(toBeAddInListDTO);
                    DeviceOnOffLogReportNewDTO toBeAddInActiveDTO =
                            addOneInActiveEntry(currentDevice, reportToken, history);
                    newFreshDtoList.add(toBeAddInActiveDTO);
                } else if (isPartiallyBelow(data, history)) {
                    //split - remove data - add to l2 -remove history
                    dtoIterator.remove();
                    //create data in temp list
                    tempDtoList.add(createResponseDTORow(currentDevice, history.getInActiveEndTime(),
                            combineUtilDateAndTime(data.getDate(), data.getEndTime()), reportToken,
                            data.getDeviceStatus(),null));
                    //create one inActive entry
                    DeviceOnOffLogReportNewDTO toBeAddInActiveDTO =
                            addOneInActiveEntry(currentDevice, reportToken, history);
                    newFreshDtoList.add(toBeAddInActiveDTO);
                    historyIterator.remove();
                } else if (coverInActiveFully(data, history)) {
                    //split - add - remove data - remove history
                    String endTimeOfData = data.getEndTime();
                    dtoIterator.remove();
                    DeviceOnOffLogReportNewDTO toBeAddInListDTO = createResponseDTORow(currentDevice,
                            combineUtilDateAndTime(data.getDate(), data.getStartTime()),
                            history.getInActiveStartTime(), reportToken,
                            data.getDeviceStatus(),null);
                    newFreshDtoList.add(toBeAddInListDTO);
                    tempDtoList.add(
                            createResponseDTORow(currentDevice, history.getInActiveEndTime(),
                                    combineUtilDateAndTime(data.getDate(), endTimeOfData), reportToken,
                                    data.getDeviceStatus(),null));
                    DeviceOnOffLogReportNewDTO toBeAddInActiveDTO =
                            addOneInActiveEntry(currentDevice, reportToken, history);
                    newFreshDtoList.add(toBeAddInActiveDTO);
                    historyIterator.remove();
                } else {
                    //m5 direct add and remove data
                    dtoIterator.remove();
                    newFreshDtoList.add(data);
                }
                // if any data in temporary list then exit from loop and call this method again.
                if (!ObjectUtils.isEmpty(tempDtoList)) {
                    break outerHistoryLoop;
                }
            }
        }
        if (!ObjectUtils.isEmpty(tempDtoList)) {
            //copy all remaining temporary list.
            if (indexOfNewDto > -1) {
                tempDtoList.addAll(
                        alreadyAddedPerDeviceList.subList(indexOfNewDto,
                                alreadyAddedPerDeviceList.size()));
            }
            //reset list value;
            alreadyAddedPerDeviceList = new ArrayList<>(tempDtoList);
            //check history list if value in temp list then remove one entry from historyList on each iteration.
            //when history list size is one then go out size of whole loop
            if (indexOfHistoryStatus > -1 && historiesList.size() > 0) {
                historiesTempList = new ArrayList<>(
                        historiesList.subList(indexOfHistoryStatus, historiesList.size()));
            } else {
                historiesTempList = new ArrayList<>(historiesList);
            }

            //here size of history list is one then copy all element of temp to fresh list and  exit from whole algorithm.
            if (historiesList.size() == 0 && !ObjectUtils.isEmpty(tempDtoList)) {
                newFreshDtoList.addAll(tempDtoList);
            } else {
                findInActiveReportRecursively(alreadyAddedPerDeviceList, tempDtoList, historiesTempList,
                        reportToken, currentDevice, newFreshDtoList);
            }
        }
        //add all remaining history in list.
        if (!ObjectUtils.isEmpty(historiesList)) {
            historiesList.forEach(history ->
                    newFreshDtoList.add(addOneInActiveEntry(currentDevice, reportToken, history))
            );
        }
    }

    private boolean isFullyImmersed(DeviceOnOffLogReportNewDTO data, DeviceStatusHistory history) {
        if (data != null && history != null && isBothDateOfSameDate(history.getInActiveStartTime(),
                data.getDate())) {
            if (isTheseDatesEqualsOrAfter(combineUtilDateAndTime(data.getDate(), data.getStartTime()),
                    history.getInActiveStartTime())) {
                return history.getInActiveEndTime() != null && isTheseDatesEqualsOrBefore(
                        combineUtilDateAndTime(data.getDate(), data.getEndTime()),
                        history.getInActiveEndTime());
            }
        }

        return false;
    }

    private boolean isPartiallyAbove(DeviceOnOffLogReportNewDTO data, DeviceStatusHistory history) {
        if (data != null && history != null && isBothDateOfSameDate(history.getInActiveStartTime(),
                data.getDate())) {
            if (combineUtilDateAndTime(data.getDate(), data.getStartTime()).before(
                    history.getInActiveStartTime())) {
                return history.getInActiveEndTime() != null && isTheseDatesEqualsOrBefore(
                        combineUtilDateAndTime(data.getDate(),
                                data.getEndTime()), history.getInActiveEndTime()) && combineUtilDateAndTime(
                        data.getDate(), data.getEndTime()).after(history.getInActiveStartTime());
            }
        }
        return false;
    }

    private boolean isPartiallyBelow(DeviceOnOffLogReportNewDTO data, DeviceStatusHistory history) {
        if (data != null && history != null && isBothDateOfSameDate(history.getInActiveStartTime(),
                data.getDate())) {
            if (isTheseDatesEqualsOrAfter(combineUtilDateAndTime(data.getDate(), data.getStartTime()),
                    history.getInActiveStartTime())) {
                return history.getInActiveEndTime() != null
                        && combineUtilDateAndTime(data.getDate(),
                        data.getEndTime()).after(history.getInActiveStartTime())
                        && combineUtilDateAndTime(
                        data.getDate(), data.getEndTime()).after(history.getInActiveEndTime())
                        && combineUtilDateAndTime(
                        data.getDate(), data.getStartTime()).before(history.getInActiveEndTime());
            }
        }
        return false;
    }

    private boolean coverInActiveFully(DeviceOnOffLogReportNewDTO data, DeviceStatusHistory history) {
        if (data != null && history != null && isBothDateOfSameDate(history.getInActiveStartTime(),
                data.getDate())) {
            if (combineUtilDateAndTime(data.getDate(), data.getStartTime()).before(
                    history.getInActiveStartTime())) {
                return history.getInActiveEndTime() != null && combineUtilDateAndTime(data.getDate(),
                        data.getEndTime()).after(history.getInActiveEndTime());
            }
        }
        return false;
    }

    private DeviceOnOffLogReportNewDTO addOneInActiveEntry(Device currentDevice, String reportToken,
                                                           DeviceStatusHistory history) {
        return createResponseDTORow(currentDevice, history.getInActiveStartTime(),
                history.getInActiveEndTime(), reportToken,
                DataCollectionEnum.OutputStatus.INACTIVE,null);
    }
//    Map<Long, List<DeviceStatusHistory>> getInActiveStatusFromDBUnderConfig(
//            List<Device> validDeviceList,
//            Date fromDate,
//            Date toDate
//    ) {
//        List<Long> validDeviceIds =
//                validDeviceList.stream().map(Device::getDeviceId).collect(Collectors.toList());
//        List<DeviceStatusHistory> deviceStatusHistories =
//                historyRepository.findAllStatusHistoryByDeviceIdAndDates(
//                        validDeviceIds,
//                        floorDate(fromDate),
//                        ceilDate(toDate)
//                );
//        //if deviceStatus history not found then find all device which is Currently inactive then find its status.
//        if (ObjectUtils.isEmpty(deviceStatusHistories)) {
//            List<Device> currentlyInActiveDevice = validDeviceList.stream()
//                    .filter(device -> Status.INACTIVE.equals(device.getStatus()))
//                    .collect(
//                            Collectors.toList());
//            if (!ObjectUtils.isEmpty(currentlyInActiveDevice)) {
//                currentlyInActiveDevice.forEach(device -> {
//                    DeviceStatusHistory deviceStatusHistory =
//                            historyRepository.findStatusHistoryByDeviceId(device.getDeviceId());
//                    if (deviceStatusHistory != null) {
//                        deviceStatusHistories.add(deviceStatusHistory);
//                    }
//                });
//            }
//        }
//        Map<Long, List<DeviceStatusHistory>> historyMap =
//                new HashMap<>(deviceStatusHistories.size());
//        //commenting this due to open if partial data under InActive then select in list.
//    /*//load all dataCollectionConfig for panelOnOff Time.
//    final Hashtable<String, List<DataCollectionConfig>> businessConfigMap = new Hashtable<>();
//    if (!ObjectUtils.isEmpty(deviceStatusHistories)) {
//      businessConfigMap.putAll(
//          getDataCollectionConfigTimesList(fromDate, toDate, validDeviceIds));
//    }
//    //default config to get panel On OFF time on basis of global config
//    final List<DataCollectionConfig> defaultDataCollectionConfig =
//        getDefaultDataCollectionConfigTime(
//            validDeviceIds);*/
//
//        //map all status in hash table to search fast
//        deviceStatusHistories.forEach(statusHistory -> {
//            assert statusHistory != null;
//            List<DeviceStatusHistory> statusHistories =
//                    historyMap.containsKey(statusHistory.getDeviceId()) ? historyMap.get(
//                            statusHistory.getDeviceId())
//                            : new ArrayList<>();
//            //commenting this due to open if partial data under InActive then select in list.
//      /*String keyForHashMap =
//          getKeysForHashMap(statusHistory.getDeviceId(), statusHistory.getInActiveStartTime());
//      boolean isStatusUnderConfig = true;
//      for (DataCollectionConfig currentConfig : chooseConfigFromMapOrDefault(keyForHashMap,
//          businessConfigMap, defaultDataCollectionConfig)) {
//        if (currentConfig != null
//            && currentConfig.getPanelOnTime() != null
//            && currentConfig.getPanelOffTime() != null) {
//          isStatusUnderConfig =
//              CommonUtils.isThisTimeBetweenTheseTimes(statusHistory.getInActiveStartTime(),
//                  currentConfig.getPanelOnTime(), currentConfig.getPanelOffTime());
//          if (isStatusUnderConfig) break;
//        }
//      }
//      if (statusHistory.getInActiveEndTime() != null && isStatusUnderConfig) {
//        keyForHashMap =
//            getKeysForHashMap(statusHistory.getDeviceId(), statusHistory.getInActiveEndTime());
//        for (DataCollectionConfig currentConfig : chooseConfigFromMapOrDefault(keyForHashMap,
//            businessConfigMap, defaultDataCollectionConfig)) {
//          if (currentConfig != null
//              && currentConfig.getPanelOnTime() != null
//              && currentConfig.getPanelOffTime() != null) {
//            isStatusUnderConfig =
//                CommonUtils.isThisTimeBetweenTheseTimes(statusHistory.getInActiveEndTime(),
//                    currentConfig.getPanelOnTime(), currentConfig.getPanelOffTime());
//            if (isStatusUnderConfig) break;
//          }
//        }
//      }
//      if (isStatusUnderConfig)*/
//            statusHistories.add(statusHistory);
//            historyMap.put(statusHistory.getDeviceId(), statusHistories);
//        });
//        return historyMap;
//    }
//    List<DeviceStatusHistory> getPerDeviceDateWiseStatus(
//            Date date,
//            List<DeviceStatusHistory> statusHistories,
//            DataCollectionConfig currentConfig
//    ) {
//        List<DeviceStatusHistory> statusHistoriesPerDays = new ArrayList<>();
//        Date configOnDate = combineUtilDateAndTime(date, currentConfig.getPanelOnTime());
//        Date configOffDate = combineUtilDateAndTime(date, currentConfig.getPanelOffTime());
//        statusHistories.forEach(historyFromListItr -> {
//            DeviceStatusHistory history = new DeviceStatusHistory();
//            BeanCopyUtil.copy(history, historyFromListItr);
//            history.setActivityId(null);
//            boolean isAmSameDayHistory = false;
//            if (history.getInActiveEndTime() != null
//                    && isBothDateOfSameDate(
//                    history.getInActiveStartTime(), history.getInActiveEndTime())
//                    && isBothDateOfSameDate(date, history.getInActiveStartTime())) {
//                //SER-2282 case is same day in active but in active start time is less than panel on time.
//                //so  now if config on date between inActiveStartTime and inActiveEndTime  then history date will be start from panel on time.
//                if (isMeBetweenTheseTwoDates(configOnDate, history.getInActiveStartTime(),
//                        history.getInActiveEndTime()
//                )) {
//                    history.setInActiveStartTime(configOnDate);
//                }
//                isAmSameDayHistory = true;
//            } else if (history.getInActiveEndTime() != null
//                    && !isBothDateOfSameDate(
//                    history.getInActiveStartTime(), history.getInActiveEndTime())
//                    && isBothDateOfSameDate(date, history.getInActiveStartTime())) {
//                if (currentConfig != null
//                        && currentConfig.getPanelOffTime() != null) {
//                    history.setInActiveEndTime(configOffDate);
//                }
//                isAmSameDayHistory = true;
//            } else if (history.getInActiveEndTime() != null
//                    && !isBothDateOfSameDate(
//                    history.getInActiveStartTime(), history.getInActiveEndTime())
//                    && isBothDateOfSameDate(date, history.getInActiveEndTime())
//                    //if case like one day before device inactive and reActive before panel on time on next day then do not add in list
//                    && isTheseDatesEqualsOrBefore(configOnDate, history.getInActiveEndTime())) {
//                if (currentConfig != null
//                        && currentConfig.getPanelOnTime() != null) {
//                    history.setInActiveStartTime(configOnDate);
//                }
//                //if mid night cross over [10:00-9:45] then device is inactive yesterday but active on 10:12 means
//                // after morning 9:45   then it does change inActiveEndTime as panel off time means 9:45
//                if (isTheseDatesEqualsOrAfter(history.getInActiveEndTime(), configOffDate)) {
//                    history.setInActiveEndTime(configOffDate);
//                }
//                isAmSameDayHistory = true;
//            } else if (history.getInActiveEndTime() == null && !isBothDateOfSameDate(
//                    history.getInActiveStartTime(), date) && date.after(history.getInActiveStartTime())) {
//                if (currentConfig != null
//                        && currentConfig.getPanelOffTime() != null) {
//                    String endTimeForThis = isCurrentTimeLessThanConfigForToday(date, currentConfig) ?
//                            getCurrentTimeWithoutDate() : currentConfig.getPanelOffTime();
//                    history.setInActiveEndTime(
//                            combineUtilDateAndTime(date, endTimeForThis));
//                }
//                if (currentConfig != null
//                        && currentConfig.getPanelOnTime() != null) {
//                    history.setInActiveStartTime(configOnDate);
//                }
//                isAmSameDayHistory = true;
//            } else if (history.getInActiveEndTime() != null
//                    && isBothDateOfSameDate(
//                    history.getInActiveEndTime(), date)) {
//                isAmSameDayHistory = true;
//            } else if (history.getInActiveEndTime() != null && isMeBetweenTheseTwoDates(
//                    date,
//                    history.getInActiveStartTime(),
//                    history.getInActiveEndTime()
//            )) {
//                if (currentConfig != null
//                        && currentConfig.getPanelOnTime() != null
//                        && currentConfig.getPanelOffTime() != null) {
//                    history.setInActiveStartTime(configOnDate);
//                    history.setInActiveEndTime(configOffDate);
//                }
//                isAmSameDayHistory = true;
//            } else if (history.getInActiveEndTime() == null && isBothDateOfSameDate(
//                    history.getInActiveStartTime(), date) && isThisTimeBetweenTheseTimes(
//                    history.getInActiveStartTime(), currentConfig.getPanelOnTime(),
//                    currentConfig.getPanelOffTime()
//            )) {
//                if (currentConfig != null
//                        && currentConfig.getPanelOffTime() != null) {
//                    String endTimeForThis = isCurrentTimeLessThanConfigForToday(date, currentConfig) ?
//                            getCurrentTimeWithoutDate() : currentConfig.getPanelOffTime();
//                    history.setInActiveEndTime(
//                            combineUtilDateAndTime(date, endTimeForThis));
//                }
//                isAmSameDayHistory = true;
//            }
//            //check this statusHistory under config then add otherwise ignore
//            boolean isStatusUnderConfig =
//                    isMeBetweenTheseTwoDates(history.getInActiveStartTime(), configOnDate, configOffDate)
//                            && isMeBetweenTheseTwoDates(history.getInActiveEndTime(), configOnDate, configOffDate
//                    );
//            if (isAmSameDayHistory && isStatusUnderConfig) statusHistoriesPerDays.add(history);
//        });
//        statusHistoriesPerDays.sort(
//                Comparator.comparing(DeviceStatusHistory::getInActiveStartTime));
//        return statusHistoriesPerDays;
//    }
//    boolean isCurrentTimeLessThanConfigForToday(
//            Date date,
//            DataCollectionConfig currentDateConfig
//    ) {
//        return isBothDateOfSameDate(date, new Date()) && ((currentDateConfig == null
//                || currentDateConfig.getPanelOffTime() == null)
//                || isBeforeTimeInStr(getCurrentTimeWithoutDate(), currentDateConfig.getPanelOffTime()));
//    }
}

