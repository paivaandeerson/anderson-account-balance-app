package com.anderson.accountbalance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.anderson.accountbalance.application.BalanceApplicationService;
import com.anderson.accountbalance.domain.model.Balance;


@RestController
@RequestMapping("/balances")
public class BalanceController {

    private final BalanceApplicationService balanceService;

    @Autowired
    public BalanceController (BalanceApplicationService balanceService){
        this.balanceService = balanceService;
    }

//    @PutMapping("/{accountId}")
//    public ResponseEntity<Void> updateBalance(
//            @PathVariable Long accountId,
//            @RequestBody Balance request
//    ) {
//        var notification = balanceService.updateBalance(accountId, request);
//
//        if (notification.getResult() == null) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//
//        return ResponseEntity.noContent().build();
//    }
}
