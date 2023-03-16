package com.example.pullpointdev.artist.service;

import com.example.pullpointdev.artist.exception.NotYourArtistException;
import com.example.pullpointdev.artist.model.dto.CreateArtistReq;
import com.example.pullpointdev.artist.model.dto.SearchArtistsReq;
import com.example.pullpointdev.artist.model.dto.UpdateArtistReq;
import com.example.pullpointdev.artist.model.Artist;
import com.example.pullpointdev.category.model.Category;
import com.example.pullpointdev.user.model.User;
import com.example.pullpointdev.artist.repository.ArtistRepository;
import com.example.pullpointdev.category.repository.CategoryRepository;
import com.example.pullpointdev.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ArtistServiceImpl implements ArtistService{
    private final ArtistRepository artistRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;


    public Artist updateArtist(UpdateArtistReq req, String ownerPhone) {
        Artist artist = artistRepository.findById(req.getId()).orElseThrow(() -> new NullPointerException("No artist with id " + req.getId()));
        User tokenOwner = userRepository.findByPhone(ownerPhone).orElseThrow(() -> new NullPointerException("No user with such phone."));
        if(tokenOwner!= artist.getOwner()){
            throw new NotYourArtistException("It's not your artist.");
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
        if (req.getSubcategories() != null && req.getSubcategories().size() != 0) {
            for (Long i : req.getSubcategories()) {
                subcategories.add(categoryRepository.findById(i).orElseThrow());
            }
            artists.removeIf(a -> (!new HashSet<>(a.getSubcategories()).containsAll(subcategories)));
        }

        return artists;
    }

    public List<Artist> getArtistsInfo(long id){
        return userRepository.findById(id).getArtists();
    }

    public Artist createArtist(CreateArtistReq req, String ownerPhone){
        User user = userRepository.findByPhone(ownerPhone).orElseThrow(() -> new NullPointerException("No such user with phone " + ownerPhone));
        user.setIsArtist(true);
        user = userRepository.save(user);
        Artist artist = new Artist();
        artist.setOwner(user);
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
        artist = updateArtist(update, user.getPhone());
        return artist;
    }

    public void deleteArtist(Long id, String ownerPhone){
        Artist artist = artistRepository.findById(id).orElseThrow(() -> new NullPointerException("No such artist with id " + id));
        User tokenOwner = userRepository.findByPhone(ownerPhone).orElseThrow(() -> new NullPointerException("No such user with phone " + ownerPhone));
        if (tokenOwner != artist.getOwner()){
            throw new NotYourArtistException("It's not your artist");
        }
        User user = artist.getOwner();
        user.getArtists().remove(artist);
        userRepository.save(user);
        artistRepository.delete(artist);
    }

    public boolean checkUsername(String username){
        if (artistRepository.findByName(username).orElse(null) == null){
            return true;
        }
        else return false;
    }
}
