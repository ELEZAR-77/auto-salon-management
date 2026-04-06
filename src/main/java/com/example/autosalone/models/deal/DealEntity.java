package com.example.autosalone.models.deal;

import com.example.autosalone.models.car.CarEntity;
import com.example.autosalone.models.user.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @JoinColumn(nullable = false)
    CarEntity car;

    @ManyToOne
    @JoinColumn(nullable = false)
    UserEntity user;

    String dealType;
    LocalDate date;
}
