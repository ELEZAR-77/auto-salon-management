package com.example.autosalone.models.deal.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

import java.time.LocalDate;

public record DealRentUpdateDto(
        @Null
        Long carId,

        @NotNull
        LocalDate endDate
) {
}
