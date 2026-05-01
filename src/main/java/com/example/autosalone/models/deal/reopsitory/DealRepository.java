package com.example.autosalone.models.deal.reopsitory;

import com.example.autosalone.models.deal.DealEntity;
import com.example.autosalone.models.deal.dto.DealEmployerDealsDto;
import com.example.autosalone.models.deal.dto.DealStatsProjection;
import com.example.autosalone.models.deal.enums.DealStatus;
import com.example.autosalone.models.deal.enums.DealType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DealRepository extends JpaRepository<DealEntity, Long> {

    @Query("""
            SELECT
            d.id     AS dealId,
            d.dealType AS dealType,
            d.dealStatus AS dealStatus,
            d.date AS dealDate,
            d.startDate AS dealStartDate,
            d.endDate AS dealEndDate,
            d.totalPrice AS totalPrice,
            u.id     AS userId,
            c.id     AS carId,
            c.brand  AS brand,
            c.model  AS model,
            c.salePrice  AS price,
            c.rentPricePerDay AS rentPricePerDay,
            c.color  AS color,
            c.year   AS year,
            c.status AS status
        FROM DealEntity d
        JOIN d.user u
        JOIN d.car c
        WHERE u.id = :userId
    """)
    List<DealEmployerDealsDto> findAllById(Long userId);


    @Query("""
    SELECT d from DealEntity d
        WHERE d.dealType = :type
            AND d.dealStatus = :status
                AND d.endDate < :toDay
    """)
    List<DealEntity> findAllActiveRentsBefore(
            @Param("type") DealType type,
            @Param("status") DealStatus status,
            @Param("toDay") LocalDate toDay
            );

    boolean existsById(Long id);

    @Query("""
            SELECT
            d.id     AS dealId,
            d.dealType AS dealType,
            d.dealStatus AS dealStatus,
            d.date AS dealDate,
            d.startDate AS dealStartDate,
            d.endDate AS dealEndDate,
            d.totalPrice AS totalPrice,
            u.id     AS userId,
            c.id     AS carId,
            c.brand  AS brand,
            c.model  AS model,
            c.salePrice  AS price,
            c.rentPricePerDay AS rentPricePerDay,
            c.color  AS color,
            c.year   AS year,
            c.status AS status
        FROM DealEntity d
        JOIN d.user u
        JOIN d.car c
    """)
    List<DealEmployerDealsDto> findAllEmployersDeals();

    @Query("""
        SELECT 
            COUNT(d) as dealCount,
            SUM(CASE WHEN d.dealType = 'SALE' THEN 1 ELSE 0 END) as saleCount,
            SUM(CASE WHEN d.dealType = 'RENT' THEN 1 ELSE 0 END) as rentCount,
            SUM(d.totalPrice) as totalIncome,
            SUM(CASE WHEN d.dealType = 'RENT' AND d.dealStatus = 'ACTIVE' THEN 1 ELSE 0 END) as activeRents
        FROM DealEntity d
            WHERE d.date >= coalesce(:from, d.date) 
            AND d.date <= coalesce(:to, d.date) 
    """)
    DealStatsProjection searchDealStats(
            @Param("from") LocalDate from,
            @Param("to") LocalDate to
    );
}
