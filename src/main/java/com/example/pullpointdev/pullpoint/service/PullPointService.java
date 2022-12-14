package com.example.pullpointdev.pullpoint.service;

import com.example.pullpointdev.pullpoint.model.dto.CreatePullPointReq;
import com.example.pullpointdev.artist.model.Artist;
import com.example.pullpointdev.category.model.Category;
import com.example.pullpointdev.pullpoint.model.PullPoint;
import com.example.pullpointdev.artist.repository.ArtistRepository;
import com.example.pullpointdev.category.repository.CategoryRepository;
import com.example.pullpointdev.pullpoint.repository.PullPointRepository;
import com.example.pullpointdev.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PullPointService {
    private final PullPointRepository pullPointRepository;
    private final ArtistRepository artistRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public List<PullPoint> getAllPullPoints(){
        return pullPointRepository.findAll();
    }

    public boolean createPullPoint(CreatePullPointReq req) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("dd.MM.yyyy:hh.mm");
        PullPoint pp = new PullPoint();
        List<Artist> artists;
        List<Category> subcategories;
        System.out.println(req.subcategories);
        pp.setSubcategories(new ArrayList<>());
        if (!(req.getArtists() == null)) {
            for (long id : req.getArtists()) {
                artists = pp.getArtists();
                artists.add(artistRepository.findById(id).orElseThrow());
                pp.setArtists(artists);
            }
        }
        for (Long id : req.subcategories) {
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
        pp.setOwner(artistRepository.findById(req.getOwner()).orElseThrow());
        pullPointRepository.save(pp);
        return true;
    }
}
