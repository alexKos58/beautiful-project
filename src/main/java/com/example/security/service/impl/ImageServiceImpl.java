package com.example.security.service.impl;

import com.example.security.service.ImageService;
import com.example.security.service.PhotoUploader;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
@Transactional
@AllArgsConstructor
public class ImageServiceImpl implements ImageService {


    private final PhotoUploader photoUploader;

    public CompletableFuture<UUID> addImage(int productId, String fileName, MultipartFile content) {
        return photoUploader.uploadPhotoAsync(productId, fileName, content);
    }

    public byte[] getFileFromMinio(String fileName) {
        return photoUploader.getFile(fileName);
    }
}