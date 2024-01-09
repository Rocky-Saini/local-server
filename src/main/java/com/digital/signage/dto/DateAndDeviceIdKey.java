package com.digital.signage.dto;

import java.util.Date;

public class DateAndDeviceIdKey {
  private Date consumedDate;
  private long deviceId;

  public DateAndDeviceIdKey(Date consumedDate, Long deviceId) {
    this.consumedDate = consumedDate;
    this.deviceId = deviceId;
  }

  public Date getConsumedDate() {
    return consumedDate;
  }

  public void setConsumedDate(Date consumedDate) {
    this.consumedDate = consumedDate;
  }

  public long getDeviceId() {
    return deviceId;
  }

  public void setDeviceId(long deviceId) {
    this.deviceId = deviceId;
  }

  @Override
  public int hashCode() {
    return consumedDate.hashCode() + Math.toIntExact(deviceId);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof DateAndDeviceIdKey) {
      DateAndDeviceIdKey other = (DateAndDeviceIdKey) obj;
      return consumedDate.equals(other.consumedDate) && deviceId == other.deviceId;
    }
    return false;
  }
}