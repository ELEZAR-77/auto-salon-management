package com.example.autosalone.models.deal.dto;

import java.math.BigDecimal;

public interface DealStatsProjection {
    Long getDealCount();
    Long getSaleCount();
    Long getRentCount();
    BigDecimal getTotalIncome();
    Long getActiveRents();
}
