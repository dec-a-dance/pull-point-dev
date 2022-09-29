package com.example.pullpointdev.wallet.controller;

import com.example.pullpointdev.security.JwtUtil;
import com.example.pullpointdev.wallet.model.Wallet;
import com.example.pullpointdev.wallet.model.dto.CreateWalletReq;
import com.example.pullpointdev.wallet.service.WalletService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wallet")
@RequiredArgsConstructor
public class WalletController {
    private final WalletService walletService;
    private final JwtUtil jwtUtil;

    @PostMapping("/{credential}")
    @SneakyThrows
    @Operation(description = "create wallet for user")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Wallet> createWallet(@PathVariable String credential, @RequestHeader("Authorization") String auth){
        return ResponseEntity.ok(walletService.createWallet(credential, jwtUtil.subjectFromToken(auth.substring(7))));
    }

   // @PostMapping()
}
