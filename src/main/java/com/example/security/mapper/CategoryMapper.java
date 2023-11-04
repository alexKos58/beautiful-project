package com.example.security.mapper;

import com.example.security.controller.dto.request.CategoryRequestDto;
import com.example.security.controller.dto.response.CategoryResponseDto;
import com.example.security.domain.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(target = "name", source = "categoryName")
    Category toCategory(CategoryRequestDto requestDto);

    @Mapping(target = "categoryId", source = "id")
    @Mapping(target = "categoryName", source = "name")
    CategoryResponseDto toResponseDto(Category category);

    List<CategoryResponseDto> toResponseList(List<Category> categoryList);
}
