package com.digital.signage.dto;

import com.digital.signage.models.UserMessage;
import com.digital.signage.util.SnapshotType;

public class DeviceSnapshotDTO extends UserMessage {
    private String uniqueRequestId;
    private Long snapshotTime;
    private String snaphotUrl;
    private String snapshotThumbnailUrl;
    private boolean isError = false;
    //for send current snapshot
    private SnapshotType snapshotType;

    public boolean getIsError() {
        return this.isError;
    }

    public void setIsError(boolean isError) {
        this.isError = isError;
    }

    public String getSnaphotUrl() {
        return snaphotUrl;
    }

    public void setSnaphotUrl(String snaphotUrl) {
        this.snaphotUrl = snaphotUrl;
    }

    public String getSnapshotThumbnailUrl() {
        return snapshotThumbnailUrl;
    }

    public void setSnapshotThumbnailUrl(String snapshotThumbnailUrl) {
        this.snapshotThumbnailUrl = snapshotThumbnailUrl;
    }

    public Long getSnapshotTime() {
        return snapshotTime;
    }

    public void setSnapshotTime(Long snapshotTime) {
        this.snapshotTime = snapshotTime;
    }

    public String getUniqueRequestId() {
        return uniqueRequestId;
    }

    public void setUniqueRequestId(String uniqueRequestId) {
        this.uniqueRequestId = uniqueRequestId;
    }

    public void setSnapshotType(SnapshotType snapshotType) {
        this.snapshotType = snapshotType;
    }

    public SnapshotType getSnapshotType() {
        return snapshotType;
    }
}
