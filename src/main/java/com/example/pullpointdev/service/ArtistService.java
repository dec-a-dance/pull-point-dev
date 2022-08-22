package com.example.pullpointdev.service;

import com.example.pullpointdev.dto.CreatePullPointReq;
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
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ArtistService {
    private final PullPointRepository pullPointRepository;
    private final ArtistRepository artistRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public boolean createPullPoint(CreatePullPointReq req) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("dd.MM.yyyy:hh.mm");
        PullPoint pp = new PullPoint();
        List<Artist> artists;
        List<Category> subcategories;
        System.out.println(req.subcategories);
        pp.setSubcategories(new ArrayList<>());
        if(!(req.getArtists() == null)) {
            for (long id : req.getArtists()) {
                artists = pp.getArtists();
                artists.add(artistRepository.findById(id));
                pp.setArtists(artists);
            }
        }
        for (Long id: req.subcategories){
            subcategories = pp.getSubcategories();
            subcategories.add(categoryRepository.findById(id.longValue()));
            pp.setSubcategories(subcategories);
        }
        pp.setName(req.getName());
        pp.setAddress(req.getAddress());
        pp.setLatitude(req.getLatitude());
        pp.setLongitude(req.getLongitude());
        pp.setDescription(req.getDescription());
        pp.setCategory(categoryRepository.findById(req.getCategory()).orElseThrow());
        pp.setStartTime(format.parse(req.getStartTime()));
        pp.setEndTime(format.parse(req.getEndTime()));
        pp.setOwner(artistRepository.findByUser(userRepository.findById(req.getOwner())));
        pullPointRepository.save(pp);
        return true;
    }

    public Artist updateArtist(UpdateArtistReq req){
        User user = userRepository.findById(req.getUser());
        Artist artist = artistRepository.findByUser(user);
        artist.setDescription(req.getDescription());
        List<Category> categories = new ArrayList<>();
        artist.setSubcategories(categories);
        for (long id: req.getSubcategories()){
            categories = artist.getSubcategories();
            categories.add(categoryRepository.findById(id));
            artist.setSubcategories(categories);
        }
        artist.setCategory(categoryRepository.findById(req.getCategory()));
        artist.setName(req.getName());
        artistRepository.save(artist);
        return artist;
    }
}
