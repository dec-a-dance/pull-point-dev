package com.example.pullpointdev.controller;

import com.example.pullpointdev.entity.PullPoint;
import com.example.pullpointdev.service.GuestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/guest")
public class GuestController {
    private final GuestService guestService;

    @GetMapping("/pull_points")
    public ResponseEntity<List<PullPoint>> getAllPullPoints(){
        List<PullPoint> points = guestService.getAllPullPoints();
        return ResponseEntity.ok(points);
    }

}
