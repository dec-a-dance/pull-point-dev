package com.example.pullpointdev.wallet.repository;

import com.example.pullpointdev.wallet.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
