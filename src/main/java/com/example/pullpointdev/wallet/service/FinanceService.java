package com.example.pullpointdev.wallet.service;

import com.example.pullpointdev.wallet.model.Transaction;

public interface FinanceService {
    public Transaction currencyInput(String userPhone, Long sum);

    public Transaction transfer(String userPhone, Long sum, String receiverName);
}
