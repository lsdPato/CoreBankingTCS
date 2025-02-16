package com.tcs.movement.dao;

import com.tcs.movement.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, String> {

    Optional<Account> findAccountByAccountNumber(String accountNumber);

}

