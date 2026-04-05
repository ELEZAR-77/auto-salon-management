package com.example.autosalone.models.car.converters;

import com.example.autosalone.models.car.Car;
import com.example.autosalone.models.car.CarEntity;
import com.example.autosalone.models.car.CarStatus;
import com.example.autosalone.models.car.dto.CarDto;
import org.springframework.stereotype.Component;

@Component
public class CarEntityConverter {

    public CarEntity toEntity(Car car) {
        return new CarEntity(
                car.id(),
                car.brand(),
                car.model(),
                car.price(),
                car.status().name()
        );
    }

    public Car toDomain(CarEntity carEntity) {
        return new Car(
                carEntity.getId(),
                carEntity.getBrand(),
                carEntity.getModel(),
                carEntity.getPrice(),
                CarStatus.valueOf(carEntity.getStatus())
        );
    }
}
