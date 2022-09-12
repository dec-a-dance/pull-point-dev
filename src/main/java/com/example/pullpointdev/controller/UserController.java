package com.example.pullpointdev.controller;

import com.example.pullpointdev.dto.ApproveTokenReq;
import com.example.pullpointdev.dto.ApproveTokenResp;
import com.example.pullpointdev.dto.SendTokenReq;
import com.example.pullpointdev.dto.UpdateUserReq;
import com.example.pullpointdev.entity.User;
import com.example.pullpointdev.service.AuthService;
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

    @PostMapping("/token")
    @SneakyThrows
    public ResponseEntity<String> sendToken(@RequestBody SendTokenReq req){
        authService.generateAndSendToken(req.getPhone());
        return ResponseEntity.ok("Token has been sent to " + req.getPhone());
    }

    @PostMapping("/verify")
    @SneakyThrows
    public ResponseEntity<ApproveTokenResp> verify(@RequestBody ApproveTokenReq req){
        ApproveTokenResp resp = authService.checkToken(req.getPhone(), req.getToken());
         if (resp.isResult()){
             return ResponseEntity.ok(resp);
         }
         else{
             return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
         }
    }

    @PutMapping()
    @SneakyThrows
    public ResponseEntity<User> updateUser(@RequestBody UpdateUserReq req){
        return ResponseEntity.ok(authService.updateUser(req));
    }
}
