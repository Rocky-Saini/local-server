package com.digital.signage.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author -Ravi Kumar created on 1/24/2023 11:48 PM
 * @project - Digital Sign-edge
 */
public class CustomerConfigResponseDTO {
    public static final String JSON_KEY_ENCRYPTED_TWITTER_SDK_CLIENT_KEY =
            "encryptedTwitterSDKClientKey";
    public static final String JSON_KEY_ENCRYPTED_TWITTER_SDK_CONSUMER_KEY =
            "encryptedTwitterSDKConsumerKey";
    public static final String JSON_KEY_ENCRYPTED_FB_APP_ID =
            "encryptedFacebookAppId";
    public static final String JSON_KEY_ENCRYPTED_FB_SECRET_ID =
            "encryptedFacebookSecret";
    public static final String PLANOGRAM_PUBLISHING_TIME =
            "planogramPublishingTime";

    public static final String JSON_KEY_DAILY_DMB_REPORT_TO_EMAIL =
            "dailyDMBReportToEmail";

    public static final String JSON_KEY_DAILY_DMB_REPORT_CC_EMAIL =
            "dailyDMBReportCCEmail";
    @JsonProperty(JSON_KEY_ENCRYPTED_TWITTER_SDK_CLIENT_KEY)
    private String encryptedTwitterSDKClientKey;
    @JsonProperty(JSON_KEY_ENCRYPTED_TWITTER_SDK_CONSUMER_KEY)
    private String encryptedTwitterSDKConsumerKey;
    @JsonProperty(JSON_KEY_ENCRYPTED_FB_APP_ID)
    private String encryptedFacebookAppId;
    @JsonProperty(JSON_KEY_ENCRYPTED_FB_SECRET_ID)
    private String encryptedFacebookSecret;

    @JsonProperty(PLANOGRAM_PUBLISHING_TIME)
    private Integer planogramPublishingTime;

    @JsonProperty(JSON_KEY_DAILY_DMB_REPORT_TO_EMAIL)
    private String dailyDMBReportToEmail;

    @JsonProperty(JSON_KEY_DAILY_DMB_REPORT_CC_EMAIL)
    private String dailyDMBReportCCEmail;

    public Integer getPlanogramPublishingTime() {
        return planogramPublishingTime;
    }

    public void setPlanogramPublishingTime(Integer planogramPublishingTime) {
        this.planogramPublishingTime = planogramPublishingTime;
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
}
