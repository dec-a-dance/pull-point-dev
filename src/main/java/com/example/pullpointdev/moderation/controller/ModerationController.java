package com.example.pullpointdev.moderation.controller;

import com.example.pullpointdev.moderation.model.req.AuthModeratorReq;
import com.example.pullpointdev.moderation.service.ModerationService;
import com.example.pullpointdev.user.model.User;
import com.example.pullpointdev.user.model.dto.ApproveTokenResp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name="moderation", description = "moderation API")
public class ModerationController {
    private final ModerationService moderationService;

    @PostMapping("/register")
    @Operation(description = "register moderator account")
    public ResponseEntity<User> register(@RequestBody AuthModeratorReq req){
        return ResponseEntity.ok(moderationService.registerModerator(req));
    }

    @PostMapping("/log-in")
    @Operation(description = "login moderator")
    public ResponseEntity<ApproveTokenResp> login(@RequestBody AuthModeratorReq req){
        return ResponseEntity.ok(moderationService.loginModerator(req));
    }
}
