package com.digital.signage.models;

import com.digital.signage.util.FileType;
import com.digital.signage.util.SnapshotAsyncProgress;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = SnapshotAsyncModel.TABLE_NAME)
public class SnapshotAsyncModel {
    public static final String TABLE_NAME = "snapshot_async_data_table";
    public static final String COLUMN_FILE_TYPE = "file_type";
    public static final String COLUMN_SNAPSHOT_ASYNC_PROGRESS = "snapshot_async_progress";
    public static final String COLUMN_SERVER_ID = "server_unique_identification";
    public static final String COLUMN_LAST_SERVER_ACCESS_TIME = "last_sync_time";
    public static final String COLUMN_LIST_OF_SNAPSHOTS_DTO_AS_JSON = "list_of_snapshots_dtos_json";
    public static final String COLUMN_CUSTOMER_ID = "customer_id";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Deprecated
    @Column(name = COLUMN_SERVER_ID)
    private String serverUniqueIdentification;

    @Column(name = COLUMN_LAST_SERVER_ACCESS_TIME)
    private Date lastSyncTime;

    @Column(name = COLUMN_SNAPSHOT_ASYNC_PROGRESS)
    private SnapshotAsyncProgress snapshotAsyncProgress;

    @Column(name = "snapshot_zip_or_tar_file_path")
    private String snapshotZipOrTarFilePath;

    @Column(name = COLUMN_LIST_OF_SNAPSHOTS_DTO_AS_JSON)
    private String listOfSnapshotsDTOAsJson;

    @Column(name = "intermediate_dir_name")
    private String intermediateDirName;

    @Deprecated
    @Column(name = "temp_location")
    private String tempLocation; // this is a directory not a file eg: /var/opt/panasonic/snapshots/temp/X0EVA

    @Deprecated
    @Column(name = "destination_path")
    private String destinationPath;

    @Column(name = "device_id")
    private Long deviceId;

    @Column(name = COLUMN_FILE_TYPE)
    private FileType fileType;

    @Column(name = COLUMN_CUSTOMER_ID)
    private Long customerId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Deprecated
    public String getSnapshotZipOrTarFilePath() {
        return snapshotZipOrTarFilePath;
    }

    @Deprecated
    public void setSnapshotZipOrTarFilePath(String snapshotZipOrTarFilePath) {
        this.snapshotZipOrTarFilePath = snapshotZipOrTarFilePath;
    }

    public String getListOfSnapshotsDTOAsJson() {
        return listOfSnapshotsDTOAsJson;
    }

    public void setListOfSnapshotsDTOAsJson(String listOfSnapshotsDTOAsJson) {
        this.listOfSnapshotsDTOAsJson = listOfSnapshotsDTOAsJson;
    }

    public String getIntermediateDirName() {
        return intermediateDirName;
    }

    public void setIntermediateDirName(String intermediateDirName) {
        this.intermediateDirName = intermediateDirName;
    }

    @Deprecated
    public String getTempLocation() {
        return tempLocation;
    }

    @Deprecated
    public void setTempLocation(String tempLocation) {
        this.tempLocation = tempLocation;
    }

    @Deprecated
    public String getDestinationPath() {
        return destinationPath;
    }

    @Deprecated
    public void setDestinationPath(String destinationPath) {
        this.destinationPath = destinationPath;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    @Deprecated
    public String getServerUniqueIdentification() {
        return serverUniqueIdentification;
    }

    @Deprecated
    public void setServerUniqueIdentification(String serverUniqueIdentification) {
        this.serverUniqueIdentification = serverUniqueIdentification;
    }

    public Date getLastSyncTime() {
        return lastSyncTime;
    }

    public void setLastSyncTime(Date lastSyncTime) {
        this.lastSyncTime = lastSyncTime;
    }

    public SnapshotAsyncProgress getSnapshotAsyncProgress() {
        return snapshotAsyncProgress;
    }

    public void setSnapshotAsyncProgress(SnapshotAsyncProgress snapshotAsyncProgress) {
        this.snapshotAsyncProgress = snapshotAsyncProgress;
    }

    public FileType getFileType() {
        return fileType;
    }

    public void setFileType(FileType fileType) {
        this.fileType = fileType;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public boolean isNewCodeBasedEntry() {
        return customerId != null;
    }

    public static final class Builder {
        private Long id;
        @Deprecated
        private String serverUniqueIdentification;
        private Date lastSyncTime;
        private SnapshotAsyncProgress snapshotAsyncProgress;
        @Deprecated
        private String snapshotZipOrTarFilePath;
        private String listOfSnapshotsDTOAsJson;
        private String intermediateDirName;
        @Deprecated
        private String tempLocation; // this is a directory not a file eg: /var/opt/panasonic/snapshots/temp/X0EVA
        @Deprecated
        private String destinationPath;
        private Long deviceId;
        private FileType fileType;
        private Long customerId;

        private Builder() {
        }

        public static Builder aBuilder() {
            return new Builder();
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        @Deprecated
        public Builder serverUniqueIdentification(
                String serverUniqueIdentification) {
            this.serverUniqueIdentification = serverUniqueIdentification;
            return this;
        }

        public Builder lastSyncTime(Date lastSyncTime) {
            this.lastSyncTime = lastSyncTime;
            return this;
        }

        public Builder snapshotAsyncProgress(
                SnapshotAsyncProgress snapshotAsyncProgress) {
            this.snapshotAsyncProgress = snapshotAsyncProgress;
            return this;
        }

        @Deprecated
        public Builder snapshotZipOrTarFilePath(String snapshotZipOrTarFilePath) {
            this.snapshotZipOrTarFilePath = snapshotZipOrTarFilePath;
            return this;
        }

        public Builder listOfSnapshotsDTOAsJson(String listOfSnapshotsDTOAsJson) {
            this.listOfSnapshotsDTOAsJson = listOfSnapshotsDTOAsJson;
            return this;
        }

        public Builder intermediateDirName(String intermediateDirName) {
            this.intermediateDirName = intermediateDirName;
            return this;
        }

        @Deprecated
        public Builder tempLocation(String tempLocation) {
            this.tempLocation = tempLocation;
            return this;
        }

        @Deprecated
        public Builder destinationPath(String destinationPath) {
            this.destinationPath = destinationPath;
            return this;
        }

        public Builder deviceId(Long deviceId) {
            this.deviceId = deviceId;
            return this;
        }

        public Builder fileType(FileType fileType) {
            this.fileType = fileType;
            return this;
        }

        public Builder customerId(Long customerId) {
            this.customerId = customerId;
            return this;
        }

        public SnapshotAsyncModel build() {
            SnapshotAsyncModel snapshotAsyncModel = new SnapshotAsyncModel();
            snapshotAsyncModel.setId(id);
            snapshotAsyncModel.setServerUniqueIdentification(serverUniqueIdentification);
            snapshotAsyncModel.setLastSyncTime(lastSyncTime);
            snapshotAsyncModel.setSnapshotAsyncProgress(snapshotAsyncProgress);
            snapshotAsyncModel.setSnapshotZipOrTarFilePath(snapshotZipOrTarFilePath);
            snapshotAsyncModel.setListOfSnapshotsDTOAsJson(listOfSnapshotsDTOAsJson);
            snapshotAsyncModel.setIntermediateDirName(intermediateDirName);
            snapshotAsyncModel.setTempLocation(tempLocation);
            snapshotAsyncModel.setDestinationPath(destinationPath);
            snapshotAsyncModel.setDeviceId(deviceId);
            snapshotAsyncModel.setFileType(fileType);
            snapshotAsyncModel.setCustomerId(customerId);
            return snapshotAsyncModel;
        }
    }
}

