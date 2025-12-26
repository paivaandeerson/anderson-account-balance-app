package com.anderson.accountbalance.application;

import com.anderson.accountbalance.domain.model.*;
import com.anderson.accountbalance.domain.repository.AccountRepository;
import com.anderson.accountbalance.domain.repository.BalanceRepository;
import com.anderson.accountbalance.sdk.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class AccountApplicationService {

    private final AccountRepository accountRepository;
    private final BalanceRepository balanceRepository;

    @Autowired
    public AccountApplicationService(AccountRepository accountRepository,
        BalanceRepository balanceRepository) {
        this.accountRepository = accountRepository;
        this.balanceRepository = balanceRepository;
    }

    /**
     * UseCase: criar conta + saldo
     */
    public Notification<Long> createAccount(Account accountCommand) {
        var notification = new Notification<Long>();

        if (accountCommand.getName() == null || accountCommand.getName().isBlank()) {
            notification.addError("Account name is required");
            return notification;
        }

        accountCommand.setCreated(OffsetDateTime.now());
        var savedAccount = accountRepository.save(accountCommand);

        // cria saldo inicial zerado
        var balance = new Balance();
        balance.setAccountId(savedAccount.getAccountId());
        balance.setAvailable(0);
        balance.setTotal(0);

        balanceRepository.save(balance);

        //TODO: converter
        notification.setResult(savedAccount.getAccountId());
        return notification;
    }

    /**
     * UseCase: buscar conta consolidada
     */
    public Notification<AccountResponseDTO> getAccount(Long accountId) {
        var notification = new Notification<Account>();

        var account = accountRepository.findById(accountId).orElse(null);
        if (account == null) {
            notification.addError("Account not found");
        //TODO: converter
            return notification;
        }
        var balance = balanceRepository.findById(accountId);

        //TODO: converter
        notification.setResult(account);        
        return notification;
    }

    /**
     * UseCase: listar contas consolidadas
     */
    public Notification<List<AccountResponseDTO>> getAllAccounts() {
        var notification = new Notification<List<AccountResponseDTO>>();
        var accounts = accountRepository.findAll();
        
        //TODO: converter
        notification.setResult(accounts);
        return notification;
    }
}
