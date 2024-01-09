package com.digital.signage.models;

import com.digital.signage.util.BandWidth.STORAGE_CAPACITY_UNIT;

import javax.persistence.*;
import java.util.Date;

/**
 * @author -Ravi Kumar created on 2/1/2023 1:17 AM
 * @project - Digital Sign-edge
 */
@Entity
@Table(name = "device_consumable")
public class DeviceConsumable implements EntityModel {

    public static final String CUSTOMER_ID = "customer";
    public static final String DEVICE_ID = "device";
    public static final String CONSUME_DATE = "consumeDate";
    public static final String BANDWIDTH = "bandwidth";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "device_consumable_id")
    private Long deviceConsumableId;

    @Column(name = "device_id")
    private Long device;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "bandwidth", columnDefinition = "Decimal(10,2)")
    private Double bandwidth;

    @Column(name = "consume_date")
    private Date consumeDate;

    @Column(name = "status")
    private byte status;

    @Column(name = "created_by")
    private Long createdBy;

    @Enumerated(EnumType.STRING)
    @Column(name = "bandwidth_unit")
    private STORAGE_CAPACITY_UNIT bandwidthUnit;

    public Long getDeviceConsumableId() {
        return deviceConsumableId;
    }

    public void setDeviceConsumableId(Long deviceConsumableId) {
        this.deviceConsumableId = deviceConsumableId;
    }

    public Long getDevice() {
        return device;
    }

    public void setDevice(Long device) {
        this.device = device;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Double getBandwidth() {
        return bandwidth;
    }

    public void setBandwidth(Double bandwidth) {
        this.bandwidth = bandwidth;
    }

    public Date getConsumeDate() {
        return consumeDate;
    }

    public void setConsumeDate(Date consumeDate) {
        this.consumeDate = consumeDate;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public STORAGE_CAPACITY_UNIT getBandwidthUnit() {
        return bandwidthUnit;
    }

    public void setBandwidthUnit(STORAGE_CAPACITY_UNIT bandwidthUnit) {
        this.bandwidthUnit = bandwidthUnit;
    }
}

