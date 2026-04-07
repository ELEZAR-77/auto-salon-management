package com.example.autosalone.models.deal.controller;

import com.example.autosalone.models.deal.converters.DealDtoConverter;
import com.example.autosalone.models.deal.dto.DealDto;
import com.example.autosalone.models.deal.dto.DealRentRequestDto;
import com.example.autosalone.models.deal.serivce.DealService;
import com.example.autosalone.models.user.UserEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/deals")
@RequiredArgsConstructor
public class DealController {

    private final DealService dealService;
    private final DealDtoConverter dealDtoConverter;


    @PostMapping("/rent")
    public ResponseEntity<DealDto> rentDeal(
            @RequestBody DealRentRequestDto requestDto,
            @AuthenticationPrincipal UserDetails employee
    ) {
        log.info("Get rent request");

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(dealDtoConverter.toDto(
                        dealService.rentDeal(
                                requestDto.carId(),
                                employee
                        )
                    )
                );
    }
}
