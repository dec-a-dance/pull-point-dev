package com.example.pullpointdev.moderation.controller;

import com.example.pullpointdev.artist.model.Artist;
import com.example.pullpointdev.moderation.model.req.AuthModeratorReq;
import com.example.pullpointdev.moderation.service.ModerationService;
import com.example.pullpointdev.user.model.User;
import com.example.pullpointdev.user.model.dto.ApproveTokenResp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/moderation")
@Tag(name="moderation", description = "moderation API")
public class ModerationController {
    private final ModerationService moderationService;

    @PostMapping("/register")
    @Operation(description = "register moderator account")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<User> register(@RequestBody AuthModeratorReq req){
        return ResponseEntity.ok(moderationService.registerModerator(req));
    }

    @PostMapping("/log-in")
    @Operation(description = "login moderator")
    public ResponseEntity<ApproveTokenResp> login(@RequestBody AuthModeratorReq req){
        return ResponseEntity.ok(moderationService.loginModerator(req));
    }

    @PostMapping("artist-list/{status}")
    @Operation(description = "get a list of artist of one status")
    public ResponseEntity<List<Artist>> getArtistByStatus(@PathVariable String status){
        return ResponseEntity.ok(moderationService.getArtistsByStatus(status));
    }

    @PostMapping("verification/{id}")
    @Operation(description = "verify artist")
    public ResponseEntity<Artist> verifyArtist(@PathVariable Long id){
        return ResponseEntity.ok(moderationService.verifyArtist(id));
    }

    @PostMapping("declination/{id}")
    @Operation(description = "decline artist verification")
    public ResponseEntity<Artist> declineArtist(@PathVariable Long id){
        return ResponseEntity.ok(moderationService.declineArtist(id));
    }
}
