package com.example.security.mapper;

import com.example.security.controller.dto.request.ProductRequestDto;
import com.example.security.controller.dto.response.ProductResponseDto;
import com.example.security.domain.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "title", source = "productTitle")
    @Mapping(target = "description", source = "productDescription")
    @Mapping(target = "category.id", source = "category")
    Product toProduct(ProductRequestDto requestDto);

    @Mapping(source = "id", target = "productId")
    @Mapping(source = "title", target = "productTitle")
    @Mapping(source = "description", target = "productDescription")
    @Mapping(source = "category.id", target = "category")
    ProductResponseDto toProductResponseDto(Product product);

    List<ProductResponseDto> toResponseList(List<Product> productList);
}
