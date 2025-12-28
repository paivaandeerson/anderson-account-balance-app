package com.anderson.accountbalance.domain.repository.balance;

import com.anderson.accountbalance.domain.model.Balance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BalanceRepository extends JpaRepository<Balance, Long> {
}