package com.example.pullpointdev.user.controller;

import com.example.pullpointdev.artist.model.Artist;
import com.example.pullpointdev.security.JwtUtil;
import com.example.pullpointdev.user.model.dto.*;
import com.example.pullpointdev.user.model.User;
import com.example.pullpointdev.user.service.AuthServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Tag(name="user", description = "Users API")
public class UserController {
    private final AuthServiceImpl authServiceImpl;
    private final JwtUtil jwtUtil;

    @PostMapping("/code")
    @Operation(description = "send a code to user")
    @SneakyThrows
    public ResponseEntity<TokenHandler> sendToken(@RequestBody SendTokenReq req){
        String resp = authServiceImpl.generateAndSendToken(req.getPhone());
        TokenHandler tknHndlr = new TokenHandler();
        tknHndlr.setCode(resp);
        return ResponseEntity.ok(tknHndlr);
    }

    @PostMapping("/verify")
    @Operation(description = "verify that code is correct")
    @SneakyThrows
    public ResponseEntity<ApproveTokenResp> verify(@RequestBody ApproveTokenReq req){
        ApproveTokenResp resp = authServiceImpl.checkToken(req.getPhone(), req.getToken());
        return ResponseEntity.ok(resp);
    }

    @PutMapping()
    @Operation(description = "update user info")
    @SneakyThrows
    public ResponseEntity<User> updateUser(@RequestBody UpdateUserReq req){
        return ResponseEntity.ok(authServiceImpl.updateUser(req));
    }

    @GetMapping("/check/{name}")
    @Operation(description = "check if username free")
    @SneakyThrows
    public ResponseEntity<Boolean> checkName(@PathVariable String name){
        return ResponseEntity.ok(authServiceImpl.checkUsername(name));
    }

    @PostMapping("/refresh")
    @SneakyThrows
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApproveTokenResp> refresh(@RequestHeader("Authorization") String auth){
        return ResponseEntity.ok(authServiceImpl.refresh(jwtUtil.phoneFromFullToken(auth)));
    }

    @GetMapping("/artists")
    @SneakyThrows
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<Artist>> getArtists(@RequestHeader("Authorization") String auth){
        return ResponseEntity.ok(authServiceImpl.getArtists(jwtUtil.phoneFromFullToken(auth)));
    }
}
