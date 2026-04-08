package com.example.autosalone.models.deal.reopsitory;

import com.example.autosalone.models.deal.DealEntity;
import com.example.autosalone.models.deal.dto.DealEmployerDealsDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DealRepository extends JpaRepository<DealEntity, Long> {

    @Query("""
            SELECT
            d.id     AS dealId,
            u.id     AS userId,
            c.id     AS carId,
            c.brand  AS brand,
            c.model  AS model,
            c.price  AS price,
            c.color  AS color,
            c.year   AS year,
            c.status AS status
        FROM DealEntity d
        JOIN d.user u
        JOIN d.car c
        WHERE u.id = :userId
    """)
    List<DealEmployerDealsDto> findAllById(Long userId);
}
