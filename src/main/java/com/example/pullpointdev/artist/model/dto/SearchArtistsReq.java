package com.example.pullpointdev.artist.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class SearchArtistsReq {
    private String name;
    private Long category;
    private List<Long> subcategories;
}
