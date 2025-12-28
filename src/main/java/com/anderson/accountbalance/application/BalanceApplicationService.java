package com.anderson.accountbalance.application;

import com.anderson.accountbalance.domain.model.*;
import com.anderson.accountbalance.domain.repository.balance.BalanceRepository;
import com.anderson.accountbalance.sdk.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BalanceApplicationService {

    private final BalanceRepository balanceRepository;

    @Autowired
    public BalanceApplicationService(BalanceRepository balanceRepository) {
        this.balanceRepository = balanceRepository;
    }

    /**
     * UseCase: atualizar saldo
     */
    public Notification<BalanceResponseDTO> updateBalance(Long accountId, Balance balanceCommand) {
        var notification = new Notification<BalanceResponseDTO>();

        var balanceDB = balanceRepository.findById(accountId).orElse(null);
        if (balanceDB == null) {
            notification.addError("Conta não encontrada para atualização de saldo");
            return notification;
        }

        balanceDB.setAvailable(balanceCommand.getAvailable());
        balanceDB.setBlocked(balanceCommand.getBlocked());

        balanceRepository.save(balanceDB);

        notification.setResult(BalanceResponseDTO.from(Optional.ofNullable(balanceDB)));
        return notification;
    }
}
