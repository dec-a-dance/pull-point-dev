package com.example.pullpointdev.dto;

import com.example.pullpointdev.entity.Artist;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
public class UpdateUserReq {
    private long id;

    private String username;

    private String phone;

    //private List<Artist> favourites;
}
