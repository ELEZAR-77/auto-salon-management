package com.example.autosalone.models.deal.converters;

import com.example.autosalone.models.deal.Deal;
import com.example.autosalone.models.deal.DealEntity;
import org.springframework.stereotype.Component;

@Component
public class DealEntityConverter {

    public Deal toDomain(DealEntity dealEntity) {
        return new Deal(
                dealEntity.getId(),
                dealEntity.getCar().getId(),
                dealEntity.getUser().getId(),
                dealEntity.getDealType(),
                dealEntity.getDealStatus(),
                dealEntity.getDate(),
                dealEntity.getTotalPrice()
        );
    }
}
