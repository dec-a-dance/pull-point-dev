package com.example.pullpointdev.dto;

import com.example.pullpointdev.entity.Category;
import com.example.pullpointdev.entity.User;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
public class SearchArtistsReq {
    private String name;
    private Long category;
    private List<Long> subcategories;
}
