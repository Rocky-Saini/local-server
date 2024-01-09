package com.digital.signage.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.util.Date;

/**
 * @author -Ravi Kumar created on 1/24/2023 11:48 PM
 * @project - Digital Sign-edge
 */
@Entity(name = "customer_config")
public class CustomerConfig implements EntityModel, Cloneable {
    private static final Logger logger = LoggerFactory.getLogger(CustomerConfig.class);
    public static final String CUSTOMER_CONFIG_ID = "customer_config_id";
    public static final String CUSTOMER_ID = "customer_id";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = CUSTOMER_CONFIG_ID)
    @JsonIgnore
    private Long customerConfigId;
    @JsonIgnore
    @Column(name = CUSTOMER_ID)
    private Long customerId;
    @JsonProperty(CustomerConfigResponseDTO.JSON_KEY_ENCRYPTED_TWITTER_SDK_CLIENT_KEY)
    @Column(name = "encrypted_twitter_sdk_client_key")
    private String encryptedTwitterSDKClientKey;
    @JsonProperty(CustomerConfigResponseDTO.JSON_KEY_ENCRYPTED_TWITTER_SDK_CONSUMER_KEY)
    @Column(name = "encrypted_twitter_sdk_consumer_key")
    private String encryptedTwitterSDKConsumerKey;
    @JsonProperty(CustomerConfigResponseDTO.JSON_KEY_ENCRYPTED_FB_APP_ID)
    @Column(name = "encrypted_facebook_app_id")
    private String encryptedFacebookAppId;
    @JsonProperty(CustomerConfigResponseDTO.JSON_KEY_ENCRYPTED_FB_SECRET_ID)
    @Column(name = "encrypted_facebook_secret")
    private String encryptedFacebookSecret;
    @JsonIgnore
    @Transient
    private String facebookAppId;
    @JsonIgnore
    @Transient
    private String facebookAppSecret;
    @JsonIgnore
    @Transient
    private String twitterSDKConsumerKey;
    @JsonIgnore
    @Transient
    private String twitterSDKClientKey;
    @Column(name = "planogram_publishing_time")
    private Integer planogramPublishingTime;
    @JsonIgnore
    private Date facebookKeyUpdateTimestamp;

    @JsonProperty(CustomerConfigResponseDTO.JSON_KEY_DAILY_DMB_REPORT_TO_EMAIL)
    @Column(name = "daily_dmb_report_to_email")
    private String dailyDMBReportToEmail;

    @JsonProperty(CustomerConfigResponseDTO.JSON_KEY_DAILY_DMB_REPORT_CC_EMAIL)
    @Column(name = "daily_dmb_report_cc_email")
    private String dailyDMBReportCCEmail;

    @JsonProperty("facebook_key_update_timestamp")
    public Date getFacebookKeyUpdateTimestamp() {
        return facebookKeyUpdateTimestamp;
    }

    @JsonIgnore
    public void setFacebookKeyUpdateTimestamp(Date facebookKeyUpdateTimestamp) {
        this.facebookKeyUpdateTimestamp = facebookKeyUpdateTimestamp;
    }

    public Integer getPlanogramPublishingTime() {
        return planogramPublishingTime;
    }

    public void setPlanogramPublishingTime(Integer planogramPublishingTime) {
        this.planogramPublishingTime = planogramPublishingTime;
    }

    public String getFacebookAppId() {
        return facebookAppId;
    }

    public void setFacebookAppId(String facebookAppId) {
        this.facebookAppId = facebookAppId;
    }

    public String getFacebookAppSecret() {
        return facebookAppSecret;
    }

    public void setFacebookAppSecret(String facebookAppSecret) {
        this.facebookAppSecret = facebookAppSecret;
    }

    public String getTwitterSDKConsumerKey() {
        return twitterSDKConsumerKey;
    }

    public void setTwitterSDKConsumerKey(String twitterSDKConsumerKey) {
        this.twitterSDKConsumerKey = twitterSDKConsumerKey;
    }

    public String getTwitterSDKClientKey() {
        return twitterSDKClientKey;
    }

    public void setTwitterSDKClientKey(String twitterSDKClientKey) {
        this.twitterSDKClientKey = twitterSDKClientKey;
    }

    public Long getCustomerConfigId() {
        return customerConfigId;
    }

    public void setCustomerConfigId(Long customerConfigId) {
        this.customerConfigId = customerConfigId;
    }

    public String getEncryptedTwitterSDKClientKey() {
        return encryptedTwitterSDKClientKey;
    }

    public void setEncryptedTwitterSDKClientKey(String encryptedTwitterSDKClientKey) {
        this.encryptedTwitterSDKClientKey = encryptedTwitterSDKClientKey;
    }

    public String getEncryptedTwitterSDKConsumerKey() {
        return encryptedTwitterSDKConsumerKey;
    }

    public void setEncryptedTwitterSDKConsumerKey(String encryptedTwitterSDKConsumerKey) {
        this.encryptedTwitterSDKConsumerKey = encryptedTwitterSDKConsumerKey;
    }

    public String getEncryptedFacebookAppId() {
        return encryptedFacebookAppId;
    }

    public void setEncryptedFacebookAppId(String encryptedFacebookAppId) {
        this.encryptedFacebookAppId = encryptedFacebookAppId;
    }

    public String getEncryptedFacebookSecret() {
        return encryptedFacebookSecret;
    }

    public void setEncryptedFacebookSecret(String encryptedFacebookSecret) {
        this.encryptedFacebookSecret = encryptedFacebookSecret;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getDailyDMBReportToEmail() {
        return dailyDMBReportToEmail;
    }

    public void setDailyDMBReportToEmail(String dailyDMBReportToEmail) {
        this.dailyDMBReportToEmail = dailyDMBReportToEmail;
    }

    public String getDailyDMBReportCCEmail() {
        return dailyDMBReportCCEmail;
    }

    public void setDailyDMBReportCCEmail(String dailyDMBReportCCEmail) {
        this.dailyDMBReportCCEmail = dailyDMBReportCCEmail;
    }

    public CustomerConfig clone() {
        try {
            return (CustomerConfig) super.clone();
        } catch (CloneNotSupportedException e) {
            logger.error("", e);
        }
        return null;
    }
}



