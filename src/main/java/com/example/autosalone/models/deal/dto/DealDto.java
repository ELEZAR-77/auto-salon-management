package com.example.autosalone.models.deal.dto;

import com.example.autosalone.models.deal.enums.DealStatus;
import com.example.autosalone.models.deal.enums.DealType;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DealDto(
        Long id,
        Long carId,
        Long userId,
        LocalDate date,
        DealType dealType,
        DealStatus dealStatus,
        BigDecimal totalPrice
) {
}
