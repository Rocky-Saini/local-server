package com.digital.signage.models;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "silent_push_entity")
public class SilentPushEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long silentPushId;
    private String fcmRequest;
    private String fcmResponse;
    private Date timeOfSendingPush;
    private Integer httpStatusCode;
    private String fcmRegistrationId;
    private Long userId;
    private Long customerId;
    private Integer messageType;
    private Long entityId;
    private String entityType;
    private String entityPage;

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public String getEntityPage() {
        return entityPage;
    }

    public void setEntityPage(String entityPage) {
        this.entityPage = entityPage;
    }

    public String getFcmRegistrationId() {
        return fcmRegistrationId;
    }

    public void setFcmRegistrationId(String fcmRegistrationId) {
        this.fcmRegistrationId = fcmRegistrationId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Integer getMessageType() {
        return messageType;
    }

    public void setMessageType(Integer messageType) {
        this.messageType = messageType;
    }

    public Integer getHttpStatusCode() {
        return httpStatusCode;
    }

    public void setHttpStatusCode(Integer httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }

    public Date getTimeOfSendingPush() {
        return timeOfSendingPush;
    }

    public void setTimeOfSendingPush(Date timeOfSendingPush) {
        this.timeOfSendingPush = timeOfSendingPush;
    }

    public String getFcmRequest() {
        return fcmRequest;
    }

    public void setFcmRequest(String fcmRequest) {
        this.fcmRequest = fcmRequest;
    }

    public String getFcmResponse() {
        return fcmResponse;
    }

    public void setFcmResponse(String fcmResponse) {
        this.fcmResponse = fcmResponse;
    }

    public Long getSilentPushId() {
        return silentPushId;
    }

    public void setSilentPushId(Long silentPushId) {
        this.silentPushId = silentPushId;
    }

    public static final class Builder {
        private String fcmRequest;
        private String fcmResponse;
        private Date timeOfSendingPush;
        private Integer httpStatusCode;
        private String fcmRegistrationId;
        private Long userId;
        private Long customerId;
        private Integer messageType;
        private Long entityId;
        private String entityType;
        private String entityPage;

        private Builder() {
        }

        public static Builder aSilentPushEntity() {
            return new Builder();
        }

        public Builder withFcmRequest(String fcmRequest) {
            this.fcmRequest = fcmRequest;
            return this;
        }

        public Builder withFcmResponse(String fcmResponse) {
            this.fcmResponse = fcmResponse;
            return this;
        }

        public Builder withTimeOfSendingPush(Date timeOfSendingPush) {
            this.timeOfSendingPush = timeOfSendingPush;
            return this;
        }

        public Builder withHttpStatusCode(Integer httpStatusCode) {
            this.httpStatusCode = httpStatusCode;
            return this;
        }

        public Builder withFcmRegistrationId(String fcmRegistrationId) {
            this.fcmRegistrationId = fcmRegistrationId;
            return this;
        }

        public Builder withUserId(Long userId) {
            this.userId = userId;
            return this;
        }

        public Builder withCustomerId(Long customerId) {
            this.customerId = customerId;
            return this;
        }

        public Builder withMessageType(Integer messageType) {
            this.messageType = messageType;
            return this;
        }

        public Builder withEntityId(Long entityId) {
            this.entityId = entityId;
            return this;
        }

        public Builder withEntityType(String entityType) {
            this.entityType = entityType;
            return this;
        }

        public Builder withEntityPage(String entityPage) {
            this.entityPage = entityPage;
            return this;
        }

        public SilentPushEntity build() {
            SilentPushEntity silentPushEntity = new SilentPushEntity();
            silentPushEntity.setFcmRequest(fcmRequest);
            silentPushEntity.setFcmResponse(fcmResponse);
            silentPushEntity.setTimeOfSendingPush(timeOfSendingPush);
            silentPushEntity.setHttpStatusCode(httpStatusCode);
            silentPushEntity.setFcmRegistrationId(fcmRegistrationId);
            silentPushEntity.setUserId(userId);
            silentPushEntity.setCustomerId(customerId);
            silentPushEntity.setMessageType(messageType);
            silentPushEntity.setEntityId(entityId);
            silentPushEntity.setEntityType(entityType);
            silentPushEntity.setEntityPage(entityPage);
            return silentPushEntity;
        }
    }
}
