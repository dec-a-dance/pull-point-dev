package com.example.pullpointdev.user.model.dto;

import lombok.Data;

@Data
public class ApproveTokenReq {
    private String phone;
    private String token;
}
