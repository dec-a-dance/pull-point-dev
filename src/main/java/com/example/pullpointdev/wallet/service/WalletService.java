package com.example.pullpointdev.wallet.service;

import com.example.pullpointdev.user.model.User;
import com.example.pullpointdev.user.repository.UserRepository;
import com.example.pullpointdev.wallet.model.Transaction;
import com.example.pullpointdev.wallet.model.Wallet;
import com.example.pullpointdev.wallet.model.dto.CreateWalletReq;
import com.example.pullpointdev.wallet.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WalletService {
    private final WalletRepository walletRepository;
    private final UserRepository userRepository;

    public Wallet createWallet(String credentials, String phone){
        User owner = userRepository.findByPhone(phone).orElseThrow(NullPointerException::new);
        Wallet wallet = new Wallet();
        wallet.setOwner(owner);
        wallet.setBalance(0L);
        wallet.setHistory(new ArrayList<>());
        wallet.setBankCredentials(credentials);
        wallet = walletRepository.save(wallet);
        owner.setWallet(wallet);
        userRepository.save(owner);
        return wallet;
    }

    public Wallet getWallet(String userPhone){
        return walletRepository.findByOwner(userRepository.findByPhone(userPhone).orElseThrow(() -> new NullPointerException("User with such id not found."))).orElseThrow(() -> new NullPointerException("No wallet for this user created."));
    }

    public List<Transaction> getTransactionHistory(String userPhone){
        return walletRepository.findByOwner(userRepository.findByPhone(userPhone).orElseThrow(() -> new NullPointerException("Such user doesn't exist."))).orElseThrow(() -> new NullPointerException("no wallet with such id")).getHistory();
    }
}
