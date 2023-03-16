package com.example.pullpointdev.wallet.service;

import com.example.pullpointdev.user.model.User;
import com.example.pullpointdev.user.repository.UserRepository;
import com.example.pullpointdev.wallet.model.Transaction;
import com.example.pullpointdev.wallet.model.TransactionType;
import com.example.pullpointdev.wallet.model.Wallet;
import com.example.pullpointdev.wallet.model.dto.TransactionDTO;
import com.example.pullpointdev.wallet.model.dto.TransactionDirection;
import com.example.pullpointdev.wallet.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService{
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

    public List<TransactionDTO> getTransactionHistory(String userPhone){
        User owner = userRepository.findByPhone(userPhone).orElseThrow(() -> new NullPointerException("Such user doesn't exist."));
        List<Transaction> trans = walletRepository.findByOwner(owner).orElseThrow(() -> new NullPointerException("no wallet with such id")).getHistory();
        List<TransactionDTO> dtos = new ArrayList<>();
        trans.forEach(t -> {
            TransactionDTO dto = new TransactionDTO();
            if (t.getReceiver()!=null && t.getOwner().equals(owner)){
                dto.setDir(TransactionDirection.OUT);
                dto.setSub(t.getReceiver().getOwner());
            }
            else if (t.getType()== TransactionType.OUTPUT){
                dto.setDir(TransactionDirection.OUT);
            }
            else{
                dto.setDir(TransactionDirection.IN);
                if (t.getReceiver()!=null){
                    dto.setSub(t.getOwner());
                }
            }
            dto.setType(t.getType());
            dto.setSum(t.getSum());
            dto.setTimestamp(t.getTimestamp());
            dtos.add(dto);
        });
        return dtos;
    }
}
