package com.example.security.service.impl;

import com.example.security.domain.entity.Image;
import com.example.security.domain.repository.ImageRepository;
import com.example.security.domain.repository.ProductRepository;
import com.example.security.service.PhotoUploader;
import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.StatObjectResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.util.Map;
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

    public CompletableFuture<Integer> uploadPhotoAsync(int productId, String fileName, MultipartFile content) {
        Image image = new Image();
        image.setProduct(productRepository.findById(productId).orElseThrow());
        image.setFileName(fileName);
        imageRepository.save(image);

        CompletableFuture<Integer> future = CompletableFuture.completedFuture(image.getId());

        // Асинхронно загружаем файл в Минио
        CompletableFuture.runAsync(() -> {
            try {
                MinioClient minioClient = minioClientMap.get(BUCKET_NAME);
                minioClient.putObject(PutObjectArgs.builder()
                        .bucket(BUCKET_NAME)
                        .object(fileName)
                        .stream(new ByteArrayInputStream(content.getBytes()), content.getBytes().length, -1)
                        .build());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }, executor);

        return future;
    }

    public byte[] getFile(String fileName) {
        try {
            MinioClient minioClient = minioClientMap.get(BUCKET_NAME);


            // Получаем содержимое файла
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