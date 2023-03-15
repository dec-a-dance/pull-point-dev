package com.example.pullpointdev.wallet.controller;

import com.example.pullpointdev.security.JwtUtil;
import com.example.pullpointdev.wallet.exception.IncorrectBalanceException;
import com.example.pullpointdev.wallet.model.Transaction;
import com.example.pullpointdev.wallet.model.dto.InputOutputReq;
import com.example.pullpointdev.wallet.model.dto.TransferReq;
import com.example.pullpointdev.wallet.service.FinanceService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/finance")
@Tag(name = "finance", description = "Finance API")
public class FinanceController {
    private final FinanceService financeService;
    private final JwtUtil jwtUtil;

    @PostMapping("/input")
    @SneakyThrows
        public ResponseEntity<Transaction> input(@RequestHeader("Authorization") String auth, @RequestBody InputOutputReq req){
        return ResponseEntity.ok(financeService.currencyInput(jwtUtil.phoneFromFullToken(auth), req.getSum()));
    }

    @PostMapping("/transfer")
    @SneakyThrows()
    public ResponseEntity<Transaction> transfer(@RequestHeader("Authorization") String auth, @RequestBody TransferReq req){
        return ResponseEntity.ok(financeService.transfer(jwtUtil.phoneFromFullToken(auth), req.getSum(), req.getArtistName()));
    }

    @PostMapping("/output")
    @SneakyThrows
    public ResponseEntity<Transaction> output(@RequestHeader("Authorization") String auth, @RequestBody InputOutputReq req){
        return ResponseEntity.ok(financeService.output(jwtUtil.phoneFromFullToken(auth), req.getSum()));
    }
}
