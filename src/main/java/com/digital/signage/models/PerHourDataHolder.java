package com.digital.signage.models;

import java.util.Set;

/**
 * @author -Ravi Kumar created on 1/24/2023 11:28 PM
 * @project - Digital Sign-edge
 */
public class PerHourDataHolder {
    private Set<Long> deviceIds;
    private boolean isComplete;
    private boolean isFullSlot;
    private int hourOfDay;

    public PerHourDataHolder(Set<Long> deviceIds, boolean isFullSlot, int hourOfDay) {
        this.deviceIds = deviceIds;
        this.isFullSlot = isFullSlot;
        this.hourOfDay = hourOfDay;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    public Set<Long> getDeviceIds() {
        return deviceIds;
    }

    public void setDeviceIds(Set<Long> deviceIds) {
        this.deviceIds = deviceIds;
    }

    public boolean isFullSlot() {
        return isFullSlot;
    }

    public void setFullSlot(boolean fullSlot) {
        isFullSlot = fullSlot;
    }

    public int getHourOfDay() {
        return hourOfDay;
    }

    public void setHourOfDay(int hourOfDay) {
        this.hourOfDay = hourOfDay;
    }
}
