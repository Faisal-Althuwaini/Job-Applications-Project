package com.wakeb.jobsapplication.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "aws")
public class AwsS3ConfigProperties {
    private String region;
    private String bucketName;
    private String accessKeyId;
    private String secretAccessKey;
}
