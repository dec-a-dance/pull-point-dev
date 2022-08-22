package com.example.pullpointdev.dto;

import lombok.Data;

@Data
public class ApproveTokenReq {
    private String phone;
    private String token;
}
