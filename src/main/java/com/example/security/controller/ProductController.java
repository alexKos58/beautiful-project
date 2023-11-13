package com.example.security.controller;

import com.example.security.controller.dto.request.ProductRequestDto;
import com.example.security.controller.dto.response.ProductResponseDto;
import com.example.security.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("${myapp.api.base-url}/product")
@Tag(name = "Контроллер продуктов", description = "Работают все эндпоинты")
public class ProductController {
    private final ProductService productService;

    @GetMapping
    @Operation(summary = "Получение списка продуктов")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponseDto> getAllProducts() {
        return productService.getAllProduct();
    }

    @PostMapping("/add")
    @Operation(summary = "Добавление нового продукта")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponseDto addProduct(
            @Parameter(description = "Добавляемый продукт") @Valid @RequestBody ProductRequestDto requestDto) {
        return productService.addProduct(requestDto);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Удаление продукта")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable("id") int id) {
        productService.deleteProduct(id);
    }

    @PutMapping("/edit/{id}")
    @Operation(summary = "Изменение продукта")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponseDto editProduct(
            @Parameter(in = ParameterIn.PATH, name = "id", description = "id продукта") @PathVariable int id,
            @Parameter(description = "Обновленный продукт") @Valid @RequestBody ProductRequestDto requestDto) {
        return productService.updateProduct(id, requestDto);
    }
}
