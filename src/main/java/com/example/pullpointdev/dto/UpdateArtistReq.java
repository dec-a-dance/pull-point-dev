package com.example.pullpointdev.dto;

import com.example.pullpointdev.entity.Category;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
public class UpdateArtistReq {
    private Long id;

    private String name;

    private String description;

    private Long category;

    private List<Long> subcategories;
}
