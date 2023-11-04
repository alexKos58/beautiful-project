package com.example.security.service.impl;

import com.example.security.controller.dto.response.OrderResponseDto;
import com.example.security.domain.entity.Order;
import com.example.security.domain.enums.Status;
import com.example.security.mapper.OrderMapper;
import com.example.security.domain.repository.OrderRepository;
import com.example.security.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    private final OrderMapper orderMapper;


    @Transactional
    public OrderResponseDto editOrderStatus(int id, Status status) {
        Order order = orderRepository.findById(id).orElseThrow();
        order.setStatus(status);
        return orderMapper.toResponseDto(order);
    }

    public OrderResponseDto searchOrder(String number) {
        Order order = orderRepository.findByNumber(number);
        if (order != null) {
            return orderMapper.toResponseDto(order);
        } else return null;
    }

    public List<OrderResponseDto> getAllOrders(){
        return orderMapper.toResponseList(orderRepository.findAll());
    }
}
