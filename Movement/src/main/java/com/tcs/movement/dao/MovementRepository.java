package com.tcs.movement.dao;

import com.tcs.movement.model.Movements;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface MovementRepository extends JpaRepository <Movements,Long>{

    Optional<Movements> findMovementsByMovementId(String movementId);
    Optional<Movements> findByDebtorAccount(String id);
    List<Movements> findByDebtorAccountAndDateTimeBetween(String accountNumber, LocalDateTime fromDate, LocalDateTime toDate);
}
