package com.digital.signage.models;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;

/**
 * @author -Ravi Kumar created on 1/23/2023 3:41 PM
 * @project - Digital Sign-edge
 */
@Entity
@Table(name = "notification_template")
public class NotificationTemplate implements EntityModel, Cloneable {
    private static final Logger logger = LoggerFactory.getLogger(NotificationTemplate.class);

    public interface KeysValuesForMap {
        String entity = "{{entity}}";
        String level = "{{level}}";
        String user = "{{user}}";
        String reason = "{{reason}}";
        String device = "{{device}}";
        String customer = "{{Customer Name}}";
        String receiver = "{{Receiver}}";
        String PLANOGRAM_START_DATE = "{{Plano start date}}";
        String PLANOGRAM_START_TIME = "{{Plano Start Time}}";
        String PLANOGRAM_NAME = "{{Planogram name}}";
        String deletedBy = "deletedBy";
        String USER_NAME = "{{user name}}";
        String MAKER_NAME = "{{Maker name}}";
        String ENTITY = "{{Entity}}";
        String ITEM = "{{Item}}";
        String RECEIVER = "{{Receiver}}";
        String DEVICE_NAME = "{{device name}}";
        String REPLACED_DEVICE_NAME = "{{Replaced device Name}}";

        String DEVICE_ID = "{{DeviceId}}";

        String PANEL_NAME = "{{Panel name}}";
        String LAYOUT_OR_LAYOUT_STRING_NAME = "{{Campaign/Campaign String name}}";
        String ACTION = "{{Action}}";
        String SUBMIT_ACTION = "submitted";
        String APPROVED_ACTION = "approved";
        String PlANOGRAM_AS_ENTITY = "Planogram";
        String CAMPAIGN_AS_ENTITY = "Campaign";
        String CAMPAIGN_STRING_AS_ENTITY = "Campaign string";
        String PLANOGRAM_NULL_START_DATE = "for {{Plano start date}}";

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "message_type")
    private Integer messageType;

    @Column(name = "subject")
    private String subject;

    @Column(name = "body")
    private String body;

    public Integer getMessageType() {
        return messageType;
    }

    public void setMessageType(Integer messageType) {
        this.messageType = messageType;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            logger.error("", e);
        }
        return null;
    }
}
