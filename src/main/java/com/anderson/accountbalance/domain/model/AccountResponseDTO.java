package com.anderson.accountbalance.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

public record AccountResponseDTO(
    Long id,
    String name,
    String type,
    int available,
    int total
) {
    public static AccountResponseDTO from(Account account, Balance balance) {
        return new AccountResponseDTO(
            account.getId(),
            account.getName(),
            account.getType(),
            balance.getAvailable()
        );
    }
}
