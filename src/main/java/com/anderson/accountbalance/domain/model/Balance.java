package com.anderson.accountbalance.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

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

    @Column(name="`available`")
    private BigDecimal available;

    @Column(name="`blocked`")
    private BigDecimal blocked;
}