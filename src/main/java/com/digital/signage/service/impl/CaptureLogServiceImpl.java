package com.digital.signage.service.impl;

import com.digital.signage.context.TenantContext;
import com.digital.signage.dto.*;
import com.digital.signage.models.*;
import com.digital.signage.report.S3Service;
import com.digital.signage.repository.*;
import com.digital.signage.service.*;
import com.digital.signage.util.*;
import com.fasterxml.jackson.core.JsonParseException;
import digital.signage.util.JsonUtilsKt;
import digital.signage.util.LogUtilKt;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static com.digital.signage.util.ApplicationConstants.*;


@Service
public class CaptureLogServiceImpl extends BaseServiceWithServerLaunchConfigImpl
        implements CaptureLogService {

    private static final Logger logger = LoggerFactory.getLogger(CaptureLogServiceImpl.class);

    @Autowired
    private DeviceRepository deviceRepository;
    public static final String DEVICE_SNAPSHOT_DIR = "LIVE-Snapshot";
    @Autowired
    private Push push;
    @Value("${root.storage.dir}")
    private String rootDir;
    @Autowired
    private DeviceSnapshotRepository deviceSnapshotRepository;
    @Autowired
    private SnapShotRepository snapShotRepository;
    @Autowired
    private FileDownloadService fileDownloadService;
    @Autowired
    private SnapshotAsyncRepository snapshotAsyncRepository;
    @Autowired
    private S3Service s3Service;
    @Autowired
    private CaptureLogsServiceFileInterface captureLogsServiceFileInterface;
    @Value("${spring.http.multipart.location}")
    private String tempWorkingDir;
    @Autowired
    private DefaultThumbnailService defaultThumbnailService;
    @Autowired
    private CaptureLogRepository captureLogRepository;
    private static final String EMPTY_BODY_ERROR_MSG = "RequestDataEmpty";


    @Override
    public Response<?> getSnapShots(Long deviceId, Long snapshotStartTime, Long snapshotEndTime) throws JsonParseException, ParseException, IOException {
        if (deviceId == null) {
            return new ValidationErrorResponse(
                    "InvalidParameter",
                    message.get(Message.ResponseMessages.INVALID_REQUEST_PARAMETER)
            );
        }

        Long customerId =/* TenantContext.getCustomerId()*/1L;
        assert customerId != null;

        if (snapshotStartTime != null
                && snapshotEndTime != null
                && snapshotStartTime.compareTo(snapshotEndTime) >= 0) {
            return Response.Builder.voidResponseBuilder()
                    .badRequest()
                    .code(ResponseCode.FAILURE)
                    .name("InvalidTime")
                    .message(message.get(Message.Logs.LOGS_TIME_MISMATCH_ERROR))
                    .build();
        }

        if (snapshotStartTime == null && snapshotEndTime != null) {
            snapshotStartTime = snapshotEndTime - TWENTY_FOUR_HRS_IN_MILLIS;
        } else if (snapshotStartTime != null && snapshotEndTime == null) {
            snapshotEndTime = snapshotStartTime + TWENTY_FOUR_HRS_IN_MILLIS;
        } else if (snapshotStartTime == null && snapshotEndTime == null) {
            snapshotEndTime = new Date().getTime();
            snapshotStartTime = snapshotEndTime - TWENTY_FOUR_HRS_IN_MILLIS;
        }

        Date startDate = new Date(snapshotStartTime);
        Date endDate = new Date(snapshotEndTime);

        List<SnapShot> itrGetSnapshots = snapShotRepository.getSnapShotsInRange(
                deviceId,
                startDate,
                endDate,
                customerId
        );

        List<DeviceSnapShotsDTO> snapShotsDTOList = new ArrayList<>();
        if (itrGetSnapshots.size() <= 0) {
            logger.error("No data found");
            return new Response.Builder<List<DeviceSnapShotsDTO>>()
                    .success()
                    .result(new ArrayList<>(0))
                    .code(ResponseCode.SUCCESS)
                    .name("DataNotFound")
                    .message(message.get(Message.Logs.LOGS_FILE_NOT_FOUND_ERROR))
                    .build();
        }

        Device device = deviceRepository.findByDeviceId(deviceId);
        if (device == null) {
            return Response.Builder.voidResponseBuilder()
                    .notFound()
                    .name("DeviceNotFound")
                    .message("Device Not Found")
                    .build();
        }

        for (SnapShot snapShot : itrGetSnapshots) {
            String snapshotDownloadUrl, snapshotThumbnailDownloadUrl = null;
            try {
                snapshotDownloadUrl = captureLogsServiceFileInterface.generateSnapshotFileDownloadUrl(
                        snapShot, customerId, device.getDeviceName());
                if (snapShot.hasThumbnailGenerationFailed()) {
                    snapshotThumbnailDownloadUrl = defaultThumbnailService.getDefaultThumbnailUrl(
                            DefaultThumbnailService.ThumbnailType.NO_THUMBNAIL);
                } else {
                    snapshotThumbnailDownloadUrl =
                            captureLogsServiceFileInterface.generateThumbnailDownloadUrl(
                                    snapShot,
                                    customerId
                            );
                }
            } catch (IOException e) {
                logger.error("", e);
                return Response.createInternalServerErrorResponseFromException(e);
            }

            DeviceSnapShotsDTO snapShotsDTO = new DeviceSnapShotsDTO();
            snapShotsDTO.setSnapshotType(snapShot.getSnapshotType());
            snapShotsDTO.setSnapshotTime(snapShot.getSnapshotTimeInDB());
            snapShotsDTO.setSnapshotUrl(snapshotDownloadUrl);
            snapShotsDTO.setSnapshotThumbnailUrl(snapshotThumbnailDownloadUrl);
            snapShotsDTOList.add(snapShotsDTO);
        }
        //According to SER-1741 sort snapShort in desc order by time.
        snapShotsDTOList.sort(
                Comparator.comparing(DeviceSnapShotsDTO::getSnapshotTime).reversed());

        return new Response.Builder<List<DeviceSnapShotsDTO>>()
                .success()
                .result(snapShotsDTOList)
                .code(ResponseCode.SUCCESS)
                .name("UrlFoundSuccess")
                .message(message.get(Message.SnapShot.SNAPSHOT_FILE_DOWNLOAD_SUCCESS))
                .build();
    }

    @Override
    @Transactional
    public Response<?> captureLogs(@Nullable MultipartFile file, @Nullable String str,
                                   HttpServletRequest request) {
        Long customerId = TenantContext.getCustomerId();
        Long deviceId = TenantContext.getDeviceId();

        // json cannot be null
        if (str == null || str.isEmpty()) {
            Response<Void> r = new Response<>(
                    null, null, "BadRequest", ResponseCode.FAILURE, "Json in values key is required"
            );
            r.setHttpStatusCode(ResponseCode.FAILURE); // Bad request
            return r;
        }

        // file cannot be null
        if (file == null || file.isEmpty()) {
            logger.error("empty files or null");
            Response<Void> response = new Response<>();
            response.setCode(ResponseCode.FAILURE);
            response.setName("FileNotSelected");
            response.setMessage(message.get(Message.Logs.LOGS_FILE_NOT_SELECTED_ERROR));
            response.setHttpStatusCode(ResponseCode.FAILURE);
            return response;
        }

        Logs captureLog = null;
        try {
            captureLog = objectMapper.readValue(str, Logs.class);
        } catch (IOException e) {
            logger.error("error", e);
            return Response.createInternalServerErrorResponseFromException(e);
        }

        if (captureLog.getLogFileStartTime() == null
                || captureLog.getLogFileEndTime() == null
                || captureLog.getDeviceId() == null) {
            // required fields are missing
            logger.error("some mandatory fields are missed");
            Response<Void> response = new Response<>();
            response.setCode(ResponseCode.FAILURE);
            response.setName("EmptyMandatoryFields");
            response.setMessage(message.get(Message.Logs.LOGS_FIELDS_EMPTY_ERROR));
            response.setHttpStatusCode(ResponseCode.FAILURE);
            return response;
        }

        // check if device is correct
        if (!captureLog.getDeviceId().equals(deviceId)) {
            Response<Void> response = new Response<>();
            response.setCode(ResponseCode.FAILURE);
            response.setName("DeviceIdMismatch");
            response.setMessage(
                    String.format("You are logged in with deviceId %1$d and json has deviceId %2$d", deviceId,
                            captureLog.getDeviceId()));
            response.setHttpStatusCode(ResponseCode.FAILURE);
            return response;
        }

        Device d = deviceRepository.getByDeviceIdAndCustomerId(captureLog.getDeviceId());

        if (d == null) {
            return new NotFoundResponse<>("DeviceCustomerMismatchError",
                    message.get(Message.DeviceConfig.DEVICECONFIG_DEVICE_CUSTOMER_MISMATCH_ERROR));
        }

        logger.debug("Uploading ...Files..");
        logger.debug("File name = {}", file.getName());
        logger.debug("Device Id = {}", captureLog.getDeviceId());
        logger.debug("Start time = {}", captureLog.getLogFileStartTime());
        logger.debug("End time = {}", captureLog.getLogFileEndTime());
        logger.debug("Successfully uploaded");
        //return validateLogsTime(captureLog);
        Response<Logs> response = validateLogsTime(captureLog);
        if (response.getResult() != null
                && response.getCode().equals(ResponseCode.SUCCESS)
                && response.getHttpStatusCode().equals(HttpStatus.OK.value())) {
            try {
                captureLogsServiceFileInterface.uploadFile(file, captureLog, customerId, d);
            } catch (IOException e) {
                logger.error("", e);
                return Response.createBadRequestResponse(
                        message.get(Message.Logs.LOGS_FILE_UPLOAD_FAILURE));
            }
            return response;
        } else {
            return response;
        }
    }

    private Response<Logs> validateLogsTime(Logs log) {
        if (log.getLogFileStartTime().compareTo(log.getLogFileEndTime()) != 0) {
            if (log.getLogFileEndTime().after(log.getLogFileStartTime())) {
                return new Response<>(log, null, "FileUploadSucessfully", ResponseCode.SUCCESS,
                        message.get(Message.Logs.LOGS_FILE_UPLOAD_SUCCESS), ResponseCode.SUCCESS);
            } else {
                return new Response<>(log, null, "InvalidTime", ResponseCode.FAILURE,
                        message.get(Message.Logs.LOGS_TIME_MISMATCH_ERROR), ResponseCode.FAILURE);
            }
        } else {
            return new Response<>(log, null, "InvalidTime", ResponseCode.FAILURE,
                    message.get(Message.Logs.LOGS_TIME_SAME_ERROR), ResponseCode.FAILURE);
        }
    }

    @Override
    public Response<?> downloadLogByURL(
            Long deviceId,
            Long logFileStartTime,
            Long logFileEndTime,
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {

        Long customerId = TenantContext.getCustomerId();
        if (deviceId == null || logFileStartTime == null || logFileEndTime == null) {
            return new ValidationErrorResponse("EmptyMandatoryFields",
                    message.get(Message.Logs.LOGS_FIELDS_EMPTY_ERROR));
        }

        if (logFileStartTime.compareTo(logFileEndTime) >= 0) {
            return new Response<>(null, null, "InvalidTime", ResponseCode.FAILURE,
                    message.get(Message.Logs.LOGS_TIME_MISMATCH_ERROR), ResponseCode.FAILURE);
        }

        Device device = deviceRepository.getByDeviceIdAndCustomerId(
                deviceId
        );
        if (device == null) {
            logger.error("No device against provided customer found in DB");
            Response<Void> urlResponse = new Response<>();
            urlResponse.setResult(null);
            urlResponse.setCode(ResponseCode.SUCCESS);
            urlResponse.setName("DeviceCustomerMappingNotFound");
            urlResponse.setMessage(message.get(Message.Device.DEVICE_CUSTOMER_MAPPING_NOT_FOUND));
            urlResponse.setHttpStatusCode(ResponseCode.SUCCESS);
            return urlResponse;
        }

        Logs log = new Logs();
        log.setDeviceId(deviceId);
        log.setLogFileStartTime(new Date(logFileStartTime));
        log.setLogFileEndTime(new Date(logFileEndTime));

        List<Logs> fileList = captureLogRepository.getLogsFilesInRange(
                log.getDeviceId(),
                log.getLogFileStartTime(),
                log.getLogFileEndTime()
        );

        if (fileList == null || fileList.isEmpty()) {
            Response<List<DeviceLogsResponseDTO>> urlResponse = new Response<>();
            urlResponse.setResult(new ArrayList<>(0));
            urlResponse.setName("FileNotFound");
            urlResponse.setCode(ResponseCode.SUCCESS);
            urlResponse.setHttpStatusCode(ResponseCode.SUCCESS);
            urlResponse.setMessage(message.get(Message.Logs.LOGS_FILE_NOT_FOUND_ERROR));
            return urlResponse;
        }

        List<DeviceLogsResponseDTO> logsList = new ArrayList<>(fileList.size());
        for (Logs file : fileList) {
//            String downloadFilePath = captureLogsServiceFileInterface.generateLogFileDownloadUrl(
//                    file,
//                    customerId,
//                    device.getDeviceName(),
//                    device.getDeviceOs()
//            );
//            String path = "device-logs/"+customerId.toString()+"/"+deviceId.toString()+"/"+"49f79f44-6ef9-4a62-99ef-b7143dd1cddf.tar.gz";
            String path = ROOT_DEVICE_LOGS_DIRECTORY+"/"+customerId.toString()+"/"+deviceId.toString()+"/"+file.getLogFileName();
            String downloadFilePath = s3Service.signedUrl(path,null,null);
            DeviceLogsResponseDTO resDTO = new DeviceLogsResponseDTO();
            resDTO.setLogFileName(
                    LogUtilKt.readableLogFileName(file) + "." + device.getDeviceOs().getLogFileExtension());
            resDTO.setLogFileUrl(downloadFilePath);
            resDTO.setLogFileStartTime(file.getLogFileStartTime());
            resDTO.setLogFileEndTime(file.getLogFileEndTime());
            resDTO.setLogsUploadStatus(file.getLogsUpdateStatus());
            logsList.add(resDTO);
        }
        return new Response<>(
                logsList,
                null,
                "UrlFoundSuccess",
                ResponseCode.SUCCESS,
                message.get(Message.Logs.LOGS_URL_FOUND_SUCCESS),
                HttpStatus.OK.value()
        );
    }

    @Autowired
    private SnapshotImprovedService snapshotImprovedService;
    private static final boolean USE_NEW_SERVICE = true;
    @Override
    public Response<?> uploadSnapShot(MultipartFile file, String str, HttpServletRequest request)
            throws IOException {
        if (USE_NEW_SERVICE) {
            return snapshotImprovedService.uploadSnapShot(file, str, request);
        }
        Long customerId = TenantContext.getCustomerId();
        Long deviceId = TenantContext.getDeviceId();

        if (customerId == null) {
            return new NotFoundResponse<>("CustomerNotFound", "Customer not found");
        }

        if ((file == null) || (file.isEmpty()) || (str == null)) {
            logger.error("uploaded zip file is null");
            return new NotFoundResponse<Void>("FileNotFound",
                    message.get(Message.SnapShot.SNAPSHOT_FILE_NOT_FOUND_ERROR));
        }
        logger.debug("requested string = {}", str);
        TOrResponse<ListOfSnapshotsDTO> tOrResponse = JsonUtilsKt.jsonFromString(
                objectMapper,
                str,
                ListOfSnapshotsDTO.class
        );

        if (tOrResponse.getResponse() != null) {
            return tOrResponse.getResponse();
        }

        ListOfSnapshotsDTO listOfSnapshotsDTO = tOrResponse.getT();
        assert listOfSnapshotsDTO != null;

        // check if snapshot array is empty
        if (listOfSnapshotsDTO.getSnapshots() == null || listOfSnapshotsDTO.getSnapshots().isEmpty()) {
            logger.error("validation failed in snapshots");
            return Response.Builder.voidResponseBuilder()
                    .badRequest()
                    .code(ResponseCode.FAILURE)
                    .name("snapshots")
                    .message(message.get(Message.SnapShot.SNAPSHOTS_ARRAY_EMPTY))
                    .build();
        }

        listOfSnapshotsDTO.setDeviceId(deviceId);
        assert listOfSnapshotsDTO.getSnapshots() != null;

        if (!fileSnapshotTimeValidation(listOfSnapshotsDTO)) {
            return Response.Builder.voidResponseBuilder()
                    .badRequest()
                    .name("FileUploadValidationFail")
                    .message(message.get(Message.SnapShot.SNAPSHOT_FILE_SNAPSHOT_TIME_ERROR))
                    .build();
        }

        Device device = deviceRepository.getByDeviceIdAndCustomerId(
                deviceId
        );
        if (device == null) {
            return new NotFoundResponse<>("DeviceCustomerMismatchError",
                    message.get(Message.DeviceConfig.DEVICECONFIG_DEVICE_CUSTOMER_MISMATCH_ERROR));
        }

        // check file formats
        if (!fileFormatCheckValidation(listOfSnapshotsDTO)) {
            logger.error("mismatched files in zip folder");
            return Response.Builder.voidResponseBuilder()
                    .badRequest()
                    .name("FileUploadValidationFail")
                    .message(message.get(Message.SnapShot.SNAPSHOT_FILE_FORMAAT_ERROR))
                    .build();
        }

        String fileExt = getFileExtension(file);
        FileType fileType = (fileExt.contains("tar")
                || fileExt.contains("gz")) ? FileType.TAR
                : (fileExt.contains("zip") ? FileType.ZIP : null);

        if (fileType == null) {
            return new ValidationErrorResponse("InvalidFileType",
                    "Invalid file type " + fileExt + " found. Supported file types  ");
        }

        if (FileType.TAR.equals(fileType)) {
            // For tar.gz files
            if (!validateFilesWithTar(file, listOfSnapshotsDTO)) {
                logger.error("mismatch files in tar.gz");
                return new NotFoundResponse<Void>("TarArchiveEntryMismatchError",
                        message.get(Message.SnapShot.SNAPSHOT_ZIP_ENTRY_MISMATCH_ERROR));
            }
        } else {
            // for zip files
            if (!validateFilesWithZip(file, listOfSnapshotsDTO)) {
                logger.error("mismatch files in zip");
                return new NotFoundResponse<Void>("ZipEntryMismatchError",
                        message.get(Message.SnapShot.SNAPSHOT_ZIP_ENTRY_MISMATCH_ERROR));
            }
            logger.debug("validateFilesWithZip method completed");
        }

        String zipOrTarFileName = file.getOriginalFilename();
        assert zipOrTarFileName != null;

        // Example: UR42Y
        String intermediateDirName =
                device.getDeviceId() + "-" + RandomStringUtils.randomAlphanumeric(5).toUpperCase();

        Path tempFilePath = Paths.get(tempWorkingDir, UUID.randomUUID().toString() + fileExt);
        file.transferTo(tempFilePath.toFile());

        SnapshotAsyncModel snapshotAsyncModel = SnapshotAsyncModel.Builder.aBuilder()
                //.snapshotZipOrTarFilePath(tempFileActualFilePath)
                // /panasonicdata/snapshots/UR42Y/{received-file-name-in-multipart-request}.{tar.gz/zip}
                .listOfSnapshotsDTOAsJson(objectMapper.writeValueAsString(listOfSnapshotsDTO))
                .intermediateDirName(intermediateDirName)// UR42Y (random 5 char alpha numeric)
                //.tempLocation(tempFileDirectoryPath) // /var/opt/panasonic/snapshots/temp/UR42Y
                .customerId(customerId)
                .deviceId(deviceId)
                //.destinationPath(
                //    destinationPath) // /var/opt/panasonic/snapshots/15/    where 15 is deviceId
                .lastSyncTime(new Date())
                .snapshotAsyncProgress(SnapshotAsyncProgress.TODO)
                .fileType(fileType)  // enum: TAR | ZIP
                //.serverUniqueIdentification(serverConfigDataDto.getServerUniqueIdentificationNumber())
                .build();

        //save in temporary
        try {
            captureLogsServiceFileInterface.saveSnapshotInTemporaryLocation(
                    snapshotAsyncModel,
                    tempFilePath.toFile()
            );
        } catch (Exception e) {
            return Response.createInternalServerErrorResponseFromException(e);
        }

        snapshotAsyncRepository.save(snapshotAsyncModel);

        //Snapshots will get Saved by Cron Scheduled at every 10 min
        // captureLogUtils.uploadDeviceSnapshotAsync(fileType);
        return Response.Builder.voidResponseBuilder()
                .success()
                .name("FileUploadSuccessfully")
                .code(ResponseCode.SUCCESS)
                .message(message.get(Message.SnapShot.SNAPSHOT_FILE_UPLOADED_SUCCESS))
                .build();
    }

    @Override
    public void downloadThumbnail(String encryptedCustomerId, Long snapshotId, Boolean download,
                                  HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws IOException {
        Long customerId = fileDownloadService.decryptCustomerId(encryptedCustomerId);
        if (customerId == null) {
            logger.error("customer id null in request");
            writeResponseToHttpServletResponse(httpServletResponse,
                    new NotFoundResponse<Void>("CustomerNotFound", "Customer not found"));
            return;
        }
        Optional<SnapShot> snapshotOptional = snapShotRepository.findById(snapshotId);

        if (snapshotOptional.isPresent()) {
            SnapShot snapShot = snapshotOptional.get();

            try {
                captureLogsServiceFileInterface.downloadThumbnail(customerId, snapShot, download, httpServletRequest, httpServletResponse);
            } catch (FileNotFoundException e) {
                writeResponseToHttpServletResponse(httpServletResponse,
                        new NotFoundResponse<Void>("SnapshotNotFound",
                                "Snapshot thumbnail having snapshotId " + snapshotId + " not found"));
            }
        } else {
            writeResponseToHttpServletResponse(httpServletResponse,
                    new NotFoundResponse<Void>("SnapshotNotFound",
                            "Snapshot thumbnail having snapshotId " + snapshotId + " not found"));
        }
    }

    @Override
    public void downloadSnapShot(String encryptedCustomerId, Long snapshotId, Boolean download,
                                 HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws IOException {
        Long customerId = fileDownloadService.decryptCustomerId(encryptedCustomerId);
        if (customerId == null) {
            writeResponseToHttpServletResponse(httpServletResponse,
                    new NotFoundResponse<Void>("CustomerNotFound", "Customer not found"));
        }
        Optional<SnapShot> snapShotOpt = snapShotRepository.findById(snapshotId);

        if (snapShotOpt.isPresent()) {
            SnapShot snapShot = snapShotOpt.get();
            Device device = deviceRepository.findByAllDeviceId(snapShot.getDeviceId());

            try {
                captureLogsServiceFileInterface.downloadSnapshotFile(
                        SnapshotUtilKt.getFilenameForSnapshot(
                                snapShot,
                                device.getDeviceName()
                        ),
                        snapShot,
                        download,
                        customerId,
                        httpServletRequest,
                        httpServletResponse
                );
            } catch (FileNotFoundException e) {
                writeResponseToHttpServletResponse(httpServletResponse,
                        new NotFoundResponse<Void>("snapShotNotFound",
                                "snapShot having snapshotId " + snapshotId + " not found"));
            }
        } else {
            writeResponseToHttpServletResponse(httpServletResponse,
                    new NotFoundResponse<Void>("snapShotNotFound",
                            "snapShot having snapshotId " + snapshotId + " not found"));
        }
    }

    @Override
    public void downloadLogFile(String encryptedCustomerId, Long logId, Boolean download,
                                HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws IOException {

        Long customerId = fileDownloadService.decryptCustomerId(encryptedCustomerId);
        if (customerId == null) {
            logger.error("customer id null in request");
            writeResponseToHttpServletResponse(httpServletResponse,
                    new NotFoundResponse<Void>("CustomerNotFound", "Customer not found"));
            return;
        }
        Optional<Logs> logsOptional = captureLogRepository.findById(logId);
        if (logsOptional.isPresent()) {
            Logs log = logsOptional.get();
            if (log != null) {
                Device device = deviceRepository.findByAllDeviceId(log.getDeviceId());
                try {
                    captureLogsServiceFileInterface.downloadLogFile(
                            log,
                            download,
                            LogUtilKt.getDesiredLogFileName(
                                    log,
                                    device.getDeviceName(),
                                    device.getDeviceOs()
                            ),
                            customerId,
                            httpServletRequest,
                            httpServletResponse
                    );
                } catch (FileNotFoundException e) {
                    writeResponseToHttpServletResponse(httpServletResponse,
                            new NotFoundResponse<Void>("LogNotFound", "Log having logId " + logId + " not found"));
                }
            } else {
                writeResponseToHttpServletResponse(httpServletResponse,
                        new NotFoundResponse<Void>("LogNotFound", "Log having logId " + logId + " not found"));
            }
        }
    }



    public static boolean fileSnapshotTimeValidation(ListOfSnapshotsDTO listOfSnapshotsDTO) {
        boolean isValidTime = true;
        assert listOfSnapshotsDTO.getSnapshots() != null;
        long now = System.currentTimeMillis();
        for (SnapShot snapShot : listOfSnapshotsDTO.getSnapshots()) {
            if (snapShot.getSnapshotTimeForJsonButTransient() > now) {
                isValidTime = false;
                break;
            }
        }
        return isValidTime;
    }
    public static boolean fileFormatCheckValidation(ListOfSnapshotsDTO listOfSnapshotsDTO) {
        assert listOfSnapshotsDTO.getSnapshots() != null;
        boolean extensionFlag = true;
        for (SnapShot snapShot : listOfSnapshotsDTO.getSnapshots()) {
            String extension = FilenameUtils.getExtension(snapShot.getSnapshotFileName());
            if (!extension.trim().equalsIgnoreCase("png")
                    && (!(extension.trim().equalsIgnoreCase("jpg")
                    || extension.trim().equalsIgnoreCase("jpeg")))) {
                extensionFlag = false;
                break;
            }
        }
        return extensionFlag;
    }
    public static boolean validateFilesWithTar(
            MultipartFile file,
            @NonNull ListOfSnapshotsDTO listOfSnapshotsDTO
    ) throws IOException {
        Objects.requireNonNull(listOfSnapshotsDTO.getSnapshots());
        try (InputStream inputStream = file.getInputStream();
             BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
             GzipCompressorInputStream gzipCompressorInputStream
                     = new GzipCompressorInputStream(bufferedInputStream);
             TarArchiveInputStream zis = new TarArchiveInputStream(gzipCompressorInputStream)) {
            TarArchiveEntry entry;
            int totalFilesCount = 0;
            while ((entry = zis.getNextTarEntry()) != null) {
                boolean isValid = false;
                int oneCount = 0;

                for (SnapShot snapShot : listOfSnapshotsDTO.getSnapshots()) {
                    if (FilenameUtils.getName(entry.getName())
                            .equalsIgnoreCase(snapShot.getSnapshotFileName()
                                    .substring(0, snapShot.getSnapshotFileName().lastIndexOf('.')))
                            || FilenameUtils.getName(entry.getName())
                            .equalsIgnoreCase(snapShot.getSnapshotFileName())) {
                        isValid = true;
                        oneCount++;
                        totalFilesCount++;
                        break;
                    }
                }
                if (!isValid) {
                    return false;
                } else if (oneCount != 1) {
                    return false;
                }
            }
            // actual files and the file sizes should match
            return totalFilesCount == listOfSnapshotsDTO.getSnapshots().size();
        }
    }
    public static boolean validateFilesWithZip(
            MultipartFile file,
            ListOfSnapshotsDTO listOfSnapshotsDTO
    ) throws IOException {
        Objects.requireNonNull(listOfSnapshotsDTO.getSnapshots());
        try (InputStream inputStream = file.getInputStream();
             BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
             ZipInputStream zis = new ZipInputStream(bufferedInputStream)) {
            ZipEntry entry;
            int totalFilesCount = 0;
            while ((entry = zis.getNextEntry()) != null) {
                boolean isValid = false;
                int oneCount = 0;
                logger.debug("Extracting: " + entry);
                for (SnapShot snapShot : listOfSnapshotsDTO.getSnapshots()) {
                    if (entry.getName().equalsIgnoreCase(snapShot.getSnapshotFileName()
                            .substring(0, snapShot.getSnapshotFileName().lastIndexOf('.'))) ||
                            entry.getName().equalsIgnoreCase(snapShot.getSnapshotFileName())) {
                        isValid = true;
                        oneCount++;
                        totalFilesCount++;
                        break;
                    }
                }
                if (!isValid) {
                    return false;
                } else if (oneCount != 1) {
                    return false;
                }
            }
            // total files count should match
            return totalFilesCount == listOfSnapshotsDTO.getSnapshots().size();
        }
    }
    public static String getFileExtension(MultipartFile file) {
        String extension = "";

        try {
            if (file != null && !file.isEmpty()) {
                String name = file.getOriginalFilename();
                if (name != null) {
                    extension = name.substring(name.lastIndexOf('.'));
                } else {
                    extension = "";
                }
            }
        } catch (Exception e) {
            extension = "";
        }

        return extension;
    }
    @Override
    public Response<?> pushDeviceCurrentSnapShot(
            CurrentSnapshotRequestDTO currentSnapshotRequestDTO
    ) {
        DeviceSnapshot snapshot;
        Long loggedInUserId = TenantContext.getCurrentUserId();
        Long customerId = TenantContext.getCustomerId();
        boolean isNonSslEnv = isWebPushViaFirebase();

        boolean isSendPushByFirebase = isWebPushViaFirebase();
        String errorMsg = isSendPushByFirebase ?
                "deviceId and firebaseRegistrationId are required"
                : "deviceId is required";

        if (currentSnapshotRequestDTO == null ||
                Objects.isNull(currentSnapshotRequestDTO.getDeviceId()) || (
                isSendPushByFirebase && StringUtils.isBlank(currentSnapshotRequestDTO.getFirebaseRegistrationId()))) {
            return Response.Builder.voidResponseBuilder()
                    .badRequest()
                    .name(EMPTY_BODY_ERROR_MSG)
                    .message(errorMsg)
                    .build();
        }

        Long deviceId = currentSnapshotRequestDTO.getDeviceId();

        TOrResponse<Device> deviceOrResponse = checkDevice(deviceId, customerId);

        if (deviceOrResponse.getResponse() != null) {
            return deviceOrResponse.getResponse();
        }

        Device device = deviceOrResponse.getT();
        assert device != null;

        snapshot = new DeviceSnapshot();
        snapshot.setDeviceId(deviceId);
        snapshot.setFirebaseRegistrationId(currentSnapshotRequestDTO.getFirebaseRegistrationId());
        snapshot.setUserId(loggedInUserId);
        snapshot.setUniqueRequestId(UUID.randomUUID().toString());
        snapshot.setCustomerId(device.getCustomerId());
        snapshot.setUploadComplete(UploadComplete.NOT_UPLOADED);
        snapshot = deviceSnapshotRepository.save(snapshot);

        //send Push Notification
        push.sendPush(PushId.ID_CLIENT_SEND_A_SNAPSHOT_NOW, device, null, loggedInUserId,
                snapshot.getUniqueRequestId());

        return new Response.Builder<DeviceSnapshot>()
                .success()
                .result(snapshot)
                .code(ResponseCode.SUCCESS)
                .message(message.get(Message.DeviceSnapshot.DEVICE_SNAPSHOT_PUSH_CURRENT))
                .build();
    }
    private TOrResponse<Device> checkDevice(Long deviceId, Long customerId) {
        Device device = deviceRepository.findByDeviceIdAndCustomerId(deviceId, customerId);
        if (device == null || Status.DELETED.equals(device.getStatus())) {
            // device deleted or not found
            logger.debug("device deleted or not found");
            return new TOrResponse<>(
                    null,
                    Response.Builder.voidResponseBuilder()
                            .notFound()
                            .name("DeviceNotFound")
                            .code(404)
                            .message("Device Not found")
                            .build()
            );
        } else {
            return new TOrResponse<>(device, null);
        }
    }

    @Override
    public Response<?> downloadCurrentImageSnapshotThumb(
            String uniqueRequestId,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse
    ) throws IOException {
        return downloadCurrentSnapshotFile(
                uniqueRequestId,
                httpServletRequest,
                httpServletResponse,
                true
        );
    }

    @Override
    public Response<?> downloadCurrentImageSnapshot(
            String uniqueRequestId,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse
    ) throws IOException {
        return downloadCurrentSnapshotFile(
                uniqueRequestId,
                httpServletRequest,
                httpServletResponse,
                false
        );
    }


    private Response<?> downloadCurrentSnapshotFile(
            String uniqueRequestId,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            boolean isThumbnail
    ) throws IOException {
        if (uniqueRequestId == null || uniqueRequestId.isEmpty()) {
            return Response.Builder.voidResponseBuilder()
                    .badRequest()
                    .name("EmptyMandatoryFields")
                    .code(ResponseCode.FAILURE)
                    .message(message.get(Message.Common.COMMON_FIELD_VALIDATION_MESSAGE))
                    .build();
        }

        DeviceSnapshot deviceSnapshot =
                deviceSnapshotRepository.findDeviceSnapshotByUniqueRequestId(uniqueRequestId);
        if (deviceSnapshot == null) {
            return Response.Builder.voidResponseBuilder()
                    .notFound()
                    .name("DeviceUniqueRequestMismatchError")
                    .message(message.get(Message.DeviceSnapshot.DEVICE_UNIQUE_REQUEST_MISMATCH_ERROR))
                    .build();
        }
        Path dirPath;
        if (isThumbnail) {
            // snapshot thumbnail
            dirPath = getCurrentSnapshotThumbnailDirPath(deviceSnapshot.getCustomerId());
        } else {
            // actual snapshot
            dirPath = getCurrentSnapshotDirPath(deviceSnapshot.getCustomerId());
        }
        Path downloadFilePath =
                Paths.get(dirPath.toString(), uniqueRequestId + "." +
                        (isThumbnail ? THUMBNAIL_EXTENSION : deviceSnapshot.getFileExtension()));
        try {
            fileDownloadService.downloadFile(
                    downloadFilePath,
                    !isThumbnail,
                    SnapshotUtilKt.getFilenameCurrentSnapshot(
                            isThumbnail,
                            deviceSnapshot
                    ),
                    httpServletRequest,
                    httpServletResponse,
                    deviceSnapshot.getCustomerId()
            );
            return null;
        } catch (IOException e) {
            logger.error("", e);
            return Response.createInternalServerErrorResponseFromException(e);
        }
    }
    public Path getCurrentSnapshotThumbnailDirPath(Long customerId) {
        return Paths.get(
                rootDir,
                DEVICE_SNAPSHOT_DIR,
                THUMB_DIR,
                String.valueOf(customerId)
        );
    }
    public Path getCurrentSnapshotDirPath(Long customerId) {
        return Paths.get(
                rootDir,
                DEVICE_SNAPSHOT_DIR,
                String.valueOf(customerId)
        );
    }




}
