package com.example.pullpointdev.service;

import com.example.pullpointdev.dto.CreateArtistReq;
import com.example.pullpointdev.dto.CreatePullPointReq;
import com.example.pullpointdev.dto.SearchArtistsReq;
import com.example.pullpointdev.dto.UpdateArtistReq;
import com.example.pullpointdev.entity.Artist;
import com.example.pullpointdev.entity.Category;
import com.example.pullpointdev.entity.PullPoint;
import com.example.pullpointdev.entity.User;
import com.example.pullpointdev.repository.ArtistRepository;
import com.example.pullpointdev.repository.CategoryRepository;
import com.example.pullpointdev.repository.PullPointRepository;
import com.example.pullpointdev.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ArtistService {
    private final ArtistRepository artistRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;


    public Artist updateArtist(UpdateArtistReq req) {
        Artist artist = artistRepository.findById(req.getId()).orElse(null);
        if (artist == null){

        }
        artist.setDescription(req.getDescription());
        List<Category> categories = new ArrayList<>();
        artist.setSubcategories(categories);
        for (long id : req.getSubcategories()) {
            categories = artist.getSubcategories();
            categories.add(categoryRepository.findById(id));
            artist.setSubcategories(categories);
        }
        artist.setCategory(categoryRepository.findById(req.getCategory()).orElseThrow());
        artist.setName(req.getName());
        artistRepository.save(artist);
        return artist;
    }

    public List<Artist> searchArtists(SearchArtistsReq req) {
        Artist artist = new Artist();
        if (req.getCategory() != null) {
            artist.setCategory(categoryRepository.findById(req.getCategory()).orElseThrow());
        }

        List<Artist> artists = artistRepository.findAll(Example.of(artist));
        if (req.getName() != null) {
            artists.removeIf(a -> (!a.getName().contains(req.getName())));
        }
        List<Category> subcategories = new ArrayList<>();
        if (req.getSubcategories() != null) {
            for (Long i : req.getSubcategories()) {
                subcategories.add(categoryRepository.findById(i).orElseThrow());
            }
            artists.removeIf(a -> (!a.getSubcategories().containsAll(subcategories)));
        }

        return artists;
    }

    public List<Artist> getArtistsInfo(long id){
        return userRepository.findById(id).getArtists();
    }

    public Artist createArtist(CreateArtistReq req){
        User user = userRepository.findById(req.getOwner()).orElseThrow();
        user.setIsArtist(true);
        user = userRepository.save(user);
        Artist artist = new Artist();
        artist.setOwner(userRepository.findById(req.getOwner()).orElseThrow());
        artist = artistRepository.save(artist);
        List<Artist> ar = user.getArtists();
        ar.add(artist);
        log.info(String.valueOf(user.getArtists().size()));
        user.setArtists(ar);
        log.info(String.valueOf(user.getArtists().size()));
        userRepository.save(user);
        UpdateArtistReq update = new UpdateArtistReq();
        update.setId(artist.getId());
        update.setCategory(req.getCategory());
        update.setSubcategories(req.getSubcategories());
        update.setName(req.getName());
        update.setDescription(req.getDescription());
        artist = updateArtist(update);
        return artist;
    }

    public void deleteArtist(long id){
        Artist artist = artistRepository.findById(id).orElseThrow(NullPointerException::new);
        User user = artist.getOwner();
        user.getArtists().remove(artist);
        userRepository.save(user);
        artistRepository.delete(artist);
    }
}
