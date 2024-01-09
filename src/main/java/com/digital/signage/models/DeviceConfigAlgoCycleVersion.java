package com.digital.signage.models;

import javax.persistence.*;

@Entity
@Table(name = DeviceConfigAlgoCycleVersion.TABLE_NAME)
public class DeviceConfigAlgoCycleVersion {
    public static final String TABLE_NAME = "device_config_algo_cycle_version";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "algo_cycle_version")
    private Long algoCycleVersion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAlgoCycleVersion() {
        return algoCycleVersion;
    }

    public void setAlgoCycleVersion(Long algoCycleVersion) {
        this.algoCycleVersion = algoCycleVersion;
    }
}
