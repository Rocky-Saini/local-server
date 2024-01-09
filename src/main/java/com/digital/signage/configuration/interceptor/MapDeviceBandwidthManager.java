package com.digital.signage.configuration.interceptor;

import com.digital.signage.dto.DateAndDeviceIdKey;
import com.digital.signage.models.Device;
import com.digital.signage.models.DeviceConsumable;
import com.digital.signage.repository.DeviceConsumableRepository;
import com.digital.signage.repository.DeviceRepository;
import com.digital.signage.util.BandWidth;
import com.digital.signage.util.DateUtils;
import com.digital.signage.util.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class MapDeviceBandwidthManager {
    private final HashMap<DateAndDeviceIdKey, Long> deviceIdConsumableMap = new HashMap<>();
    //private static final Logger logger = LoggerFactory.getLogger(DeviceBandwidthManager.class);
    private final Set<Long> deviceIds = new HashSet<>();
    @Autowired
    private DeviceConsumableRepository deviceConsumableRepository;
    @Autowired
    private DeviceRepository deviceRepository;
/*    @Autowired
    private CustomerRepository customerRepository;*/

    public synchronized void insertOrUpdate(Long deviceId, Long consumableInBytes) {
        insertOrUpdate(deviceId, DateUtils.getTodayDate(), consumableInBytes);
    }

    public synchronized void insertOrUpdate(
            Long deviceId,
            Date consumedDate,
            Long consumableInBytes
    ) {
        deviceIdConsumableMap.merge(new DateAndDeviceIdKey(consumedDate, deviceId),
                consumableInBytes, Long::sum);
        deviceIds.add(deviceId);
    }

    public synchronized Long readConsumable(Long deviceId, Date consumedDate) {
        Long consumable = deviceIdConsumableMap.get(
                new DateAndDeviceIdKey(consumedDate, deviceId));
        return consumable == null ? 0L : consumable;
    }

    public synchronized Long readConsumable(Long deviceId) {
        return readConsumable(deviceId, DateUtils.getTodayDate());
    }

    @Transactional
    public synchronized void writeToDb() {
        //long start = System.currentTimeMillis();
        if (ObjectUtils.isEmpty(deviceIds)) {
            return;
        }
        List<java.sql.Date> consumedDateList = deviceIdConsumableMap.keySet()
                .stream()
                .map(dateAndDeviceIdKey ->
                        new java.sql.Date(DateUtils.floorDate(dateAndDeviceIdKey.getConsumedDate()).getTime()))
                .distinct()
                .collect(Collectors.toList());
        List<Long> copyOfdeviceIds = new ArrayList<>(this.deviceIds);
        List<DeviceConsumable> dbDeviceConsumables = deviceConsumableRepository.getDeviceConsumablesByDeviceIdsForDatesIn(
                copyOfdeviceIds,
                consumedDateList
        );
        List<Device> devices = deviceRepository.findByDeviceIds(copyOfdeviceIds);
        Map<DateAndDeviceIdKey, DeviceConsumable> dbDataMap = dbDeviceConsumables
                .stream()
                .collect(Collectors.toMap(deviceConsumable -> new DateAndDeviceIdKey(
                                deviceConsumable.getConsumeDate(),
                                deviceConsumable.getDevice()
                        ),
                        Function.identity()
                ));
        //Set<Long> customerIds = new HashSet<>();
        Map<Long, Device> deviceMap = new HashMap<>();
        for (Device device : devices) {
            //customerIds.add(device.getCustomerId());
            deviceMap.put(device.getDeviceId(), device);
        }
/*        List<Customer> customers = customerRepository.getAllCustomers(new ArrayList<>(customerIds));
        Map<Long, Customer> customerMap = customers.stream().collect(Collectors.toMap(
                Customer::getCustomerId, Function.identity()));*/
        // update db data
        for (Long deviceId : copyOfdeviceIds) {
            for (Date date : consumedDateList) {
                DateAndDeviceIdKey idKey = new DateAndDeviceIdKey(date, deviceId);
                Long data = deviceIdConsumableMap.get(idKey);
                if (data == null) {
                    //this date and device id has no any consumed data.
                    continue;
                }
                DeviceConsumable deviceConsumable = dbDataMap.get(idKey);
                if (deviceConsumable != null) {
                    if (deviceConsumable.getBandwidth() != null) {
                        BandWidth.STORAGE_CAPACITY_UNIT bw = deviceConsumable.getBandwidthUnit();
                        if (BandWidth.STORAGE_CAPACITY_UNIT.BYTE.equals(bw)) {
                            deviceConsumable.setBandwidth(
                                    data + deviceConsumable.getBandwidth());
                        } else if (BandWidth.STORAGE_CAPACITY_UNIT.MB.equals(bw)) {
                            deviceConsumable.setBandwidth(
                                    data + (1024 * 1024 * deviceConsumable.getBandwidth()));
                        } else if (BandWidth.STORAGE_CAPACITY_UNIT.KB.equals(bw)) {
                            deviceConsumable.setBandwidth(
                                    data + (1024 * deviceConsumable.getBandwidth()));
                        } else if (BandWidth.STORAGE_CAPACITY_UNIT.GB.equals(bw)) {
                            deviceConsumable.setBandwidth(
                                    data + (1024 * 1024 * 1024 * deviceConsumable.getBandwidth()));
                        }
                    } else {
                        deviceConsumable.setBandwidth((double) data);
                    }
                    deviceConsumable.setCustomer(/*customerMap.get(deviceMap.get(deviceId).getCustomerId())*/null);
                    deviceConsumable.setBandwidthUnit(BandWidth.STORAGE_CAPACITY_UNIT.BYTE);
                } else {
                    // create a new consumable
                    deviceConsumable = new DeviceConsumable();
                    deviceConsumable.setBandwidth((double) data);
                    deviceConsumable.setConsumeDate(date);
                    deviceConsumable.setCustomer(/*customerMap.get(deviceMap.get(deviceId).getCustomerId())*/null);
                    deviceConsumable.setDevice(deviceId);
                    deviceConsumable.setStatus((byte) Status.ACTIVE.getValue());
                    deviceConsumable.setBandwidthUnit(BandWidth.STORAGE_CAPACITY_UNIT.BYTE);
                    dbDeviceConsumables.add(deviceConsumable);
                }
            }
        }
        // save data to DB
        deviceConsumableRepository.saveAll(dbDeviceConsumables);
        // clear in memory data
        this.deviceIds.clear();
        deviceIdConsumableMap.clear();
        //long operationTime = System.currentTimeMillis() - start;
        //logger.debug("DeviceBandwidthManager::writeToDb operation time = " + operationTime);
    }
}
