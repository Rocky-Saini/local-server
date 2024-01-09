package com.digital.signage.models;

import com.digital.signage.util.PushId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class NotificationRequest implements EntityModel {

    private List<Long> deviceIds;
    @JsonProperty("pushIds")
    private List<Integer> pushIdIntegers;
    @JsonIgnore
    private List<PushId> pushIds;

    private Boolean instantAppUpgrade;

    public List<Integer> getPushIdIntegers() {
        return pushIdIntegers;
    }

    public void setPushIdIntegers(List<Integer> pushIdIntegers) {
        this.pushIdIntegers = pushIdIntegers;
    }

    public List<PushId> getPushIds() {
        if (pushIds != null) return pushIds;
        if (pushIdIntegers == null) return null;
        if (Objects.isNull(pushIds) && Objects.nonNull(pushIdIntegers)) {
            pushIds = pushIdIntegers.stream()
                    .map(PushId::valueOf)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        }
        return pushIds;
    }

    public void setPushIds(List<PushId> pushIds) {
        this.pushIds = pushIds;
    }

    public List<Long> getDeviceIds() {
        return deviceIds;
    }

    public void setDeviceIds(List<Long> deviceIds) {
        this.deviceIds = deviceIds;
    }

    public Boolean getInstantAppUpgrade() {
        return instantAppUpgrade;
    }

    public void setInstantAppUpgrade(Boolean instantAppUpgrade) {
        this.instantAppUpgrade = instantAppUpgrade;
    }
}
