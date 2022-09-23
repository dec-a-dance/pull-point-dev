package com.example.pullpointdev.pullpoint.repository;

import com.example.pullpointdev.pullpoint.model.PullPoint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PullPointRepository extends JpaRepository<PullPoint, Long> {
    List<PullPoint> findAll();
}
