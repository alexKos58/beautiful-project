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
public class CategoryResponseDto {

    @Schema(description = "Id категории")
    private int categoryId;

    @Schema(description = "Наименование категории")
    private String categoryName;
}
