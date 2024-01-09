package com.digital.signage.dto;

import com.digital.signage.models.UserMessage;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class UserMessageResponseDTO {
    @JsonProperty("unreadCount")
    Integer unreadCount;

    @JsonProperty("notifications")
    List<UserMessage> userMessageList;

    public UserMessageResponseDTO(Integer unreadCount,
                                  List<UserMessage> userMessageList) {
        this.unreadCount = unreadCount;
        this.userMessageList = userMessageList;
    }

    public Integer getUnreadCount() {
        return unreadCount;
    }

    public void setUnreadCount(Integer unreadCount) {
        this.unreadCount = unreadCount;
    }

    public List<UserMessage> getUserMessageList() {
        return userMessageList;
    }

    public void setUserMessageList(List<UserMessage> userMessageList) {
        this.userMessageList = userMessageList;
    }
}
