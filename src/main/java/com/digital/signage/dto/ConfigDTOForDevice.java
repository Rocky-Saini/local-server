package com.digital.signage.dto;

import com.digital.signage.models.DeviceConfig;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author -Ravi Kumar created on 1/19/2023 10:07 AM
 * @project - Digital Sign-edge
 */
public class ConfigDTOForDevice extends DeviceConfig {

    private String twitterSDKClientKey;
    private String twitterSDKConsumerKey;
    private String rabbitMqHost;
    private String baseServerUrlForDevice;
    private Long customerId;
    private String customerCode;
    private boolean alwaysUseRabbitMqForOnPremise;
    //this property will be used only for angular
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Boolean showUploadDemographicDataInterval;

    public Boolean getShowUploadDemographicDataInterval() {
        return showUploadDemographicDataInterval;
    }

    public void setShowUploadDemographicDataInterval(Boolean showUploadDemographicDataInterval) {
        this.showUploadDemographicDataInterval = showUploadDemographicDataInterval;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getRabbitMqHost() {
        return rabbitMqHost;
    }

    public void setRabbitMqHost(String rabbitMqHost) {
        this.rabbitMqHost = rabbitMqHost;
    }

    public String getTwitterSDKClientKey() {
        return twitterSDKClientKey;
    }

    public void setTwitterSDKClientKey(String twitterSDKClientKey) {
        this.twitterSDKClientKey = twitterSDKClientKey;
    }

    public String getTwitterSDKConsumerKey() {
        return twitterSDKConsumerKey;
    }

    public void setTwitterSDKConsumerKey(String twitterSDKConsumerKey) {
        this.twitterSDKConsumerKey = twitterSDKConsumerKey;
    }

    public boolean getAlwaysUseRabbitMqForOnPremise() {
        return this.alwaysUseRabbitMqForOnPremise;
    }

    public void setAlwaysUseRabbitMqForOnPremise(boolean alwaysUseRabbitMqForOnPremise) {
        this.alwaysUseRabbitMqForOnPremise = alwaysUseRabbitMqForOnPremise;
    }

    public String getBaseServerUrlForDevice() {
        return baseServerUrlForDevice;
    }

    public void setBaseServerUrlForDevice(String baseServerUrlForDevice) {
        this.baseServerUrlForDevice = baseServerUrlForDevice;
    }

    @Override
    public Long getCustomerId() {
        return customerId;
    }

    @Override
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}

