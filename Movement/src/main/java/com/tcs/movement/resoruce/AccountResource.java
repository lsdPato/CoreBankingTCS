package com.tcs.movement.resoruce;

import com.tcs.movement.model.Account;
import com.tcs.movement.service.AccountService;
import com.tcs.movement.service.MovementService;
import com.tcs.movement.model.Movements;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cuentas")
@RequiredArgsConstructor
public class AccountResource {
    private final AccountService accountService;
    private final MovementService movementService;



    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        Account newAccount = accountService.assignedAccount(account);
        return ResponseEntity.ok(newAccount);
    }

  
    @PutMapping
    public ResponseEntity<Account> updateAccount(@RequestBody Account account) {
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
    @GetMapping("/movement-report")
    public ResponseEntity<List<Map<String, Object>>> movementReport(
            @RequestParam("fromDate") String fromDate,
            @RequestParam("toDate") String toDate,
            @RequestParam("ci") String ci) {
        List<Map<String, Object>> report = accountService.movementReport(
                LocalDateTime.parse(fromDate),
                LocalDateTime.parse(toDate),
                ci
        );
        return ResponseEntity.ok(report);
    }



}
