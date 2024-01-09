package com.digital.signage.service.impl;

import com.digital.signage.dto.BusinessOnOffDto;
import com.digital.signage.models.DeviceConfigDownloadSlot;
import com.digital.signage.repository.DeviceConfigDownloadSlotRepository;
import com.digital.signage.service.DeviceConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author -Ravi Kumar created on 1/25/2023 12:49 AM
 * @project - Digital Sign-edge
 */
@Service
public class DeviceConfigDownloadSlotService {
    private static final Logger LOGGER =
            LoggerFactory.getLogger(DeviceConfigDownloadSlotService.class);
    private static final LocalTime JUST_BEFORE_MIDNIGHT = LocalTime.of(23, 59, 59, 9000);
    @Autowired
    private DeviceConfigDownloadSlotRepository repository;
    @Autowired
    @Lazy
    private DeviceConfigService deviceConfigService;

    public void createSlotsOnceADeviceIsAdded(Long deviceId) {
        Optional<BusinessOnOffDto> businessOnOffDto =
                deviceConfigService.getBusinessOnOffDtoOfADevice(deviceId);
        if (businessOnOffDto.isPresent()) {
            createSlotsOnceADeviceIsAdded(deviceId, businessOnOffDto.get().getBOnTime(),
                    businessOnOffDto.get().getBOffTime());
        } else {
            LOGGER.error("Business config DTO is missing for deviceId = {} and customerId = {}", deviceId);
        }
    }

    public void createSlotsOnceADeviceIsAdded(Long deviceId, Time bOnTime,
                                              Time bOffTime) {
        // create slots
        List<DeviceConfigDownloadSlot> slots = generateSlotsForDeviceWhenAdded(bOffTime, bOnTime);

        slots.forEach(s -> {
            s.setDeviceId(deviceId);
            s.setCustomerId(null);
        });

        // add to database
        repository.saveAll(slots);
    }

    public void removeSlotsOnceADeviceIsRemoved(Long deviceId, Long customerId) {
        List<DeviceConfigDownloadSlot> slots = repository.findAllSlotsOfADevice(customerId, deviceId);
        if (slots.size() > 0) repository.deleteAll(slots);
    }

    public void updateSlotsWhenConfigChanges(Long deviceId, Long customerId, Time bOnTime,
                                             Time bOffTime) {
        removeSlotsOnceADeviceIsRemoved(deviceId, customerId);
        createSlotsOnceADeviceIsAdded(deviceId, bOnTime, bOffTime);
    }

    public List<DeviceConfigDownloadSlot> generateSlotsForDeviceWhenAdded(Time bOff, Time bOn) {
        LocalTime bOffLT = bOff.toLocalTime();
        LocalTime bOnLT = bOn.toLocalTime();
        return generateSlotsForDeviceWhenAdded(bOffLT, bOnLT);
    }

    public List<DeviceConfigDownloadSlot> generateSlotsForDeviceWhenAdded(LocalTime bOffLT,
                                                                          LocalTime bOnLT) {
        List<DeviceConfigDownloadSlot> deviceConfigDownloadSlots = new ArrayList<>();

        int compare = bOffLT.compareTo(bOnLT);
        if (compare == 0) {
            throw new IllegalArgumentException("Both business off and on cannot be of same time");
        }

        boolean isCrossingMidnight = compare > 0;

        if (isCrossingMidnight) {
            // break at mid night
            // 1st break
            addSlotsBeforeMidnight(bOffLT, deviceConfigDownloadSlots);
            // 2nd break
            addSlotsIfNotCrossingMidnight(LocalTime.MIDNIGHT, bOnLT, deviceConfigDownloadSlots);
        } else {
            addSlotsIfNotCrossingMidnight(bOffLT, bOnLT, deviceConfigDownloadSlots);
        }

        return deviceConfigDownloadSlots;
    }

    void addSlotsIfNotCrossingMidnight(LocalTime bOffLT, LocalTime bOnLT,
                                       List<DeviceConfigDownloadSlot> deviceConfigDownloadSlots) {
        DeviceConfigDownloadSlot slot = null;

        LocalTime tempSlot = bOffLT;

        // loop till all slots are complete
        boolean isSlotComplete = false;

        while (!isSlotComplete) {

            slot = new DeviceConfigDownloadSlot();

            slot.setSlotStartTime(tempSlot);

            // is round time so just add a round slot
            // add one hr to it

            boolean isRound = isRoundTime(tempSlot);

            LocalTime nextStart = isRound ? tempSlot.plusHours(1) : getNextRoundOffLocalTime(tempSlot);

            if (nextStart.compareTo(LocalTime.MIDNIGHT) == 0) {
                // next start is MIDNIGHT (00:00:00) so set it to just before MIDNIGHT
                nextStart = JUST_BEFORE_MIDNIGHT;
            }

            int compare = nextStart.compareTo(bOnLT);

            if (compare == 0) {
                // same
                slot.setSlotStopTime(bOnLT);
                slot.setFullSlot(isRound);

                // end loop
                isSlotComplete = true;
            } else if (compare > 0) {
                // greater so end time will be bOnLT
                slot.setSlotStopTime(bOnLT);
                slot.setFullSlot(false);
                isSlotComplete = true;
            } else {
                // lesser
                slot.setFullSlot(isRound);
                slot.setSlotStopTime(nextStart);
                // continue to next loop
            }

            // add to slots
            deviceConfigDownloadSlots.add(slot);

            tempSlot = nextStart;
        }
    }

    void addSlotsBeforeMidnight(LocalTime bOffLT,
                                List<DeviceConfigDownloadSlot> deviceConfigDownloadSlots) {
        DeviceConfigDownloadSlot slot = null;

        LocalTime tempSlot = bOffLT;

        // loop till all slots are complete
        boolean isSlotComplete = false;

        while (!isSlotComplete) {

            slot = new DeviceConfigDownloadSlot();

            slot.setSlotStartTime(tempSlot);

            // is round time so just add a round slot
            // add one hr to it

            boolean isRound = isRoundTime(tempSlot);

            LocalTime nextStart = isRound ? tempSlot.plusHours(1) : getNextRoundOffLocalTime(tempSlot);

            int compare = nextStart.compareTo(JUST_BEFORE_MIDNIGHT);

            if (compare == 0) {
                // same
                slot.setSlotStopTime(nextStart);
                slot.setFullSlot(isRound);

                // end loop
                isSlotComplete = true;
            } else if (nextStart.compareTo(LocalTime.MIDNIGHT) == 0) {
                // is midnight
                slot.setSlotStopTime(LocalTime.MIDNIGHT);
                slot.setFullSlot(tempSlot.compareTo(LocalTime.of(23, 0, 0)) == 0);
                isSlotComplete = true;
            } else if (compare < 0) {
                // lesser
                slot.setFullSlot(isRound);
                slot.setSlotStopTime(nextStart);
                // continue to next loop
            } else if (compare > 0) {
                // not possible
                throw new IllegalStateException("compare cannot be greater than 0");
            }

            // add to slots
            deviceConfigDownloadSlots.add(slot);

            tempSlot = nextStart;
        }
    }

    boolean isRoundTime(Time t) {
        LocalTime lt = t.toLocalTime();
        return isRoundTime(lt);
    }

    boolean isRoundTime(LocalTime lt) {
        return lt.getMinute() == 0 && lt.getSecond() == 0;
    }

    LocalTime getNextRoundOffLocalTime(Time t) {
        LocalTime lt = t.toLocalTime();
        return getNextRoundOffLocalTime(lt);
    }

    LocalTime getNextRoundOffLocalTime(LocalTime lt) {
        LocalTime nextLt = lt.plusHours(1);
        return LocalTime.of(nextLt.getHour(), 0, 0);
    }
}
