package com.digital.signage.util;

import com.digital.signage.configuration.ConfigSplitter;
import com.digital.signage.dto.PercentageParamDTO;
import com.digital.signage.models.*;
import com.digital.signage.report.XlsHelper;
import com.digital.signage.repository.*;
import com.digital.signage.service.DeviceConfigService;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.EnumUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Nullable;
import java.util.*;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.Collectors;

import static com.digital.signage.util.CommonUtils.*;
import static com.digital.signage.util.DataCollectionEnum.OutputStatus.*;
import static com.digital.signage.util.DateUtils.*;
import static com.digital.signage.util.Message.DevicePanelReport.*;
import static com.digital.signage.util.ReportConstants.*;

@Component
@Primary
public class ReportsUtils {

    private static final Logger logger = LoggerFactory.getLogger(ReportsUtils.class);
    public static final LongAdder LONG_ADDER = new LongAdder();
    public static final String DELIMITER = "<br /><br />";
    public static final Map<DataCollectionEnum.OutputStatus, String>
            DEVICE_REPORT_MESSAGE_MAP =
            ImmutableMap.<DataCollectionEnum.OutputStatus, String>builder()
                    .put(ON, DEVICE_TOTAL_ON_STRING)
                    .put(OFF, DEVICE_TOTAL_OFF_STRING)
                    .put(NOT_APPLICABLE, DEVICE_TOTAL_NOT_APP_STRING)
                    .put(NOT_AVAILABLE, DEVICE_TOTAL_NOT_AVAIL_STRING)
                    .put(INACTIVE, DEVICE_TOTAL_IN_ACTIVE_STRING)
                    .put(WEEK_OFF, DEVICE_TOTAL_WEEK_OFF_STRING)
                    .put(DATA_DELETED, DEVICE_TOTAL_DATA_DELETED_STRING)
                    .put(DISCONNECTED, DEVICE_TOTAL_DISCONNECTED_STRING)
                    .build();

    @Autowired
    private DataCollectionConfigRepository dataCollectionConfigRepository;
    @Autowired
    private DeviceConfigService deviceConfigService;
    @Autowired
    private DeviceRepository deviceRepository;
    @Autowired
    private DeviceStatusHistoryRepository historyRepository;
    @Autowired
    private XlsHelper xlsHelper;
    @Autowired
    private Message message;
    @Autowired
    private DeviceDataRepository deviceDataRepository;
    @Autowired
    private PanelStatusRepository panelStatusRepository;

    public String getKeysForHashMap(
            Long aLong,
            Date configDate
    ) {
        return aLong + "-" + DateTimeUtilsKt.getOnlyDateAsString(configDate);
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

    protected final Map<String, List<DeviceOnOffLogReportNewDTO>> getEntryDateWiseHashtableOfDeviceDTO(
            List<DeviceOnOffLogReportNewDTO> logReportNewDTOS,
            List<Device> devices
    ) {
        Map<String, List<DeviceOnOffLogReportNewDTO>> entryDateWiseMap =
                new HashMap<>(logReportNewDTOS.size());
        logReportNewDTOS.forEach(onOffDTO ->
                devices.forEach(device -> {
                    String hashMapKey = getKeysForHashMap(device.getDeviceId(), onOffDTO.getDate());
                    List<DeviceOnOffLogReportNewDTO> entriesOfThisDate =
                            entryDateWiseMap.containsKey(hashMapKey) ?
                                    entryDateWiseMap.get(hashMapKey) : new ArrayList<>();
                    if (device.getDeviceId().equals(onOffDTO.getDeviceId())) {
                        entriesOfThisDate.add(onOffDTO);
                        entryDateWiseMap.put(hashMapKey, entriesOfThisDate);
                    }
                }));
        return ImmutableMap.copyOf(entryDateWiseMap);
    }

    public Map<String, List<PanelLogReportResponseNewDTO>> getEntryDateWiseHashtableOfPanelDTO(
            List<PanelLogReportResponseNewDTO> logReportNewDTOS,
            List<Panel> panelList
    ) {
        Map<String, List<PanelLogReportResponseNewDTO>> entryDateWiseMap =
                new HashMap<>(logReportNewDTOS.size());
        logReportNewDTOS.forEach(onOffDTO ->
                panelList.forEach(panel -> {
                    String hashMapKey = getKeysForHashMap(panel.getId(), onOffDTO.getDate());
                    List<PanelLogReportResponseNewDTO> entriesOfThisDate =
                            entryDateWiseMap.containsKey(hashMapKey) ?
                                    entryDateWiseMap.get(hashMapKey) : new ArrayList<>();
                    if (panel.getDeviceId().equals(onOffDTO.getDeviceId())) {
                        entriesOfThisDate.add(onOffDTO);
                        entryDateWiseMap.put(hashMapKey, entriesOfThisDate);
                    }
                }));
        return ImmutableMap.copyOf(entryDateWiseMap);
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


    public PanelLogReportResponseNewDTO createResponseDTORow(
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
                /*panel.getDevice().getLocation2().getLocationName()*/null);
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


    // create one row in panel status
    protected DeviceOnOffLogReportNewDTO createResponseDTORow(
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








//    @Deprecated
//    <T> String createXlsFromJson(
//            List<T> lists,
//            Class<T> tClass
//    ) {
//        return org.springframework.util.ObjectUtils.isEmpty(lists) || tClass == null ? null
//                : xlsHelper.convertJsonToXlsPartByPart(lists, tClass).getXlsPath();
//    }
protected List<DataCollectionConfig> chooseConfigFromMapOrDefault(
        String hashTableKey,
        Map<String, List<DataCollectionConfig>> businessConfigMapOfPerDate,
        Map<Long, List<DataCollectionConfig>> defaultBusinessConfigMap
) {
    return businessConfigMapOfPerDate.getOrDefault(
            hashTableKey, findDefaultDeviceConfigFromMap(hashTableKey, defaultBusinessConfigMap));
}

    public DataCollectionEnum.OutputStatus convertDeviceStatusToOutputStatus(
            final DataCollectionEnum.DeviceStatus deviceStatus
    ) {
        if (deviceStatus == null) return null;
        switch (deviceStatus) {
            case ON:
                return ON;
            case OFF:
                return OFF;
            case WEEK_OFF:
                return WEEK_OFF;
            case DATA_DELETED:
                return DATA_DELETED;
        }
        return null;
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

    public boolean isDateInLoopAWeekOff(
            List<Week> weekOffFromRequest,
            Date dateInLoop,
            Long deviceId,
            Map<String, List<DataCollectionConfig>> dataCollectionConfigMapPerDate,
            List<DataCollectionConfig> defaultDeviceConfigs
    ) {
        // first check week off from dto
        // if week is empty then check from db
        if (ObjectUtils.isEmpty(weekOffFromRequest)) {
            // create hash key for map
            String hashKey = getKeysForHashMap(deviceId, dateInLoop);
            //choose config from dataCollection config
            List<DataCollectionConfig> dataCollectionConfigs =
                    dataCollectionConfigMapPerDate.get(hashKey);

            if (!ObjectUtils.isEmpty(dataCollectionConfigs)) {
                // config entry is sent by device
                // so use the week of from the config entry
                weekOffFromRequest = List.of((dataCollectionConfigs.get(0).getWeekOffs()));
            } else {
                // check config entry of the device itself
                weekOffFromRequest = List.of((defaultDeviceConfigs.get(0).getWeekOffs()));
            }
        }
        if (ObjectUtils.isEmpty(weekOffFromRequest)) {
            return false;
        } else {
            return DateTimeUtilsKt.isThisDayOfWeekInGivenDaysArray(dateInLoop, weekOffFromRequest);
        }
    }

    public Map<String, List<DataCollectionConfig>> findDataCollectionConfigFromDB(
            Set<Date> allDateSet,
            Long deviceId
    ) {
        Date startDate = allDateSet.stream().min(Date::compareTo).orElse(null);
        Date endDate = allDateSet.stream().max(Date::compareTo).orElse(null);
        if (Objects.nonNull(startDate) && Objects.nonNull(endDate)) {
            return getDataCollectionConfigTimesList(
                    startDate, endDate, Lists.newArrayList(deviceId)
            );
        }
        return new HashMap<>(0);
    }

    public String generateNewCustomerWiseAndDateRangeWiseTableName(kotlin.Pair<String, String> databaseAndTableName,
                                                                   kotlin.Pair<Long, String> customerIdAndMonthPair) {
        return databaseAndTableName.getSecond();
    }

    public String generateNewCustomerWiseAndDateRangeWiseTableNameWithoutDatabase(String tableNamePrefix,
                                                                                  kotlin.Pair<Long, String> customerIdAndMonthPair) {
        return tableNamePrefix ;
    }

    public String generateNewCustomerWiseAndDateRangeWiseTableName(Pair<String, String> databaseAndTableName,
                                                         Pair<Long, String> customerIdAndMonthPair) {
        return ("`"
                + databaseAndTableName.getFirst()
                + "`."
                + "`"
                + generateNewCustomerWiseAndDateRangeWiseTableNameWithoutDatabase(
                 databaseAndTableName.getSecond(),
                customerIdAndMonthPair = customerIdAndMonthPair)
                + "`"
        );
    }

    public String generateNewCustomerWiseAndDateRangeWiseTableNameWithoutDatabase(String tableNamePrefix,
                                                                        Pair<Long, String> customerIdAndMonthPair)  {
        return (tableNamePrefix
                + "_"
                + customerIdAndMonthPair.getFirst()
                + "_"
                + customerIdAndMonthPair.getSecond()
        );
    }

    public  PanelLogReportResponseNewDTO createResponseDTORow(
            Panel panel,
            Date startTime,
            Date endTime,
            String reportToken,
            DataCollectionEnum.OutputStatus panelStatus
    ) {
        return createResponseDTORow(panel, startTime, endTime, reportToken, panelStatus, null);
    }
    public  List<Device> findValidDeviceLists(List<Panel> panels) {
        if (ObjectUtils.isEmpty(panels)) return Lists.newArrayListWithCapacity(0);
        Set<Device> validDeviceList = new HashSet<>();
        panels.forEach(panel -> {
            if (panel.getDevice() != null) {
                validDeviceList.add(panel.getDevice());
            }
        });
        return new ArrayList<>(validDeviceList);
    }
    public Map<Long, List<DeviceStatusHistory>> getInActiveStatusFromDBUnderConfig(
            List<Device> validDeviceList,
            Date fromDate,
            Date toDate
    ) {
        List<Long> validDeviceIds =
                validDeviceList.stream().map(Device::getDeviceId).collect(Collectors.toList());
        List<DeviceStatusHistory> deviceStatusHistories =
                historyRepository.findAllStatusHistoryByDeviceIdAndDates(
                        validDeviceIds,
                        floorDate(fromDate),
                        ceilDate(toDate)
                );
        //if deviceStatus history not found then find all device which is Currently inactive then find its status.
        if (ObjectUtils.isEmpty(deviceStatusHistories)) {
            List<Device> currentlyInActiveDevice = validDeviceList.stream()
                    .filter(device -> Status.INACTIVE.equals(device.getStatus()))
                    .collect(
                            Collectors.toList());
            if (!ObjectUtils.isEmpty(currentlyInActiveDevice)) {
                currentlyInActiveDevice.forEach(device -> {
                    DeviceStatusHistory deviceStatusHistory =
                            historyRepository.findStatusHistoryByDeviceId(device.getDeviceId());
                    if (deviceStatusHistory != null) {
                        deviceStatusHistories.add(deviceStatusHistory);
                    }
                });
            }
        }
        Map<Long, List<DeviceStatusHistory>> historyMap =
                new HashMap<>(deviceStatusHistories.size());
        //commenting this due to open if partial data under InActive then select in list.
    /*//load all dataCollectionConfig for panelOnOff Time.
    final Hashtable<String, List<DataCollectionConfig>> businessConfigMap = new Hashtable<>();
    if (!ObjectUtils.isEmpty(deviceStatusHistories)) {
      businessConfigMap.putAll(
          getDataCollectionConfigTimesList(fromDate, toDate, validDeviceIds));
    }
    //default config to get panel On OFF time on basis of global config
    final List<DataCollectionConfig> defaultDataCollectionConfig =
        getDefaultDataCollectionConfigTime(
            validDeviceIds);*/

        //map all status in hash table to search fast
        deviceStatusHistories.forEach(statusHistory -> {
            assert statusHistory != null;
            List<DeviceStatusHistory> statusHistories =
                    historyMap.containsKey(statusHistory.getDeviceId()) ? historyMap.get(
                            statusHistory.getDeviceId())
                            : new ArrayList<>();
            //commenting this due to open if partial data under InActive then select in list.
      /*String keyForHashMap =
          getKeysForHashMap(statusHistory.getDeviceId(), statusHistory.getInActiveStartTime());
      boolean isStatusUnderConfig = true;
      for (DataCollectionConfig currentConfig : chooseConfigFromMapOrDefault(keyForHashMap,
          businessConfigMap, defaultDataCollectionConfig)) {
        if (currentConfig != null
            && currentConfig.getPanelOnTime() != null
            && currentConfig.getPanelOffTime() != null) {
          isStatusUnderConfig =
              CommonUtils.isThisTimeBetweenTheseTimes(statusHistory.getInActiveStartTime(),
                  currentConfig.getPanelOnTime(), currentConfig.getPanelOffTime());
          if (isStatusUnderConfig) break;
        }
      }
      if (statusHistory.getInActiveEndTime() != null && isStatusUnderConfig) {
        keyForHashMap =
            getKeysForHashMap(statusHistory.getDeviceId(), statusHistory.getInActiveEndTime());
        for (DataCollectionConfig currentConfig : chooseConfigFromMapOrDefault(keyForHashMap,
            businessConfigMap, defaultDataCollectionConfig)) {
          if (currentConfig != null
              && currentConfig.getPanelOnTime() != null
              && currentConfig.getPanelOffTime() != null) {
            isStatusUnderConfig =
                CommonUtils.isThisTimeBetweenTheseTimes(statusHistory.getInActiveEndTime(),
                    currentConfig.getPanelOnTime(), currentConfig.getPanelOffTime());
            if (isStatusUnderConfig) break;
          }
        }
      }
      if (isStatusUnderConfig)*/
            statusHistories.add(statusHistory);
            historyMap.put(statusHistory.getDeviceId(), statusHistories);
        });
        return historyMap;
    }
    public List<DeviceStatusHistory> getPerDeviceDateWiseStatus(
            Date date,
            List<DeviceStatusHistory> statusHistories,
            DataCollectionConfig currentConfig
    ) {
        List<DeviceStatusHistory> statusHistoriesPerDays = new ArrayList<>();
        Date configOnDate = combineUtilDateAndTime(date, currentConfig.getPanelOnTime());
        Date configOffDate = combineUtilDateAndTime(date, currentConfig.getPanelOffTime());
        statusHistories.forEach(historyFromListItr -> {
            DeviceStatusHistory history = new DeviceStatusHistory();
            BeanCopyUtil.copy(history, historyFromListItr);
            history.setActivityId(null);
            boolean isAmSameDayHistory = false;
            if (history.getInActiveEndTime() != null
                    && isBothDateOfSameDate(
                    history.getInActiveStartTime(), history.getInActiveEndTime())
                    && isBothDateOfSameDate(date, history.getInActiveStartTime())) {
                //SER-2282 case is same day in active but in active start time is less than panel on time.
                //so  now if config on date between inActiveStartTime and inActiveEndTime  then history date will be start from panel on time.
                if (isMeBetweenTheseTwoDates(configOnDate, history.getInActiveStartTime(),
                        history.getInActiveEndTime()
                )) {
                    history.setInActiveStartTime(configOnDate);
                }
                isAmSameDayHistory = true;
            } else if (history.getInActiveEndTime() != null
                    && !isBothDateOfSameDate(
                    history.getInActiveStartTime(), history.getInActiveEndTime())
                    && isBothDateOfSameDate(date, history.getInActiveStartTime())) {
                if (currentConfig != null
                        && currentConfig.getPanelOffTime() != null) {
                    history.setInActiveEndTime(configOffDate);
                }
                isAmSameDayHistory = true;
            } else if (history.getInActiveEndTime() != null
                    && !isBothDateOfSameDate(
                    history.getInActiveStartTime(), history.getInActiveEndTime())
                    && isBothDateOfSameDate(date, history.getInActiveEndTime())
                    //if case like one day before device inactive and reActive before panel on time on next day then do not add in list
                    && isTheseDatesEqualsOrBefore(configOnDate, history.getInActiveEndTime())) {
                if (currentConfig != null
                        && currentConfig.getPanelOnTime() != null) {
                    history.setInActiveStartTime(configOnDate);
                }
                //if mid night cross over [10:00-9:45] then device is inactive yesterday but active on 10:12 means
                // after morning 9:45   then it does change inActiveEndTime as panel off time means 9:45
                if (isTheseDatesEqualsOrAfter(history.getInActiveEndTime(), configOffDate)) {
                    history.setInActiveEndTime(configOffDate);
                }
                isAmSameDayHistory = true;
            } else if (history.getInActiveEndTime() == null && !isBothDateOfSameDate(
                    history.getInActiveStartTime(), date) && date.after(history.getInActiveStartTime())) {
                if (currentConfig != null
                        && currentConfig.getPanelOffTime() != null) {
                    String endTimeForThis = isCurrentTimeLessThanConfigForToday(date, currentConfig) ?
                            getCurrentTimeWithoutDate() : currentConfig.getPanelOffTime();
                    history.setInActiveEndTime(
                            combineUtilDateAndTime(date, endTimeForThis));
                }
                if (currentConfig != null
                        && currentConfig.getPanelOnTime() != null) {
                    history.setInActiveStartTime(configOnDate);
                }
                isAmSameDayHistory = true;
            } else if (history.getInActiveEndTime() != null
                    && isBothDateOfSameDate(
                    history.getInActiveEndTime(), date)) {
                isAmSameDayHistory = true;
            } else if (history.getInActiveEndTime() != null && isMeBetweenTheseTwoDates(
                    date,
                    history.getInActiveStartTime(),
                    history.getInActiveEndTime()
            )) {
                if (currentConfig != null
                        && currentConfig.getPanelOnTime() != null
                        && currentConfig.getPanelOffTime() != null) {
                    history.setInActiveStartTime(configOnDate);
                    history.setInActiveEndTime(configOffDate);
                }
                isAmSameDayHistory = true;
            } else if (history.getInActiveEndTime() == null && isBothDateOfSameDate(
                    history.getInActiveStartTime(), date) && isThisTimeBetweenTheseTimes(
                    history.getInActiveStartTime(), currentConfig.getPanelOnTime(),
                    currentConfig.getPanelOffTime()
            )) {
                if (currentConfig != null
                        && currentConfig.getPanelOffTime() != null) {
                    String endTimeForThis = isCurrentTimeLessThanConfigForToday(date, currentConfig) ?
                            getCurrentTimeWithoutDate() : currentConfig.getPanelOffTime();
                    history.setInActiveEndTime(
                            combineUtilDateAndTime(date, endTimeForThis));
                }
                isAmSameDayHistory = true;
            }
            //check this statusHistory under config then add otherwise ignore
            boolean isStatusUnderConfig =
                    isMeBetweenTheseTwoDates(history.getInActiveStartTime(), configOnDate, configOffDate)
                            && isMeBetweenTheseTwoDates(history.getInActiveEndTime(), configOnDate, configOffDate
                    );
            if (isAmSameDayHistory && isStatusUnderConfig) statusHistoriesPerDays.add(history);
        });
        statusHistoriesPerDays.sort(
                Comparator.comparing(DeviceStatusHistory::getInActiveStartTime));
        return statusHistoriesPerDays;
    }
    public boolean isCurrentTimeLessThanConfigForToday(
            Date date,
            DataCollectionConfig currentDateConfig
    ) {
        return isBothDateOfSameDate(date, new Date()) && ((currentDateConfig == null
                || currentDateConfig.getPanelOffTime() == null)
                || isBeforeTimeInStr(getCurrentTimeWithoutDate(), currentDateConfig.getPanelOffTime()));
    }
    public boolean isThisPastDate(Date startTime) {
        return DateUtils.isTheseDatesEqualsOrBefore(startTime, new Date());
    }

    public boolean isDtoValidToAdd(PanelLogReportResponseNewDTO dto) {
        return isThisPastDate(combineUtilDateAndTime(dto.getDate(), dto.getStartTime()))
                && isThisPastDate(combineUtilDateAndTime(dto.getDate(), dto.getEndTime()));
    }

    public boolean isDtoValidToAdd(DeviceOnOffLogReportNewDTO dto) {
        return isThisPastDate(combineUtilDateAndTime(dto.getDate(), dto.getStartTime()))
                && isThisPastDate(combineUtilDateAndTime(dto.getDate(), dto.getEndTime()));
    }

    public static PanelLogPercentageResponseNewDTO from(
            PanelLogReportResponseNewDTO responseDTO,
            PercentageParamDTO paramDTO,
            String reportToken
    ) {

        PanelLogPercentageResponseNewDTO panelLogPercentageResponseNewDTO = new PanelLogPercentageResponseNewDTO();
        panelLogPercentageResponseNewDTO.setDeviceId(responseDTO.getDeviceId());
        panelLogPercentageResponseNewDTO.setDeviceName(responseDTO.getDeviceName());
        panelLogPercentageResponseNewDTO.setLocationId(responseDTO.getLocationId());
        panelLogPercentageResponseNewDTO.setLocationName(responseDTO.getLocationName());
        panelLogPercentageResponseNewDTO.setDate(responseDTO.getDate());
        panelLogPercentageResponseNewDTO.setScheduledPlayerUpTime(responseDTO.getScheduledPlayerUpTime());
        panelLogPercentageResponseNewDTO.setPanelId(responseDTO.getPanelId());
        panelLogPercentageResponseNewDTO.setPanelName(responseDTO.getPanelName());
        panelLogPercentageResponseNewDTO.setPanelIp(responseDTO.getPanelIp());
        panelLogPercentageResponseNewDTO.setOnHours(convertTimeInReadableFormatFromMilliSec(paramDTO.getOnHours()));
        panelLogPercentageResponseNewDTO.setOffHours(convertTimeInReadableFormatFromMilliSec(paramDTO.getOffHours()));
        panelLogPercentageResponseNewDTO.setWeekOffHours(convertTimeInReadableFormatFromMilliSec(paramDTO.getWeekOffHours()));
        panelLogPercentageResponseNewDTO.setNotApplicablePercentage(convertTimeInReadableFormatFromMilliSec(paramDTO.getNotApplicableHours()));
        panelLogPercentageResponseNewDTO.setNotApplicableHours(convertTimeInReadableFormatFromMilliSec(paramDTO.getNotAvailHours()));
        panelLogPercentageResponseNewDTO.setInActiveHours(convertTimeInReadableFormatFromMilliSec(paramDTO.getInActiveHours()));
        panelLogPercentageResponseNewDTO.setDataDeletedHours(convertTimeInReadableFormatFromMilliSec(paramDTO.getDeletedHours()));
        panelLogPercentageResponseNewDTO.setDisconnectedHours(convertTimeInReadableFormatFromMilliSec(paramDTO.getDisconnectedHours()));
        panelLogPercentageResponseNewDTO.setOnPercentage(convertFloatToStringAfterRounding(paramDTO.getOnPercentage()));
        panelLogPercentageResponseNewDTO.setOffPercentage(convertFloatToStringAfterRounding(paramDTO.getOffPercentage()));
        panelLogPercentageResponseNewDTO.setWeekOffPercentage(convertFloatToStringAfterRounding(paramDTO.getWeekOffPercentage()));
        panelLogPercentageResponseNewDTO.setNotApplicablePercentage(convertFloatToStringAfterRounding(paramDTO.getNotApplicablePercentage()));
        panelLogPercentageResponseNewDTO.setDataNotAvailablePercentage(convertFloatToStringAfterRounding(paramDTO.getNotAvailPercentage()));
        panelLogPercentageResponseNewDTO.setInActivePercentage(convertFloatToStringAfterRounding(paramDTO.getInActivePercentage()));
        panelLogPercentageResponseNewDTO.setDataDeletedPercentage(convertFloatToStringAfterRounding(paramDTO.getDeletedPercentage()));
        panelLogPercentageResponseNewDTO.setDisconnectedPercentage(convertFloatToStringAfterRounding(paramDTO.getDisconnectedPercentage()));
//        return new PanelLogPercentageResponseNewDTO(
//                null,
//                responseDTO.getDeviceId(),
//                responseDTO.getDeviceName(),
//                responseDTO.getLocationId(),
//                responseDTO.getLocationName(),
//                responseDTO.getDate(),
//                responseDTO.getScheduledPlayerUpTime(),
//                responseDTO.getPanelId(),
//                responseDTO.getPanelName(),
//                responseDTO.getPanelIp(),
//                convertTimeInReadableFormatFromMilliSec(paramDTO.getOnHours()),
//                convertTimeInReadableFormatFromMilliSec(paramDTO.getOffHours()),
//                convertTimeInReadableFormatFromMilliSec(paramDTO.getWeekOffHours()),
//                convertTimeInReadableFormatFromMilliSec(paramDTO.getNotApplicableHours()),
//                convertTimeInReadableFormatFromMilliSec(paramDTO.getNotAvailHours()),
//                convertTimeInReadableFormatFromMilliSec(paramDTO.getInActiveHours()),
//                convertTimeInReadableFormatFromMilliSec(paramDTO.getDeletedHours()),
//                convertTimeInReadableFormatFromMilliSec(paramDTO.getDisconnectedHours()),
//                convertFloatToStringAfterRounding(paramDTO.getOnPercentage()),
//                convertFloatToStringAfterRounding(paramDTO.getOffPercentage()),
//                convertFloatToStringAfterRounding(paramDTO.getWeekOffPercentage()),
//                convertFloatToStringAfterRounding(paramDTO.getNotApplicablePercentage()),
//                convertFloatToStringAfterRounding(paramDTO.getNotAvailPercentage()),
//                convertFloatToStringAfterRounding(paramDTO.getInActivePercentage()),
//                convertFloatToStringAfterRounding(paramDTO.getDeletedPercentage()),
//                convertFloatToStringAfterRounding(paramDTO.getDisconnectedPercentage()),
//                reportToken
//        );

        return panelLogPercentageResponseNewDTO;
    }

}

