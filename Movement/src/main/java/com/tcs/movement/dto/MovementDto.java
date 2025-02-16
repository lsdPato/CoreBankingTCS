package com.tcs.movement.dto;

import com.tcs.movement.enums.MovementType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovementDto {
    private String movementId;
    private BigDecimal amount;
    private BigDecimal balance;
    private String debtorAccount ;
    private String creditorAccount;
    private MovementType movementType;
    private LocalDateTime dateTime;

}
