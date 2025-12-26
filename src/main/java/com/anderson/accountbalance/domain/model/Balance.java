package com.anderson.accountbalance.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "balance")
public class Balance {
    @Id
    @Column(name="`accountId`")
    private Long accountId;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(name="`blocked`")
    private int available;
}