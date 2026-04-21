package com.example.autosalone.models.deal.dto;

import java.time.LocalDate;

public record DealRentRequestDto(
    Long carId,
    LocalDate startDate,
    LocalDate endDate
) {
}
