package com.digital.signage.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author -Ravi Kumar created on 1/29/2023 8:20 PM
 * @project - Digital Sign-edge
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IsDeviceOnboardedDTO {
    @JsonProperty("isDeviceOnboarded")
    private Boolean isDeviceOnboarded;
    @JsonProperty("deviceId")
    private Long deviceId;
    @JsonProperty("unregisteredPostNeedsToBeCalled")
    private Boolean unregisteredPostNeedsToBeCalled;

    public IsDeviceOnboardedDTO() {
    }

    public IsDeviceOnboardedDTO(
            Boolean isDeviceOnboarded,
            Long deviceId,
            Boolean unregisteredPostNeedsToBeCalled
    ) {
        this.isDeviceOnboarded = isDeviceOnboarded;
        this.deviceId = deviceId;
        this.unregisteredPostNeedsToBeCalled = unregisteredPostNeedsToBeCalled;
    }

    public Boolean getIsDeviceOnboarded() {
        return isDeviceOnboarded;
    }

    public void setIsDeviceOnboarded(Boolean deviceOnboarded) {
        isDeviceOnboarded = deviceOnboarded;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public Boolean getUnregisteredPostNeedsToBeCalled() {
        return unregisteredPostNeedsToBeCalled;
    }

    public void setUnregisteredPostNeedsToBeCalled(Boolean unregisteredPostNeedsToBeCalled) {
        this.unregisteredPostNeedsToBeCalled = unregisteredPostNeedsToBeCalled;
    }
}

