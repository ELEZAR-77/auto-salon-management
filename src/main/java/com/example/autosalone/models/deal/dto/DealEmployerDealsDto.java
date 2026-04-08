package com.example.autosalone.models.deal.dto;

import com.example.autosalone.models.car.Car;
import com.example.autosalone.models.car.CarStatus;
import com.example.autosalone.models.car.dto.CarDto;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.math.BigDecimal;
import java.time.LocalDate;

@JsonPropertyOrder({
        "dealId",
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
    Long getUserId();

    Long getCarId();
    String getBrand();
    String getModel();
    BigDecimal getPrice();
    String getColor();
    Integer getYear();
    String getStatus();
}
