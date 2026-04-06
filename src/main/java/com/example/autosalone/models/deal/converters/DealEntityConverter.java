//package com.example.autosalone.models.deal.converters;
//
//import com.example.autosalone.models.car.converters.CarEntityConverter;
//import com.example.autosalone.models.deal.Deal;
//import com.example.autosalone.models.deal.DealEntity;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//@Component
//public class DealEntityConverter {
//
//    public DealEntity toEntity(Deal deal) {
//        return new DealEntity(
//                deal.id(),
//                carEntityConverter.toEntity(deal.car()),
//                deal.user(),
//                deal.type().name(),
//                deal.date()
//        )
//    }
//}
