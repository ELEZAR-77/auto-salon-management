package com.example.autosalone.models.deal.serivce;

import com.example.autosalone.models.car.Car;
import com.example.autosalone.models.car.CarEntity;
import com.example.autosalone.models.car.CarStatus;
import com.example.autosalone.models.car.exception.CarNotAvailableException;
import com.example.autosalone.models.car.exception.CarNotExistException;
import com.example.autosalone.models.car.repository.CarRepository;
import com.example.autosalone.models.deal.Deal;
import com.example.autosalone.models.deal.DealEntity;
import com.example.autosalone.models.deal.converters.DealEntityConverter;
import com.example.autosalone.models.deal.dto.DealDto;
import com.example.autosalone.models.deal.dto.DealRentRequestDto;
import com.example.autosalone.models.deal.enums.DealType;
import com.example.autosalone.models.deal.reopsitory.DealRepository;
import com.example.autosalone.models.user.UserEntity;
import com.example.autosalone.models.user.UserRole;
import com.example.autosalone.models.user.exceptions.UserAccessDeniedException;
import com.example.autosalone.models.user.exceptions.UserNotFoundException;
import com.example.autosalone.models.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class DealService {

    private final DealRepository dealRepository;
    private final UserRepository userRepository;
    private final CarRepository carRepository;
    private final DealEntityConverter entityConverter;

    public Deal rentDeal(
            Long carId,
            UserDetails employee
    ) {
        UserEntity user = userRepository.findByUsername(employee.getUsername())
                .orElseThrow(() -> new UserNotFoundException("User not found: " + employee.getUsername()));

        CarEntity car = carRepository.findById(carId).orElseThrow(
                () -> new CarNotExistException("Car not found")
        );

        if (user.getRole() != UserRole.EMPLOYEE) {
            throw new UserAccessDeniedException("You are not allowed to use this functionality");
        }

        if (car.getStatus() != CarStatus.AVAILABLE) {
            throw new CarNotAvailableException("Car not available");
        }

        DealEntity dealEntity = new DealEntity();
        dealEntity.setCar(car);
        dealEntity.setUser(user);
        dealEntity.setDealType(DealType.RENT);
        dealEntity.setDate(LocalDate.now());

        return entityConverter.toDomain(
                dealRepository.save(dealEntity)
        );
    }
}
