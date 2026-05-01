package com.example.autosalone.models.deal.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DealRentRequestDto(
        @NotNull
        Long carId,

        @NotNull
        LocalDate startDate,

        @NotNull
        LocalDate endDate
) {
}
