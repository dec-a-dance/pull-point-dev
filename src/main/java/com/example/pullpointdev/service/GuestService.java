package com.example.pullpointdev.service;

import com.example.pullpointdev.entity.PullPoint;
import com.example.pullpointdev.repository.PullPointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GuestService {
    private final PullPointRepository pullPointRepository;

    public List<PullPoint> getAllPullPoints(){
        return pullPointRepository.findAll();
    }
}
