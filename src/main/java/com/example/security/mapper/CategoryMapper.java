package com.example.security.mapper;

import com.example.security.dto.request.CategoryRequestDto;
import com.example.security.dto.response.CategoryResponseDto;
import com.example.security.entity.Category;
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
