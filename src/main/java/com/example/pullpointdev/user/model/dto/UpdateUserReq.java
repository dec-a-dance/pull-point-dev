package com.example.pullpointdev.user.model.dto;

import lombok.Data;

@Data
public class UpdateUserReq {
    private long id;

    private String username;

    private String phone;

    //private List<Artist> favourites;
}
