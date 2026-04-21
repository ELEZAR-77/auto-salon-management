package com.example.autosalone.models.deal.controller;

import com.example.autosalone.models.deal.converters.DealDtoConverter;
import com.example.autosalone.models.deal.dto.DealDto;
import com.example.autosalone.models.deal.dto.DealEmployerDealsDto;
import com.example.autosalone.models.deal.dto.DealRentRequestDto;
import com.example.autosalone.models.deal.dto.DealRentUpdateDto;
import com.example.autosalone.models.deal.serivce.DealService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
                                requestDto,
                                employee
                        )
                    )
                );
    }

    @PostMapping("/sale/{carId}")
    @PreAuthorize("hasAuthority('EMPLOYEE')")
    public ResponseEntity<DealDto> saleDeal(
            @PathVariable Long carId,
            @AuthenticationPrincipal UserDetails employee
    ) {
        log.info("Get sale request");

        return ResponseEntity
                .ok(dealDtoConverter.toDto(
                        dealService.saleDeal(carId, employee)
                ));
    }

    @GetMapping
    public ResponseEntity<List<DealEmployerDealsDto>> getEmployerDeals(
            @AuthenticationPrincipal UserDetails user
    ) {
        log.info("Get employer deal request");

        return ResponseEntity
                .ok(dealService.getEmployerDeals(user));
    }

    @PutMapping("/rent-update/{id}")
    @PreAuthorize("hasAuthority('EMPLOYEE')")
    public ResponseEntity<DealDto> extendRentalDeal(
            @PathVariable Long id,
            @RequestBody DealRentUpdateDto requestDto
    ) {
        log.info("Get update rent request");

        return ResponseEntity
                .ok(
                        dealDtoConverter.toDto(
                                dealService.extendRentalDeal(id, requestDto)
                        )
                );
    }

    @DeleteMapping("rent-delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> deleteDeal(@PathVariable Long id) {
        log.info("Delete deal request");

        dealService.deleteDealById(id);

        return ResponseEntity
                .noContent()
                .build();
    }
}
