package com.example.autosalone.models.car.controller;

import com.example.autosalone.models.car.converters.CarDtoConverter;
import com.example.autosalone.models.car.dto.CarDto;
import com.example.autosalone.models.car.dto.CarSearchFilterDto;
import com.example.autosalone.models.car.dto.CreateCarRequest;
import com.example.autosalone.models.car.service.CarService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/cars")
@RequiredArgsConstructor
public class CarController {


    private final CarService carService;
    private final CarDtoConverter carDtoConverter;

    @PostMapping
    public ResponseEntity<CarDto> createCar(
            @RequestBody CreateCarRequest request
    ) {
        log.info("Creating car {}", request);

        var car = carDtoConverter.toDomainCreateRequest(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(carDtoConverter.toDto(
                        carService.createCar(car)
                ));
    }

    @GetMapping("/{model}")
    public ResponseEntity<CarDto> getCarByModel(
            @PathVariable String model
    ) {
        return ResponseEntity
                .ok(carDtoConverter.toDto(
                        carService.findCarByModel(model)
                ));
    }

    @GetMapping
    public ResponseEntity<List<CarDto>> searchCars(
            @Valid CarSearchFilterDto carSearchFilterDto
    ) {
        log.info("Get request for get cars with filter {}", carSearchFilterDto);

        return ResponseEntity
                .ok(
                        carService.searchCars(carSearchFilterDto)
                                .stream()
                                .map(carDtoConverter::toDto)
                                .toList()
                );
    }

    public ResponseEntity<CarDto> updateCarById() {

    }
}
