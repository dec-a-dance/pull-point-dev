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
        public Transaction input(@RequestHeader("Authorization") String auth, @RequestBody InputOutputReq req){
        return financeService.currencyInput(jwtUtil.subjectFromToken(jwtUtil.parseToken(auth)), req.getSum());
    }

    @PostMapping("/transfer")
    @SneakyThrows()
    public Transaction transfer(@RequestHeader("Authorization") String auth, @RequestBody TransferReq req){
        return financeService.transfer(jwtUtil.subjectFromToken(jwtUtil.parseToken(auth)), req.getSum(), req.getArtistName());
    }

    @PostMapping("/output")
    @SneakyThrows
    public Transaction output(@RequestHeader("Authorization") String auth, @RequestBody InputOutputReq req){
        return financeService.output(jwtUtil.subjectFromToken(jwtUtil.parseToken(auth)), req.getSum());
    }
}
