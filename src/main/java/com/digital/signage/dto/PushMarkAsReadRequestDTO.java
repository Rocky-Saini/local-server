package com.digital.signage.dto;

public class PushMarkAsReadRequestDTO {
    private Long messageId;
    private Boolean markAllAsRead;

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public Boolean getMarkAllAsRead() {
        return markAllAsRead;
    }

    public void setMarkAllAsRead(Boolean markAllAsRead) {
        this.markAllAsRead = markAllAsRead;
    }
}
