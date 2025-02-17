package com.tcs.movement.mapper;

import com.tcs.movement.dto.AccountDto;
import com.tcs.movement.dto.MovementDto;
import com.tcs.movement.model.Account;
import com.tcs.movement.model.Movements;

import java.util.UUID;

public class AccountMapper {
    public static Account buildWithdrawal(AccountDto accountDto) {
        return Account.builder()
                .accountNumber(UUID.randomUUID().toString())
                .accountType(accountDto.getAccountType())
                .build();
    }
}
