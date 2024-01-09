package com.digital.signage.models;

import javax.persistence.*;

@Entity
@Table(name = "user_message_status")
public class UserMessageStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_message_id")
    private Long userMessageId;

    @Column(name = "web_registration_id")
    private String webRegistrationId;

    @Column(name = "status")
    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserMessageId() {
        return userMessageId;
    }

    public void setUserMessageId(Long userMessageId) {
        this.userMessageId = userMessageId;
    }

    public String getWebRegistrationId() {
        return webRegistrationId;
    }

    public void setWebRegistrationId(String webRegistrationId) {
        this.webRegistrationId = webRegistrationId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
