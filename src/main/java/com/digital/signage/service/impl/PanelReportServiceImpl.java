package com.digital.signage.service.impl;

import com.digital.signage.configuration.ConfigSplitter;
import com.digital.signage.dto.DeviceLogReportRequestDTO;
import com.digital.signage.dto.PercentageParamDTO;
import com.digital.signage.models.*;
import com.digital.signage.report.PanelInActiveReport;
import com.digital.signage.report.PanelNotApplicableReport;
import com.digital.signage.report.PanelNotAvailableReport;
import com.digital.signage.repository.*;
import com.digital.signage.service.DeviceConfigService;
import com.digital.signage.service.PanelReportService;
import com.digital.signage.util.*;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Nullable;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import static com.digital.signage.util.CommonUtils.*;
import static com.digital.signage.util.DataCollectionEnum.AdditionalInfo.AFTER_ONBOARDING;
import static com.digital.signage.util.DataCollectionEnum.OutputStatus.*;
import static com.digital.signage.util.DataCollectionEnum.PanelStatus.DATA_UNAVAILABLE_DUE_TO_TIME_CHANGES;
import static com.digital.signage.util.DateUtils.*;
import static com.digital.signage.util.ReportConstants.*;
import static com.digital.signage.util.ReportsUtils.*;

@Service
public class PanelReportServiceImpl extends BaseServiceImpl implements PanelReportService {

    @Autowired
    private PanelRepository panelRepository;
    @Autowired
    private PanelStatusRepository panelStatusRepository;
    @Autowired
    private  DataCollectionConfigRepository dataCollectionConfigRepository;
    @Autowired
    private DeviceConfigService deviceConfigService;
    @Autowired
    private PanelInActiveReport panelInActiveReport;
    @Autowired
    private PanelNotApplicableReport panelNotApplicableReport;
    @Autowired
    private PanelNotAvailableReport panelNotAvailableReport;
    @Autowired
    private PanelReportRepository panelReportRepository;
    @Autowired
    private PanelPercentLogRepository panelPercentLogRepository;

    @Override
    public Response<?> savePanelLogReport(PanelLogReportRequestDTO panelRequestDTO, boolean isCallFromPercentageReport,String reportToken) throws IOException {

        PanelLogReportRequestDTO requestDTO = new PanelLogReportRequestDTO();
        if(panelRequestDTO!=null){
            requestDTO.setFromDate(panelRequestDTO.getFromDate());
            requestDTO.setToDate(panelRequestDTO.getToDate());
        }else {
            requestDTO.setFromDate(new Date());
            requestDTO.setToDate(new Date());
        }

        List<Panel> validPanels = (List<Panel>) panelRepository.findAll();

        List<Long> validPanelIds = validPanels.stream().map(Panel::getId).collect(Collectors.toList());
        List<PanelStatus> panelStatusList = panelStatusRepository.findAllPanelStatusOfBetweenDates(
                floorDate(requestDTO.getFromDate()),
                ceilDate(requestDTO.getToDate()),
                validPanelIds
        );

        final List<PanelLogReportResponseNewDTO> responseDTOSFromAlgo = new ArrayList<>();
        List<Long> validDeviceIds = findValidDeviceIds(validPanels);
        //if device config hack is on then use old config
        final Map<String, List<DataCollectionConfig>> dataCollectionConfigPerDateMap =
                getDataCollectionConfigTimesList(requestDTO.getFromDate(),
                        requestDTO.getToDate(),
                        validDeviceIds);
        final Map<Long, List<DataCollectionConfig>> defaultDataCollectionConfigMap =
                getDefaultDataCollectionConfigTimesList(validDeviceIds);
        //storeDeviceData in map with dateWise;
        final Map<String, List<PanelStatus>> panelStatusHashMap =
                new HashMap<>(panelStatusList.size());
        createPerDayPanelStatusMap(panelStatusList, panelStatusHashMap);
        if (!ObjectUtils.isEmpty(panelStatusHashMap)) {
            //if device config hack is on then use old config
            panelStatusHashMap.forEach(
                    (keyOfMap, panelStatusListFromMap) -> chooseConfigFromMapOrDefault(keyOfMap,
                            dataCollectionConfigPerDateMap, defaultDataCollectionConfigMap).forEach(
                            currentDateConfig -> {
                                //remove all status which are does not lies between panel on-off time.
                                List<PanelStatus> panelStatusUnderConfig =
                                        panelStatusListFromMap.stream().filter(panelStatus ->
                                                currentDateConfig != null
                                                        && CommonUtils.isThisTimeBetweenTheseTimes(
                                                        panelStatus.getTimeOfStatus(),
                                                        currentDateConfig.getPanelOnTime(), currentDateConfig.getPanelOffTime())
                                        ).collect(Collectors.toList());
                                addOneEntryInPanelStatusForMidNightConfig(currentDateConfig,
                                        panelStatusUnderConfig);
                                panelStatusUnderConfig.sort(Comparator.comparing(PanelStatus::getTimeOfStatus));
                                checkIsPanelCreatedOrDeletedOnWeekOffDay(panelStatusUnderConfig, currentDateConfig);
                                readPanelStatusAndCreateDTOAlgorithm(panelStatusUnderConfig, reportToken,
                                        responseDTOSFromAlgo);
                            }));
        }
        List<PanelLogReportResponseNewDTO> responseDTOS = new ArrayList<>(responseDTOSFromAlgo);
        //find All date between these
        List<Date> filterDateRange = findAllDatesBetweenTwoDateRange(
                requestDTO.getFromDate(), requestDTO.getToDate(), true);
        //Going to check and add inActive entries in response list.
        responseDTOS =
                panelInActiveReport.findAllInActiveEntryWithForPanelReport(requestDTO, reportToken,
                        validPanelIds, responseDTOS, dataCollectionConfigPerDateMap,
                        defaultDataCollectionConfigMap);
        //Going to check and add not applicable entries in response list.
        responseDTOS =
                panelNotApplicableReport.addNotApplicableEntryInResponseDTOS(filterDateRange, reportToken,
                        validPanelIds, responseDTOS, dataCollectionConfigPerDateMap,
                        defaultDataCollectionConfigMap);
        //Going to check and add Not Available entries in response list.
        responseDTOS = sortMeByDateForPanel(
                panelNotAvailableReport.addNotAvailableEntryInResponseDTOS(
                        filterDateRange, reportToken, validPanelIds, responseDTOS, isCallFromPercentageReport,
                        dataCollectionConfigPerDateMap, defaultDataCollectionConfigMap));
        //update expected Schedule up time
        updateScheduleTimeOfPanelStatus(responseDTOS, dataCollectionConfigPerDateMap,
                defaultDataCollectionConfigMap);
        // update summary string if report is called from angular web not from percentage report utils.
        if (!isCallFromPercentageReport && !ObjectUtils.isEmpty(responseDTOS)) {
            updateSummaryStringInDtoLists(responseDTOS);
        }

        //try {
        //  panelWatch.stop();
        //  logger.debug("Total time to calculate data for task::{}, Time::{} msc.",
        //      panelWatch.getLastTaskName(), panelWatch.getLastTaskTimeMillis());
        //} catch (IllegalStateException e) {
        //}
        createPanelPercentageReportFromLogDataList(responseDTOS,null);
        List<PanelLogReportResponseNewDTO> lists =panelReportRepository.CurrentDateRecords(new Date());
        panelReportRepository.deleteAll(lists);
        panelReportRepository.saveAll(responseDTOS);
//        return responseDTOS;
        return new Response.Builder<List<PanelLogReportResponseNewDTO>>().result(responseDTOS).httpStatusCode(200).build();    }
    List<Long> findValidDeviceIds(List<Panel> panels) {
        if (ObjectUtils.isEmpty(panels)) return Lists.newArrayList();
        Set<Long> validDeviceIds = new HashSet<>();
        panels.forEach(panel -> {
            if (panel.getDevice() != null) {
                validDeviceIds.add(panel.getDevice().getDeviceId());
            }
        });
        return new ArrayList<>(validDeviceIds);
    }
    final Map<String, List<DataCollectionConfig>> getDataCollectionConfigTimesList(
            Date startDate,
            Date endDate,
            List<Long> validDeviceIds
    ) {
        Map<String, List<DataCollectionConfig>> businessConfigMap =
                new HashMap<>();
        if (!ObjectUtils.isEmpty(validDeviceIds)) {

            List<DataCollectionConfig> businessConfigList =
                    dataCollectionConfigRepository.getDeviceConfigBetweenTwoConfigDates(
                            floorDate(startDate), ceilDate(endDate),
                            validDeviceIds
                    );
            if (ObjectUtils.isEmpty(businessConfigList)) {
                return businessConfigMap;
            }
            businessConfigList.forEach(config -> {
                String keySForHash = getKeysForHashMap(config.getDeviceId(), config.getConfigDate());
                List<DataCollectionConfig> listItr =
                        businessConfigMap.getOrDefault(keySForHash, new ArrayList<>());
                listItr.add(config);
                businessConfigMap.put(keySForHash, listItr);
            });
        }
        return splitConfigAndFindLatest(businessConfigMap);
    }
    public String getKeysForHashMap(
            Long aLong,
            Date configDate
    ) {
        return aLong + "-" + DateTimeUtilsKt.getOnlyDateAsString(configDate);
    }

    private Map<String, List<DataCollectionConfig>> splitConfigAndFindLatest(
            Map<String, List<DataCollectionConfig>> businessConfigMap
    ) {
        Map<String, List<DataCollectionConfig>> finalConfigMap = new HashMap<>();
        if (businessConfigMap != null && businessConfigMap.size() > 0) {
            businessConfigMap.forEach((key, dataCollectionConfigs) -> {
                dataCollectionConfigs.sort(
                        Comparator.comparing(DataCollectionConfig::getConfigDate).reversed());
                DataCollectionConfig latestConfig = dataCollectionConfigs.get(0);
                List<DataCollectionConfig> finalConfigList = new ArrayList<>();
                ConfigSplitter.splitByPanelTime(latestConfig, finalConfigList);
                finalConfigMap.put(key, finalConfigList);
            });
        }
        return ImmutableMap.copyOf(finalConfigMap);
    }
    private void createPerDayPanelStatusMap(List<PanelStatus> panelStatusList,
                                            final Map<String, List<PanelStatus>> panelStatusHashMap) {
        if (!ObjectUtils.isEmpty(panelStatusList)) {
            panelStatusList.forEach(panelStatus -> {
                        String keyForHashTable =
                                getKeysForHashMap(panelStatus.getDevice().getDeviceId(), panelStatus.getTimeOfStatus());
                        List<PanelStatus> panelStatusFromMap =
                                panelStatusHashMap.getOrDefault(keyForHashTable, new ArrayList<>());
                        panelStatusFromMap.add(panelStatus);
                        panelStatusHashMap.put(keyForHashTable, panelStatusFromMap);
                    }
            );
        }
    }
    Map<Long, List<DataCollectionConfig>> getDefaultDataCollectionConfigTimesList(
            List<Long> validDeviceIds
    ) {
        Map<Long, List<DataCollectionConfig>> businessConfigMap = new HashMap<>();
        if (!ObjectUtils.isEmpty(validDeviceIds)) {
            validDeviceIds.forEach(
                    aLong -> businessConfigMap.put(aLong, getDefaultDeviceConfigPerUniqueDevice(aLong)));
        }
        return ImmutableMap.copyOf(businessConfigMap);
    }
    public List<DataCollectionConfig> getDefaultDeviceConfigPerUniqueDevice(Long deviceId) {
        List<DataCollectionConfig> dataCollectionConfigs = new ArrayList<>();
        if (deviceId != null) {
            Optional<DeviceConfig> deviceConfig =
                    deviceConfigService.getDeletedDeviceConfigForReports(deviceId);
            if (deviceConfig.isPresent()) {
                List<DeviceConfig> deviceConfigs = new ArrayList<>();
                ConfigSplitter.splitByPanelTime(deviceConfig.get(), deviceConfigs);
                deviceConfigs.forEach(deviceConfigItr -> {
                    DataCollectionConfig config = new DataCollectionConfig();
                    config.setConfigDate(DateUtils.resetTime(deviceConfigItr.getPanelOnTime()));
                    config.setPanelOnTime(
                            DateUtils.getTimeFromTimeStampAsString(deviceConfigItr.getPanelOnTime()));
                    config.setPanelOffTime(
                            DateUtils.getTimeFromTimeStampAsString(deviceConfigItr.getPanelOffTime()));
                    config.setDeviceId(deviceId);
                    config.setWeekOffs(deviceConfigItr.getWeekOffs());
                    dataCollectionConfigs.add(config);
                });
            }
        }
        return dataCollectionConfigs;
    }
    private void updateSummaryStringInDtoLists(List<PanelLogReportResponseNewDTO> responseDTOS) {
        Map<String, String> totalDataMsgStringMap = new HashMap<>();
        Map<String, List<PanelLogReportResponseNewDTO>> perDateDtoMap = new HashMap<>();
        responseDTOS.forEach(dto -> {
            String key = getKeysForHashMap(dto.getPanelId(), dto.getDate());
            List<PanelLogReportResponseNewDTO> perDayList = perDateDtoMap.getOrDefault(key,
                    Lists.newArrayList());
            perDayList.add(dto);
            perDateDtoMap.put(key, perDayList);
        });
        perDateDtoMap.forEach((key, dtoList) -> {
            String totalMessage =
                    calculatePanelStatusDataAndAddTotalDataStringThenReturn(
                            dtoList);
            totalDataMsgStringMap.put(key, totalMessage);
        });
        responseDTOS.forEach(dto -> {
            String key = getKeysForHashMap(dto.getPanelId(), dto.getDate());
            dto.setTotalSummary(totalDataMsgStringMap.getOrDefault(key, ""));
        });
    }
    String calculatePanelStatusDataAndAddTotalDataStringThenReturn(
            List<PanelLogReportResponseNewDTO> toBeShowListInJson
    ) {
        StringJoiner finalTotalMsg = new StringJoiner(DELIMITER);
        for (DataCollectionEnum.OutputStatus panelStatus : EnumUtils.getEnumList(
                DataCollectionEnum.OutputStatus.class)) {
            int totalDeviceStatus = addPanelStatusFromListDto(toBeShowListInJson, panelStatus);
            if (totalDeviceStatus > 0) {
                finalTotalMsg.add(
                        message.get(
                                DEVICE_REPORT_MESSAGE_MAP.get(panelStatus),
                                convertTimeInReadableFormatFromMilliSec(totalDeviceStatus)
                        ));
            }
        }
        return finalTotalMsg.toString().trim();
    }
    private int addPanelStatusFromListDto(
            List<PanelLogReportResponseNewDTO> newDTOList,
            DataCollectionEnum.OutputStatus outputStatus
    ) {
        LONG_ADDER.reset();
        newDTOList.stream()
                .filter(dto -> outputStatus.equals(dto.getPanelStatus()))
                .map(PanelLogReportResponseNewDTO::getDurationInSeconds)
                .forEach(LONG_ADDER::add);
        return LONG_ADDER.intValue();
    }
    private void updateScheduleTimeOfPanelStatus(List<PanelLogReportResponseNewDTO> responseDTOS,
                                                 final Map<String, List<DataCollectionConfig>> dataCollectionConfigMapPerDate,
                                                 final Map<Long, List<DataCollectionConfig>> defaultDataCollectionConfigMap) {
        //load all dataCollectionConfig for panelOnOff Time.
        if (!ObjectUtils.isEmpty(responseDTOS)) {
            responseDTOS.forEach(responseDTO -> {
                String hashKey = getKeysForHashMap(responseDTO.getDeviceId(), responseDTO.getDate());
                List<DataCollectionConfig> dataCollectionConfigs =
                        chooseConfigFromMapOrDefault(hashKey, dataCollectionConfigMapPerDate,
                                defaultDataCollectionConfigMap);
                AtomicLong totalExpectedOn = new AtomicLong(0);
                dataCollectionConfigs.forEach(dataCollectionConfig -> totalExpectedOn.getAndAdd(
                        DateUtils.diffOfTwoTimesInSeconds(dataCollectionConfig.getPanelOnTime(),
                                dataCollectionConfig.getPanelOffTime())));
                if (!ObjectUtils.isEmpty(dataCollectionConfigs)) {
                    responseDTO.setScheduledTimeInSeconds(totalExpectedOn.get());
                    responseDTO.setScheduledPlayerUpTime(
                            convertTimeInReadableFormatFromSec(totalExpectedOn.get()));
                }
            });
        }
    }
    List<DataCollectionConfig> chooseConfigFromMapOrDefault(
            String hashTableKey,
            Map<String, List<DataCollectionConfig>> businessConfigMapOfPerDate,
            Map<Long, List<DataCollectionConfig>> defaultBusinessConfigMap
    ) {
        return businessConfigMapOfPerDate.getOrDefault(
                hashTableKey, findDefaultDeviceConfigFromMap(hashTableKey, defaultBusinessConfigMap));
    }
    private List<DataCollectionConfig> findDefaultDeviceConfigFromMap(
            String key,
            Map<Long, List<DataCollectionConfig>> defaultDataCollectionMap
    ) {
        return defaultDataCollectionMap.getOrDefault(
                parseDeviceIdFromHashKey(key),
                Lists.newArrayListWithCapacity(0)
        );
    }
    private Long parseDeviceIdFromHashKey(String key) {
        return Long.parseLong(key.split("-")[0]);
    }

    private void addOneEntryInPanelStatusForMidNightConfig(DataCollectionConfig currentDateConfig,
                                                           List<PanelStatus> panelStatusListUnderConfig) {
        if (currentDateConfig != null && !ObjectUtils.isEmpty(panelStatusListUnderConfig)) {
            PanelStatus panelStatus = panelStatusListUnderConfig.get(0);
            if (isSameTimeInStr(currentDateConfig.getPanelOnTime(), MID_NIGHT_TWELVE_TIME)) {
                PanelStatus panelStatusAfterMidNight =
                        panelStatusRepository.getLatestMidNightAfterByPanelId(
                                panelStatus.getPanel().getId(), resetTime(panelStatus.getTimeOfStatus()));
                if (panelStatusAfterMidNight != null) {
                    PanelStatus panelStatusAfterMidNightClone = new PanelStatus();
                    BeanCopyUtil.copy(panelStatusAfterMidNightClone, panelStatusAfterMidNight);
                    panelStatusAfterMidNightClone.setPanelStatusId(null);
                    panelStatusAfterMidNightClone.setTimeOfStatus(
                            combineUtilDateAndTime(panelStatus.getTimeOfStatus(), MID_NIGHT_TWELVE_TIME));
                    panelStatusListUnderConfig.add(panelStatusAfterMidNightClone);
                }
            }
            if (isSameTimeInStr(currentDateConfig.getPanelOffTime(), MID_NIGHT_ELEVEN_TIME)) {
                PanelStatus panelStatusBeforeMidNight =
                        panelStatusRepository.getLatestMidNightBeforeByPanelId(
                                panelStatus.getPanel().getId(), resetTime(panelStatus.getTimeOfStatus()));
                if (panelStatusBeforeMidNight != null) {
                    PanelStatus panelStatusAfterMidNightClone = new PanelStatus();
                    BeanCopyUtil.copy(panelStatusAfterMidNightClone, panelStatusBeforeMidNight);
                    panelStatusAfterMidNightClone.setPanelStatusId(null);
                    panelStatusAfterMidNightClone.setTimeOfStatus(
                            combineUtilDateAndTime(panelStatus.getTimeOfStatus(), MID_NIGHT_ELEVEN_TIME));
                    /*
                     * add one entry of panel off time when either not same day entry or currentTime is equals or less than dto time
                     * */
                    if (!isSameDate(new Date(), panelStatus.getTimeOfStatus())
                            || isTheseDatesEqualsOrBefore(new Date(), panelStatus.getTimeOfStatus())) {
                        panelStatusListUnderConfig.add(panelStatusAfterMidNightClone);
                    }
                }
            }
        }
    }
    private void checkIsPanelCreatedOrDeletedOnWeekOffDay(List<PanelStatus> panelStatusUnderConfig,
                                                          DataCollectionConfig currentDateConfig) {
        /*
         * hack enabled for maintain WEEK_OFF and NOT_APPLICABLE entries only for created date on weekOff
         * when device is onBoarded on week off day then device will send entry of week off on panelOn and panel off time only.
         * from server end NOT_APPLICABLE AND WEEK_OFF both entry is creating now, so we can are now changing startTime ad device createdDate
         * example 1 if device config is 10:00 to 18:00 and device is created on 12:00 and today is week_off then, entries should be like
         *  1.> 10:00 - 12:00 NOT_APPLICABLE
         *  2.> 12:00 - 18:00 WEEK_OFF
         * example 2 if device config is 12:00 to 09:00 and device is created on 22:00 and today is week_off then, entries should be like
         *  1.> 12:00 - 22:00 NOT_APPLICABLE
         *  2.> 22:00 - 23:59:59 WEEK_OFF
         * example 3 if device config is 12:00 to 09:00 and device is created on 06:00 next day  and today is week_off then, entries should be like
         *  1.> 00:00:00 - 06:00 NOT_APPLICABLE
         *  2.> 06:00 - 09:00 WEEK_OFF
         *  note:- in case of example 3 12:00-23:59:59 status is missed because device is not registered in that config slot.
         */
        //find first panelStatus for getting date and deviceId
        Optional<PanelStatus> panelStatus = panelStatusUnderConfig.stream().findFirst();
        panelStatus.ifPresent(data -> {
            Panel panel = data.getPanel();
            if (Objects.nonNull(panel)) {
                //we are iterating panelStatus object list on basis of panel and date.
                // in case of week_off day panel is either created or deleted then week off entry of whole day will be create from DeviceNotApplicableReport.java
                if (isPanelCreatedOrDeletedOnWeekOffDay(panel, currentDateConfig,
                        data.getTimeOfStatus())) {
                    panelStatusUnderConfig.clear();
                }
            }
        });
    }
    public boolean isPanelCreatedOrDeletedOnWeekOffDay(Panel panel, DataCollectionConfig config,
                                                       Date date) {
        /*
         * if panel will created or deleted on week off day then
         * panel will send week off entry at panel on time (in case of mid night first slot will send at 00:00:00)
         * */
        boolean isTodayDeviceCreated = isBothDateOfSameDate(date, panel.getCreatedOn());
        boolean isTodayDeviceDeleted = isBothDateOfSameDate(date, panel.getDeletedOn());
        if (isTodayDeviceCreated || isTodayDeviceDeleted) {
            //in case of mid night config panel will deleted on first slot then this block will execute.
            if (isSameTimeInStr(config.getPanelOnTime(), MID_NIGHT_TWELVE_TIME)) {
                PanelStatus statusAfterMidNight =
                        panelStatusRepository.getLatestMidNightAfterByPanelId(
                                panel.getId(), resetTime(date));
                return statusAfterMidNight != null && DataCollectionEnum.PanelStatus.WEEK_OFF.equals(
                        statusAfterMidNight.getPanelStatus());
            } else {
                Optional<PanelStatus> dataWithWeekOff =
                        panelStatusRepository.findWeeKOffEntryOnSameTimeOfStatus(
                                panel.getId(), combineUtilDateAndTime(date, config.getPanelOnTime()));
                return dataWithWeekOff.isPresent();
            }
        }
        return false;
    }
    private void readPanelStatusAndCreateDTOAlgorithm(List<PanelStatus> panelStatusUnderConfigList,
                                                      String reportToken, List<PanelLogReportResponseNewDTO> responseDTOS) {
        if (!ObjectUtils.isEmpty(panelStatusUnderConfigList)) {
            Long currentPanelId = 0L, currentDeviceId = 0L, currentCustomerId = 0L;
            boolean isFirstRecord = true;
            Date startTime = null, endTime = null, preStatusDate = null, preEndTime = null;
            PanelStatus currentPanelStatusObj = null;
            StringBuilder reasonOfFailures = new StringBuilder();
            DataCollectionEnum.OutputStatus currentPanelStatus = null;
            for (PanelStatus panelStatus : panelStatusUnderConfigList) {
                boolean addInListFlag = true;
                boolean afterOnBoardEntry = false;
                if (currentCustomerId.equals(panelStatus.getCustomerId())) {
                    if (currentDeviceId.equals(panelStatus.getDevice().getDeviceId())) {
                        if (currentPanelId.equals(panelStatus.getPanel().getId())) {
                            if (DateUtils.resetTime(panelStatus.getTimeOfStatus()).equals(preStatusDate)) {
                                preEndTime = endTime;
                                endTime = panelStatus.getTimeOfStatus();
                                if (AFTER_ONBOARDING.equals(
                                        panelStatus.getPanelAdditionalInfo())) {
                                    afterOnBoardEntry = true;
                                } else if (currentPanelStatus == panelStatus.getOutputStatus()) {
                                    addInListFlag = false;
                                }
                                switch (currentPanelStatusObj.getOutputStatus()) {
                                    case ON:
                                        clear(reasonOfFailures);
                                        break;
                                    case OFF:
                                    case DISCONNECTED:
                                    case WEEK_OFF:
                                    case DATA_DELETED:
                                        reasonOfFailures.append(currentPanelStatusObj.getPanelStatus().name())
                                                .append(",");
                                        break;
                                }
                            }
                        }
                    }
                }
                if (addInListFlag) {
                    if (!isFirstRecord) {
                        if (afterOnBoardEntry) {
                            addOneRowInDailyStatusList(responseDTOS, currentPanelStatusObj, startTime, preEndTime,
                                    reportToken, reasonOfFailures);
                            addOneRowInDailyStatusList(responseDTOS, currentPanelStatusObj, preEndTime, endTime,
                                    reportToken, AFTER_ONBOARDING.name(), true);
                        } else if (DATA_UNAVAILABLE_DUE_TO_TIME_CHANGES.equals(
                                currentPanelStatusObj.getPanelStatus())) {
                            addOneRowInDailyStatusList(responseDTOS, currentPanelStatusObj, startTime, endTime,
                                    reportToken, DATA_UNAVAILABLE_DUE_TO_TIME_CHANGES.name(), true);
                        } else {
                            addOneRowInDailyStatusList(responseDTOS, currentPanelStatusObj, startTime, endTime,
                                    reportToken, reasonOfFailures);
                        }
                    }
                    //resetData
                    currentCustomerId = panelStatus.getCustomerId();
                    currentPanelId = panelStatus.getPanel().getId();
                    currentPanelStatus = panelStatus.getOutputStatus();
                    currentDeviceId = panelStatus.getDevice().getDeviceId();
                    preEndTime = startTime = endTime = panelStatus.getTimeOfStatus();
                    currentPanelStatusObj = panelStatus;
                    preStatusDate = DateUtils.resetTime(panelStatus.getTimeOfStatus());
                    clear(reasonOfFailures);
                    isFirstRecord = false;
                }
            }
            if (DATA_UNAVAILABLE_DUE_TO_TIME_CHANGES.equals(
                    currentPanelStatusObj.getPanelStatus())) {
                addOneRowInDailyStatusList(responseDTOS, currentPanelStatusObj, startTime, endTime,
                        reportToken, DATA_UNAVAILABLE_DUE_TO_TIME_CHANGES.name(), true);
            } else {
                addOneRowInDailyStatusList(responseDTOS, currentPanelStatusObj, startTime, endTime,
                        reportToken, reasonOfFailures);
            }
        }
    }
    private void clear(StringBuilder stringBuffer) {
        stringBuffer.setLength(0);
    }
    private void addOneRowInDailyStatusList(List<PanelLogReportResponseNewDTO> responseDTOS,
                                            PanelStatus currentPanelStatus, Date startTime, Date endTime, String reportToken,
                                            StringBuilder reasonOfFailures) {
        addOneRowInDailyStatusList(responseDTOS, currentPanelStatus, startTime, endTime, reportToken,
                reasonOfFailures.toString(), false);
    }
    private void addOneRowInDailyStatusList(List<PanelLogReportResponseNewDTO> responseDTOS,
                                            PanelStatus currentPanelStatus, Date startTime, Date endTime, String reportToken,
                                            String reasonOfFailures, boolean isNotAvailableEntry) {
        if (startTime.getTime() == endTime.getTime()) return;
        /*
         * hack enabled for maintain WEEK_OFF and NOT_APPLICABLE entries only for created date on weekOff
         * when device is onBoarded on week off day then device will send entry of week off on panelOn and panel off time only.
         * from server end NOT_APPLICABLE AND WEEK_OFF both entry is creating now, so we can are now changing startTime ad device createdDate
         * example 1 if device config is 10:00 to 18:00 and device is created on 12:00 and today is week_off then, entries should be like
         *  1.> 10:00 - 12:00 NOT_APPLICABLE
         *  2.> 12:00 - 18:00 WEEK_OFF
         * example 2 if device config is 12:00 to 09:00 and device is created on 22:00 and today is week_off then, entries should be like
         *  1.> 12:00 - 22:00 NOT_APPLICABLE
         *  2.> 22:00 - 23:59:59 WEEK_OFF
         * example 3 if device config is 12:00 to 09:00 and device is created on 06:00 next day  and today is week_off then, entries should be like
         *  1.> 00:00:00 - 06:00 NOT_APPLICABLE
         *  2.> 06:00 - 09:00 WEEK_OFF
         *  note:- in case of example 3 12:00-23:59:59 status is missed because device is not registered in that config slot.
         */
        Date createdDate = currentPanelStatus.getPanel().getCreatedOn();
        Date deletedDate = currentPanelStatus.getPanel().getDeletedOn();
        //do not add any entries after panel deleted time
        if (isTheseDatesEqualsOrAfter(startTime, deletedDate)
                && isTheseDatesEqualsOrAfter(endTime, deletedDate)) {
            return;
        }
        //do not add any entries before panel created time
        if (isTheseDatesEqualsOrBefore(startTime, createdDate)
                && isTheseDatesEqualsOrBefore(endTime, createdDate)) {
            return;
        }
        if (isThisPastDate(startTime) && isThisPastDate(endTime)) {
            PanelLogReportResponseNewDTO panelLogReportResponseNewDTO =
                    createResponseDTORow(currentPanelStatus.getPanel(), startTime, endTime,
                            reportToken, currentPanelStatus.getOutputStatus(), reasonOfFailures);
            if (isNotAvailableEntry) {
                panelLogReportResponseNewDTO.setPanelStatus(NOT_AVAILABLE);
                if (!StringUtils.isEmpty(reasonOfFailures)) {
                    panelLogReportResponseNewDTO.setReasonForFailure(reasonOfFailures);
                }
            }
            if (panelLogReportResponseNewDTO != null) {
                responseDTOS.add(panelLogReportResponseNewDTO);
            }
        }
    }
    boolean isThisPastDate(Date startTime) {
        return DateUtils.isTheseDatesEqualsOrBefore(startTime, new Date());
    }
    PanelLogReportResponseNewDTO createResponseDTORow(
            Panel panel,
            Date startTime,
            Date endTime,
            String reportToken,
            DataCollectionEnum.OutputStatus panelStatus,
            @Nullable String reasonsOfFailure
    ) {
        PanelLogReportResponseNewDTO responseDTO = new PanelLogReportResponseNewDTO();
        responseDTO.setDeviceId(panel.getDevice().getDeviceId());
        responseDTO.setDeviceName(panel.getDevice().getDeviceName());
        responseDTO.setLocationId(panel.getDevice().getLocationId());
        responseDTO.setLocationName(
               /* panel.getDevice().getLocation2().getLocationName()*/null);
        responseDTO.setDate(DateUtils.resetTime(startTime));
        responseDTO.setStartTime(DateUtils.getTimeFromTimeStampAsString(startTime));
        responseDTO.setEndTime(DateUtils.getTimeFromTimeStampAsString(endTime));
        responseDTO.setPanelId(panel.getId());
        responseDTO.setPanelName(panel.getPanelName());
        responseDTO.setPanelIp(panel.getPanelIp());
        responseDTO.setReportToken(reportToken);
        switch (panelStatus) {
            case ON:
                responseDTO.setPanelStatus(ON);
                responseDTO.setReasonForFailure(EMPTY_STRING);
                break;
            case OFF:
                responseDTO.setPanelStatus(OFF);
                responseDTO.setReasonForFailure(
                        removeLastCommaFromReasonsAndDuplicateError(reasonsOfFailure));
                break;
            case INACTIVE:
                responseDTO.setPanelStatus(INACTIVE);
                responseDTO.setReasonForFailure(
                        INACTIVE.name());
                break;

            case NOT_APPLICABLE:
                responseDTO.setPanelStatus(NOT_APPLICABLE);
                responseDTO.setReasonForFailure(
                        NOT_APPLICABLE.name());
                break;
            case NOT_AVAILABLE:
                responseDTO.setPanelStatus(NOT_AVAILABLE);
                responseDTO.setReasonForFailure(
                        NOT_AVAILABLE.name());
                break;
            case DISCONNECTED:
                responseDTO.setPanelStatus(DISCONNECTED);
                responseDTO.setReasonForFailure(
                        removeLastCommaFromReasonsAndDuplicateError(reasonsOfFailure));
                break;
            case DATA_DELETED:
                responseDTO.setPanelStatus(DATA_DELETED);
                responseDTO.setReasonForFailure(
                        DATA_DELETED.name());
                break;
            case WEEK_OFF:
                responseDTO.setPanelStatus(WEEK_OFF);
                responseDTO.setReasonForFailure(
                        WEEK_OFF.name());
                break;
        }
        long duration = endTime.getTime() - startTime.getTime();
        responseDTO.setDuration(convertTimeInReadableFormatFromMilliSec(duration));
        responseDTO.setDurationInSeconds(duration);
        responseDTO.setScheduledTimeInSeconds(EMPTY_LONG);
        responseDTO.setScheduledPlayerUpTime(DUMMY_TIME);
        return responseDTO;
    }
    private String removeLastCommaFromReasonsAndDuplicateError(String reasons) {
        if (StringUtils.isEmpty(reasons)) return EMPTY_STRING;
        return Arrays.stream(reasons.split(",")).distinct().collect(Collectors.joining(","));
    }
    List<PanelLogReportResponseNewDTO> sortMeByDateForPanel(
            List<PanelLogReportResponseNewDTO> reportNewDTOS
    ) {
        if (ObjectUtils.isEmpty(reportNewDTOS)) return Lists.newArrayList();
        reportNewDTOS.sort((o1, o2) -> {
            assert o1 != null;
            assert o2 != null;
            assert o1.getDate() != null;
            assert o2.getDate() != null;
            if (o1 != null && o2 != null
                    && o1.getDate() != null && o2.getDate() != null) {
                return o1.getDate().compareTo(o2.getDate());
            }
            return 0;
        });
        return reportNewDTOS;
    }

    public List<PanelLogPercentageResponseNewDTO> createPanelPercentageReportFromLogDataList(
            List<PanelLogReportResponseNewDTO> reportResponseDTOS, String uniqueIdentifier) {
        List<PanelLogPercentageResponseNewDTO> toBeSaveInDbList = new ArrayList<>();
        if (!ObjectUtils.isEmpty(reportResponseDTOS)) {
            Long previousDeviceId = 0L, prePanelIdd = 0L;
            float weekOffHours = 0, notApplicableHours = 0, notAvailHours = 0, deletedHours = 0;
            float inActiveHours = 0, onHours = 0, offHours = 0, totalDuration = 0;
            float disconnectedHours = 0;
            Date preStatusDate = null;
            for (PanelLogReportResponseNewDTO responseDTO : reportResponseDTOS) {
                boolean addInFlag = false;
                PercentageParamDTO paramDTO = new PercentageParamDTO();
                if (responseDTO.getDeviceId().equals(previousDeviceId)) {
                    if (responseDTO.getPanelId().equals(prePanelIdd)) {
                        if (responseDTO.getDate().equals(preStatusDate)) {
                            totalDuration = totalDuration + responseDTO.getDurationInSeconds();
                            if (totalDuration > 0) {
                                switch (responseDTO.getPanelStatus()) {
                                    case ON:
                                        onHours += responseDTO.getDurationInSeconds();
                                        break;
                                    case OFF:
                                        offHours += responseDTO.getDurationInSeconds();
                                        break;
                                    case INACTIVE:
                                        inActiveHours += responseDTO.getDurationInSeconds();
                                        break;
                                    case WEEK_OFF:
                                        weekOffHours += responseDTO.getDurationInSeconds();
                                        break;
                                    case DATA_DELETED:
                                        deletedHours += responseDTO.getDurationInSeconds();
                                        break;
                                    case DISCONNECTED:
                                        disconnectedHours += responseDTO.getDurationInSeconds();
                                        break;
                                    case NOT_AVAILABLE:
                                        notAvailHours += responseDTO.getDurationInSeconds();
                                        break;
                                    case NOT_APPLICABLE:
                                        notApplicableHours += responseDTO.getDurationInSeconds();
                                        break;
                                }
                                paramDTO = new PercentageParamDTO(
                                        onHours,
                                        offHours,
                                        weekOffHours,
                                        notApplicableHours,
                                        notAvailHours,
                                        deletedHours,
                                        inActiveHours,
                                        disconnectedHours,
                                        ((onHours * 100) / totalDuration),
                                        ((offHours * 100) / totalDuration),
                                        ((weekOffHours * 100) / totalDuration),
                                        ((notApplicableHours * 100) / totalDuration),
                                        ((notAvailHours * 100) / totalDuration),
                                        ((deletedHours * 100) / totalDuration),
                                        ((inActiveHours * 100) / totalDuration),
                                        ((disconnectedHours * 100) / totalDuration)
                                );
                            }
                            addInFlag = true;
                            updatePanelPerLogList(toBeSaveInDbList, paramDTO);
                        }
                    }
                }
                if (!addInFlag) {
                    totalDuration = responseDTO.getDurationInSeconds();
                    onHours = offHours = weekOffHours = deletedHours =
                            disconnectedHours = inActiveHours = notApplicableHours = notAvailHours = 0;
                    if (totalDuration > 0) {
                        switch (responseDTO.getPanelStatus()) {
                            case ON:
                                onHours += responseDTO.getDurationInSeconds();
                                break;
                            case OFF:
                                offHours += responseDTO.getDurationInSeconds();
                                break;
                            case INACTIVE:
                                inActiveHours += responseDTO.getDurationInSeconds();
                                break;
                            case WEEK_OFF:
                                weekOffHours += responseDTO.getDurationInSeconds();
                                break;
                            case DATA_DELETED:
                                deletedHours += responseDTO.getDurationInSeconds();
                                break;
                            case DISCONNECTED:
                                disconnectedHours += responseDTO.getDurationInSeconds();
                                break;
                            case NOT_AVAILABLE:
                                notAvailHours += responseDTO.getDurationInSeconds();
                                break;
                            case NOT_APPLICABLE:
                                notApplicableHours += responseDTO.getDurationInSeconds();
                                break;
                        }
                        paramDTO = new PercentageParamDTO(
                                onHours,
                                offHours,
                                weekOffHours,
                                notApplicableHours,
                                notAvailHours,
                                deletedHours,
                                inActiveHours,
                                disconnectedHours,
                                ((onHours * 100) / totalDuration),
                                ((offHours * 100) / totalDuration),
                                ((weekOffHours * 100) / totalDuration),
                                ((notApplicableHours * 100) / totalDuration),
                                ((notAvailHours * 100) / totalDuration),
                                ((deletedHours * 100) / totalDuration),
                                ((inActiveHours * 100) / totalDuration),
                                ((disconnectedHours * 100) / totalDuration)
                        );
                    }
                    PanelLogPercentageResponseNewDTO percentageResponseDTO =
                            ReportsUtils.from(responseDTO, paramDTO, uniqueIdentifier);
                    toBeSaveInDbList.add(percentageResponseDTO);
                    previousDeviceId = responseDTO.getDeviceId();
                    prePanelIdd = responseDTO.getPanelId();
                    preStatusDate = responseDTO.getDate();
                }
            }
        }
        List<PanelLogPercentageResponseNewDTO> lists =  panelPercentLogRepository.CurrentDateRecords(new Date());
        panelPercentLogRepository.deleteAll(lists);
        panelPercentLogRepository.saveAll(toBeSaveInDbList);
        return toBeSaveInDbList;
    }
    private void updatePanelPerLogList(List<PanelLogPercentageResponseNewDTO> toBeSaveInDbList,
                                       PercentageParamDTO paramDTO) {
        int lastInsertedIndex = toBeSaveInDbList.size() - 1;
        toBeSaveInDbList.get(lastInsertedIndex)
                .setOnHours(convertTimeInReadableFormatFromMilliSec(paramDTO.getOnHours()));
        toBeSaveInDbList.get(lastInsertedIndex)
                .setOffHours(convertTimeInReadableFormatFromMilliSec(paramDTO.getOffHours()));
        toBeSaveInDbList.get(lastInsertedIndex)
                .setWeekOffHours(convertTimeInReadableFormatFromMilliSec(paramDTO.getWeekOffHours()));
        toBeSaveInDbList.get(lastInsertedIndex)
                .setNotApplicableHours(
                        convertTimeInReadableFormatFromMilliSec(paramDTO.getNotApplicableHours()));
        toBeSaveInDbList.get(lastInsertedIndex)
                .setDataNotAvailableHours(
                        convertTimeInReadableFormatFromMilliSec(paramDTO.getNotAvailHours()));
        toBeSaveInDbList.get(lastInsertedIndex)
                .setInActiveHours(convertTimeInReadableFormatFromMilliSec(paramDTO.getInActiveHours()));
        toBeSaveInDbList.get(lastInsertedIndex)
                .setDataDeletedHours(
                        convertTimeInReadableFormatFromMilliSec(paramDTO.getDeletedHours()));
        toBeSaveInDbList.get(lastInsertedIndex)
                .setDisconnectedHours(
                        convertTimeInReadableFormatFromMilliSec(paramDTO.getDisconnectedHours()));

        toBeSaveInDbList.get(lastInsertedIndex)
                .setOnPercentage(convertFloatToStringAfterRounding(paramDTO.getOnPercentage()));
        toBeSaveInDbList.get(lastInsertedIndex)
                .setOffPercentage(convertFloatToStringAfterRounding(paramDTO.getOffPercentage()));
        toBeSaveInDbList.get(lastInsertedIndex)
                .setWeekOffPercentage(convertFloatToStringAfterRounding(paramDTO.getWeekOffPercentage()));
        toBeSaveInDbList.get(lastInsertedIndex)
                .setNotApplicablePercentage(
                        convertFloatToStringAfterRounding(paramDTO.getNotApplicablePercentage()));
        toBeSaveInDbList.get(lastInsertedIndex)
                .setDataNotAvailablePercentage(
                        convertFloatToStringAfterRounding(paramDTO.getNotAvailPercentage()));
        toBeSaveInDbList.get(lastInsertedIndex)
                .setInActivePercentage(convertFloatToStringAfterRounding(paramDTO.getInActivePercentage()));
        toBeSaveInDbList.get(lastInsertedIndex)
                .setDataDeletedPercentage(
                        convertFloatToStringAfterRounding(paramDTO.getDeletedPercentage()));
        toBeSaveInDbList.get(lastInsertedIndex)
                .setDisconnectedPercentage(
                        convertFloatToStringAfterRounding(paramDTO.getDisconnectedPercentage()));
    }


}
