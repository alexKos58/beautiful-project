package com.example.security.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface PhotoUploader {
    CompletableFuture<UUID> uploadPhotoAsync(int productId, String fileName, MultipartFile content);

    byte[] getFile(String fileName);
}