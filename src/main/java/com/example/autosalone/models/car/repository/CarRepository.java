package com.example.autosalone.models.car.repository;

import com.example.autosalone.models.car.Car;
import com.example.autosalone.models.car.CarEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<CarEntity, Long> {
    boolean existsByModel(String model);

    CarEntity findByModel(String model);

    @Query("""
    SELECT c from CarEntity c
        WHERE (:brand IS NULL OR c.brand = :brand)
        AND (:model IS NULL OR c.model = :model)
        AND (:maxPrice IS NULL OR c.price <= :maxPrice)
    """)
    List<CarEntity> searchCars(
            String brand,
            String model,
            BigDecimal maxPrice,
            Pageable pageable);
}