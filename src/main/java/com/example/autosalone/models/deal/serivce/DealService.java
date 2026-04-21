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
import com.example.autosalone.models.deal.dto.DealEmployerDealsDto;
import com.example.autosalone.models.deal.dto.DealRentRequestDto;
import com.example.autosalone.models.deal.dto.DealRentUpdateDto;
import com.example.autosalone.models.deal.enums.DealStatus;
import com.example.autosalone.models.deal.enums.DealType;
import com.example.autosalone.models.deal.reopsitory.DealRepository;
import com.example.autosalone.models.user.UserEntity;
import com.example.autosalone.models.user.UserRole;
import com.example.autosalone.models.user.exceptions.UserAccessDeniedException;
import com.example.autosalone.models.user.exceptions.UserNotFoundException;
import com.example.autosalone.models.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DealService {

    private final DealRepository dealRepository;
    private final UserRepository userRepository;
    private final CarRepository carRepository;
    private final DealEntityConverter entityConverter;

    @Transactional
    public Deal rentDeal(
            DealRentRequestDto request,
            UserDetails employee
    ) {
        UserEntity user = userRepository.findByEmail(employee.getUsername())
                .orElseThrow(() -> new UserNotFoundException("User not found: " + employee.getUsername()));

        CarEntity car = carRepository.findById(request.carId()).orElseThrow(
                () -> new CarNotExistException("Car not found")
        );

        carStatusCheck(car.getStatus());
        userStatusCheck(user.getRole());

        car.setStatus(CarStatus.RENTED);

        DealEntity dealEntity = new DealEntity();
        dealEntity.setCar(car);
        dealEntity.setUser(user);
        dealEntity.setDealType(DealType.RENT);
        dealEntity.setDealStatus(DealStatus.ACTIVE);
        dealEntity.setDate(LocalDate.now());
        dealEntity.setStartDate(request.startDate());
        dealEntity.setEndDate(request.endDate());

        return entityConverter.toDomain(
                dealRepository.save(dealEntity)
        );
    }

    @Transactional
    public Deal saleDeal(Long carId, UserDetails employee) {
        var car = carRepository.findById(carId).orElseThrow(
                () -> new CarNotExistException("Car not found")
        );

        var user = userRepository.findByEmail(employee.getUsername()).orElseThrow(
                () -> new UserNotFoundException("User not found: " + employee.getUsername())
        );

        carStatusCheck(car.getStatus());
        userStatusCheck(user.getRole());

        car.setStatus(CarStatus.SOLD);

        DealEntity dealEntity = new DealEntity();
        dealEntity.setCar(car);
        dealEntity.setUser(user);
        dealEntity.setDealType(DealType.SALE);
        dealEntity.setDealStatus(DealStatus.FINISHED);
        dealEntity.setDate(LocalDate.now());

        return entityConverter.toDomain(
                dealRepository.save(dealEntity)
        );
    }

    public List<DealEmployerDealsDto> getEmployerDeals(UserDetails user) {
        var userEntity = userRepository.findByEmail(user.getUsername()).orElseThrow(
                () -> new UserNotFoundException("User not found: " + user.getUsername())
        );
        if (userEntity.getRole().equals(UserRole.EMPLOYEE)) {
            return dealRepository.findAllById(userEntity.getId());
        }

        return dealRepository.findAllEmployersDeals();
    }

    @Transactional
    public Deal extendRentalDeal(
            Long dealId,
            DealRentUpdateDto requestDto
    ) {
        var deal = dealRepository.findById(dealId).orElseThrow(
                () -> new EntityNotFoundException("No deal found with id: " + dealId)
        );

        if (requestDto.carId() != null) {
            var car =  carRepository.findById(requestDto.carId()).orElseThrow(
                    () -> new CarNotExistException("Car not found")
            );
            carStatusCheck(car.getStatus());
            deal.setCar(car);
        }

        deal.setDealStatus(DealStatus.EXTENDED);
        deal.setEndDate(requestDto.endDate());

        return entityConverter.toDomain(deal);
    }


    @Transactional
    public void deleteDealById(Long id) {
        if (!dealRepository.existsById(id)) {
            throw new EntityNotFoundException("No deal found with id: " + id);
        }

        dealRepository.deleteById(id);
    }

    public void carStatusCheck(CarStatus carStatus) {
        if (carStatus != CarStatus.AVAILABLE) {
            throw new CarNotAvailableException("The car is unavailable");
        }
    }

    public void userStatusCheck(UserRole userRole) {
        if (userRole != UserRole.EMPLOYEE) {
            throw new UserAccessDeniedException("You are not allowed to use this functionality");
        }
    }
}
