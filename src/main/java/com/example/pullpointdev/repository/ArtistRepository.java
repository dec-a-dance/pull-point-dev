package com.example.pullpointdev.repository;

import com.example.pullpointdev.entity.Artist;
import com.example.pullpointdev.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArtistRepository extends JpaRepository<Artist, Long> {
    Artist findByName(String name);

    Optional<Artist> findById(Long id);

    Artist findByOwner(User user);
}
