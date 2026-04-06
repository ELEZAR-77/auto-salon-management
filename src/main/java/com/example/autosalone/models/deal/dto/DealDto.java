package com.example.autosalone.models.deal.dto;

import java.time.LocalDate;

public record DealDto(
        Long id,
        Long carId,
        Long userId,
        LocalDate date
) {
}
