package com.example.security.config;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.errors.MinioException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Getter
@Setter
@AllArgsConstructor
@Configuration
@Slf4j
public class S3Config {
    private final S3Settings s3Settings;

    public static final String BUCKET_NAME = "test";

    @SneakyThrows
    @Bean(name = BUCKET_NAME)
    public MinioClient security() {
        return createClient();
    }

    private MinioClient createClient() throws IOException, NoSuchAlgorithmException, InvalidKeyException {
        var bucketSettings = s3Settings.getBucketMap().get(BUCKET_NAME);

        MinioClient minioClient = MinioClient.builder()
                .endpoint(s3Settings.getUrl())
                .credentials(bucketSettings.getAccess(), bucketSettings.getSecret())
                .build();
        createBucket(minioClient);
        return minioClient;
    }

    private void createBucket(MinioClient minioClient) throws IOException, NoSuchAlgorithmException, InvalidKeyException {
        try {
            boolean found = minioClient.bucketExists(BucketExistsArgs.builder()
                    .bucket(BUCKET_NAME)
                    .build());

            if (!found) {
                minioClient.makeBucket(MakeBucketArgs.builder()
                        .bucket(BUCKET_NAME)
                        .build());
            } else {
                log.info("Bucket 'test' already exists.");
            }

        } catch (MinioException ex) {
            log.error(ex.getMessage());
        }
    }
}
