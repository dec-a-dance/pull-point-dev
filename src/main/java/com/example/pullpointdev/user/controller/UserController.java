package com.example.pullpointdev.user.controller;

import com.example.pullpointdev.user.model.dto.ApproveTokenReq;
import com.example.pullpointdev.user.model.dto.ApproveTokenResp;
import com.example.pullpointdev.user.model.dto.SendTokenReq;
import com.example.pullpointdev.user.model.dto.UpdateUserReq;
import com.example.pullpointdev.user.model.User;
import com.example.pullpointdev.user.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Tag(name="user", description = "Users API")
public class UserController {
    private final AuthService authService;

    @PostMapping("/code")
    @Operation(description = "send a code to user")
    @SneakyThrows
    public ResponseEntity<String> sendToken(@RequestBody SendTokenReq req){
        String resp = authService.generateAndSendToken(req.getPhone());
        return ResponseEntity.ok("Your code is: " + resp);
    }

    @PostMapping("/verify")
    @Operation(description = "verify that code is correct")
    @SneakyThrows
    public ResponseEntity<ApproveTokenResp> verify(@RequestBody ApproveTokenReq req){
        ApproveTokenResp resp = authService.checkToken(req.getPhone(), req.getToken());
        return ResponseEntity.ok(resp);
    }

    @PutMapping()
    @Operation(description = "update user info")
    @SneakyThrows
    public ResponseEntity<User> updateUser(@RequestBody UpdateUserReq req){
        return ResponseEntity.ok(authService.updateUser(req));
    }
}
