package com.digital.signage.service.impl;

import com.digital.signage.constants.UrlPaths;
import com.digital.signage.dto.ServerLaunchConfig;
import com.digital.signage.models.Device;
import com.digital.signage.models.Logs;
import com.digital.signage.models.SnapShot;
import com.digital.signage.models.SnapshotAsyncModel;
import com.digital.signage.report.S3Service;
import com.digital.signage.repository.CaptureLogRepository;
import com.digital.signage.repository.SnapShotRepository;
import com.digital.signage.service.CaptureLogsServiceFileInterface;
import com.digital.signage.service.FileDownloadService;
import com.digital.signage.util.DeviceOs;
import com.digital.signage.util.UrlUtilsKt;
import digital.signage.exceptions.NotValidInCaseOfS3Exception;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static com.digital.signage.util.ApplicationConstants.*;

@Service
public class CaptureLogsServiceFileInterfaceForAWS implements CaptureLogsServiceFileInterface {

    public static final Logger logger = LoggerFactory.getLogger(
            CaptureLogsServiceFileInterfaceForAWS.class);
    @Autowired
    private FileDownloadService fileDownloadService;

    private static final String S3_INTERMEDIATE_DIR_KEY = "{{intermediateDirName}}";
    private static final String S3_ACTUAL_FILE_KEY = "{{actualFileName}}";
    private static final String S3_DEVICE_ID_KEY = "{{device-id}}";
    private static final String S3_CUSTOMER_ID_KEY = "{{customer-id}}";
    private static final String TEMP_LOCATION_PREFIX = "temp-snapshot";
    private static final String S3_SNAP_SHOT_TEMP_DIRECTORY_KEY = String.join("/",
            TEMP_LOCATION_PREFIX, S3_CUSTOMER_ID_KEY, S3_DEVICE_ID_KEY, S3_INTERMEDIATE_DIR_KEY);

    private static final String S3_SNAP_SHOT_TEMP_ACTUAL_FILE_KEY = String.join("/",
            S3_SNAP_SHOT_TEMP_DIRECTORY_KEY, S3_ACTUAL_FILE_KEY);

    @Autowired
    private ServerLaunchConfig serverLaunchConfig;
    @Autowired
    private SnapShotRepository snapShotRepository;
    @Autowired
    private CaptureLogRepository captureLogRepository;
    @Value("${root.storage.dir}")
    private String rootStorageDir;
    @Autowired
    private S3Service s3Service;

    @Override
    public String generateSnapshotFileDownloadUrl(SnapShot snapShot, Long customerId, String deviceName) throws IOException {
        return UrlUtilsKt.removeSlashFromEndOfUrlIfRequired(
                serverLaunchConfig.getServerBaseUrlForAngularDownloadLinks())
                + UrlPaths.PATH_SNAPSHOT_FILE
                /*+ fileDownloadService.encryptCustomerId(customerId)*/
                + "/"
                + snapShot.getSnapshotId()
                + "?download=true";
    }

    @Override
    public String generateThumbnailDownloadUrl(SnapShot snapShot, Long customerId) throws IOException {
        logger.debug("fetching Thumbnail in shared drive");
        logger.debug(serverLaunchConfig.getServerBaseUrlForAngularDownloadLinks()
                + UrlPaths.PATH_THUMBNAIL_FILE);
        logger.debug("serverBaseUrl = {}",
                serverLaunchConfig.getServerBaseUrlForAngularDownloadLinks());
        return UrlUtilsKt.removeSlashFromEndOfUrlIfRequired(
                serverLaunchConfig.getServerBaseUrlForAngularDownloadLinks())
                + UrlPaths.PATH_THUMBNAIL_FILE
                /*+ fileDownloadService.encryptCustomerId(customerId)*/
                + "/"
                + snapShot.getSnapshotId()
                + "?download=false";
    }

    @Override
    @Transactional
    public void uploadFile(MultipartFile file, Logs captureLog, Long customerId, Device device)
            throws IOException {
        long startTime = System.currentTimeMillis();

        Path checkDeviceDirectory = getDeviceLogsPath(captureLog.getDeviceId(), customerId);
        logger.debug("checkDeviceDirectory = {}", checkDeviceDirectory);
        Path checkStartDirectory = Paths.get(checkDeviceDirectory.toString());
        logger.debug("checkStartDirectory = {}", checkDeviceDirectory);

        Path pathForFileName =
                Paths.get(
                        UUID.randomUUID() + "." + device.getDeviceOs().getLogFileExtension());
        logger.debug("pathForFileName = {}", pathForFileName);
        Path uploadPath = Paths.get(checkDeviceDirectory.toString(), pathForFileName.toString());
        String forS3 = (ROOT_DEVICE_LOGS_DIRECTORY+"/"+
                String.valueOf(customerId)+"/"+ String.valueOf(captureLog.getDeviceId())+"/"+pathForFileName.toString());
        logger.debug("uploadPath = {}", uploadPath);
        File files = new File(checkStartDirectory.toString());
        boolean result = files.mkdirs();
        logger.debug("files.mkdirs() = {}", result);
        if (file.isEmpty()) throw new IOException("file is empty.");
        try (InputStream is = file.getInputStream();
             OutputStream os = new FileOutputStream(uploadPath.toString())) {

            File convFile = new File(file.getOriginalFilename());
            convFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(convFile);
            fos.write(file.getBytes());
            fos.close();
            s3Service.uploadFile(forS3.toString(),convFile);
            logger.debug("Uploading files");
            IOUtils.copy(is, os);
            logger.debug("File {} Uploaded Successfully on path", file.getName());
            logger.debug(
                    "file uploaded path {}, consumed time = {} ms", pathForFileName,
                    (System.currentTimeMillis() - startTime));

            captureLog.setLogFileName(pathForFileName.toString());
            captureLog.setLogsUpdateStatus(true);
            captureLogRepository.save(captureLog);
            logger.debug("File successfully saved in DB");
        } catch (IOException e) {
            captureLog.setLogsUpdateStatus(false);
            captureLogRepository.save(captureLog);
            throw e;
        }
    }
    @Override
    public String generateLogFileDownloadUrl(
            Logs captureLog,
            Long customerId,
            String deviceName,
            DeviceOs deviceOs
    ) throws IOException {
        logger.debug("fetching Thumbnail in shared drive");
        logger.debug(serverLaunchConfig.getServerBaseUrlForAngularDownloadLinks()
                + UrlPaths.PATH_THUMBNAIL_FILE);
        logger.debug("serverBaseUrl = {}",
                serverLaunchConfig.getServerBaseUrlForAngularDownloadLinks());
        logger.debug("captureLog.getLogId() {}", captureLog.getLogId());
        return UrlUtilsKt.removeSlashFromEndOfUrlIfRequired(
                serverLaunchConfig.getServerBaseUrlForAngularDownloadLinks())
                + UrlPaths.PATH_LOG_FILE
                + customerId
                + "/"
                + captureLog.getLogId()
                + "?download=true";
    }

    @Override
    public void saveSnapshotInTemporaryLocation(
            SnapshotAsyncModel snapshotAsyncModel,
            File file
    ) throws IOException {
        String key = getSnapshotTemporaryActualFileKey(
                generateTemporarySnapshotTarOrZipFileNameWithExtension(snapshotAsyncModel),
                getSnapshotTemporaryDirectoryKey(
                        snapshotAsyncModel.getIntermediateDirName(),
                        snapshotAsyncModel.getDeviceId(),
                        snapshotAsyncModel.getCustomerId()
                )
        );

        try {
            s3Service.uploadFile(key, file);
        } catch (Exception e) {
            throw e;
        } finally {
            FileUtils.deleteQuietly(file);
        }
    }
    private String getSnapshotTemporaryDirectoryKey(
            String intermediateDirName,
            Long deviceId,
            Long customerId
    ) {
        return S3_SNAP_SHOT_TEMP_DIRECTORY_KEY
                .replace(S3_CUSTOMER_ID_KEY, customerId.toString())
                .replace(S3_DEVICE_ID_KEY, deviceId.toString())
                .replace(S3_INTERMEDIATE_DIR_KEY, intermediateDirName);
    }
    private String generateTemporarySnapshotTarOrZipFileNameWithExtension(
            SnapshotAsyncModel snapshotAsyncModel
    ) {
        return snapshotAsyncModel.getIntermediateDirName() + "." + snapshotAsyncModel.getFileType()
                .getFileExtension();
    }

    private String getSnapshotTemporaryActualFileKey(
            String actualFileName,
            String tempDirectoryPath
    ) {
        return S3_SNAP_SHOT_TEMP_ACTUAL_FILE_KEY
                .replace(S3_SNAP_SHOT_TEMP_DIRECTORY_KEY, tempDirectoryPath)
                .replace(S3_ACTUAL_FILE_KEY, actualFileName);
    }


    public Path getDeviceLogsPath(Long deviceId, Long customerId) {
        return Paths.get(rootStorageDir, ROOT_DEVICE_LOGS_DIRECTORY,
                String.valueOf(customerId), String.valueOf(deviceId));
    }

    @Override
    public void uploadSnapshotToDesiredLocation(
            Path tempFilePath,
            List<SnapShot> listOfSnapShots,
            Long customerId,
            Long deviceId
    ) throws IOException {
        // This is Async so tenantContext will not work, Fetch customer Id from device Model
        if (!listOfSnapShots.isEmpty()) {
            Objects.requireNonNull(listOfSnapShots.get(0).getDeviceId(),
                    "listOfSnapShots.get(0).getDeviceId() == null");

            // it is IMPORTANT to save the snapshots in DB because otherwise the snapshotId would be NULL
            // and S3 key would fail
            snapShotRepository.saveAll(listOfSnapShots);

            for (SnapShot snapShot : listOfSnapShots) {
                // save to db first because we need the snapShot.getSnapshotId() in the s3 key

                String snapshotFileKey = getSnapshotFileS3Key(customerId, snapShot.getDeviceId(),
                        snapShot.getSnapshotId());

                try {
                    Path uploadedSnapShotFilePath =
                            Paths.get(tempFilePath.toString(), snapShot.getSnapshotFileName());
                    s3Service.uploadFile(snapshotFileKey, uploadedSnapShotFilePath);

                    if (snapShot.getThumbnailPath() != null) {
                        // since thumbnail creation was a success we have a path
                        Path uploadedThumbNailFilePath = Paths.get(tempFilePath.toString(),
                                snapShot.getThumbnailPath());
                        String snapshotThumbKey = getSnapshotThumbS3Key(customerId, snapShot.getDeviceId(),
                                snapShot.getSnapshotId());
                        snapShot.setThumbnailPath(snapshotThumbKey);
                        s3Service.uploadFile(snapshotThumbKey, uploadedThumbNailFilePath);
                    } else {
                        // thumbnail creation had failed so don't do anything
                    }
                } catch (Exception e) {
                    throw new IOException(e);
                }
            }

            snapShotRepository.saveAll(listOfSnapShots);
        }
        // else do nothing
    }

    @Override
    public void downloadThumbnail(Long customerId, SnapShot snapShot, Boolean download,
                                  HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws IOException, FileNotFoundException {
        throw new NotValidInCaseOfS3Exception();
    }

    @Override
    public void downloadSnapshotFile(String desiredFilename, SnapShot snapShot, Boolean download,
                                     Long customerId, HttpServletRequest httpServletRequest,
                                     HttpServletResponse httpServletResponse) throws IOException, FileNotFoundException {
        throw new NotValidInCaseOfS3Exception();
    }

    public static String getSnapshotThumbS3Key(long customerId, long deviceId, long snapshotFileId) {
        logger.debug("Getting snapshots Thumb file from S3 server");
        logger.debug("customerId = {}, deviceId = {}, snapshotFileId = {}", customerId, deviceId,
                snapshotFileId);

        return S3_SNAPSHOT_THUMB_KEY.replace(REPLACE_CUSTOMER_ID, String.valueOf(customerId))
                .replace(REPLACE_DEVICE_ID, String.valueOf(deviceId))
                .replace(REPLACE_SNAPSHOT_FILE_ID, String.valueOf(snapshotFileId));
    }
    public static String getSnapshotFileS3Key(long customerId, long deviceId, long snapshotFileId) {
        logger.debug("Getting snapshots file key");
        logger.debug("customerId = {}, deviceId = {}, snapshotFileId = {}", customerId, deviceId,
                snapshotFileId);
        final String key = S3_SNAPSHOT_KEY.replace(REPLACE_CUSTOMER_ID, String.valueOf(customerId))
                .replace(REPLACE_DEVICE_ID, String.valueOf(deviceId))
                .replace(REPLACE_SNAPSHOT_FILE_ID, String.valueOf(snapshotFileId));
        logger.debug("device Key = {} => {}", S3_SNAPSHOT_KEY, key);
        return key;
    }
    @Override
    public void downloadLogFile(Logs log, Boolean download, String desiredFileName, Long customerId,
                                HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws IOException, FileNotFoundException {
        throw new NotValidInCaseOfS3Exception();
    }
}
