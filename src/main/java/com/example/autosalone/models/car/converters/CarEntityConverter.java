package com.example.autosalone.models.car.converters;

import com.example.autosalone.models.car.Car;
import com.example.autosalone.models.car.CarEntity;
import com.example.autosalone.models.car.CarStatus;
import com.example.autosalone.models.car.dto.CarDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CarEntityConverter {

    public CarEntity toEntity(Car car) {
        return new CarEntity(
                car.id(),
                car.brand(),
                car.model(),
                car.salePrice(),
                car.rentPricePerDay(),
                car.color(),
                car.year(),
                car.status()
        );
    }

    public Car toDomain(CarEntity carEntity) {
        return new Car(
                carEntity.getId(),
                carEntity.getBrand(),
                carEntity.getModel(),
                carEntity.getColor(),
                carEntity.getYear(),
                carEntity.getSalePrice(),
                carEntity.getRentPricePerDay(),
                carEntity.getStatus()
        );
    }
}
