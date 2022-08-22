package com.example.pullpointdev.controller;

import com.example.pullpointdev.dto.CreatePullPointReq;
import com.example.pullpointdev.dto.UpdateArtistReq;
import com.example.pullpointdev.entity.Artist;
import com.example.pullpointdev.service.ArtistService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
