package com.example.pullpointdev.artist.controller;

import com.example.pullpointdev.artist.service.ArtistService;
import com.example.pullpointdev.artist.model.dto.CreateArtistReq;
import com.example.pullpointdev.artist.model.dto.SearchArtistsReq;
import com.example.pullpointdev.artist.model.dto.UpdateArtistReq;
import com.example.pullpointdev.artist.model.Artist;
import com.example.pullpointdev.security.JwtFilter;
import com.example.pullpointdev.security.JwtUtil;
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
@RequestMapping("/artist")
@Tag(name = "artist", description = "Artist API")
public class ArtistController {
    private final ArtistService artistService;
    private final JwtUtil jwtUtil;

    @PutMapping()
    @Operation(description = "update artist info")
    @SneakyThrows
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Artist> updateArtist(@RequestHeader("Authorization") String auth, @RequestBody UpdateArtistReq req) {
        return ResponseEntity.ok(artistService.updateArtist(req, jwtUtil.phoneFromFullToken(auth)));
    }

    @PostMapping()
    @Operation(description = "create new artist")
    @SneakyThrows
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Artist> createArtist(@RequestHeader("Authorization") String auth, @RequestBody CreateArtistReq req) {
        return ResponseEntity.ok(artistService.createArtist(req, jwtUtil.phoneFromFullToken(auth)));
    }

    @PostMapping("/search")
    @Operation(description = "search artist by parameters")
    public ResponseEntity<List<Artist>> filter(@RequestBody SearchArtistsReq req) {
        return ResponseEntity.ok(artistService.searchArtists(req));
    }

    @GetMapping("byUser/{userId}")
    @Operation(description = "get all artist of user via {userId}")
    public ResponseEntity<List<Artist>> getMyArtists(@PathVariable Long userId) {
        return ResponseEntity.ok(artistService.getArtistsInfo(userId));
    }

    @DeleteMapping("/{id}")
    @Operation(description = "delete artist")
    @SneakyThrows
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity deleteArtist(@RequestHeader("Authorization") String auth, @PathVariable Long id) {
        artistService.deleteArtist(id, jwtUtil.phoneFromFullToken(auth));
        return ResponseEntity.ok(null);
    }

    @GetMapping("/check/{name}")
    @Operation(description = "check if username free")
    public ResponseEntity<Boolean> checkName(@PathVariable String name){
        return ResponseEntity.ok(artistService.checkUsername(name));
    }
}
