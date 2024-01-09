package com.digital.signage.service.impl;


import com.digital.signage.context.TenantContext;
import com.digital.signage.dto.MediaDetailDto;
import com.digital.signage.dto.MediaVersionDTO;
import com.digital.signage.localserver.Response.ApiResponsee;
import com.digital.signage.localserver.Response.NotFoundResponse;
import com.digital.signage.localserver.apiservice.MediaApiService;
import com.digital.signage.localserver.apiservice.RetrofitHelper;
import com.digital.signage.models.Response;
import com.digital.signage.service.MediaService;
import com.digital.signage.util.ApplicationConstants;
import com.digital.signage.util.MD5Util;
import com.digital.signage.util.MediaDownloadUtility;
import com.digital.signage.util.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.UUID;

@Service
@Slf4j
public class MediaServiceImpl extends BaseServiceImpl implements MediaService {
    public static final Logger logger = LoggerFactory.getLogger(MediaServiceImpl.class);
    @Autowired
    RetrofitHelper retrofitHelper;
    @Value("${content.encrypt.decrypt.key}")
    private String encryptDecryptKey;
    @Value("${spring.http.multipart.location}")
    private String tmpDir;
    @Value("${root.storage.dir}")
    private String rootStorageDir;
    @Autowired
    private MediaDownloadUtility contentDownloadUtils;
    @Override
    public MediaDetailDto mediaDetail(Long mediaDetailId, int mediaVersion) {
        return null;
    }

    @Override
    public MediaVersionDTO mediaLatestVersion(Long mediaDetailId) {
        return null;
    }

    @Override
    public void mediaFile(
            Long mediaDetailId,
            String encryptedCustomerId,
            Boolean download,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse)
            throws IOException {
        boolean isFileExistOnLocalServer = false;
        //get Latest content version from server
//        final String uriForGetContentVersion =
//                mainServerBaseUrl() + UrlPaths.PATH_CONTENT_LATEST_VERSION
//                        + encryptedCustomerId + "/" + mediaDetailId;
        final String uriForGetContentVersion =
                mainServerBaseUrl() + "/content-management/cms/"+ TenantContext.getTenantId()+"/v1/media/version" + "/" + mediaDetailId;
        logger.debug(
                "going to send request to main server for get Latest version Detail for contentId: {}",
                mediaDetailId);
 /*   HttpEntity<?> contentVersionEntity = new HttpEntity<>("", getContentVersionHeaders);
    Response contentVersionResponse = new RestTemplate()
        .exchange(uriForGetContentVersion, HttpMethod.GET, contentVersionEntity, Response.class)
        .getBody();*/
        ApiResponsee<?> contentVersionResponse = null;
        try {

            retrofit2.Response<ApiResponsee<LinkedHashMap<String, Object>>> resp =
                    retrofitHelper.newMainServerRetrofit()
                            .create(MediaApiService.class)
                            .getContentVersion(TenantContext.getTenantId(),mediaDetailId, TenantContext.getCurrentToken())
                            .execute();

            MediaVersionDTO result = MediaVersionDTO.linkedHashMapToMediaVersionDTO(
                    resp.body().getData()
            );
            contentVersionResponse = new ApiResponsee.ApiResponseBuilder<Object>()
                    .data(result)
                    .status(resp.body().getStatus())
                    .occurredOn(resp.body().getOccurredOn())
                    .build();
//             contentVersionResponse = resp.body();
        } catch (IOException e) {
            logger.error("", e);
        }

        MediaVersionDTO contentVersionDTO = null;
        if (contentVersionResponse != null
                && contentVersionResponse.getStatus() != null
                && contentVersionResponse.getStatus().is2xxSuccessful()
                && contentVersionResponse.getData() != null) {
            contentVersionDTO = (MediaVersionDTO) contentVersionResponse.getData();
        }
        if (contentVersionDTO == null) {
            logger.info("No content found at main server for contentId:{}", mediaDetailId);
            writeResponseToHttpServletResponse(httpServletResponse,
                    new NotFoundResponse<>("ContentNotFound",
                            "No content found at main server for contentId:" + mediaDetailId));
            return;
        }

        // for checking file at local server
        logger.debug("checking content file at local server for contentId: {}", mediaDetailId);
        File f = null;
        Path filePath = getContentFilePathBasedOnVersion(contentVersionDTO, mediaDetailId);
        Path newFilePath = filePath.resolveSibling(filePath.getFileName().toString());
        try {
            f = newFilePath.toFile();
        } catch (Exception e) {
            logger.error("", e);
        }

        if (f != null && f.exists()) {
            isFileExistOnLocalServer = true;
        }
        // if content not exist on local server then hit main server
        if (!isFileExistOnLocalServer) {
            logger.debug("content file  not exist at local server for contentId: {}", mediaDetailId);
            ResponseUtils.writeResponseModelToHttpServletResponse(
                    objectMapper,
                    httpServletResponse,
                    new Response<>(
                            null,
                            null,
                            "FileNotAvailableOnLocalServer",
                            1,
                            "Please call the /fetch-files API as this file is not available on local server",
                            HttpStatus.NOT_FOUND.value()
                    )
            );
        } else {
            logger.info("content file exist at local server for contentId: {}", mediaDetailId);
            // Decrypt .jpc.enc and get the inputStream for downloading
            logger.debug("decrypting file");
            logger.debug("sending file to device");
            Path tempFilePath =
                    Paths.get(tmpDir, UUID.randomUUID().toString() + "." + contentVersionDTO.getFileExt());
            File tmpFile = new File(tempFilePath.toString());
            try
//                    (
//                    InputStream inputStream = decryptContentFile(filePath.toString(), encryptDecryptKey);
//                 FileOutputStream fileOutputStream = new FileOutputStream(tmpFile))
            {
//                IOUtils.copyLarge(inputStream, fileOutputStream);
                // local decrypted copy ready
                String mediaHex = MD5Util.getMD5HashOfTheFile(tempFilePath);
                logger.debug("MD5 hash of contentId {} on local server is {}", mediaDetailId, mediaHex);
            } catch (IOException e) {
                logger.error("", e);
                ResponseUtils.writeResponseModelToHttpServletResponse(
                        objectMapper,
                        httpServletResponse,
                        Response.createInternalServerErrorResponseFromException(e)
                );
                try {
                    Files.delete(tempFilePath);
                } catch (IOException e1) {
                    logger.error("", e1);
                }
                return;
            }
            try (InputStream is = new FileInputStream(tmpFile)) {
                long fileSize = Files.size(tempFilePath);
                logger.debug("File size of contentId {} on local server is {} bytes", mediaDetailId, fileSize);
                contentDownloadUtils.downloadFileUsingPath(
                        tempFilePath,
                        is,
                        download,
                        httpServletResponse,
                        contentVersionDTO.getContentName() + "." + contentVersionDTO.getFileExt(),
                        fileSize
                );
            } catch (IOException e) {
                logger.error("", e);
                ResponseUtils.writeResponseModelToHttpServletResponse(
                        objectMapper,
                        httpServletResponse,
                        Response.createInternalServerErrorResponseFromException(e)
                );
            } finally {
                new Thread(() -> {
                    try {
                        Thread.sleep(2000L);
                    } catch (InterruptedException e) {
                        logger.error("", e);
                    }
                    if (tmpFile != null && tmpFile.exists()) {
                        String fileName = tmpFile.getName();
                        try {
                            logger.debug("deleting temp file {}", fileName);
                            Files.delete(tempFilePath);
                            logger.debug("deletion of {} successful", fileName);
                        } catch (IOException e1) {
                            logger.error("", e1);
                        }
                    }
                }).start();
            }
//        logger.info("this method not supported by local server");
//        throw new NotRequiredForLocalServerException();

        }
    }
    private Path getContentFilePathBasedOnVersion(MediaVersionDTO contentVersionDTO,
                                                  Long contentId) {
        return Paths.get(rootStorageDir, ApplicationConstants.ROOT_CONTENT_DIRECTORY,
                String.valueOf(contentId), String.valueOf(
                        contentVersionDTO.getVersion()), String.valueOf(contentId) + "."
                        + contentVersionDTO.getFileExt());
    }
}
