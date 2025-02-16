package com.tcs.movement.service;

import com.tcs.movement.dao.AccountRepository;
import com.tcs.movement.dao.MovementRepository;
import com.tcs.movement.dto.MovementDto;
import com.tcs.movement.enums.MovementType;
import com.tcs.movement.exception.NotEnoughFundsException;
import com.tcs.movement.mapper.MovementMapper;
import com.tcs.movement.model.Account;
import com.tcs.movement.model.Movements;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j

public class MovementService {
    private final AccountRepository accountRepository;
    private final MovementRepository transactionRepository;

    public Movements makeWithdrawal(MovementDto movementDto){
        Account account = this.findAccountByAccountNumber(movementDto.getDebtorAccount());
        this.validateAccountFunds(account, movementDto.getAmount());
        this.withdrawAmount(movementDto.getAmount(), account);


        Movements movements = MovementMapper.buildWithdrawal(movementDto);
        movements.setMovementType(MovementType.withdrawal);


        return this.transactionRepository.save(movements);
    }
    public Movements makeDeposit(MovementDto movementDto){
        Account account = this.findAccountByAccountNumber(movementDto.getDebtorAccount());
        this.validateAccountFunds(account, movementDto.getAmount());
        this.depositToAccount(movementDto.getAmount(), account);

        Movements movements = MovementMapper.buildWithdrawal(movementDto);
        movements.setMovementType(MovementType.deposit);


        return this.transactionRepository.save(movements);
    }
    public Movements makeTransfer(MovementDto movementDto){
        Account creditorAccount =
                this.findAccountByAccountNumber(movementDto.getCreditorAccount());
        Account debtorAccount =
                this.findAccountByAccountNumber(movementDto.getDebtorAccount());
            this.validateAccountFunds(debtorAccount, movementDto.getAmount());
            this.withdrawAmount(movementDto.getAmount(), debtorAccount);
            this.depositToAccount(movementDto.getAmount(), creditorAccount);

        Movements movements = MovementMapper.buildWithdrawal(movementDto);
        movements.setMovementType(MovementType.transfer);
        movements.setDateTime(movementDto.getDateTime());
        log.info("Movimiento de {} del tipo {} de la cuenta {} a la cuenta {}",movements.getAmount(), movements.getMovementType()
        ,movements.getDebtorAccount(), movements.getCreditorAccount());

        return this.transactionRepository.save(movements);
    }
    private void validateAccountFunds(Account account, BigDecimal amount)
            throws NotEnoughFundsException {
        boolean hasNotEnoughFunds = account.getInitialBalance().compareTo(amount) < 0;
        if (hasNotEnoughFunds) {
            throw new NotEnoughFundsException(
                    "La cuenta " + account.getAccountNumber() + " Saldo no disponible");
        }
    }
    private Account findAccountByAccountNumber(String accountNumber) {
        Optional<Account> accountOpt =
                accountRepository.findAccountByAccountNumber(accountNumber);
        return accountOpt.orElseThrow(() -> new RuntimeException("Cuenta no encontrada" + accountNumber));
    }
    private void depositToAccount(BigDecimal amount, Account account) {
        BigDecimal previousBalance = account.getInitialBalance();
        BigDecimal newBalance = previousBalance.add(amount);

        account.setInitialBalance(newBalance);

        this.accountRepository.save(account);
    }
    private void withdrawAmount(BigDecimal amount, Account account) {
        BigDecimal previousBalance = account.getInitialBalance();
        BigDecimal newBalance = previousBalance.subtract(amount);


        account.setInitialBalance(newBalance);

        this.accountRepository.save(account);
    }



}

