package com.example.autosalone.models.car;

import com.example.autosalone.models.deal.Deal;

import java.math.BigDecimal;
import java.util.List;

public record Car(
        Long id,
        String brand,
        String model,
        String color,
        Integer year,
        BigDecimal price,
        CarStatus status
) {
}
