package com.anderson.accountbalance.application;

import com.anderson.accountbalance.domain.model.*;
import com.anderson.accountbalance.domain.repository.BalanceRepository;
import com.anderson.accountbalance.sdk.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BalanceApplicationService {

    private final BalanceRepository balanceRepository;

    @Autowired
    public BalanceApplicationService(BalanceRepository balanceRepository) {
        this.balanceRepository = balanceRepository;
    }

    /**
     * UseCase: consultar saldo
     */
    public Notification<Balance> getBalance(Long accountId) {
        var notification = new Notification<Balance>();

        var balance = balanceRepository.findById(accountId).orElse(null);
        if (balance == null) {
            notification.addError("Balance not found for account");
            return notification;
        }

        //TODO: converter
        notification.setResult(balance);
        return notification;
    }

    /**
     * UseCase: atualizar saldo
     */
    public Notification<Void> updateBalance(Long accountId, Balance balance) {
        var notification = new Notification<Void>();

        var balanceDB = balanceRepository.findById(accountId).orElse(null);
        if (balanceDB == null) {
            notification.addError("Balance not found for account");
            return notification;
        }

        balanceDB.setAvailable(available);
        balanceDB.setBlocked(blocked);
        balanceDB.setTotal(available + blocked);

        balanceRepository.save(balanceDB);
        
        //TODO: converter
        notification.setResult(null);
        return notification;
    }
}
