package com.tcs.movement.service;

import com.tcs.movement.dao.AccountRepository;
import com.tcs.movement.exception.AccountAssignedException;
import com.tcs.movement.model.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public Account assignedAccount(Account account) {
        Optional<Account> accountOpt =
                accountRepository.findAccountByAccountNumber(account.getAccountNumber());
        if (accountOpt.isPresent()) {
            throw new AccountAssignedException("Error, la cuenta ya ha sido asignada");
        }


        return this.accountRepository.save(account);
    }
    public Optional<Account> findAccountByNumber(String accountNumber){
        return accountRepository.findAccountByAccountNumber(accountNumber);
    }

    public void deleteAccount(String accountNumber) {
        Account accountToDelete = accountRepository.findAccountByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));
        accountRepository.delete(accountToDelete);
    }
    public Account updateAccount(Account account) {
        Optional<Account> accountId = accountRepository.findAccountByAccountNumber(account.getAccountNumber());
        accountRepository.save(account);
        return account;
    }

    public Account setBalance(BigDecimal newBalance, Account account) {
        account.setGeneralBalance(newBalance);
        return accountRepository.save(account);
    }

}
