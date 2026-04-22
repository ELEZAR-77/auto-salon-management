package com.example.autosalone.models.car.service;

import com.example.autosalone.models.car.Car;
import com.example.autosalone.models.car.CarEntity;
import com.example.autosalone.models.car.CarStatus;
import com.example.autosalone.models.car.converters.CarEntityConverter;
import com.example.autosalone.models.car.dto.CarDto;
import com.example.autosalone.models.car.dto.CarSearchFilterDto;
import com.example.autosalone.models.car.dto.CarUpdateRequestDto;
import com.example.autosalone.models.car.dto.CreateCarRequest;
import com.example.autosalone.models.car.exception.CarExistException;
import com.example.autosalone.models.car.exception.CarModelNotFound;
import com.example.autosalone.models.car.exception.CarNotExistException;
import com.example.autosalone.models.car.repository.CarRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;
    private final CarEntityConverter carEntityConverter;

    public Car createCar(Car car) {

        if (carRepository.existsByModel(car.model())) {
            throw new CarExistException("Car Model " + car.model() + " does exist");
        }

        var entity =  carEntityConverter.toEntity(car);

        carRepository.save(entity);

        return carEntityConverter.toDomain(entity);
    }

    public Car findCarByModel(String model) {
        if (!carRepository.existsByModel(model)) {
            throw new CarNotExistException("Car Model " + model + " does NOT exist");
        }

        return carEntityConverter.toDomain(
                carRepository.findByModel(model)
        );
    }

    public List<Car> searchCars(CarSearchFilterDto carSearchFilterDto) {

        int pageNumber = carSearchFilterDto.pageNumber() != null
                ? carSearchFilterDto.pageNumber()
                : 0;

        int pageSize = carSearchFilterDto.pageSize() != null
                ? carSearchFilterDto.pageSize()
                : 3;

        Pageable pageable = Pageable
                .ofSize(pageSize)
                .withPage(pageNumber);

        return carRepository.searchCars(
                carSearchFilterDto.brand(),
                carSearchFilterDto.model(),
                carSearchFilterDto.maxPrice(),

                pageable
                )
                    .stream()
                    .map(carEntityConverter::toDomain)
                    .toList();
    }

    @Transactional
    public Car updateCar(Long id, CarUpdateRequestDto updateRequest) {
        CarEntity entityToUpdate = carRepository.findById(id).orElseThrow(
                () -> new CarNotExistException("Car ID " + id + " does NOT exist")
        );

        if (updateRequest.brand() != null) {
            entityToUpdate.setBrand(updateRequest.brand());
        }
        if (updateRequest.model() != null) {
            entityToUpdate.setModel(updateRequest.model());
        }
        if (updateRequest.price() != null) {
            entityToUpdate.setSalePrice(updateRequest.price());
        }

        return carEntityConverter.toDomain(entityToUpdate);
    }

    @Transactional
    public void deleteCarById(Long id) {
        CarEntity entityToDelete = carRepository.findById(id).orElseThrow(
                () -> new CarNotExistException("Car ID " + id + " does NOT exist")
        );

        carRepository.delete(entityToDelete);
    }
}
