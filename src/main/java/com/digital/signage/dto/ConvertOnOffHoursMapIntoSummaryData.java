package com.digital.signage.dto;

import com.digital.signage.models.DeviceLogPercentageReportNewDTO;
import com.digital.signage.util.NumberUtils;
import com.digital.signage.util.TypeEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ConvertOnOffHoursMapIntoSummaryData {

    private static final int OFF = -1;

    public static List<DmbReportResponseData> convert(
            Map<Integer, Integer> hourCount,
            Map<Integer, List<DeviceIdAndName>> hourDevicesMap,
            List<DeviceLogPercentageReportNewDTO> data
    ) {
        int count;
        Integer hour;
        String time;
        int totalRecords = data.size();

        for (DeviceLogPercentageReportNewDTO reportDto : data) {
            time = reportDto.getOnHours();
            count = 0;
            Objects.requireNonNull(time, "time cannot be null");
            if ((time.length() == 8 && "00:00:00".equals(time))
                    || (time.length() == 5 && "00:00".equals(time))) {
                // put it in OFF map
                hour = OFF;
            } else {
                hour = Integer.valueOf(time.substring(0, time.indexOf(':')));
            }
            if (hourCount.containsKey(hour)) {
                count = hourCount.get(hour);
            }
            hourCount.put(hour, count + 1);
            List<DeviceIdAndName> deviceList = hourDevicesMap.get(hour);
            deviceList.add(new DeviceIdAndName(
                    Objects.requireNonNull(reportDto.getDeviceId()),
                    Objects.requireNonNull(reportDto.getDeviceName()))
            );
            hourDevicesMap.put(hour, deviceList);
        }

        List<DmbReportResponseData> responseData = new ArrayList<>();

        for (Map.Entry<Integer, Integer> entry : hourCount.entrySet()) {
            if (entry.getKey() == null) {
                continue;
            }
            TypeEnum type = entry.getKey().equals(OFF) ? TypeEnum.OFF : TypeEnum.ON;
            responseData.add(new DmbReportResponseData(
                    type, // type
                    TypeEnum.ON.equals(type) ? entry.getKey() : null, // number of hrs
                    hourCount.get(entry.getKey()), // count
                    getPercentage(entry.getValue(), totalRecords), // percentage
                    hourDevicesMap.get(entry.getKey()) // device list
            ));
        }
        responseData.add(new DmbReportResponseData(
                TypeEnum.TOTAL, // type
                null, // number of hrs
                totalRecords, // count
                100D, // percentage
                new ArrayList<>()
        ));

        return responseData;
    }

    private static double getPercentage(double count, double total) {
        if (total == 0D) return 0D;
        return NumberUtils.roundOff(((count * 100D) / total), 2);
    }
}

