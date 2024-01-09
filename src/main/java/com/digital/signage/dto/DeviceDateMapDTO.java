package com.digital.signage.dto;

import java.util.Date;

public class DeviceDateMapDTO {

    private Long deviceId;

    public DeviceDateMapDTO(Long deviceId, Date date) {
        this.deviceId = deviceId;
        this.date = date;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    private Date date;

    public Long getDeviceId() {
        return deviceId;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public int hashCode() {
        return ((int) this.date.getTime() + this.deviceId.intValue());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof DeviceDateMapDTO) {
            DeviceDateMapDTO deviceDateMapDTO = (DeviceDateMapDTO) obj;
            return this.deviceId.equals(deviceDateMapDTO.deviceId) && this.date.equals(
                    deviceDateMapDTO.date);
        }
        return false;
    }
}
