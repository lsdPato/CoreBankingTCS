package com.tcs.movement.dto;

import com.tcs.movement.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class AccountDto {
    private Long accountId;
    private String accountNumber;
    private AccountType accountType;
    private BigDecimal initialBalance;
    private BigDecimal clientId;
    private BigDecimal generalBalance;

}
