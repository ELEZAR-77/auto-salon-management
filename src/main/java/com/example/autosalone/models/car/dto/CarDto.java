package com.example.autosalone.models.car.dto;

import com.example.autosalone.models.deal.DealDto;

import java.math.BigDecimal;
import java.util.List;

public record CarDto(
        Long id,
        String brand,
        String model,
        String color,
        Integer year,
        BigDecimal price
) {
}
