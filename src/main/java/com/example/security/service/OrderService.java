package com.example.security.service;

import com.example.security.controller.dto.response.OrderResponseDto;

import java.util.List;

public interface OrderService {

    OrderResponseDto editOrderStatus(int id);

    OrderResponseDto searchOrder(String number);

    List<OrderResponseDto> getAllOrders();
}