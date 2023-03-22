package com.example.pullpointdev.pullpoint.service;

import com.example.pullpointdev.artist.exception.NotYourArtistException;
import com.example.pullpointdev.notification.model.PlannedNotification;
import com.example.pullpointdev.notification.model.PlannedNotificationType;
import com.example.pullpointdev.notification.repository.PlannedNotificationRepository;
import com.example.pullpointdev.pullpoint.model.dto.CreatePullPointReq;
import com.example.pullpointdev.artist.model.Artist;
import com.example.pullpointdev.category.model.Category;
import com.example.pullpointdev.pullpoint.model.PullPoint;
import com.example.pullpointdev.artist.repository.ArtistRepository;
import com.example.pullpointdev.category.repository.CategoryRepository;
import com.example.pullpointdev.pullpoint.repository.PullPointRepository;
import com.example.pullpointdev.user.model.User;
import com.example.pullpointdev.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PullPointServiceImpl implements PullPointService{
    private final PullPointRepository pullPointRepository;
    private final ArtistRepository artistRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final PlannedNotificationRepository plannedNotificationRepository;

    public List<PullPoint> getAllPullPoints(){
        return pullPointRepository.findAll();
    }

    public PullPoint createPullPoint(CreatePullPointReq req, String ownerPhone) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("dd.MM.yyyy:HH.mm");
        PullPoint pp = new PullPoint();
        Artist artist = artistRepository.findById(req.getOwner()).orElseThrow(() -> new NullPointerException("No such artist with id " + req.getOwner()));
        User tokenOwner = userRepository.findByPhone(ownerPhone).orElseThrow(() -> new NullPointerException("No such user with phone " + ownerPhone));
        if (tokenOwner != artist.getOwner()){
            throw new NotYourArtistException("It's not your artist.");
        }
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
        pp.setOwnerAccount(artist.getOwner());
        pp.setAddress(req.getAddress());
        pp.setLatitude(req.getLatitude());
        pp.setLongitude(req.getLongitude());
        pp.setDescription(req.getDescription());
        pp.setCategory(categoryRepository.findById(req.getCategory()).orElseThrow());
        pp.setStartTime(new Date(format.parse(req.getStartTime()).getTime() + 10800000));
        pp.setEndTime(new Date(format.parse(req.getEndTime()).getTime() + 10800000));
        pp.setOwner(artist);
        pp = pullPointRepository.save(pp);
        generateNots(artist, pp);
        return pp;
    }
    public void closePP(String phone){
        User rec = userRepository.findByPhone(phone).orElseThrow(() -> new NullPointerException("No such user with phone " + phone));
        pullPointRepository.deleteByOwnerAccount(rec);
        plannedNotificationRepository.deleteByReceiver(rec);
    }

    private void generateNots(Artist artist, PullPoint pp){
        List<PlannedNotification> nots = new ArrayList<>();
        //generating end pp notification
        PlannedNotification endNot = new PlannedNotification();
        endNot.setReceiver(artist.getOwner());
        endNot.setTime(pp.getEndTime());
        endNot.setType(PlannedNotificationType.PP_END);
        endNot.setArtist(artist);
        nots.add(endNot);
        //generating warning notification
        PlannedNotification warnNot = new PlannedNotification();
        warnNot.setReceiver(artist.getOwner());
        warnNot.setTime(new Date(pp.getEndTime().getTime() - 600000));
        warnNot.setType(PlannedNotificationType.PP_END_WARN);
        warnNot.setArtist(artist);
        nots.add(warnNot);
        //generating notification about pp start
        PlannedNotification startNot = new PlannedNotification();
        startNot.setTime(pp.getStartTime());
        startNot.setType(PlannedNotificationType.SUBSCRIBE_START);
        startNot.setArtist(artist);
        nots.add(startNot);
        if (pp.getStartTime().getTime() - new Date().getTime() > 600000){
            //generating notification about pp creation
            PlannedNotification createNot = new PlannedNotification();
            createNot.setTime(new Date());
            createNot.setType(PlannedNotificationType.SUBSCRIBE_CREATE);
            createNot.setArtist(artist);
            nots.add(createNot);
        }
        plannedNotificationRepository.saveAll(nots);
    }
}
