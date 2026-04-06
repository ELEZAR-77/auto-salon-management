package com.example.autosalone.models.car.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record CreateCarRequest(

        @Min(3) @Max(100) @NotBlank
        String brand,

        @Min(3) @Max(100) @NotBlank
        String model,

        @Min(3) @Max(100) @NotBlank
        String color,

        @Min(1885) @NotNull
        Integer year,

        @Positive @NotNull
        BigDecimal price
) {
}
