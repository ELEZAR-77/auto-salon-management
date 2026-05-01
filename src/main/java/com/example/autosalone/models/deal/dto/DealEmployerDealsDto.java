package com.example.autosalone.models.deal.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.math.BigDecimal;
import java.time.LocalDate;

@JsonPropertyOrder({
        "dealId",
        "dealType",
        "dealStatus",
        "dealDate",
        "dealStartDate",
        "dealEndDate",
        "dealTotalPrice",
        "userId",
        "carId",
        "brand",
        "model",
        "price",
        "rentPricePerDay",
        "color",
        "year",
        "status"
})
public interface DealEmployerDealsDto {
    Long getDealId();
    String getDealType();
    String getDealStatus();
    LocalDate getDealDate();
    LocalDate getStartDate();
    LocalDate getDealEndDate();
    BigDecimal getDealTotalPrice();

    Long getUserId();

    Long getCarId();
    String getBrand();
    String getModel();
    BigDecimal getPrice();
    BigDecimal getRentPricePerDay();
    String getColor();
    Integer getYear();
    String getStatus();
}
