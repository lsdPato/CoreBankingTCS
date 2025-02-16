package com.tcs.movement.resoruce;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import com.tcs.movement.service.MovementService;
import com.tcs.movement.dto.MovementDto;

@RestController
@RequestMapping("/movimientos")
@RequiredArgsConstructor
public class MovementResource {

    private final MovementService movementService;

    @PostMapping("/withdraw")
    public ResponseEntity<?> makeWithdrawal(@RequestBody MovementDto movementDto) {
        return ResponseEntity.ok(movementService.makeWithdrawal(movementDto));
    }

    @PostMapping("/deposit")
    public ResponseEntity<?> makeDeposit(@RequestBody MovementDto movementDto) {
        return ResponseEntity.ok(movementService.makeDeposit(movementDto));
    }

    @PostMapping("/transfer")
    public ResponseEntity<?> makeTransfer(@RequestBody MovementDto movementDto) {
        return ResponseEntity.ok(movementService.makeTransfer(movementDto));
    }

   /* @GetMapping("/report")
    public ResponseEntity<?> generateReport() {
        return ResponseEntity.ok(movementService.movementReport());
    }
    */
    
    
}
