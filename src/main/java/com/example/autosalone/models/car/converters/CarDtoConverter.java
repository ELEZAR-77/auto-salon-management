package com.example.autosalone.models.car.converters;

import com.example.autosalone.models.car.Car;
import com.example.autosalone.models.car.CarStatus;
import com.example.autosalone.models.car.dto.CarDto;
import com.example.autosalone.models.car.dto.CreateCarRequest;
import org.springframework.stereotype.Component;

@Component
public class CarDtoConverter {

    public CarDto toDto(Car car) {
        return new CarDto(
                car.id(),
                car.brand(),
                car.model(),
                car.price()
        );
    }

    public Car toDomain(CarDto carDto) {
        return new Car(
                carDto.id(),
                carDto.brand(),
                carDto.model(),
                carDto.price(),
                null
        );
    }

    public Car toDomainCreateRequest(CreateCarRequest carRequest) {
        return new Car(
                null,
                carRequest.brand(),
                carRequest.model(),
                carRequest.price(),
                CarStatus.AVAILABLE
        );
    }
}
