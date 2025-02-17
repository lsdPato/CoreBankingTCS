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

    @PutMapping("/{id}")
    public ResponseEntity<?> updateMovement(@PathVariable Long id, @RequestBody MovementDto movementDto) {
        return ResponseEntity.ok(movementService.updateMovement(id, movementDto));
    }

    @GetMapping
    public ResponseEntity<?> getAllMovements() {
        return ResponseEntity.ok(movementService.getAllMovements());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMovementById(@PathVariable Long id) {
        movementService.deleteMovementById(id);
        return ResponseEntity.noContent().build();
    }


   /* @GetMapping("/report")
    public ResponseEntity<?> generateReport() {
        return ResponseEntity.ok(movementService.movementReport());
    }
    */
    
    
}
