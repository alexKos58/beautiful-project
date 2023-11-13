package com.example.security.controller;

import com.example.security.service.ImageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@AllArgsConstructor
@RequestMapping("${myapp.api.base-url}/images")
@Tag(name = "Контроллер изображений", description = "Работают все эндпоинты")
public class ImageController {

    private final ImageService imageService;

    @PostMapping(value = "/add-image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Добавить фотографию продукта")
    @ResponseStatus(HttpStatus.CREATED)
    public CompletableFuture<UUID> addImage(
            @Parameter(description = "Id продукта") @RequestParam int productId,
            @Parameter(description = "Наименование файла") @RequestParam String fileName,
            @Parameter(description = "Картинка товара") @ModelAttribute MultipartFile content
    ) {
        return imageService.addImage(productId, fileName, content);
    }

    @GetMapping
    @Operation(summary = "Получить изображение")
    @ResponseStatus(HttpStatus.OK)
    public byte[] getImage(String fileName) {
        return imageService.getFileFromMinio(fileName);
    }
}