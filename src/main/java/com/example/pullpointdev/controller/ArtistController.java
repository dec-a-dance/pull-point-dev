package com.example.pullpointdev.controller;

import com.example.pullpointdev.dto.CreateArtistReq;
import com.example.pullpointdev.dto.CreatePullPointReq;
import com.example.pullpointdev.dto.SearchArtistsReq;
import com.example.pullpointdev.dto.UpdateArtistReq;
import com.example.pullpointdev.entity.Artist;
import com.example.pullpointdev.service.ArtistService;
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
public class ArtistController {
    private final ArtistService artistService;

    @PostMapping("/pull_point")
    @SneakyThrows
    public ResponseEntity<String> createPP(@RequestBody CreatePullPointReq req){
        if(artistService.createPullPoint(req)){
            return ResponseEntity.ok("Pull Point has been successfully created.");
        }
        else{
            return new ResponseEntity<>("Something gone wrong during creation of PP", HttpStatus.I_AM_A_TEAPOT);
        }
    }

    @PutMapping()
    @SneakyThrows
    public ResponseEntity<Artist> updateArtist(@RequestBody UpdateArtistReq req){
        return ResponseEntity.ok(artistService.updateArtist(req));
    }

    @PostMapping()
    @SneakyThrows
    public ResponseEntity<Artist> createArtist(@RequestBody CreateArtistReq req){
        return ResponseEntity.ok(artistService.createArtist(req));
    }

    @PostMapping("/filter")
    public ResponseEntity<List<Artist>> filter(@RequestBody SearchArtistsReq req){
        return ResponseEntity.ok(artistService.searchArtists(req));
    }

    @GetMapping("byUser/{userId}")
    public ResponseEntity<List<Artist>> getMyArtists(@PathVariable Long userId){
        return ResponseEntity.ok(artistService.getArtistsInfo(userId));
    }

    @DeleteMapping("/{id}")
    @SneakyThrows
    public ResponseEntity<Boolean> deleteArtist(@PathVariable Long id){
        artistService.deleteArtist(id);
        return ResponseEntity.ok(true);
    }
}
