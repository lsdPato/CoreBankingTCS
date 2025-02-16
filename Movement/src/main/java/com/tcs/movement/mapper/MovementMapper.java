package com.tcs.movement.mapper;

import com.tcs.movement.dto.MovementDto;
import com.tcs.movement.model.Account;
import com.tcs.movement.model.Movements;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MovementMapper {
    public static Movements buildWithdrawal(MovementDto movementDto){
        return Movements.builder()
                .movementId(UUID.randomUUID().toString())
                .amount(movementDto.getAmount())
                .debtorAccount(movementDto.getDebtorAccount())
                .creditorAccount(movementDto.getCreditorAccount())
                .movementType(movementDto.getMovementType())
                .balance(movementDto.getBalance())
                .dateTime(movementDto.getDateTime())
                .build();


    }
    public static Movements buildMovementsDto(Movements movement){
        return Movements.builder()
                .movementId(UUID.randomUUID().toString())
                .amount(movement.getAmount())
                .movementType(movement.getMovementType())
                .balance(movement.getBalance())
                .dateTime(movement.getDateTime())
                .build();

    }

}
