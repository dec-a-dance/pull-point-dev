package com.example.pullpointdev.user.model;

import com.example.pullpointdev.user.model.User;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="user_auth")
public class UserAuth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    @OneToOne
    private User owner;

    @Column(name="token")
    private String token;

    @Column(name="used")
    private boolean used;
}
