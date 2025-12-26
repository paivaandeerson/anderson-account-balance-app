package com.anderson.accountbalance.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anderson.accountbalance.domain.model.Account;

//TODO: gerar base para o h2
public interface AccountRepository extends JpaRepository<Account, Long> {
}