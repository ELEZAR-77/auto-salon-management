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
        "userId",
        "carId",
        "brand",
        "model",
        "price",
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

    Long getUserId();

    Long getCarId();
    String getBrand();
    String getModel();
    BigDecimal getPrice();
    String getColor();
    Integer getYear();
    String getStatus();
}
