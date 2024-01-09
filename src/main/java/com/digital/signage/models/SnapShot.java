package com.digital.signage.models;

import com.digital.signage.util.SnapshotType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.nio.file.Path;
import java.util.Date;

@Entity(name = "snapshot")
public class SnapShot implements EntityModel {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "snapshot_id")
  private Long snapshotId;
  @Column(name = "device_id")
  private Long deviceId;
  @Column(name = "snapshot_file_name")
  private String snapshotFileName;
  @Column(name = "thumbnail_path")
  private String thumbnailPath;
  @Transient
  @JsonProperty("snapshotTime")
  private Long snapshotTimeForJsonButTransient;
  @Column(name = "snapshot_time")
  private Date snapshotTimeInDB;
  @JsonInclude(JsonInclude.Include.NON_NULL)
  @Column(name = "snapshot_type")
  @Enumerated(value = EnumType.STRING)
  private SnapshotType snapshotType;
  @JsonIgnore
  @Transient
  private Path tempFilePath;
  @JsonIgnore
  @Transient
  private Path tempThumbPath;
  @JsonIgnore
  private Boolean hasThumbnailGenerationFailed;

  public Path getTempThumbPath() {
    return tempThumbPath;
  }

  public void setTempThumbPath(Path tempThumbPath) {
    this.tempThumbPath = tempThumbPath;
  }

  public Path getTempFilePath() {
    return tempFilePath;
  }

  public void setTempFilePath(Path tempFilePath) {
    this.tempFilePath = tempFilePath;
  }

  public SnapshotType getSnapshotType() {
    return snapshotType;
  }

  public void setSnapshotType(SnapshotType snapShotType) {
    this.snapshotType = snapShotType;
  }

  public Long getSnapshotId() {
    return snapshotId;
  }

  public void setSnapshotId(Long snapshotId) {
    this.snapshotId = snapshotId;
  }

  public Long getDeviceId() {
    return deviceId;
  }

  public void setDeviceId(Long deviceId) {
    this.deviceId = deviceId;
  }

  public String getSnapshotFileName() {
    return this.snapshotFileName;
  }

  public void setSnapshotFileName(String snapshotFileName) {
    this.snapshotFileName = snapshotFileName;
  }

  public Long getSnapshotTimeForJsonButTransient() {
    return this.snapshotTimeForJsonButTransient;
  }

  public void setSnapshotTimeForJsonButTransient(Long snapshotTimeForJsonButTransient) {
    this.snapshotTimeForJsonButTransient = snapshotTimeForJsonButTransient;
  }

  public String getThumbnailPath() {
    return thumbnailPath;
  }

  public void setThumbnailPath(String thumbnailPath) {
    this.thumbnailPath = thumbnailPath;
  }

  public Date getSnapshotTimeInDB() {
    return snapshotTimeInDB;
  }

  public void setSnapshotTimeInDB(Date snapshotTimeInDB) {
    this.snapshotTimeInDB = snapshotTimeInDB;
  }

  public Boolean getHasThumbnailGenerationFailed() {
    return hasThumbnailGenerationFailed;
  }

  public void setHasThumbnailGenerationFailed(Boolean hasThumbnailGenerationFailed) {
    this.hasThumbnailGenerationFailed = hasThumbnailGenerationFailed;
  }

  @JsonIgnore
  @Transient
  public boolean hasThumbnailGenerationFailed() {
    return hasThumbnailGenerationFailed == null ? false : hasThumbnailGenerationFailed;
  }

  @Override
  public String toString() {
    return "SnapShot{" +
        "snapshotId=" + snapshotId +
        ", deviceId=" + deviceId +
        ", snapshotFileName='" + snapshotFileName + '\'' +
        ", thumbnailPath='" + thumbnailPath + '\'' +
        ", snapshotTimeForJsonButTransient=" + snapshotTimeForJsonButTransient +
        ", snapshotTimeInDB=" + snapshotTimeInDB +
        ", snapshotType=" + snapshotType +
        ", tempFilePath=" + tempFilePath +
        ", tempThumbPath=" + tempThumbPath +
        ", hasThumbnailGenerationFailed=" + hasThumbnailGenerationFailed +
        '}';
  }
}