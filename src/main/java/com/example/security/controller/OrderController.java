package com.example.security.controller;

import com.example.security.controller.dto.response.OrderResponseDto;
import com.example.security.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("${myapp.api.base-url}/order")
@Tag(name = "Контроллер заказов", description = "Работают все эндпоинты")
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/edit/{id}")
    @Operation(summary = "Изменение статуса заказа")
    @ResponseStatus(HttpStatus.OK)
    public OrderResponseDto editOrderStatus(
            @Parameter(in = ParameterIn.PATH, name = "id", description = "id заказа") @PathVariable int id) {
        return orderService.editOrderStatus(id);
    }


    @GetMapping("/search")
    @Operation(summary = "Поиск заказа")
    @ResponseStatus(HttpStatus.OK)
    public OrderResponseDto searchOrder(
            @Parameter(description = "Номер заказа") @RequestParam("number") String number) {
        return orderService.searchOrder(number);
    }

    @GetMapping("/orders")
    @Operation(summary = "Получение списка заказов")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderResponseDto> getOrders() {
        return orderService.getAllOrders();
    }
}
