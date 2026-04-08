package com.example.autosalone.models.car.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record CreateCarRequest(

        @Size(min = 3, max = 100)
        @NotBlank
        String brand,

        @Size(min = 3, max = 100)
        @NotBlank
        String model,

        @Size(min = 3, max = 100)
        @NotBlank
        String color,

        @Min(1885)
        @NotNull
        Integer year,

        @Positive
        @NotNull
        BigDecimal price
) {
}
