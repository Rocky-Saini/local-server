package com.digital.signage.report;

import com.digital.signage.models.DeviceOnOffLogReportNewDTO;
import com.digital.signage.models.DataCollectionConfig;
import com.digital.signage.models.Device;
import com.digital.signage.repository.DeviceDataRepository;
import com.digital.signage.repository.DeviceRepository;
import com.digital.signage.util.DateUtils;
import com.digital.signage.util.ReportsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.digital.signage.util.DataCollectionEnum.OutputStatus.*;
import static com.digital.signage.util.DateUtils.*;

@Component
public class DeviceNotApplicableReport extends ReportsUtils {

    @Autowired
    private DeviceRepository deviceRepository;
    @Autowired
    private DeviceDataRepository deviceDataRepository;
    @Autowired
    private ReportsUtils reportsUtils;


    public List<DeviceOnOffLogReportNewDTO> addNotApplicableEntryInResponseDTOS(
            List<Date> filterDateRange, String reportToken, List<Long> validDeviceIds,
            List<DeviceOnOffLogReportNewDTO> responseDTOS,
            final Map<String, List<DataCollectionConfig>> dataCollectionConfigMapPerDay,
            final Map<Long, List<DataCollectionConfig>> defaultDataCollectionConfigMap) {
        responseDTOS = ObjectUtils.isEmpty(responseDTOS) ? new ArrayList<>() : responseDTOS;
        if (!ObjectUtils.isEmpty(filterDateRange) && !ObjectUtils.isEmpty(validDeviceIds)) {
            List<Device> deviceListFromDb = deviceRepository.findByDeviceIds(validDeviceIds);
            Map<Long, Device> deviceMap =
                    deviceListFromDb.stream().collect(Collectors.toMap(Device::getDeviceId,
                            Function.identity()));
            final List<DeviceOnOffLogReportNewDTO> reportNewDTOS = new ArrayList<>();
            if (!ObjectUtils.isEmpty(deviceListFromDb) && !ObjectUtils.isEmpty(filterDateRange)) {
                filterDateRange.forEach(date ->
                        validDeviceIds.forEach(deviceId -> {
                            Device device = deviceMap.get(deviceId);
                            if (device != null) {
                                //fill NA as status as either when device is created after requested from date or device is delete before requested to date.
                                if (isBothDateOfSameDate(date, device.getCreatedOn()) || (
                                        device.getDeletedOn() != null && isBothDateOfSameDate(date,
                                                device.getDeletedOn()))) {
                                    String hashKey = getKeysForHashMap(deviceId, date);
                                    List<DataCollectionConfig> dataCollectionConfigs =
                                            chooseConfigFromMapOrDefault(hashKey, dataCollectionConfigMapPerDay,
                                                    defaultDataCollectionConfigMap);
                                    for (DataCollectionConfig currentDateConfig : dataCollectionConfigs) {
                                        /*
                                         * handle when same date device is created and deleted
                                         * */
                                        if (isDeletedAndCreatedOnSameDateDateUnderConfig(currentDateConfig, device,
                                                date)) {
                                            addSameCreatedAndCreatedNotApplicableEntry(currentDateConfig, date, device, reportToken,
                                                    reportNewDTOS);
                                        } else {
                                            DeviceOnOffLogReportNewDTO notApplicableDto =
                                                    createNotApplicableEntry(currentDateConfig, device, date, reportToken);
                                            if (notApplicableDto != null) reportNewDTOS.add(notApplicableDto);
                                        }
                                        //add week off for createDate and deletedDate if device is deleted or created on week_off
                                        DeviceOnOffLogReportNewDTO weekOffOnDeletedDate =
                                                createWeekOffDeletedDayEntry(currentDateConfig, device, date, reportToken);
                                        if (weekOffOnDeletedDate != null) reportNewDTOS.add(weekOffOnDeletedDate);
                                    }
                                }
                            }
                        })
                );
            }
            if (!ObjectUtils.isEmpty(reportNewDTOS)) {
                responseDTOS.addAll(reportNewDTOS);
            }
        }
        return responseDTOS;
    }

    private void addSameCreatedAndCreatedNotApplicableEntry(DataCollectionConfig currentDateConfig, Date date,
                                                            Device device, String reportToken, List<DeviceOnOffLogReportNewDTO> reportNewDTOS) {
        reportNewDTOS.add(
                 createResponseDTORow(device,
                        combineUtilDateAndTime(date, currentDateConfig.getPanelOnTime()), device.getCreatedOn(),
                        reportToken,
                        NOT_APPLICABLE,null));
        reportNewDTOS.add(createResponseDTORow(device, device.getDeletedOn(),
                combineUtilDateAndTime(date, currentDateConfig.getPanelOffTime()), reportToken,
                NOT_APPLICABLE,null));
    }

    private boolean isDeletedAndCreatedOnSameDateDateUnderConfig(
            DataCollectionConfig currentDateConfig, Device device, Date date) {
        boolean isSameDayDeleted =
                device.getDeletedOn() != null && isBothDateOfSameDate(device.getCreatedOn(),
                        device.getDeletedOn());
        if (isSameDayDeleted) {
            Date panelOnTime = combineUtilDateAndTime(date, currentDateConfig.getPanelOnTime());
            Date panelOffTime = combineUtilDateAndTime(date, currentDateConfig.getPanelOffTime());
            return
                    isMeBetweenTheseTwoDates(device.getCreatedOn(), panelOnTime, panelOffTime)
                            && isMeBetweenTheseTwoDates(device.getDeletedOn(), panelOnTime, panelOffTime);
        }
        return false;
    }

    private DeviceOnOffLogReportNewDTO createWeekOffDeletedDayEntry(
            DataCollectionConfig currentDateConfig, Device device, Date date, String reportToken) {
        /*
         * WEEK_OFF handle on deletedDate and createDate , add one week_off entry if today is week_Off And today device is deleted or created
         * Example 1: config time 09:00 to 18:00 and today is week_off and device is deleted at 15:00
         *   entries{
         *      1.> 09:00 to 15:00  WEEK_OFF
         *      2.> 15:00 to 18:00 NOT_APPLICABLE
         *     }
         * Example 2: config time 10:00 to 08:00 and today and tomorrow is week_off and device is deleted at 15:00
         *   entries{
         *      1.> 10:00 to 15:00  WEEK_OFF
         *      2.> 15:00 to 23:59:59 NOT_APPLICABLE
         *     }
         * Example 3: config time 10:00 to 08:00 and today and tomorrow is week_off and device is deleted at 01:00(next day)
         *   entries{
         *      1.> 10:00 to 23:59:59  WEEK_OFF
         *      2.> 00:00:00 to 01:00:00 WEEK_OFF
         *      2.> 01:00 to 08:00 NOT_APPLICABLE
         *     }
         * Example 4: same day deleted case like config is 10:00 to 18:00 and device is created at 12:00 and deleted at 12:30 then
         * entries{
         *  10:00 -12:00 NOT_APPLICABLE
         *  12:00- 12:30 WEEK_OFF
         *  12:30 to 18:00 NOT_APPLICABLE
         *  }
         */

        if (isDeviceCreatedOrDeletedOnWeekOffDay(device, currentDateConfig, date)) {
            Date panelOnTime = combineUtilDateAndTime(date, currentDateConfig.getPanelOnTime());
            Date panelOffTime = combineUtilDateAndTime(date, currentDateConfig.getPanelOffTime());

            boolean isCreatedUnderConfig =
                    isMeBetweenTheseTwoDates(device.getCreatedOn(), panelOnTime, panelOffTime);
            Date startTime = isCreatedUnderConfig ? device.getCreatedOn() : panelOnTime;
            boolean isDeletedUnderConfig =
                    isMeBetweenTheseTwoDates(device.getDeletedOn(), panelOnTime, panelOffTime);
            Date endTime = isDeletedUnderConfig ? device.getDeletedOn() : panelOffTime;
            if (isTheseDatesEqualsOrBefore(endTime, DateUtils.getCurrentTime())) {
                return createResponseDTORow(device, startTime, endTime, reportToken, WEEK_OFF,null);
            }
        }
        return null;
    }

    private DeviceOnOffLogReportNewDTO createNotApplicableEntry(
            DataCollectionConfig currentDateConfig,
            Device device, Date date, String reportToken) {
        Date startTime, endTime;
        Date panelOnTime = combineUtilDateAndTime(date, currentDateConfig.getPanelOnTime());
        Date panelOffTime = combineUtilDateAndTime(date, currentDateConfig.getPanelOffTime());
        boolean isDeviceDeletedUnderConfig =
                device.getDeletedOn() != null && isMeBetweenTheseTwoDates(device.getDeletedOn(),
                        panelOnTime, panelOffTime);
        boolean isDeviceCreatedUnderConfig = isMeBetweenTheseTwoDates(device.getCreatedOn(),
                panelOnTime, panelOffTime);
        /*
         * if device is deleted before config or device created before config then return null
         */
        if (!isDeviceDeletedUnderConfig && !isDeviceCreatedUnderConfig) return null;
        // now make start date and end date
        //in case of createdDate ==date then  startTime= panelOnTime and endTime= createdDate
        //in case of deletedDate ==date then  startTime= deletedDate and endTime= panelOffTime
        if (device.getDeletedOn() != null && isDeviceDeletedUnderConfig) {
            startTime = device.getDeletedOn();
        } else if (currentDateConfig != null
                && currentDateConfig.getPanelOnTime() != null) {
            startTime = panelOnTime;
        } else {
            startTime = floorDate(date);
        }
        if (isDeviceCreatedUnderConfig) {
            endTime = device.getCreatedOn();
        } else if (currentDateConfig != null
                && currentDateConfig.getPanelOffTime() != null) {
            endTime = panelOffTime;
        } else {
            endTime = ceilDate(date);
        }

        boolean isSlotUnderConfigTime =
                currentDateConfig != null
                        && currentDateConfig.getPanelOnTime() != null
                        && currentDateConfig.getPanelOffTime() != null
                        && isaTimeBetweenTheseTimes(getTimeFromTimeStampAsString(startTime),
                        currentDateConfig.getPanelOnTime(), currentDateConfig.getPanelOffTime(), true)
                        && isaTimeBetweenTheseTimes(getTimeFromTimeStampAsString(endTime),
                        currentDateConfig.getPanelOnTime(), currentDateConfig.getPanelOffTime(), true);
        //if time slot under config then add otherwise return null
        if (isSlotUnderConfigTime && isThisPastDate(startTime) && isThisPastDate(endTime)) {
            return createResponseDTORow(device, startTime, endTime, reportToken,
                    NOT_APPLICABLE,null);
        } else {
            return null;
        }
    }
}

