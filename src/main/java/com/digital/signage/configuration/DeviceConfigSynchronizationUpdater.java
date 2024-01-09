package com.digital.signage.configuration;

import com.digital.signage.models.Device;
import com.digital.signage.models.DeviceConfig;
import com.digital.signage.models.*;
import com.digital.signage.repository.*;
import com.digital.signage.service.Push;
import com.digital.signage.util.ApplicationConstants;
import com.digital.signage.util.PushId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.sql.Time;
import java.time.Duration;
import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @author -Ravi Kumar created on 1/22/2023 7:35 PM
 * @project - Digital Sign-edge
 */
@Component
public class DeviceConfigSynchronizationUpdater {

    private static final Logger logger =
            LoggerFactory.getLogger(DeviceConfigSynchronizationUpdater.class);
    private static final byte MIDNIGHT = 0;
    private static final long TIME_PER_CONFIG_IN_MINUTE = 30;
    private static final long NUMBER_OF_SECONDS_IN_AN_HOUR = TimeUnit.HOURS.toSeconds(1);
    private static final long MILLIS_IN_AN_HOUR = TimeUnit.HOURS.toMillis(1L);
    @Value("${config.number.of.device.per.slot}")
    Integer numberOfDevicesPerSlot;
    @Autowired
    private DeviceConfigRepository deviceConfigRepository;
    //@Autowired
    //private DeviceRepository deviceRepository;
    @Autowired
    private DeviceConfigSyncTimeRepository deviceConfigSyncTimeRepository;
    @Autowired
    private Push push;
    @Autowired
    private DeviceConfigAlgoCycleVersionRepository deviceConfigAlgoCycleVersionRepository;
    @Autowired
    private DeviceConfigAlgoCycleVersionMappingRepository
            deviceConfigAlgoCycleVersionMappingRepository;
    @Autowired
    private DeviceConfigDownloadSlotRepository deviceConfigDownloadSlotRepository;

    @Async(ApplicationConstants.ASYNC_CONFIG_WORKERS)
    @Transactional
    public synchronized void newSyncAlgo() {
        Long versionFromDb = getAlgoVersionFromDb();
        long newVersion = versionFromDb + 1L;

        Map<Integer, PerHourDataHolder> dataHolder = new HashMap<>();
        // set 24 hrs in map
        initMap(dataHolder);

        // find first X devices in each hour and start filling full slots.
        fillFullSlotsInLoop(dataHolder, newVersion, numberOfDevicesPerSlot);

        // find non complete slots and fill devices
        fillNonFullSlots(dataHolder);

        // map is read. now distribute time for devices
        distributeTimeForDevices(dataHolder);
    }

    void distributeTimeForDevices(Map<Integer, PerHourDataHolder> dataHolderMap) {
        dataHolderMap.forEach((hourOfDay, perHourDataHolder) -> {
            Set<Long> deviceIds = perHourDataHolder.getDeviceIds();
            logger.debug("distributeTimeForDevices for number of devices: {}", deviceIds.size());
            if (!deviceIds.isEmpty()) {
                int size = deviceIds.size();
                LocalTime startLocalTime = LocalTime.of(hourOfDay, 0, 0);
                List<DeviceConfigSyncTime> list = new ArrayList<>();

                long x = 1;
                if (size > NUMBER_OF_SECONDS_IN_AN_HOUR) {
                    x = 1;
                } else {
                    x = NUMBER_OF_SECONDS_IN_AN_HOUR / size;
                }

                LocalTime lt = startLocalTime;

                long timeStart = System.currentTimeMillis();

                for (Long deviceId : deviceIds) {
                    lt = lt.plus(Duration.ofSeconds(x));
                    if (startLocalTime.plusHours(1).compareTo(lt) <= 0) {
                        // start with new start time again
                        lt = startLocalTime;
                    }
                    DeviceConfigSyncTime deviceConfigSyncTime = new DeviceConfigSyncTime();
                    deviceConfigSyncTime.setDeviceId(deviceId);
                    deviceConfigSyncTime.setPlanogramSyncStartTime(Time.valueOf(lt));
                    list.add(deviceConfigSyncTime);
                }

                // save
                deviceConfigSyncTimeRepository.saveAll(list);
                logger.debug("time to save each device config for hour, {} is {}ms", hourOfDay,
                        (System.currentTimeMillis() - timeStart));
            }
        });
    }

    void fillNonFullSlots(Map<Integer, PerHourDataHolder> dataHolder) {
        // TODO but not required because of a 1 hour validation
        //No longer required because device business hour should not be less than 1 hr.
    }

    @Transactional
    void fillFullSlotsInLoop(Map<Integer, PerHourDataHolder> dataHolder,
                             long newAlgoCycleVersion, int topX) {
        long start = System.currentTimeMillis();
        boolean isEntireSetComplete = false;

        while (!isEntireSetComplete) {

            dataHolder.forEach((hourOfDay, perHourDataHolder) -> {
                // find remaining devices in this hour whose version is not update
                List<DeviceConfigDownloadSlot> deviceConfigDownloadSlots =
                        deviceConfigDownloadSlotRepository.getFullSlotsHavingLessThanAlogCycleVersionAndSlotStartTimeTopX(
                                newAlgoCycleVersion, Time.valueOf(LocalTime.of(hourOfDay, 0, 0)), topX);
                if (deviceConfigDownloadSlots.size() > 0) {
                    Set<Long> deviceIds =
                            deviceConfigDownloadSlots.stream() // add all these devices to the map
                                    .map(DeviceConfigDownloadSlot::getDeviceId) // get deviceIds from the slots
                                    .collect(Collectors.toSet());
                    perHourDataHolder.getDeviceIds()
                            .addAll(deviceIds); // collect to a set

                    List<DeviceConfigAlgoCycleVersionMapping> mapping =
                            deviceConfigAlgoCycleVersionMappingRepository.getMappingForDevices(deviceIds);

                    List<Long> doneList = new ArrayList<>();

                    mapping.forEach(deviceConfigAlgoCycleVersionMapping -> doneList.add(
                            deviceConfigAlgoCycleVersionMapping.getDeviceId()));

                    // update the version for these devices in DB
                    deviceConfigAlgoCycleVersionMappingRepository.updateVersionForGivenDeviceIds(deviceIds,
                            newAlgoCycleVersion);

                    if (doneList.size() == deviceIds.size()) {
                        // all saved
                        logger.debug("all data saved");
                    } else {
                        List<Long> remainingDeviceIds = new ArrayList<>(deviceIds);
                        remainingDeviceIds.removeAll(doneList);

                        logger.debug("remaining deviceIds list size = {}", remainingDeviceIds.size());

                        List<DeviceConfigAlgoCycleVersionMapping> remainingMapping =
                                remainingDeviceIds.parallelStream().map(remainingDeviceId -> {
                                    logger.debug("remaining deviceId = {}", remainingDeviceId);
                                    DeviceConfigAlgoCycleVersionMapping m = new DeviceConfigAlgoCycleVersionMapping();
                                    m.setAlgoCycleVersion(newAlgoCycleVersion);
                                    m.setDeviceId(remainingDeviceId);
                                    return m;
                                }).collect(Collectors.toList());
                        deviceConfigAlgoCycleVersionMappingRepository.saveAll(remainingMapping);
                    }
                } else {
                    perHourDataHolder.setComplete(true);
                }
            });

            // check if entire set is complete
            isEntireSetComplete = dataHolder.entrySet()
                    .stream()
                    .allMatch(entry -> entry.getValue().isComplete());
        }
        // calculate and update sync time
        long diff = System.currentTimeMillis() - start;
        logger.debug("time to save sync time = {}ms", diff);
    }

    void initMap(Map<Integer, PerHourDataHolder> map) {
        for (int hourOfDay = 0; hourOfDay < 24; hourOfDay++) {
            map.put(hourOfDay, new PerHourDataHolder(new HashSet<>(), true, hourOfDay));
        }
    }

    private Long getAlgoVersionFromDb() {
        DeviceConfigAlgoCycleVersion version = deviceConfigAlgoCycleVersionRepository.findById1();
        return version == null ? 1 : version.getAlgoCycleVersion();
    }

    @Deprecated
    @Async(ApplicationConstants.ASYNC_CONFIG_WORKERS)
    public void updateDeviceContentDownloadTimer() {
        logger.debug(":::::::::::::::::::Config Algorithm Starts ::::::::::::::::::::");
        Map<Integer, List<DeviceConfig>> perSlotConfigMap = getTimeSlotDTOS();
        Set<Integer> slotDTOS = perSlotConfigMap.keySet();
        logger.debug("Getting TimeSlot set as::{}", slotDTOS);
        List<DeviceConfigSyncTime> deviceConfigSyncTimes = new ArrayList<>();
        deviceConfigSyncTimeRepository.findAll().forEach(deviceConfigSyncTimes::add);
        Map<Integer, List<DeviceConfigSyncTime>> updatedPerSlotConfigMap = new HashMap<>();
        Set<DeviceConfigSyncTime> updatedConfigList = new HashSet<>();
        List<Long> impactedDeviceIds = new ArrayList<>();
        slotDTOS.forEach(keys -> {
            List<DeviceConfig> perHourDeviceConfigs = perSlotConfigMap.get(keys);
            LocalTime slotStartTime = LocalTime.of(keys, MIDNIGHT);
            if (!ObjectUtils.isEmpty(perHourDeviceConfigs)) {
                int numberOfConfigsPerHour = perHourDeviceConfigs.size();
                AtomicInteger indexCounter = new AtomicInteger(0);

                perHourDeviceConfigs.forEach(deviceConfig -> {
                    //if size of list is less than NO_OF_DEVICE_CONFIG_PER_SLOT then synchronizedTime  is as start time.
                    //if deviceConfig is greater than NO_OF_DEVICE_CONFIG_PER_SLOT then synchronizedTime is as (slotStartTime+TIME_PER_CONFIG_IN_MINUTE).
                    long numberOfDevicesPerHour = numberOfConfigsPerHour / numberOfDevicesPerSlot;
                    //if size of list is more than 200  than calculate TIME_PER_CONFIG_IN_MINUTE by diving from size of list.

                    double timePerMin =
                            (numberOfDevicesPerHour > 2) ? (TIME_PER_CONFIG_IN_MINUTE * 2.0D)
                                    / numberOfDevicesPerHour
                                    : TIME_PER_CONFIG_IN_MINUTE;
                    int pageCounter = indexCounter.incrementAndGet() / numberOfDevicesPerSlot;
                    LocalTime synchronizedLocalTime =
                            slotStartTime.plusMinutes((long) timePerMin * pageCounter);

                    DeviceConfigSyncTime configSyncTime
                            = deviceConfigSyncTimes.stream().filter(deviceConfigSyncTime ->
                            (deviceConfigSyncTime.getCustomerId().longValue() == deviceConfig.getCustomerId()
                                    .longValue()
                                    && deviceConfigSyncTime.getDeviceId().longValue() == deviceConfig.getDeviceId()
                                    .longValue()
                            )).findAny().orElse(null);
                    Time newTime = Time.valueOf(synchronizedLocalTime);
                    boolean isSameTime = false;
                    if (configSyncTime != null) {
                        Time oldSyncStartTime = configSyncTime.getPlanogramSyncStartTime();
                        isSameTime = newTime.equals(oldSyncStartTime);
                    } else {
                        configSyncTime = new DeviceConfigSyncTime();
                    }
                    if (!isSameTime) impactedDeviceIds.add(deviceConfig.getDeviceId());
                    configSyncTime.setDeviceId(deviceConfig.getDeviceId());
                    configSyncTime.setCustomerId(deviceConfig.getCustomerId());
                    configSyncTime.setPlanogramSyncIntervalInMinutes((long) timePerMin);
                    configSyncTime.setPlanogramSyncStartTime(Time.valueOf(synchronizedLocalTime));
                    List<DeviceConfigSyncTime> valueList =
                            (updatedPerSlotConfigMap.get(keys) != null) ?
                                    updatedPerSlotConfigMap.get(keys) : new ArrayList<>();
                    valueList.add(configSyncTime);
                    updatedPerSlotConfigMap.put(keys, valueList);
                });
            }
        });
        //going to update config table in database.
        updatedPerSlotConfigMap.values().forEach(updatedConfigList::addAll);
        deviceConfigSyncTimeRepository.saveAll(updatedConfigList);

        // send push to impacted device to redownload config
        //todo
        push.sendPush(PushId.ID_CLIENT_REDOWNLOAD_CONFIG, impactedDeviceIds, null);

        logger.debug(":::::::::::::::::::Config Algorithm ends ::::::::::::::::::::");
    }

    private Map<Integer, List<DeviceConfig>> getTimeSlotDTOS() {
        List<DeviceConfig> deviceConfigs = getAllDeviceConfigOfDevice();
        Map<Integer, List<DeviceConfig>> perSlotConfigMap = new HashMap<>();
        if (!ObjectUtils.isEmpty(deviceConfigs)) {
            deviceConfigs.forEach(deviceConfig -> {
                Integer key = deviceConfig.getBusinessOffTime().toLocalTime().getHour();
                key = key == 23 ? 0 : (key + 1);
                List<DeviceConfig> valueList =
                        (perSlotConfigMap.get(key) != null) ?
                                perSlotConfigMap.get(key) : new ArrayList<>();
                valueList.add(deviceConfig);
                perSlotConfigMap.put(key, valueList);
            });
        }
        return perSlotConfigMap;
    }

    /**
     * This method returns config related to per device.
     * in some scenario in config device id is null in that case return config of customer.
     */
    private List<DeviceConfig> getAllDeviceConfigOfDevice() {
        List<DeviceConfig> finalList = new ArrayList<>();
        List<DeviceConfig> deviceConfigAllList = new ArrayList<>();
        List<Device> deviceList = new ArrayList<>();//deviceRepository.findByDeviceIdsAndStatusNot3();
        //deviceConfigRepository.findAll().forEach(deviceConfigAllList::add);
        for (Device device : deviceList) {
            DeviceConfig config;
            //getting find device config on basis of device id.
            config = deviceConfigAllList.stream().filter(deviceConfig ->
                            (deviceConfig.getDeviceId() != null
                                    && device.getDeviceId().longValue() == deviceConfig.getDeviceId().longValue()))
                    .findAny().orElse(null);
            if (config != null) {
                continue;
            }
            //if device config is not found then find config of customer.
            config = deviceConfigAllList.stream()
                    .filter(deviceConfig ->
                            (deviceConfig.getCustomerId().longValue() == device.getCustomerId()
                                    .longValue()
                            ))
                    .filter(deviceConfig -> deviceConfig.getDeviceId() == null)
                    //.filter(deviceConfig -> Boolean.FALSE.equals(deviceConfig.getConfigUpdated())) TODO// no longer required
                    .findAny()
                    .orElse(null);
            //if config is found either device or customer then add in list.
            if (config != null) {
                DeviceConfig deviceConfig = config.clone();
                deviceConfig.setDeviceId(device.getDeviceId());
                finalList.add(deviceConfig);
            }
        }
        return finalList;
    }
}
