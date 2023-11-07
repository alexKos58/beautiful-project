package com.example.security.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.concurrent.CompletableFuture;

public interface PhotoUploader {
    CompletableFuture<Integer> uploadPhotoAsync(int productId, String fileName, MultipartFile content);
}
