package com.example.security.controller;

import com.example.security.service.ImageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("${myapp.api.base-url}/images")
@Tag(name = "Контроллер изображений", description = "Работают все эндпоинты")
public class ImageController {

    private final ImageService imageService;

    @PostMapping("/add-image/{id}")
    @Operation(summary = "Добавить фотографию продукта")
    @ResponseStatus(HttpStatus.CREATED)
    public int addImage(
            @Parameter(in = ParameterIn.PATH, name = "id", description = "id продукта") @PathVariable int id) {
        return imageService.addImage(id);
    }
}
