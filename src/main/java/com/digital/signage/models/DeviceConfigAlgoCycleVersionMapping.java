package com.digital.signage.models;

import javax.persistence.*;

@Entity
@Table(name = DeviceConfigAlgoCycleVersionMapping.TABLE_NAME)
public class DeviceConfigAlgoCycleVersionMapping {
    public static final String TABLE_NAME = "device_config_algo_cycle_version_mapping";

    public static final String COLUMN_ALGO_CYCLE_VERSION = "algo_cycle_version";
    public static final String COLUMN_DEVICE_ID = "device_id";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "device_id")
    private Long deviceId;

    @Column(name = COLUMN_ALGO_CYCLE_VERSION)
    private Long algoCycleVersion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public Long getAlgoCycleVersion() {
        return algoCycleVersion;
    }

    public void setAlgoCycleVersion(Long algoCycleVersion) {
        this.algoCycleVersion = algoCycleVersion;
    }
}
