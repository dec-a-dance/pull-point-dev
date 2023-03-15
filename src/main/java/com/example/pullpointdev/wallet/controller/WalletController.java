package com.example.pullpointdev.wallet.controller;

import com.example.pullpointdev.security.JwtUtil;
import com.example.pullpointdev.wallet.model.Transaction;
import com.example.pullpointdev.wallet.model.Wallet;
import com.example.pullpointdev.wallet.model.dto.CreateWalletReq;
import com.example.pullpointdev.wallet.model.dto.TransactionDTO;
import com.example.pullpointdev.wallet.service.WalletService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wallet")
@RequiredArgsConstructor
@Tag(name = "wallet", description = "Wallet API")
public class WalletController {
    private final WalletService walletService;
    private final JwtUtil jwtUtil;

    @PostMapping("/{credential}")
    @SneakyThrows
    @Operation(description = "create wallet for user")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Wallet> createWallet(@PathVariable String credential, @RequestHeader("Authorization") String auth){
        return ResponseEntity.ok(walletService.createWallet(credential, jwtUtil.phoneFromFullToken(auth)));
    }

    @GetMapping()
    @SneakyThrows
    @Operation(description = "get a wallet info")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Wallet> getWallet(@RequestHeader("Authorization") String auth){
        return ResponseEntity.ok(walletService.getWallet(jwtUtil.phoneFromFullToken(auth)));
    }

    @GetMapping("/history")
    @SneakyThrows
    @Operation(description = "get transaction history")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<TransactionDTO>> getTransactionHistory(@RequestHeader("Authorization") String auth){
        return ResponseEntity.ok(walletService.getTransactionHistory(jwtUtil.phoneFromFullToken(auth)));
    }


   // @PostMapping()
}
