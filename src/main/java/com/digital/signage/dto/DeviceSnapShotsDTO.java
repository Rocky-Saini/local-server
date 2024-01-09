package com.digital.signage.dto;

import com.digital.signage.util.SnapshotType;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class DeviceSnapShotsDTO {
    private Date snapshotTime;
    @JsonProperty("snaphotUrl")
    private String snapshotUrl;
    private SnapshotType snapshotType;

    public SnapshotType getSnapshotType() {
        return snapshotType;
    }

    public void setSnapshotType(SnapshotType snapshotType) {
        this.snapshotType = snapshotType;
    }

    public String getSnapshotThumbnailUrl() {
        return snapshotThumbnailUrl;
    }

    public void setSnapshotThumbnailUrl(String snapshotThumbnailUrl) {
        this.snapshotThumbnailUrl = snapshotThumbnailUrl;
    }

    private String snapshotThumbnailUrl;

    public Date getSnapshotTime() {
        return snapshotTime;
    }

    public void setSnapshotTime(Date snapshotTime) {
        this.snapshotTime = snapshotTime;
    }

    public String getSnapshotUrl() {
        return snapshotUrl;
    }

    public void setSnapshotUrl(String snapshotUrl) {
        this.snapshotUrl = snapshotUrl;
    }
}
