package com.example.security.controller;

import com.example.security.controller.dto.request.CategoryRequestDto;
import com.example.security.controller.dto.response.CategoryResponseDto;
import com.example.security.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("${myapp.api.base-url}/category")
@Tag(name = "Контроллер категорий", description = "Работают все эндпоинты")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    @Operation(summary = "Получение списка категорий")
    @ResponseStatus(HttpStatus.OK)
    public List<CategoryResponseDto> getListOfCategories() {
        return categoryService.findAll();
    }

    @PostMapping("/base-categories")
    @Operation(summary = "Добавление базовых категорий в БД")
    @ResponseStatus(HttpStatus.CREATED)
    public void addBaseCategories() {
        categoryService.createCategories();
    }

    @PostMapping("/add")
    @Operation(summary = "Добавить категорию товара")
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryResponseDto addCategory(
            @Parameter(description = "Добавляемая категория") @Valid @RequestBody CategoryRequestDto requestDto) {
        return categoryService.addCategory(requestDto);
    }

    @GetMapping("/search/{name}")
    @Operation(summary = "Найти категорию товара по имени")
    @ResponseStatus(HttpStatus.FOUND)
    public CategoryResponseDto findCategory(
            @Parameter(in = ParameterIn.PATH, name = "name", description = "Наименование категории") @PathVariable String name) {
        return categoryService.findCategory(name);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Удалить категорию")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(
            @Parameter(in = ParameterIn.PATH, name = "id", description = "Идентификатор категории") @PathVariable int id) {
        categoryService.deleteCategory(id);
    }
}
