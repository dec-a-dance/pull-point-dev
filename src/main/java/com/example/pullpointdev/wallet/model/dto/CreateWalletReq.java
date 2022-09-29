package com.example.pullpointdev.wallet.model.dto;

import lombok.Data;

@Data
public class CreateWalletReq {
    private Long userId;
    private String credentials;
}
