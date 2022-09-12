package com.example.pullpointdev.controller;

import com.example.pullpointdev.dto.CreateArtistReq;
import com.example.pullpointdev.dto.CreatePullPointReq;
import com.example.pullpointdev.dto.SearchArtistsReq;
import com.example.pullpointdev.dto.UpdateArtistReq;
import com.example.pullpointdev.entity.Artist;
import com.example.pullpointdev.service.ArtistService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/artist")
@Tag(name = "artist", description = "Artist API")
public class ArtistController {
    private final ArtistService artistService;

    @PutMapping()
    @SneakyThrows
    public ResponseEntity<Artist> updateArtist(@RequestBody UpdateArtistReq req) {
        return ResponseEntity.ok(artistService.updateArtist(req));
    }

    @PostMapping()
    @SneakyThrows
    public ResponseEntity<Artist> createArtist(@RequestBody CreateArtistReq req) {
        return ResponseEntity.ok(artistService.createArtist(req));
    }

    @PostMapping("/search")
    public ResponseEntity<List<Artist>> filter(@RequestBody SearchArtistsReq req) {
        return ResponseEntity.ok(artistService.searchArtists(req));
    }

    @GetMapping("byUser/{userId}")
    public ResponseEntity<List<Artist>> getMyArtists(@PathVariable Long userId) {
        return ResponseEntity.ok(artistService.getArtistsInfo(userId));
    }

    @DeleteMapping("/{id}")
    @SneakyThrows
    public ResponseEntity<Boolean> deleteArtist(@PathVariable Long id) {
        artistService.deleteArtist(id);
        return ResponseEntity.ok(true);
    }
}
