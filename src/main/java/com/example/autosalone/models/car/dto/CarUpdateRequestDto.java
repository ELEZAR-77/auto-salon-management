package com.example.autosalone.models.car.dto;

import java.math.BigDecimal;

public record CarUpdateRequestDto(
        String brand,
        String model,
        BigDecimal price
) {
}
