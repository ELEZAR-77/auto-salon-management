package com.example.autosalone.models.deal;

import com.example.autosalone.models.deal.enums.DealStatus;
import com.example.autosalone.models.deal.enums.DealType;

import java.math.BigDecimal;
import java.time.LocalDate;

public record Deal(
        Long id,
        Long carId,
        Long userId,
        DealType type,
        DealStatus status,
        LocalDate date,
        BigDecimal totalPrice
) {
}
