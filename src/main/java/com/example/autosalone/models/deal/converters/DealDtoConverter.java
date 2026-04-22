package com.example.autosalone.models.deal.converters;

import com.example.autosalone.models.deal.Deal;
import com.example.autosalone.models.deal.dto.DealDto;
import org.springframework.stereotype.Component;

@Component
public class DealDtoConverter {

    public DealDto toDto(Deal deal) {
        return new DealDto(
                deal.id(),
                deal.carId(),
                deal.userId(),
                deal.date(),
                deal.type(),
                deal.status(),
                deal.totalPrice()
        );
    }
}
