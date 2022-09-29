package com.example.pullpointdev.user.model.dto;

import com.example.pullpointdev.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApproveTokenResp {
    private boolean result;
    private String jwt;
    private User user;
}
