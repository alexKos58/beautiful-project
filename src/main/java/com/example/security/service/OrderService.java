package com.example.security.service;

import com.example.security.controller.dto.response.OrderResponseDto;
import com.example.security.domain.enums.Status;

import java.util.List;

public interface OrderService {

    OrderResponseDto editOrderStatus(int id, Status status);

    OrderResponseDto searchOrder(String number);

    List<OrderResponseDto> getAllOrders();
}
