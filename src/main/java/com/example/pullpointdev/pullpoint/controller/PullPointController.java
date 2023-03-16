package com.example.pullpointdev.pullpoint.controller;

import com.example.pullpointdev.pullpoint.model.dto.CreatePullPointReq;
import com.example.pullpointdev.pullpoint.model.PullPoint;
import com.example.pullpointdev.pullpoint.service.PullPointServiceImpl;
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
@RequestMapping("/pull_point")
@Tag(name="pull_point", description = "Pull point API")
public class PullPointController {
    private final PullPointServiceImpl pullPointServiceImpl;

    private final JwtUtil jwtUtil;

    @GetMapping()
    @Operation(description = "get all pull points")
    public ResponseEntity<List<PullPoint>> getAllPullPoints(){
        List<PullPoint> points = pullPointServiceImpl.getAllPullPoints();
        return ResponseEntity.ok(points);
    }

    @PostMapping()
    @Operation(description = "create new pull point")
    @SneakyThrows
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<PullPoint> createPP(@RequestHeader("Authorization") String auth, @RequestBody CreatePullPointReq req){
        PullPoint result =pullPointServiceImpl.createPullPoint(req, jwtUtil.phoneFromFullToken(auth));
        return ResponseEntity.ok(result);
    }
}
