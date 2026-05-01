package com.example.autosalone.models.deal.serivce;

import com.example.autosalone.models.car.CarEntity;
import com.example.autosalone.models.car.CarStatus;
import com.example.autosalone.models.car.repository.CarRepository;
import com.example.autosalone.models.deal.DealEntity;
import com.example.autosalone.models.deal.enums.DealStatus;
import com.example.autosalone.models.deal.enums.DealType;
import com.example.autosalone.models.deal.reopsitory.DealRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DealSchedulerService {

    private DealRepository dealRepository;
    private CarRepository carRepository;


    @Scheduled(cron = "0 0 0 * * *")
    public void finishExpiredRents() {

        LocalDate toDay = LocalDate.now();

        List<DealEntity> expiredDeals = dealRepository.findAllActiveRentsBefore(DealType.RENT, DealStatus.ACTIVE, toDay);

        for (DealEntity deal : expiredDeals) {
            deal.setDealStatus(DealStatus.FINISHED);

            CarEntity car = deal.getCar();
            car.setStatus(CarStatus.AVAILABLE);
        }
    }
}
