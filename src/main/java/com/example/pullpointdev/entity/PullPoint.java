package com.example.pullpointdev.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

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

    @OneToOne
    Artist owner;

    @OneToMany
    List<Artist> artists;

    @OneToOne
    Category category;

    @ManyToMany
    List<Category> subcategories;

    @Column(name="start_time")
    private Date startTime;

    @Column(name="end_time")
    private Date endTime;

}
