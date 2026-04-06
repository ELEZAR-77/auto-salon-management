package com.example.autosalone.models.deal.converters;

import com.example.autosalone.models.deal.Deal;
import com.example.autosalone.models.deal.DealDto;
import com.example.autosalone.models.deal.DealType;
import org.springframework.stereotype.Component;

@Component
public class DealDtoConverter {

    public DealDto toDto(Deal deal) {
        return new DealDto(
                deal.id(),
                deal.car(),
                deal.user(),
                deal.date()
        );
    }
}
