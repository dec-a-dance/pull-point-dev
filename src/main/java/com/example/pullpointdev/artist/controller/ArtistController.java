package com.example.pullpointdev.artist.controller;

import com.example.pullpointdev.artist.service.ArtistService;
import com.example.pullpointdev.artist.model.dto.CreateArtistReq;
import com.example.pullpointdev.artist.model.dto.SearchArtistsReq;
import com.example.pullpointdev.artist.model.dto.UpdateArtistReq;
import com.example.pullpointdev.artist.model.Artist;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
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
