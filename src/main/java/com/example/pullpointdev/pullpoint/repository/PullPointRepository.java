package com.example.pullpointdev.pullpoint.repository;

import com.example.pullpointdev.artist.model.Artist;
import com.example.pullpointdev.pullpoint.model.PullPoint;
import com.example.pullpointdev.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PullPointRepository extends JpaRepository<PullPoint, Long> {
    List<PullPoint> findAll();

    void deleteByOwnerAccount(User owner);
}
