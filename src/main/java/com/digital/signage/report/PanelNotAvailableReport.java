package com.digital.signage.report;

import com.digital.signage.dto.ConfigTimeDTO;
import com.digital.signage.models.DataCollectionConfig;
import com.digital.signage.models.Panel;
import com.digital.signage.models.PanelLogReportResponseNewDTO;
import com.digital.signage.models.PanelStatus;
import com.digital.signage.repository.PanelRepository;
import com.digital.signage.repository.PanelStatusRepository;
import com.digital.signage.util.DataCollectionEnum;
import com.digital.signage.util.ReportsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

import static com.digital.signage.util.CommonUtils.isNullSafeLessTimeCompare;
import static com.digital.signage.util.DateUtils.*;

@Component
public class PanelNotAvailableReport extends ReportsUtils {
    @Autowired
    private PanelRepository panelRepository;
    @Autowired
    private PanelStatusRepository panelStatusRepository;

    public List<PanelLogReportResponseNewDTO> addNotAvailableEntryInResponseDTOS(
            List<Date> filterDateRange, String reportToken, List<Long> validPanelIds,
            List<PanelLogReportResponseNewDTO> responseDTOS, boolean isCallFromPercentageReport,
            final Map<String, List<DataCollectionConfig>> dataCollectionConfigMapPerDate ,
            final Map<Long, List<DataCollectionConfig>> defaultDataCollectionMap) {
        responseDTOS.sort(Comparator.comparing(PanelLogReportResponseNewDTO::getPanelId));
        List<Panel> panelList = panelRepository.findAllByPanelIds(validPanelIds);
        if (!ObjectUtils.isEmpty(panelList)) {
            if (!ObjectUtils.isEmpty(filterDateRange)) {
                final Map<String, List<PanelLogReportResponseNewDTO>> entryDateWiseMap =
                        getEntryDateWiseHashtableOfPanelDTO(responseDTOS, panelList);
                //create entry for whole date which date data is not available.
                final List<PanelLogReportResponseNewDTO> dtoFullDayNotAvailable = new ArrayList<>();
                final List<PanelLogReportResponseNewDTO> dtoWithPartialDayNA = new ArrayList<>();
                if (!ObjectUtils.isEmpty(panelList) && !ObjectUtils.isEmpty(filterDateRange)) {
                    filterDateRange.forEach(date ->
                            panelList.forEach(panel -> {
                                ///add full day entry
                                String keysForHash = getKeysForHashMap(panel.getId(), date);
                                List<DataCollectionConfig> collectionConfigs =
                                        chooseConfigFromMapOrDefault(
                                                getKeysForHashMap(panel.getDevice().getDeviceId(), date),
                                                dataCollectionConfigMapPerDate,
                                                defaultDataCollectionMap);
                                for (DataCollectionConfig currentDateConfig : collectionConfigs) {
                                    if (!entryDateWiseMap.containsKey(keysForHash)) {
                                        addFullDayNotAvailableEntriesInList(currentDateConfig, panel, date, reportToken,
                                                dtoFullDayNotAvailable);
                                    } else {
                                        List<PanelLogReportResponseNewDTO> dtoOfThatDayEntries =
                                                entryDateWiseMap.get(keysForHash);
                                        if (!ObjectUtils.isEmpty(dtoOfThatDayEntries)) {
                                            dtoOfThatDayEntries = dtoOfThatDayEntries.stream()
                                                    .filter(dto ->
                                                            isaTimeBetweenTheseTimes(dto.getStartTime(),
                                                                    currentDateConfig.getPanelOnTime(),
                                                                    currentDateConfig.getPanelOffTime(), true)
                                                    )
                                                    .filter(dto ->
                                                            isaTimeBetweenTheseTimes(dto.getEndTime(),
                                                                    currentDateConfig.getPanelOnTime(),
                                                                    currentDateConfig.getPanelOffTime(), true))
                                                    .collect(Collectors.toList());
                                            if (ObjectUtils.isEmpty(dtoOfThatDayEntries)) {
                                                addFullDayNotAvailableEntriesInList(currentDateConfig, panel, date,
                                                        reportToken,
                                                        dtoFullDayNotAvailable);
                                            } else {
                                                addPartialDayNotAvailableEntriesInDTOs(dtoOfThatDayEntries,
                                                        currentDateConfig,
                                                        panel, date, reportToken, dtoWithPartialDayNA);
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
                if (!ObjectUtils.isEmpty(dtoFullDayNotAvailable)) {
                    responseDTOS.addAll(dtoFullDayNotAvailable);
                }
            }
        }
        return isCallFromPercentageReport ? responseDTOS : addOnAfterBoardEntriesInDTOS(responseDTOS);
    }

    private void addFullDayNotAvailableEntriesInList(DataCollectionConfig currentDateConfig,
                                                     Panel panel, Date date, String reportToken,
                                                     List<PanelLogReportResponseNewDTO> reportNewDTOS) {
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
                panel.getDeletedOn() != null ? (date.after(panel.getCreatedOn())
                        && date.before(panel.getDeletedOn())) : date.after(panel.getCreatedOn());
        if (isUnderConfig && isThisPastDate(startTime) && isThisPastDate(endTime)) {
            PanelLogReportResponseNewDTO newDto =
                    createResponseDTORow(panel, startTime, endTime, reportToken,
                            DataCollectionEnum.OutputStatus.NOT_AVAILABLE);
            reportNewDTOS.add(newDto);
        }
    }

    private void addPartialDayNotAvailableEntriesInDTOs(
            List<PanelLogReportResponseNewDTO> entriesInThisDate, DataCollectionConfig currentDateConfig,
            Panel panel, Date date, String reportToken,
            List<PanelLogReportResponseNewDTO> dtoWithPartialDayNA) {
        final ConfigTimeDTO timeInConfig = new ConfigTimeDTO("00:00:00", "23:59:59");
        final ConfigTimeDTO preConfigDto = new ConfigTimeDTO();
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
            entriesInThisDate.sort(Comparator.comparing(PanelLogReportResponseNewDTO::getStartTime));
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
                        PanelLogReportResponseNewDTO newDto =
                                createResponseDTORow(panel,
                                        combineUtilDateAndTime(date, preConfigDto.getEndTime()),
                                        combineUtilDateAndTime(date, onOffLogDTO.getStartTime()),
                                        reportToken,
                                        DataCollectionEnum.OutputStatus.NOT_AVAILABLE);
                        dtoWithPartialDayNA.add(newDto);
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
                PanelLogReportResponseNewDTO newDto =
                        createResponseDTORow(panel,
                                combineUtilDateAndTime(date, timeInConfig.getStartTime()),
                                combineUtilDateAndTime(date, timeInDto.getStartTime()),
                                reportToken,
                                DataCollectionEnum.OutputStatus.NOT_AVAILABLE);
                if (isDtoValidToAdd(newDto)) {
                    dtoWithPartialDayNA.add(newDto);
                }
            }
            if (isNullSafeLessTimeCompare(timeInDto.getEndTime(), timeInConfig.getEndTime())) {
                PanelLogReportResponseNewDTO newDto =
                        createResponseDTORow(panel,
                                combineUtilDateAndTime(date, timeInDto.getEndTime()),
                                combineUtilDateAndTime(date, timeInConfig.getEndTime()),
                                reportToken,
                                DataCollectionEnum.OutputStatus.NOT_AVAILABLE);
                if (isDtoValidToAdd(newDto)) {
                    dtoWithPartialDayNA.add(newDto);
                }
            }
        }
    }

    private List<PanelLogReportResponseNewDTO> addOnAfterBoardEntriesInDTOS(
            List<PanelLogReportResponseNewDTO> dtos) {
        List<Date> endTimeLists = dtos.parallelStream()
                .map(dto -> combineUtilDateAndTime(dto.getDate(), dto.getEndTime()))
                .distinct()
                .collect(Collectors.toList());
        List<Long> panelIds =
                dtos.parallelStream().map(PanelLogReportResponseNewDTO::getPanelId)
                        .distinct()
                        .collect(Collectors.toList());
        List<PanelStatus> panelStatuses;
        if (panelIds.size() == 0 || endTimeLists.size() == 0) {
            panelStatuses = new ArrayList<>(0);
        } else {
            panelStatuses =
                    panelStatusRepository.findAllByPanelIdInAndTimeOfStatusInAndPanelAdditionalInfoIsNotNull(
                            panelIds, endTimeLists);
        }

        Map<Long, PanelStatus> panelStatusMap = new HashMap<>();
        panelStatuses.forEach(
                panelStatus -> panelStatusMap.put(generateKeysForPanelStatus(panelStatus.getPanel().getId(),
                        panelStatus.getTimeOfStatus()), panelStatus));
        //Map<Long, PanelStatus> panelStatusMap = panelStatuses.stream()
        //    .collect(Collectors.toMap(
        //        panelStatus -> generateKeysForPanelStatus(panelStatus.getPanel().getId(),
        //            panelStatus.getTimeOfStatus()), Function.identity()));
        dtos.forEach(dto -> {
            long key = generateKeysForPanelStatus(dto.getPanelId(),
                    combineUtilDateAndTime(dto.getDate(), dto.getEndTime()));
            PanelStatus panelStatus = panelStatusMap.get(key);
            boolean isAfterOnBoard =
                    Objects.nonNull(panelStatus) && DataCollectionEnum.AdditionalInfo.AFTER_ONBOARDING.equals(
                            panelStatus.getPanelAdditionalInfo());
            if (isAfterOnBoard) {
                dto.setReasonForFailure(DataCollectionEnum.AdditionalInfo.AFTER_ONBOARDING.name());
            }
        });
        return dtos;
    }

    private Long generateKeysForPanelStatus(Long panelId, Date timeOfStatus) {
        return panelId + timeOfStatus.getTime();
    }
}

