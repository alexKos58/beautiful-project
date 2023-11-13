package com.example.security.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@Getter
@Setter
@ConfigurationProperties(prefix = "app.minio")
public class S3Settings {

    private String url;
    private Map<String, Bucket> bucketMap = new HashMap<>();

    @Getter
    @Setter
    public static class Bucket{

        private String name;
        private String access;
        private String secret;
    }
}
