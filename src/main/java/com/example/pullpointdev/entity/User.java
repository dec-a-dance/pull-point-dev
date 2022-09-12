package com.example.pullpointdev.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name="user_data")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    @Column(name="username")
    private String username;

    @Column(name="phone")
    private String phone;

    @Column(name="favourites")
    @OneToMany
    private List<Artist> favourites;

    @Column(name="is_artist")
    private Boolean isArtist;

    @OneToMany
    private List<Artist> artists;
}
