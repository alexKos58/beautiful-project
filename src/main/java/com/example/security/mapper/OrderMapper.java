package com.example.security.mapper;

import com.example.security.controller.dto.response.OrderResponseDto;
import com.example.security.domain.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(target = "orderId", source = "id")
    @Mapping(target = "orderNumber", source = "number")
    @Mapping(target = "orderPrice", source = "price")
    @Mapping(target = "personId", source = "person.id")
    @Mapping(target = "productId", source = "product.id")
    OrderResponseDto toResponseDto(Order order);

    List<OrderResponseDto> toResponseList(List<Order> orderList);
}
