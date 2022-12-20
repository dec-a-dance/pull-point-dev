package com.example.pullpointdev.user.controller;

import com.example.pullpointdev.artist.model.Artist;
import com.example.pullpointdev.security.JwtUtil;
import com.example.pullpointdev.user.service.FavouritesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/favs")
@Tag(name="favourites", description = "favourites API")
public class FavouritesController {
    private final FavouritesService service;
    private final JwtUtil jwtUtil;

    @GetMapping()
    @PreAuthorize("isAuthenticated()")
    @Operation(description = "get user's list of favourites")
    public ResponseEntity<List<Artist>> getFavs(@RequestHeader("Authorization") String auth){
        return ResponseEntity.ok(service.getFavourites(jwtUtil.subjectFromToken(jwtUtil.parseToken(auth))));
    }

    @PostMapping("/{artistId}")
    @PreAuthorize("isAuthenticated()")
    @Operation(description = "add artist to favourites")
    public ResponseEntity addToFavs(@RequestHeader("Authorization") String auth, @PathVariable Long artistId){
        service.addToFavourites(jwtUtil.subjectFromToken(jwtUtil.parseToken(auth)), artistId);
        return ResponseEntity.ok("added");
    }
}
