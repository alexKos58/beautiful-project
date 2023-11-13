package com.example.security.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface ImageService {

    CompletableFuture<UUID> addImage(int id, String fileName, MultipartFile content);

    byte[] getFileFromMinio(String fileName);
}