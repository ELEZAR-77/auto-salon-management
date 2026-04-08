package com.example.autosalone.models.car;

import com.example.autosalone.models.deal.DealEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cars")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CarEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private String model;
    private BigDecimal price;
    private String color;
    private Integer year;

    @Enumerated(EnumType.STRING)
    private CarStatus status;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
    private List<DealEntity> deals = new ArrayList<>();

    public CarEntity(Long id, String brand, String model, BigDecimal price, String color, Integer year, CarStatus status) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.color = color;
        this.year = year;
        this.status = status;
    }
}
