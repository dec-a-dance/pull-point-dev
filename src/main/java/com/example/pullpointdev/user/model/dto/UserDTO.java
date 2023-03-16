package com.example.pullpointdev.user.model.dto;

import com.example.pullpointdev.artist.model.Artist;
import com.example.pullpointdev.wallet.model.Wallet;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
public class UserDTO {
    private long id;

    private String username;

    private String phone;

    private List<Artist> favourites;

    private Boolean isArtist;

    private List<Artist> artists;

    private Wallet wallet;
}
