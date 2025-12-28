package com.anderson.accountbalance.application;

import com.anderson.accountbalance.domain.model.*;
import com.anderson.accountbalance.domain.repository.account.AccountRepository;
import com.anderson.accountbalance.domain.repository.balance.BalanceRepository;
import com.anderson.accountbalance.sdk.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Optional;

@Service
public class AccountApplicationService {

    private final AccountRepository accountRepository;
    private final BalanceRepository balanceRepository;

    private final String contaCorrente = "CC";
    private final String contaPoupanca = "PP";

    @Autowired
    public AccountApplicationService(AccountRepository accountRepository,
        BalanceRepository balanceRepository) {
        this.accountRepository = accountRepository;
        this.balanceRepository = balanceRepository;
    }

    /**
     * UseCase: criar conta + saldo
     */
    @Transactional("accountTransactionManager")
    public Notification<AccountResponseDTO> createAccount(Account accountCommand) {
        var notification = new Notification<AccountResponseDTO>();

        if (accountCommand.getType() == null ||
                (!contaCorrente.equals(accountCommand.getType()) && !contaPoupanca.equals(accountCommand.getType()))) {
            notification.addError("Tipo de conta inválido, valores válidos ("+ contaCorrente +","+ contaPoupanca +")");
            notification.setResult(AccountResponseDTO.from(accountCommand,null));
            return notification;
        }

        accountCommand.setCreated(OffsetDateTime.now());
        var savedAccount = accountRepository.save(accountCommand);


        var savedBalance = new Balance();
        savedBalance.setAccountId(savedAccount.getAccountId());
        savedBalance.setAvailable(BigDecimal.valueOf(0));
        savedBalance.setBlocked(BigDecimal.valueOf(0));

        balanceRepository.save(savedBalance);

        notification.setResult(AccountResponseDTO.from(savedAccount, Optional.ofNullable(savedBalance)));
        return notification;
    }

//    /**
//     * UseCase: buscar conta consolidada
//     */
//    public Notification<AccountResponseDTO> getAccount(Long accountId) {
//        var notification = new Notification<Account>();
//
//        var account = accountRepository.findById(accountId).orElse(null);
//        if (account == null) {
//            notification.addError("Account not found");
//        //TODO: converter
//            return notification;
//        }
//        var balance = balanceRepository.findById(accountId);
//
//        //TODO: converter
//        notification.setResult(account);
//        return notification;
//    }
//
//    /**
//     * UseCase: listar contas consolidadas
//     */
//    public Notification<List<AccountResponseDTO>> getAllAccounts() {
//        var notification = new Notification<List<AccountResponseDTO>>();
//        var accounts = accountRepository.findAll();
//
//        //TODO: converter
//        notification.setResult(accounts);
//        return notification;
//    }
}
