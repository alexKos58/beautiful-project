package com.example.security.service;

import com.example.security.dto.request.CategoryRequestDto;
import com.example.security.dto.response.CategoryResponseDto;
import com.example.security.entity.Category;
import com.example.security.mapper.CategoryMapper;
import com.example.security.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor
@Transactional
public class CategoryService {

    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;


    @Transactional(readOnly = true)
    public List<CategoryResponseDto> findAll() {
        return categoryMapper.toResponseList(categoryRepository.findAll());
    }

    @Transactional(readOnly = true)
    public CategoryResponseDto findCategory(String name) {
        return categoryMapper.toResponseDto(categoryRepository.findByName(name));
    }

    public void createCategories() {
        List<Category> categories = new ArrayList<>();
        if (categoryRepository.findByName("Мебель") == null) {
            categories.add(Category.builder().name("Мебель").build());
        }
        if (categoryRepository.findByName("Бытовая техника") == null) {
            categories.add(Category.builder().name("Бытовая техника").build());
        }
        if (categoryRepository.findByName("Одежда") == null) {
            categories.add(Category.builder().name("Одежда").build());
        }
        if (categoryRepository.findByName("Продукты") == null) {
            categories.add(Category.builder().name("Продукты").build());
        }
        if (categories.size() > 0) {
            categoryRepository.saveAll(categories);
        }
    }

    public CategoryResponseDto addCategory(CategoryRequestDto requestDto){
        Category category = categoryMapper.toCategory(requestDto);
        categoryRepository.save(category);
        return categoryMapper.toResponseDto(category);
    }

    public void deleteCategory(int id){
            categoryRepository.deleteById(id);
    }
}
