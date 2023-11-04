package com.example.security.controller;

import com.example.security.controller.dto.response.PersonResponseDto;
import com.example.security.service.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("${myapp.api.base-url}/person")
@Tag(name = "Контроллер админа", description = "Работают все эндпоинты")
public class AdminController {

    private final PersonService personService;

    @GetMapping
    @Operation(summary = "Получение списка пользователей")
    @ResponseStatus(HttpStatus.OK)
    public List<PersonResponseDto> getPersons() {
        return personService.getAll();
    }

    @PostMapping("/edit/{id}")
    @Operation(summary = "Изменение роли пользователя")
    @ResponseStatus(HttpStatus.OK)
    public PersonResponseDto editPersonRole(
            @Parameter(in = ParameterIn.PATH, name = "id", description = "id пользователя") @PathVariable Long id) {
        return personService.editPersonRole(id);
    }

}
