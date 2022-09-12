package com.example.pullpointdev.dto;

import lombok.Data;

import java.util.List;

@Data
public class CreateArtistReq {
    private Long owner;

    private String name;

    private String description;

    private Long category;

    private List<Long> subcategories;
}
