package com.tcs.movement.model;

import com.tcs.movement.enums.AccountType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@Table(name = "cuentas")
@NoArgsConstructor
@AllArgsConstructor
@Builder


public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String accountNumber;
    @Enumerated(EnumType.STRING)
    private AccountType accountType;
    @Column(nullable = false)
    private BigDecimal initialBalance;
    @Column(nullable = false)
    private String clientId;
    @Column(nullable = false)
    private BigDecimal generalBalance;

}
