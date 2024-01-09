package com.digital.signage.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.digital.signage.models.UserMessage;
import com.digital.signage.models.WebPush;

/**
 * {
 * "to" : "fo9xmPkfz30:APA91bEhRTnqhqvNGwMc9S7OstJ63Q4Ec9VFvnqw8S8KOcm_J1SqqWme9IbhpNtNBVb0XLmJAQlVVL3J3BL1xCTfk49n3K7_2XwAM2JweiluzXh3oKP0UnhDUea4ZO3i0HeoYLCGzf27",
 * "notification": {
 * "title": "Background Message Title",
 * "body": "Background message body"
 * },
 * "data": {
 * "messageId": 21321,
 * "messageType": 14,
 * "messageTitle": "subject",
 * "messageString": "message",
 * "customerId": 144,
 * "entityType": "layout",
 * "entityId": 12,
 * "entityPage": null,
 * "sentOn": 1527155499000,
 * "read": true
 * }
 * "webpush":{
 * "headers":{
 * "TTL":"4500"
 * }
 * }
 * }
 */
public class FcmPushRequestModel {
    @JsonProperty("to")
    private String toRegistrationId;
    private Notification notification;
    @JsonIgnore
    private UserMessage userMessage;
    private WebPush webPush;
    private Priority priority;
    private Data data;

    public Data getData() {
        return data;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public WebPush getWebPush() {
        return webPush;
    }

    public void setWebPush(WebPush webPush) {
        this.webPush = webPush;
    }

    public String getToRegistrationId() {
        return toRegistrationId;
    }

    public void setToRegistrationId(String toRegistrationId) {
        this.toRegistrationId = toRegistrationId;
    }

    public Notification getNotification() {
        return notification;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }

    @JsonIgnore
    public UserMessage getUserMessage() {
        return userMessage;
    }

    @JsonIgnore
    public void setUserMessage(UserMessage userMessage, Data data) {
        this.userMessage = userMessage;
        this.data = data;
    }

    public enum Priority {
        normal, high;
    }

    public static class Data {
        public final String result;

        public Data(String result) {
            this.result = result;
        }
    }

    @Override
    public String toString() {
        return "FcmPushRequestModel{" +
                "toRegistrationId='" + toRegistrationId + '\'' +
                ", notification=" + notification +
                ", userMessage=" + userMessage +
                ", webPush=" + webPush +
                '}';
    }

    public static final class Builder {
        private String toRegistrationId;
        private Notification notification;
        private UserMessage userMessage;
        private WebPush webPush;
        private Priority priority;

        private Builder() {
        }

        public static Builder aFcmPushRequestModel() {
            return new Builder();
        }

        public Builder withToRegistrationId(String toRegistrationId) {
            this.toRegistrationId = toRegistrationId;
            return this;
        }

        public Builder withNotification(Notification notification) {
            this.notification = notification;
            return this;
        }

        public Builder withUserMessage(UserMessage userMessage) {
            this.userMessage = userMessage;
            return this;
        }

        public Builder withWebPush(WebPush webPush) {
            this.webPush = webPush;
            return this;
        }

        public Builder withPriority(Priority priority) {
            this.priority = priority;
            return this;
        }

        public FcmPushRequestModel build(ObjectMapper objectMapper) throws JsonProcessingException {
            FcmPushRequestModel fcmPushRequestModel = new FcmPushRequestModel();
            fcmPushRequestModel.setToRegistrationId(toRegistrationId);
            fcmPushRequestModel.setNotification(notification);
            fcmPushRequestModel.setUserMessage(userMessage,
                    new Data(objectMapper.writeValueAsString(userMessage)));
            fcmPushRequestModel.setWebPush(webPush);
            fcmPushRequestModel.setPriority(priority);
            return fcmPushRequestModel;
        }
    }
}
