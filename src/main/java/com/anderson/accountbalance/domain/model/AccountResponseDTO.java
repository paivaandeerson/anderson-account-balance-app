package com.anderson.accountbalance.domain.model;


import java.time.OffsetDateTime;
import java.util.Optional;

public record AccountResponseDTO(
    Long accountId,
    String name,
    OffsetDateTime created,
    boolean active,
    BalanceResponseDTO balance
) {
    public static AccountResponseDTO from(Account account, Optional<Balance> balance) {
        return new AccountResponseDTO(
                account.getAccountId(),
                "Conta : "+account.getType()+"-"+account.getAccountId(),
                account.getCreated(),
                account.isActive(),
                BalanceResponseDTO.from(balance)
        );
    }
}
