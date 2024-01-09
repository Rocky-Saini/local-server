package com.digital.signage.util;
import com.digital.signage.context.TenantContext;
import kotlin.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class DeviceReportUtils extends ReportsUtils {

    private static final Logger logger = LoggerFactory.getLogger(DeviceReportUtils.class);

//    @Value("${panel-device.database.name.reports}")
//    private String cacheDatabaseName;

//    @Autowired
//    private XlsHelper xlsHelper;
//    @Autowired
//    private DeviceReportService reportService;
//    @Autowired
//    private DeviceDataRepository deviceDataRepository;
//    @Autowired
//    private DeviceRepository deviceRepository;
//    @Autowired
//    private DeviceNotAvailableReport deviceNotAvailableReport;
//    @Autowired
//    private DeviceInActiveReport deviceInActiveReport;
//    @Autowired
//    private DeviceNotApplicableReport deviceNotApplicableReport;
//
//    //this is called from cron Job
//    public List<DeviceOnOffLogReportNewDTO> filterDeviceStatusRowsForReport(
//            DeviceLogReportRequestDTO requestDTO,
//            Device device,
//            String reportToken,
//            boolean isCallFromPercentageReport
//    ) {
//        Long deviceId = device.getDeviceId();
//        List<Long> validDeviceIds = Collections.singletonList(deviceId);
//        List<Device> validDeviceList = Lists.newArrayList(device);
//        /*:::::::::::::::::::::::Start Making Data for Save::::::::::::::::::::::::::::::::::::::::::*/
//        final List<DeviceOnOffLogReportNewDTO> toBeSavedDeviceStatusFromAlgo = new ArrayList<>();
//        List<DeviceData> deviceStatusList = deviceDataRepository.getDeviceDataBetweenTwoDates(
//                floorDate(requestDTO.getFromDate()),
//                ceilDate(requestDTO.getToDate()),
//                validDeviceIds
//        );
//        // find dataCollectionConfig from map then set in map
//        final Map<String, List<DataCollectionConfig>> dataCollectionConfigMapPerDate =
//                getDataCollectionConfigTimesList(
//                        requestDTO.getFromDate(),
//                        requestDTO.getToDate(),
//                        validDeviceIds
//                );
//        // find defaultDataCollectionConfig from map then set in map
//        final Map<Long, List<DataCollectionConfig>> defaultDeviceConfigMap =
//                getDefaultDataCollectionConfigTimesList(validDeviceIds);
//
//        //storeDeviceData in map with dateWise;
//        final Map<String, List<DeviceData>> deviceDataHashMap =
//                new HashMap<>(deviceStatusList.size());
//        createPerDayDeviceDataMap(deviceStatusList, deviceDataHashMap);
//
//        Map<Long, Device> mapOfDevices = new HashMap<>(validDeviceList.size());
//        validDeviceList.forEach(deviceItr -> mapOfDevices.put(deviceItr.getDeviceId(), deviceItr));
//
//        if (!org.springframework.util.ObjectUtils.isEmpty(deviceDataHashMap)) {
//            //if device config hack is on then use old config
//            deviceDataHashMap.forEach((keyOfMap, deviceDataList) ->
//                    chooseConfigFromMapOrDefault(
//                            keyOfMap,
//                            dataCollectionConfigMapPerDate,
//                            defaultDeviceConfigMap
//                    ).forEach(currentDateConfig -> {
//                        List<DeviceData> deviceDataUnderConfig = deviceDataList.stream()
//                                .filter(deviceData ->
//                                        currentDateConfig != null
//                                                && CommonUtils.isThisTimeBetweenTheseTimes(
//                                                deviceData.getTimeOfStatus(),
//                                                currentDateConfig.getPanelOnTime(),
//                                                currentDateConfig.getPanelOffTime()
//                                        )).collect(Collectors.toList());
//                        addOneEntryInDeviceDataForMidNightConfig(currentDateConfig, deviceDataUnderConfig);
//                        deviceDataUnderConfig.sort(Comparator.comparing(DeviceData::getTimeOfStatus));
//                        checkIsDeviceCreatedOrDeletedOnWeekOffDay(mapOfDevices, currentDateConfig,
//                                deviceDataUnderConfig);
//                        readDeviceDataAndCreateDtoThroughAlgorithm(
//                                deviceDataUnderConfig,
//                                mapOfDevices,
//                                reportToken,
//                                toBeSavedDeviceStatusFromAlgo
//                        );
//                    })
//            );
//        }
//        List<DeviceOnOffLogReportNewDTO> toBeSavedDeviceStatus =
//                new ArrayList<>(toBeSavedDeviceStatusFromAlgo);
//        //find All date between these
//        List<Date> filterDateRange = findAllDatesBetweenTwoDateRange(
//                requestDTO.getFromDate(), requestDTO.getToDate(), true);
//        //Going to check and add inActive entries in response list.
//        toBeSavedDeviceStatus =
//                deviceInActiveReport.findAllInActiveEntryWithForDeviceReport(
//                        requestDTO,
//                        reportToken,
//                        validDeviceIds,
//                        toBeSavedDeviceStatus,
//                        dataCollectionConfigMapPerDate,
//                        defaultDeviceConfigMap
//                );
//        //Going to check and add not applicable entries in response list.
//        toBeSavedDeviceStatus =
//                deviceNotApplicableReport.addNotApplicableEntryInResponseDTOS(
//                        filterDateRange,
//                        reportToken,
//                        validDeviceIds,
//                        toBeSavedDeviceStatus,
//                        dataCollectionConfigMapPerDate,
//                        defaultDeviceConfigMap
//                );
//        //Going to check and add Not Available entries in response list.
//        toBeSavedDeviceStatus = sortMeByDateForDevice(
//                deviceNotAvailableReport.addNotAvailableEntryInResponseDTOS(
//                        filterDateRange,
//                        reportToken, validDeviceIds,
//                        toBeSavedDeviceStatus,
//                        isCallFromPercentageReport,
//                        dataCollectionConfigMapPerDate,
//                        defaultDeviceConfigMap
//                ));
//        //update schedule on time.
//        updateScheduleTimeOfMPStatus(toBeSavedDeviceStatus,
//                new Pair<>(dataCollectionConfigMapPerDate, defaultDeviceConfigMap));
//
//        // update summary string if report is called from angular web not from percentage report utils.
//        if (!isCallFromPercentageReport && !org.springframework.util.ObjectUtils.isEmpty(toBeSavedDeviceStatus)) {
//            updateSummaryStringInDtoLists(toBeSavedDeviceStatus);
//        }
//        /*:::::::::::::::::::::::End Making Data for Save::::::::::::::::::::::::::::::::::::::::::*/
//        return toBeSavedDeviceStatus;
//    }
//
//    private void createPerDayDeviceDataMap(final List<DeviceData> deviceStatusList,
//                                           final Map<String, List<DeviceData>> deviceDataHashMap) {
//        if (!org.springframework.util.ObjectUtils.isEmpty(deviceStatusList)) {
//            deviceStatusList.forEach(deviceData -> {
//                String keyForHashTable =
//                        getKeysForHashMap(deviceData.getDeviceId(), deviceData.getTimeOfStatus());
//                List<DeviceData> deviceDataListFromMap =
//                        deviceDataHashMap.getOrDefault(keyForHashTable, new ArrayList<>());
//                deviceDataListFromMap.add(deviceData);
//                deviceDataHashMap.put(keyForHashTable, deviceDataListFromMap);
//            });
//        }
//    }
//
//    private void addOneEntryInDeviceDataForMidNightConfig(DataCollectionConfig currentDateConfig,
//                                                          List<DeviceData> deviceDataList) {
//        if (currentDateConfig != null && !org.springframework.util.ObjectUtils.isEmpty(deviceDataList)) {
//            DeviceData deviceData = deviceDataList.get(0);
//            if (isSameTimeInStr(currentDateConfig.getPanelOnTime(), MID_NIGHT_TWELVE_TIME)) {
//                DeviceData deviceDataAfterMidNight =
//                        deviceDataRepository.getLatestMidNightAfterByDeviceId(
//                                deviceData.getDeviceId(), resetTime(deviceData.getTimeOfStatus()));
//                if (deviceDataAfterMidNight != null) {
//                    DeviceData deviceDataAfterMidNightClone = new DeviceData();
//                    BeanCopyUtil.copy(deviceDataAfterMidNightClone, deviceDataAfterMidNight);
//                    deviceDataAfterMidNightClone.setDeviceDataId(null);
//                    deviceDataAfterMidNightClone.setTimeOfStatus(
//                            combineUtilDateAndTime(deviceData.getTimeOfStatus(), MID_NIGHT_TWELVE_TIME));
//                    deviceDataList.add(deviceDataAfterMidNightClone);
//                }
//            }
//            if (isSameTimeInStr(currentDateConfig.getPanelOffTime(), MID_NIGHT_ELEVEN_TIME)) {
//                DeviceData deviceDataBeforeMidNight =
//                        deviceDataRepository.getLatestMidNightBeforeByDeviceId(
//                                deviceData.getDeviceId(), resetTime(deviceData.getTimeOfStatus()));
//                if (deviceDataBeforeMidNight != null) {
//                    DeviceData deviceDataAfterMidNightClone = new DeviceData();
//                    BeanCopyUtil.copy(deviceDataAfterMidNightClone, deviceDataBeforeMidNight);
//                    deviceDataAfterMidNightClone.setDeviceDataId(null);
//                    deviceDataAfterMidNightClone.setTimeOfStatus(
//                            combineUtilDateAndTime(deviceData.getTimeOfStatus(), MID_NIGHT_ELEVEN_TIME));
//                    /*
//                     * add one entry of panel off time when either not same day entry or currentTime is equals or less than dto time
//                     * */
//                    if (!isSameDate(new Date(), deviceData.getTimeOfStatus())
//                            || isTheseDatesEqualsOrBefore(new Date(), deviceData.getTimeOfStatus())) {
//                        deviceDataList.add(deviceDataAfterMidNightClone);
//                    }
//                }
//            }
//        }
//    }

//    private void readDeviceDataAndCreateDtoThroughAlgorithm(
//            List<DeviceData> deviceStatusUnderConfigList, Map<Long, Device> mapOfDevices,
//            String reportToken, List<DeviceOnOffLogReportNewDTO> toBeSavedDeviceStatus) {
//        if (!org.springframework.util.ObjectUtils.isEmpty(deviceStatusUnderConfigList)) {
//            Long currentDeviceId = 0L, currentCustomerId = 0L;
//            boolean isFirstRecord = true;
//            Date startTime = null, endTime = null, preStatusDate = null, preEndTime = null;
//            DeviceData currentDeviceStatusObj = null;
//
//            DataCollectionEnum.DeviceStatus currentDeviceStatus = null;
//            for (DeviceData deviceData : deviceStatusUnderConfigList) {
//                boolean addInListFlag = true;
//                boolean afterOnBoardEntry = false;
//                if (currentCustomerId.equals(deviceData.getCustomerId())
//                        && currentDeviceId.equals(deviceData.getDeviceId())
//                        && DateUtils.resetTime(deviceData.getTimeOfStatus()).equals(preStatusDate)) {
//                    preEndTime = endTime;
//                    endTime = deviceData.getTimeOfStatus();
//                    if (AFTER_ONBOARDING.equals(
//                            deviceData.getDeviceAdditionalInfo())) {
//                        afterOnBoardEntry = true;
//                    } else if (deviceData.getDeviceStatus() != null && deviceData.getDeviceStatus()
//                            == currentDeviceStatus) {
//                        addInListFlag = false;
//                    }
//                }
//                if (addInListFlag) {
//                    if (!isFirstRecord) {
//                        if (afterOnBoardEntry) {
//                            addOneRowInDailyStatusList(toBeSavedDeviceStatus, currentDeviceStatusObj,
//                                    mapOfDevices.get(currentDeviceStatusObj.getDeviceId()), startTime, preEndTime,
//                                    reportToken);
//                            addOneRowInDailyStatusList(toBeSavedDeviceStatus, currentDeviceStatusObj,
//                                    mapOfDevices.get(currentDeviceStatusObj.getDeviceId()), preEndTime, endTime,
//                                    reportToken, true, AFTER_ONBOARDING.name());
//                        } else if (currentDeviceStatusObj.getDeviceStatus()
//                                .equals(DATA_UNAVAILABLE_DUE_TO_TIME_CHANGES)) {
//                            addOneRowInDailyStatusList(toBeSavedDeviceStatus, currentDeviceStatusObj,
//                                    mapOfDevices.get(currentDeviceStatusObj.getDeviceId()), startTime, endTime,
//                                    reportToken, true, DATA_UNAVAILABLE_DUE_TO_TIME_CHANGES.name());
//                        } else {
//                            addOneRowInDailyStatusList(toBeSavedDeviceStatus, currentDeviceStatusObj,
//                                    mapOfDevices.get(currentDeviceStatusObj.getDeviceId()), startTime, endTime,
//                                    reportToken);
//                        }
//                    }
//                    //resetData
//                    currentCustomerId = deviceData.getCustomerId();
//                    currentDeviceStatus = deviceData.getDeviceStatus();
//                    currentDeviceId = deviceData.getDeviceId();
//                    preEndTime = startTime = endTime = deviceData.getTimeOfStatus();
//                    currentDeviceStatusObj = deviceData;
//                    preStatusDate = DateUtils.resetTime(deviceData.getTimeOfStatus());
//                    isFirstRecord = false;
//                }
//            }
//            if (currentDeviceStatusObj.getDeviceStatus()
//                    .equals(DATA_UNAVAILABLE_DUE_TO_TIME_CHANGES)) {
//                addOneRowInDailyStatusList(toBeSavedDeviceStatus, currentDeviceStatusObj,
//                        mapOfDevices.get(currentDeviceStatusObj.getDeviceId()), startTime, endTime,
//                        reportToken, true, DATA_UNAVAILABLE_DUE_TO_TIME_CHANGES.name());
//            } else {
//                addOneRowInDailyStatusList(toBeSavedDeviceStatus, currentDeviceStatusObj,
//                        currentDeviceStatusObj == null ? null
//                                : mapOfDevices.get(currentDeviceStatusObj.getDeviceId()), startTime,
//                        endTime, reportToken);
//            }
//        }
//    }
//
//    private void addOneRowInDailyStatusList(List<DeviceOnOffLogReportNewDTO> responseDTOS,
//                                            DeviceData deviceData, Device device, Date startTime, Date endTime, String reportToken) {
//        addOneRowInDailyStatusList(responseDTOS, deviceData, device, startTime, endTime, reportToken,
//                false, null);
//    }
//
//    private void addOneRowInDailyStatusList(List<DeviceOnOffLogReportNewDTO> responseDTOS,
//                                            DeviceData deviceData, Device device, Date startTime, Date endTime, String reportToken,
//                                            boolean isNotAvailableEntry, String additionalInfo) {
//        if (startTime.getTime() == endTime.getTime()) return;
//        //do not add any entries after device deleted time
//        if (isTheseDatesEqualsOrAfter(startTime, device.getDeletedOn())
//                && isTheseDatesEqualsOrAfter(endTime, device.getDeletedOn())) {
//            return;
//        }
//        //do not add any entries before device created time
//        if (isTheseDatesEqualsOrBefore(startTime, device.getCreatedOn())
//                && isTheseDatesEqualsOrBefore(endTime, device.getCreatedOn())) {
//            return;
//        }
//        if (isThisPastDate(startTime) && isThisPastDate(endTime)) {
//            DeviceOnOffLogReportNewDTO responseDTO =
//                    createResponseDTORow(device, startTime, endTime, reportToken,
//                            isNotAvailableEntry ? NOT_AVAILABLE
//                                    : convertDeviceStatusToOutputStatus(deviceData.getDeviceStatus()),
//                            additionalInfo);
//
//            responseDTOS.add(responseDTO);
//        }
//    }
//
//    private void updateScheduleTimeOfMPStatus(List<DeviceOnOffLogReportNewDTO> responseDTOS,
//                                              Pair<Map<String, List<DataCollectionConfig>>, Map<Long, List<DataCollectionConfig>>> businessConfigMapPair) {
//        //load all dataCollectionConfig for panelOnOff Time.
//        responseDTOS.forEach(responseDTO -> {
//            String hashKey = getKeysForHashMap(responseDTO.getDeviceId(), responseDTO.getDate());
//            List<DataCollectionConfig> dataCollectionConfigs =
//                    chooseConfigFromMapOrDefault(hashKey, businessConfigMapPair.getFirst(),
//                            businessConfigMapPair.getSecond());
//            AtomicLong totalExpectedOn = new AtomicLong(0);
//            dataCollectionConfigs.forEach(dataCollectionConfig -> totalExpectedOn.getAndAdd(
//                    DateUtils.diffOfTwoTimesInSeconds(dataCollectionConfig.getPanelOnTime(),
//                            dataCollectionConfig.getPanelOffTime())));
//            if (!org.springframework.util.ObjectUtils.isEmpty(dataCollectionConfigs)) {
//                responseDTO.setScheduledUpTimeInSecond(totalExpectedOn.get());
//                responseDTO.setScheduledPlayerUpTime(
//                        convertTimeInReadableFormatFromSec(totalExpectedOn.get()));
//            }
//        });
//    }

//    public List<DeviceLogPercentageReportNewDTO> createDevicePercentageReportFormDeviceOnOffReport(
//            List<DeviceOnOffLogReportNewDTO> deviceOnOffLogReportDTOS,
//            String reportToken
//    ) {
//        //    ":::::::::::::::::::::::Start Making Data for Save::::::::::::::::::::::::::::::::::::::::::");
//        List<DeviceLogPercentageReportNewDTO> percentageReportDTOList = new ArrayList<>();
//        if (!ObjectUtils.isEmpty(deviceOnOffLogReportDTOS)) {
//            Long previousDeviceId = EMPTY_LONG;
//            float weekOffHours = 0, notApplicableHours = 0, notAvailHours = 0, deletedHours = 0;
//            float inActiveHours = 0, onHours = 0, offHours = 0, totalDuration = 0;
//            Date preStatusDate = null;
//            PercentageParamDTO paramDTO = new PercentageParamDTO();
//            for (DeviceOnOffLogReportNewDTO responseDTO : deviceOnOffLogReportDTOS) {
//                boolean addInFlag = true;
//                if (responseDTO.getDeviceId().equals(previousDeviceId)) {
//                    if (responseDTO.getDate().equals(preStatusDate)) {
//                        totalDuration = totalDuration + responseDTO.getDurationInSeconds();
//                        if (totalDuration > 0) {
//                            switch (responseDTO.getDeviceStatus()) {
//                                case ON:
//                                    onHours += responseDTO.getDurationInSeconds();
//                                    break;
//                                case OFF:
//                                    offHours += responseDTO.getDurationInSeconds();
//                                    break;
//                                case NOT_APPLICABLE:
//                                    notApplicableHours += responseDTO.getDurationInSeconds();
//                                    break;
//                                case NOT_AVAILABLE:
//                                    notAvailHours += responseDTO.getDurationInSeconds();
//                                    break;
//                                case DATA_DELETED:
//                                    deletedHours += responseDTO.getDurationInSeconds();
//                                    break;
//                                case WEEK_OFF:
//                                    weekOffHours += responseDTO.getDurationInSeconds();
//                                    break;
//                                case INACTIVE:
//                                    inActiveHours += responseDTO.getDurationInSeconds();
//                                    break;
//                            }
//                            paramDTO.setOnHours(onHours);
//                            paramDTO.setOffHours(offHours);
//                            paramDTO.setWeekOffHours(weekOffHours);
//                            paramDTO.setNotApplicableHours(notApplicableHours);
//                            paramDTO.setNotAvailHours(notAvailHours);
//                            paramDTO.setInActiveHours(inActiveHours);
//                            paramDTO.setDeletedHours(deletedHours);
//                            paramDTO.setOnPercentage((onHours * 100) / totalDuration);
//                            paramDTO.setOffPercentage((offHours * 100) / totalDuration);
//                            paramDTO.setWeekOffPercentage((weekOffHours * 100) / totalDuration);
//                            paramDTO.setNotApplicablePercentage((notApplicableHours * 100) / totalDuration);
//                            paramDTO.setNotAvailPercentage((notAvailHours * 100) / totalDuration);
//                            paramDTO.setInActivePercentage((inActiveHours * 100) / totalDuration);
//                            paramDTO.setDeletedPercentage((deletedHours * 100) / totalDuration);
//                        }
//
//                        addInFlag = false;
//                        updatePanelPerLogList(percentageReportDTOList, paramDTO);
//                    }
//                }
//                if (addInFlag) {
//                    totalDuration = responseDTO.getDurationInSeconds();
//                    paramDTO = new PercentageParamDTO();
//                    onHours = 0;
//                    offHours = 0;
//                    weekOffHours = 0;
//                    notApplicableHours = 0;
//                    notAvailHours = 0;
//                    inActiveHours = 0;
//                    deletedHours = 0;
//                    if (totalDuration > 0) {
//                        switch (responseDTO.getDeviceStatus()) {
//                            case ON:
//                                onHours += responseDTO.getDurationInSeconds();
//                                break;
//                            case OFF:
//                                offHours += responseDTO.getDurationInSeconds();
//                                break;
//                            case NOT_APPLICABLE:
//                                notApplicableHours += responseDTO.getDurationInSeconds();
//                                break;
//                            case NOT_AVAILABLE:
//                                notAvailHours += responseDTO.getDurationInSeconds();
//                                break;
//                            case DATA_DELETED:
//                                deletedHours += responseDTO.getDurationInSeconds();
//                                break;
//                            case WEEK_OFF:
//                                weekOffHours += responseDTO.getDurationInSeconds();
//                                break;
//                            case INACTIVE:
//                                inActiveHours += responseDTO.getDurationInSeconds();
//                                break;
//                        }
//                        paramDTO.setOnHours(onHours);
//                        paramDTO.setOffHours(offHours);
//                        paramDTO.setWeekOffHours(weekOffHours);
//                        paramDTO.setNotAvailHours(notAvailHours);
//                        paramDTO.setInActiveHours(inActiveHours);
//                        paramDTO.setDeletedHours(deletedHours);
//                        paramDTO.setOnPercentage((onHours * 100) / totalDuration);
//                        paramDTO.setOffPercentage((offHours * 100) / totalDuration);
//                        paramDTO.setWeekOffPercentage((weekOffHours * 100) / totalDuration);
//                        paramDTO.setNotApplicablePercentage((notApplicableHours * 100) / totalDuration);
//                        paramDTO.setNotAvailPercentage((notAvailHours * 100) / totalDuration);
//                        paramDTO.setInActivePercentage((inActiveHours * 100) / totalDuration);
//                        paramDTO.setDeletedPercentage((deletedHours * 100) / totalDuration);
//                    }
//                    DeviceLogPercentageReportNewDTO percentageResponseDTO =
//                            createPercentageReportNewDto(responseDTO, paramDTO, reportToken);
//                    percentageReportDTOList.add(percentageResponseDTO);
//                    previousDeviceId = responseDTO.getDeviceId();
//                    preStatusDate = responseDTO.getDate();
//                }
//            }
//        }
//        /*:::::::::::::::::::::::End Making Data for Save::::::::::::::::::::::::::::::::::::::::::*/
//        return percentageReportDTOList;
//    }

//    private void updatePanelPerLogList(List<DeviceLogPercentageReportNewDTO> toBeSaveInDbList,
//                                       PercentageParamDTO paramDTO) {
//        int lastInsertedIndex = toBeSaveInDbList.size() - 1;
//        toBeSaveInDbList.get(lastInsertedIndex)
//                .setOnHours(convertTimeInReadableFormatFromMilliSec(paramDTO.getOnHours()));
//        toBeSaveInDbList.get(lastInsertedIndex)
//                .setOffHours(convertTimeInReadableFormatFromMilliSec(paramDTO.getOffHours()));
//        toBeSaveInDbList.get(lastInsertedIndex)
//                .setWeekOffHours(convertTimeInReadableFormatFromMilliSec(paramDTO.getWeekOffHours()));
//        toBeSaveInDbList.get(lastInsertedIndex)
//                .setNotApplicableHours(
//                        convertTimeInReadableFormatFromMilliSec(paramDTO.getNotApplicableHours()));
//        toBeSaveInDbList.get(lastInsertedIndex)
//                .setDataNotAvailableHours(
//                        convertTimeInReadableFormatFromMilliSec(paramDTO.getNotAvailHours()));
//        toBeSaveInDbList.get(lastInsertedIndex)
//                .setInActiveHours(convertTimeInReadableFormatFromMilliSec(paramDTO.getInActiveHours()));
//        toBeSaveInDbList.get(lastInsertedIndex)
//                .setDataDeletedHours(
//                        convertTimeInReadableFormatFromMilliSec(paramDTO.getDeletedHours()));
//        toBeSaveInDbList.get(lastInsertedIndex)
//                .setOnPercentage(convertFloatToStringAfterRounding(paramDTO.getOnPercentage()));
//        toBeSaveInDbList.get(lastInsertedIndex)
//                .setOffPercentage(convertFloatToStringAfterRounding(paramDTO.getOffPercentage()));
//        toBeSaveInDbList.get(lastInsertedIndex)
//                .setWeekOffPercentage(convertFloatToStringAfterRounding(paramDTO.getWeekOffPercentage()));
//        toBeSaveInDbList.get(lastInsertedIndex)
//                .setNotApplicablePercentage(
//                        convertFloatToStringAfterRounding(paramDTO.getNotApplicablePercentage()));
//        toBeSaveInDbList.get(lastInsertedIndex)
//                .setDataNotAvailablePercentage(
//                        convertFloatToStringAfterRounding(paramDTO.getNotAvailPercentage()));
//        toBeSaveInDbList.get(lastInsertedIndex)
//                .setInActivePercentage(convertFloatToStringAfterRounding(paramDTO.getInActivePercentage()));
//        toBeSaveInDbList.get(lastInsertedIndex)
//                .setDataDeletedPercentage(
//                        convertFloatToStringAfterRounding(paramDTO.getDeletedPercentage()));
//    }
//
//    private DeviceLogPercentageReportNewDTO createPercentageReportNewDto(
//            DeviceOnOffLogReportNewDTO responseDTO, PercentageParamDTO paramDTO, String reportToken) {
//        return new DeviceLogPercentageReportNewDTO(
//                responseDTO.getDeviceId(),
//                responseDTO.getDeviceName(), responseDTO.getLocationId(),
//                responseDTO.getLocationName(), responseDTO.getDeviceGroupId(),
//                responseDTO.getDeviceGroupName(),
//                responseDTO.getDate(),
//                responseDTO.getScheduledPlayerUpTime(),
//                convertTimeInReadableFormatFromMilliSec(paramDTO.getOnHours()),
//                convertTimeInReadableFormatFromMilliSec(paramDTO.getOffHours()),
//                convertTimeInReadableFormatFromMilliSec(paramDTO.getWeekOffHours()),
//                convertTimeInReadableFormatFromMilliSec(paramDTO.getNotApplicableHours()),
//                convertTimeInReadableFormatFromMilliSec(paramDTO.getNotAvailHours()),
//                convertTimeInReadableFormatFromMilliSec(paramDTO.getInActiveHours()),
//                convertTimeInReadableFormatFromMilliSec(paramDTO.getDeletedHours()),
//                convertFloatToStringAfterRounding(paramDTO.getOnPercentage()),
//                convertFloatToStringAfterRounding(paramDTO.getOffPercentage()),
//                convertFloatToStringAfterRounding(paramDTO.getWeekOffPercentage()),
//                convertFloatToStringAfterRounding(paramDTO.getNotApplicablePercentage()),
//                convertFloatToStringAfterRounding(paramDTO.getNotAvailPercentage()),
//                convertFloatToStringAfterRounding(paramDTO.getInActivePercentage()),
//                convertFloatToStringAfterRounding(paramDTO.getDeletedPercentage()),
//                reportToken
//        );
//    }
//
//    private void updateSummaryStringInDtoLists(List<DeviceOnOffLogReportNewDTO> responseDTOS) {
//        Map<String, String> totalDataMsgStringMap = new HashMap<>();
//        Map<String, List<DeviceOnOffLogReportNewDTO>> perDateDtoMap = new HashMap<>();
//        responseDTOS.forEach(dto -> {
//            String key = getKeysForHashMap(dto.getDeviceId(), dto.getDate());
//            List<DeviceOnOffLogReportNewDTO> perDayList = perDateDtoMap.getOrDefault(key,
//                    Lists.newArrayList());
//            perDayList.add(dto);
//            perDateDtoMap.put(key, perDayList);
//        });
//        perDateDtoMap.forEach((key, dtoList) -> {
//            String totalMessage =
//                    calculateDeviceStatusDataAndAddTotalDataStringThenReturn(
//                            dtoList);
//            totalDataMsgStringMap.put(key, totalMessage);
//        });
//        responseDTOS.forEach(dto -> {
//            String key = getKeysForHashMap(dto.getDeviceId(), dto.getDate());
//            dto.setTotalSummary(totalDataMsgStringMap.getOrDefault(key, ""));
//        });
//    }
//
//    private void checkIsDeviceCreatedOrDeletedOnWeekOffDay(Map<Long, Device> mapOfDevices,
//                                                           DataCollectionConfig currentDateConfig,
//                                                           List<DeviceData> deviceDataUnderConfig) {
//        /*
//         * Here in case of week off  for createdDate and for deleted date or same day deleted and created date week off is handled in DeviceNotApplicableReport.java
//         * hack enabled for maintain WEEK_OFF and NOT_APPLICABLE entries
//         * when device is onBoarded on week off day then device will send entry of week off on panelOn and panel off time only.
//         * from server end NOT_APPLICABLE AND WEEK_OFF both entry is creating now, so we can are now changing startTime ad device createdDate
//         * example 1 if device config is 10:00 to 18:00 and device is created on 12:00 and today is week_off then, entries should be like
//         *  1.> 10:00 - 12:00 NOT_APPLICABLE
//         *  2.> 12:00 - 18:00 WEEK_OFF
//         * example 2 if device config is 12:00 to 09:00 and device is created on 22:00 and today is week_off then, entries should be like
//         *  1.> 12:00 - 22:00 NOT_APPLICABLE
//         *  2.> 22:00 - 23:59:59 WEEK_OFF
//         * example 3 if device config is 12:00 to 09:00 and device is created on 06:00 next day  and today is week_off then, entries should be like
//         *  1.> 00:00:00 - 06:00 NOT_APPLICABLE
//         *  2.> 06:00 - 09:00 WEEK_OFF
//         *  note:- in case of example 3 12:00-23:59:59 status is missed because device is not registered in that config slot.
//         */
//
//        //find first deviceData for getting date and deviceId
//        Optional<DeviceData> deviceData = deviceDataUnderConfig.stream().findFirst();
//        deviceData.ifPresent(data -> {
//            Device device = mapOfDevices.get(data.getDeviceId());
//            if (Objects.nonNull(device)) {
//                //we are iterating deviceData object list on basis of device and date.
//                // in case of week_off day device is either created or deleted then week off entry of whole day will be create from DeviceNotApplicableReport.java
//                if (isDeviceCreatedOrDeletedOnWeekOffDay(device, currentDateConfig,
//                        data.getTimeOfStatus())) {
//                    deviceDataUnderConfig.clear();
//                }
//            }
//        });
//    }
//
//    public String getLogReportTableName(Long customerId, String monthYearSuffix) {
//        return ReportUtilsKt.generateNewCustomerWiseAndDateRangeWiseTableName(
//                getLogReportTableNamePair(),
//                new Pair<>(customerId, monthYearSuffix));
//    }

    public Pair<String, String> getLogReportTableNamePair() {
        return new Pair<>(TenantContext.getCurrentSlug(), ReportConstants.DEVICE_LOG_TEMP_NEW_TABLE_NAME);
    }

    public Pair<String, String> getLogPerReportTableNamePair() {
        return new Pair<>(TenantContext.getCurrentSlug(), ReportConstants.DEVICE_LOG_PERCENTAGE_NEW_TABLE_NAME);
    }
//
//    public String getLogPerReportTableName(Long customerId, String monthYearSuffix) {
//        return ReportUtilsKt.generateNewCustomerWiseAndDateRangeWiseTableName(
//                getLogPerReportTableNamePair(),
//                new Pair<>(customerId, monthYearSuffix));
//    }
}

