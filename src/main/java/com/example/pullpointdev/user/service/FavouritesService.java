package com.example.pullpointdev.user.service;

import com.example.pullpointdev.artist.model.Artist;

import java.util.List;

public interface FavouritesService {
    List<Artist> getFavourites(String phone);

    void addToFavourites(String phone, Long artistId);

    void removeFromFavourites(String phone, Long artistId);
}
