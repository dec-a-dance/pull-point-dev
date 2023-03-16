package com.example.pullpointdev.wallet.service;

import com.example.pullpointdev.wallet.model.Wallet;
import com.example.pullpointdev.wallet.model.dto.TransactionDTO;

import java.util.List;

public interface WalletService {
    Wallet createWallet(String credentials, String phone);

    Wallet getWallet(String userPhone);

    List<TransactionDTO> getTransactionHistory(String userPhone);
}
