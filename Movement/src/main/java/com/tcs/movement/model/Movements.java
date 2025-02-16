package com.tcs.movement.model;

import com.tcs.movement.enums.MovementType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "movimientos")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor

public class Movements {
    @Id
    @Column(nullable = false, updatable = false, unique = true)
    private String movementId;
    @Column(nullable = false)
    private LocalDateTime dateTime;
    @Enumerated(EnumType.STRING)
    private MovementType movementType;
    @Column(nullable = false)
    private BigDecimal amount;
    @Column(nullable = false)
    private BigDecimal balance;
    @Column
    private String debtorAccount;
    @Column
    private String creditorAccount;



}
