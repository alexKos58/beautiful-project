package com.example.security.controller.dto.response;

import com.example.security.domain.enums.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDto {

    @Schema(description = "Идентификатор заказа")
    private int orderId;

    @Schema(description = "Номер заказа")
    private String orderNumber;

    @Schema(description = "Цена")
    private float orderPrice;

    @Schema(description = "Статус заказа")
    private Status status;

    @Schema(description = "Заказчик")
    private int personId;

    @Schema(description = "Продукт")
    private int productId;
}
