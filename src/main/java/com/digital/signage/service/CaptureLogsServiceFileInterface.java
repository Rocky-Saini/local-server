package com.digital.signage.service;

import com.digital.signage.models.Device;
import com.digital.signage.models.Logs;
import com.digital.signage.models.SnapShot;
import com.digital.signage.models.SnapshotAsyncModel;
import com.digital.signage.util.DeviceOs;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

@Service
public interface CaptureLogsServiceFileInterface {

    String generateSnapshotFileDownloadUrl(SnapShot snapShot, Long customerId, String deviceName)
            throws IOException;
    void uploadFile(MultipartFile file, Logs captureLog, Long customerId, Device device)
            throws IOException;
    String generateThumbnailDownloadUrl(SnapShot snapShot, Long customerId) throws IOException;
    String generateLogFileDownloadUrl(
            Logs captureLog,
            Long customerId,
            String deviceName,
            DeviceOs deviceOs
    ) throws IOException;
    void saveSnapshotInTemporaryLocation(
            SnapshotAsyncModel snapshotAsyncModel,
            File file
    ) throws IOException;
    void uploadSnapshotToDesiredLocation(
            Path tempFilePath,
            List<SnapShot> listOfSnapShots,
            Long customerId,
            Long deviceId
    ) throws IOException;
    void downloadThumbnail(Long customerId, SnapShot snapShot, Boolean download,
                           HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws IOException, FileNotFoundException;
    void downloadSnapshotFile(String desiredFilename, SnapShot snapShot, Boolean download,
                              Long customerId, HttpServletRequest httpServletRequest,
                              HttpServletResponse httpServletResponse) throws IOException, FileNotFoundException;
    void downloadLogFile(Logs log, Boolean download, String desiredFileName, Long customerId,
                         HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws IOException, FileNotFoundException;
}
