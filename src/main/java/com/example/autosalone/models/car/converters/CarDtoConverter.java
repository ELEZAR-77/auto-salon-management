package com.example.autosalone.models.car.converters;

import com.example.autosalone.models.car.Car;
import com.example.autosalone.models.car.CarStatus;
import com.example.autosalone.models.car.dto.CarDto;
import com.example.autosalone.models.car.dto.CreateCarRequest;
import com.example.autosalone.models.deal.converters.DealDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CarDtoConverter {

    public CarDto toDto(Car car) {
        return new CarDto(
                car.id(),
                car.brand(),
                car.model(),
                car.color(),
                car.year(),
                car.salePrice(),
                car.rentPricePerDay(),
                car.status()
        );
    }

    public Car toDomainCreateRequest(CreateCarRequest carRequest) {
        return new Car(
                null,
                carRequest.brand(),
                carRequest.model(),
                carRequest.color(),
                carRequest.year(),
                carRequest.salePrice(),
                carRequest.rentPricePerDay(),
                CarStatus.AVAILABLE
        );
    }
}
