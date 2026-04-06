package com.example.autosalone.models.car.dto;

import java.math.BigDecimal;

public record CreateCarRequest(
        String brand,
        String model,
        String color,
        Integer year,
        BigDecimal price
) {
}
