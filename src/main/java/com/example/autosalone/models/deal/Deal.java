package com.example.autosalone.models.deal;

import com.example.autosalone.models.car.Car;
import com.example.autosalone.models.user.User;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;

public record Deal(
        Long id,
        Car car,
        User user,
        DealType type,
        LocalDate date
) {
}
