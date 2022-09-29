package com.example.pullpointdev.wallet.repository;

import com.example.pullpointdev.user.model.User;
import com.example.pullpointdev.wallet.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
    Optional<Wallet> findByOwner(User owner);
}
