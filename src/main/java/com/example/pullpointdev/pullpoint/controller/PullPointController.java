package com.example.pullpointdev.pullpoint.controller;

import com.example.pullpointdev.pullpoint.model.dto.CreatePullPointReq;
import com.example.pullpointdev.pullpoint.model.PullPoint;
import com.example.pullpointdev.pullpoint.service.PullPointService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pull_point")
@Tag(name="pull_point", description = "Pull point API")
public class PullPointController {
    private final PullPointService pullPointService;

    @GetMapping()
    public ResponseEntity<List<PullPoint>> getAllPullPoints(){
        List<PullPoint> points = pullPointService.getAllPullPoints();
        return ResponseEntity.ok(points);
    }

    @PostMapping()
    @SneakyThrows
    public ResponseEntity<String> createPP(@RequestBody CreatePullPointReq req){
        if(pullPointService.createPullPoint(req)){
            return ResponseEntity.ok("Pull Point has been successfully created.");
        }
        else{
            return new ResponseEntity<>("Something gone wrong during creation of PP", HttpStatus.I_AM_A_TEAPOT);
        }
    }
}
