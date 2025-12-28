package com.anderson.accountbalance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.anderson.accountbalance.application.AccountApplicationService;

import com.anderson.accountbalance.domain.model.Account;


@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountApplicationService accountService;

    @Autowired
    public AccountController(AccountApplicationService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<?> createAccount(
            @RequestBody Account request
    ) {
        var notification = accountService.createAccount(request);

        if (notification.hasErrors()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(notification.getErrors());
        }

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(notification.getResult());
    }

    @GetMapping("/")
    public ResponseEntity<?> getAccounts() {
        var notification = accountService.getAllAccounts();
        return ResponseEntity.ok(notification.getResult());
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<?> getAccountById(
            @PathVariable Long accountId
    ) {
        var notification = accountService.getAccount(accountId);

        if (notification.getResult() == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(notification.getErrors());
        }

        return ResponseEntity.ok(notification.getResult());
    }
}

