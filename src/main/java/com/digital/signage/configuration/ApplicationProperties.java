package com.digital.signage.configuration;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@Getter
@Setter
@ToString
@ConfigurationProperties(prefix = "application")
public class ApplicationProperties {
    private String pdnCmsUrl;
    private Server serverConfig;
    private Set<String> serverAndAngularHosts;
    private String serverBaseUrlForAngularDownloadLinks;
    private String rsaPrivateKeyDirectory;
    private String storageDirectory;
    private CustomerType customerType;
    private boolean demographicEnabled;

    private boolean advertisementEnabled;

    private String version;

    private Tenant tenant;

    private QMS qms;

    private Directory directory;
    private Mail mail;
    private String baseUrlForEmail = null;

    @Setter
    @Getter
    public static class Directory {
        private String emailTemplate;
        private String mailImages;
    }

    @Setter
    @Getter
    public static class Mail {
        private Boolean enableTlsOnEmail = true;
        private String sesRegion = "us-east-1";
    }

    @Setter
    @Getter
    public static class Server {
        private S3 s3;
        private Config serviceGateway;


        @Setter
        @Getter
        public static class Config {
            private String baseUrl;
            private URI uri;
            private String accept;
            private long responseTimeout;
            private int connectionTimeout;
            private long readTimeout;
            private long writeTimeout;
            private int retryCount;


            @Setter
            @Getter
            public static class URI {
                private String saveLicenseInfo;
                private String editLicenseInfo;
                private String customerLicenseInfo;
                private String getUserInfo;
                private String updateUserInfo;
                private String searchLicenseInfo;
                private String createPointOfContactUser;
            }
        }

        @Setter
        @Getter
        public static class S3 {
            private boolean enabled;
            private String bucketName;
            private String region;
            private String accessKey;
            private String secretAccessKey;
            private long preSignedUrlExpirationSeconds;
        }
    }

    @Getter
    @Setter
    public static class QMS {
        private String adminUrl;
    }

    @Getter
    @Setter
    public static class Tenant {
        private String schemaFilePath;
        private int port;
        private String hostName;
    }

    @Getter
    @Setter
    public static class CustomerType {
        private Basic basic;

        @Getter
        @Setter
        public static class Basic {
            private Long maxLicense;
        }
    }
}
