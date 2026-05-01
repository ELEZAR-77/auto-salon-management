package com.example.autosalone.models.deal;

import com.example.autosalone.models.car.CarEntity;
import com.example.autosalone.models.deal.enums.DealStatus;
import com.example.autosalone.models.deal.enums.DealType;
import com.example.autosalone.models.user.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "deals")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DealEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false, name = "car_Id")
    CarEntity car;

    @ManyToOne
    @JoinColumn(nullable = false, name = "user_Id")
    UserEntity user;

    @Enumerated(EnumType.STRING)
    private DealType dealType;

    private LocalDate date;

    private LocalDate startDate;

    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "deal_status")
    private DealStatus dealStatus;

    private BigDecimal totalPrice;
}
