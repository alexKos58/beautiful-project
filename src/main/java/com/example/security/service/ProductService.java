package com.example.security.service;

import com.example.security.controller.dto.request.ProductRequestDto;
import com.example.security.controller.dto.response.ProductResponseDto;

import java.util.List;

public interface ProductService {

    List<ProductResponseDto> getAllProduct();

    ProductResponseDto addProduct(ProductRequestDto requestDto);

    ProductResponseDto updateProduct(int id, ProductRequestDto requestDto);

    void deleteProduct(int id);
}
