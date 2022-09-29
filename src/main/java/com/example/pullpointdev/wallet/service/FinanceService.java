package com.example.pullpointdev.wallet.service;

import com.example.pullpointdev.user.model.User;
import com.example.pullpointdev.user.repository.UserRepository;
import com.example.pullpointdev.wallet.model.Transaction;
import com.example.pullpointdev.wallet.model.TransactionType;
import com.example.pullpointdev.wallet.model.Wallet;
import com.example.pullpointdev.wallet.repository.TransactionRepository;
import com.example.pullpointdev.wallet.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

//todo bank service

@Service
@RequiredArgsConstructor
public class FinanceService {
    private final TransactionRepository transactionRepository;
    private final WalletRepository walletRepository;
    private final UserRepository userRepository;

    public Transaction currencyInput(Long userId, Long sum){
        User owner = userRepository.findById(userId).orElseThrow(NullPointerException::new);
        Wallet wallet = walletRepository.findByOwner(owner).orElseThrow(NullPointerException::new);
        Transaction transaction = new Transaction();
        transaction.setType(TransactionType.INPUT);
        transaction.setSum(sum);
        transaction.setOwner(owner);
        transaction = transactionRepository.save(transaction);
        wallet.getHistory().add(transaction);
        walletRepository.save(wallet);
        return transaction;
    }


}
