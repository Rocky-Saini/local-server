package com.digital.signage.jwt;


import com.digital.signage.exceptions.InvalidJwtException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class JwtCompatForSignedUrls {
  private JwtCompatForSignedUrls() {
  }

  private static final String SECRET_FOR_SIGNED_URL = "c8807867-f9eb-4761-8f04-85d18c340c15";

  private static final byte[] SECRET_IN_BYTE_ARRAY
      = Base64.getEncoder().encode(SECRET_FOR_SIGNED_URL.getBytes());

  private static final ObjectMapper mapper = new ObjectMapper();

  private static final Logger logger = LoggerFactory.getLogger(JwtCompatForSignedUrls.class);

  public static String generateCompatJwtForSignedUrl(long customerId, long mediaDetailId,
      String name, String fileExtension, String nameOfFileOnDisk, Long userId, Long deviceId,
      long expiry,
      TimeUnit expiryTimeUnit) throws IOException {
    if (expiry <= 0) throw new IllegalArgumentException("Expiry cannot be less than or equal to 0");
    String subject = mapper.writeValueAsString(
        new CustomerIdMediaDetailIdModel(customerId, mediaDetailId, userId, deviceId, name,
            fileExtension, nameOfFileOnDisk));
    JwtBuilder builder = Jwts.builder().setId(UUID.randomUUID().toString())
        .setIssuedAt(new Date())
        .setSubject(subject)
        .setIssuer("Digital Signage Server")
        .signWith(SignatureAlgorithm.HS512, SECRET_IN_BYTE_ARRAY);

    long expMillis;
    expMillis = System.currentTimeMillis() + expiryTimeUnit.toMillis(expiry);
    Date exp = new Date(expMillis);
    builder.setExpiration(exp);
    return builder.compact();
  }

  public static String generateCompatJwtForSignedUrl(long customerId, Long userId,
      Long deviceId, long expiry, TimeUnit expiryTimeUnit) throws IOException {
    if (expiry <= 0) throw new IllegalArgumentException("Expiry cannot be less than or equal to 0");
    String subject =
        mapper.writeValueAsString(
            new CustomerIdMediaDetailIdModel(customerId, null, userId, deviceId, null, null, null));
    JwtBuilder builder = Jwts.builder().setId(UUID.randomUUID().toString())
        .setIssuedAt(new Date())
        .setSubject(subject)
        .setIssuer("Digital Signage Server")
        .signWith(SignatureAlgorithm.HS512, SECRET_IN_BYTE_ARRAY);

    long expMillis;
    expMillis = System.currentTimeMillis() + expiryTimeUnit.toMillis(expiry);
    Date exp = new Date(expMillis);
    builder.setExpiration(exp);
    return builder.compact();
  }

  public static String generateCompatJwtForSignedUrlForBuildDownload(
      boolean isOnPremiseServer, Long deviceId, Long userId, long expiry, TimeUnit expiryTimeUnit)
      throws IOException {
    if (expiry <= 0) throw new IllegalArgumentException("Expiry cannot be less than or equal to 0");
    String subject =
        mapper.writeValueAsString(
            new BuildDownloadInfoModel(deviceId, isOnPremiseServer, userId));
    JwtBuilder builder = Jwts.builder().setId(UUID.randomUUID().toString())
        .setIssuedAt(new Date())
        .setSubject(subject)
        .setIssuer("Digital Signage Server")
        .signWith(SignatureAlgorithm.HS512, SECRET_IN_BYTE_ARRAY);

    long expMillis;
    expMillis = System.currentTimeMillis() + expiryTimeUnit.toMillis(expiry);
    Date exp = new Date(expMillis);
    builder.setExpiration(exp);
    return builder.compact();
  }

  public static BuildDownloadInfoModel decryptCompatJwtForSignedUrlForBuildInfoModel(
      String jwtToken
  ) throws InvalidJwtException {
    try {
      String subject = Jwts.parser()
          .setSigningKey(SECRET_IN_BYTE_ARRAY)
          .parseClaimsJws(jwtToken)
          .getBody().getSubject();
      if (subject == null) throw new InvalidJwtException();
      return mapper.readValue(subject, BuildDownloadInfoModel.class);
    } catch (Exception e) {
      throw new InvalidJwtException(e, e instanceof ExpiredJwtException);
    }
  }

  public static String generateCompatJwtForSignedUrlForUserModel(
      boolean isOnPremiseServer, Long userId, long expiry, TimeUnit expiryTimeUnit)
      throws IOException {
    if (expiry <= 0) throw new IllegalArgumentException("Expiry cannot be less than or equal to 0");
    String subject =
        mapper.writeValueAsString(
            new UserIdModel(userId, isOnPremiseServer));
    JwtBuilder builder = Jwts.builder().setId(UUID.randomUUID().toString())
        .setIssuedAt(new Date())
        .setSubject(subject)
        .setIssuer("Digital Signage Server")
        .signWith(SignatureAlgorithm.HS512, SECRET_IN_BYTE_ARRAY);

    long expMillis;
    expMillis = System.currentTimeMillis() + expiryTimeUnit.toMillis(expiry);
    Date exp = new Date(expMillis);
    builder.setExpiration(exp);
    return builder.compact();
  }

  public static UserIdModel decryptCompatJwtForSignedUrlForUserModel(String jwtToken)
      throws InvalidJwtException {
    try {
      String subject =
          Jwts.parser()
              .setSigningKey(SECRET_IN_BYTE_ARRAY)
              .parseClaimsJws(jwtToken)
              .getBody().getSubject();
      if (subject == null) throw new InvalidJwtException();
      return mapper.readValue(subject, UserIdModel.class);
    } catch (Exception e) {
      throw new InvalidJwtException(e, e instanceof ExpiredJwtException);
    }
  }

  public static CustomerIdMediaDetailIdModel decryptCompatJwtForSignedUrl(String jwtToken)
      throws InvalidJwtException {
    try {
      String subject = Jwts.parser()
          .setSigningKey(SECRET_IN_BYTE_ARRAY)
          .parseClaimsJws(jwtToken)
          .getBody().getSubject();
      if (subject == null) throw new InvalidJwtException();
      return mapper.readValue(subject, CustomerIdMediaDetailIdModel.class);
    } catch (Exception e) {
      throw new InvalidJwtException(e, e instanceof ExpiredJwtException);
    }
  }

  public static class BuildDownloadInfoModel {
    public final Long deviceId;
    public final Long userId;
    public final boolean isOnPremiseServer;

    public BuildDownloadInfoModel() {
      // constructor required for jackson parsing
      this.deviceId = null;
      this.userId = null;
      this.isOnPremiseServer = false;
    }

    public BuildDownloadInfoModel(
        Long deviceId,
        boolean isOnPremiseServer,
        Long userId
    ) {
      this.deviceId = deviceId;
      this.userId = userId;
      this.isOnPremiseServer = isOnPremiseServer;
    }
  }

  public static class CustomerIdMediaDetailIdModel {
    public Long customerId;
    public Long mediaDetailId;
    public Long userId;
    public Long deviceId;
    public String name;
    public String fileExtension;
    public String nameOfFileOnDisk;

    public CustomerIdMediaDetailIdModel() {
      // constructor required for jackson parsing
    }

    public CustomerIdMediaDetailIdModel(Long customerId, Long mediaDetailId, Long userId, Long deviceId,
                                        String name, String fileExtension, String nameOfFileOnDisk) {
      this.customerId = customerId;
      this.mediaDetailId = mediaDetailId;
      this.userId = userId;
      this.deviceId = deviceId;
      this.name = name;
      this.fileExtension = fileExtension;
      this.nameOfFileOnDisk = nameOfFileOnDisk;
    }

    public Long getCustomerId() {
      return customerId;
    }

    public String getNameOfFileOnDisk() {
      return nameOfFileOnDisk;
    }

    public void setCustomerId(Long customerId) {
      this.customerId = customerId;
    }

    public Long getMediaDetailId() {
      return mediaDetailId;
    }

    public void setMediaDetailId(Long mediaDetailId) {
      this.mediaDetailId = mediaDetailId;
    }

    public Long getUserId() {
      return userId;
    }

    public void setUserId(Long userId) {
      this.userId = userId;
    }

    public Long getDeviceId() {
      return deviceId;
    }

    public void setDeviceId(Long deviceId) {
      this.deviceId = deviceId;
    }

    public String getName() {
      return name;
    }

    public String getFileExtension() {
      return fileExtension;
    }
  }

  public static class UserIdModel {

    public Long userId;

    public Boolean isOnPremiseServer;

    public UserIdModel() {
      // constructor required for jackson parsing
    }

    public UserIdModel(Long userId, Boolean isOnPremiseServer) {

      this.userId = userId;
      this.isOnPremiseServer = isOnPremiseServer;
    }

    public Long getUserId() {
      return userId;
    }

    public void setUserId(Long userId) {
      this.userId = userId;
    }

    public Boolean getOnPremiseServer() {
      return isOnPremiseServer;
    }

    public void setOnPremiseServer(Boolean onPremiseServer) {
      isOnPremiseServer = onPremiseServer;
    }
  }
}
