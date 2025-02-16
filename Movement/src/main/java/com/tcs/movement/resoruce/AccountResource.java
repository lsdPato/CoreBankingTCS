package com.tcs.movement.resoruce;

import com.tcs.movement.model.Account;
import com.tcs.movement.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cuentas")
@RequiredArgsConstructor
public class AccountResource {
    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        Account newAccount = accountService.assignedAccount(account);
        return ResponseEntity.ok(newAccount);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Account> updateAccount(@PathVariable String id, @RequestBody Account account) {
        Account updatedAccount = accountService.updateAccount(account);
        return ResponseEntity.ok(updatedAccount);
    }

//    @PatchMapping("/{id}")
//    public ResponseEntity<Account> partialUpdateAccount(@PathVariable String id, @RequestBody Account account) {
//        Account partiallyUpdatedAccount = accountService.partialUpdateAccount(id, account);
//        return ResponseEntity.ok(partiallyUpdatedAccount);
//    }

    @DeleteMapping("/{accountNumber}")
    public ResponseEntity<Void> deleteAccount(@PathVariable String accountNumber) {
        accountService.deleteAccount(accountNumber);
        return ResponseEntity.noContent().build();
    }
}
