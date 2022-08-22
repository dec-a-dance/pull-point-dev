package com.example.pullpointdev.repository;

import com.example.pullpointdev.entity.Artist;
import com.example.pullpointdev.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist, Long> {
    Artist findByName(String name);

    Artist findById(long id);

    Artist findByUser(User user);
}
