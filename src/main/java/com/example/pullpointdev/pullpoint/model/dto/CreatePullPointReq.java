package com.example.pullpointdev.pullpoint.model.dto;

import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.List;

@Data
@JsonNaming
public class CreatePullPointReq{
    String name;

    private double latitude;

    private double longitude;

    private String address;

    private String description;

    private long owner;

    private List<Long> artists;

    private Long category;

    public List<Long> subcategories;

    private String startTime;

    private String endTime;
}
