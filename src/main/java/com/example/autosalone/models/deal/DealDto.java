package com.example.autosalone.models.deal;

import com.example.autosalone.models.car.Car;
import com.example.autosalone.models.user.User;

import java.time.LocalDate;

public record DealDto(
        Long id,
        Car car,
        User user,
        LocalDate date
) {
}
