package com.example.autosalone.models.car.dto;

import jakarta.validation.constraints.Min;

import java.math.BigDecimal;

public record CarSearchFilterDto(
        String brand,
        String model,
        BigDecimal maxPrice,

        @Min(0)
        Integer pageNumber,

        @Min(3)
        Integer pageSize
) {
}
