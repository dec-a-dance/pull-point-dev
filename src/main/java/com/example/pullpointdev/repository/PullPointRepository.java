package com.example.pullpointdev.repository;

import com.example.pullpointdev.entity.PullPoint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PullPointRepository extends JpaRepository<PullPoint, Long> {
    List<PullPoint> findAll();
}
