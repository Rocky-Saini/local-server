package com.digital.signage.report.S3;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.digital.signage.configuration.ApplicationProperties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

@Configuration
public class S3Config {
    private final ApplicationProperties properties;
//
    public S3Config(ApplicationProperties properties) {
        this.properties = properties;
    }

    @Bean
    public AmazonS3 s3client() {
        if(!properties.getServerConfig().getS3().isEnabled())
            return null;

        AWSCredentials awsCredentials = new BasicAWSCredentials(
                properties.getServerConfig().getS3().getAccessKey(),
                properties.getServerConfig().getS3().getSecretAccessKey()
        );
        String region = properties.getServerConfig().getS3().getRegion();
        Regions regions = null;
        if(StringUtils.isNotBlank(region) && StringUtils.isNotBlank(region.trim()))
            regions = Regions.fromName(region.trim());

        return AmazonS3ClientBuilder.standard()
                .withRegion(Objects.isNull(regions) ?  Regions.AP_SOUTH_1 : regions)
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();
    }
}
