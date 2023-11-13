package com.example.security.controller.dto.response;

import com.example.security.domain.enums.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonResponseDto {

    @Schema(description = "Логин")
    private String login;

    @Schema(description = "Пароль")
    private String password;

    @Schema(description = "Роль")
    private Role role;
}
