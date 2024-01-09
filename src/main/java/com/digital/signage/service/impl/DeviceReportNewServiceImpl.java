package com.digital.signage.service.impl;

import com.digital.signage.configuration.ConfigSplitter;
import com.digital.signage.dto.*;
import com.digital.signage.models.*;
import com.digital.signage.report.DeviceInActiveReport;
import com.digital.signage.report.DeviceNotApplicableReport;
import com.digital.signage.report.DeviceNotAvailableReport;
import com.digital.signage.repository.*;
import com.digital.signage.service.DeviceConfigService;
import com.digital.signage.service.DeviceReportService;
import com.digital.signage.service.WebClientService;
import com.digital.signage.util.*;
import com.google.common.base.Functions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import kotlin.Pair;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import static com.digital.signage.util.CommonUtils.convertTimeInReadableFormatFromMilliSec;
import static com.digital.signage.util.DataCollectionEnum.AdditionalInfo.AFTER_ONBOARDING;
import static com.digital.signage.util.DataCollectionEnum.DeviceStatus.DATA_UNAVAILABLE_DUE_TO_TIME_CHANGES;
import static com.digital.signage.util.DataCollectionEnum.OutputStatus.*;
import static com.digital.signage.util.DateUtils.*;
import static com.digital.signage.util.ReportConstants.*;
import static com.digital.signage.util.ReportsUtils.*;

@Service
public class DeviceReportNewServiceImpl extends BaseServiceImpl implements DeviceReportService{

    @Autowired
    private DeviceDataRepository deviceDataRepository;
    @Autowired LastApiHitTimeRepository lastApiHitTimeRepository;
    @Autowired
    private DataCollectionConfigRepository dataCollectionConfigRepository;
    @Autowired
    private DeviceConfigService deviceConfigService;
    @Autowired
    WebClientService webClientService;
    @Autowired
    private ReportsUtils reportsUtils;
    @Autowired
    private DeviceNotApplicableReport deviceNotApplicableReport;
    @Autowired
    private DeviceInActiveReport deviceInActiveReport;
    @Autowired
    private DeviceNotAvailableReport deviceNotAvailableReport;
    @Autowired
    private DeviceRepository deviceRepository;
    @Autowired
    private DeviceReportRepository deviceReportRepository;
    @Autowired
    private EntityManager entityManager;



    @Override
    public Response<?> saveDeviceLogReport(
            DeviceLogReportRequestDTO deviceRequestDTO,boolean isCallFromPercentageReport
    ) {

          List<Device>  validDeviceList = deviceRepository.findAll();
           List<Long>  validDeviceIds =  validDeviceList.stream()
                     .map(Device::getDeviceId)
                     .collect(Collectors.toList());
        DeviceLogReportRequestDTO requestDTO = new DeviceLogReportRequestDTO();
           if(deviceRequestDTO!=null){
               requestDTO.setFromDate(deviceRequestDTO.getFromDate());
               requestDTO.setToDate(deviceRequestDTO.getToDate());
           }else {
               requestDTO.setFromDate(new Date());
               requestDTO.setToDate(new Date());
           }


        final List<DeviceOnOffLogReportNewDTO> toBeSavedDeviceStatusFromAlgo = new ArrayList<>();
        List<DeviceData> deviceStatusList = deviceDataRepository.getDeviceDataBetweenTwoDates(
                floorDate(requestDTO.getFromDate()),
                ceilDate(requestDTO.getToDate()),
                validDeviceIds
        );
        // find dataCollectionConfig from map then set in map
        final Map<String, List<DataCollectionConfig>> dataCollectionConfigMapPerDate =
                getDataCollectionConfigTimesList(
                        requestDTO.getFromDate(),
                        requestDTO.getToDate(),
                        validDeviceIds
                );
        // find defaultDataCollectionConfig from map then set in map
        final Map<Long, List<DataCollectionConfig>> defaultDeviceConfigMap =
                getDefaultDataCollectionConfigTimesList(validDeviceIds);

        //storeDeviceData in map with dateWise;
        final Map<String, List<DeviceData>> deviceDataHashMap =
                new HashMap<>(deviceStatusList.size());
        createPerDayDeviceDataMap(deviceStatusList, deviceDataHashMap);

        Map<Long, Device> mapOfDevices = new HashMap<>(validDeviceList.size());
        validDeviceList.forEach(deviceItr -> mapOfDevices.put(deviceItr.getDeviceId(), deviceItr));

        if (!ObjectUtils.isEmpty(deviceDataHashMap)) {
            //if device config hack is on then use old config
            deviceDataHashMap.forEach((keyOfMap, deviceDataList) ->
                    chooseConfigFromMapOrDefault(
                            keyOfMap,
                            dataCollectionConfigMapPerDate,
                            defaultDeviceConfigMap
                    ).forEach(currentDateConfig -> {
                        List<DeviceData> deviceDataUnderConfig = deviceDataList.stream()
                                .filter(deviceData ->
                                        currentDateConfig != null
                                                && CommonUtils.isThisTimeBetweenTheseTimes(
                                                deviceData.getTimeOfStatus(),
                                                currentDateConfig.getPanelOnTime(),
                                                currentDateConfig.getPanelOffTime()
                                        )).collect(Collectors.toList());
                        addOneEntryInDeviceDataForMidNightConfig(currentDateConfig, deviceDataUnderConfig);
                        deviceDataUnderConfig.sort(Comparator.comparing(DeviceData::getTimeOfStatus));
                        checkIsDeviceCreatedOrDeletedOnWeekOffDay(mapOfDevices, currentDateConfig,
                                deviceDataUnderConfig);
                        readDeviceDataAndCreateDtoThroughAlgorithm(
                                deviceDataUnderConfig,
                                mapOfDevices,
                                null,
                                toBeSavedDeviceStatusFromAlgo
                        );
                    })
            );
        }
        List<DeviceOnOffLogReportNewDTO> toBeSavedDeviceStatus =
                new ArrayList<>(toBeSavedDeviceStatusFromAlgo);
        //find All date between these
        List<Date> filterDateRange = findAllDatesBetweenTwoDateRange(
                requestDTO.getFromDate(), requestDTO.getToDate(), true);
        //Going to check and add inActive entries in response list.
        toBeSavedDeviceStatus =
                deviceInActiveReport.findAllInActiveEntryWithForDeviceReport(
                        requestDTO,
                        null,
                        validDeviceIds,
                        toBeSavedDeviceStatus,
                        dataCollectionConfigMapPerDate,
                        defaultDeviceConfigMap
                );
        //Going to check and add not applicable entries in response list.
        toBeSavedDeviceStatus =
                deviceNotApplicableReport.addNotApplicableEntryInResponseDTOS(
                        filterDateRange,
                        null,
                        validDeviceIds,
                        toBeSavedDeviceStatus,
                        dataCollectionConfigMapPerDate,
                        defaultDeviceConfigMap
                );
        //Going to check and add Not Available entries in response list.
        toBeSavedDeviceStatus = sortMeByDateForDevice(
                deviceNotAvailableReport.addNotAvailableEntryInResponseDTOS(
                        filterDateRange,
                        null,
                        validDeviceIds,
                        toBeSavedDeviceStatus,
                        isCallFromPercentageReport,
                        dataCollectionConfigMapPerDate,
                        defaultDeviceConfigMap
                ));
        //update schedule on time.
        updateScheduleTimeOfMPStatus(toBeSavedDeviceStatus,
                new Pair<>(dataCollectionConfigMapPerDate, defaultDeviceConfigMap));

        // update summary string if report is called from angular web not from percentage report utils.
        if (!isCallFromPercentageReport && !ObjectUtils.isEmpty(toBeSavedDeviceStatus)) {
            updateSummaryStringInDtoLists(toBeSavedDeviceStatus);
        }

        List<DeviceOnOffLogReportNewDTO> lists = deviceReportRepository.CurrentDateRecords(new Date());
        deviceReportRepository.deleteAll(lists);
//        deviceReportRepository.deleteCurrentDateRecords(new Date());
//        String query = "DELETE FROM device_report where date = :currentDate";
//        Query qry = entityManager.createNativeQuery(query);
//        qry.setParameter("currentDate", new Date());
//        List<Object[]>  objects = qry.getResultList();
        deviceReportRepository.saveAll(toBeSavedDeviceStatus);
//        deviceReportRepository.delete()
        saveDevicePercentageReportFormDeviceOnOffReport(toBeSavedDeviceStatus,null);

      return new Response.Builder<List<DeviceOnOffLogReportNewDTO>>().result(toBeSavedDeviceStatus).httpStatusCode(200).build();
    }

    @Override
    public Response<?> saveDeviceConnctedDisconcted() {
                List<DeviceStatusReport> deviceStatusReports = lastApiHitTimeRepository.getDeviceCountByLocation(LocalDateTime.now());
        return new Response.Builder<List<DeviceStatusReport>>().result(deviceStatusReports).build();
    }

    @Override
    public Response<?> DeviceConnctedDisconctedById(String locationId) {

        List<Long> locationIds = CommonUtils.convertStringToLongList(locationId);

        try {
            Set<Long> allLeafNodesOfRequest = new HashSet<>();
            for (Long l : locationIds) {
                Response<?> childLocationResponse = webClientService.getChildLocation(l);
                if (childLocationResponse.getData() == null) {
                    return null;
                }
                allLeafNodesOfRequest.addAll(objectMapper.convertValue(childLocationResponse.getData(), Set.class));
            }
            List<String> commonLocations =
                    allLeafNodesOfRequest.stream()
                            .map(Functions.toStringFunction())
                            .collect(Collectors.toList());
            String commonLocationInString = String.join(",", commonLocations);

            List<Long> locationIdss = CommonUtils.convertStringToLongList(commonLocationInString);

            List<DeviceStatusReport> deviceStatusReports = lastApiHitTimeRepository.getDeviceCountByLocationId(LocalDateTime.now(), locationIdss);
            return new Response.Builder<List<DeviceStatusReport>>().result(deviceStatusReports).build();
        } catch (Exception e) {
            e.printStackTrace();
            return handleExceptionResponse();
        }
    }


    //for rebuild add this line
    private Response<?> handleExceptionResponse() {
        return new Response.Builder<>().message("An error occurred.").build();
    }
    private void updateSummaryStringInDtoLists(List<DeviceOnOffLogReportNewDTO> responseDTOS) {
        Map<String, String> totalDataMsgStringMap = new HashMap<>();
        Map<String, List<DeviceOnOffLogReportNewDTO>> perDateDtoMap = new HashMap<>();
        responseDTOS.forEach(dto -> {
            String key = getKeysForHashMap(dto.getDeviceId(), dto.getDate());
            List<DeviceOnOffLogReportNewDTO> perDayList = perDateDtoMap.getOrDefault(key,
                    Lists.newArrayList());
            perDayList.add(dto);
            perDateDtoMap.put(key, perDayList);
        });
        perDateDtoMap.forEach((key, dtoList) -> {
            String totalMessage =
                    calculateDeviceStatusDataAndAddTotalDataStringThenReturn(
                            dtoList);
            totalDataMsgStringMap.put(key, totalMessage);
        });
        responseDTOS.forEach(dto -> {
            String key = getKeysForHashMap(dto.getDeviceId(), dto.getDate());
            dto.setTotalSummary(totalDataMsgStringMap.getOrDefault(key, ""));
        });
    }
    public String calculateDeviceStatusDataAndAddTotalDataStringThenReturn(
            List<DeviceOnOffLogReportNewDTO> toBeShowListInJson
    ) {
        StringJoiner finalTotalMsg = new StringJoiner(DELIMITER);
        for (DataCollectionEnum.OutputStatus deviceStatus : EnumUtils.getEnumList(
                DataCollectionEnum.OutputStatus.class)) {
            int totalDeviceStatus = addDeviceStatusFromListDto(toBeShowListInJson, deviceStatus);
            if (totalDeviceStatus > 0) {
                finalTotalMsg.add(
                        message.get(
                                DEVICE_REPORT_MESSAGE_MAP.get(deviceStatus),
                                convertTimeInReadableFormatFromMilliSec(totalDeviceStatus)
                        ));
            }
        }
        return finalTotalMsg.toString().trim();
    }
    private int addDeviceStatusFromListDto(
            List<DeviceOnOffLogReportNewDTO> newDTOList,
            DataCollectionEnum.OutputStatus outputStatus
    ) {
        LONG_ADDER.reset();
        newDTOList.stream()
                .filter(dto -> outputStatus.equals(dto.getDeviceStatus()))
                .map(DeviceOnOffLogReportNewDTO::getDurationInSeconds)
                .forEach(LONG_ADDER::add);
        return LONG_ADDER.intValue();
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
    private void createPerDayDeviceDataMap(final List<DeviceData> deviceStatusList,
                                           final Map<String, List<DeviceData>> deviceDataHashMap) {
        if (!ObjectUtils.isEmpty(deviceStatusList)) {
            deviceStatusList.forEach(deviceData -> {
                String keyForHashTable =
                        getKeysForHashMap(deviceData.getDeviceId(), deviceData.getTimeOfStatus());
                List<DeviceData> deviceDataListFromMap =
                        deviceDataHashMap.getOrDefault(keyForHashTable, new ArrayList<>());
                deviceDataListFromMap.add(deviceData);
                deviceDataHashMap.put(keyForHashTable, deviceDataListFromMap);
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
    private void addOneEntryInDeviceDataForMidNightConfig(DataCollectionConfig currentDateConfig,
                                                          List<DeviceData> deviceDataList) {
        if (currentDateConfig != null && !ObjectUtils.isEmpty(deviceDataList)) {
            DeviceData deviceData = deviceDataList.get(0);
            if (isSameTimeInStr(currentDateConfig.getPanelOnTime(), MID_NIGHT_TWELVE_TIME)) {
                DeviceData deviceDataAfterMidNight =
                        deviceDataRepository.getLatestMidNightAfterByDeviceId(
                                deviceData.getDeviceId(), resetTime(deviceData.getTimeOfStatus()));
                if (deviceDataAfterMidNight != null) {
                    DeviceData deviceDataAfterMidNightClone = new DeviceData();
                    BeanCopyUtil.copy(deviceDataAfterMidNightClone, deviceDataAfterMidNight);
                    deviceDataAfterMidNightClone.setDeviceDataId(null);
                    deviceDataAfterMidNightClone.setTimeOfStatus(
                            combineUtilDateAndTime(deviceData.getTimeOfStatus(), MID_NIGHT_TWELVE_TIME));
                    deviceDataList.add(deviceDataAfterMidNightClone);
                }
            }
            if (isSameTimeInStr(currentDateConfig.getPanelOffTime(), MID_NIGHT_ELEVEN_TIME)) {
                DeviceData deviceDataBeforeMidNight =
                        deviceDataRepository.getLatestMidNightBeforeByDeviceId(
                                deviceData.getDeviceId(), resetTime(deviceData.getTimeOfStatus()));
                if (deviceDataBeforeMidNight != null) {
                    DeviceData deviceDataAfterMidNightClone = new DeviceData();
                    BeanCopyUtil.copy(deviceDataAfterMidNightClone, deviceDataBeforeMidNight);
                    deviceDataAfterMidNightClone.setDeviceDataId(null);
                    deviceDataAfterMidNightClone.setTimeOfStatus(
                            combineUtilDateAndTime(deviceData.getTimeOfStatus(), MID_NIGHT_ELEVEN_TIME));
                    /*
                     * add one entry of panel off time when either not same day entry or currentTime is equals or less than dto time
                     * */
                    if (!isSameDate(new Date(), deviceData.getTimeOfStatus())
                            || isTheseDatesEqualsOrBefore(new Date(), deviceData.getTimeOfStatus())) {
                        deviceDataList.add(deviceDataAfterMidNightClone);
                    }
                }
            }
        }
    }
    public static boolean isSameTimeInStr(String firstTimeStr, String secondTimeStr) {
        if (firstTimeStr == null || secondTimeStr == null) {
            return false;
        } else {
            return LocalTime.parse(firstTimeStr).equals(LocalTime.parse(secondTimeStr));
        }
    }
    public static Date resetTime(Date aDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(aDate);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }
    public static Date combineUtilDateAndTime(Date date, String timeInStr) {
        return convertLocalDateTimeToUtilDate(
                combineDateAndTime(LocalDate.parse(DateTimeUtilsKt.getOnlyDateAsString(date)),
                        LocalTime.parse(timeInStr)));
    }
    public static Date convertLocalDateTimeToUtilDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
    public static LocalDateTime combineDateAndTime(LocalDate date, LocalTime time) {
        return LocalDateTime.of(date, time);
    }
    public static boolean isSameDate(Date firstDate, Date secondDate) {
        if (firstDate == null || secondDate == null) {
            return false;
        } else {
            int compDay = floorDate(firstDate).compareTo(floorDate(secondDate));
            if (compDay == 0) {
                return true;
            } else {
                return false;
            }
        }
    }
    public static boolean isTheseDatesEqualsOrBefore(Date firstDate, Date secondDate) {
        if (firstDate == null || secondDate == null) {
            return false;
        } else {
            int compDay = firstDate.compareTo(secondDate);
            return compDay <= 0;
        }
    }
    private void checkIsDeviceCreatedOrDeletedOnWeekOffDay(Map<Long, Device> mapOfDevices,
                                                           DataCollectionConfig currentDateConfig,
                                                           List<DeviceData> deviceDataUnderConfig) {
        /*
         * Here in case of week off  for createdDate and for deleted date or same day deleted and created date week off is handled in DeviceNotApplicableReport.java
         * hack enabled for maintain WEEK_OFF and NOT_APPLICABLE entries
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

        //find first deviceData for getting date and deviceId
        Optional<DeviceData> deviceData = deviceDataUnderConfig.stream().findFirst();
        deviceData.ifPresent(data -> {
            Device device = mapOfDevices.get(data.getDeviceId());
            if (Objects.nonNull(device)) {
                //we are iterating deviceData object list on basis of device and date.
                // in case of week_off day device is either created or deleted then week off entry of whole day will be create from DeviceNotApplicableReport.java
                if (isDeviceCreatedOrDeletedOnWeekOffDay(device, currentDateConfig,
                        data.getTimeOfStatus())) {
                    deviceDataUnderConfig.clear();
                }
            }
        });
    }
    public boolean isDeviceCreatedOrDeletedOnWeekOffDay(
            Device device,
            DataCollectionConfig config,
            Date date
    ) {
        /*
         * if device will created or deleted on week off day then
         * device will send week off entry at panel on time (in case of mid night first slot will send at 00:00:00)
         * */
        boolean isTodayDeviceCreated = isBothDateOfSameDate(date, device.getCreatedOn());
        boolean isTodayDeviceDeleted = isBothDateOfSameDate(date, device.getDeletedOn());
        if (isTodayDeviceCreated || isTodayDeviceDeleted) {
            //in case of mid night config device will deleted on first slot then this block will execute.
            if (isSameTimeInStr(config.getPanelOnTime(), MID_NIGHT_TWELVE_TIME)) {
                DeviceData deviceDataAfterMidNight =
                        deviceDataRepository.getLatestMidNightAfterByDeviceId(
                                device.getDeviceId(), resetTime(date));
                return deviceDataAfterMidNight != null && DataCollectionEnum.DeviceStatus.WEEK_OFF.equals(
                        deviceDataAfterMidNight.getDeviceStatus());
            } else {
                Optional<DeviceData> dataWithWeekOff =
                        deviceDataRepository.findFirstByDeviceIdAndTimeOfStatusAndDeviceStatusOrderByTimeOfStatus(
                                device.getDeviceId(), combineUtilDateAndTime(date, config.getPanelOnTime()),
                                DataCollectionEnum.DeviceStatus.WEEK_OFF);
                return dataWithWeekOff.isPresent();
            }
        }
        return false;
    }
    public static boolean isBothDateOfSameDate(final Date firstDate,
                                               final Date secondDate) {
        if (firstDate == null || secondDate == null) {
            return false;
        } else {
            int compDay = floorDate(firstDate).compareTo(floorDate(secondDate));
            if (compDay == 0) {
                return true;
            } else {
                return false;
            }
        }
    }
    private void readDeviceDataAndCreateDtoThroughAlgorithm(
            List<DeviceData> deviceStatusUnderConfigList, Map<Long, Device> mapOfDevices,
            String reportToken, List<DeviceOnOffLogReportNewDTO> toBeSavedDeviceStatus) {
        if (!ObjectUtils.isEmpty(deviceStatusUnderConfigList)) {
            Long currentDeviceId = 0L, currentCustomerId = 0L;
            boolean isFirstRecord = true;
            Date startTime = null, endTime = null, preStatusDate = null, preEndTime = null;
            DeviceData currentDeviceStatusObj = null;

            DataCollectionEnum.DeviceStatus currentDeviceStatus = null;
            for (DeviceData deviceData : deviceStatusUnderConfigList) {
                boolean addInListFlag = true;
                boolean afterOnBoardEntry = false;
                if (currentCustomerId.equals(deviceData.getCustomerId())
                        && currentDeviceId.equals(deviceData.getDeviceId())
                        && DateUtils.resetTime(deviceData.getTimeOfStatus()).equals(preStatusDate)) {
                    preEndTime = endTime;
                    endTime = deviceData.getTimeOfStatus();
                    if (AFTER_ONBOARDING.equals(
                            deviceData.getDeviceAdditionalInfo())) {
                        afterOnBoardEntry = true;
                    } else if (deviceData.getDeviceStatus() != null && deviceData.getDeviceStatus()
                            == currentDeviceStatus) {
                        addInListFlag = false;
                    }
                }
                if (addInListFlag) {
                    if (!isFirstRecord) {
                        if (afterOnBoardEntry) {
                            addOneRowInDailyStatusList(toBeSavedDeviceStatus, currentDeviceStatusObj,
                                    mapOfDevices.get(currentDeviceStatusObj.getDeviceId()), startTime, preEndTime,
                                    reportToken);
                            addOneRowInDailyStatusList(toBeSavedDeviceStatus, currentDeviceStatusObj,
                                    mapOfDevices.get(currentDeviceStatusObj.getDeviceId()), preEndTime, endTime,
                                    reportToken, true, AFTER_ONBOARDING.name());
                        } else if (currentDeviceStatusObj.getDeviceStatus()
                                .equals(DATA_UNAVAILABLE_DUE_TO_TIME_CHANGES)) {
                            addOneRowInDailyStatusList(toBeSavedDeviceStatus, currentDeviceStatusObj,
                                    mapOfDevices.get(currentDeviceStatusObj.getDeviceId()), startTime, endTime,
                                    reportToken, true, DATA_UNAVAILABLE_DUE_TO_TIME_CHANGES.name());
                        } else {
                            addOneRowInDailyStatusList(toBeSavedDeviceStatus, currentDeviceStatusObj,
                                    mapOfDevices.get(currentDeviceStatusObj.getDeviceId()), startTime, endTime,
                                    reportToken);
                        }
                    }
                    //resetData
                    currentCustomerId = deviceData.getCustomerId();
                    currentDeviceStatus = deviceData.getDeviceStatus();
                    currentDeviceId = deviceData.getDeviceId();
                    preEndTime = startTime = endTime = deviceData.getTimeOfStatus();
                    currentDeviceStatusObj = deviceData;
                    preStatusDate = DateUtils.resetTime(deviceData.getTimeOfStatus());
                    isFirstRecord = false;
                }
            }
            if (currentDeviceStatusObj.getDeviceStatus()
                    .equals(DATA_UNAVAILABLE_DUE_TO_TIME_CHANGES)) {
                addOneRowInDailyStatusList(toBeSavedDeviceStatus, currentDeviceStatusObj,
                        mapOfDevices.get(currentDeviceStatusObj.getDeviceId()), startTime, endTime,
                        reportToken, true, DATA_UNAVAILABLE_DUE_TO_TIME_CHANGES.name());
            } else {
                addOneRowInDailyStatusList(toBeSavedDeviceStatus, currentDeviceStatusObj,
                        currentDeviceStatusObj == null ? null
                                : mapOfDevices.get(currentDeviceStatusObj.getDeviceId()), startTime,
                        endTime, reportToken);
            }
        }
    }
    private void addOneRowInDailyStatusList(List<DeviceOnOffLogReportNewDTO> responseDTOS,
                                            DeviceData deviceData, Device device, Date startTime, Date endTime, String reportToken,
                                            boolean isNotAvailableEntry, String additionalInfo) {
        if (startTime.getTime() == endTime.getTime()) return;
        //do not add any entries after device deleted time
        if (isTheseDatesEqualsOrAfter(startTime, device.getDeletedOn())
                && isTheseDatesEqualsOrAfter(endTime, device.getDeletedOn())) {
            return;
        }
        //do not add any entries before device created time
        if (isTheseDatesEqualsOrBefore(startTime, device.getCreatedOn())
                && isTheseDatesEqualsOrBefore(endTime, device.getCreatedOn())) {
            return;
        }
        if (isThisPastDate(startTime) && isThisPastDate(endTime)) {
            DeviceOnOffLogReportNewDTO responseDTO =
                    createResponseDTORow(device, startTime, endTime, reportToken,
                            isNotAvailableEntry ? NOT_AVAILABLE
                                    : reportsUtils.convertDeviceStatusToOutputStatus(deviceData.getDeviceStatus()),
                            additionalInfo);

            responseDTOS.add(responseDTO);
        }
    }
    boolean isThisPastDate(Date startTime) {
        return DateUtils.isTheseDatesEqualsOrBefore(startTime, new Date());
    }
    private void addOneRowInDailyStatusList(List<DeviceOnOffLogReportNewDTO> responseDTOS,
                                            DeviceData deviceData, Device device, Date startTime, Date endTime, String reportToken) {
        addOneRowInDailyStatusList(responseDTOS, deviceData, device, startTime, endTime, reportToken,
                false, null);
    }
    DeviceOnOffLogReportNewDTO createResponseDTORow(
            Device device,
            Date startTime,
            Date endTime,
            String reportToken,
            final DataCollectionEnum.OutputStatus deviceStatus,
            String reasonForDisconnection
    ) {
        DeviceOnOffLogReportNewDTO responseDTO = new DeviceOnOffLogReportNewDTO();
        responseDTO.setDeviceId(device.getDeviceId());
        responseDTO.setDeviceName(device.getDeviceName());
        responseDTO.setLocationId(device.getLocationId());
        responseDTO.setLocationName(
                /*device.getLocation2().getLocationName()*/null);
        responseDTO.setDeviceGroupId(device.getDeviceGroupId());
        responseDTO.setDeviceGroupName(device.getDeviceGroupName());
        responseDTO.setDate(DateUtils.resetTime(startTime));
        responseDTO.setStartTime(DateUtils.getTimeFromTimeStampAsString(startTime));
        responseDTO.setEndTime(DateUtils.getTimeFromTimeStampAsString(endTime));
        switch (deviceStatus) {
            case ON:
                responseDTO.setIsDeviceDown(Boolean.FALSE);
                responseDTO.setDeviceStatus(ON);
                responseDTO.setReasonForOffOrDisconnection(null);
                break;
            case OFF:
                responseDTO.setIsDeviceDown(Boolean.TRUE);
                responseDTO.setDeviceStatus(OFF);
                responseDTO.setReasonForOffOrDisconnection(OFF.toString());
                break;
            case INACTIVE:
                responseDTO.setDeviceStatus(INACTIVE);
                responseDTO.setReasonForOffOrDisconnection(INACTIVE.toString());
                break;

            case NOT_APPLICABLE:
                responseDTO.setDeviceStatus(NOT_APPLICABLE);
                responseDTO.setReasonForOffOrDisconnection(NOT_APPLICABLE.toString());
                break;
            case NOT_AVAILABLE:
                responseDTO.setDeviceStatus(NOT_AVAILABLE);
                if (StringUtils.isEmpty(reasonForDisconnection)) {
                    responseDTO.setReasonForOffOrDisconnection(NOT_AVAILABLE.name());
                } else {
                    responseDTO.setReasonForOffOrDisconnection(reasonForDisconnection);
                }
                break;
            case WEEK_OFF:
                responseDTO.setDeviceStatus(WEEK_OFF);
                responseDTO.setReasonForOffOrDisconnection(WEEK_OFF.toString());
                break;
            case DATA_DELETED:
                responseDTO.setDeviceStatus(DATA_DELETED);
                responseDTO.setReasonForOffOrDisconnection(DATA_DELETED.toString());
                break;
        }
        responseDTO.setReportToken(reportToken);
        long duration = endTime.getTime() - startTime.getTime();
        responseDTO.setDuration(convertTimeInReadableFormatFromMilliSec(duration));
        responseDTO.setDurationInSeconds(duration);
        responseDTO.setScheduledUpTimeInSecond(EMPTY_LONG);
        responseDTO.setScheduledPlayerUpTime(DUMMY_TIME);
        return responseDTO;
    }
    public static boolean isTheseDatesEqualsOrAfter(Date firstDate, Date secondDate) {
        if (firstDate == null || secondDate == null) {
            return false;
        } else {
            int compDay = firstDate.compareTo(secondDate);
            if (compDay >= 0) {
                return true;
            } else {
                return false;
            }
        }
    }
    private void updateScheduleTimeOfMPStatus(List<DeviceOnOffLogReportNewDTO> responseDTOS,
                                              Pair<Map<String, List<DataCollectionConfig>>, Map<Long, List<DataCollectionConfig>>> businessConfigMapPair) {
        //load all dataCollectionConfig for panelOnOff Time.
        responseDTOS.forEach(responseDTO -> {
            String hashKey = getKeysForHashMap(responseDTO.getDeviceId(), responseDTO.getDate());
            List<DataCollectionConfig> dataCollectionConfigs =
                    chooseConfigFromMapOrDefault(hashKey, businessConfigMapPair.getFirst(),
                            businessConfigMapPair.getSecond());
            AtomicLong totalExpectedOn = new AtomicLong(0);
            dataCollectionConfigs.forEach(dataCollectionConfig -> totalExpectedOn.getAndAdd(
                    DateUtils.diffOfTwoTimesInSeconds(dataCollectionConfig.getPanelOnTime(),
                            dataCollectionConfig.getPanelOffTime())));
            if (!ObjectUtils.isEmpty(dataCollectionConfigs)) {
                responseDTO.setScheduledUpTimeInSecond(totalExpectedOn.get());
                responseDTO.setScheduledPlayerUpTime(
                        convertTimeInReadableFormatFromSec(totalExpectedOn.get()));
            }
        });
    }
    public static String convertTimeInReadableFormatFromSec(long longVal) {
        String durationInString = "";
        int hours = (int) longVal / 3600;
        int remainder = (int) longVal - hours * 3600;
        int mins = remainder / 60;
        remainder = remainder - mins * 60;
        int secs = remainder;
        if (hours < 10) {
            if (hours == 0) {
                durationInString = "00:";
            } else {
                durationInString = "0" + hours + ":";
            }
        } else {
            durationInString += hours + ":";
        }
        if (mins < 10) {
            if (mins == 0) {
                durationInString += "00:";
            } else {
                durationInString += "0" + mins + ":";
            }
        } else {
            durationInString += mins + ":";
        }
        if (secs < 10) {
            if (secs == 0) {
                durationInString += "00";
            } else {
                durationInString += "0" + secs;
            }
        } else {
            durationInString += secs;
        }

        return durationInString;
    }
    List<DeviceOnOffLogReportNewDTO> sortMeByDateForDevice(
            List<DeviceOnOffLogReportNewDTO> toBeSavedDeviceStatus
    ) {
        if (ObjectUtils.isEmpty(toBeSavedDeviceStatus)) return Lists.newArrayList();
        //Comparator.comparing(DeviceOnOffLogReportNewDTO::getDate)
        toBeSavedDeviceStatus.sort((o1, o2) -> {
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
        return toBeSavedDeviceStatus;
    }


    public List<DeviceLogPercentageReportNewDTO> saveDevicePercentageReportFormDeviceOnOffReport(
            List<DeviceOnOffLogReportNewDTO> deviceOnOffLogReportDTOS,
            String reportToken
    ) {
        //    ":::::::::::::::::::::::Start Making Data for Save::::::::::::::::::::::::::::::::::::::::::");
        List<DeviceLogPercentageReportNewDTO> percentageReportDTOList = new ArrayList<>();
        if (!ObjectUtils.isEmpty(deviceOnOffLogReportDTOS)) {
            Long previousDeviceId = EMPTY_LONG;
            float weekOffHours = 0, notApplicableHours = 0, notAvailHours = 0, deletedHours = 0;
            float inActiveHours = 0, onHours = 0, offHours = 0, totalDuration = 0;
            Date preStatusDate = null;
            PercentageParamDTO paramDTO = new PercentageParamDTO();
            for (DeviceOnOffLogReportNewDTO responseDTO : deviceOnOffLogReportDTOS) {
                boolean addInFlag = true;
                if (responseDTO.getDeviceId().equals(previousDeviceId)) {
                    if (responseDTO.getDate().equals(preStatusDate)) {
                        totalDuration = totalDuration + responseDTO.getDurationInSeconds();
                        if (totalDuration > 0) {
                            switch (responseDTO.getDeviceStatus()) {
                                case ON:
                                    onHours += responseDTO.getDurationInSeconds();
                                    break;
                                case OFF:
                                    offHours += responseDTO.getDurationInSeconds();
                                    break;
                                case NOT_APPLICABLE:
                                    notApplicableHours += responseDTO.getDurationInSeconds();
                                    break;
                                case NOT_AVAILABLE:
                                    notAvailHours += responseDTO.getDurationInSeconds();
                                    break;
                                case DATA_DELETED:
                                    deletedHours += responseDTO.getDurationInSeconds();
                                    break;
                                case WEEK_OFF:
                                    weekOffHours += responseDTO.getDurationInSeconds();
                                    break;
                                case INACTIVE:
                                    inActiveHours += responseDTO.getDurationInSeconds();
                                    break;
                            }
                            paramDTO.setOnHours(onHours);
                            paramDTO.setOffHours(offHours);
                            paramDTO.setWeekOffHours(weekOffHours);
                            paramDTO.setNotApplicableHours(notApplicableHours);
                            paramDTO.setNotAvailHours(notAvailHours);
                            paramDTO.setInActiveHours(inActiveHours);
                            paramDTO.setDeletedHours(deletedHours);
                            paramDTO.setOnPercentage((onHours * 100) / totalDuration);
                            paramDTO.setOffPercentage((offHours * 100) / totalDuration);
                            paramDTO.setWeekOffPercentage((weekOffHours * 100) / totalDuration);
                            paramDTO.setNotApplicablePercentage((notApplicableHours * 100) / totalDuration);
                            paramDTO.setNotAvailPercentage((notAvailHours * 100) / totalDuration);
                            paramDTO.setInActivePercentage((inActiveHours * 100) / totalDuration);
                            paramDTO.setDeletedPercentage((deletedHours * 100) / totalDuration);
                        }

                        addInFlag = false;
                        updatePanelPerLogList(percentageReportDTOList, paramDTO);
                    }
                }
                if (addInFlag) {
                    totalDuration = responseDTO.getDurationInSeconds();
                    paramDTO = new PercentageParamDTO();
                    onHours = 0;
                    offHours = 0;
                    weekOffHours = 0;
                    notApplicableHours = 0;
                    notAvailHours = 0;
                    inActiveHours = 0;
                    deletedHours = 0;
                    if (totalDuration > 0) {
                        switch (responseDTO.getDeviceStatus()) {
                            case ON:
                                onHours += responseDTO.getDurationInSeconds();
                                break;
                            case OFF:
                                offHours += responseDTO.getDurationInSeconds();
                                break;
                            case NOT_APPLICABLE:
                                notApplicableHours += responseDTO.getDurationInSeconds();
                                break;
                            case NOT_AVAILABLE:
                                notAvailHours += responseDTO.getDurationInSeconds();
                                break;
                            case DATA_DELETED:
                                deletedHours += responseDTO.getDurationInSeconds();
                                break;
                            case WEEK_OFF:
                                weekOffHours += responseDTO.getDurationInSeconds();
                                break;
                            case INACTIVE:
                                inActiveHours += responseDTO.getDurationInSeconds();
                                break;
                        }
                        paramDTO.setOnHours(onHours);
                        paramDTO.setOffHours(offHours);
                        paramDTO.setWeekOffHours(weekOffHours);
                        paramDTO.setNotAvailHours(notAvailHours);
                        paramDTO.setInActiveHours(inActiveHours);
                        paramDTO.setDeletedHours(deletedHours);
                        paramDTO.setOnPercentage((onHours * 100) / totalDuration);
                        paramDTO.setOffPercentage((offHours * 100) / totalDuration);
                        paramDTO.setWeekOffPercentage((weekOffHours * 100) / totalDuration);
                        paramDTO.setNotApplicablePercentage((notApplicableHours * 100) / totalDuration);
                        paramDTO.setNotAvailPercentage((notAvailHours * 100) / totalDuration);
                        paramDTO.setInActivePercentage((inActiveHours * 100) / totalDuration);
                        paramDTO.setDeletedPercentage((deletedHours * 100) / totalDuration);
                    }
                    DeviceLogPercentageReportNewDTO percentageResponseDTO =
                            createPercentageReportNewDto(responseDTO, paramDTO, reportToken);
                    percentageReportDTOList.add(percentageResponseDTO);
                    previousDeviceId = responseDTO.getDeviceId();
                    preStatusDate = responseDTO.getDate();
                }
            }
        }
//        String query = "DELETE FROM device_per_report where date = :currentDate";
//        Query qry = entityManager.createNativeQuery(query);
//        qry.setParameter("currentDate", new Date());
//        List<Object[]>  objects = qry.getResultList();

        List<DeviceLogPercentageReportNewDTO> lists =  devicePercentageReportRepository.CurrentDateRecords(new Date());
        devicePercentageReportRepository.deleteAll(lists);
        devicePercentageReportRepository.saveAll(percentageReportDTOList);
        return percentageReportDTOList;
    }

    private void updatePanelPerLogList(List<DeviceLogPercentageReportNewDTO> toBeSaveInDbList,
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
    }

    private DeviceLogPercentageReportNewDTO createPercentageReportNewDto(
            DeviceOnOffLogReportNewDTO responseDTO, PercentageParamDTO paramDTO, String reportToken) {
        return new DeviceLogPercentageReportNewDTO(
                responseDTO.getDeviceId(),
                responseDTO.getDeviceName(), responseDTO.getLocationId(),
                responseDTO.getLocationName(), responseDTO.getDeviceGroupId(),
                responseDTO.getDeviceGroupName(),
                responseDTO.getDate(),
                responseDTO.getScheduledPlayerUpTime(),
                convertTimeInReadableFormatFromMilliSec(paramDTO.getOnHours()),
                convertTimeInReadableFormatFromMilliSec(paramDTO.getOffHours()),
                convertTimeInReadableFormatFromMilliSec(paramDTO.getWeekOffHours()),
                convertTimeInReadableFormatFromMilliSec(paramDTO.getNotApplicableHours()),
                convertTimeInReadableFormatFromMilliSec(paramDTO.getNotAvailHours()),
                convertTimeInReadableFormatFromMilliSec(paramDTO.getInActiveHours()),
                convertTimeInReadableFormatFromMilliSec(paramDTO.getDeletedHours()),
                convertFloatToStringAfterRounding(paramDTO.getOnPercentage()),
                convertFloatToStringAfterRounding(paramDTO.getOffPercentage()),
                convertFloatToStringAfterRounding(paramDTO.getWeekOffPercentage()),
                convertFloatToStringAfterRounding(paramDTO.getNotApplicablePercentage()),
                convertFloatToStringAfterRounding(paramDTO.getNotAvailPercentage()),
                convertFloatToStringAfterRounding(paramDTO.getInActivePercentage()),
                convertFloatToStringAfterRounding(paramDTO.getDeletedPercentage()),
                reportToken
        );
    }

    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.##");
    @Autowired
    private DevicePercentageReportRepository devicePercentageReportRepository;

    public static String convertFloatToStringAfterRounding(float floatingValue) {
        return DECIMAL_FORMAT.format(floatingValue);
    }
}

