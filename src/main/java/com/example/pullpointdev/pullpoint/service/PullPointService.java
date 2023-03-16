package com.example.pullpointdev.pullpoint.service;

import com.example.pullpointdev.artist.model.Artist;
import com.example.pullpointdev.pullpoint.model.PullPoint;
import com.example.pullpointdev.pullpoint.model.dto.CreatePullPointReq;

import java.text.ParseException;
import java.util.List;

public interface PullPointService {
    List<PullPoint> getAllPullPoints();

    PullPoint createPullPoint(CreatePullPointReq req, String ownerPhone) throws ParseException;

    void closePP(String phone);

    private void generateNots(Artist artist, PullPoint pp) {

    }
}
