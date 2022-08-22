package com.example.pullpointdev.dto;

import com.example.pullpointdev.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApproveTokenResp {
    private boolean result;
    private User user;
}
