package com.digital.signage.report;

import com.digital.signage.models.*;
import com.digital.signage.repository.PanelRepository;
import com.digital.signage.util.DataCollectionEnum;
import com.digital.signage.util.ReportsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.*;
import java.util.stream.Collectors;

import static com.digital.signage.util.DateUtils.*;

@Component
public class PanelInActiveReport extends ReportsUtils {
    @Autowired
    private PanelRepository panelRepository;


    public List<PanelLogReportResponseNewDTO> findAllInActiveEntryWithForPanelReport(
            PanelLogReportRequestDTO requestDTO, String reportToken, List<Long> validPanelIds,
            List<PanelLogReportResponseNewDTO> alreadyAddedEntriesInList,
            final Map<String, List<DataCollectionConfig>> dataCollectionConfigMapPerDate,
            final Map<Long, List<DataCollectionConfig>> defaultDataCollectionConfigMap) {
        List<Panel> validPanelList = panelRepository.findAllByPanelIds(validPanelIds);
        List<Device> deviceLists = findValidDeviceLists(validPanelList);
        Map<Long, List<DeviceStatusHistory>> historyMap =
                getInActiveStatusFromDBUnderConfig(deviceLists, requestDTO.getFromDate(),
                        requestDTO.getToDate());
        List<Date> validDatesList =
                findAllDatesBetweenTwoDateRange(requestDTO.getFromDate(), requestDTO.getToDate(), true);
        // make hast table to perform searching fast
        final Map<String, List<PanelLogReportResponseNewDTO>> entryDateWiseMap =
                getEntryDateWiseHashtableOfPanelDTO(alreadyAddedEntriesInList, validPanelList);
        List<PanelLogReportResponseNewDTO> freshDtoList = new ArrayList<>();
        if (!ObjectUtils.isEmpty(validPanelList)) {
            validPanelList.forEach(panel ->
                    validDatesList.forEach(date -> {
                        String keyForHash = getKeysForHashMap(panel.getDevice().getDeviceId(), date);
                        List<DataCollectionConfig> currentConfigs =
                                chooseConfigFromMapOrDefault(keyForHash, dataCollectionConfigMapPerDate,
                                        defaultDataCollectionConfigMap);
                        List<DeviceStatusHistory> statusHistories = historyMap.get(panel.getDeviceId());
                        List<PanelLogReportResponseNewDTO> logReportDTOSInList =
                                entryDateWiseMap.get(getKeysForHashMap(panel.getId(), date));
                        if (!ObjectUtils.isEmpty(logReportDTOSInList)) {
                            logReportDTOSInList.sort(
                                    Comparator.comparing(PanelLogReportResponseNewDTO::getStartTime));
                        } else {
                            logReportDTOSInList = new ArrayList<>();
                        }
                        if (!ObjectUtils.isEmpty(statusHistories)) {
                            for (DataCollectionConfig currentConfig : currentConfigs) {
                                List<DeviceStatusHistory> statusHistoriesPerDays =
                                        getPerDeviceDateWiseStatus(date, statusHistories, currentConfig);
                                List<PanelLogReportResponseNewDTO> logReportNewDTOSUnderConfig =
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
                                            addInActiveEntryInResponseDTOS(panel, logReportNewDTOSUnderConfig,
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

    private List<PanelLogReportResponseNewDTO> addInActiveEntryInResponseDTOS(
            Panel currentPanel, List<PanelLogReportResponseNewDTO> alreadyAddedPerDeviceList,
            List<DeviceStatusHistory> historiesList, String reportToken) {
        List<PanelLogReportResponseNewDTO> newFreshDtoList = new ArrayList<>();
        List<PanelLogReportResponseNewDTO> tempDtoList = new ArrayList<>();
        historiesList.sort(Comparator.comparing(DeviceStatusHistory::getDeviceId)
                .thenComparing(DeviceStatusHistory::getInActiveStartTime));
        if (!ObjectUtils.isEmpty(alreadyAddedPerDeviceList) || !ObjectUtils.isEmpty(historiesList)) {
            findInActiveReportRecursively(alreadyAddedPerDeviceList, tempDtoList, historiesList,
                    reportToken, currentPanel, newFreshDtoList);
        }
        return newFreshDtoList.stream().distinct().collect(Collectors.toList());
    }

    private void findInActiveReportRecursively(
            List<PanelLogReportResponseNewDTO> alreadyAddedPerDeviceList,
            List<PanelLogReportResponseNewDTO> tempDtoList, List<DeviceStatusHistory> historiesList,
            String reportToken, Panel currentPanel, List<PanelLogReportResponseNewDTO> newFreshDtoList) {
        int indexOfNewDto = 0, indexOfHistoryStatus = 0;
        List<DeviceStatusHistory> historiesTempList = new ArrayList<>();
        PanelLogReportResponseNewDTO currentInLoopDto;
        Iterator<DeviceStatusHistory> historyIterator = historiesList.listIterator();
        outerLoop:
        while (historyIterator.hasNext()) {
            DeviceStatusHistory history = historyIterator.next();
            Iterator<PanelLogReportResponseNewDTO> dtoIterator = alreadyAddedPerDeviceList.listIterator();
            while (dtoIterator.hasNext()) {
                PanelLogReportResponseNewDTO data = dtoIterator.next();
                //clear first tempList
                tempDtoList.clear();
                currentInLoopDto = data.createMyCopy();
                indexOfNewDto = alreadyAddedPerDeviceList.indexOf(currentInLoopDto);
                indexOfHistoryStatus = historiesList.indexOf(history);
                if (isFullyImmersed(data, history)) {
                    //remove data
                    dtoIterator.remove();
                } else if (isPartiallyAbove(data, history)) {
                    //split //add //remove
                    dtoIterator.remove();
                    newFreshDtoList.add(createResponseDTORow(currentPanel,
                            combineUtilDateAndTime(data.getDate(), data.getStartTime()),
                            history.getInActiveStartTime(), reportToken,
                            data.getPanelStatus(), data.getReasonForFailure()));
                    newFreshDtoList.add(addOneInActiveEntry(currentPanel, reportToken, history));
                } else if (isPartiallyBelow(data, history)) {
                    //split - remove data - add to l2 //remove history
                    dtoIterator.remove();
                    //create data in temp list
                    tempDtoList.add(createResponseDTORow(currentPanel, history.getInActiveEndTime(),
                            combineUtilDateAndTime(data.getDate(), data.getEndTime()), reportToken,
                            data.getPanelStatus(), data.getReasonForFailure()));
                    //create one inActive entry
                    newFreshDtoList.add(addOneInActiveEntry(currentPanel, reportToken, history));
                    historyIterator.remove();
                } else if (coverInActiveFully(data, history)) {
                    //split - add - remove data and history
                    String endTimeOfData = data.getEndTime();
                    dtoIterator.remove();
                    newFreshDtoList.add(createResponseDTORow(currentPanel,
                            combineUtilDateAndTime(data.getDate(), data.getStartTime()),
                            history.getInActiveStartTime(), reportToken,
                            data.getPanelStatus(), data.getReasonForFailure()));
                    newFreshDtoList.add(
                            addOneInActiveEntry(currentPanel, reportToken, history));
                    tempDtoList.add(
                            createResponseDTORow(currentPanel, history.getInActiveEndTime(),
                                    combineUtilDateAndTime(data.getDate(), endTimeOfData), reportToken,
                                    data.getPanelStatus(), data.getReasonForFailure()));
                    historyIterator.remove();
                } else {
                    //m5 direct add
                    dtoIterator.remove();
                    newFreshDtoList.add(data);
                }
                if (!ObjectUtils.isEmpty(tempDtoList)) {
                    break outerLoop;
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
            if (indexOfHistoryStatus > -1 && (historiesList.size() - indexOfHistoryStatus > 1)) {
                historiesTempList = new ArrayList<>(
                        historiesList.subList(indexOfHistoryStatus, historiesList.size()));
            } else {
                historiesTempList = new ArrayList<>(historiesList);
            }
            if (historiesList.size() == 0 && !ObjectUtils.isEmpty(tempDtoList)) {
                newFreshDtoList.addAll(tempDtoList);
            } else {
                findInActiveReportRecursively(alreadyAddedPerDeviceList, tempDtoList, historiesTempList,
                        reportToken, currentPanel, newFreshDtoList);
            }
        }
        // if history is not till added then add it.
        if (!ObjectUtils.isEmpty(historiesList)) {
            historiesList.forEach(
                    history -> newFreshDtoList.add(addOneInActiveEntry(currentPanel, reportToken, history)));
        }
    }

    private PanelLogReportResponseNewDTO addOneInActiveEntry(Panel currentPanel, String reportToken,
                                                             DeviceStatusHistory history) {
        return createResponseDTORow(currentPanel, history.getInActiveStartTime(),
                history.getInActiveEndTime(), reportToken,
                DataCollectionEnum.OutputStatus.INACTIVE);
    }

    private boolean isFullyImmersed(PanelLogReportResponseNewDTO data, DeviceStatusHistory history) {
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

    private boolean isPartiallyAbove(PanelLogReportResponseNewDTO data, DeviceStatusHistory history) {
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

    private boolean isPartiallyBelow(PanelLogReportResponseNewDTO data, DeviceStatusHistory history) {
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

    private boolean coverInActiveFully(PanelLogReportResponseNewDTO data,
                                       DeviceStatusHistory history) {
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
}

