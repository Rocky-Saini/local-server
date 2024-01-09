package com.digital.signage.util;

import com.digital.signage.models.NotificationTemplate;
import com.digital.signage.repository.NotificationTemplateRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author -Ravi Kumar created on 1/23/2023 3:42 PM
 * @project - Digital Sign-edge
 */
@Service
public class NotificationTemplateUtils {
    @Autowired
    private NotificationTemplateRepository repository;
    private Logger logger = LoggerFactory.getLogger(NotificationTemplateUtils.class);
    private static final int ARRAY_INDEX_SUBJECT = 0;
    private static final int ARRAY_INDEX_BODY = 1;

    public NotificationTemplate getNotificationTemplate(int messageType) {
        logger.debug("entering getNotificationTemplate with messageType = " + messageType);
        NotificationTemplate notificationTemplate = repository.findByMessageType(messageType);
        if (notificationTemplate == null) {
            logger.error(
                    "Notification Template Not found for the messageType = " + messageType);
            throw new IllegalStateException(
                    "Notification messageType " + messageType + " not found in DB");
        }
        return (NotificationTemplate) notificationTemplate.clone();
    }

    public void replaceNotificationStrings(NotificationTemplate notificationTemplate,
                                           Map<String, String> values) {
        if (values != null) {
            if (notificationTemplate.getSubject() != null) {
                notificationTemplate.setSubject(simpleReplace(values, notificationTemplate.getSubject()));
            }
            if (notificationTemplate.getBody() != null) {
                notificationTemplate.setBody(simpleReplace(values, notificationTemplate.getBody()));
            }
        }
    }

    public String[] applyValuesNotificationTemplate(NotificationTemplate notificationTemplate,
                                                    Map<String, String> values) {
        String[] strings = new String[2];
        strings[ARRAY_INDEX_SUBJECT] = notificationTemplate.getSubject();
        strings[ARRAY_INDEX_BODY] = simpleReplace(values, notificationTemplate.getBody());
        return strings;
    }

    private String simpleReplace(Map<String, String> values, String body) {
        logger.debug("simpleReplace :: values = " + values + ", body = " + body);
        if (values != null) {
            logger.debug("values not null");
            for (Map.Entry<String, String> entry : values.entrySet()) {
                logger.debug("for loop:: key = " + entry.getKey() + ", value = " + entry.getValue());
                body = body.replace(entry.getKey(), entry.getValue());
            }
        }
        logger.debug("simpleReplace ; return " + body);
        return body;
    }

    public NotificationTemplate createTemplateClone(NotificationTemplate notificationTemplate) {
        if (notificationTemplate == null) {
            logger.error(
                    "Notification Template Not found is null.");
            throw new IllegalStateException(
                    "Notification Template Not found is null.");
        }
        return (NotificationTemplate) notificationTemplate.clone();
    }
}

