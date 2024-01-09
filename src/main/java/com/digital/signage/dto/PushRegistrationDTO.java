package com.digital.signage.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PushRegistrationDTO {

    @JsonProperty("pushRegsitrationId")
    private String pushRegId;

    public String getPushRegId() {
        return pushRegId;
    }

    public void setPushRegId(String pushRegId) {
        this.pushRegId = pushRegId;
    }
}
