package com.example.pullpointdev.wallet.service;

import com.example.pullpointdev.user.model.User;
import com.example.pullpointdev.user.repository.UserRepository;
import com.example.pullpointdev.wallet.model.Transaction;
import com.example.pullpointdev.wallet.model.Wallet;
import com.example.pullpointdev.wallet.model.dto.CreateWalletReq;
import com.example.pullpointdev.wallet.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class WalletService {
    private final WalletRepository walletRepository;
    private final UserRepository userRepository;

    public Wallet createWallet(String credentials, String phone){
        User owner = userRepository.findByPhone(phone).orElseThrow(NullPointerException::new);
        Wallet wallet = new Wallet();
        wallet.setBalance(0L);
        wallet.setOwner(owner);
        wallet.setHistory(new ArrayList<>());
        wallet.setBankCredentials(credentials);
        wallet = walletRepository.save(wallet);
        owner.setWallet(wallet);
        userRepository.save(owner);
        return wallet;
    }
}
