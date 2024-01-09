package com.digital.signage.report;


import org.springframework.context.annotation.Bean;
import org.springframework.lang.Nullable;

import java.io.File;
import java.nio.file.Path;


public interface S3Service {
    String signedUrl(String objectKey, @Nullable String contentDisposition, @Nullable Long deviceId);

    String signedUrl(String objectKey, long expiryTimeInMillis, @Nullable String contentDisposition, @Nullable Long deviceId);

    String signedUrl(String objectKey, long expiryTimeInMillis, String contentDisposition, String bucketName,
                     @Nullable Long deviceId);
    void uploadFile(String keyName, File fileToBeUploaded);
    void uploadFile(String keyName, File fileToBeUploaded, String md5);
    void delete(String objectKey);
    void deleteObjects(String objectKey);
    boolean checkIfObjectExist(String objectKey);
    boolean checkIfObjectExist(String objectKey, String bucketName);
    void uploadFile(String fileObjKeyName, Path filePath)
            throws FileUploadFailedException;

}