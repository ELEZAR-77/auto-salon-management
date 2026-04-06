package com.example.autosalone.models.deal;

import com.example.autosalone.models.deal.enums.DealType;

import java.time.LocalDate;

public record Deal(
        Long id,
        Long carId,
        Long userId,
        DealType type,
        LocalDate date
) {
}
