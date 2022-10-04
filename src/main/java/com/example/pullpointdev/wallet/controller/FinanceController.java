package com.example.pullpointdev.wallet.controller;

import com.example.pullpointdev.security.JwtUtil;
import com.example.pullpointdev.wallet.exception.IncorrectBalanceException;
import com.example.pullpointdev.wallet.model.Transaction;
import com.example.pullpointdev.wallet.model.dto.InputOutputReq;
import com.example.pullpointdev.wallet.model.dto.TransferReq;
import com.example.pullpointdev.wallet.service.FinanceService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/finance")
public class FinanceController {
    private final FinanceService financeService;
    private final JwtUtil jwtUtil;

    @PostMapping("/input")
    @SneakyThrows
    public Transaction input(@RequestHeader("Authorization") String auth, @RequestBody InputOutputReq req){
        return financeService.currencyInput(jwtUtil.subjectFromToken(jwtUtil.parseToken(auth)), req.getSum());
    }

    @PostMapping("/transfer")
    @SneakyThrows()
    public Transaction transfer(@RequestHeader("Authorization") String auth, @RequestBody TransferReq req){
        return financeService.transfer(jwtUtil.subjectFromToken(jwtUtil.parseToken(auth)), req.getSum(), req.getArtistName());
    }
}
