package com.example.autosalone.models.car;

import java.math.BigDecimal;

public record Car(
        Long id,
        String brand,
        String model,
        BigDecimal price,
        CarStatus status
) {
}
