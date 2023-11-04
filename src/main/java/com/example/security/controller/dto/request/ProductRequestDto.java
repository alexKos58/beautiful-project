package com.example.security.controller.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRequestDto {

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
