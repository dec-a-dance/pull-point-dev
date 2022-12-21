package com.example.pullpointdev.user.service;

import com.example.pullpointdev.artist.model.Artist;
import com.example.pullpointdev.artist.repository.ArtistRepository;
import com.example.pullpointdev.security.JwtUtil;
import com.example.pullpointdev.user.model.User;
import com.example.pullpointdev.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FavouritesService {
    private final UserRepository userRepository;
    private final ArtistRepository artistRepository;
    private final JwtUtil jwtUtil;

    public List<Artist> getFavourites(String phone){
        return userRepository.findByPhone(phone).orElseThrow(() -> new NullPointerException("no such user")).getFavourites();
    }

    public void addToFavourites(String phone, Long artistId){
        User owner = userRepository.findByPhone(phone).orElseThrow(() -> new NullPointerException("no such user"));
        Artist artist = artistRepository.findById(artistId).orElseThrow(() -> new NullPointerException("no such artists"));
        List<Artist> list = owner.getFavourites();
        if (list.contains(artist)){
            throw new RuntimeException("already in favs");
        }
        list.add(artist);
        owner.setFavourites(list);
        userRepository.save(owner);
    }

    public void removeFromFavourites(String phone, Long artistId){
        User owner = userRepository.findByPhone(phone).orElseThrow(() -> new NullPointerException("no such user"));
        Artist artist = artistRepository.findById(artistId).orElseThrow(() -> new NullPointerException("no such artists"));
        List<Artist> list = owner.getFavourites();
        if (list.contains(artist)){
            list.remove(artist);
            return;
        }
        throw new NullPointerException("not in favs");
    }
}
