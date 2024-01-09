package com.digital.signage.models;

import com.digital.signage.util.EntityTypeEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user_message")
public class UserMessage {

    public static final String JSON_KEY_MESSAGE_ID = "messageId";
    public static final String JSON_KEY_ENTITY_ID = "entityId";
    public static final String JSON_KEY_CUSTOMER_ID = "customerId";
    public static final String JSON_KEY_ENTITY_TYPE = "entityType";
    public static final String JSON_KEY_ENTITY_PAGE = "entityPage";
    public static final String JSON_KEY_MESSAGE_TYPE = "messageType";
    public static final String JSON_KEY_MESSAGE_TITLE = "messageTitle";
    public static final String JSON_KEY_MESSAGE_STRING = "messageString";
    public static final String JSON_KEY_SENT_ON = "sentOn";
    public static final String JSON_KEY_USER_ID = "userId";


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "message_id")
    private Long messageId;

    @JsonProperty("read")
    @Column(name = "state")
    private boolean state;

    @Column(name = "entity_id")
    @JsonProperty(JSON_KEY_ENTITY_ID)
    private Long entityId;

    @Column(name = "customer_id")
    @JsonProperty(JSON_KEY_CUSTOMER_ID)
    private Long customerId;

    @Enumerated(EnumType.STRING)
    @Column(name = "entity_type")
    @JsonProperty(JSON_KEY_ENTITY_TYPE)
    private EntityTypeEnum entityType;

    @Column(name = "entity_page")
    @JsonProperty(JSON_KEY_ENTITY_PAGE)
    private String entityPage;

    @Column(name = "message_type")
    @JsonProperty(JSON_KEY_MESSAGE_TYPE)
    private Integer messageType;

    @Column(name = "message_title")
    @JsonProperty(JSON_KEY_MESSAGE_TITLE)
    private String messageTitle;

    @Column(name = "message_string")
    @JsonProperty(JSON_KEY_MESSAGE_STRING)
    private String messageString;

    @Column(name = "sent_on")
    @JsonProperty(JSON_KEY_SENT_ON)
    private Date sentOn;
    @JsonProperty(JSON_KEY_USER_ID)
    @Column(name = "user_id")
    private Long userId;

    @JsonIgnore
    @Transient
    private String reason;

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public EntityTypeEnum getEntityType() {
        return entityType;
    }

    public void setEntityType(EntityTypeEnum entityType) {
        this.entityType = entityType;
    }

    public String getEntityPage() {
        return entityPage;
    }

    public void setEntityPage(String entityPage) {
        this.entityPage = entityPage;
    }

    public Integer getMessageType() {
        return messageType;
    }

    public void setMessageType(Integer messageType) {
        this.messageType = messageType;
    }

    public String getMessageTitle() {
        return messageTitle;
    }

    public void setMessageTitle(String messageTitle) {
        this.messageTitle = messageTitle;
    }

    public String getMessageString() {
        return messageString;
    }

    public void setMessageString(String messageString) {
        this.messageString = messageString;
    }

    public Date getSentOn() {
        return sentOn;
    }

    public void setSentOn(Date sentOn) {
        this.sentOn = sentOn;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getEntityTypeString() {
        return (entityType == null)?null:entityType.name();
    }

    @Override
    public String toString() {
        return "UserMessage{" +
                "messageId=" + messageId +
                ", state=" + state +
                ", entityId=" + entityId +
                ", customerId=" + customerId +
                ", entityType=" + entityType +
                ", entityPage='" + entityPage + '\'' +
                ", messageType=" + messageType +
                ", messageTitle='" + messageTitle + '\'' +
                ", sentOn=" + sentOn +
                ", userId=" + userId +
                ", reason='" + reason + '\'' +
                '}';
    }
}
