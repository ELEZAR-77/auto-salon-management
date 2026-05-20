package com.example.autosalone.models.deal.serivce;

import com.example.autosalone.models.car.CarEntity;
import com.example.autosalone.models.car.CarStatus;
import com.example.autosalone.models.car.repository.CarRepository;
import com.example.autosalone.models.deal.DealEntity;
import com.example.autosalone.models.deal.enums.DealStatus;
import com.example.autosalone.models.deal.enums.DealType;
import com.example.autosalone.models.deal.reopsitory.DealRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DealSchedulerService {

    private final DealRepository dealRepository;


    @Transactional
    @Scheduled(cron = "0 0 0 * * *")
    public void finishExpiredRents() {
        log.info("The Scheduler was completed");
        LocalDate toDay = LocalDate.now();

        List<DealEntity> expiredDeals = dealRepository.findAllActiveRentsBefore(DealType.RENT, DealStatus.ACTIVE, toDay);

        for (DealEntity deal : expiredDeals) {
            deal.setDealStatus(DealStatus.FINISHED);

            CarEntity car = deal.getCar(); 
            car.setStatus(CarStatus.AVAILABLE);
        }
    }
}
