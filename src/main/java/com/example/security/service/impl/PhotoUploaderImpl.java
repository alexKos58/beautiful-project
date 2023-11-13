package com.example.security.service.impl;

import com.example.security.domain.entity.Image;
import com.example.security.domain.repository.ImageRepository;
import com.example.security.domain.repository.ProductRepository;
import com.example.security.service.PhotoUploader;
import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

import static com.example.security.config.S3Config.BUCKET_NAME;

@Service
@AllArgsConstructor
@Transactional
public class PhotoUploaderImpl implements PhotoUploader {
    private final Map<String, MinioClient> minioClientMap;
    private final Executor executor;
    private final ProductRepository productRepository;
    private final ImageRepository imageRepository;

    public CompletableFuture<UUID> uploadPhotoAsync(int productId, String fileName, MultipartFile content) {
        UUID imageId = UUID.randomUUID();

        CompletableFuture<UUID> future = CompletableFuture.completedFuture(imageId);

        CompletableFuture.runAsync(() -> {
            try {
                MinioClient minioClient = minioClientMap.get(BUCKET_NAME);
                minioClient.putObject(PutObjectArgs.builder()
                        .bucket(BUCKET_NAME)
                        .object(fileName)
                        .stream(new ByteArrayInputStream(content.getBytes()), content.getBytes().length, -1)
                        .build());

                Image image = Image.builder()
                        .id(imageId)
                        .product(productRepository.findById(productId).orElseThrow())
                        .fileName(fileName)
                        .build();
                imageRepository.save(image);
            } catch (Exception e) {
                future.completeExceptionally(e);
            }
        }, executor);

        return future;
    }

    public byte[] getFile(String fileName) {
        try {
            MinioClient minioClient = minioClientMap.get(BUCKET_NAME);

            return minioClient.getObject(
                    GetObjectArgs.builder()
                            .bucket(BUCKET_NAME)
                            .object(fileName)
                            .build()
            ).readAllBytes();

        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}