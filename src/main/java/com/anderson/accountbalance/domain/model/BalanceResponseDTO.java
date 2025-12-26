package com.anderson.accountbalance.domain.model;

public record BalanceResponseDTO(
    int available,
    int blocked,
    int total
) {
    public static BalanceResponseDTO from(
        int available,
        int blocked
    ) {
        return new BalanceResponseDTO(
            available,
            blocked,
            available + blocked
        );
    }
}
