package com.example.autosalone.models.deal.dto;

import java.math.BigDecimal;

public record DealStatsShowDto (
        Integer dealCount,
        Integer saleCount,
        Integer rentCount,
        BigDecimal totalIncome,
        BigDecimal saleIncome,
        BigDecimal rentIncome,
        Integer activeRents
) {
}
