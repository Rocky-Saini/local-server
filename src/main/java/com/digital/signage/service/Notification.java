package com.digital.signage.service;

import com.digital.signage.context.TenantContext;
import com.digital.signage.models.NotificationTemplate;
import com.digital.signage.models.UserMessage;
import com.digital.signage.repository.UserMessageRepository;
import com.digital.signage.util.EntityTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Component
public class Notification {

    @Autowired
    private UserMessageRepository userMessageRepository;


    public void sendNotification(Integer messageType, NotificationTemplate notificationTemplate, Long userId, EntityTypeEnum entityType){
        UserMessage userMessage = new UserMessage();

        userMessage.setSentOn(Timestamp.valueOf(LocalDateTime.now()));
        userMessage.setUserId(userId);

        userMessage.setMessageType(messageType);
        userMessage.setState(false);

        userMessage.setMessageTitle(notificationTemplate.getSubject());
        userMessage.setMessageString(notificationTemplate.getBody());
        userMessage.setEntityType(entityType);

        //userMessage.setMessageTitle("Hello");
        userMessageRepository.save(userMessage);

    }
}