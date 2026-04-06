package com.example.autosalone.models.deal.reopsitory;

import com.example.autosalone.models.deal.DealEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DealRepository extends JpaRepository<DealEntity, Long> {
}
