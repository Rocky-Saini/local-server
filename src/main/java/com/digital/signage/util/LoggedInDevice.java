package com.digital.signage.util;

public class LoggedInDevice {
    private long deviceId;
    private long customerId;
    private Status customerStatus;
    private boolean isCustomerOnboarded;

    public LoggedInDevice(long deviceId, long customerId, Status customerStatus, boolean isCustomerOnboarded) {
        this.deviceId = deviceId;
        this.customerId = customerId;
        this.customerStatus = customerStatus;
        this.isCustomerOnboarded = isCustomerOnboarded;
    }

    public long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(long deviceId) {
        this.deviceId = deviceId;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public Status getCustomerStatus() {
        return customerStatus;
    }

    public void setCustomerStatus(Status customerStatus) {
        this.customerStatus = customerStatus;
    }

    public boolean isCustomerOnboarded() {
        return isCustomerOnboarded;
    }

    public void setCustomerOnboarded(boolean customerOnboarded) {
        isCustomerOnboarded = customerOnboarded;
    }
}

