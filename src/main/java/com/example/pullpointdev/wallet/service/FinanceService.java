package com.example.pullpointdev.wallet.service;

import com.example.pullpointdev.artist.model.Artist;
import com.example.pullpointdev.artist.repository.ArtistRepository;
import com.example.pullpointdev.user.model.User;
import com.example.pullpointdev.user.repository.UserRepository;
import com.example.pullpointdev.wallet.exception.IncorrectBalanceException;
import com.example.pullpointdev.wallet.model.Transaction;
import com.example.pullpointdev.wallet.model.TransactionType;
import com.example.pullpointdev.wallet.model.Wallet;
import com.example.pullpointdev.wallet.repository.TransactionRepository;
import com.example.pullpointdev.wallet.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.tomcat.jni.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Date;
import java.util.List;

//todo bank service

@Service
@RequiredArgsConstructor
public class FinanceService {
    private final TransactionRepository transactionRepository;
    private final WalletRepository walletRepository;
    private final UserRepository userRepository;
    private final ArtistRepository artistRepository;

    public Transaction currencyInput(String userPhone, Long sum) {
        User owner = userRepository.findByPhone(userPhone).orElseThrow(() -> new NullPointerException("No user found."));
        Wallet wallet = walletRepository.findByOwner(owner).orElseThrow(() -> new NullPointerException("No wallet found."));
        Transaction transaction = new Transaction();
        transaction.setType(TransactionType.INPUT);
        transaction.setTimestamp(new Date());
        transaction.setSum(sum);
        transaction.setOwner(wallet.getOwner());
        transaction = transactionRepository.save(transaction);
        wallet.setBalance(wallet.getBalance() + sum);
        wallet.getHistory().add(transaction);
        walletRepository.save(wallet);
        return transaction;
    }

    @Transactional(rollbackFor = {IncorrectBalanceException.class})
    public Transaction transfer(String userPhone, Long sum, String receiverName) {
        User owner = userRepository.findByPhone(userPhone).orElseThrow(() -> new NullPointerException("No user found."));
        Wallet wallet = walletRepository.findByOwner(owner).orElseThrow(() -> new NullPointerException("You don't have wallet."));
        Artist receiver = artistRepository.findByName(receiverName).orElseThrow(() -> new NullPointerException("No artist found."));
        Wallet receiverWallet = walletRepository.findByOwner(receiver.getOwner()).orElseThrow(() -> new NullPointerException("Artist doesn't have a wallet. Why? I don't know. Egor, chini."));

        receiverWallet.setBalance(receiverWallet.getBalance() + sum);
        wallet.setBalance(wallet.getBalance() - sum);
        Transaction transaction = new Transaction();
        transaction.setSum(sum);
        transaction.setTimestamp(new Date());
        transaction.setType(TransactionType.TRANSFER);
        transaction.setOwner(wallet.getOwner());
        transaction.setReceiver(receiver);
        transaction = transactionRepository.save(transaction);
        wallet.getHistory().add(transaction);
        receiverWallet.getHistory().add(transaction);
        walletRepository.saveAll(List.of(wallet, receiverWallet));
        if (wallet.getBalance() < 0) {
            throw new IncorrectBalanceException("Not enough money");
        }
        return transaction;
    }

    @Transactional(rollbackFor = {IncorrectBalanceException.class})
    public Transaction output(String userPhone, Long sum){
        User owner = userRepository.findByPhone(userPhone).orElseThrow(() -> new NullPointerException("No user found."));
        Wallet wallet = walletRepository.findByOwner(owner).orElseThrow(() -> new NullPointerException("No wallet found."));
        Transaction transaction = new Transaction();
        transaction.setType(TransactionType.OUTPUT);
        transaction.setTimestamp(new Date());
        transaction.setSum(sum);
        transaction.setOwner(wallet.getOwner());
        transaction = transactionRepository.save(transaction);
        wallet.setBalance(wallet.getBalance() - sum);
        wallet.getHistory().add(transaction);
        wallet = walletRepository.save(wallet);
        if (wallet.getBalance() < 0) {
            throw new IncorrectBalanceException("Not enough money");
        }
        return transaction;
    }
}
