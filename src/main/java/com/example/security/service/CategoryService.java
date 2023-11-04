package com.example.security.service;

import com.example.security.controller.dto.request.CategoryRequestDto;
import com.example.security.controller.dto.response.CategoryResponseDto;

import java.util.List;

public interface CategoryService {

    List<CategoryResponseDto> findAll();

    CategoryResponseDto findCategory(String name);

    void createCategories();

    CategoryResponseDto addCategory(CategoryRequestDto requestDto);

    void deleteCategory(int id);
}
