package com.example.pullpointdev.artist.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class UpdateArtistReq {
    private Long id;

    private String name;

    private String description;

    private Long category;

    private List<Long> subcategories;
}
