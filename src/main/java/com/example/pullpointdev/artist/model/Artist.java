package com.example.pullpointdev.artist.model;

import com.example.pullpointdev.category.model.Category;
import com.example.pullpointdev.user.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "artist")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;

    @OneToOne
    private Category category;

    @Column(name="subcategories")
    @ManyToMany
    private List<Category> subcategories;

    @ManyToOne
    @JsonIgnore
    private User owner;

    @Column(name="verification")
    private Boolean verification;
}
