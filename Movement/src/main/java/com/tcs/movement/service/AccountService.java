package com.tcs.movement.service;

import com.tcs.movement.dao.AccountRepository;
import com.tcs.movement.dao.MovementRepository;
import com.tcs.movement.dto.AccountDto;
import com.tcs.movement.exception.AccountAssignedException;
import com.tcs.movement.model.Account;
import com.tcs.movement.model.Movements;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@Slf4j
@Service
@RequiredArgsConstructor
public class AccountService {

    private final RestTemplate restTemplate;
    private final AccountRepository accountRepository;
    private final MovementRepository transactionRepository;
    private final MovementRepository movementRepository;

    public Account assignedAccount(Account account) {
//        String url =
//                "http://localhost:8080/clientes/"
//                        + ci
//                        + "/name";
//        String name = restTemplate.getForObject(url, String.class);
//       log.info(name);

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
    public List<Map<String, Object>> movementReport(LocalDateTime fromDate, LocalDateTime toDate, String ci) {
        String url =
                "http://localhost:8080/clientes"
                        + ci
                        + "/name";

        List<Account> accounts = accountRepository.findAccountsByClientId(ci);

        return accounts.stream().map(account -> {
            List<Movements> movements = movementRepository.findByDebtorAccountAndDateTimeBetween(
                    account.getAccountNumber(), fromDate, toDate);

            Map<String, Object> report = new HashMap<>();
            report.put("Numero de cuenta", account.getAccountNumber());
            report.put("Balance de cuenta", account.getInitialBalance());
            report.put("Movimientos", movements);


            return report;
        }).collect(Collectors.toList());}

}
