package com.digital.signage.configuration;

import com.digital.signage.models.DataCollectionConfig;
import com.digital.signage.models.DeviceConfig;
import com.digital.signage.util.BeanCopyUtil;

import java.sql.Time;
import java.time.LocalTime;
import java.util.List;

/**
 * @author -Ravi Kumar created on 1/23/2023 4:09 PM
 * @project - Digital Sign-edge
 */
public class ConfigSplitter {
    public static void splitByPanelTime(DeviceConfig deviceConfig,
                                        List<DeviceConfig> splittedConfigs) {
        if (isSplitRequired(deviceConfig.getPanelOnTime().toLocalTime(),
                deviceConfig.getPanelOffTime().toLocalTime())) {
            DeviceConfig config1 = new DeviceConfig();
            DeviceConfig config2 = new DeviceConfig();

            BeanCopyUtil.copy(config1, deviceConfig);
            BeanCopyUtil.copy(config2, deviceConfig);

            config1.setPanelOffTime(Time.valueOf(LocalTime.of(23, 59, 59)));
            config2.setPanelOnTime(Time.valueOf(LocalTime.of(0, 0, 0)));

            splittedConfigs.add(config1);
            splittedConfigs.add(config2);
        } else {

            DeviceConfig dc = new DeviceConfig();
            BeanCopyUtil.copy(dc, deviceConfig);

            splittedConfigs.add(dc);
        }
    }

    public static void splitByPanelTime(DataCollectionConfig dataCollectionConfig,
                                        List<DataCollectionConfig> splittedConfigs) {
        if (isSplitRequired(dataCollectionConfig.getPanelOnTime(),
                dataCollectionConfig.getPanelOffTime())) {
            DataCollectionConfig config1 = new DataCollectionConfig();
            DataCollectionConfig config2 = new DataCollectionConfig();

            BeanCopyUtil.copy(config1, dataCollectionConfig);
            BeanCopyUtil.copy(config2, dataCollectionConfig);

            config1.setPanelOffTime(LocalTime.of(23, 59, 59).toString());
            config2.setPanelOnTime(LocalTime.of(0, 0, 0).toString());

            splittedConfigs.add(config1);
            splittedConfigs.add(config2);
        } else {

            DataCollectionConfig dc = new DataCollectionConfig();
            BeanCopyUtil.copy(dc, dataCollectionConfig);

            splittedConfigs.add(dc);
        }
    }


    public static void splitByBusinessTime(DeviceConfig deviceConfig,
                                           List<DeviceConfig> splittedConfigs) {
        if (isSplitRequired(deviceConfig.getBusinessOffTime().toLocalTime(),
                deviceConfig.getBusinessOnTime().toLocalTime())) {
            DeviceConfig config1 = new DeviceConfig();
            DeviceConfig config2 = new DeviceConfig();

            BeanCopyUtil.copy(config1, deviceConfig);
            BeanCopyUtil.copy(config2, deviceConfig);

            config1.setBusinessOnTime(Time.valueOf(LocalTime.of(23, 59, 59)));
            config2.setBusinessOffTime(Time.valueOf(LocalTime.of(0, 0, 0)));

            splittedConfigs.add(config1);
            splittedConfigs.add(config2);
        } else {

            DeviceConfig dc = new DeviceConfig();
            BeanCopyUtil.copy(dc, deviceConfig);

            splittedConfigs.add(dc);
        }
    }

    public static boolean isSplitRequired(LocalTime on, LocalTime off) {
        return on.isAfter(off);
    }

    private static boolean isSplitRequired(String panelOnTime, String panelOffTime) {
        return isSplitRequired(LocalTime.parse(panelOnTime), LocalTime.parse(panelOffTime));
    }
}
