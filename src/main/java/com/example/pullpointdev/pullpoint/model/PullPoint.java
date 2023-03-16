package com.example.pullpointdev.pullpoint.model;

import com.example.pullpointdev.artist.model.Artist;
import com.example.pullpointdev.category.model.Category;
import com.example.pullpointdev.user.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "pull_point")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PullPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(name="name")
    String name;

    @Column(name="lat")
    private double latitude;

    @Column(name="long")
    private double longitude;

    @Column(name="address")
    private String address;

    @Column(name="description")
    String description;

    @ManyToOne
    Artist owner;

    @OneToOne
    User ownerAccount;

    @OneToMany
    List<Artist> artists;

    @ManyToOne
    Category category;

    @ManyToMany
    List<Category> subcategories;

    @Column(name="start_time")
    private Date startTime;

    @Column(name="end_time")
    private Date endTime;

}
