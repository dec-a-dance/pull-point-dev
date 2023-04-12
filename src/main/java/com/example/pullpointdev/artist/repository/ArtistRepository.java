package com.example.pullpointdev.artist.repository;

import com.example.pullpointdev.artist.model.Artist;
import com.example.pullpointdev.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ArtistRepository extends JpaRepository<Artist, Long> {
    Optional<Artist> findByName(String name);

    Optional<Artist> findById(Long id);

    List<Artist> findAllByVerification(Boolean verification);
}
