package com.example.security.controller.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDto {

    @Schema(description = "Id товара")
    private int productId;

    @Schema(description = "Наименование товара")
    private String productTitle;

    @Schema(description = "Описание товара")
    private String productDescription;

    @Schema(description = "Цена товара")
    private float price;

    @Schema(description = "Категория товара")
    private int category;

    @Schema(description = "Склад хранения товара")
    private String warehouse;

    @Schema(description = "Продавец товара")
    private String seller;
}
