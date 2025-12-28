package com.anderson.accountbalance.domain.model;

import java.math.BigDecimal;
import java.util.Optional;

public record BalanceResponseDTO(
        BigDecimal available,
        BigDecimal blocked,
        BigDecimal total
) {
    public static BalanceResponseDTO from(
            BigDecimal available,
            BigDecimal blocked
    ) {
        return new BalanceResponseDTO(
                available,
                blocked,
                available.add(blocked)
        );
    }
    public static BalanceResponseDTO from(
            Optional<Balance> balance
    ) {

        if (balance.isEmpty())
            return null;

        return BalanceResponseDTO.from(
                balance.get().getAvailable(),
                balance.get().getBlocked()
        );
    }
}
