package com.example.security.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Status {
    Принят(0),
    Оформлен(1),
    Ожидает(2),
    Получен(3);

    private final int order;
}
