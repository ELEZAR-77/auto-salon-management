package com.example.autosalone.models.car.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Min;

import java.math.BigDecimal;

public record CarUpdateRequestDto(
        @Nullable
        String brand,

        @Nullable
        String model,

        @Nullable
        BigDecimal price
) {
}
