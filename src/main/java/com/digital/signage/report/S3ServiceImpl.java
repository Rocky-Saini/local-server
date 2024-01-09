package com.digital.signage.report;


import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.HttpMethod;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.Md5Utils;
import com.digital.signage.configuration.ApplicationProperties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
public class S3ServiceImpl implements S3Service {
    private ApplicationProperties properties;
    private final AmazonS3 s3client;

    public S3ServiceImpl(@NonNull AmazonS3 s3client, ApplicationProperties properties) {
        this.properties = properties;
        this.s3client = s3client;
    }

    @Override
    public String signedUrl(String objectKey, String contentDisposition, Long deviceId) {
        long expTimeMillis = System.currentTimeMillis();
        expTimeMillis += TimeUnit.SECONDS.toMillis(properties.getServerConfig().getS3().getPreSignedUrlExpirationSeconds());
        return signedUrl(objectKey, expTimeMillis, contentDisposition, deviceId);
    }

    @Override
    public String signedUrl(String objectKey, long expiryTimeInMillis, String contentDisposition, Long deviceId)  {
        return signedUrl(objectKey, expiryTimeInMillis, contentDisposition, properties.getServerConfig().getS3().getBucketName(), deviceId);
    }
    @Override
    public String signedUrl(String objectKey, long expiryTimeInMillis, String contentDisposition, String bucketName, Long deviceId) {
        Objects.requireNonNull(s3client);
        Objects.requireNonNull(bucketName);
        try {
            // Set the pre signed URL to expire after one hour.
            GeneratePresignedUrlRequest generatePresignedUrlRequest =
                    new GeneratePresignedUrlRequest(bucketName, objectKey)
                            .withMethod(HttpMethod.GET)
                            .withExpiration(new Date(expiryTimeInMillis));
            if (StringUtils.isNotBlank(contentDisposition)) {
                ResponseHeaderOverrides responseHeaderOverrides = new ResponseHeaderOverrides();
                responseHeaderOverrides.setContentDisposition(contentDisposition);
                generatePresignedUrlRequest.setResponseHeaders(responseHeaderOverrides);
            }
            if (Objects.nonNull(deviceId)) {
                generatePresignedUrlRequest.addRequestParameter("deviceId", deviceId.toString());
            }
            URL url = s3client.generatePresignedUrl(generatePresignedUrlRequest);
            return url.toString();
        } catch (SdkClientException sdkClientException) {
            throw new SignedUrlGenerationFailedException(sdkClientException.getLocalizedMessage(), sdkClientException);
        }
    }
    @Override
    public void uploadFile(String keyName, File fileToBeUploaded) {
        try {
            uploadFile(keyName, fileToBeUploaded, Md5Utils.md5AsBase64(fileToBeUploaded));
        } catch (IOException ioException) {
            throw new FileUploadFailedException(ioException.getLocalizedMessage(), ioException);
        }
    }
    @Override
    public void uploadFile(String keyName, File fileToBeUploaded, String md5) {
        Objects.requireNonNull(s3client);
        try {
            String bucketName = properties.getServerConfig().getS3().getBucketName();
            PutObjectRequest req = new PutObjectRequest(bucketName, keyName, fileToBeUploaded);
            req.putCustomRequestHeader("Content-MD5", md5);
            s3client.putObject(req);
            checkIfObjectExist(keyName);
        } catch (AmazonClientException amazonClientException) {
            throw new FileUploadFailedException(amazonClientException.getLocalizedMessage(), amazonClientException);
        }
    }
    @Override
    public boolean checkIfObjectExist(String objectKey) {
        return checkIfObjectExist(objectKey, properties.getServerConfig().getS3().getBucketName());
    }
    @Override
    public boolean checkIfObjectExist(String objectKey, String bucketName) {
        Objects.requireNonNull(s3client);
        try {
            return s3client.doesObjectExist(bucketName, objectKey);
        } catch (NullPointerException | SdkClientException runtimeException) {
            throw new CouldNotCheckObjectException(runtimeException.getLocalizedMessage(), runtimeException);
        }
    }
    @Override
    public void uploadFile(String keyName, Path uploadFilePath) {
        uploadFile(keyName, uploadFilePath.toFile());
    }

    @Override
    public void deleteObjects(String objectKey) {
        try {
            Objects.requireNonNull(s3client);
            ListObjectsV2Result result;
            String bucketName = properties.getServerConfig().getS3().getBucketName();
            do {
                result = s3client.listObjectsV2(bucketName, objectKey);
                if (result.getKeyCount() != 0) {
                    for (S3ObjectSummary objectSummary : result.getObjectSummaries()) {
                        s3client.deleteObject(bucketName, objectSummary.getKey());
                    }
                }
            } while (result.isTruncated());
        } catch (AmazonServiceException amazonServiceException) {
            throw new DeleteUrlGenerationFailedException(amazonServiceException.getLocalizedMessage(), amazonServiceException);
        }
    }

    @Override
    public void delete(String objectKey) {
        Objects.requireNonNull(s3client);
        try {
            String bucketName = properties.getServerConfig().getS3().getBucketName();
            s3client.deleteObject(new DeleteObjectRequest(bucketName, objectKey));
        } catch (NullPointerException | SdkClientException runtimeException) {
            throw new DeleteUrlGenerationFailedException(runtimeException.getLocalizedMessage(), runtimeException);
        }
    }
}
