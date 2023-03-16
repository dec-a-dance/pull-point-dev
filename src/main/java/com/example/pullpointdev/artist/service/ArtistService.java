package com.example.pullpointdev.artist.service;

import com.example.pullpointdev.artist.model.Artist;
import com.example.pullpointdev.artist.model.dto.CreateArtistReq;
import com.example.pullpointdev.artist.model.dto.SearchArtistsReq;
import com.example.pullpointdev.artist.model.dto.UpdateArtistReq;

import java.util.List;

public interface ArtistService {
    Artist updateArtist(UpdateArtistReq req, String ownerPhone);

    List<Artist> searchArtists(SearchArtistsReq req);

    List<Artist> getArtistsInfo(long id);

    Artist createArtist(CreateArtistReq req, String ownerPhone);

    void deleteArtist(Long id, String ownerPhone);

    boolean checkUsername(String username);
}
